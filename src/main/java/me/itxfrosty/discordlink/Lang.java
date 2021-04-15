package me.itxfrosty.discordlink;

import me.itxfrosty.discordlink.utils.HexColor;
import org.bukkit.ChatColor;

public class Lang {

	/**
	 * Message Utils.
	 */

	public static final String LINE = ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "                                                   ";

	public static final String MESSAGE_START = HexColor.DARK_GREEN + ChatColor.BOLD.toString() + "✦ " + ChatColor.RESET + HexColor.valueOf("#2BFF39");
	public static final String MESSAGE_START_RED = HexColor.DARK_RED + ChatColor.BOLD.toString() + "✦ " + ChatColor.RESET + HexColor.valueOf("#FF2B2B");
	public static final String MESSAGE_START_BLUE = HexColor.CORN_FLOWER_BLUE + ChatColor.BOLD.toString() + "✦ " + ChatColor.RESET;

	public static final String PLAYERS_ONLY = MESSAGE_START_RED + "Only players can do this command!";
	public static final String PLAYER_NOT_FOUND = MESSAGE_START_RED + "Player not found!";
	public static final String NO_PERMISSION = MESSAGE_START_RED + "You do not have access to that command.";

	/**
	 * Link Command.
	 */

	public static final String PLAYER_IN_DB = "";

	public static String LINK_MINECRAFT(String code) {
		return MESSAGE_START_BLUE + HexColor.CORN_FLOWER_BLUE + "Linked Started! Link you're account by typing !link " + code + ".";
	}

	public static String HAS_CODE(String code) {
		return MESSAGE_START_BLUE + HexColor.CORN_FLOWER_BLUE + "You already have a code generated! Link you're account by typing !link " + code + ".";
	}
}