package tlh.Other;

import tlh.Parser.ParserOutput;

/**
 * Classe astratta che definisce il gestore di gioco.
 * A seconda del gioco che si vuole implementare e della differente
 * gestione dei comandi, si possono implementare i metodi.
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
     * @param g
     */
    public GameManager(final GameDescription g) {
        this.game = g;
    }

    /**
     * Costruttore.
     *
     * @param g
     * @param url
     */
    public GameManager(GameDescription g, String url ) {
        this.game = g;

        // Caricamento della musica
        music.playSound(url);
    }

    /**
     * @return entità di gioco.
     */
    public GameDescription getGame() {
        return game;
    }

    /**
     * Imposta l'entità di gioco.
     *
     * @param g
     */
    public void setGame(final GameDescription g) {
        this.game = g;
    }

    /**
     * @return musica del gioco.
     */
    public Music getMusic() {
        return music;
    }

    /**
     * Imposta la musica del gioco.
     *
     * @param music
     */
    public void setMusic(Music music) {
        this.music = music;
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
