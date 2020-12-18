package events;

import org.json.JSONException;
import org.json.JSONObject;
import sqlite.SQLite;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd;
        try{
            bd = BigDecimal.valueOf(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
        }catch (Exception e){
            return value;
        }

        return bd.doubleValue();
    }

    public static double getQuoteCount(String userID) {
        try {
            ResultSet rs1 = SQLite.onQuery("SELECT COUNT('zitattext') AS zitattext FROM quotes WHERE username = '" + userID + "'");
            ResultSet rs2 = SQLite.onQuery("SELECT COUNT('zitattext') AS zitattext FROM quotes");
            double prozentwert = rs1.getDouble(1) / rs2.getDouble(1);
            return prozentwert;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }
    public static LocalDate getMaddindate() {
      try {
            ResultSet rs1 = SQLite.onQuery("SELECT time FROM Maddin WHERE MID =(select max(MID) From Maddin)");
            return LocalDate.parse(rs1.getString("time"));


       } catch (SQLException e) {
          e.printStackTrace();
        }
      return null;

    }
    public static void insertMaddindate(LocalDate newtime) {
        SQLite.onQuery("INSERT INTO Maddin (time) VALUES ('"+newtime.toString()+"');");
    }
}
