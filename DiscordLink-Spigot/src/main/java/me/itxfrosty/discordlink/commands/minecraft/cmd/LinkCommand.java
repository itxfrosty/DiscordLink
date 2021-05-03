package me.itxfrosty.discordlink.commands.minecraft.cmd;

import me.itxfrosty.database.QueryHandler;
import me.itxfrosty.database.managers.LinkDBManager;
import me.itxfrosty.database.tables.LinkDB;
import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.commands.minecraft.CommandBase;
import me.itxfrosty.discordlink.managers.LinkManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class LinkCommand extends CommandBase {
	public LinkCommand() {
		super("link",null);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			final UUID playerUUID = player.getUniqueId();

			final LinkManager linkManager = DiscordLink.getLinkManager();

			QueryHandler<List<LinkDB>> linkHandler = LinkDBManager.containsLinkUUID(playerUUID.toString());
			linkHandler.onComplete(link -> {
				String check = link.toString();
				if (check.contains(playerUUID.toString())) {
					player.sendMessage("");
				} else {
					if (linkManager.getCodeCache().asMap().containsKey(playerUUID)) {
						String code = linkManager.getCodeCache().asMap().get(playerUUID);
						player.sendMessage(code);
						return;
					}

					String code = linkManager.startLink(player.getUniqueId());
					player.sendMessage(code);
				}
			});
			linkHandler.execute();




		}
	}
}
