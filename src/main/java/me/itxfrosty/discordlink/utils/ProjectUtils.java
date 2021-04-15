package me.itxfrosty.discordlink.utils;

import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.commands.minecraft.CommandBase;
import me.itxfrosty.discordlink.commands.minecraft.CommandModule;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ProjectUtils {

	/**
	 * Register's Commands.
	 *
	 * @param command Command.
	 */
	public static void registerCommands(CommandBase... command) {
		for (CommandBase commandBase : command) {
			CommandModule.registerCommand(commandBase);
		}
	}

	/**
	 * Register's Listeners.
	 *
	 * @param plugin Plugin.
	 * @param listeners Listeners.
	 */
	public static void registerListeners(Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			DiscordLink.getInstance().getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
}
