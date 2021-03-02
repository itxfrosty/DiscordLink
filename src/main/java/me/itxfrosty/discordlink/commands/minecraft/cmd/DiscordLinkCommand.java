package me.itxfrosty.discordlink.commands.minecraft.cmd;

import lombok.SneakyThrows;
import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.managers.DatabaseManager;
import me.itxfrosty.discordlink.managers.LinkManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscordLinkCommand extends ListenerAdapter {

    private DiscordLink discordLink;
    private final DatabaseManager db = new DatabaseManager();

    @SneakyThrows
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot() || e.isWebhookMessage()) return;
        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase("!link")) {
            int code = Integer.parseInt(args[1]);

            Player player = LinkManager.completeLink(code);
            Member member = e.getMember();

            db.connect();
            db.insert(player.getUniqueId(),member.getId(), player.getDisplayName());

            player.sendMessage("Linked!");


        }
    }
}
