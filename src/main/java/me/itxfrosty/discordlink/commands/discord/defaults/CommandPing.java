package me.itxfrosty.discordlink.commands.discord.defaults;

import me.itxfrosty.discordlink.commands.discord.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class CommandPing extends Command {


    public CommandPing() {
        super("ping","Ping Command", "!ping", Category.DEFAULT);
    }

    @Override
    public boolean execute(MessageReceivedEvent event, String[] args) {
        long time = System.currentTimeMillis();
        Message msg = event.getChannel().sendMessage(":signal_strength: Ping").complete();
        long latency = System.currentTimeMillis() - time;
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(":ping_pong: Pong!");
        embed.addField("Latency", latency + "ms", false);
        embed.addField("API", "2ms", false);
        embed.setColor(Color.CYAN);
        event.getChannel().sendMessage(embed.build()).queue();
        msg.delete().queue();
        return true;
    }
}
