package me.itxfrosty.discordlink.managers;

import me.itxfrosty.discordlink.DiscordLink;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkManager {

    /**
     * Link Manager.
     *
     * Creation date: 3/13/2021
     * @author itxfrosty
     */

    private final HashMap<Integer, Player> links = new HashMap<>();
    private static final HashMap<Player, Integer> hasCode = new HashMap<>();

    private final DatabaseManager database = DiscordLink.getDBManager();

    /**
     * Start's Link process.
     *
     * @param player Player.
     * @return Code for link.
     */
    public int link(Player player) {
        int code = generateCode();
        links.put(code, player);
        hasCode.put(player, code);
        return code;

    }

    /**
     * Confirms Link.
     *
     * @param code Code.
     * @return Player.
     */
    public Player confirmLink(int code) {
        if (!links.containsKey(code)) return null;
        hasCode.remove(links.get(code));
        return links.get(code);
    }

    /**
     * Unlinks player.
     * @param player Player.
     */
    public boolean unlink(Player player) {
        if (database.contains(player.getUniqueId())) {
            database.remove(player.getUniqueId());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Generates Code for Link.
     * @return Code.
     */
    public int generateCode() {
        int code = new Random().nextInt(10000);
        while (links.containsKey(code)) code = new Random().nextInt(10000);
        return code;
    }

    public HashMap<Player, Integer> getHasCode() {
        return hasCode;
    }
}
