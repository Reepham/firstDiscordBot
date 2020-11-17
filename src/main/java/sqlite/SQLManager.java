package sqlite;

import java.sql.SQLException;

public class SQLManager {

	public static void onCreate() throws SQLException {

    // ID = Entryzahl
		// UserID = Discord ID des Users
    // Username = Name des Users
    // Zitattext = Selbsterklaerend

	SQLite.onUpdate("CREATE TABLE IF NOT EXISTS quotes(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, userid TEXT, username TEXT, zitattext TEXT)");
		//debug
		//LiteSQL.onUpdate("INSERT INTO quotes(userid, username, zitat) VALUES ('716953065885794348', 'Raffael', 'Kuschel mich Daddy')");
		//LiteSQL.onUpdate("INSERT INTO quotes(userid, username, zitat) VALUES ('776846250557440002', 'ThimoBot', 'Aber gerne')");
	}
}
