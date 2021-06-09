package TheLostHotel.Other;

import TheLostHotel.Parser.Parser;
import TheLostHotel.Parser.ParserOutput;

public class GameInteraction {

    private GameManager g;
    private Parser p;

    // Costruttore
    GameInteraction(GameManager g) {
        this.g = g;
        p = new Parser();
    }

    public GameManager getGameManager() {
        return g;
    }

    /**
     * Si occupa di dare delle risposte ai comandi inseriti dal giocatore.
     * @param sInput comando preso in input
     * @return risposta al giocatore
     */
    public String inputPlayer(String sInput) {

        ParserOutput pOutput;
        String sOutput = "";

        try {
            // Consegna la stringa in input al parser e la salva nel ParserOutput
            pOutput = p.parse(sInput, g.getGame().getCurrentRoom(), g.getGame().getInventory(), g.getGame().getCommands());

            // Salva in una stringa di output la risposta ricevuta dal gestore
            sOutput = g.executeCommand(pOutput);
        } catch (Exception e) {
            sOutput = "Idea interessante... ma no.";
        }

        return sOutput;
    }
}
