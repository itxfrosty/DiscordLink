package me.itxfrosty.discordlink.utils;

public class ConsoleMessage {
    public static final String RESET = "\u001b[0m";
    public static final String BLACK = "\u001b[0;30m";
    public static final String RED = "\u001b[0;31m";
    public static final String GREEN = "\u001b[0;32m";
    public static final String YELLOW = "\u001b[0;33m";
    public static final String BLUE = "\u001b[0;34m";
    public static final String PURPLE = "\u001b[0;35m";
    public static final String CYAN = "\u001b[0;36m";
    public static final String WHITE = "\u001b[0;37m";

    public ConsoleMessage() {
    }

    public static void log(String message) {
        System.out.println(message + "\u001b[0m");
    }

    public static void log(String plugin, String message) {
        System.out.println("\u001b[0;33m[" + plugin + "] " + message + "\u001b[0m");
    }
}
