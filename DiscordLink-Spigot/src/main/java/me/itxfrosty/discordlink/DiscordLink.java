package me.itxfrosty.discordlink;

import lombok.Getter;
import me.itxfrosty.discordlink.commands.discord.Command;
import me.itxfrosty.discordlink.managers.BotManager;
import me.itxfrosty.discordlink.managers.LinkManager;
import me.itxfrosty.discordlink.managers.MessageManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class DiscordLink extends JavaPlugin {

	@Getter private static DiscordLink instance;

	@Getter private static BotManager botManager;
	@Getter private static LinkManager linkManager;
	@Getter private static MessageManager messageManager;

	@Getter private List<Command> commands;

	@Override
	public void onEnable() {
		instance = this;

		commands = new ArrayList<>();
		messageManager = new MessageManager();
		linkManager = new LinkManager();
		botManager = new BotManager(
				MessageManager.TOKEN,
				OnlineStatus.ONLINE,
				Activity.watching("Test"));
		onStart();
	}

	@Override
	public void onDisable() {
		onStop();
	}

	/**
	 * Start Method.
	 */
	public void onStart() {
		botManager.connect();
	}

	/**
	 * Stop Method.
	 */
	public void onStop() {
		if (MessageManager.TOKEN != null) {
			try {
				botManager.disconnectBot();
			} catch (Exception e) {
				MessageManager.log("Could not log out.");
			}
		}
	}
}