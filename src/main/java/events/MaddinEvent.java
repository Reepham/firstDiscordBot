package events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MaddinEvent extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String ping;
        String[] receivedMessage = event.getMessage().getContentRaw().split(" ");
        JDA client = event.getJDA();

        if (receivedMessage[0].equalsIgnoreCase("!daily") && !event.getMember().getUser().isBot()) {
            try {
                ping = client.getUserByTag("Maddin#7057").getAsMention();
            }catch (NullPointerException e){
                ping =  event.getMember().getAsMention();
            }
                event.getChannel().sendMessage(ping +" du suckst!").queue();
        }
    }

}
