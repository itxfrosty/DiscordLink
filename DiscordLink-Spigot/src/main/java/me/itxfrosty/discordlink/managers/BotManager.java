package me.itxfrosty.discordlink.managers;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BotManager {

	@Getter private JDA jda;
	@Getter private JDABuilder jdaBuilder;

	@Getter private final String token;
	@Setter @Getter private OnlineStatus onlineStatus;
	@Setter @Getter private Activity activity;

	private final List<ListenerAdapter> eventListeners = new ArrayList<>();

	/**
	 * BotManager Constructor.
	 *
	 * @param token Discord Bot Token.
	 * @param onlineStatus Online Status.
	 * @param activity Activity.
	 */
	public BotManager(String token, OnlineStatus onlineStatus, Activity activity) {
		this.token = token;
		this.onlineStatus = onlineStatus;
		this.activity = activity;
	}

	/**
	 * BotManager Constructor.
	 *
	 * @param token Discord Bot Token.
	 */
	public BotManager(String token) {
		this.token = token;
	}

	/**
	 * Start's the bot.
	 */
	@SneakyThrows
	public void connect() {
		jdaBuilder = JDABuilder.createDefault(token);
		if ((onlineStatus != null) || (activity != null)) { jdaBuilder.setStatus(onlineStatus).setActivity(activity); }
		jdaBuilder.setMemberCachePolicy(MemberCachePolicy.ALL);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS);
		addListeners();
		jda = jdaBuilder.build().awaitReady();
	}

	/**
	 * Disconnects the bot if online.
	 */
	public void disconnectBot() {
		if (jda != null) {
			jda.shutdownNow();
		}
	}

	/**
	 * Register Listener.
	 *
	 * @param listeners Listener.
	 */
	public void registerListener(@NonNull ListenerAdapter listeners) {
		eventListeners.add(listeners);
	}

	/**
	 * Register Listeners.
	 *
	 * @param listeners Listeners.
	 */
	public void registerListeners(@NonNull ListenerAdapter... listeners) {
		Arrays.asList(listeners).forEach(this::registerListener);
	}

	/**
	 * Register's Listeners.
	 */
	public void addListeners() {
		for (ListenerAdapter listener : eventListeners) {
			jdaBuilder.addEventListeners(listener);
		}
	}
}
