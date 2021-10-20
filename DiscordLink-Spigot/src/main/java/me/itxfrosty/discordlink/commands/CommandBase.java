package me.itxfrosty.discordlink.commands;


import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public abstract class CommandBase extends Command {

	@Getter private String commandLabel;

	public CommandBase(String cmd, String permission, String... aliases) {
		super(cmd);
		this.setPermission(permission);
		this.setPermissionMessage(ChatColor.RED + "You do not have permission to do that!");
		this.setAliases(Arrays.asList(aliases));
	}

	@Override
	public final boolean execute(CommandSender sender, String commandLabel, String[] args) {
		this.commandLabel = commandLabel;

		if (!testPermission(sender)) {
			return false;
		}

		execute(sender, args);
		return true;
	}

	public abstract void execute(CommandSender sender, String[] args);

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		return super.tabComplete(sender, alias, args);
	}

}