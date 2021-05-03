package me.itxfrosty.discordlink.commands.discord;

import com.google.common.collect.Sets;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public abstract class Command {

	public final String name;
	public final Category category;
	public final String description;
	public final String usage;

	/**
	 * Command Constructor.
	 *
	 * @param name Name of Command.
	 * @param description Description of Command.
	 * @param usage Usage of Command.
	 * @param category Category of Command.
	 */
	public Command(String name, String description, String usage, Category category) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.usage = usage;
	}

	/**
	 * Command Executor.
	 */
	public abstract boolean execute(MessageReceivedEvent event, String[] args);

	/**
	 * Get's Aliases.
	 * @return Aliases.
	 */
	public @NotNull Set<String> getAliases() {
		return Sets.newHashSet();
	}

	/**
	 * Command Category Enum.
	 */
	public enum Category {
		STAFF, LEVELS, MUSIC, ECONOMY, FUN, UTILITY
	}
}