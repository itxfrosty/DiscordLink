package me.itxfrosty.discordlink.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.ChatMessageType;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JsonMessageUtil {

	public static void sendClickableHoverableMessageCommand(Player player, String text, String clickableText, String hoverText, String command) {
		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\",\"extra\":" + "[{\"text\":\"" + clickableText + "\"," +
				"\"hoverEvent\":{\"action\":\"show_text\", " + "\"value\":\"" + hoverText + "\"}," +
				"\"clickEvent\":{\"action\":\"suggest_command\",\"value\":" + "\"/" + command + "\"}}]}");

		PacketPlayOutChat packet = new PacketPlayOutChat(chat, ChatMessageType.CHAT, player.getUniqueId());
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}

	/**
	 *
	 * @param player the target player
	 * @param text message to be sent
	 * @param clickableText text that you cna click on
	 * @param hoverText  text that will show
	 * @param runCommand command that will be run on click
	 */
	public static void sendClickableHoverableMessage(Player player, String text, String clickableText, String hoverText, String runCommand) {
		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\",\"extra\":" + "[{\"text\":\"" + clickableText + "\",\"hoverEvent\":{\"action\":\"show_text\", " + "\"value\":\"" + hoverText + "\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":" + "\"/" + runCommand + "\"}}]}");
		PacketPlayOutChat packet = new PacketPlayOutChat(chat, ChatMessageType.CHAT, player.getUniqueId());
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}

	/**
	 *
	 * @param player the target player
	 * @param text message to be sent
	 * @param hoverableText text that will show the value
	 * @param hoverText text that will show
	 */
	public static void sendHoverableMessage(Player player, String text, String hoverableText, String hoverText) {
		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\",\"extra\":" + "[{\"text\":\"" + hoverableText + "\",\"hoverEvent\":{\"action\":\"show_text\", " + "\"value\":\"" + hoverText + "\"}}]}");
		PacketPlayOutChat packet = new PacketPlayOutChat(chat, ChatMessageType.CHAT, player.getUniqueId());
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}

	/**
	 *
	 * @param player the target player
	 * @param text message to be sent
	 * @param clickableText text that you cna click on
	 * @param runCommand command that will be run on click
	 */
	public static void sendClickableMessage(Player player, String text, String clickableText, String runCommand) {
		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\",\"extra\":" + "[{\"text\":\"" + clickableText + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":" + "\"/" + runCommand + "\"}}]}");
		PacketPlayOutChat packet = new PacketPlayOutChat(chat, ChatMessageType.CHAT, player.getUniqueId());
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}

	/**
	 *
	 * @param player the target player
	 * @param text message sent to player before click text
	 * @param clickableText click text sent after text
	 * @param link clickable link
	 */
	public static void sendClickableMessageLink(Player player, String text, String clickableText, String link, ChatColor chatColor) {
		TextComponent message = new TextComponent( text );
		TextComponent clickText = new TextComponent( clickableText );
		clickText.setColor(chatColor);
		clickText.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link));
		message.addExtra(clickText);
		player.spigot().sendMessage(message);
	}
}