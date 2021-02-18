package me.itxfrosty.discordlink;

import lombok.Getter;
import me.itxfrosty.discordlink.commands.CommandModule;
import me.itxfrosty.discordlink.managers.BotManager;
import me.itxfrosty.discordlink.managers.LinkManager;
import me.itxfrosty.discordlink.utils.ConsoleMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordLink extends JavaPlugin {

    @Getter private static DiscordLink instance;

    private final BotManager bot = new BotManager();
    private final LinkManager link = new LinkManager();


    @Override
    public void onEnable() {
        instance = this;

        startBot();

        CommandModule.registerCommands();
    }

    @Override
    public void onDisable() {
        bot.disconnectBot();
    }


    public void startBot() {
        if (!getConfig().getBoolean("configured")) {
            getLogger().warning("Discord Link has not been configured! Configure the config.yml file and then reload the plugin.");
            Bukkit.getPluginManager().disablePlugin(JavaPlugin.getPlugin(DiscordLink.class));
            return;
        }
        if (!getConfig().getBoolean("bot.enabled")) {
            ConsoleMessage.log("Bot is not enabled in the config.");
            return;
        }
        bot.connectBot(getConfig().getString("bot.token"));
        bot.setActivityStatus(getConfig().getString("bot.activity"), getConfig().getString("bot.status"));
        bot.setOnlineStatus(getConfig().getString("bot.online_status"));
        bot.build();
    }
}
