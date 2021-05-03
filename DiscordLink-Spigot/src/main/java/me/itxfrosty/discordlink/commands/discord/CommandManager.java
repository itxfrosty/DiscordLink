package me.itxfrosty.discordlink.commands.discord;

import me.itxfrosty.discordlink.DiscordLink;
import me.itxfrosty.discordlink.managers.MessageManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CommandManager extends ListenerAdapter {

	/**
	 * Register Commands.
	 * @param commands Command.
	 */
	public void registerCommands(Command... commands) {
		DiscordLink.getInstance().getCommands().addAll(Arrays.asList(commands));
	}

	/**
	 * Get's Commands
	 * @return Commands.
	 */
	public List<Command> getCommands() {
		return new ArrayList<>(DiscordLink.getInstance().getCommands());
	}

	/**
	 * Message Listener.
	 */
	@Override
	public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
		if (event.getAuthor().isBot()) return;

		String[] messageArray = event.getMessage().getContentRaw().split(" ");
		String command = messageArray[0];

		if (command.startsWith(MessageManager.PREFIX)) {
			String[] args = new String[messageArray.length - 1];
			IntStream.range(0, messageArray.length).filter(i -> i > 0).forEach(i -> args[i - 1] = messageArray[i]);

			for (Command cmd : DiscordLink.getInstance().getCommands()) {
				if ((MessageManager.PREFIX + cmd.name).equalsIgnoreCase(command)) {
					if (!cmd.execute(event, args)) {
						System.out.println("Failed to execute.");
					}
					return;
				}

				if (cmd.getAliases().contains(command.substring(1).toLowerCase())) {
					if (!cmd.execute(event, args)) {
						System.out.println("Failed to execute.");
					}
					return;
				}
			}
		}
	}
}