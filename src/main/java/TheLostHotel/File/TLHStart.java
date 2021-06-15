package TheLostHotel.File;

import TheLostHotel.GUI.GameGUI;
import TheLostHotel.Other.GameDescription;
import TheLostHotel.Other.RoomsID;
import TheLostHotel.Type.*;

import javax.swing.*;

public class TLHStart {

    private GameDescription g = new GameDescription(); // Entità in cui caricare il gioco

    public GameDescription getGame() {
        return g;
    }

    private void init() {

        //Items dell'inventario
        GameItem cellulare = new GameItem(50, "cellulare", "Il tuo ossessionato telefono. Te lo porti dappertutto!");
        cellulare.setAlias(new String[]{"telefono", "telefonino"});
        ImageIcon imgCellulare = new ImageIcon("resources//img//gameItem//phone.png");
        cellulare.setItemImage(imgCellulare);
        g.getInventory().add(cellulare);

        GameItem portafoglio = new GameItem(12, "portafoglio", "Mai partire senza portafoglio..."); //Continuare
        portafoglio.setAlias(new String[]{"portafogli", "portamonete"});
        ImageIcon imgPortafoglio = new ImageIcon("resources//img//gameItem//portafoglio.png");
        portafoglio.setItemImage(imgPortafoglio);
        portafoglio.setPickupable(true);
        g.getInventory().add(portafoglio);

        GameItem accendino = new GameItem(20, "accendino", "Il tuo fidato accendino. Non puoi farne a meno!");
        accendino.setAlias(new String[]{"accenditore", "accendigas"});
        ImageIcon imgAccendino = new ImageIcon("resources//img//gameItem//lighter.png");
        accendino.setItemImage(imgAccendino);
        accendino.setConsumable((byte) 1); /////////////
        accendino.setPickupable(true);
        //g.getInventory().add(accendino);

        GameItem foglio = new GameItem(33, "foglio", "Descrizione foglio di carta!");
        foglio.setAlias(new String[]{"nota", "carta", "note", "appunti"});
        ImageIcon imgFoglio = new ImageIcon("resources//img//gameItem//note.png");
        foglio.setItemImage(imgFoglio);
        //g.getInventory().add(foglio);

        GameItem bibita = new GameItem(23, "bibita", "La tua bibita preferita. Chissà se ti sarà utile...");
        bibita.setAlias(new String[]{"drink"});
        ImageIcon imgBibita = new ImageIcon("resources//img//gameItem//drink.png");
        bibita.setItemImage(imgBibita);
        bibita.setPickupable(true);
        g.getInventory().add(bibita);

        GameItem coltellino = new GameItem(35, "coltellino", "Il tuo coltellino preferito. Chissà se ti sarà utile...");
        coltellino.setAlias(new String[]{"coltello"});
        ImageIcon imgColtellino = new ImageIcon("resources//img//gameItem//coltellino.png");
        coltellino.setItemImage(imgColtellino);
        g.getInventory().add(coltellino);

        //ItemContainer
        GameItemContainer wardrobe = new GameItemContainer(80, "mobile", "Il mobile è chiuso a chiave. "
                + "La serratura è argentata con dei simboli verdi.");
        wardrobe.setAlias(new String[]{"guardaroba", "armadio"});
        wardrobe.add(accendino);
        //wardrobe.add(bibita);
        wardrobe.setLockedBy(coltellino.getName());

        /*GameItemContainer strongbox = new GameItemContainer(80, "cassaforte", "Descrizione cassaforte");
        strongbox.setAlias(new String[]{"guardaroba", "armadio"});
        strongbox.setLockedBy("123456");*/

        //Stanze
        Room myRoom = new Room(69, "Stanza 69", RoomsID.DESCRIPTION_ROOM_69);
        myRoom.setLookDescription("Osserva stanza");
        myRoom.setRoomImage(new ImageIcon("resources//img//room//stanza69.png"));
        myRoom.setVisitedDescription("Descrizione stanza gia visitata");
        myRoom.addItem(portafoglio);

        //myRoom.addItem(accendino);

        Room corridoio = new Room(4, "corridoio", "Descrizione corridoio");
        corridoio.setLookDescription("Osserva corridoio");
        corridoio.setRoomImage(new ImageIcon("resources//img//room//corridoio.png"));
        corridoio.setVisitedDescription("Descrizione corridoio gia visitato");
        corridoio.setLockedBy("accendino"); //////////

        Room bagnoMyRoom = new Room(79, "Bagno della tua stanza", "Descrizione bagno della tua stanza");
        bagnoMyRoom.setLookDescription("Osserva bagno della tua stanza");
        bagnoMyRoom.setRoomImage(new ImageIcon("resources//img//room//bagno69.png"));
        bagnoMyRoom.setVisitedDescription("Descrizione bagno gia visitato");
        //bagnoMyRoom.setLockedBy("cellulare"); /////////
        bagnoMyRoom.addItem(wardrobe); /////////

        g.setCurrentRoom(myRoom);
        myRoom.setNorth(corridoio);
        myRoom.setWest(bagnoMyRoom);
        bagnoMyRoom.setEast(myRoom);
        corridoio.setSouth(myRoom);

        //Comandi
        Command north = new Command("nord", CommandType.NORD);
        //north.setAlias(new String[]{"n"});
        g.getCommands().add(north);

        Command south = new Command("sud", CommandType.SOUTH);
        //south.setAlias(new String[]{"s"});
        g.getCommands().add(south);

        Command east = new Command("est", CommandType.EAST);
        //east.setAlias(new String[]{"e"});
        g.getCommands().add(east);

        Command west = new Command("ovest", CommandType.WEST);
        //west.setAlias(new String[]{"o", "w"});
        g.getCommands().add(west);

        Command look = new Command("guarda", CommandType.LOOK);
        look.setAlias(new String[]{"osserva", "vedi", "trova", "cerca", "descrivi", "controlla"});
        g.getCommands().add(look);

        Command help = new Command("help", CommandType.HELP);
        help.setAlias(new String[]{"aiuto", "guida"});
        g.getCommands().add(help);

        Command inventory = new Command("inventario", CommandType.INVENTORY);
        inventory.setAlias(new String[]{"inv", "zaino", "sacca"});
        g.getCommands().add(inventory);

        Command open = new Command("apri", CommandType.OPEN);
        g.getCommands().add(open);

        Command use = new Command("usa", CommandType.USE);
        g.getCommands().add(use);

        Command pickUp = new Command("prendi", CommandType.PICK_UP);
        pickUp.setAlias(new String[]{"raccogli"});
        g.getCommands().add(pickUp);

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
