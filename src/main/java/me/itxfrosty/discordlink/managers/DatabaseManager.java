package me.itxfrosty.discordlink.managers;

import me.itxfrosty.discordlink.utils.ConsoleMessage;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {

    /**
     * Creation Date: 2/25/2021
     * @author itxfrosty
     */

    private Connection connection;

    private final String host = "na04-sql.pebblehost.com";
    private final int port = 3306;
    private final String database = "customer_171117_link";
    private final String username = "customer_171117_link";
    private final String password = "3rcL2XSc!Vsr8iNrk48J";

    /**
     * Connects to SQL.
     */
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"
                            + this.host + ":" + this.port + "/" + this.database,
                    this.username, this.password);
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    /**
     * Disconnects from SQL.
     */
    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                ConsoleMessage.log("Failed to close database connection");
            }
        }
    }

    /**
     * Adds player to the database.
     *
     * @param player Player's UUID.
     * @param discordID User's discord ID.
     */
    public void insert(UUID player, String discordID) {
        String SQL = String.format("INSERT INTO linked_users (player_uuid, discord_id) VALUES (\"%h\", \"%s\");", player, discordID);
        try {
            Statement statement = connection.createStatement();
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if player is in database.
     *
     * @param player Player's UUID.
     * @return If player is in database.
     */
    public boolean contains(UUID player) {
        String SQL = String.format("SELECT * FROM linked_users WHERE player_uuid = \"%h\";", player);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Removes player from database.
     *
     * @param player Player's UUID.
     */
    public void remove(UUID player) {
        String SQL = String.format("DELETE FROM linked_users WHERE player_uuid = \"%h\";", player);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check's if Connection is null.
     *
     * @return Null if false.
     */
    public boolean isConnected() {
        return (connection != null);
    }

    /**
     * SQL connection.
     *
     * @return Connection.
     */
    public Connection getConnection() {
        return connection;
    }
}
