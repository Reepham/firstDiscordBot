import events.JoinEvent;
import events.MaddinEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import sqlite.SQLManager;
import sqlite.SQLite;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Bot {

    public static void main(String[] args) throws LoginException, IOException, SQLException {
        String token = Files.readString(Paths.get("B:\\Desktop\\Java Projekte\\DiscordBotLogin.txt"), StandardCharsets.US_ASCII);
        SQLite.connect();
        SQLManager.onCreate();

        JDA jda =  JDABuilder
                .createDefault(token)
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();
        jda.addEventListener(new MaddinEvent());
        jda.addEventListener(new JoinEvent());

    }
}
