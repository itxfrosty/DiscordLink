package me.itxfrosty.discordlink.commands.cmd;

import me.itxfrosty.discordlink.Lang;
import me.itxfrosty.discordlink.commands.CommandBase;
import me.itxfrosty.discordlink.managers.LinkManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkCommand extends CommandBase {


    public LinkCommand() {
        super("link",null);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                if (LinkManager.done.containsKey(player)) {
                    sender.sendMessage(ChatColor.AQUA + "You have already have synced your account!");
                    return;
                }

                if (LinkManager.checks.containsKey(player.getUniqueId())) {
                    sender.sendMessage(ChatColor.AQUA + "You already have a code generated. !link " + LinkManager.checks.get(player.getUniqueId()));
                    return;
                }
                LinkManager.link(player);
                int code = LinkManager.link(player);
                player.sendMessage(ChatColor.AQUA + "Go to discord and run !link " + code);
                return;
            }

            if (args[0].equalsIgnoreCase("help")) {
                player.sendMessage("help");
                return;
            }
            if (args[0].equalsIgnoreCase("details")) {
                Lang.PLAYER_DETAILS(player);
            }
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {

        if(args.length == 1) {
            return new ArrayList<>(Arrays.asList("help", "details"));
        }

        return super.tabComplete(sender, alias, args);
    }

}
