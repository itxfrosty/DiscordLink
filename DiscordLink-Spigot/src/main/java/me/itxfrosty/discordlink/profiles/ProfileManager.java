package me.itxfrosty.discordlink.profiles;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileManager implements Listener {

	private static ProfileManager instance;

	private final HashMap<Player, Profile> profiles = new HashMap<>();

	/**
	 * Get's List of Online Players.
	 * @return List of Players.
	 */
	public List<Player> getOnlinePlayers() {
		return new ArrayList<>(profiles.keySet());
	}

	/**
	 * Creates Player profile for Player.
	 * @param player Player.
	 */
	public void createProfile(Player player) {
		this.profiles.put(player, new Profile(player));
	}

	/**
	 * Removes Player's Profile.
	 * @param player Player.
	 */
	public void removeProfile(Player player) {
		this.profiles.remove(player);
	}

	/**
	 * Get's Player's Profile.
	 * @param player Player.
	 * @return Profile of Player.
	 */
	public Profile getProfile(Player player) {
		return this.profiles.get(player);
	}

	/**
	 * Check's if player has Profile.
	 * @param player Player.
	 * @return If Profile Exist.
	 */
	public boolean hasProfile(Player player) {
		return this.profiles.containsKey(player);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();

		this.createProfile(player);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		final Player player = event.getPlayer();

		this.removeProfile(player);
	}

	/**
	 * Get's Instance of Profile Manager.
	 * @return Instance of Profile Manager.
	 */
	public static ProfileManager getInstance() {
		if(instance == null) {
			instance = new ProfileManager();
		}
		return instance;
	}
}