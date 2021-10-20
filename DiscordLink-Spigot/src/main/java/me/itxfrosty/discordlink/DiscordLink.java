package me.itxfrosty.discordlink;

import lombok.Getter;
import me.itxfrosty.database.DiscordDatabase;
import me.itxfrosty.discordlink.commands.CommandModule;
import me.itxfrosty.discordlink.commands.cmd.LinkCommand;
import me.itxfrosty.discordlink.factories.BotFactory;
import me.itxfrosty.discordlink.profiles.ProfileManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class DiscordLink extends JavaPlugin {

	@Getter private DiscordLink instance;

	@Getter private BotFactory botFactory;

	@Override
	public void onLoad() {

		if(!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}

		this.saveDefaultConfig();

		File sqlFile = new File(getDataFolder() + "/sql.yml");
		FileConfiguration sqlConfig = YamlConfiguration.loadConfiguration(sqlFile);

		if(!sqlFile.exists()) {
			try {
				sqlFile.createNewFile();

				sqlConfig.set("host", "localhost");
				sqlConfig.set("port", 3306);
				sqlConfig.set("database", "linked_users");
				sqlConfig.set("user", "root");
				sqlConfig.set("password", "");

				sqlConfig.save(sqlFile);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		DiscordDatabase.setHost(sqlConfig.getString("host"));
		DiscordDatabase.setPort(sqlConfig.getInt("port"));
		DiscordDatabase.setDatabase(sqlConfig.getString("database"));
		DiscordDatabase.setUser(sqlConfig.getString("user"));
		DiscordDatabase.setPassword(sqlConfig.getString("password"));
	}

	@Override
	public void onEnable() {
		instance = this;

		this.botFactory = new BotFactory()
				.setToken(getConfig().getString("bot.token"))
				.setMemberCachePolicy(MemberCachePolicy.ALL)
				.registerIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_PRESENCES)
				.disableCaches(CacheFlag.EMOTE, CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS)
				.setActivity(Activity.playing(getConfig().getString("bot.status")))
				.setOnlineStatus(OnlineStatus.fromKey(getConfig().getString("bot.online_status").toLowerCase()))
				.registerListeners();
		this.botFactory.build();

		CommandModule.registerCommand(new LinkCommand());

		this.getServer().getPluginManager().registerEvents(ProfileManager.getInstance(), this);

		for (Player player : Bukkit.getOnlinePlayers()) {
			ProfileManager.getInstance().createProfile(player);
		}
	}

	@Override
	public void onDisable() {
		this.botFactory.disconnectBot();
	}
}
