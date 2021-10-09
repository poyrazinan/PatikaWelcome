package main.java.patika.dev.discord.welcome;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.awt.*;

public class Main extends ListenerAdapter {

    public static final String BOT_TOKEN = "BOT TOKEN HERE";
    public static final String BOT_STATUS = "CUSTOM STATUS TEXT HERE";
    public static final long WELCOME_CHANNEL_ID = 852923003545321473L;

    public static JDA jda = null;

    /**
     * main
     *
     * @param args
     * @throws LoginException
     * @throws InterruptedException
     */
    public static void main(String... args) throws LoginException, InterruptedException {
        JDABuilder builder = JDABuilder.create(BOT_TOKEN, GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_PRESENCES);

        builder.setActivity(Activity.playing(BOT_STATUS))
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .setBulkDeleteSplittingEnabled(false)
                .addEventListeners(new Main())
                .setAutoReconnect(true);

        jda = builder.build();
        jda.awaitReady();

        System.out.println("JDA Bağlantısı başarıyla kuruldu!");
    }

    /**
     * Message listening on "tanışma" channel
     *
     * @param event
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();

        if (channel.getIdLong() == WELCOME_CHANNEL_ID)
            msg.addReaction("\uD83D\uDC4B").queue();
    }

    /**
     * Use it if you want to send message to person on #onMessageReceived
     *
     * @return
     */
    private @NotNull EmbedBuilder welcomeDMEmbed() {
        final String PHOTO_LINK = "";

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Merhaba!")
            .setColor(Color.red)
            .setDescription("")
            .addField("Aramıza Hoşgeldin", "Burada aradığını bulabileceğini umuyorum :)", false)
        //  .addField("2. Satır", "2. Satır mesajı. ", false)
            .setFooter("Patika", PHOTO_LINK)
            .setThumbnail(PHOTO_LINK);

        return embed;
    }


}
