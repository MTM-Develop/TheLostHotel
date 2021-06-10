package TheLostHotel.File;

import TheLostHotel.Other.GameDescription;
import TheLostHotel.Type.Command;
import TheLostHotel.Type.CommandType;
import TheLostHotel.Type.GameItem;
import TheLostHotel.Type.Room;

import javax.swing.*;

public class TLHStart {

    private GameDescription g = new GameDescription(); // Entità in cui caricare il gioco

    public GameDescription getGame() {
        return g;
    }

    private void init() {

        //Items dell'inventario
        GameItem taralli = new GameItem(50, "taralli", "Taralli in busta... Gli ottimi taralli del forno (che ti compra tua madre), ti viene l'acquolina in bocca solo a pensarci!");
        taralli.setAlias(new String[]{"tarallini"});
        ImageIcon t = new ImageIcon("resources//img//door_13.jpg");
        taralli.setItemImage(t);
        g.getInventory().add(taralli);

        //Stanze
        Room myRoom = new Room(42, "La tua stanza", "Descrizione stanza", "Osserva stanza", false);
        myRoom.setRoomImage(new ImageIcon("resources//img//immagine_def.png"));

        //myRoom.addItem(taralli);

        g.setCurrentRoom(myRoom);

        Room corridoio = new Room(4, "Corridoio", "Descrizione corridoio", "Osserva corridoio", false);
        corridoio.setRoomImage(new ImageIcon("resources//img//corridoio.png"));

        myRoom.setNorth(corridoio);
        corridoio.setSouth(myRoom);

        //Comandi
        Command north = new Command("nord", CommandType.NORD);
        g.getCommands().add(north);
        Command south = new Command("sud", CommandType.SOUTH);
        g.getCommands().add(south);
        Command east = new Command("est", CommandType.EAST);
        g.getCommands().add(east);
        Command west = new Command("ovest", CommandType.WEST);
        g.getCommands().add(west);
        Command look = new Command("guarda", CommandType.LOOK);
        look.setAlias(new String[]{"osserva", "vedi", "trova", "cerca", "descrivi", "controlla"});
        g.getCommands().add(look);
        Command help = new Command("help", CommandType.HELP);
        help.setAlias(new String[]{"aiuto", "guida"});
        g.getCommands().add(help);
        Command inventory = new Command("inventario", CommandType.INVENTORY);
        inventory.setAlias(new String[]{"zaino"});
        g.getCommands().add(inventory);
    }

    /**
     * Rende eseguibile la classe, in modo da ricreare i file di gioco.
     *
     * @param args
     */
    public static void main(String[] args) {

        File fs = new File();
        TLHStart tlhStart = new TLHStart();

        // Inizializza il gioco
        tlhStart.init();

        try {

            // Scrive il file di "nuova partita" con il gioco creato
            fs.saveFile("NewGame//NewGame.dat", tlhStart.getGame());

        } catch (Exception e) {

            System.out.println("Eccezione: " + e.getMessage());

        }
    }
}
