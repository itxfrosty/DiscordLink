package me.itxfrosty.discordlink.commands.discord;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface CommandContext {

	void run(GuildMessageReceivedEvent event, String[] args);

	String getCommand();

	String getHelp();
}
