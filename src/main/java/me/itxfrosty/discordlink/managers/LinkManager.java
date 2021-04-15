package me.itxfrosty.discordlink.managers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import me.itxfrosty.discordlink.DiscordLink;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class LinkManager {

	/**
	 * Link Manager.
	 *
	 * Creation date: 4/7/2021
	 * @author itxfrosty
	 */

	private static final String alphabetWithNumbers = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * Start's Link.
	 *
	 * @param player Player.
	 * @return Code for Link.
	 */
	public String link(Player player) {
		String code = DiscordLink.getLinkManager().generateCode(6);
		UUID pp = player.getUniqueId();
		getLinkCache().put(code, pp);
		getCodeCache().put(pp, code);
		return code;
	}

	public UUID confirmLink(String code) {
		if (!linkCache.asMap().containsKey(code)) return null;
		codeCache.asMap().remove(linkCache.asMap().get(code));
		return linkCache.asMap().get(code);
	}


	/**
	 * Cache for Linking.
	 */
	private final Cache<String, UUID> linkCache = CacheBuilder.newBuilder()
			.expireAfterWrite(3, TimeUnit.MINUTES)
			.build();

	/**
	 * Cache for getting code.
	 */
	private final Cache<UUID,String> codeCache = CacheBuilder.newBuilder()
			.expireAfterWrite(3, TimeUnit.MINUTES)
			.build();


	/**
	 * Generates Code.
	 *
	 * @param digits Code Length.
	 * @return Code.
	 */
	public String generateCode(int digits) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < digits; i++) {
			builder.append(alphabetWithNumbers.charAt(ThreadLocalRandom.current().nextInt(0, alphabetWithNumbers.length())));
		}

		return builder.toString();
	}

	/**
	 * Getter for Link Cache.
	 * @return LinkCache.
	 */
	public Cache<String,UUID> getLinkCache() {
		return linkCache;
	}


	/**
	 * Getter for Code Cache.
	 * @return Code Cache.
	 */
	public Cache<UUID,String> getCodeCache() {
		return codeCache;
	}
}