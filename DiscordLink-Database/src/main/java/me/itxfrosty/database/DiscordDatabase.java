package me.itxfrosty.database;

import lombok.Setter;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import me.itxfrosty.database.context.AccountLinkContext;
import me.itxfrosty.database.tables.AccountLink;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

public class DiscordDatabase {

	private static Sql2o sql2o;
	private final static Map<String, String> colMaps = new HashMap<>();

	@Setter private static String host;
	@Setter private static int port;
	@Setter private static String database;
	@Setter private static String user;
	@Setter private static String password;

	private static void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(host);
		dataSource.setPort(port);
		dataSource.setDatabaseName(database);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setUseSSL(false);

		sql2o = new Sql2o(dataSource);

		registerColMaps();

		sql2o.setDefaultColumnMappings(colMaps);

		AccountLinkContext.createTable();
	}

	private static void registerColMaps() {
		colMaps.putAll(AccountLink.getColMaps());
	}

	public static Sql2o getSql2o() {
		if (sql2o == null) {
			createConnection();
		}
		return sql2o;
	}
}