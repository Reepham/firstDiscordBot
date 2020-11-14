package events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class JoinEvent extends ListenerAdapter {


    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Guild guild = event.getGuild(); // Get the guild that the user joined.
        Member member = event.getMember();    // Get the user that joined.
        JDA client = event.getJDA();    // Get the already existing JDA instance.

        List<TextChannel> channels = guild.getTextChannelsByName("general", true); // Get the list of channels in the guild that matches that name.

        for (TextChannel channel : channels) { // Loops through the channels and sends a message to each one.
            channel.sendMessage("Herzlich Willkommen " + event.getMember().getAsMention() + "!\nStell dich doch mal vor ^^").queue();
        }
    }
}
