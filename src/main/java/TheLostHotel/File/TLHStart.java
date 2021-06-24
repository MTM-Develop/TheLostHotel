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

        GameItem paperHallwayColumbus = new GameItem(Description.ID_PAPER_HALLWAY_COLUMBUS, "foglio", Description.DESCRIPTION_PAPER_HALLWAY_COLUMBUS);
        paperHallwayColumbus.setAlias(new String[]{"nota", "carta", "note", "appunti"});
        ImageIcon imgPaperHallwayColumbus = new ImageIcon("resources//img//gameItem//paperHallwayColumbus.png");
        paperHallwayColumbus.setItemImage(imgPaperHallwayColumbus);

        GameItem drink = new GameItem(Description.ID_DRINK, "bibita", Description.DESCRIPTION_DRINK);
        drink.setAlias(new String[]{"drink"});
        ImageIcon imgDrink = new ImageIcon("resources//img//gameItem//drink.png");
        drink.setItemImage(imgDrink);
        drink.setConsumable((byte) 1);
        //g.getInventory().add(drink);

        GameItem knife = new GameItem(Description.ID_KNIFE, "coltellino", Description.DESCRIPTION_KNIFE);
        knife.setAlias(new String[]{"coltello"});
        ImageIcon imgKnife = new ImageIcon("resources//img//gameItem//knife.png");
        knife.setItemImage(imgKnife);
        g.getInventory().add(knife);

        GameItem key79 = new GameItem(Description.ID_KEY79, "chiave", Description.DESCRIPTION_KEY_79);
        key79.setAlias(new String[]{"chiavi"});
        ImageIcon imgKey79 = new ImageIcon("resources//img//gameItem//key79.png");
        key79.setItemImage(imgKey79);
        key79.setConsumable((byte) 1);
        //g.getInventory().add(chiave79);

        GameItem handle = new GameItem(Description.ID_HANDLE, "maniglia", Description.DESCRIPTION_HANDLE);
        ImageIcon imgHandle = new ImageIcon("resources//img//gameItem//handle.png");
        handle.setItemImage(imgHandle);

        GameItem cardGameRoom = new GameItem(Description.ID_CARD_GAMEROOM, "tessera", Description.DESCRIPTION_CARD_GAMEROOM);
        cardGameRoom.setAlias(new String[]{"card", "carta"});
        ImageIcon imgCardGameRoom = new ImageIcon("resources//img//gameItem//card.png");
        cardGameRoom.setItemImage(imgCardGameRoom);

        GameItem batteries = new GameItem(Description.ID_BATTERY, "batterie", Description.DESCRIPTION_BATTERIES);
        batteries.setAlias(new String[]{"pila", "batteria", "pile"});
        ImageIcon imgBatteries = new ImageIcon("resources//img//gameItem//batteries.png");
        batteries.setItemImage(imgBatteries);

        GameItem map = new GameItem(Description.ID_MAP, "mappa", Description.DESCRIPTION_MAP);
        map.setAlias(new String[]{"cartina", "planimetria", "icnografia"});
        ImageIcon imgMap = new ImageIcon("resources//img//gameItem//map.png");
        map.setItemImage(imgMap);
        g.getInventory().add(map);


        GameItemContainer button = new GameItemContainer(45324533, "bottone", Description.DESCRIPTION_KNIFE); //CAMBIARE
        button.setAlias(new String[]{"pulsante"}); //CAMBIARE
        button.setPushable(true);
        button.setUseless(true);

        //ItemContainer e oggetti della stanza
        GameItemContainer furniture79 = new GameItemContainer(Description.ID_FURNITURE_79, "mobile", Description.DESCRIPTION_FURNITURE_79);
        furniture79.setOpenedDescription(Description.OPENED_DESCRIPTION_FURNITURE_79);
        furniture79.setAlias(new String[]{"comodino"});
        furniture79.add(cellphone);
        furniture79.setLockedBy("");

        GameItemContainer wardrobe79 = new GameItemContainer(Description.ID_WARDROBE_79, "armadio", Description.DESCRIPTION_WARDROBE_79);
        wardrobe79.setOpenedDescription(Description.OPENED_DESCRIPTION_WARDROBE_79);
        wardrobe79.setAlias(new String[]{"guardaroba"});
        //wardrobe79.add(knife);
        wardrobe79.add(lighter);
        wardrobe79.setLockedBy(wallet.getName());

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

        GameItemContainer slot = new GameItemContainer(Description.ID_SLOT, "slot", Description.DESCRIPTION_SLOT);
        slot.setAlias(new String[]{"macchinetta"});
        slot.setDescriptionUsableWithDrops(Description.DESCRIPTION_SLOT_USABLE_WITH_DROPS);
        slot.setDescriptionAlreadyUsedWithDrops(Description.DESCRIPTION_SLOT_ALREADY_USED_WITH_DROPS);
        slot.setUsableWithDrops(true);
        slot.add(handle);

        GameItemContainer changeMachine = new GameItemContainer(Description.ID_CHANGE_MACHINE, "cambiamonete", Description.DESCRIPTION_CHANGE_MACHINE);
        changeMachine.setAlias(new String[]{"cambiovaluta"});
        changeMachine.setDescriptionUsableWithDrops(Description.DESCRIPTION_CHANGE_MACHINE_USABLE_WITH_DROPS);
        changeMachine.setDescriptionAlreadyUsedWithDrops(Description.DESCRIPTION_CHANGE_MACHINE_ALREADY_USED_WITH_DROPS);
        changeMachine.setUsableWithDrops(true);
        changeMachine.add(cardGameRoom);

        GameItemContainer radio = new GameItemContainer(Description.ID_RADIO, "radio", Description.DESCRIPTION_RADIO);
        radio.setAlias(new String[]{"emittente", "radiostazione", "radiofonia", "radiocomunicazione", "radiodiffusione"});
        radio.setUsableWithItem(true);
        radio.setDescriptionUsableWithDrops(Description.DESCRIPTION_RADIO_USABLE_WITH_DROPS);
        radio.setDescriptionAlreadyUsedWithDrops(Description.DESCRIPTION_RADIO_ALREADY_USED_WITH_DROPS);
        radio.setDescriptionUsableButItemRemoved(Description.DESCRIPTION_RADIO_USABLE_BUT_ITEM_REMOVED);
        radio.setLockedBy(knife.getName());

        GameItemContainer remoteControl = new GameItemContainer(Description.ID_REMOTE_CONTROL, "telecomando", Description.DESCRIPTION_REMOTE_CONTROL);
        remoteControl.setOpenedDescription(Description.OPENED_DESCRIPTION_REMOTE_CONTROL);
        remoteControl.setAlias(new String[]{"apparecchio"});
        remoteControl.setDescriptionUsableWithDrops(Description.DESCRIPTION_REMOTE_CONTROL_USABLE_WITH_DROPS);
        remoteControl.setDescriptionAlreadyUsedWithDrops(Description.DESCRIPTION_REMOTE_CONTROL_ALREADY_USED_WITH_DROPS);
        remoteControl.setDescriptionUsableButItemRemoved(Description.DESCRIPTION_REMOTE_CONTROL_USABLE_BUT_ITEM_REMOVED);
        remoteControl.add(batteries);
        remoteControl.setLockedBy("");
        remoteControl.setUsableWithItem(true);

        GameItemContainer tv = new GameItemContainer(Description.ID_TV, "televisione", Description.DESCRIPTION_TV);
        tv.setAlias(new String[]{"tv", "televisore"});

        GameItemContainer sofa = new GameItemContainer(Description.ID_SOFA, "divano", Description.DESCRIPTION_SOFA);
        sofa.setAlias(new String[]{"poltrona", "sofa"});

        GameItemContainer plantRelaxRoom = new GameItemContainer(Description.ID_PLANT_RELAX_ROOM, "pianta", Description.DESCRIPTION_PLANT);
        plantRelaxRoom.setAlias(new String[]{"piantina"});
        //plant.setUseless(true);

        GameItemContainer fan = new GameItemContainer(Description.ID_FAN, "ventilatore", Description.DESCRIPTION_FAN);
        fan.setAlias(new String[]{"ventola"});

        /*GameItemContainer box = new GameItemContainer(Description.ID_BOX, "scatola", Description.DESCRIPTION_BOX);
        box.setOpenedDescription(Description.OPENED_DESCRIPTION_BOX);
        box.setAlias(new String[]{"scatolone", "cartone", "box", "contenitore"});
        box.setLockedBy(knife.getName());
        box.add(drink);*/

        GameItemContainer plantHallwayColumbus = new GameItemContainer(Description.ID_PLANT_HALLWAY_COLUMBUS, "pianta", Description.DESCRIPTION_PLANT);
        plantHallwayColumbus.setMovedDescription(Description.MOVED_DESCRIPTION_PLANT_HALLWAY_COLUMBUS);
        plantHallwayColumbus.setAlias(new String[]{"piantina"});
        plantHallwayColumbus.setMovable(true);
        plantHallwayColumbus.add(paperHallwayColumbus);


        //GameItem password = new GameItem(Description.ID_PASSWORD, "1"); //Occhio alla descrizione

        /*GameItemContainer strongbox = new GameItemContainer(80, "cassaforte", "Descrizione cassaforte");
        strongbox.setLockedBy(password.getName());//Description.PASS_STRONGBOX);
        strongbox.setPassword_locked(true);
        strongbox.setPasswordUnlockedDescription("Già sbloccato");
        strongbox.add(key79);*/

        //Stanze
        Room room79 = new Room(Description.ID_ROOM_79, "Stanza 79", Description.DESCRIPTION_ROOM_79);
        room79.setLookDescription(Description.LOOK_ROOM_79);
        room79.setRoomImage(new ImageIcon("resources//img//room//room79.png"));
        room79.setVisitedDescription(Description.DESCRIPTION_VISITED_ROOM_79);
        room79.addItem(wardrobe79);
        room79.addItem(furniture79);
        room79.addItem(paint79);
        room79.addItem(bed79);
        room79.addItem(coatHook79);
        room79.addItem(button);
        //room79.addItem(strongbox);

        Room bathroom79 = new Room(Description.ID_BATHROOM_79, "Bagno stanza 79", Description.DESCRIPTION_BATHROOM_79);
        bathroom79.setLookDescription(Description.LOOK_BATHROOM_79);
        bathroom79.setRoomImage(new ImageIcon("resources//img//room//bathroom79.png"));
        bathroom79.setVisitedDescription(Description.DESCRIPTION_VISITED_BATHROOM_79);
        bathroom79.addItem(furniture_bathroom79);
        bathroom79.addItem(batteries);

        Room hallway = new Room(Description.ID_HALLWAY, "Corridoio", Description.DESCRIPTION_HALLWAY);
        hallway.setLookDescription(Description.LOOK_HALLWAY);
        hallway.setRoomImage(new ImageIcon("resources//img//room//hallway.png"));
        hallway.setVisitedDescription(Description.DESCRIPTION_VISITED_HALLWAY);
        hallway.setLockedBy(""); //chiave

        Room gameRoom = new Room(Description.ID_GAMEROOM, "Sala giochi", Description.DESCRIPTION_GAMEROOM);
        gameRoom.setLookDescription(Description.LOOK_GAMEROOM);
        gameRoom.setRoomImage(new ImageIcon("resources//img//room//gameRoom.png"));
        gameRoom.setVisitedDescription(Description.DESCRIPTION_VISITED_GAMEROOM);
        gameRoom.setLockedBy("");
        gameRoom.addItem(slot);
        gameRoom.addItem(changeMachine);

        Room kitchen = new Room(Description.ID_KITCHEN, "Cucina", Description.DESCRIPTION_KITCHEN);
        kitchen.setLookDescription(Description.LOOK_KITCHEN);
        kitchen.setRoomImage(new ImageIcon("resources//img//room//kitchen.png"));
        kitchen.setVisitedDescription(Description.DESCRIPTION_VISITED_KITCHEN);
        kitchen.setLockedBy(cardGameRoom.getName());
        kitchen.addItem(radio);

        Room relaxRoom = new Room(Description.ID_RELAX_ROOM, "Stanza relax", Description.DESCRIPTION_RELAX_ROOM);
        relaxRoom.setLookDescription(Description.LOOK_RELAX_ROOM);
        relaxRoom.setRoomImage(new ImageIcon("resources//img//room//relaxRoom.png"));
        relaxRoom.setVisitedDescription(Description.DESCRIPTION_VISITED_RELAX_ROOM);
        gameRoom.setLockedBy("");
        relaxRoom.addItem(remoteControl);
        relaxRoom.addItem(tv);
        relaxRoom.addItem(sofa);
        relaxRoom.addItem(plantRelaxRoom);
        relaxRoom.addItem(fan);

        Room hallwayColumbus = new Room(Description.ID_HALLWAY_COLUMBUS, "Corridoio Columbus", Description.DESCRIPTION_HALLWAY_COLUMBUS);
        hallwayColumbus.setLookDescription(Description.LOOK_HALLWAY_COLUMBUS);
        hallwayColumbus.setRoomImage(new ImageIcon("resources//img//room//hallwayColumbus.png"));
        hallwayColumbus.setVisitedDescription(Description.DESCRIPTION_VISITED_HALLWAY_COLUMBUS);
        hallwayColumbus.setLockedBy(""); //chiave
        hallwayColumbus.addItem(plantHallwayColumbus);
        //hallwayOvest.addItem(box);

        Room bar = new Room(Description.ID_BAR, "Bar Columbus", Description.DESCRIPTION_BAR);
        bar.setLookDescription(Description.LOOK_BAR);
        bar.setRoomImage(new ImageIcon("resources//img//room//bar.png"));
        bar.setVisitedDescription(Description.DESCRIPTION_VISITED_BAR);
        bar.setLockedBy(""); //chiave

        g.setCurrentRoom(room79);
        room79.setNorth(hallway);
        room79.setWest(bathroom79);
        bathroom79.setEast(room79);
        hallway.setSouth(room79);
        hallway.setEast(gameRoom);
        hallway.setWest(hallwayColumbus);
        hallway.setNorth(relaxRoom);
        gameRoom.setWest(hallway);
        hallwayColumbus.setEast(hallway);
        hallwayColumbus.setWest(bar);
        relaxRoom.setSouth(hallway);
        bar.setEast(hallwayColumbus);

        //Comandi
        Command north = new Command("nord", CommandType.NORD);
        g.getCommands().add(north);

        Command south = new Command("sud", CommandType.SUD);
        g.getCommands().add(south);

        Command east = new Command("est", CommandType.EST);
        g.getCommands().add(east);

        Command west = new Command("ovest", CommandType.OVEST);
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

        Command drop = new Command("lascia", CommandType.DROP);
        drop.setAlias(new String[]{"molla", "abbandona"});
        g.getCommands().add(drop);

        Command push = new Command("premi", CommandType.PUSH);
        push.setAlias(new String[]{"spingi"});
        g.getCommands().add(push);

        /*Command quit = new Command("abbandona", CommandType.QUIT);
        quit.setAlias(new String[]{"arrenditi", "termina", "ammazzati", "suicidati", "resa"});
        g.getCommands().add(quit);*/

        Command insert = new Command("inserisci", CommandType.INSERT);
        g.getCommands().add(insert);

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
