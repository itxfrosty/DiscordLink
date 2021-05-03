package me.itxfrosty.database.tables;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LinkDB {

	private String playerUUID;
	private String discordID;

	private static Map<String, String> colMaps = new HashMap<>();

	public static Map<String, String> getColMaps() {
		if (colMaps.size() <= 0) {
			colMaps.put("player_uuid", "playerUUID");
			colMaps.put("discord_id", "discordID");
		}
		return colMaps;
	}


}