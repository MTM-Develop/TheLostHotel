package tlh.Other;

import java.io.IOException;

/**
 * Classe che gestisce il menu principale di gioco.
 *
 * @author MTM-Develop.
 */
public class MenuManager {

    /**
     * Gestore di interazione di gioco.
     */
    private GameInteraction gInteraction;

    /**
     * Costruttore.
     *
     * @param g gestore di gioco.
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
     * Restituisce il gestore di interazione di gioco.
     *
     * @return gInteraction.
     */
    public GameInteraction getgInteraction() {
        return gInteraction;
    }

}
