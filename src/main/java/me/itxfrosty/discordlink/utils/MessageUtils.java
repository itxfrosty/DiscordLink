package me.itxfrosty.discordlink.utils;

import me.itxfrosty.discordlink.DiscordLink;

public class MessageUtils {

    /**
     * Config Configured.
     */
    public static boolean CONFIGURED = DiscordLink.getInstance().getConfig().getBoolean("configured");

    /**
     * Discord Info.
     */
    public static final String TOKEN = DiscordLink.getInstance().getConfig().getString("bot.token");
    public static final String ONLINE_STATUS = DiscordLink.getInstance().getConfig().getString("bot.online_status");
    public static final String ACTIVITY = DiscordLink.getInstance().getConfig().getString("bot.activity");
    public static final String STATUS = DiscordLink.getInstance().getConfig().getString("bot.status");

    /**
     * Database Info.
     */
    public static final String HOST = DiscordLink.getInstance().getConfig().getString("database.host");
    public static final String PORT = DiscordLink.getInstance().getConfig().getString("database.port");
    public static final String DATABASE = DiscordLink.getInstance().getConfig().getString("database.database");
    public static final String USERNAME = DiscordLink.getInstance().getConfig().getString("database.username");
    public static final String PASSWORD = DiscordLink.getInstance().getConfig().getString("database.password");

    /**
     * Console Log.
     */
    public static void log(String message) {
        System.out.println(message + "\u001b[0m");
    }

    /**
     * Log Color's.
     */
    public static final String RESET = "\u001b[0m";
    public static final String BLACK = "\u001b[0;30m";
    public static final String RED = "\u001b[0;31m";
    public static final String GREEN = "\u001b[0;32m";
    public static final String YELLOW = "\u001b[0;33m";
    public static final String BLUE = "\u001b[0;34m";
    public static final String PURPLE = "\u001b[0;35m";
    public static final String CYAN = "\u001b[0;36m";
    public static final String WHITE = "\u001b[0;37m";

}
