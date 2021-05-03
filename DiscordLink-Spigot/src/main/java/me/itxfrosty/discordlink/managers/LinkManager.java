package me.itxfrosty.discordlink.managers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class LinkManager {

	/**
	 * Start's Link.
	 *
	 * @param playerUUID Player's UUID.
	 * @return Code for Link.
	 */
	public String startLink(UUID playerUUID) {
		String code = generateCode(6);
		linkCache.put(code, playerUUID);
		codeCache.put(playerUUID, code);
		return code;
	}

	/**
	 * Confirm Link.
	 *
	 * @param code Code.
	 * @return UUID of Player.
	 */
	public UUID confirmLink(String code) {
		if (!linkCache.asMap().containsKey(code)) return null;
		codeCache.asMap().remove(linkCache.asMap().get(code));
		return linkCache.asMap().get(code);
	}

	/**
	 * Cache for Linking.
	 */
	@Getter
	private final Cache<String, UUID> linkCache = CacheBuilder.newBuilder()
			.expireAfterWrite(3, TimeUnit.MINUTES)
			.build();

	/**
	 * Cache for getting code.
	 */
	@Getter
	private final Cache<UUID,String> codeCache = CacheBuilder.newBuilder()
			.expireAfterWrite(3, TimeUnit.MINUTES)
			.build();

	/**
	 * Generates Code for link.
	 *
	 * @param digits Code Length.
	 * @return Code.
	 */
	public String generateCode(int digits) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < digits; i++) {
			String alphabetWithNumbers = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			builder.append(alphabetWithNumbers.charAt(ThreadLocalRandom.current().nextInt(0, alphabetWithNumbers.length())));
		}

		return builder.toString();
	}
}