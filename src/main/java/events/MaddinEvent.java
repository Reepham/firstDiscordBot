package events;

import events.commands.Commands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MaddinEvent extends ListenerAdapter {


    public void onGuildMessageReceived(GuildMessageReceivedEvent event){

        String[] receivedMessage = event.getMessage().getContentRaw().split(" ");
        JDA client = event.getJDA();
        String memberid = event.getAuthor().getId();
        Member member = event.getMember();
        Guild guild = event.getGuild();
        TextChannel channel = event.getChannel();
        Commands command = new Commands();

        if (!event.getMember().getUser().isBot()){
            switch (receivedMessage[0].toLowerCase()){
                case "!maddin":
                    command.maddin(client,member,channel);
                    break;

                case "!joke":
                    command.joke(channel);
                    break;

                case "!quote": case "!quotes":
                    command.quotes(guild,channel,receivedMessage);
                    break;

                case "!obichdummbin":
                    command.dummheit(channel,memberid);
                    break;
            }
        }

    }

}

