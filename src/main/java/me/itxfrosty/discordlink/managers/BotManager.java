package me.itxfrosty.discordlink.managers;

import lombok.SneakyThrows;
import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.commands.minecraft.cmd.DiscordLinkCommand;
import me.itxfrosty.discordlink.utils.ConsoleMessage;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class BotManager {

    /**
     * Creation date: 2/13/2021
     * @author itxfrosty
     */

    private JDA jda;
    private JDABuilder jdaBuilder;

    private DiscordLink bot;

    /**
     * Starts the DiscordBot.
     *
     * @param token Token for DiscordAPI.
     */
    @SneakyThrows
    public void connectBot(String token) {
        jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.addEventListeners(new DiscordLinkCommand());
    }

    /**
     * Disconnects the bot if online.
     */
    public void disconnectBot() {
        if (jda != null) {
            jda.shutdownNow();
        }
    }

    /**
     * Build's the bot.
     */
    public void build() {
        try {
            jda = jdaBuilder.build().awaitReady();
        } catch (LoginException | InterruptedException e) {
            ConsoleMessage.log("Failed to log in to Discord: " + e.getMessage());
        }
    }

    /**
     * Set's Online Status of bot.
     *
     * @param onlineStatus Status of Bot.
     */
    public void setOnlineStatus(String onlineStatus) {
        if (onlineStatus.equalsIgnoreCase("online")) {
            jdaBuilder.setStatus(OnlineStatus.ONLINE);
            return;
        }
        if (onlineStatus.equalsIgnoreCase("idle")) {
            jdaBuilder.setStatus(OnlineStatus.IDLE);
            return;
        }
        if (onlineStatus.equalsIgnoreCase("do_not_disturb")) {
            jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
            return;
        }
        if (onlineStatus.equalsIgnoreCase("offline")) {
            jdaBuilder.setStatus(OnlineStatus.OFFLINE);
            return;
        }
        jdaBuilder.setStatus(OnlineStatus.ONLINE);
    }

    /**
     * Set's the bots status and activity.
     *
     * @param activity Set's Activity of the bot.
     * @param status   Set's the Status of the bot.
     */
    public void setActivityStatus(String activity, String status) {
        if (activity.equalsIgnoreCase("watching")) {
            jdaBuilder.setActivity(Activity.watching(status));
            return;
        }
        if (activity.equalsIgnoreCase("listening")) {
            jdaBuilder.setActivity(Activity.listening(status));
            return;
        }
        if (activity.equalsIgnoreCase("playing")) {
            jdaBuilder.setActivity(Activity.playing(status));
            return;
        }
        jdaBuilder.setActivity(Activity.playing(status));
    }

    /**
     * Get's the status of the Discord Bot!
     */
    public void getBotStatus() {
        jda.getStatus();
    }

    /**
     * Set's the token for the bot.
     *
     * @param token Discord Bot Token.
     */
    public void setToken(String token) {
        jdaBuilder.setToken(token);
    }

    /**
     * Get's the token for the bot.
     *
     * @return Discord Bot Token.
     */
    public String getToken() {
        return jda.getToken();
    }

    /**
     * Get's the bot ID.
     *
     * @return The Discord bot ID loooooooooong.
     */
    public long getBotID() { return jda.getSelfUser().getIdLong(); }

    /**
     * Get's a Invite for the bot to a Discord Server.
     *
     * @return Link for inviting bot to Discord.
     */
    public String getBotInvite() {
        return jda.getInviteUrl(Permission.ADMINISTRATOR);
    }

    /**
     * Get's JDA.
     *
     * @return JDA.
     */
    public JDA getJda() {
        return jda;
    }

    /**
     * Get's JDA Builder. Need's to be run before BotManager#build();
     *
     * @return JDABuilder.
     */
    public JDABuilder getJdaBuilder() {
        return jdaBuilder;
    }
}