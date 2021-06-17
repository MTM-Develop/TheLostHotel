package TheLostHotel.File;

import TheLostHotel.Other.GameDescription;
import TheLostHotel.Other.Description;
import TheLostHotel.Type.*;


import javax.swing.*;

public class TLHStart {

    private GameDescription g = new GameDescription(); // Entità in cui caricare il gioco

    public GameDescription getGame() {
        return g;
    }

    private void init() {

        //Items dell'inventario
        GameItem cellphone = new GameItem(Description.ID_CELLPHONE, "cellulare", Description.DESCRIPTION_CELLPHONE);
        cellphone.setAlias(new String[]{"telefono", "telefonino"});
        ImageIcon imgCellphone = new ImageIcon("resources//img//gameItem//cellphone.png");
        cellphone.setItemImage(imgCellphone);
        //g.getInventory().add(cellulare);

        GameItem wallet = new GameItem(Description.ID_WALLET, "portafoglio", Description.DESCRIPTION_WALLET);
        wallet.setAlias(new String[]{"portafogli", "portamonete"});
        ImageIcon imgWallet = new ImageIcon("resources//img//gameItem//wallet.png");
        wallet.setItemImage(imgWallet);
        wallet.setPickupable(true);
        g.getInventory().add(wallet);

        GameItem lighter = new GameItem(Description.ID_LIGHTER, "accendino", Description.DESCRIPTION_LIGHTER);
        lighter.setAlias(new String[]{"accenditore", "accendigas"});
        ImageIcon imgLighter = new ImageIcon("resources//img//gameItem//lighter.png");
        lighter.setItemImage(imgLighter);
        //g.getInventory().add(accendino);

        GameItem paper79 = new GameItem(Description.ID_PAPER79, "foglio", Description.DESCRIPTION_PAPER_79);
        paper79.setAlias(new String[]{"nota", "carta", "note", "appunti"});
        ImageIcon imgPaper79 = new ImageIcon("resources//img//gameItem//paper79.png");
        paper79.setItemImage(imgPaper79);

        GameItem drink = new GameItem(Description.ID_DRINK, "bibita", Description.DESCRIPTION_DRINK);
        drink.setAlias(new String[]{"drink"});
        ImageIcon imgDrink = new ImageIcon("resources//img//gameItem//drink.png");
        drink.setItemImage(imgDrink);
        //bibita.setPickupable(true);
        drink.setConsumable((byte) 1);
        g.getInventory().add(drink);

        GameItem knife = new GameItem(Description.ID_KNIFE, "coltellino", Description.DESCRIPTION_KNIFE);
        knife.setAlias(new String[]{"coltello"});
        ImageIcon imgKnife = new ImageIcon("resources//img//gameItem//knife.png");
        knife.setItemImage(imgKnife);
        //g.getInventory().add(coltellino);

        GameItem key79 = new GameItem(Description.ID_KEY79, "chiave", Description.DESCRIPTION_KEY_79);
        key79.setAlias(new String[]{"chiavi"});
        ImageIcon imgKey79 = new ImageIcon("resources//img//gameItem//key79.png");
        key79.setItemImage(imgKey79);
        key79.setConsumable((byte) 1);
        //g.getInventory().add(chiave79);

        //ItemContainer e oggetti della stanza
        GameItemContainer furniture79 = new GameItemContainer(Description.ID_FURNITURE_79, "mobile", Description.DESCRIPTION_FURNITURE_79);
        furniture79.setOpenedDescription(Description.OPENED_DESCRIPTION_FURNITURE_79);
        furniture79.setAlias(new String[]{"comodino"});
        furniture79.add(paper79);
        furniture79.add(cellphone);
        furniture79.setLockedBy("");

        GameItemContainer wardrobe79 = new GameItemContainer(Description.ID_WARDROBE_79, "armadio", Description.DESCRIPTION_WARDROBE_79);
        wardrobe79.setOpenedDescription(Description.OPENED_DESCRIPTION_WARDROBE_79);
        wardrobe79.setAlias(new String[]{"guardaroba"});
        wardrobe79.add(knife);
        wardrobe79.add(lighter);
        wardrobe79.setLockedBy(drink.getName());

        GameItemContainer paint79 = new GameItemContainer(Description.ID_PAINT_79, "quadro", Description.DESCRIPTION_PAINT_79);
        paint79.setMovedDescription(Description.MOVED_DESCRIPTION_PAINT_79);
        paint79.setAlias(new String[]{"riquadro", "figura", "pittura", "affresco"});
        paint79.add(key79);
        paint79.setMovable(true);

        GameItemContainer furniture_bathroom79 = new GameItemContainer(Description.ID_FURNITURE_BATHROOM_79, "cestino", Description.DESCRIPTION_FURNITURE_BATHROOM_79);
        furniture_bathroom79.setOpenedDescription(Description.OPENED_DESCRIPTION_FURTNITURE_BATHROOM_79);
        furniture_bathroom79.setAlias(new String[]{"portaoggetti", "portabiancheria", "cesto"});
        furniture_bathroom79.setLockedBy("");

        GameItemContainer bed79 = new GameItemContainer(Description.ID_BED_79, "letto", Description.DESCRIPTION_BED_79);
        bed79.setAlias(new String[]{"alcova", "lettiera"});
        bed79.setUseless(true);

        GameItemContainer coatHook79 = new GameItemContainer(Description.ID_COATHOOK_79, "appendiabiti", Description.DESCRIPTION_COATHOOK_79);
        coatHook79.setAlias(new String[]{"attaccapanni", "appendiabito"});
        coatHook79.setUseless(true);

        /*GameItemContainer strongbox = new GameItemContainer(80, "cassaforte", "Descrizione cassaforte");
        strongbox.setAlias(new String[]{"guardaroba", "armadio"});
        strongbox.setLockedBy("123456");*/

        //Stanze
        Room room79 = new Room(Description.ID_ROOM_79, "Stanza 79", Description.DESCRIPTION_ROOM_79);
        room79.setLookDescription(Description.LOOK_ROOM_79);
        room79.setRoomImage(new ImageIcon("resources//img//room//stanza69.png"));
        room79.setVisitedDescription(Description.DESCRIPTION_VISITED_ROOM_79);
        room79.addItem(wardrobe79);
        room79.addItem(furniture79);
        room79.addItem(paint79);
        room79.addItem(bed79);
        room79.addItem(coatHook79);

        //myRoom.addItem(accendino);
        Room bathroom79 = new Room(Description.ID_BATHROOM_79, "Bagno stanza 79", Description.DESCRIPTION_BATHROOM_79);
        bathroom79.setLookDescription(Description.LOOK_BATHROOM_79);
        bathroom79.setRoomImage(new ImageIcon("resources//img//room//bagno69.png"));
        bathroom79.setVisitedDescription(Description.DESCRIPTION_VISITED_BATHROOM_79);
        bathroom79.addItem(furniture_bathroom79);

        Room hallway = new Room(Description.ID_HALLWAY, "Corridoio", Description.DESCRIPTION_HALLWAY);
        hallway.setLookDescription(Description.LOOK_HALLWAY);
        hallway.setRoomImage(new ImageIcon("resources//img//room//corridoio.png"));
        hallway.setVisitedDescription(Description.DESCRIPTION_VISITED_HALLWAY);
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
