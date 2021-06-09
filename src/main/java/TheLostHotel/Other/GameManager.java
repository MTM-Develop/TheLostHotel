package TheLostHotel.Other;

import TheLostHotel.Parser.ParserOutput;

/**
 * Classe astratta che definisce il gestore di gioco.
 * A seconda del gioco che si vuole implementare e della differente
 * gestione dei comandi si possono implementare i metodi.
 */
public abstract class GameManager {

    private GameDescription game; // entità di gioco

    // Musica di sottofondo del gioco
    //private PlayMusic music = new PlayMusic();

    // Costruttori

    public GameManager(GameDescription g) {
        this.game = g;
    }

    /*public GameManager(Game g, String url ) {
        this.game = g;

        // Caricamento della musica
        //music.playSound(url);
    }*/

    // Metodi di get e set

    public GameDescription getGame() {
        return game;
    }

    public void setGame(GameDescription game) {
        this.game = game;
    }

    /*public PlayMusic getMusic() {
        return music;
    }

    public void setMusic(PlayMusic music) {
        this.music = music;
    }*/

    /**
     * Esegue il comando dell'utente, se corretto rispetto
     * alla logica dello stato attuale del gioco.
     *
     * @param pOutput input dell'utente ridefinito e strutturato dal Parser
     * @return stringa di risposta per l'utente
     */
    protected abstract String executeCommand(ParserOutput pOutput);

    /**
     * Mostra la guida di gioco col fine di spiegare come inserire input validi
     * e i comandi presenti.
     * @return stringa di guida
     */
    public abstract String showHelp();
}
