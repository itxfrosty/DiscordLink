package me.itxfrosty.database.contexts;

import me.itxfrosty.database.Database;
import me.itxfrosty.database.tables.LinkDB;
import org.sql2o.Connection;

import java.util.List;

public class LinkContext {

	public static LinkDB getLinkUUID(String playerUUID) {
		String query = "SELECT * "
				+ "FROM linked_users "
				+ "WHERE player_uuid = :playerUUID";

		try(Connection con = Database.getSql2o().open()) {
			return con.createQuery(query)
					.addParameter("playerUUID", playerUUID)
					.executeAndFetchFirst(LinkDB.class);
		}
	}

	public static LinkDB getLinkID(String discordID) {
		String query = "SELECT * "
				+ "FROM linked_users "
				+ "WHERE discord_id = :discordID";

		try(Connection con = Database.getSql2o().open()) {
			return con.createQuery(query)
					.addParameter("discordID", discordID)
					.executeAndFetchFirst(LinkDB.class);
		}
	}

	public static List<LinkDB> containsLinkUUID(String playerUUID) {
		String query = "SELECT * "
				+ "FROM linked_users "
				+ "WHERE player_uuid = :playerUUID";

		try(Connection con = Database.getSql2o().open()) {
			return con.createQuery(query)
					.addParameter("playerUUID", playerUUID)
					.executeAndFetch(LinkDB.class);
		}
	}

	public static List<LinkDB> containsLinkID(String discordID) {
		String query = "SELECT * "
				+ "FROM linked_users "
				+ "WHERE discord_id = :discordID";

		try(Connection con = Database.getSql2o().open()) {
			return con.createQuery(query)
					.addParameter("discordID", discordID)
					.executeAndFetch(LinkDB.class);
		}
	}

	public static boolean insertLink(String playerUUID, String discordID) {
		String query = "INSERT INTO linked_users(player_uuid, discord_id) "
				+ "VALUES(:playerUUID, :discordID) ";

		try (Connection con = Database.getSql2o().open()) {
			con.createQuery(query)
					.addParameter("playerUUID", playerUUID)
					.addParameter("discordID", discordID)
					.executeUpdate();
			return con.getResult() > 0;
		}
	}

	public static boolean removeLinkD(String discordID) {
		String query = "DELETE FROM linked_users "
				+ "WHERE discord_id = :discordID";
		try (Connection con = Database.getSql2o().open()) {
			con.createQuery(query)
					.addParameter("discordID", discordID)
					.executeUpdate();
			return con.getResult() > 0;
		}
	}

	public static boolean removeLinkM(String playerUUID) {
		String query = "DELETE FROM linked_users " +
				"WHERE player_uuid = :playerUUID";
		try (Connection con = Database.getSql2o().open()) {
			con.createQuery(query)
					.addParameter("playerUUID", playerUUID)
					.executeUpdate();
			return con.getResult() > 0;
		}
	}
}