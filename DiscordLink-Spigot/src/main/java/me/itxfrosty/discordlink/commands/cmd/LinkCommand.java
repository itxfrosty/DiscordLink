package me.itxfrosty.discordlink.commands.cmd;

import me.itxfrosty.discordlink.commands.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkCommand extends CommandBase {
	public LinkCommand() {
		super("link", "dl.link");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;

		}
	}
}
