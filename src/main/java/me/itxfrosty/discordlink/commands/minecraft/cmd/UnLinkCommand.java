package me.itxfrosty.discordlink.commands.minecraft.cmd;

import me.itxfrosty.discordlink.commands.minecraft.CommandBase;
import me.itxfrosty.discordlink.managers.DatabaseManager;
import me.itxfrosty.discordlink.managers.LinkManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnLinkCommand extends CommandBase {

    private final DatabaseManager db = new DatabaseManager();

    public UnLinkCommand() {
        super("unlink", null);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (db.contains(player.getUniqueId())) {
                LinkManager.unlink(player);
                player.sendMessage("You have been unlinked.");
            }

        }

    }
}
