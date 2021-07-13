package tlh.Other;

import tlh.File.File;
import tlh.Type.Command;
import tlh.Type.CommandType;
import tlh.Type.Inventory;
import tlh.Type.Room;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe che gestisce le fondamenta del gioco.
 *
 * @author MTM-Develop.
 */
public class GameDescription {

    /**
     * Comandi del gioco.
     */
    private Set<Command> commands;

    /**
     * Inventario del giocatore.
     */
    private Inventory inventory;

    /**
     * Stanza in cui si trova il giocatore.
     */
    private Room currentRoom;

    /**
     * Nome del giocatore.
     */
    private String player = "";

    /**
     * Tempo di completamento del gioco.
     */
    private GameTime gTime = new GameTime();

    /**
     * Costruttore.
     */
    public GameDescription() {
        this.commands = new HashSet<>();
        this.inventory = new Inventory();
    }

    /**
     * Restituisce l'inventario del giocatore.
     *
     * @return inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Imposta l'inventario del giocatore.
     *
     * @param inv inventario del giocatore.
     */
    public void setInventory(final Inventory inv) {
        this.inventory = inv;
    }

    /**
     * Restituisce la stanza corrente del giocatore.
     *
     * @return currentRoom.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Imposta la stanza corrente del giocatore.
     *
     * @param currRoom stanza corrente.
     */
    public void setCurrentRoom(final Room currRoom) {
        this.currentRoom = currRoom;
    }

    /**
     * Restituisce i comandi del gioco.
     *
     * @return commands.
     */
    public Set<Command> getCommands() {
        return commands;
    }

    /**
     * Imposta i comandi del gioco.
     *
     * @param gCommands comandi di gioco.
     */
    public void setCommands(final Set<Command> gCommands) {
        this.commands = gCommands;
    }

    /**
     * Restituisce il nome del giocatore.
     *
     * @return player.
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Imposta il nome del giocatore.
     *
     * @param p nome del giocatore.
     */
    public void setPlayer(final String p) {
        this.player = p;
    }

    /**
     * Restituisce il tempo di gioco.
     *
     * @return gTime.
     */
    public GameTime getgTime() {
        return gTime;
    }

    /**
     * Avvia una nuova partita da file.
     *
     * @param filePath percorso del file.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void initGame(final String filePath)
            throws IOException, ClassNotFoundException {

        File fs = new File();
        fs.readFile(filePath, this);

    }

    /**
     * Carica una partita esistente da file.
     *
     * @param dirPath percorso del file.
     * @throws IOException
     */
    public void saveGame(final String dirPath) throws IOException {

        File fs = new File();
        fs.saveFile(dirPath + "//TheLostHotel.dat", this);

    }

    /**
     * In base al nome del comando, ne restituisce il tipo.
     *
     * @param commandName nome del possibile comando.
     * @return tipo di comando, se presente nell'insieme dei comandi,
     * null altrimenti.
     */
    public CommandType getCommandType(final String commandName) {

        for (Command c : commands) {

            if (c.getName().equals(commandName)
                    || c.getAlias().contains(commandName)) {

                return c.getcType();

            }

        }

        return null;

    }
}
