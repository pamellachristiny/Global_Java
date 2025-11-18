
package br.com.fiap.global.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectionfactory {

    private static String url;
    private static String user;
    private static String password;

    static {
        // Prioritize environment variables (useful for Render)
        url = System.getenv("ORACLE_URL");
        user = System.getenv("ORACLE_USER");
        password = System.getenv("ORACLE_PASSWORD");

        // Fallbacks can be set here if needed
        if (url == null) {
            url = System.getProperty("oracle.url");
        }
        if (user == null) {
            user = System.getProperty("oracle.user");
        }
        if (password == null) {
            password = System.getProperty("oracle.password");
        }
    }

    public static Connection getConnection() throws SQLException {
        if (url == null || user == null) {
            throw new SQLException("Database connection parameters not set. Please set ORACLE_URL and ORACLE_USER (and ORACLE_PASSWORD).");
        }
        return DriverManager.getConnection(url, user, password);
    }
}
