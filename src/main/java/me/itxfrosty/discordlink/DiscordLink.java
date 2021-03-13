package me.itxfrosty.discordlink;

import me.itxfrosty.discordlink.commands.minecraft.CommandModule;
import me.itxfrosty.discordlink.managers.BotManager;
import me.itxfrosty.discordlink.managers.DatabaseManager;
import me.itxfrosty.discordlink.utils.MessageUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordLink extends JavaPlugin {

    private static DiscordLink instance;

    private BotManager botManager;
    private static DatabaseManager databaseManager;
    private static MessageUtils messageUtils;

    @Override
    public void onEnable() {
        instance = this;

        botManager = new BotManager();
        messageUtils = new MessageUtils();
        databaseManager = new DatabaseManager(
                MessageUtils.HOST,
                MessageUtils.PORT,
                MessageUtils.DATABASE,
                MessageUtils.USERNAME,
                MessageUtils.PASSWORD);
        onStart();
    }

    @Override
    public void onDisable() {
        onStop();
    }

    /**
     * Start Method.
     */
    public void onStart() {
        databaseManager.connect();

        assert MessageUtils.ONLINE_STATUS != null;
        assert MessageUtils.ACTIVITY != null;

        botManager.connectBot(MessageUtils.TOKEN);
        botManager.setOnlineStatus(MessageUtils.ONLINE_STATUS);
        botManager.setActivityStatus(MessageUtils.ACTIVITY, MessageUtils.STATUS);
        botManager.build();

        CommandModule.registerCommands();
    }

    /**
     * Stop Method.
     */
    public void onStop() {
        botManager.disconnectBot();
        databaseManager.closeConnection();
    }

    /**
     * Get's databaseManager.
     * @return DatabaseManager.
     */
    public static DatabaseManager getDBManager() {
        return databaseManager;
    }

    /**
     * Get's Bot Manager.
     * @return BotManager.
     */
    public BotManager getBotManager() {
        return botManager;
    }

    /**
     * Get's Message Utils.
     * @return MessageUtils.
     */
    public MessageUtils getMessageUtils() {
        return messageUtils;
    }

    /**
     * Get's instance in main class.
     * @return Instance of main class.
     */
    public static DiscordLink getInstance() {
        return instance;
    }

}
/*

private static DiscordLink instance;

    private BotManager bot;
    private static DatabaseManager db;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        new MessageUtils();
        bot = new BotManager();
        db = new DatabaseManager(
                MessageUtils.HOST,
                MessageUtils.PORT,
                MessageUtils.DATABASE,
                MessageUtils.USERNAME,
                MessageUtils.PASSWORD);
        db.connect();

        startBot("NzgxNjI3Mzk2MzYxMDI3NTk1.X8AZPQ.SLgLWlJ5CkUhHlR1Bx4vG5dhMdc");

        CommandModule.registerCommands();
    }

    @Override
    public void onDisable() {
        bot.disconnectBot();
        db.closeConnection();
    }

    public void startBot(String token) {
        bot.connectBot(token);

        bot.setOnlineStatus(OnlineStatus.ONLINE);
        bot.setActivityStatus(Activity.ActivityType.WATCHING,"Hey");

        bot.build();
    }


 */