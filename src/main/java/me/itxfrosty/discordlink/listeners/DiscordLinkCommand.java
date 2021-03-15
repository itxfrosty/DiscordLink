package me.itxfrosty.discordlink.listeners;

import lombok.SneakyThrows;
import me.itxfrosty.discordlink.DiscordLink;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscordLinkCommand extends ListenerAdapter {

    @SneakyThrows
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot() || e.isWebhookMessage()) return;
        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase("!link")) {
            int code = Integer.parseInt(args[1]);

            Player player = DiscordLink.getLinkManager().confirmLink(code);
            Member member = e.getMember();

            DiscordLink.getDBManager().insert(player.getUniqueId(), member.getId(), player.getDisplayName());

            player.sendMessage("Linked!");


        }
    }
}
