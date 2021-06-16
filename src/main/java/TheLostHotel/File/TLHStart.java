package TheLostHotel.File;

import TheLostHotel.Other.GameDescription;
import TheLostHotel.Other.RoomsDescription;
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
        //g.getInventory().add(cellulare);

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
        accendino.setConsumable((byte) 1);
        accendino.setPickupable(true);
        //g.getInventory().add(accendino);

        GameItem foglio79 = new GameItem(33, "foglio", "Descrizione foglio di carta!");
        foglio79.setAlias(new String[]{"nota", "carta", "note", "appunti"});
        ImageIcon imgFoglio = new ImageIcon("resources//img//gameItem//note.png");
        foglio79.setItemImage(imgFoglio);

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
        //g.getInventory().add(coltellino);

        GameItem chiave79 = new GameItem(565, "chiave", "Descrizione chiave");
        chiave79.setAlias(new String[]{"chiavi"});
        ImageIcon imgChiave = new ImageIcon("resources//img//gameItem//chiave79.png");
        chiave79.setItemImage(imgChiave);
        chiave79.setConsumable((byte) 1);
        //g.getInventory().add(chiave79);

        //ItemContainer e oggetti della stanza
        GameItemContainer furniture79 = new GameItemContainer(80, "mobile", "Descrizione mobile"); //CAMBIARE NOME
        furniture79.setOpenedDescription("GIA APERTO");
        furniture79.setAlias(new String[]{"comodino"});
        furniture79.add(foglio79);
        furniture79.setLockedBy("");

        GameItemContainer wardrobe79 = new GameItemContainer(80, "armadio", "Descrizione mobile"); //CAMBIARE NOME
        wardrobe79.setOpenedDescription("GIA APERTO");
        wardrobe79.setAlias(new String[]{"guardaroba"});
        wardrobe79.add(coltellino);
        wardrobe79.setLockedBy(bibita.getName());

        GameItemContainer paint79 = new GameItemContainer(80, "quadro", "Descrizione mobile"); //CAMBIARE NOME
        paint79.setMovedDescription("Gia spostato");
        paint79.setAlias(new String[]{"riquadro", "figura", "pittura", "affresco"});
        paint79.add(chiave79);
        paint79.setMovable(true);

        GameItemContainer furniture_bathroom79 = new GameItemContainer(80, "cestino", "Descrizione mobile"); //CAMBIARE NOME
        furniture_bathroom79.setOpenedDescription("GIA APERTO");
        furniture_bathroom79.setAlias(new String[]{"mobile", "portaoggetti", "portabiancheria"});
        furniture_bathroom79.setLockedBy("");

        /*GameItemContainer strongbox = new GameItemContainer(80, "cassaforte", "Descrizione cassaforte");
        strongbox.setAlias(new String[]{"guardaroba", "armadio"});
        strongbox.setLockedBy("123456");*/

        //Stanze
        Room room79 = new Room(79, "Stanza 79", RoomsDescription.DESCRIPTION_ROOM_79);
        room79.setLookDescription(RoomsDescription.LOOK_ROOM_79);
        room79.setRoomImage(new ImageIcon("resources//img//room//stanza69.png"));
        room79.setVisitedDescription(RoomsDescription.DESCRIPTION_VISITED_ROOM_79);
        room79.addItem(wardrobe79);
        room79.addItem(furniture79);
        room79.addItem(paint79);

        //myRoom.addItem(accendino);
        Room bathroom79 = new Room(3, "Bagno stanza 79", RoomsDescription.DESCRIPTION_BATHROOM_79);
        bathroom79.setLookDescription(RoomsDescription.LOOK_BATHROOM_79);
        bathroom79.setRoomImage(new ImageIcon("resources//img//room//bagno69.png"));
        bathroom79.setVisitedDescription(RoomsDescription.DESCRIPTION_VISITED_BATHROOM_79);
        bathroom79.addItem(furniture_bathroom79);

        Room hallway = new Room(4, "Corridoio", RoomsDescription.DESCRIPTION_HALLWAY);
        hallway.setLookDescription(RoomsDescription.LOOK_HALLWAY);
        hallway.setRoomImage(new ImageIcon("resources//img//room//corridoio.png"));
        hallway.setVisitedDescription(RoomsDescription.DESCRIPTION_VISITED_HALLWAY);
        hallway.setLockedBy("chiave");

        g.setCurrentRoom(room79);
        room79.setNorth(hallway);
        room79.setWest(bathroom79);
        bathroom79.setEast(room79);
        hallway.setSouth(room79);

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

        Command move = new Command("sposta", CommandType.MOVE);
        move.setAlias(new String[]{"muovi", "trascina"});
        g.getCommands().add(move);

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
