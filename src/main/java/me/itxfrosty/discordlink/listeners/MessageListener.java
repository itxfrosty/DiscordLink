package me.itxfrosty.discordlink.listeners;

import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.utils.MessageUtils;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class MessageListener implements Listener {

    TextChannel textChannel = DiscordLink.getBotManager().getJda().getTextChannelById(MessageUtils.CHAT_CHANNEL);
    FileConfiguration config = DiscordLink.getInstance().getConfig();

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.contains("@everyone") || message.contains("@here")) {
            return;
        }

        textChannel.sendMessage(Objects.requireNonNull(config.getString("minecraftToDiscord")).replace("%player%",
                event.getPlayer().getName()).replace("%message%",
                event.getMessage().replace("%prefix%",
                Objects.requireNonNull(config.getString("prefix"))))).queue();
    }



}
