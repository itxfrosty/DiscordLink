package me.itxfrosty.discordlink.utils;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessage;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import me.itxfrosty.discordlink.managers.BotManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WebhookUtil {

    /**
     * Creation Date: 2/20/2021
     * @author itxfrosty
     */

    private final BotManager botManager = new BotManager();
    private final WebhookClient client = WebhookClient.withId(botManager.getBotID(), botManager.getToken());

    /**
     * Sends message as a Webhook.
     *
     * @param player Player that sent the message.
     * @param message Message the player sent.
     */
    public void sendMessage(Player player, String message) {
        try {
            String avatar = String.format("https://crafatar.com/avatars/%s.png?size=120&overlay", player.getUniqueId().toString());
            executeWebhook(player.getDisplayName(), avatar, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes Webhook.
     *
     * @param name Name for Webhook.
     * @param avatar Avatar for the Webhook.
     * @param message Message sent in Webhook.
     */
    private void executeWebhook(@NotNull String name, @NotNull String avatar, @NotNull String message) {
        WebhookMessageBuilder builder = new WebhookMessageBuilder();
        builder.setAvatarUrl(avatar);
        builder.setContent(message);
        builder.setUsername(name);
        client.send(builder.build());
    }
}
