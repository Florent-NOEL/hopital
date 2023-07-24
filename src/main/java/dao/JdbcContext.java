package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcContext {
    private Connection connection;
	private static JdbcContext singleton = null;

    static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Factory
	private JdbcContext() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital", "root", "root123@");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static JdbcContext getContext() {
		if (singleton == null) {
			singleton = new JdbcContext();
		}
		return singleton;
	}

	public Connection getConnection() {
		return connection;
	}

	public static void close() {
		if (singleton != null) {
			try {
				singleton.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				singleton = null;
			}
		}
	}
}
