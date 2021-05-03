package me.itxfrosty.discordlink.commands.discord.cmd;

import me.itxfrosty.database.QueryHandler;
import me.itxfrosty.database.managers.LinkDBManager;
import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.commands.discord.Command;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CommandLink extends Command {
	public CommandLink() {
		super("link","Link Command","!link", Category.UTILITY);
	}

	@Override
	public boolean execute(MessageReceivedEvent event, String[] args) {
		if (event.getAuthor().isBot()) return false;

		String discord_id = Objects.requireNonNull(event.getMember()).getId();

		LinkDBManager.containsLinkID(discord_id).onComplete(p -> {
			if (p == null) {
				Message message = event.getChannel().sendMessage("You are already Linked.").complete();
				message.delete().queueAfter(5, TimeUnit.SECONDS);
			} else {
				String code = args[0];
				UUID playerUUID = DiscordLink.getLinkManager().confirmLink(code);
				String player_uuid = playerUUID.toString();

				QueryHandler<Boolean> link = LinkDBManager.insertLink(player_uuid, discord_id);
				link.execute();
			}
		});
		return true;
	}
}
