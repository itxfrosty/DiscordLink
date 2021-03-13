package me.itxfrosty.discordlink.commands.minecraft;

import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.commands.minecraft.cmd.LinkCommand;
import me.itxfrosty.discordlink.commands.minecraft.cmd.UnLinkCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

public class CommandModule {

	
	private static CommandMap commandMap;

	public static void registerCommands() {
		CommandModule.registerCommand(new LinkCommand());
		CommandModule.registerCommand(new UnLinkCommand());
	}

	public static void registerCommand(CommandBase command) {
		getCommandMap().register(DiscordLink.getInstance().getName(), command);
	}
	
	private static CommandMap getCommandMap() {
		if(commandMap == null) {
		 
			try {
				if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
					Field f = SimplePluginManager.class.getDeclaredField("commandMap");
					f.setAccessible(true);
		 
					commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
				}
			} catch (Exception e) {
				e.printStackTrace();
				commandMap = null;
			}
		}
		return commandMap;
	}
}
