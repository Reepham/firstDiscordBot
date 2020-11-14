import events.JoinEvent;
import events.MaddinEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Bot {

    public static void main(String[] args) throws LoginException {

        JDA jda =  JDABuilder
                .createDefault("Nzc2ODQ2MjUwNTU3NDQwMDAy.X660cw.eAmOBG4BO_35FbjPGANy60IC-gY")
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();
        jda.addEventListener(new MaddinEvent());
        jda.addEventListener(new JoinEvent());

    }
}
