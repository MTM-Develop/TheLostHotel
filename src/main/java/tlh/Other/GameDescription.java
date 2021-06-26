package tlh.Other;

import tlh.File.File;
import tlh.Type.Command;
import tlh.Type.CommandType;
import tlh.Type.Inventory;
import tlh.Type.Room;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

    //private GameTimeTask gTime = new GameTimeTask(); // tempo di completamento

    /**
     * Costruttore.
     */
    public GameDescription() {
        this.commands = new HashSet<>();
        this.inventory = new Inventory();
    }

    /**
     * Costruttore.
     *
     * @param gCommands
     * @param gInventory
     * @param gCurrentRoom
     */
    public GameDescription(final Set<Command> gCommands,
                           final Inventory gInventory,
                           final Room gCurrentRoom) {
        this.commands = gCommands;
        this.inventory = gInventory;
        this.currentRoom = gCurrentRoom;
    }

    // Metodi di get e set

    /**
     * @return inventario del giocatore.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Imposta l'inventario del giocatore.
     *
     * @param inv
     */
    public void setInventory(final Inventory inv) {
        this.inventory = inv;
    }

    /**
     * @return stanza corrente del giocatore.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Imposta la stanza corrente del giocatore.
     *
     * @param currRoom
     */
    public void setCurrentRoom(final Room currRoom) {
        this.currentRoom = currRoom;
    }

    /**
     * @return comandi del gioco.
     */
    public Set<Command> getCommands() {
        return commands;
    }

    /**
     * Imposta i comandi del gioco.
     *
     * @param gCommands
     */
    public void setCommands(final Set<Command> gCommands) {
        this.commands = gCommands;
    }

    /**
     * Avvia una nuova partita da file.
     *
     * @param filePath
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
     * @param dirPath
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
