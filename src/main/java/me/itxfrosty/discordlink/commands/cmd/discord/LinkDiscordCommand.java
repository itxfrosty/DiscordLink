package me.itxfrosty.discordlink.commands.cmd.discord;

import lombok.SneakyThrows;
import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.managers.LinkManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LinkDiscordCommand extends ListenerAdapter {

    private DiscordLink discordLink;

    @SneakyThrows
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot() || e.isWebhookMessage()) return;
        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase("!link")) {
            int code = Integer.parseInt(args[1]);

            Player player = LinkManager.completeLink(code);
            Member member = e.getMember();


            DiscordLink.playerData.set("Data."+ player.getUniqueId().toString(),member.getId());
            DiscordLink.playerData.save(DiscordLink.data);

            player.sendMessage("Linked!");














        }



    }
}
