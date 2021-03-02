package me.itxfrosty.discordlink;

import me.itxfrosty.discordlink.commands.minecraft.CommandModule;
import me.itxfrosty.discordlink.managers.BotManager;
import me.itxfrosty.discordlink.managers.DatabaseManager;
import me.itxfrosty.discordlink.utils.Methods;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordLink extends JavaPlugin {

    private static DiscordLink instance;
    private static final DatabaseManager dbm = new DatabaseManager();

    private final BotManager bot = new BotManager();

    @Override
    public void onEnable() {
        instance = this;

        Methods.startBot();
        CommandModule.registerCommands();
        dbm.connect();


        System.out.println(dbm.isConnected() + " end of onEnable");
    }

    @Override
    public void onDisable() {
        bot.disconnectBot();
        dbm.disconnect();
    }

    /**
     * Get's databaseManager.
     * @return DatabaseManager.
     */
    public static DatabaseManager getDatabaseManager() {
        return dbm;
    }

    /**
     * Get's instance in main class.
     * @return Instance of main class.
     */
    public static DiscordLink getInstance() {
        return instance;
    }
}