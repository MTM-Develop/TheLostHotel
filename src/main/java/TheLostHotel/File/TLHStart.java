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
        GameItem cellulare = new GameItem(50, "Cellulare", "Descrizione cellulare!");
        cellulare.setAlias(new String[]{"telefono", "telefonino"});
        ImageIcon imgCellulare = new ImageIcon("resources//img//phone.png");
        cellulare.setItemImage(imgCellulare);
        g.getInventory().add(cellulare);

        GameItem portafoglio = new GameItem(12, "Portafoglio", "Descrizione portafoglio!");
        portafoglio.setAlias(new String[]{"portafogli", "portamonete"});
        ImageIcon imgPortafoglio = new ImageIcon("resources//img//portafoglio.png");
        portafoglio.setItemImage(imgPortafoglio);
        g.getInventory().add(portafoglio);

        GameItem accendino = new GameItem(20, "Accendino", "Descrizione accendino!");
        accendino.setAlias(new String[]{"accenditore", "accendigas"});
        ImageIcon imgAccendino = new ImageIcon("resources//img//lighter.png");
        accendino.setItemImage(imgAccendino);
        g.getInventory().add(accendino);

        GameItem foglio = new GameItem(33, "Foglio", "Descrizione foglio di carta!");
        foglio.setAlias(new String[]{"nota", "carta", "note", "appunti"});
        ImageIcon imgFoglio = new ImageIcon("resources//img//note.png");
        foglio.setItemImage(imgFoglio);
        g.getInventory().add(foglio);

        GameItem bibita = new GameItem(23, "Bibita", "Descrizione bibita!");
        bibita.setAlias(new String[]{"fanta", "drink"});
        ImageIcon imgBibita = new ImageIcon("resources//img//drink.png");
        bibita.setItemImage(imgBibita);
        g.getInventory().add(bibita);

        //Stanze
        Room myRoom = new Room(42, "La tua stanza", "Descrizione stanza", "Osserva stanza", false);
        myRoom.setRoomImage(new ImageIcon("resources//img//immagine_def.png"));
        myRoom.setVisitedDescription("Descrizione stanza gia visitata");

        //myRoom.addItem(taralli);

        Room corridoio = new Room(4, "Corridoio", "Descrizione corridoio", "Osserva corridoio", false);
        corridoio.setRoomImage(new ImageIcon("resources//img//corridoio.png"));
        corridoio.setVisitedDescription("Descrizione corridoio gia visitato");

        Room bagnoMyRoom = new Room(79, "Bagno della tua stanza", "Descrizione bagnoo della tua stanza", "Osserva bagno della tua stanza", false);
        bagnoMyRoom.setRoomImage(new ImageIcon("resources//img//background2.jpg"));
        bagnoMyRoom.setVisitedDescription("Descrizione bagno gia visitato");

        g.setCurrentRoom(myRoom);
        myRoom.setNorth(corridoio);
        myRoom.setWest(bagnoMyRoom);
        bagnoMyRoom.setEast(myRoom);
        corridoio.setSouth(myRoom);

        //Comandi
        Command north = new Command("nord", CommandType.NORD);
        north.setAlias(new String[]{"n"});
        g.getCommands().add(north);

        Command south = new Command("sud", CommandType.SOUTH);
        south.setAlias(new String[]{"s"});
        g.getCommands().add(south);

        Command east = new Command("est", CommandType.EAST);
        east.setAlias(new String[]{"e"});
        g.getCommands().add(east);

        Command west = new Command("ovest", CommandType.WEST);
        west.setAlias(new String[]{"o", "w"});
        g.getCommands().add(west);

        Command look = new Command("guarda", CommandType.LOOK);
        look.setAlias(new String[]{"osserva", "vedi", "trova", "cerca", "descrivi", "controlla"});
        g.getCommands().add(look);

        Command help = new Command("help", CommandType.HELP);
        help.setAlias(new String[]{"aiuto", "guida", "?"});
        g.getCommands().add(help);

        Command inventory = new Command("inventario", CommandType.INVENTORY);
        inventory.setAlias(new String[]{"zaino", "sacca"});
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
