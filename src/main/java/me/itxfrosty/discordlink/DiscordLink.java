package me.itxfrosty.discordlink;

import lombok.Getter;
import me.itxfrosty.discordlink.commands.discord.CommandManager;
import me.itxfrosty.discordlink.commands.minecraft.cmd.CommandLink;
import me.itxfrosty.discordlink.listeners.ChatListeners;
import me.itxfrosty.discordlink.managers.BotManager;
import me.itxfrosty.discordlink.managers.DatabaseManager;
import me.itxfrosty.discordlink.managers.LinkManager;
import me.itxfrosty.discordlink.managers.MessageManager;
import me.itxfrosty.discordlink.user.UserModule;
import me.itxfrosty.discordlink.utils.ProjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordLink extends JavaPlugin {

	@Getter private static DiscordLink instance;

	@Getter private static BotManager botManager;
	@Getter private static DatabaseManager databaseManager;
	@Getter private static LinkManager linkManager;
	@Getter private static MessageManager messageManager;

	@Override
	public void onEnable() {
		instance = this;

		messageManager = new MessageManager();
		botManager = new BotManager();
		linkManager = new LinkManager();
		databaseManager = new DatabaseManager(
				MessageManager.HOST,
				MessageManager.PORT,
				MessageManager.DATABASE,
				MessageManager.USERNAME,
				MessageManager.PASSWORD);
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
		if (!MessageManager.CONFIGURED) {
			System.err.println("Bot is not configured. Please Configure the bot before starting.");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}

		databaseManager.connect();

		assert MessageManager.ONLINE_STATUS != null;
		assert MessageManager.ACTIVITY != null;

		botManager.connectBot(MessageManager.TOKEN);
		botManager.setOnlineStatus(MessageManager.ONLINE_STATUS);
		botManager.setActivityStatus(MessageManager.ACTIVITY, MessageManager.STATUS);

		botManager.registerEventListener(new CommandManager());

		if (MessageManager.CHAT_BOOLEAN) {
			ProjectUtils.registerListeners(this, new ChatListeners());
			botManager.registerEventListener(new ChatListeners());
		}

		botManager.build();

		ProjectUtils.registerCommands(new CommandLink());
		ProjectUtils.registerListeners(this, UserModule.getInstance());
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
		databaseManager.closeConnection();
	}
}