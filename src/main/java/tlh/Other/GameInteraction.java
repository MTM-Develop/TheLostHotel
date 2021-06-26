package tlh.Other;

import tlh.Parser.Parser;
import tlh.Parser.ParserOutput;

public class GameInteraction {

    /**
     * Gestore di gioco.
     */
    private GameManager g;

    /**
     * Parser del gioco.
     */
    private Parser p;

    // Costruttore
    GameInteraction(final GameManager gM) {
        this.g = gM;
        p = new Parser();
    }

    /**
     * @return gestore del gioco.
     */
    public GameManager getGameManager() {
        return g;
    }

    /**
     * Si occupa di dare delle risposte ai comandi inseriti dal giocatore.
     *
     * @param sInput comando preso in input.
     * @return risposta al giocatore.
     */
    public String inputPlayer(final String sInput) {

        ParserOutput pOutput;
        String sOutput = "";

        try {
            //Consegna la stringa in input al parser e la salva nel ParserOutput
            pOutput = p.parse(sInput, g.getGame().getCurrentRoom(),
                    g.getGame().getInventory(), g.getGame().getCommands());

            //Salva in una stringa di output la risposta ricevuta dal gestore
            sOutput = g.executeCommand(pOutput);
        } catch (Exception e) {
            sOutput = "Idea interessante... ma no.";
        }

        return sOutput;
    }
}
