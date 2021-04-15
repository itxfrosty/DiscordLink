package me.itxfrosty.discordlink.commands.discord.cmd;

import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.commands.discord.CommandContext;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandDLink implements CommandContext {

	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		String code = args[0];

		Player player = Bukkit.getPlayer(DiscordLink.getLinkManager().confirmLink(code));
		UUID playerUUID = DiscordLink.getLinkManager().confirmLink(code);
		Member member = event.getMember();

		if (player == null) {
			event.getChannel().sendMessage("Code is not valid").complete();
			return;
		}

		DiscordLink.getDatabaseManager().insert(playerUUID, member.getIdLong());
		player.sendMessage("Linked!");
	}

	@Override
	public String getCommand() {
		return "link";
	}

	@Override
	public String getHelp() {
		return null;
	}
}