package tlh.Other;

import java.io.IOException;

public class MenuManager {

    /**
     * Gestore di interazione di gioco.
     */
    private GameInteraction gInteraction;

    /**
     * Costruttore.
     *
     * @param g
     */
    public MenuManager(final GameManager g) {
        this.gInteraction = new GameInteraction(g);
    }

    /**
     * Carica una nuova partita da file.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void newGame() throws IOException, ClassNotFoundException {
        gInteraction.getGameManager().getGame().
                initGame("NewGame//NewGame.dat");
    }

    /**
     * Carica una partita salvata da file.
     *
     * @param path del file da cui caricare.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadGame(final String path)
            throws IOException, ClassNotFoundException {
        gInteraction.getGameManager().getGame().initGame(path);
    }

    /**
     * Esce dal gioco.
     */
    public void quitGame() {
        Runtime.getRuntime().exit(0);
    }

    /**
     * @return gestore di interazione di gioco.
     */
    public GameInteraction getgInteraction() {
        return gInteraction;
    }

    /**
     * Imposta il gestore di interazione di gioco.
     *
     * @param gameInteraction
     */
    public void setgInteraction(final GameInteraction gameInteraction) {
        this.gInteraction = gameInteraction;
    }
}
