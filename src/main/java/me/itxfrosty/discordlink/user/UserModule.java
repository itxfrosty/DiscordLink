package me.itxfrosty.discordlink.user;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserModule implements Listener {

	/**
	 * Instance Variable.
	 */
	private static UserModule instance;

	/**
	 * User's Map.
	 */
	@Getter private final HashMap<Player, User> users = new HashMap<>();

	/**
	 * Get's Instance of Class.
	 * @return UserModule.
	 */
	public static UserModule getInstance() {
		if(instance == null) {
			instance = new UserModule();
		}

		return instance;
	}

	/**
	 * Get's Online Players.
	 * @return Get's all Online Players.
	 */
	public List<Player> getOnlinePlayers() {
		return new ArrayList<>(users.keySet());
	}

	/**
	 * Get's User.
	 *
	 * @param player Player.
	 * @return User.
	 */
	public User getUser(Player player) {
		return this.users.get(player);
	}

	/**
	 * Adds Player to User.
	 * @param player Player.
	 */
	public void addUser(Player player) {
		this.users.put(player, new User(player));
	}

	/**
	 * Removes User froms Users.
	 * @param player Player.
	 */
	public void removeUser(Player player) {
		this.users.remove(player);
	}

	/**
	 * Join Player User Handler.
	 */
	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		addUser(player);
	}

	/**
	 * Quit Player User Handler.
	 */
	@EventHandler
	public void quit(PlayerQuitEvent e) {
		Player player = e.getPlayer();

		removeUser(player);
	}
}