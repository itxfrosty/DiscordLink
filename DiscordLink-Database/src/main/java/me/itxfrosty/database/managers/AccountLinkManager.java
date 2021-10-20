package me.itxfrosty.database.managers;

import me.itxfrosty.database.QueryHandler;
import me.itxfrosty.database.context.AccountLinkContext;
import me.itxfrosty.database.tables.AccountLink;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class AccountLinkManager {

	/**
	 * Insert's playerUUID, discordID and the time of the link in to the database.
	 *
	 * @param playerUUID UUID of the player to insert in to the database.
	 * @param discordID Long ID of the user to insert in to the database.
	 * @param linkDate Time of the link to insert in to the database.
	 * @return If insert was successful.
	 */
	public static QueryHandler<Boolean> insertLink(@NotNull UUID playerUUID, long discordID, long linkDate) {
		return new QueryHandler<>(() -> AccountLinkContext.insertLink(playerUUID, discordID, linkDate));
	}

	/**
	 * Gets Account Link from player's UUID.
	 *
	 * @param playerUUID UUID of the player's minecraft account.
	 * @return Account Link if player is linked in the database.
	 */
	public static QueryHandler<AccountLink> getAccountLinkByPlayer(@NotNull UUID playerUUID) {
		return new QueryHandler<>(() -> AccountLinkContext.getAccountLinkByPlayer(playerUUID));
	}

	/**
	 * Gets Account link from user's id.
	 *
	 * @param discordID ID of user's discord account.
	 * @return Account link if user is linked in the database.
	 */
	public static QueryHandler<AccountLink> getAccountLinkByUser(long discordID) {
		return new QueryHandler<>(() -> AccountLinkContext.getAccountLinkByUser(discordID));
	}

	/**
	 * Check's if the user is linked.
	 *
	 * @param playerUUID UUID of the player's minecraft account.
	 * @return If linked or not.
	 */
	public static QueryHandler<Boolean> containsAccountLinkByPlayer(@NotNull UUID playerUUID) {
		return new QueryHandler<>(() -> AccountLinkContext.containsAccountLinkByPlayer(playerUUID));
	}

	/**
	 * Check's if the user is linked.
	 *
	 * @param discordID ID of user's discord account.
	 * @return If linked or not.
	 */
	public static QueryHandler<Boolean> containsAccountLinkByUser(long discordID) {
		return new QueryHandler<>(() -> AccountLinkContext.containsAccountLinkByUser(discordID));
	}

	/**
	 * Removes AccountLink from database using player's UUID.
	 *
	 * @param playerUUID UUID of the player's minecraft account.
	 * @return If was able to remove.
	 */
	public static QueryHandler<Boolean> removeAccountLinkByPlayer(UUID playerUUID) {
		return new QueryHandler<>(() -> AccountLinkContext.removeAccountLinkByPlayer(playerUUID));
	}

	/**
	 * Removes AccountLink from database using user's ID.
	 *
	 * @param discordID ID of user's discord account.
	 * @return If was able to remove.
	 */
	public static QueryHandler<Boolean> removeAccountLinkByUser(long discordID) {
		return new QueryHandler<>(() -> AccountLinkContext.removeAccountLinkByUser(discordID));
	}

	/**
	 * Fetches a list of current linked accounts.
	 *
	 * @return List of AccountLink's.
	 */
	public static QueryHandler<List<AccountLink>> fetchAccountLinks() {
		return new QueryHandler<>(AccountLinkContext::fetchAccountLinks);
	}
}