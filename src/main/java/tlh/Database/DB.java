/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlh.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

/**
 * Classe che gestisce il jdbc per
 * tenere traccia dei giocatori e i loro punteggi.
 */
public class DB {

    /**
     * Utile per la connessione al db.
     */
    private Connection con;

    /**
     * Query di creazione.
     */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + "scores (name VARCHAR(25), time TIME, vote VARCHAR(2))";

    /**
     * Query di selezione.
     */
    private static final String SELECT_TABLE = "SELECT name, time, vote FROM scores "
            + "ORDER BY time LIMIT 3";

    /**
     * Query di inserimento.
     */
    private static final String INSERT_VALUES =
            "INSERT INTO scores VALUES (?,?,?)";

    //private static final String QUERY_DROP = "DROP TABLE IF EXISTS scores";

    /**
     * Si connette al db locale.
     *
     * @throws SQLException
     */
    public void connect() throws SQLException {

        con = DriverManager.getConnection(
                "jdbc:h2:./resources/database/scores");

        Statement stm = con.createStatement();
        stm.executeUpdate(CREATE_TABLE);
        stm.close();
    }


    /**
     * Si riconnette, se la connessione è caduta.
     *
     * @throws SQLException
     */
    public void reconnect() throws SQLException {
        if (con != null && !con.isValid(0)) {
            con = DriverManager.getConnection("jdbc:h2:./resources/db/scores");
        }
    }

    /**
     * Inserisce il punteggio di un nuovo giocatore.
     *
     * @param s nome del giocatore.
     * @param t punteggio del giocatore (in questo caso il tempo).
     * @throws SQLException
     * @throws ParseException
     */
    public void insertScore(final String s, final String t)
            throws SQLException, ParseException {
        reconnect();
        PreparedStatement prstm = con.prepareStatement(INSERT_VALUES);
        prstm.setString(1, s);

        // Converte la stringa in tempo.
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Time time = new Time(dateFormat.parse(
                t.replaceAll("[hms]", ":")).getTime());
        prstm.setTime(2, time);

        String vote = null;
        if (time.toLocalTime().isBefore(LocalTime.of(0, 20))) {
            vote = "A+";
        } else { //CONTINUARE CON ALTRI CONTROLLI SUL VOTO DA DARE
            vote = "B";
        }
        prstm.setString(3, vote);

        prstm.executeUpdate();
        prstm.close();
    }

    /**
     * Restituisce il risultato della query
     * per i migliori 3 punteggi.
     *
     * @return stringa.
     * @throws SQLException
     */
    public String topScores() throws SQLException {
        reconnect();

        StringBuilder results = new StringBuilder();
        Statement stm = con.createStatement();
        ResultSet resSet = stm.executeQuery(SELECT_TABLE);

        while (resSet.next()) {

            results.append(resSet.getString(1)).
                    append("\t").
                    append(resSet.getTime(2)).
                    append("\t").
                    append(resSet.getString(3)).
                    append("\n\n");
        }

        resSet.close();
        stm.close();

        return results.toString();
    }

    /**
     * Chiude la connessione del database.
     *
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        con.close();
    }

}
