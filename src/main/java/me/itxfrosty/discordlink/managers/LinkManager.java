package me.itxfrosty.discordlink.managers;

import me.itxfrosty.discordlink.DiscordLink;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class LinkManager extends ListenerAdapter {

    /**
     * Creation date: 2/13/2021
     * @author itxfrosty
     */

    // This is is disgusting. FIX THIS.
    private static final HashMap<Integer, Player> links = new HashMap<>();

    public static HashMap<UUID, Integer> checks = new HashMap<UUID, Integer>();
    public static HashMap<Player, String> done = new HashMap<Player, String>();
    public final HashMap<Player, Integer> link2 = new HashMap<>();

    /**
     * Start's Link for player.
     *
     * @param player Player.
     * @return Code for Link.
     */
    public static int link(Player player) {
        int code = code();
        links.put(code, player);
        return code;
    }

    /**
     * End's Link for player.
     *
     * @param code Player's Code.
     * @return player's UUID.
     */
    public static Player completeLink(int code) {
        if (!links.containsKey(code)) return null;
        return links.get(code);
    }

    /**
     * Deletes code from HashMap.
     *
     * @param code Player's Code.
     */
    public static void remove(int code) {
        if (!links.containsKey(code)) return;
        links.remove(code);
    }

    public static void unlink(Player player) {
        DiscordLink.getDBManager().remove(player.getUniqueId());
    }

    /**
     * Creates Code for linking player.
     *
     * @return The code.
     */
    private static int code() {
        int code = new Random().nextInt(10000);
        while (links.containsKey(code)) code = new Random().nextInt(10000);
        return code;
    }



}