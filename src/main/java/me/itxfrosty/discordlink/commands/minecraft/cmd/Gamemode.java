package me.itxfrosty.discordlink.commands.minecraft.cmd;

import me.itxfrosty.discordlink.commands.minecraft.CommandBase;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode extends CommandBase {


    public Gamemode(String cmd, String permission, String... aliases) {
        super(cmd, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String gm = args[1].toLowerCase();

            switch (gm) {
                case "survival":
                    player.setGameMode(GameMode.SURVIVAL);
                    break;
                case "creative":
                    player.setGameMode(GameMode.CREATIVE);
                    break;
                case "spectator":
                    player.setGameMode(GameMode.SPECTATOR);
                    break;
                case "adventure":
                    player.setGameMode(GameMode.ADVENTURE);
                    break;
                default:
                    player.sendMessage("Gamemode");
                    break;
            }

        }

    }
}
