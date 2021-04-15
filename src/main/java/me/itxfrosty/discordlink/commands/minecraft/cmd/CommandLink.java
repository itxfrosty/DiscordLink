package me.itxfrosty.discordlink.commands.minecraft.cmd;

import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.Lang;
import me.itxfrosty.discordlink.commands.minecraft.CommandBase;
import me.itxfrosty.discordlink.managers.DatabaseManager;
import me.itxfrosty.discordlink.managers.LinkManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandLink extends CommandBase {
	public CommandLink() {
		super("link", null);
	}

	private final DatabaseManager database = DiscordLink.getDatabaseManager();
	private final LinkManager linkManager = DiscordLink.getLinkManager();

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			final UUID playerUUID = player.getUniqueId();

			if (database.contains(player.getUniqueId())) {
				player.sendMessage(Lang.PLAYER_IN_DB);
				return;
			}

			if (linkManager.getCodeCache().asMap().containsKey(playerUUID)) {
				String code = linkManager.getCodeCache().asMap().get(playerUUID);
				player.sendMessage(code);
				return;
			}

			String code = linkManager.link(player);
			player.sendMessage(Lang.LINK_MINECRAFT(code));
		}
	}
}
