package events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class MaddinEvent extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String ping;
        String[] receivedMessage = event.getMessage().getContentRaw().split(" ");
        JDA client = event.getJDA();

        try {
            ping = client.getUserByTag("Maddin#7057").getAsMention();
        }catch (NullPointerException e){
            ping =  event.getMember().getAsMention();
        }
        if (receivedMessage[0].equalsIgnoreCase("!daily")) {

            if (!event.getMember().getUser().isBot()) {
                event.getChannel().sendMessage(ping +" du suckst!").queue();
            }
        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Guild guild = event.getGuild(); // Get the guild that the user joined.
        User user = event.getUser();    // Get the user that joined.
        JDA client = event.getJDA();    // Get the already existing JDA instance.

        List<TextChannel> channels = guild.getTextChannelsByName("bottestserver", true); // Get the list of channels in the guild that matches that name.

        for (TextChannel channel : channels) { // Loops through the channels and sends a message to each one.
            if (channel.getName().equals("Allgemein")){
                System.out.println("Es geht!");
                channel.sendMessage("New member joined: " + user).queue();
            }
        }
    }
}
