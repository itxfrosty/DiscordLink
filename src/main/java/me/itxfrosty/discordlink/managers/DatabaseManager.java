package me.itxfrosty.discordlink.managers;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {

    /**
     * Database Manager.
     *
     * Creation Date: 3/12/2021
     * @author itxfrosty
     */

    private Connection connection;

    private final String host;
    private final String port;
    private final String database;
    private final String username;
    private final String password;

    /**
     * Public Constructor.
     *
     * @param host Database Host.
     * @param port Database Port.
     * @param database Database database.
     * @param username Database username.
     * @param password Database password.
     */
    public DatabaseManager(String host, String port, String database, String username, String password) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    /**
     * Connects to the Database.
     */
    public void connect() {
        String sql = "CREATE TABLE IF NOT EXISTS linked_users (player_uuid VARCHAR(100), discord_id VARCHAR(100), player_name VARCHAR(100));";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds player to the database.
     *
     * @param player Player's UUID.
     * @param discordID User's discord ID.
     * @param username Player's IGN.
     */
    public void insert(UUID player, String discordID, String username) {
        String SQL = String.format("INSERT INTO linked_users (player_uuid, discord_id, player_name) VALUES (\"%h\", \"%s\", \"%h\");", player, discordID, username);
        this.refreshConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute();
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
        this.refreshConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
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
        this.refreshConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Refreshes the Connection.
     */
    public void refreshConnection() {
        if (connection == null) {
            connect();
        }
    }

    /**
     * Manually close the connection.
     */
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}