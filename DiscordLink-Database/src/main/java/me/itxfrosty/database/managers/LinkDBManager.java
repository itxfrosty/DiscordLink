package me.itxfrosty.database.managers;

import me.itxfrosty.database.QueryHandler;
import me.itxfrosty.database.contexts.LinkContext;
import me.itxfrosty.database.tables.LinkDB;

import java.util.List;

public class LinkDBManager {

	/**
	 * Get's LinkDB of player.
	 *
	 * @param playerUUID UUID of Player.
	 * @return LinkDB.
	 */
	public static QueryHandler<LinkDB> getLinkUUID(String playerUUID) {
		return new QueryHandler<>(() -> LinkContext.getLinkUUID(playerUUID));
	}

	/**
	 * Get's LinkDB of user.
	 *
	 * @param discordID ID of User.
	 * @return LinkDB.
	 */
	public static QueryHandler<LinkDB> getLinkID(String discordID) {
		return new QueryHandler<>(() -> LinkContext.getLinkID(discordID));
	}

	/**
	 * Get's list of players/users in the database.
	 *
	 * @param playerUUID UUID of Player.
	 * @return List of UUID's.
	 */
	public static QueryHandler<List<LinkDB>> containsLinkUUID(String playerUUID) {
		return new QueryHandler<>(() -> LinkContext.containsLinkUUID(playerUUID));
	}

	/**
	 * Get's list of players/users in the database.
	 *
	 * @param discordID UUID of Player.
	 * @return List of UUID's.
	 */
	public static QueryHandler<List<LinkDB>> containsLinkID(String discordID) {
		return new QueryHandler<>(() -> LinkContext.containsLinkID(discordID));
	}

	/**
	 * Inserts User's Player's UUID and Discord ID.
	 *
	 * @param playerUUID UUID of Player.
	 * @param discordID ID of Discord User.
	 */
	public static QueryHandler<Boolean> insertLink(String playerUUID, String discordID) {
		return new QueryHandler<>(() -> LinkContext.insertLink(playerUUID, discordID));
	}

	/**
	 * Remove's User ID/Player UUID from database.
	 *
	 * @param discordID ID of User.
	 */
	public static QueryHandler<Boolean> removeLinkD(String discordID) {
		return new QueryHandler<>(() -> LinkContext.removeLinkD(discordID));
	}

	/**
	 * Remove's User ID/Player UUID from database.
	 *
	 * @param playerUUID UUID of Player.
	 */
	public static QueryHandler<Boolean> removeLinkM(String playerUUID) {
		return new QueryHandler<>(() -> LinkContext.removeLinkM(playerUUID));
	}

}
