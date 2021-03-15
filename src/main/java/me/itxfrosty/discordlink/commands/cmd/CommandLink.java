package me.itxfrosty.discordlink.commands.cmd;

import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.Lang;
import me.itxfrosty.discordlink.commands.CommandBase;
import me.itxfrosty.discordlink.managers.DatabaseManager;
import me.itxfrosty.discordlink.managers.LinkManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLink extends CommandBase {
    public CommandLink() {
        super("link",null);
    }

    private final DatabaseManager database = DiscordLink.getDBManager();
    private final LinkManager linkManager = DiscordLink.getLinkManager();

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (database.contains(player.getUniqueId())) {
                player.sendMessage(Lang.PLAYER_IN_DB);
                return;
            }

            if (linkManager.getHasCode().containsKey(player)) {
                player.sendMessage(Lang.HAS_CODE(linkManager.getHasCode().get(player)));
                return;
            }

            linkManager.link(player);
            int code = linkManager.link(player);
            player.sendMessage(Lang.LINK_MINECRAFT(code));
        }
    }
}
