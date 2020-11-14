package events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;
import java.util.Random;

public class MaddinEvent extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String ping;
        String[] receivedMessage = event.getMessage().getContentRaw().split(" ");
        JDA client = event.getJDA();
        Guild guild = event.getGuild();
        String message = "Fallback-Message";
        if (!event.getMember().getUser().isBot()){
            switch (receivedMessage[0]){
                case "!Maddin":
                    try {
                        ping = client.getUserByTag("Maddin#7057").getAsMention();
                    }catch (NullPointerException e){
                        ping =  event.getMember().getAsMention();
                    }
                    message = ping + " du suckst!";
                    break;

                case "!quote":
                    List<TextChannel> channels = guild.getTextChannelsByName("quotes", true);
                    for (TextChannel channel : channels) {
                        MessageHistory history = new MessageHistory(channel);

                        List<Message> messages = history.retrievePast(100).complete();
                        Random ran = new Random();
                        int x = ran.nextInt(messages.size()-1);
                        message = messages.get(x).getContentRaw();
                        break;
                    }
            }
            event.getChannel().sendMessage(message).queue();
        }

    }
}
