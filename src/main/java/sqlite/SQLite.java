package sqlite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//fuer hauptklasse
//beim start
//LiteSQL.connect();
//SQLManager.onCreate();
//im shutdown
//LiteSQL.disconnect();

public class SQLite {

	private static Connection conn;
	private static Statement stmt;
	// hab keinen bock auf konstruktor, die connection ist static
	public static void connect () {
		conn = null; // falls sies irgendwie nicht ist
		// erstellen der datenbank
		try {
			File file = new File ("Datenbank.db");
			if (!file.exists()) {
				file.createNewFile();
        }
        // eigentliche connection
        String url = "jdbc:sqlite:" + file.getPath();
        conn = DriverManager.getConnection(url);
        stmt = conn.createStatement();

        System.out.println("Verbindung hergestellt."); //debug
      } catch (SQLException | IOException e) {
    	  e.printStackTrace(); // wenn er meckert
      }
	}

	public static void disconnect() {
		try {
			if (conn != null) { // null wirft fehler
				conn.close();
				System.out.println("Verbindung getrennt.");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // wenn er meckert
		}
	}

	//veraenderung der datenbank, string muss in sql"sprache" sein
	public static void onUpdate(String sql) {
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace(); // wenn er meckert
		}
	}

    //lesen der datenbank, string muss in sql"sprache" sein
	public static ResultSet onQuery(String sql) {
		try {
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace(); // wie immer
			return null;
		}
	}
}
