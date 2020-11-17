package events;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Util {



        private static String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        public static JSONObject readJsonFromUrl(String url) throws JSONException {

            InputStream is;
            try {
                is = new URL(url).openStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);
                is.close();
                return json;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

      public static float getQuoteCount(String userID) {
          try {
              ResultSet rs1 = SQLite.onQuery("SELECT COUNT('zitattext') AS zitattext FROM quotes WHERE username = '" + userID + "'");
              ResultSet rs2 = SQLite.onQuery("SELECT COUNT('zitattext') AS zitattext FROM quotes");
              Float prozentwert = rs1.getFloat(1) / rs2.getFloat(1);
              return prozentwert;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;

        }
}
