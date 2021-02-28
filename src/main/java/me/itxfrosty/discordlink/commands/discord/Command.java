package me.itxfrosty.discordlink.commands.discord;

import com.google.common.collect.Sets;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public abstract class Command {

    public static final String PREFIX = "!";

    public final String name;
    public final Category category;
    public final String description;
    public final String usage;

    public Command(@NotNull final String name, String description, String usage, Category category) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.category = category;
    }

    public abstract boolean execute(MessageReceivedEvent event, String[] args);

    public @NotNull Set<String> getAliases() {
        return Sets.newHashSet();
    }

    public enum Category {
        DEFAULT, MUSIC, STAFF
    }
}
