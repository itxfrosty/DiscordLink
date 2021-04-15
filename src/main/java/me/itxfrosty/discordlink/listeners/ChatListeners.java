package me.itxfrosty.discordlink.listeners;

import me.itxfrosty.discordlink.DiscordLink;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListeners extends ListenerAdapter implements Listener {

	/**
	 * Minecraft Chat Event.
	 */
	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent e){
		String message = e.getMessage();
		TextChannel textChannel = DiscordLink.getBotManager().getJDA().getTextChannelsByName("game",true).get(0);
		textChannel.sendMessage("**"+e.getPlayer().getName()+":** "+message).queue();
	}

	/**
	 * Discord Chat Event.
	 */
	public void chatEvent(GuildMessageReceivedEvent event) {
		if (event.getAuthor().isBot() || event.isWebhookMessage()) return;
		String message = event.getMessage().getContentRaw();
		User user = event.getAuthor();
		Bukkit.broadcastMessage("§9§l" + user.getName() + "#" + user.getDiscriminator() + ": §e" + message);
	}
}