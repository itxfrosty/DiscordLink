package me.itxfrosty.discordlink;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordLink extends JavaPlugin {

	@Getter private DiscordLink instance;

	@Override
	public void onEnable() {
		instance = this;

	}
}
