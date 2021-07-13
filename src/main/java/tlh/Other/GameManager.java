package tlh.Other;

import tlh.Parser.ParserOutput;

/**
 * Classe astratta che definisce il gestore di gioco.
 * A seconda del gioco che si vuole implementare e della differente
 * gestione dei comandi, si possono implementare i metodi.
 *
 * @author MTM-Develop.
 */
public abstract class GameManager {

    /**
     * Entità di gioco.
     */
    private GameDescription game;

    /**
     * Musica di sottofondo del gioco.
     */
    private Music music = new Music();

    /**
     * Costruttore.
     *
     * @param g gestore di gioco.
     */
    public GameManager(final GameDescription g) {
        this.game = g;
    }

    /**
     * Costruttore.
     *
     * @param g gestore di gioco.
     * @param url percorso per riprodurre la musica di gioco.
     */
    public GameManager(final GameDescription g, final String url) {
        this.game = g;

        // Caricamento della musica
        music.playSound(url);
    }

    /**
     * Restituisce l'entità di gioco.
     *
     * @return game.
     */
    public GameDescription getGame() {
        return game;
    }

    /**
     * Restituisce la musica del gioco.
     *
     * @return music.
     */
    public Music getMusic() {
        return music;
    }

    /**
     * Esegue il comando dell'utente, se corretto rispetto
     * alla logica dello stato attuale del gioco.
     *
     * @param pOutput input dell'utente ridefinito e strutturato dal Parser.
     * @return stringa di risposta per l'utente.
     */
    protected abstract String executeCommand(ParserOutput pOutput);

    /**
     * Mostra la guida di gioco al fine di spiegare come inserire
     * input validi e i comandi presenti.
     *
     * @return stringa di guida.
     */
    public abstract String showHelp();
}
