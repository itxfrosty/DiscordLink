package me.itxfrosty.discordlink.utils;

import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.managers.BotManager;
import me.itxfrosty.discordlink.managers.LinkManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Methods {

    public static void startBot() {
        final BotManager bot = new BotManager();
        if (!DiscordLink.getInstance().getConfig().getBoolean("configured")) {
            DiscordLink.getInstance().getLogger().warning("Discord Link has not been configured! Configure the config.yml file and then reload the plugin.");
            Bukkit.getPluginManager().disablePlugin(JavaPlugin.getPlugin(DiscordLink.class));
            return;
        }
        if (!DiscordLink.getInstance().getConfig().getBoolean("bot.enabled")) {
            ConsoleMessage.log("Bot is not enabled in the config.");
            return;
        }
        bot.connectBot(DiscordLink.getInstance().getConfig().getString("bot.token"));
        bot.setActivityStatus(DiscordLink.getInstance().getConfig().getString("bot.activity"), DiscordLink.getInstance().getConfig().getString("bot.status"));
        bot.setOnlineStatus(DiscordLink.getInstance().getConfig().getString("bot.online_status"));
        bot.build();
    }


}
