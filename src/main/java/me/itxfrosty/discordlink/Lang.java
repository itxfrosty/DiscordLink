package me.itxfrosty.discordlink;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Lang {

    public static final String LINE = ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "                                                   ";

    public static void PLAYER_DETAILS(Player player) {
        player.sendMessage(Lang.LINE);
        player.sendMessage(ChatColor.AQUA + ChatColor.BOLD.toString() + "Linking Details");
        player.sendMessage(Lang.LINE);
        player.sendMessage(ChatColor.AQUA + "Account Type:" +ChatColor.BOLD+ " Minecraft");
        player.sendMessage(ChatColor.AQUA + player.getDisplayName());
        player.sendMessage(ChatColor.AQUA + "UUID: " + player.getUniqueId().toString());
        player.sendMessage(" ");
        player.sendMessage(ChatColor.AQUA + "Account Type:" +ChatColor.BOLD+" Discord");
        player.sendMessage(ChatColor.AQUA + "Username: " + DiscordLink.playerData.get(player.getUniqueId().toString()));
        player.sendMessage(ChatColor.AQUA + "UUID: ");
        player.sendMessage(Lang.LINE);
    }

}
