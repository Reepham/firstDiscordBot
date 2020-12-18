package events.commands;

import events.Util;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Commands {
    //Standard commands !maddin,!joke etc
    String ping;
    String message;


    public void joke(TextChannel channel) {
        JSONObject json = Util.readJsonFromUrl("https://sv443.net/jokeapi/v2/joke/Any?lang=de");

        try {
            message = (String)json.get("setup");
            message = message + "\n"+json.get("delivery");
        }catch (Exception e){
            message = (String) json.get("joke");
        }

        channel.sendMessage(message).queue();
    }

    public void maddin(JDA client, Member member, TextChannel channel){
        LocalDate maddincache = Util.getMaddindate();
        if (maddincache==null){
            Util.insertMaddindate(LocalDate.now());
        } else {
            if (maddincache.isBefore(LocalDate.now().minus(6, ChronoUnit.DAYS))) {
                Util.insertMaddindate(LocalDate.now());
            } else {
                Period cooldown = maddincache.until(LocalDate.now().minus(6, ChronoUnit.DAYS));
                message = "Befehl ist grad auf Cooldown \n Zeit verbleibend: " + cooldown.getDays()*-1 + "Tage";
                channel.sendMessage(message).queue();
                return;
            }
        }

        try {
            ping = client.getUserByTag("Maddin#7057").getAsMention();
        }catch (NullPointerException e){
            ping =  member.getAsMention();
        }
        message = ping + " du suckst!";
        channel.sendMessage(message).queue();
    }

    public void dummheit(TextChannel channel,String memberid){

        double prozentSatz = Util.getQuoteCount(memberid) * 100;
        message = "Du bist zu " + Util.round(prozentSatz,2) + "% dumm!";
        channel.sendMessage(message).queue();

    }

    public void quotes(Guild guild, TextChannel textChannel, String[] receivedMessage) {
        List<TextChannel> channels = guild.getTextChannelsByName("quotes", true);
        for (TextChannel channel : channels) {
            MessageHistory history = new MessageHistory(channel);
            List<Message> messages = history.retrievePast(100).complete();

            if (receivedMessage.length > 1) {
                List<Message> filtermessages = new ArrayList<>();
                for (Message m : messages) {
                    if (m.getContentRaw().matches("(?i).*\\b" + receivedMessage[1] + "\\b.*")) {
                        filtermessages.add(m);
                    }
                }
                messages = filtermessages;
                if (messages.size() == 0) {
                    message = "Leider keine Quotes gefunden";
                    textChannel.sendMessage(message).queue();
                    return;
                }
            }

            int x = new Random().nextInt(messages.size());
            if (messages.get(x).getAttachments().size() > 0) {
                message = messages.get(x).getAttachments().get(0).getProxyUrl();
                message += "\n " + messages.get(x).getContentRaw();
            } else {
                message = messages.get(x).getContentRaw();
            }

            textChannel.sendMessage(message).queue();
        }
    }



}
