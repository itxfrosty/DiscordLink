package me.itxfrosty.discordlink.commands.discord;

import me.itxfrosty.discordlink.commands.discord.cmd.CommandDLink;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Pattern;

public class CommandManager extends ListenerAdapter {

	private final Map<String, CommandContext> commands = new HashMap<>();

	public CommandManager() {
			addCommand(new CommandDLink());
	}

	private void addCommand(CommandContext c) {
		if(!commands.containsKey(c.getCommand())) {
			commands.put(c.getCommand(), c);
		}
	}

	public Collection<CommandContext> getCommands() {
		return commands.values();
	}

	public CommandContext getCommand(String commandName) {
		if(commandName == null) {
			return null;
		}
		return commands.get(commandName);
	}

	@Override
	public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
		final String msg = event.getMessage().getContentRaw();
		if(!msg.startsWith("!")) {
			return;
		}
		final String[] split = msg.replaceFirst("(?i)" + Pattern.quote("!"), "").split("\\s+");
		final String command = split[0].toLowerCase();
		if(commands.containsKey(command)) {
			String[] args = Arrays.copyOfRange(split, 1, split.length);
			commands.get(command).run(event, args);
		}
	}
}