package me.itxfrosty.database.context;

import me.itxfrosty.database.DiscordDatabase;
import me.itxfrosty.database.tables.AccountLink;
import org.jetbrains.annotations.NotNull;
import org.sql2o.Connection;

import java.util.List;
import java.util.UUID;

public class AccountLinkContext {

	/**
	 * Insert's playerUUID, discordID and the time of the link in to the database.
	 *
	 * @param playerUUID UUID of the player to insert in to the database.
	 * @param discordID Long ID of the user to insert in to the database.
	 * @param linkDate Time of the link to insert in to the database.
	 * @return If insert was successful.
	 */
	public static boolean insertLink(@NotNull UUID playerUUID, long discordID, long linkDate) {
		final String insertQuery =
				"INSERT INTO linked_users(player_uuid, discord_id, link_date) " +
				"VALUES (:playerUUID, :discordID, :linkDate)";

		try (Connection con = DiscordDatabase.getSql2o().beginTransaction()) {
			con.createQuery(insertQuery)
					.addParameter("playerUUID", playerUUID.toString())
					.addParameter("discordID", String.valueOf(discordID))
					.addParameter("linkDate", String.valueOf(linkDate))
					.executeUpdate();
			return con.getResult() > 0;
		}
	}

	/**
	 * Gets Account Link from player's UUID.
	 *
	 * @param playerUUID UUID of the player's minecraft account.
	 * @return Account Link if player is linked in the database.
	 */
	public static AccountLink getAccountLinkByPlayer(@NotNull UUID playerUUID) {
		final String selectQuery =
				"SELECT * " +
				"FROM linked_users " +
				"WHERE player_uuid = :playerUUID";

		try (Connection con = DiscordDatabase.getSql2o().open()) {
			return con.createQuery(selectQuery)
					.addParameter("playerUUID", playerUUID.toString())
					.executeAndFetchFirst(AccountLink.class);
		}
	}

	/**
	 * Gets Account link from user's id.
	 *
	 * @param discordID ID of user's discord account.
	 * @return Account link if user is linked in the database.
	 */
	public static AccountLink getAccountLinkByUser(long discordID) {
		final String selectQuery =
				"SELECT * " +
				"FROM linked_users " +
				"WHERE discord_id = :discordID";

		try (Connection con = DiscordDatabase.getSql2o().open()) {
			return con.createQuery(selectQuery)
					.addParameter("discordID", String.valueOf(discordID))
					.executeAndFetchFirst(AccountLink.class);
		}
	}

	/**
	 * Check's if the user is linked.
	 *
	 * @param playerUUID UUID of the player's minecraft account.
	 * @return If linked or not.
	 */
	public static boolean containsAccountLinkByPlayer(@NotNull UUID playerUUID) {
		final String containsQuery =
				"SELECT * " +
				"FROM linked_users " +
				"WHERE player_uuid = :playerUUID";

		try (Connection con = DiscordDatabase.getSql2o().open()) {
			con.createQuery(containsQuery)
					.addParameter("playerUUID", playerUUID.toString())
					.executeAndFetch(AccountLink.class);
			return con.getResult() > 0;
		}
	}

	/**
	 * Check's if the user is linked.
	 *
	 * @param discordID ID of user's discord account.
	 * @return If linked or not.
	 */
	public static boolean containsAccountLinkByUser(long discordID) {
		final String containsQuery =
				"SELECT * " +
						"FROM linked_users " +
						"WHERE discord_id = :discordID";

		try (Connection con = DiscordDatabase.getSql2o().open()) {
			con.createQuery(containsQuery)
					.addParameter("discordID", String.valueOf(discordID))
					.executeAndFetch(AccountLink.class);
			return con.getResult() > 0;
		}
	}

	/**
	 * Removes AccountLink from database using player's UUID.
	 *
	 * @param playerUUID UUID of the player's minecraft account.
	 * @return If was able to remove.
	 */
	public static boolean removeAccountLinkByPlayer(UUID playerUUID) {
		final String removeQuery =
				"DELETE FROM linked_users " +
				"WHERE player_uuid = :playerUUID";

		try (Connection con = DiscordDatabase.getSql2o().open()) {
			con.createQuery(removeQuery)
					.addParameter("playerUUID", playerUUID)
					.executeUpdate();
			return con.getResult() > 0;
		}
	}

	/**
	 * Removes AccountLink from database using user's ID.
	 *
	 * @param discordID ID of user's discord account.
	 * @return If was able to remove.
	 */
	public static boolean removeAccountLinkByUser(long discordID) {
		final String removeQuery =
				"DELETE FROM linked_users " +
				"WHERE discord_id = :discordID";

		try (Connection con = DiscordDatabase.getSql2o().open()) {
			con.createQuery(removeQuery)
					.addParameter("discordID", discordID)
					.executeUpdate();
			return con.getResult() > 0;
		}
	}

	/**
	 * Fetches a list of current linked accounts.
	 *
	 * @return List of AccountLink's.
	 */
	public static List<AccountLink> fetchAccountLinks() {
		final String fetchQuery =
				"SELECT * " +
				"FROM linked_users";
		try (Connection con = DiscordDatabase.getSql2o().open()) {
			return con.createQuery(fetchQuery)
					.executeAndFetch(AccountLink.class);
		}
	}

	public static void createTable() {
		final String createTable = "CREATE TABLE IF NOT EXISTS " +
				"linked_users(player_uuid VARCHAR(45),discord_id VARCHAR(45),link_date VARCHAR(45))";

		try (Connection con = DiscordDatabase.getSql2o().open()) {
			con.createQuery(createTable).executeUpdate();
		}
	}
}