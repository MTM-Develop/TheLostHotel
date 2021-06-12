package TheLostHotel.Other;

import javax.swing.*;
import java.io.IOException;

public class MenuManager {
    private GameInteraction gInteraction; // Gestore di interazione di gioco

    // Costruttore
    public MenuManager(GameManager g) {
        this.gInteraction = new GameInteraction(g);
    }

    /**
     * Carica una nuova partita da file
     * @throws IOException
     * //@throws InvalidStringException
     * @throws ClassNotFoundException
     */
    public void newGame() throws IOException, ClassNotFoundException {
        gInteraction.getGameManager().getGame().initGame("NewGame//NewGame.dat");
    }

    /**
     * Carica una partita salvata da file
     * @param path del file da cui caricare
     * @throws IOException
     * //@throws InvalidStringException
     * @throws ClassNotFoundException
     */
    public void loadGame(String path) throws IOException, ClassNotFoundException {
        gInteraction.getGameManager().getGame().initGame(path);
    }

    /**
     * Esce dal gioco
     */
    public void quitGame() {
        Runtime.getRuntime().exit(0);
    }

    // Metodi di get e set

    public GameInteraction getgInteraction() {
        return gInteraction;
    }

    public void setgInteraction(GameInteraction gInteraction) {
        this.gInteraction = gInteraction;
    }
}
