package tlh.File;

import tlh.Other.GameDescription;
import tlh.Other.Description;
import tlh.Type.GameItem;
import tlh.Type.GameItemContainer;
import tlh.Type.Room;
import tlh.Type.Command;
import tlh.Type.CommandType;

import javax.swing.ImageIcon;

public class TLHStart {

    /**
     * Entità in cui caricare il gioco.
     */
    private GameDescription g = new GameDescription();

    /**
     * @return entità in cui è caricato il gioco.
     */
    public GameDescription getGame() {
        return g;
    }

    private void init() {

        //Items dell'inventario
        GameItem cellphone = new GameItem(Description.ID_CELLPHONE, "cellulare",
                Description.DESCRIPTION_CELLPHONE);
        cellphone.setAlias(new String[]{"telefonino"});
        ImageIcon imgCellphone = new ImageIcon(
                "resources//img//gameItem//cellphone.png");
        cellphone.setItemImage(imgCellphone);
        //g.getInventory().add(cellphone);

        GameItem wallet = new GameItem(Description.ID_WALLET, "portafoglio",
                Description.DESCRIPTION_WALLET);
        wallet.setAlias(new String[]{"portafogli", "portamonete"});
        ImageIcon imgWallet = new ImageIcon(
                "resources//img//gameItem//wallet.png");
        wallet.setItemImage(imgWallet);
        wallet.setPickupable(true);
        wallet.setConsumable((byte) 1);
        g.getInventory().add(wallet);

        GameItem lighter = new GameItem(Description.ID_LIGHTER, "accendino",
                Description.DESCRIPTION_LIGHTER);
        lighter.setAlias(new String[]{"accenditore", "accendigas"});
        ImageIcon imgLighter = new ImageIcon(
                "resources//img//gameItem//lighter.png");
        lighter.setItemImage(imgLighter);
        //g.getInventory().add(accendino);

        GameItem paperHallwayColumbus = new GameItem(
                Description.ID_PAPER_HALLWAY_COLUMBUS, "foglio",
                Description.DESCRIPTION_PAPER_HALLWAY_COLUMBUS);
        paperHallwayColumbus.setAlias(new String[]{"nota", "carta",
                "note", "appunti"});
        ImageIcon imgPaperHallwayColumbus = new ImageIcon(
                "resources//img//gameItem//paperHallwayColumbus.png");
        paperHallwayColumbus.setItemImage(imgPaperHallwayColumbus);

        GameItem drink = new GameItem(Description.ID_DRINK, "bibita",
                Description.DESCRIPTION_DRINK);
        drink.setAlias(new String[]{"drink", "bevanda"});
        ImageIcon imgDrink = new ImageIcon(
                "resources//img//gameItem//drink.png");
        drink.setItemImage(imgDrink);
        drink.setConsumable((byte) 1);
        //g.getInventory().add(drink);

        GameItem knife = new GameItem(Description.ID_KNIFE, "coltellino",
                Description.DESCRIPTION_KNIFE);
        knife.setAlias(new String[]{"coltello"});
        ImageIcon imgKnife = new ImageIcon(
                "resources//img//gameItem//knife.png");
        knife.setItemImage(imgKnife);
        g.getInventory().add(knife);

        GameItem key79 = new GameItem(Description.ID_KEY79, "chiave79",
                Description.DESCRIPTION_KEY_79);
        key79.setAlias(new String[]{"chiavi79"});
        ImageIcon imgKey79 = new ImageIcon(
                "resources//img//gameItem//key79.png");
        key79.setItemImage(imgKey79);
        key79.setConsumable((byte) 1);

        GameItem handle = new GameItem(Description.ID_HANDLE, "maniglia",
                Description.DESCRIPTION_HANDLE);
        ImageIcon imgHandle = new ImageIcon(
                "resources//img//gameItem//handle.png");
        handle.setItemImage(imgHandle);
        handle.setConsumable((byte) 1);

        GameItem cardGameRoom = new GameItem(Description.ID_CARD_GAMEROOM,
                "tessera", Description.DESCRIPTION_CARD_GAMEROOM);
        cardGameRoom.setAlias(new String[]{"card", "carta"});
        ImageIcon imgCardGameRoom = new ImageIcon(
                "resources//img//gameItem//card.png");
        cardGameRoom.setItemImage(imgCardGameRoom);

        GameItem batteries = new GameItem(Description.ID_BATTERIES, "batterie",
                Description.DESCRIPTION_BATTERIES);
        batteries.setAlias(new String[]{"pila", "batteria", "pile"});
        batteries.setConsumable((byte) 1);
        ImageIcon imgBatteries = new ImageIcon(
                "resources//img//gameItem//batteries.png");
        batteries.setItemImage(imgBatteries);

        GameItem map = new GameItem(Description.ID_MAP, "mappa",
                Description.DESCRIPTION_MAP);
        map.setAlias(new String[]{"cartina", "planimetria", "icnografia"});
        ImageIcon imgMap = new ImageIcon("resources//img//gameItem//map.png");
        map.setItemImage(imgMap);
        g.getInventory().add(map);

        /*GameItem key33 = new GameItem(Description.ID_KEY33, "chiave33",
                Description.DESCRIPTION_KEY_33);
        key33.setAlias(new String[]{"chiavi33"});
        ImageIcon imgKey33 = new ImageIcon(
                "resources//img//gameItem//key33.png");
        key33.setItemImage(imgKey33);
        key33.setConsumable((byte) 1); //cambiare*/

        GameItem token = new GameItem(Description.ID_TOKEN, "gettone",
                Description.DESCRIPTION_TOKEN);
        token.setAlias(new String[]{"token", "moneta"});
        ImageIcon imgToken = new ImageIcon(
                "resources//img//gameItem//token.png");
        token.setItemImage(imgToken);
        token.setConsumable((byte) 1); //da usare

        GameItem keyBar = new GameItem(Description.ID_KEY_BAR, "chiave47",
                Description.DESCRIPTION_KEY_BAR);
        keyBar.setAlias(new String[]{"chiavi47"});
        ImageIcon imgKey47 = new ImageIcon(
                "resources//img//gameItem//key47.png");
        keyBar.setItemImage(imgKey47);
        keyBar.setConsumable((byte) 1);

        GameItem usb = new GameItem(Description.ID_USB, "usb",
                Description.DESCRIPTION_USB);
        usb.setAlias(new String[]{"chiavetta", "pendrive", "penna"});
        usb.setPickupable(true);
        ImageIcon imgUsb = new ImageIcon(
                "resources//img//gameItem//usb.png");
        usb.setItemImage(imgUsb);
        usb.setConsumable((byte) 1);

        GameItem hook = new GameItem(Description.ID_HOOK, "gancio",
                Description.DESCRIPTION_HOOK);
        hook.setAlias(new String[]{"punta", "uncino", "gancetto"});
        ImageIcon imgHook = new ImageIcon(
                "resources//img//gameItem//hook.png");
        hook.setItemImage(imgHook);
        hook.setConsumable((byte) 1);

        GameItem pass = new GameItem(
                Description.ID_PASSWORD_STRONGBOX_CCTV,
                Description.PASSWORD_STRONGBOX_CCTV, "");
        pass.setGIPassword(true);
        g.getInventory().add(pass);

        GameItem passHall = new GameItem(
                Description.ID_PASSWORD_STRONGBOX_HALL,
                Description.PASSWORD_STRONGBOX_HALL, "");
        passHall.setGIPassword(true);
        g.getInventory().add(passHall);

        GameItem paperPass1 = new GameItem(
                Description.ID_PAPER_PASS1_CCTV, "foglio03",
                Description.DESCRIPTION_PAPER_PASS_CCTV);
        paperPass1.setAlias(new String[]{"nota03", "carta03",
                "note03", "appunti03"});
        ImageIcon imgPaperPass1 = new ImageIcon(
                "resources//img//gameItem//paperPass1CCTV.png");
        paperPass1.setItemImage(imgPaperPass1);

        GameItem paperPass2 = new GameItem(
                Description.ID_PAPER_PASS2_CCTV, "foglio08",
                Description.DESCRIPTION_PAPER_PASS_CCTV);
        paperPass2.setAlias(new String[]{"nota08", "carta08",
                "note08", "appunti08"});
        ImageIcon imgPaperPass2 = new ImageIcon(
                "resources//img//gameItem//paperPass2CCTV.png");
        paperPass2.setItemImage(imgPaperPass2);

        GameItem paperPass3 = new GameItem(
                Description.ID_PAPER_PASS3_CCTV, "foglio17",
                Description.DESCRIPTION_PAPER_PASS_CCTV);
        paperPass3.setAlias(new String[]{"nota17", "carta17",
                "note17", "appunti17"});
        ImageIcon imgPaperPass3 = new ImageIcon(
                "resources//img//gameItem//paperPass3CCTV.png");
        paperPass3.setItemImage(imgPaperPass3);

        GameItem paperPass4 = new GameItem(
                Description.ID_PAPER_PASS4_CCTV, "foglio21",
                Description.DESCRIPTION_PAPER_PASS_CCTV);
        paperPass4.setAlias(new String[]{"nota21", "carta21",
                "note21", "appunti21"});
        ImageIcon imgPaperPass4 = new ImageIcon(
                "resources//img//gameItem//paperPass4CCTV.png");
        paperPass4.setItemImage(imgPaperPass4);

        GameItem paperRoom63 = new GameItem(
                Description.ID_PAPER_63, "foglio63",
                Description.DESCRIPTION_PAPER_63); //CAMBIARE
        paperRoom63.setAlias(new String[]{"nota63", "carta63",
                "note63", "appunti63"});
        ImageIcon imgPaperRoom63 = new ImageIcon(
                "resources//img//gameItem//paperHallwayColumbus.png");
        //CAMBIARE IMMAGINE NEL CASO
        paperRoom63.setItemImage(imgPaperRoom63);

        GameItem rifle = new GameItemContainer(
                Description.ID_RIFLE,
                "fucile", Description.DESCRIPTION_RIFLE); //CAMBIARE
        rifle.setAlias(new String[]{"schioppo", "archibugio",
                "carabina", "doppietta", "moschetto", "arma"});
        ImageIcon imgRifle = new ImageIcon(
                "resources//img//gameItem//rifle.png");
        rifle.setItemImage(imgRifle);
        rifle.setPickupable(true);

        GameItem key63 = new GameItem(Description.ID_KEY63, "chiave63",
                Description.DESCRIPTION_KEY_63);
        //KEY63 o KEY53? XD
        key63.setAlias(new String[]{"chiavi63"});
        ImageIcon imgKey63 = new ImageIcon(
                "resources//img//gameItem//key63.png");
        key63.setItemImage(imgKey63);
        key63.setConsumable((byte) 1);

        GameItem key13 = new GameItem(Description.ID_KEY13, "chiave13",
                Description.DESCRIPTION_KEY_13);
        key13.setAlias(new String[]{"chiavi13"});
        ImageIcon imgKey13 = new ImageIcon(
                "resources//img//gameItem//key13.png");
        key13.setItemImage(imgKey13);
        key13.setConsumable((byte) 1);

        GameItem stone = new GameItem(Description.ID_STONE, "pietra",
                Description.DESCRIPTION_STONE);
        stone.setAlias(new String[]{"pietre", "pietroline", "pietrine", "pietrolina", "pietrina", "ciottolo"});
        ImageIcon imgStone = new ImageIcon(
                "resources//img//gameItem//stone.png");
        stone.setItemImage(imgStone);
        stone.setPickupable(true);

        GameItem photo = new GameItem(Description.ID_PHOTO, "foto",
                Description.DESCRIPTION_PHOTO);
        photo.setAlias(new String[]{"screen", "immagine", "fotografia"});
        ImageIcon imgPhoto = new ImageIcon(
                "resources//img//gameItem//photo.png");
        photo.setItemImage(imgPhoto);
        photo.setIndispensable(true);
        photo.setGIPassword(true);
        g.getInventory().add(photo);

        GameItem paperRoomBar = new GameItem(
                Description.ID_PAPER_BAR, "foglio47",
                Description.DESCRIPTION_PAPER_BAR); //CAMBIARE
        paperRoomBar.setAlias(new String[]{"nota47", "carta47",
                "note47", "appunti47"});
        ImageIcon imgPaperRoomBar = new ImageIcon(
                "resources//img//gameItem//paperHallwayColumbus.png");
        //CAMBIARE IMMAGINE NEL CASO
        paperRoomBar.setItemImage(imgPaperRoomBar);
        paperRoomBar.setIndispensable(true);

        //ItemContainer e oggetti della stanza
        GameItemContainer furniture79 = new GameItemContainer(
                Description.ID_FURNITURE_79, "mobile",
                Description.DESCRIPTION_FURNITURE_79);
        furniture79.setOpenedDescription(
                Description.OPENED_DESCRIPTION_FURNITURE_79);
        furniture79.setAlias(new String[]{"comodino"});
        furniture79.add(cellphone);
        furniture79.setLockedBy("");

        GameItemContainer wardrobe79 = new GameItemContainer(
                Description.ID_WARDROBE_79, "armadio",
                Description.DESCRIPTION_WARDROBE_79);
        wardrobe79.setOpenedDescription(
                Description.OPENED_DESCRIPTION_WARDROBE_79);
        wardrobe79.setAlias(new String[]{"guardaroba"});
        //wardrobe79.add(knife);
        wardrobe79.add(lighter);
        wardrobe79.setLockedBy(wallet.getName());

        GameItemContainer paint79 = new GameItemContainer(
                Description.ID_PAINT_79, "quadro",
                Description.DESCRIPTION_PAINT_79);
        paint79.setMovedDescription(Description.MOVED_DESCRIPTION_PAINT_79);
        paint79.setAlias(new String[]{"riquadro", "figura",
                "pittura", "affresco"});
        paint79.add(key79);
        paint79.setMovable(true);
        paint79.setiCNotInsertable(true);

        GameItemContainer furnitureBathroom79 = new GameItemContainer(
                Description.ID_FURNITURE_BATHROOM_79, "cestino",
                Description.DESCRIPTION_FURNITURE_BATHROOM_79);
        furnitureBathroom79.setOpenedDescription(
                Description.OPENED_DESCRIPTION_FURTNITURE_BATHROOM_79);
        furnitureBathroom79.setAlias(new String[]{"portaoggetti",
                "portabiancheria", "cesto"});
        furnitureBathroom79.setLockedBy("");

        GameItemContainer bed79 = new GameItemContainer(Description.ID_BED_79,
                "letto", Description.DESCRIPTION_BED_79);
        bed79.setAlias(new String[]{"alcova", "lettiera"});
        bed79.setiCNotOpenable(true);
        bed79.setiCNotInsertable(true);

        GameItemContainer coatHook79 = new GameItemContainer(
                Description.ID_COATHOOK_79, "appendiabiti",
                Description.DESCRIPTION_COATHOOK_79);
        coatHook79.setAlias(new String[]{"attaccapanni", "appendiabito"});
        coatHook79.setiCNotOpenable(true);
        coatHook79.setiCNotInsertable(true);

        GameItemContainer slot = new GameItemContainer(Description.ID_SLOT,
                "slot", Description.DESCRIPTION_SLOT);
        slot.setAlias(new String[]{"macchinetta"});
        slot.setDescriptionUsableWithDrops(
                Description.DESCRIPTION_SLOT_USABLE_WITH_DROPS);
        slot.setDescriptionAlreadyUsedWithDrops(
                Description.DESCRIPTION_SLOT_ALREADY_USED_WITH_DROPS);
        slot.setUsableWithDrops(true);
        slot.add(handle);
        slot.setiCNotOpenable(true);
        slot.setiCNotInsertable(true);

        GameItemContainer changeMachine = new GameItemContainer(
                Description.ID_CHANGE_MACHINE, "cambiamonete",
                Description.DESCRIPTION_CHANGE_MACHINE); //CAMBIAREEE
        changeMachine.setAlias(new String[]{"cambiovaluta", "cambiavaluta"});
        //changeMachine.setdescr //DESCRIZIONE SECONDARIA
        changeMachine.add(cardGameRoom);
        changeMachine.setiCNotInsertable(true);
        changeMachine.setiCNotOpenable(true);

        GameItemContainer radio = new GameItemContainer(Description.ID_RADIO,
                "radio", Description.DESCRIPTION_RADIO);
        radio.setAlias(new String[]{"emittente", "radiostazione", "radiofonia",
                "radiocomunicazione", "radiodiffusione"});
        radio.setUsableWithItem(true);
        radio.setOpenedDescription(
                Description.OPENED_DESCRIPTION_RADIO);
        radio.setDescriptionUsableWithDrops(
                Description.DESCRIPTION_RADIO_USABLE_WITH_DROPS);
        radio.setDescriptionAlreadyUsedWithDrops(
                Description.DESCRIPTION_RADIO_ALREADY_USED_WITH_DROPS);
        radio.setDescriptionUsableButItemRemoved(
                Description.DESCRIPTION_RADIO_USABLE_BUT_ITEM_REMOVED);
        radio.setLockedBy(knife.getName());

        GameItemContainer furnace = new GameItemContainer(
                Description.ID_FURNACE,
                "forno", Description.DESCRIPTION_FURNACE); //CAMBIARE
        furnace.setOpenedDescription(
                Description.OPENED_DESCRIPTION_FURNACE);
        furnace.setAlias(new String[]{"forni", "fornace", "fornaci"});
        furnace.setLockedBy("");
        furnace.add(paperPass3);
        //LASCIAMO CHE SI POSSONO INSERIRE OGGETTI NEL FORNO O NO?

        GameItemContainer remoteControl = new GameItemContainer(
                Description.ID_REMOTE_CONTROL, "telecomando",
                Description.DESCRIPTION_REMOTE_CONTROL);
        remoteControl.setOpenedDescription(
                Description.OPENED_DESCRIPTION_REMOTE_CONTROL);
        remoteControl.setAlias(new String[]{"apparecchio"});
        remoteControl.setDescriptionUsableWithDrops(
                Description.DESCRIPTION_REMOTE_CONTROL_USABLE_WITH_DROPS);
        remoteControl.setDescriptionAlreadyUsedWithDrops(
                Description.DESCRIPTION_REMOTE_CONTROL_ALREADY_USED_WITH_DROPS);
        remoteControl.setDescriptionUsableButItemRemoved(
                Description.DESCRIPTION_REMOTE_CONTROL_USABLE_BUT_ITEM_REMOVED);
        remoteControl.add(batteries);
        remoteControl.setLockedBy("");
        remoteControl.setUsableWithItem(true);

        GameItemContainer tv = new GameItemContainer(Description.ID_TV,
                "televisione", Description.DESCRIPTION_TV);
        tv.setAlias(new String[]{"tv", "televisore"});
        tv.setiCNotOpenable(true);
        tv.setiCNotInsertable(true); //CAPIRE SE INSERIRE USB O MENO

        GameItemContainer sofa = new GameItemContainer(Description.ID_SOFA,
                "divano", Description.DESCRIPTION_SOFA);
        sofa.setAlias(new String[]{"poltrona", "sofa"});
        sofa.setiCNotOpenable(true);
        sofa.setiCNotInsertable(true);

        GameItemContainer plantRelaxRoom = new GameItemContainer(
                Description.ID_PLANT_RELAX_ROOM, "pianta",
                Description.DESCRIPTION_PLANT);
        plantRelaxRoom.setAlias(new String[]{"piantina"});
        plantRelaxRoom.setiCNotOpenable(true);
        plantRelaxRoom.setiCNotInsertable(true);

        GameItemContainer fan = new GameItemContainer(Description.ID_FAN,
                "ventilatore", Description.DESCRIPTION_FAN);
        fan.setMovedDescription(Description.MOVED_DESCRIPTION_FAN);
        fan.setAlias(new String[]{"ventola"});
        fan.setMovable(true);
        fan.setiCNotInsertable(true);
        fan.add(paperPass2);

        /*GameItemContainer box = new GameItemContainer(456456456,
        "scatola", "boooob");
        box.setOpenedDescription("booooobb2");
        box.setAlias(new String[]{"scatolone", "cartone", "box",
        "contenitore"});
        box.setLockedBy("");
        box.add(drink);*/

        GameItemContainer plantHallwayColumbus = new GameItemContainer(
                Description.ID_PLANT_HALLWAY_COLUMBUS, "pianta",
                Description.DESCRIPTION_PLANT);
        plantHallwayColumbus.setMovedDescription(
                Description.MOVED_DESCRIPTION_PLANT_HALLWAY_COLUMBUS);
        plantHallwayColumbus.setAlias(new String[]{"piantina"});
        plantHallwayColumbus.setMovable(true);
        plantHallwayColumbus.add(paperHallwayColumbus);
        plantHallwayColumbus.setiCNotInsertable(true);

        GameItemContainer table = new GameItemContainer(Description.ID_TABLE,
                "tavolo", Description.DESCRIPTION_TABLE);
        table.setAlias(new String[]{"tavoli", "tavolino", "tavolini"});
        table.setiCNotOpenable(true);
        table.setiCNotInsertable(true);

        GameItemContainer glass = new GameItemContainer(Description.ID_GLASS,
                "bicchiere", Description.DESCRIPTION_GLASS);
        glass.setAlias(new String[]{"bicchieri", "calice", "flute", "calici"});
        glass.setiCNotOpenable(true);
        glass.setiCNotInsertable(true);

        GameItemContainer cup = new GameItemContainer(Description.ID_CUP,
                "tazza", Description.DESCRIPTION_CUP);
        cup.setAlias(new String[]{"tazze", "tazzina", "tazzine"});
        cup.setMovedDescription(Description.MOVED_DESCRIPTION_CUP);
        cup.setMovable(true);
        cup.add(token);
        cup.setiCNotInsertable(true);

        GameItemContainer displayCase = new GameItemContainer(
                Description.ID_DISPLAY_CASE, "vetrina",
                Description.DESCRIPTION_DISPLAY_CASE);
        displayCase.setAlias(new String[]{"espositore", "cristalliera",
                "bacheca", "teca"}); //vetriera, vetrata
        displayCase.setOpenedDescription(
                Description.OPENED_DESCRIPTION_DISPLAY_CASE);
        //INSERTABLE SI O NO?
        displayCase.setLockedBy(handle.getName());
        displayCase.add(paperRoomBar);

        GameItemContainer cashDesk = new GameItemContainer(
                Description.ID_CASH_DESK, "cassa",
                Description.DESCRIPTION_CASH_DESK);
        cashDesk.setOpenedDescription(Description.OPENED_DESCRIPTION_CASH_DESK);
        //INSERTABLE SI O NO?
        cashDesk.setLockedBy(""); //cambiare con chiave
        cashDesk.add(paperPass1);

        GameItemContainer computer = new GameItemContainer(
                Description.ID_COMPUTER, "computer",
                Description.DESCRIPTION_COMPUTER);
        computer.setAlias(new String[]{"pc", "notebook", "portatile",
                "calcolatore", "elaboratore"});
        computer.setUsableWithItem(true);
        computer.setiCNotOpenable(true);
        computer.add(usb);

        GameItemContainer bed53 = new GameItemContainer(Description.ID_BED_53,
                "letto", Description.DESCRIPTION_BED_53);
        bed53.setAlias(new String[]{"alcova", "lettiera"});
        bed53.setiCNotOpenable(true);
        bed53.setiCNotInsertable(true);

        GameItemContainer babycot = new GameItemContainer(
                Description.ID_BABYCOT,
                "culla", Description.DESCRIPTION_BABYCOT);
        babycot.setAlias(new String[]{"connola", "brandina"});
        babycot.setiCNotOpenable(true);
        babycot.setiCNotInsertable(true);

        GameItemContainer tv53 = new GameItemContainer(Description.ID_TV_53,
                "televisione", Description.DESCRIPTION_TV_53);
        tv53.setAlias(new String[]{"tv", "televisore"});
        tv53.setiCNotOpenable(true);
        //tv53.setiCNotInsertable(true);

        GameItemContainer shoeRack = new GameItemContainer(
                Description.ID_SHOE_RACK,
                "scarpiera", Description.DESCRIPTION_SHOE_RACK);
        shoeRack.setAlias(new String[]{"portascarpe"});
        shoeRack.setiCNotOpenable(true);
        shoeRack.setiCNotInsertable(true);

        GameItemContainer armchair53 = new GameItemContainer(
                Description.ID_ARMCHAIR53,
                "poltrona", Description.DESCRIPTION_ARMCHAIR53);
        armchair53.setAlias(new String[]{"sofa", "pouf", "poltroncina"});
        armchair53.setiCNotOpenable(true);
        armchair53.setiCNotInsertable(true);

        GameItemContainer window53 = new GameItemContainer(
                Description.ID_WINDOW53,
                "finestra", Description.DESCRIPTION_WINDOW53);
        window53.setAlias(new String[]{"vetro"}); //vetrina
        window53.setOpenedDescription(
                Description.OPENED_DESCRIPTION_WINDOW53); //CAMBIARE
        window53.setiCNotInsertable(true);

        GameItemContainer dresser53 = new GameItemContainer(
                Description.ID_DRESSER53,
                "comò", Description.DESCRIPTION_DRESSER53);
        dresser53.setAlias(new String[]{"cassettone", "cassettiera",
                "canterano", "como"});
        dresser53.setOpenedDescription(
                Description.OPENED_DESCRIPTION_DRESSER53);
        dresser53.setLockedBy(""); //cambiare
        //dresser53.add(socks);

        GameItemContainer coatHook53 = new GameItemContainer(
                Description.ID_COATHOOK53, "appendiabiti",
                Description.DESCRIPTION_COATHOOK53);
        coatHook53.setAlias(new String[]{"attaccapanni", "appendiabito"});
        coatHook53.setMovedDescription(
                Description.MOVED_DESCRIPTION_COATHOOK53);
        coatHook53.add(hook);
        coatHook53.setMovable(true);
        coatHook53.setiCNotInsertable(true);

        GameItemContainer printer = new GameItemContainer(
                Description.ID_PRINTER,
                "stampante", Description.DESCRIPTION_PRINTER);
        printer.setOpenedDescription(Description.OPENED_DESCRIPTION_PRINTER);
        printer.setAlias(new String[]{"fotocopiatrice", "scanner"});
        printer.setiCNotInsertable(true);
        printer.add(paperPass4);

        GameItemContainer whiteboard = new GameItemContainer(
                Description.ID_WHITEBOARD,
                "lavagna", Description.DESCRIPTION_WHITEBOARD);
        whiteboard.setAlias(new String[]{"lavagnetta"});
        whiteboard.setiCNotOpenable(true);
        whiteboard.setiCNotInsertable(true);

        GameItemContainer glassCabinet = new GameItemContainer(
                Description.ID_GLASS_CABINET,
                "vetrina", Description.DESCRIPTION_GLASS_CABINET);
        glassCabinet.setAlias(new String[]{"espositore",
                "cristalliera", "armadio", "vetrata"});
        glassCabinet.setOpenedDescription(
                Description.OPENED_DESCRIPTION_GLASS_CABINET);
        //INSERTABLE SI O NO?
        glassCabinet.setLockedBy(""); //aggiungere qualcosa;
        //glassCabinet.add();

        GameItemContainer tableCCTV = new GameItemContainer(
                Description.ID_TABLE_CCTV,
                "scrivania", Description.DESCRIPTION_TABLE_CCTV);
        tableCCTV.setAlias(new String[]{"tavolo", "tavoli",
                "tavolino", "tavolini"});
        tableCCTV.setiCNotOpenable(true);
        tableCCTV.setiCNotInsertable(true);

        GameItemContainer computerCCTV = new GameItemContainer(
                Description.ID_COMPUTER_CCTV, "fisso",
                Description.DESCRIPTION_COMPUTER_CCTV);
        computerCCTV.setUsableWithItem(true);
        computerCCTV.setDescriptionUsableWithDrops(
                Description.DESCRIPTION_COMPUTER_CCTV_USABLE_WITH_DROPS);
        computerCCTV.setDescriptionAlreadyUsedWithDrops(
                Description.DESCRIPTION_COMPUTER_CCTV_ALREADY_USED_WITH_DROPS);
        computerCCTV.setDescriptionUsableButItemRemoved(
                Description.DESCRIPTION_COMPUTER_CCTV_USABLE_BUT_ITEM_REMOVED);
        computerCCTV.setiCNotOpenable(true);

        GameItemContainer laptopCCTV = new GameItemContainer(
                Description.ID_LAPTOP_CCTV, "portatile",
                Description.DESCRIPTION_LAPTOP_CCTV);
        laptopCCTV.setAlias(new String[]{"pc", "notebook", "laptop",
                "calcolatore", "elaboratore", "computer"});
        laptopCCTV.setiCNotOpenable(true);
        laptopCCTV.setiCNotInsertable(true);

        GameItemContainer chairCCTV = new GameItemContainer(
                Description.ID_CHAIR_CCTV, "sedia",
                Description.DESCRIPTION_CHAIR_CCTV);
        chairCCTV.setAlias(new String[]{"sedie", "girovole",
                "sgabello", "seggiola"});
        chairCCTV.setiCNotOpenable(true);
        chairCCTV.setiCNotInsertable(true);

        GameItemContainer fanLaundry = new GameItemContainer(
                Description.ID_FAN_LAUNDRY,
                "ventola", Description.DESCRIPTION_FAN_LAUNDRY);
        fanLaundry.setAlias(new String[]{"grata"});
        fanLaundry.setLockedBy(hook.getName());
        fanLaundry.setOpenedDescription(
                Description.OPENED_DESCRIPTION_FAN_LAUNDRY);
        fanLaundry.setiCNotInsertable(true);
        fanLaundry.setSecretAccess(true);

        GameItemContainer washingMachine = new GameItemContainer(
                Description.ID_WASHING_MACHINE, "lavatrice",
                Description.DESCRIPTION_WASHING_MACHINE);
        washingMachine.setAlias(new String[]{"lavatrici", "lavabiancheria"});
        washingMachine.setiCNotOpenable(true); //DA VALUTARE
        washingMachine.setiCNotInsertable(true); //DA VALUTARE

        GameItemContainer vacuumCleaner = new GameItemContainer(
                Description.ID_VACUUM_CLEANER, "aspirapolvere",
                Description.DESCRIPTION_VACUUM_CLEANER);
        vacuumCleaner.setAlias(new String[]{"aspiratore"});
        vacuumCleaner.setiCNotOpenable(true); //DA VALUTARE
        vacuumCleaner.setiCNotInsertable(true); //DA VALUTARE

        GameItemContainer strongboxCCTV = new GameItemContainer(
                Description.ID_STRONGBOX_CCTV,
        "cassaforte", Description.DESCRIPTION_STRONGBOX_CCTV);
        strongboxCCTV.setLockedBy("");
        strongboxCCTV.setPasswordLocked(true);
        strongboxCCTV.setPasswordLockedBy(
                Description.PASSWORD_STRONGBOX_CCTV);
        strongboxCCTV.setPasswordUnlockedDescription(
                Description.DESCRIPTION_STRONGBOX_UNLOCKED_CCTV);
        strongboxCCTV.add(keyBar); //CAMBIARE

        GameItemContainer book = new GameItemContainer(
                Description.ID_BOOK,
                "libro", Description.DESCRIPTION_BOOK);
        book.setAlias(new String[]{"quaderno", "rubrica",
                "agenda", "taccuino"});
        book.setOpenedDescription(
                Description.OPENED_DESCRIPTION_BOOK);
        book.setLockedBy("");
        book.setiCNotInsertable(true);
        book.add(paperRoom63);

        GameItemContainer mirror = new GameItemContainer(
                Description.ID_MIRROR,
                "specchio", Description.DESCRIPTION_MIRROR);
        mirror.setAlias(new String[]{"riflesso", "vetro"});
        mirror.setiCNotOpenable(true);
        mirror.setiCNotInsertable(true);
        //MOVABLE?

        GameItemContainer guitar = new GameItemContainer(
                Description.ID_GUITAR,
                "chitarra", Description.DESCRIPTION_GUITAR);
        guitar.setiCNotOpenable(true);
        guitar.setiCNotInsertable(true);

        GameItemContainer clock = new GameItemContainer(
                Description.ID_CLOCK,
                "orologio", Description.DESCRIPTION_CLOCK);
        clock.setAlias(new String[]{"pendolo", "sveglia"});
        //aggiungere descrizione per il move
        clock.setiCNotOpenable(true);
        clock.setiCNotInsertable(true);
        clock.setMovable(true); ///////VALUTARE

        GameItemContainer swing = new GameItemContainer(
                Description.ID_SWING,
                "altalena", Description.DESCRIPTION_SWING);
        swing.setAlias(new String[]{"dondolo"});
        swing.setiCNotOpenable(true);
        swing.setiCNotInsertable(true);

        GameItemContainer deckchair = new GameItemContainer(
                Description.ID_DECKCHAIR,
                "sdraio", Description.DESCRIPTION_DECKCHAIR);
        deckchair.setAlias(new String[]{"lettino"});
        deckchair.setMovedDescription(Description.MOVED_DESCRIPTION_DECKCHAIR);
        //CAMBIARE DESCRIZIONE
        deckchair.setMovable(true);
        deckchair.add(key63); //VEDERE SE VA BENE
        deckchair.setiCNotOpenable(true);
        deckchair.setiCNotInsertable(true);

        GameItemContainer exerciseBike = new GameItemContainer(
                Description.ID_EXERCISE_BIKE,
                "cyclette", Description.DESCRIPTION_EXERCISE_BIKE);
        exerciseBike.setAlias(new String[]{"bicicletta", "bici"});
        exerciseBike.setiCNotOpenable(true);
        exerciseBike.setiCNotInsertable(true);

        GameItemContainer bench = new GameItemContainer(
                Description.ID_BENCH,
                "panca", Description.DESCRIPTION_BENCH);
        bench.setiCNotOpenable(true);
        bench.setiCNotInsertable(true);

        GameItemContainer pool = new GameItemContainer(
                Description.ID_POOL,
                "piscina", Description.DESCRIPTION_POOL);
        pool.setAlias(new String[]{"vasca"});
        pool.setiCNotOpenable(true);
        pool.setiCNotInsertable(true);

        GameItemContainer lightPole = new GameItemContainer(
                Description.ID_LIGHT_POLE,
                "lampione", Description.DESCRIPTION_LIGHT_POLE);
        lightPole.setAlias(new String[]{"lampada", "lampadario",
                "palo", "luce"});
        lightPole.setiCNotOpenable(true);
        lightPole.setiCNotInsertable(true);

        GameItemContainer windowGarden = new GameItemContainer(
                Description.ID_WINDOW_GARDEN,
                "finestra", Description.DESCRIPTION_WINDOW_GARDEN); //CAMBIARE
        windowGarden.setAlias(new String[]{"vetro"}); //vetrina
        windowGarden.setiCNotOpenable(true);
        windowGarden.setiCNotInsertable(true);

        GameItemContainer speaker = new GameItemContainer(
                Description.ID_SPEAKER, "cassa",
                Description.DESCRIPTION_SPEAKER);
        speaker.setAlias(new String[]{"casse", "stereo"});
        speaker.setiCNotOpenable(true);
        speaker.setiCNotInsertable(true);

        GameItemContainer chandelier = new GameItemContainer(
                Description.ID_CHANDELIER,
                "lampadario", Description.DESCRIPTION_CHANDELIER);
        chandelier.setAlias(new String[]{"lampada", "luce", "lumiera"});
        chandelier.setiCNotOpenable(true);
        chandelier.setiCNotInsertable(true);

        GameItemContainer ampoule = new GameItemContainer(
                Description.ID_AMPOULE,
                "ampolla", Description.DESCRIPTION_AMPOULE);
        ampoule.setAlias(new String[]{"boccia"});
        ampoule.setiCNotOpenable(true);
        ampoule.setiCNotInsertable(true);

        GameItemContainer landlinePhone= new GameItemContainer(
                Description.ID_LANDLINE_PHONE,
                "fisso", Description.DESCRIPTION_LANDLINE_PHONE);
        landlinePhone.setAlias(new String[]{"telefono"});
        landlinePhone.setiCNotOpenable(true);
        landlinePhone.setiCNotInsertable(true);

        GameItemContainer piano = new GameItemContainer(
                Description.ID_PIANO,
                "pianoforte", Description.DESCRIPTION_PIANO);
        piano.setAlias(new String[]{"piano"});
        piano.setiCNotOpenable(true);
        piano.setiCNotInsertable(true);

        GameItemContainer strongboxHall = new GameItemContainer(
                Description.ID_STRONGBOX_HALL,
                "cassaforte", Description.DESCRIPTION_STRONGBOX_HALL);
        strongboxHall.setLockedBy("");
        strongboxHall.setPasswordLocked(true);
        strongboxHall.setPasswordLockedBy(
                Description.PASSWORD_STRONGBOX_HALL);
        strongboxHall.setPasswordUnlockedDescription(
                Description.DESCRIPTION_STRONGBOX_UNLOCKED_HALL);
        //strongboxHall.add(keyBar); //CAMBIARE CON  CHIAVE  13

        //Stanze
        Room room79 = new Room(Description.ID_ROOM_79, "Stanza 79",
                Description.DESCRIPTION_ROOM_79);
        room79.setLookDescription(Description.LOOK_ROOM_79);
        room79.setRoomImage(new ImageIcon("resources//img//room//room79.png"));
        room79.setVisitedDescription(Description.DESCRIPTION_VISITED_ROOM_79);
        room79.addItem(wardrobe79);
        room79.addItem(furniture79);
        room79.addItem(paint79);
        room79.addItem(bed79);
        room79.addItem(coatHook79);

        Room bathroom79 = new Room(Description.ID_BATHROOM_79,
                "Bagno stanza 79", Description.DESCRIPTION_BATHROOM_79);
        bathroom79.setLookDescription(Description.LOOK_BATHROOM_79);
        bathroom79.setRoomImage(new ImageIcon(
                "resources//img//room//bathroom79.png"));
        bathroom79.setVisitedDescription(
                Description.DESCRIPTION_VISITED_BATHROOM_79);
        bathroom79.addItem(furnitureBathroom79);
        bathroom79.addItem(batteries);

        Room hallway = new Room(Description.ID_HALLWAY, "Corridoio",
                Description.DESCRIPTION_HALLWAY);
        hallway.setLookDescription(Description.LOOK_HALLWAY);
        hallway.setRoomImage(new ImageIcon(
                "resources//img//room//hallway.png"));
        hallway.setVisitedDescription(Description.DESCRIPTION_VISITED_HALLWAY);
        hallway.setLockedBy(""); //chiave79

        Room gameRoom = new Room(Description.ID_GAMEROOM, "Sala giochi",
                Description.DESCRIPTION_GAMEROOM);
        gameRoom.setLookDescription(Description.LOOK_GAMEROOM);
        gameRoom.setRoomImage(new ImageIcon(
                "resources//img//room//gameRoom.png"));
        gameRoom.setVisitedDescription(
                Description.DESCRIPTION_VISITED_GAMEROOM);
        gameRoom.setLockedBy(""); //VALUTARE
        gameRoom.addItem(slot);
        gameRoom.addItem(changeMachine);

        Room kitchen = new Room(Description.ID_KITCHEN, "Cucina",
                Description.DESCRIPTION_KITCHEN);
        kitchen.setLookDescription(Description.LOOK_KITCHEN);
        kitchen.setRoomImage(new ImageIcon(
                "resources//img//room//kitchen.png"));
        kitchen.setVisitedDescription(Description.DESCRIPTION_VISITED_KITCHEN);
        kitchen.setLockedBy(""/*cardGameRoom.getName()*/);
        kitchen.addItem(radio);
        kitchen.addItem(furnace);

        Room relaxRoom = new Room(Description.ID_RELAX_ROOM,
                "Stanza relax", Description.DESCRIPTION_RELAX_ROOM);
        relaxRoom.setLookDescription(Description.LOOK_RELAX_ROOM);
        relaxRoom.setRoomImage(new ImageIcon(
                "resources//img//room//relaxRoom.png"));
        relaxRoom.setVisitedDescription(
                Description.DESCRIPTION_VISITED_RELAX_ROOM);
        gameRoom.setLockedBy(""); //VALUTARE
        relaxRoom.addItem(remoteControl);
        relaxRoom.addItem(tv);
        relaxRoom.addItem(sofa);
        relaxRoom.addItem(plantRelaxRoom);
        relaxRoom.addItem(fan); //CHIAVE USB

        Room hallwayColumbus = new Room(Description.ID_HALLWAY_COLUMBUS,
                "Corridoio Columbus",
                Description.DESCRIPTION_HALLWAY_COLUMBUS);
        hallwayColumbus.setLookDescription(Description.LOOK_HALLWAY_COLUMBUS);
        hallwayColumbus.setRoomImage(new ImageIcon(
                "resources//img//room//hallwayColumbus.png"));
        hallwayColumbus.setVisitedDescription(
                Description.DESCRIPTION_VISITED_HALLWAY_COLUMBUS);
        hallwayColumbus.setLockedBy(""); //chiaveColombus o //VALUTARE
        hallwayColumbus.addItem(plantHallwayColumbus);
        //hallwayOvest.addItem(box);

        Room bar = new Room(Description.ID_BAR, "Bar Columbus",
                Description.DESCRIPTION_BAR);
        bar.setLookDescription(Description.LOOK_BAR);
        bar.setRoomImage(new ImageIcon("resources//img//room//bar.png"));
        bar.setVisitedDescription(Description.DESCRIPTION_VISITED_BAR);
        bar.setLockedBy(""); //chiave o //VALUTARE
        bar.addItem(table);
        bar.addItem(glass);
        bar.addItem(cup);
        bar.addItem(displayCase);
        bar.addItem(cashDesk);
        //contenitore apribile con la chiave della cassa*/

        Room laundry = new Room(Description.ID_LAUNDRY, "Lavanderia",
                Description.DESCRIPTION_LAUNDRY);
        laundry.setLookDescription(Description.LOOK_LAUNDRY);
        laundry.setRoomImage(new ImageIcon(
                "resources//img//room//laundry.png"));
        laundry.setVisitedDescription(Description.DESCRIPTION_VISITED_LAUNDRY);
        laundry.setLockedBy(""/*cardGameRoom.getName()*/);
        //chiave che trova nella cassaforte o //VALUTARE
        laundry.addItem(fanLaundry);
        laundry.addItem(washingMachine);
        laundry.addItem(vacuumCleaner);

        Room room53 = new Room(Description.ID_ROOM_53, "Stanza 53",
                Description.DESCRIPTION_ROOM_53); //CAMBIARE
        room53.setLookDescription(Description.LOOK_ROOM_53);
        room53.setRoomImage(new ImageIcon("resources//img//room//room53.png"));
        room53.setVisitedDescription(Description.DESCRIPTION_VISITED_ROOM_53);
        room53.addItem(computer);
        room53.addItem(bed53);
        room53.addItem(babycot);
        room53.addItem(tv53);
        room53.addItem(shoeRack);
        room53.addItem(armchair53);
        room53.addItem(window53);
        room53.addItem(coatHook53);
        room53.addItem(dresser53);
        room53.setLockedBy(key63.getName()); //o key53?
        room53.setAnOpenDoor(true);

        Room cctv = new Room(Description.ID_CCTV, "CCTV",
                Description.DESCRIPTION_CCTV);
        cctv.setLookDescription(Description.LOOK_CCTV);
        cctv.setRoomImage(new ImageIcon(
                "resources//img//room//cctv.png"));
        cctv.setVisitedDescription(Description.DESCRIPTION_VISITED_CCTV);
        cctv.setLockedBy(""/*cardGameRoom.getName()*/);
        //VALUTARE TESSERA (DIVERSI GRADI IN BASE ALL'IMPORTANZA DELLA STANZA)
        cctv.addItem(printer);
        cctv.addItem(whiteboard);
        cctv.addItem(glassCabinet);
        cctv.addItem(tableCCTV);
        cctv.addItem(computerCCTV);
        cctv.addItem(laptopCCTV);
        cctv.addItem(chairCCTV);
        cctv.addItem(strongboxCCTV);

        Room room63 = new Room(Description.ID_ROOM_63, "Stanza 63",
                Description.DESCRIPTION_ROOM_63); //CAMBIARE
        room63.setLookDescription(Description.LOOK_ROOM_63);
        room63.setRoomImage(new ImageIcon("resources//img//room//room63.png"));
        room63.setVisitedDescription(Description.DESCRIPTION_VISITED_ROOM_63);
        room63.addItem(book);
        room63.addItem(rifle);
        room63.addItem(mirror);
        room63.addItem(guitar);
        room63.addItem(clock);
        //room63.setLockedBy(key63.getName()); //VALUTARE ATTENTAMENTE
        //lBy FARE ATTENZIONE (problema ovest per giardino e nord per la 53)

        Room garden = new Room(Description.ID_GARDEN, "Giardino",
                Description.DESCRIPTION_GARDEN); //CAMBIARE
        garden.setLookDescription(Description.LOOK_GARDEN);
        garden.setRoomImage(new ImageIcon(
                "resources//img//room//garden.png"));
        garden.setVisitedDescription(Description.DESCRIPTION_VISITED_GARDEN);
        //CAMBIARE VISITED
        garden.setImpossibleToAccessDirectly(true);
        garden.addItem(swing);
        garden.addItem(deckchair);
        garden.addItem(exerciseBike);
        garden.addItem(bench);
        garden.addItem(pool);
        garden.addItem(lightPole);
        garden.addItem(windowGarden);
        garden.setLockedBy(""); //DOVREBBE ANDAR BENE

        Room hallwayClippings = new Room(
                Description.ID_HALLWAY_CLIPPINGS, "Corridoio Clippings",
                Description.DESCRIPTION_HALLWAY_CLIPPINGS); //CAMBIARE
        hallwayClippings.setLookDescription(
                Description.LOOK_HALLWAY_CLIPPINGS); //CAMBIARE
        hallwayClippings.setRoomImage(new ImageIcon(
                "resources//img//room//hallwayClippings.png"));
        hallwayClippings.setVisitedDescription(
                Description.DESCRIPTION_VISITED_HALLWAY_CLIPPINGS); //CAMBIARE
        hallwayClippings.setLockedBy(""); //chiaveClippings o //VALUTARE

        Room hall = new Room(Description.ID_HALL,
                "Hall", Description.DESCRIPTION_HALL);
        hall.setLookDescription(Description.LOOK_HALL);
        hall.setRoomImage(new ImageIcon(
                "resources//img//room//hall.png"));
        hall.setImpossibleToAccessDirectly(true);
        hall.addItem(speaker);
        hall.addItem(chandelier);
        hall.addItem(ampoule);
        hall.addItem(landlinePhone);
        hall.addItem(piano);
        hall.addItem(strongboxHall);
        hall.addItem(stone);

        Room room13 = new Room(Description.ID_ROOM_13, "Stanza 13",
                Description.DESCRIPTION_ROOM_13); //CAMBIARE
        room13.setLookDescription(Description.LOOK_ROOM_13); //CAMBIARE
        room13.setRoomImage(new ImageIcon(
                "resources//img//room//room63.png")); //CAMBIARE IMMAGINE
        room13.setVisitedDescription(Description.DESCRIPTION_VISITED_ROOM_13);
        //CAMBIARE VISITED
        room13.setImpossibleToAccessDirectly(true);

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
        hallwayColumbus.setNorth(hallwayClippings);
        hallwayColumbus.setSouth(room53);
        hallwayClippings.setSouth(hallwayColumbus);
        room53.setNorth(hallwayColumbus);
        room53.setWest(garden);
        garden.setEast(room63);
        room53.setSouth(room63);
        room63.setNorth(room53);
        room63.setWest(garden);
        relaxRoom.setSouth(hallway);
        bar.setEast(hallwayColumbus);
        hallwayClippings.setEast(cctv);
        cctv.setWest(hallwayClippings);
        hallwayClippings.setWest(kitchen);
        kitchen.setEast(hallwayClippings);
        hallwayClippings.setNorth(laundry);
        laundry.setSouth(hallwayClippings);
        laundry.setNorth(hall);
        //hall.setNorth(room13)
        //apportare modifiche

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
        look.setAlias(new String[]{"osserva", "vedi", "descrivi",
                "controlla", "esamina"});
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
        use.setAlias(new String[]{"utilizza"});
        g.getCommands().add(use);

        Command move = new Command("sposta", CommandType.MOVE);
        move.setAlias(new String[]{"muovi", "trascina"});
        g.getCommands().add(move);

        Command pickUp = new Command("prendi", CommandType.PICK_UP);
        pickUp.setAlias(new String[]{"raccogli", "afferra",
                "agguanta", "piglia"});
        g.getCommands().add(pickUp);

        Command drop = new Command("lascia", CommandType.DROP);
        drop.setAlias(new String[]{"molla", "abbandona"});
        g.getCommands().add(drop);

        Command insert = new Command("inserisci", CommandType.INSERT);
        insert.setAlias(new String[]{"immetti", "infila",
                "introduci", "metti"});
        g.getCommands().add(insert);

    }

    /**
     * Rende eseguibile la classe, in modo da ricreare i file di gioco.
     *
     * @param args
     */
    public static void main(final String[] args) {

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
