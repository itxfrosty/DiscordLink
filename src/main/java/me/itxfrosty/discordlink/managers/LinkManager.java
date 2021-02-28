package me.itxfrosty.discordlink.managers;

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
    //private static final dbm db = new dbm("na04-sql.pebblehost.com",3306,"customer_171117_link","customer_171117_link","3rcL2XSc!Vsr8iNrk48J");

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
        //db.connect();
        //db.remove(player.getUniqueId());
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