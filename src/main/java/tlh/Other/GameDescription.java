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

    private Set<Command> commands; // Comandi del gioco
    private Inventory inventory; // Inventario del protagonista
    private Room currentRoom; // stanza in cui si trova il giocatore

    //private String player = ""; // nome del giocatore
    //private GameTimeTask gTime = new GameTimeTask(); // tempo di completamento

    // Costruttori

    public GameDescription() {
        this.commands = new HashSet<>();
        this.inventory = new Inventory();
    }

    public GameDescription(Set<Command> commands, Inventory inventory, Room currentRoom) {
        this.commands = commands;
        this.inventory = inventory;
        this.currentRoom = currentRoom;
    }

    // Metodi di get e set

    public Set<Command> getCommands() {
        return commands;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setCommands(Set<Command> commands) {
        this.commands = commands;
    }

    public void initGame(String filePath) throws IOException, ClassNotFoundException {

        File fs = new File();
        fs.readFile(filePath, this);

    }

    public void saveGame(String dirPath) throws IOException {

        File fs = new File();
        fs.saveFile(dirPath + "//TheLostHotel.dat", this);

    }

    /*public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }*/

    /**
     * In base al nome del comando, ne restituisce il tipo
     * @param commandName nome del possibile comando
     * @return tipo di comando, se presente nell'insieme dei comandi, null altrimenti
     */
    public CommandType getCommandType(String commandName) {

        for (Command c : commands ) {

            if (c.getName().equals(commandName) || c.getAlias().contains(commandName)) {

                return c.getcType();

            }

        }

        return null;

    }
}
