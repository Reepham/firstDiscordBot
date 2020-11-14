package events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaddinEvent extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String ping;
        String[] receivedMessage = event.getMessage().getContentRaw().split(" ");
        JDA client = event.getJDA();
        Guild guild = event.getGuild();
        String message;
        if (!event.getMember().getUser().isBot()){
            switch (receivedMessage[0].toLowerCase()){
                case "!maddin":
                    try {
                        ping = client.getUserByTag("Maddin#7057").getAsMention();
                    }catch (NullPointerException e){
                        ping =  event.getMember().getAsMention();
                    }
                    message = ping + " du suckst!";

                    event.getChannel().sendMessage(message).queue();
                    break;

                case "!quote":
                    List<TextChannel> channels = guild.getTextChannelsByName("quotes", true);
                    for (TextChannel channel : channels) {
                        MessageHistory history = new MessageHistory(channel);
                        List<Message> messages = history.retrievePast(100).complete();

                        if (receivedMessage.length>1){
                            List<Message> filtermessages = new ArrayList<>();
                            for (Message m:messages) {
                                if (m.getContentRaw().matches("(?i).*\\b"+receivedMessage[1]+"\\b.*")){
                                    filtermessages.add(m);
                                }
                            }
                            messages = filtermessages;
                            if (messages.size() == 0){
                                message = "Leider keine Quotes gefunden";
                                event.getChannel().sendMessage(message).queue();
                                break;
                            }
                        }

                        Random ran = new Random();
                        int x = ran.nextInt(messages.size());

                        if (messages.get(x).getAttachments().size()>0){
                            message = messages.get(x).getAttachments().get(0).getProxyUrl();
                            message += "\n " +messages.get(x).getContentRaw();
                        } else{
                            message = messages.get(x).getContentRaw();
                        }

                        event.getChannel().sendMessage(message).queue();
                        break;
                    }
            }

        }

    }
}
