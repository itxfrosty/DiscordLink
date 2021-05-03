package me.itxfrosty.discordlink.user;

import lombok.Getter;
import org.bukkit.entity.Player;

public class User {

	@Getter private final Player player;

	public User(Player player) {
		this.player = player;
	}
}