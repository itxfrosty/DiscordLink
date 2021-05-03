package me.itxfrosty.discordlink.managers;

import me.itxfrosty.discordlink.DiscordLink;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

public class MessageManager {

	private final FileConfiguration config = DiscordLink.getInstance().getConfig();

	/**
	 * Config Booleans.
	 */
	public static boolean CONFIGURED = DiscordLink.getInstance().getConfig().getBoolean("configured");
	public static boolean CHAT_BOOLEAN = DiscordLink.getInstance().getConfig().getBoolean("bot.chat_boolean");

	/**
	 * Discord Info.
	 */
	public static final String TOKEN = DiscordLink.getInstance().getConfig().getString("bot.token");
	public static final String ONLINE_STATUS = DiscordLink.getInstance().getConfig().getString("bot.online_status");
	public static final String ACTIVITY = DiscordLink.getInstance().getConfig().getString("bot.activity");
	public static final String STATUS = DiscordLink.getInstance().getConfig().getString("bot.status");

	public static final Long CHAT_CHANNEL = DiscordLink.getInstance().getConfig().getLong("bot.chat_channel");
	public static final Long LINK_CHANNEL = DiscordLink.getInstance().getConfig().getLong("bot.link_channel");

	/**
	 * Database Info.
	 */
	public static final String HOST = DiscordLink.getInstance().getConfig().getString("database.host");
	public static final Integer PORT = Integer.parseInt(Objects.requireNonNull(DiscordLink.getInstance().getConfig().getString("database.port")));
	public static final String DATABASE = DiscordLink.getInstance().getConfig().getString("database.database");
	public static final String USERNAME = DiscordLink.getInstance().getConfig().getString("database.username");
	public static final String PASSWORD = DiscordLink.getInstance().getConfig().getString("database.password");

	/**
	 * Command.
	 */

	public static final String PREFIX = "!";


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


	public static final String VERIFY_REACTION = "\u2705";
	public static final String VERIFY_REACTION_CODEPOINT = "U+2705";

}