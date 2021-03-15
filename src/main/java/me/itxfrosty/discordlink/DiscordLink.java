package me.itxfrosty.discordlink;

import me.itxfrosty.discordlink.commands.CommandModule;
import me.itxfrosty.discordlink.managers.BotManager;
import me.itxfrosty.discordlink.managers.DatabaseManager;
import me.itxfrosty.discordlink.managers.LinkManager;
import me.itxfrosty.discordlink.utils.MessageUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordLink extends JavaPlugin {

    private static DiscordLink instance;

    private static BotManager botManager;
    private static DatabaseManager databaseManager;
    private static LinkManager linkManager;
    private static MessageUtils messageUtils;

    @Override
    public void onEnable() {
        instance = this;

        messageUtils = new MessageUtils();
        botManager = new BotManager();
        linkManager = new LinkManager();
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
        if (MessageUtils.TOKEN == null) {
            try {
                botManager.disconnectBot();
            } catch (Exception e) {
                MessageUtils.log("Could not log out");
            }
        }
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
    public static BotManager getBotManager() {
        return botManager;
    }

    /**
     * Get's Link Manager.
     * @return LinkManager.
     */
    public static LinkManager getLinkManager() {
        return linkManager;
    }

    /**
     * Get's Message Utils.
     * @return MessageUtils.
     */
    public static MessageUtils getMessageUtils() {
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