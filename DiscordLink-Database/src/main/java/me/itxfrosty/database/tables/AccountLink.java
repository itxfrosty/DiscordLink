package me.itxfrosty.database.tables;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AccountLink {

	private final String playerUUID;
	private final String discordID;
	private final String linkDate;
	
	private static Map<String, String> colMaps = new HashMap<>();

	public static Map<String, String> getColMaps() {
		if (colMaps.size() <= 0) {
			colMaps.put("player_uuid", "playerUUID");
			colMaps.put("discord_id", "discord_ID");
			colMaps.put("link_date", "linkDate");
		}
		return colMaps;
	}





}
