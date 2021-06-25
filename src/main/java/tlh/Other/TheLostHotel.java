package tlh.Other;

import tlh.Parser.WordType;
import tlh.Type.CommandType;
import tlh.Parser.ParserOutput;
import tlh.Type.GameItem;
import tlh.Type.GameItemContainer;
import tlh.Type.Room;

import java.util.Iterator;
import java.util.Objects;

public class TheLostHotel extends GameManager {

    public TheLostHotel(GameDescription game) {
        super(game);
    }

    @Override
    protected String executeCommand(ParserOutput pOutput) {

        // Prende il tipo di comando in modo da gestire la richiesta
        CommandType command = this.getGame().getCommandType(pOutput.getString(WordType.COMMAND));

        Room room = null;
        GameItem gameItem = null;
        StringBuilder output = new StringBuilder();

        // Se un singolo item viene sfruttato nella richiesta lo salva
        gameItem = this.findGameItem(pOutput);

        try {

            switch (command) {

                case NORD:
                case EST:
                case SUD:
                case OVEST:

                    // Controlla se ci si può spostare in quella direzione o meno
                    if(!pOutput.containsWordType(WordType.ERROR)) {
                        if (Objects.isNull(room = this.move(command))) {

                            output.append("Non puoi andare lì!\n");

                        } // Controlla che non sia bloccata
                        else if (room.getLockedBy().equals("")) {

                            // Si sposta nella stanza designata
                            this.getGame().setCurrentRoom(room);

                            // Controlla se si è finito il gioco in maniera "lecita"
                            //this.advancePlot();

                            output.append("-- " + this.getGame().getCurrentRoom().getName() + " --" + "\n\n");
                            if (!room.isVisited())
                                output.append(this.getGame().getCurrentRoom().getDescription() + "\n");
                            else
                                output.append(this.getGame().getCurrentRoom().getVisitedDescription() + "\n");

                            room.setVisited(true);

                        } else {
                            // Nel caso la stanza sia bloccata
                            output.append("Questa stanza è chiusa!\n");
                        }
                    } else {
                        String direction = command.toString();
                        output.append("Forse intendevi \"" + direction.toLowerCase() + "\"?\n");
                    }
                    break;

                case INVENTORY:
                    if(!pOutput.containsWordType(WordType.ERROR)) {
                        if(!this.getGame().getInventory().getInventoryList().isEmpty())
                            output.append("Oggetti presenti nell'inventario: " + this.getGame().getInventory().toString() + "\n");
                        else
                            output.append(this.getGame().getInventory().toString());
                    }
                    else
                        output.append("Forse intendevi \"inventario\"?\n");
                    break;

                // Comando per guardare
                case HELP:
                    if(!pOutput.containsWordType(WordType.ERROR) && !pOutput.containsWordType(WordType.INVENTORY_OBJ) && !pOutput.containsWordType(WordType.ROOM_OBJ)) {
                        output.append(showHelp());
                    }
                    else
                        output.append("Forse intendevi \"aiuto\"?\n");
                    break;

                case LOOK:

                    // Se si vuole guardare un oggetto
                    if(!pOutput.containsWordType(WordType.ERROR)) {
                        if (pOutput.size() == 2) {
                            if(pOutput.containsWordType(WordType.INVENTORY_OBJ)) {
                                output.append(gameItem.getDescription() + "\n");
                            }
                            else if(pOutput.containsWordType(WordType.ROOM_OBJ)) {
                                if(gameItem instanceof GameItemContainer) {
                                    if(((GameItemContainer) gameItem).isMovable()){
                                        if(!((GameItemContainer) gameItem).isMoved())
                                            output.append(gameItem.getDescription() + "\n");
                                        else
                                            output.append(((GameItemContainer) gameItem).getMovedDescription() + "\n");
                                    }
                                    else {
                                        if (((GameItemContainer) gameItem).isClosed())
                                            output.append(gameItem.getDescription() + "\n");
                                        else
                                            output.append(((GameItemContainer) gameItem).getOpenedDescription() + "\n");
                                    }

                                    if(gameItem.getName().equals("armadio") && ((GameItemContainer) gameItem).isClosed())
                                        output.append("\nN.B: Alcuni oggetti contenitori necessitano di un oggetto per essere aperti.\n" +
                                                "Per sbloccarli è necessario inserire il comando:\n\"apri [oggetto contenitore] con [oggetto]\"\n");

                                    if (gameItem.getName().equals("quadro") && !((GameItemContainer) gameItem).isMoved())
                                        output.append("\nN.B: Alcuni oggetti possono essere spostati con il comando:\n" +
                                                "sposta [oggetto da spostare]\n" +
                                                "Potresti trovarci qualcosa.\n");
                                }
                                //LO COMMENTO PERCHE' CRASHA PER OGGETTI PRESENTI IN CONTENITORI BLOCCATI -_- (COME "USA")
                                //else if (gameItem.isPickupable())
                                    //output.append("Devi prendere l'oggetto prima di poterlo esaminare!\n"); //CAMBIARE
                                else
                                    output.append("Specifica correttamente l'oggetto che vuoi esaminare, " +
                                            "altrimenti digita \"osserva\" per guardarti intorno.\n");
                            } //else
                                //output.append("PROVA OSSERVA\n");

                        } else if (pOutput.size() == 1) { // Se si vuole guardare la stanza

                            output.append(this.getGame().getCurrentRoom().getLookDescription() + "\n");

                        }
                        else if (pOutput.size() > 2) {
                            output.append("Uno alla volta...\n");
                        }
                    }
                    else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() > 2) {
                        output.append("Uno alla volta...\n");
                    }
                    else {
                        output.append("Specifica correttamente l'oggetto che vuoi esaminare, " +
                                "altrimenti digita \"osserva\" per guardarti intorno.\n");
                    }
                    break;

                // Comandi per sbloccare stanze o contenitori
                case USE:

                    if(pOutput.containsWordType(WordType.ROOM_OBJ))
                    {
                        if(gameItem instanceof GameItemContainer)
                        {
                            if(((GameItemContainer) gameItem).isUsableWithItem())
                            {
                                if(pOutput.size() == 2) {
                                    if (!gameItem.isUsed() && !((GameItemContainer) gameItem).getcItemList().getInventoryList().isEmpty()) {
                                        output.append(gameItem.getDescriptionUsableWithDrops() + "\n");
                                        gameItem.setUsed(true);
                                    } else if (!gameItem.isUsed() && ((GameItemContainer) gameItem).getcItemList().getInventoryList().isEmpty()) {
                                        output.append(gameItem.getDescriptionUsableButItemRemoved() + "\n");
                                    } else
                                        output.append(gameItem.getDescriptionAlreadyUsedWithDrops() + "\n");
                                }
                                else
                                    output.append("Specifica correttamente l'oggetto che vuoi usare.\n");

                                //CAMBIARE E VEDERE SE CAMBIARE DESCRIZIONE OSSERVA SE é STATO PRESO L'OGGETTO
                            }
                            else if(pOutput.size() == 2)
                            {
                                output.append("Non puoi usare questo oggetto contenitore (ora/così).\n"); //CAMBIARE (CAPIRE LA FUNZIONALITA' DEL COMANDO USA
                            }
                            else
                                output.append("Specifica correttamente l'oggetto che vuoi usare.\n");
                            //((GameItemContainer) gameItem).getcItemList().getInventoryList().removeIf(GameItem::isPicked);

                        }
                        //LO COMMENTO PERCHE' CRASHA PER OGGETTI PRESENTI IN CONTENITORI BLOCCATI -_- (COME "OSSERVA")
                        /*else if (gameItem.isPickupable())
                            output.append("Devi prendere l'oggetto prima di poterlo usare!\n"); //CAMBIARE*/
                        else
                            output.append("Specifica correttamente l'oggetto che vuoi usare. " +
                                    "Premi CTRL-L per capire le modalità di utilizzo.\n"); //CAMBIARE IMMEDIATAMENTE
                    }
                    // Se si vuole aprire una stanza con un oggetto dell'inventario
                    else if (pOutput.size() == 2 && pOutput.containsWordType(WordType.INVENTORY_OBJ)) {

                        //Apertura stanza
                        if (!gameItem.isConsumed() && this.unlockRoom(gameItem.getName())) {
                            output.append("Hai sbloccato la stanza!\n");

                            gameItem.consume();

                            // Se l'oggetto è stato consumato, lo rimuove dall'inventario
                            if (gameItem.isConsumed()) {

                                this.getGame().getInventory().remove(gameItem);
                                output.append("\nL'oggetto " + gameItem.getName() + " è stato rimosso.\n");

                            }

                            if(gameItem.getName().equals("chiave"))
                                output.append("\nN.B: Il tutorial è terminato.\n" +
                                        "Adesso dipende solo da te!\n");

                        } else {
                            output.append("Non puoi usare questo oggetto ora/così!\n");
                        }

                    }
                    else if(pOutput.containsWordType(WordType.ROOM_OBJ) && gameItem.isUsableWithDrops())
                    {
                        if(gameItem instanceof GameItemContainer)
                        {

                            ((GameItemContainer) gameItem).getcItemList().getInventoryList().removeIf(GameItem::isPicked);

                            if(!gameItem.isUsed())
                                output.append(gameItem.getDescriptionUsableWithDrops() + "\n");
                            else if(gameItem.isUsed() && ((GameItemContainer) gameItem).getcItemList().getInventoryList().isEmpty())
                                output.append(gameItem.getDescriptionAlreadyUsedWithDrops() + "\n");
                            else
                                output.append("già usato oggetto contentitore ma oggetti non ancora raccolti\n");
                            //CAMBIARE E VEDERE SE CAMBIARE DESCRIZIONE OSSERVA SE é STATO PRESO L'OGGETTO

                            for(GameItem g : ((GameItemContainer) gameItem).getcItemList().getInventoryList()) {
                                g.setPickupable(true);
                            }

                        }

                        gameItem.setUsed(true);

                    }
                    else if (pOutput.size() == 1) {
                        output.append("Specifica l'oggetto che vuoi usare.\n");
                    }
                    else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() > 2) {
                        output.append("Uno alla volta...\n");
                    }
                    else
                        output.append("Specifica correttamente l'oggetto che vuoi usare.\n");
                    break;

                case OPEN:

                     if (pOutput.containsWordType(WordType.ROOM_OBJ)) { // Apertura di un contenitore da sbloccare

                        GameItem iC = null; //contenitore
                        gameItem = null;
                        byte index = 0;

                        // Iteratore per ciclare sul ParserOutput
                        Iterator<WordType> it = pOutput.iterator();
                        it.next(); // Salta il comando iniziale, già conosciuto

                        // Salva gli oggetti che devono interagire nell'ordine prestabilito (e.g. "Apri baule con chiave")
                        while (it.hasNext()) {

                            //itemContainer. obbligatoriamente un oggetto della Room
                            if (index == 1 && it.next().equals(WordType.ROOM_OBJ)) {

                                iC = this.getGame().getCurrentRoom().getItemList().searchItem(pOutput.getString(WordType.ROOM_OBJ));

                            } //Chiave. obbligatoriamente un oggetto dell'inv
                            else if (index == 2 && it.next().equals(WordType.INVENTORY_OBJ)) {

                                gameItem = this.getGame().getInventory().searchItem(pOutput.getString(WordType.INVENTORY_OBJ));

                            }

                            index++;
                        }

                        // Se l'oggetto è effettivamente un contenitore
                        if (iC instanceof GameItemContainer) {

                            // Se trova l'oggetto per aprirlo ed è corretto oppure se il contenitore non è bloccato lo apre
                            if(!((GameItemContainer) iC).isMovable()) {
                                if (((GameItemContainer) iC).getLockedBy().equals("")) {
                                    if (!pOutput.containsWordType(WordType.ERROR)) {
                                        if (((GameItemContainer) iC).getcItemList().getInventoryList().isEmpty()) {

                                            if(((GameItemContainer) iC).isiCNotOpenable())
                                                output.append("Non puoi aprire quest'oggetto!\n");
                                            else {
                                                output.append("L'oggetto è stato aperto, ma è vuoto!\n");

                                                if (iC.getName().equals("cestino") && ((GameItemContainer) iC).isClosed())
                                                    output.append("\nN.B: Non in tutte le stanze sono presenti degli oggetti...\n" +
                                                            "In altre dovrai essere tu in grado di trovarli!");

                                                ((GameItemContainer) iC).setClosed(false);
                                            }

                                        } else {

                                            if(((GameItemContainer) iC).isClosed()) {
                                                output.append("Hai aperto l'oggetto " + iC.getName()
                                                        + "! Ecco il suo contenuto:" + iC.toString() + "\n");
                                            }
                                            else {
                                                output.append("Contenitore aperto ma oggetti non ancora raccolti"
                                                        + iC.toString() + "\n"); //CAMBIARE
                                            }

                                            if (iC.getName().equals("mobile") && ((GameItemContainer) iC).isClosed())
                                                output.append("\nN.B: Non sempre gli oggetti saranno visibili nelle varie stanze. Sfrutta nel migliore dei modi " +
                                                        "tutti i comandi per cercarli tutti... (Premi CTRL-L per visualizzare i comandi)\n");

                                            for (GameItem g : ((GameItemContainer) iC).getcItemList().getInventoryList()) {
                                                g.setPickupable(true);
                                            }

                                            ((GameItemContainer) iC).setClosed(false);

                                        }
                                    } else if(((GameItemContainer) iC).isiCNotOpenable())
                                        output.append("Non puoi aprire quest'oggetto!\n");
                                    else
                                        output.append("L'oggetto " + iC.getName() + " è sbloccato!\n");
                                } else {
                                    if (!pOutput.containsWordType(WordType.CON))
                                        output.append("Non puoi aprire quest'oggetto così!\n");
                                    else if ((gameItem != null && !gameItem.isConsumed() && ((GameItemContainer) iC).unlockContainer(gameItem.getName()))) {
                                        if (((GameItemContainer) iC).getcItemList().getInventoryList().isEmpty()) {

                                            output.append("L'oggetto è stato aperto, ma è vuoto!\n");
                                            ((GameItemContainer) iC).setClosed(false);

                                        } else {

                                            output.append("Hai aperto l'oggetto " + iC.getName()
                                                    + "! Ecco il suo contenuto:" + iC.toString() + "\n");

                                            for (GameItem g : ((GameItemContainer) iC).getcItemList().getInventoryList()) {
                                                g.setPickupable(true);
                                            }

                                            ((GameItemContainer) iC).setClosed(false);

                                            gameItem.consume();

                                            // Se l'oggetto è stato consumato, lo rimuove dall'inventario
                                            if (gameItem.isConsumed()) {
                                                this.getGame().getInventory().remove(gameItem);
                                                output.append("\nL'oggetto " + gameItem.getName() + " è stato rimosso.\n");
                                            }

                                        }
                                    } else {
                                        output.append("Non puoi aprire quest'oggetto così!\n");
                                    }

                                }
                            }
                            else
                                output.append("Non puoi aprire quest'oggetto!\n");

                        } else {
                            output.append("Specifica correttamente l'oggetto che vuoi aprire.\n"); //PROBLEMA SE NON PRENDE UN OGGETTO PICKABLE
                        }

                     } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ)) {
                         output.append("Non puoi aprire quest'oggetto!\n"); //da modificare nel caso in cui ci siano iC nell'inventario
                     } else if (pOutput.size() == 1) {
                         output.append("Specifica l'oggetto che vuoi aprire.\n");
                     }
                     else {
                         output.append("Specifica correttamente l'oggetto che vuoi aprire.\n");
                    }

                    break;

                // Comando per spostare
                case MOVE:
                    if (pOutput.containsWordType(WordType.ROOM_OBJ)) { //spostamento di un oggetto

                        GameItem iC = null; //contenitore
                        gameItem = null;
                        byte index = 0;

                        // Iteratore per ciclare sul ParserOutput
                        Iterator<WordType> it = pOutput.iterator();
                        it.next(); // Salta il comando iniziale, già conosciuto

                        // Salva gli oggetti che devono interagire nell'ordine prestabilito (e.g. "Apri baule con chiave")
                        while (it.hasNext()) {

                            //itemContainer. obbligatoriamente un oggetto della Room
                            if (index == 1 && it.next().equals(WordType.ROOM_OBJ)) {

                                iC = this.getGame().getCurrentRoom().getItemList().searchItem(pOutput.getString(WordType.ROOM_OBJ));

                            }

                            index++;
                        }
                        if (iC instanceof GameItemContainer) {

                            // Se trova l'oggetto per aprirlo ed è corretto oppure se il contenitore non è bloccato lo apre
                            if (((GameItemContainer) iC).isMovable() && pOutput.size() == 2) {
                                if (!pOutput.containsWordType(WordType.ERROR) && !pOutput.containsWordType(WordType.INVENTORY_OBJ)) {
                                    if (((GameItemContainer) iC).getcItemList().getInventoryList().isEmpty()) {

                                        output.append("L'oggetto è stato spostato ma non c'è nulla!\n");

                                    } else {

                                        if(!((GameItemContainer) iC).isMoved()) {
                                            output.append("Hai spostato l'oggetto " + iC.getName()
                                                    + "! Hai trovato:" + iC.toString() + "\n");
                                        }
                                        else {
                                            output.append("Contenitore aperto ma oggetti non ancora raccolti"
                                                    + iC.toString() + "\n"); //CAMBIARE
                                        }

                                        for (GameItem g : ((GameItemContainer) iC).getcItemList().getInventoryList()) {
                                            g.setPickupable(true);
                                        }

                                    }
                                    ((GameItemContainer) iC).setMoved(true);
                                } else
                                    output.append("Sposta... cosa?\n");
                            } else if (pOutput.size() > 2) {
                                output.append("Sposta... cosa?\n");
                            } else {
                                output.append("Non puoi spostare questo oggetto!\n");
                            }
                        } else
                            output.append("Sposta... cosa?\n");
                    }
                    else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() == 2)
                        output.append("Non puoi spostare questo oggetto!\n");
                    else if (pOutput.size() == 1) {
                        output.append("Specifica l'oggetto che vuoi spostare.\n");
                    }
                    else
                        output.append("Sposta... cosa?\n");
                    break;
                // Comando per raccogliere oggetti
                case PICK_UP:

                    // Controlla che l'oggetto sia della stanza e non presente nell'inventario
                    if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.size() == 2) {

                        // Controlla che l'oggetto si possa raccogliere
                        if (!Objects.isNull(gameItem) && gameItem.isPickupable()) {

                            if(!this.getGame().getInventory().isFull()) {
                                // Aggiunge l'oggetto all'inventario e lo rimuove dalla stanza
                                this.getGame().getInventory().add(gameItem);
                                this.getGame().getCurrentRoom().removeItem(gameItem);

                                gameItem.setPicked(true);
                                output.append("L'oggetto è stato aggiunto al tuo inventario.\n");
                            }
                            else
                                output.append("L'inventario è pieno!\n");

                        } else {

                            if(gameItem instanceof GameItemContainer)
                                output.append("Non puoi prendere un oggetto contenitore!\n"); //CAMBIARE
                            else
                                output.append("Prendi... cosa?\n");

                        }
                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() == 2) {

                        output.append("L'oggetto è già presente nel tuo inventario!\n");

                    } else if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.size() > 2) {

                        if(gameItem.isPickupable())
                            output.append("Uno alla volta...\n");
                        else
                            output.append("Prendi... cosa?\n");

                    } else if (pOutput.size() == 1) {
                        output.append("Specifica l'oggetto che vuoi prendere.\n");
                    } else{
                        output.append("Prendi... cosa?\n");
                    }
                    break;

                case DROP:
                    // Controlla che l'oggetto sia presente nell'inventario
                    if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() == 2) {

                        // Controlla che l'oggetto si possa lasciare
                        if (!Objects.isNull(gameItem)){

                            // Aggiunge l'oggetto all'inventario e lo rimuove dalla stanza
                            this.getGame().getInventory().remove(gameItem);
                            this.getGame().getCurrentRoom().addItem(gameItem);

                            gameItem.setPickupable(true);

                            output.append("Hai lasciato l'oggetto " + gameItem.getName() + ".\n");

                        } else {

                            output.append("Lascia... cosa?\n");

                        }
                    } else if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.size() == 2) {

                        if(gameItem instanceof GameItemContainer)
                            output.append("Non puoi lasciare un oggetto contenitore!\n"); //CAMBIARE
                        else
                            output.append("Lascia... cosa?\n");

                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() > 2) {

                        output.append("Uno alla volta...\n");

                    } else if (pOutput.size() == 1) {
                        output.append("Specifica l'oggetto che vuoi lasciare.\n");
                    } else {
                        output.append("Lascia... cosa?\n");
                    }
                    break;

                case PUSH:
                    if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.size() == 2) {

                        // Controlla che l'oggetto si possa spingere
                        if (!Objects.isNull(gameItem) && gameItem.isPushable()) {

                            gameItem.setPush(!gameItem.isPush());
                            // Aggiunge l'oggetto all'inventario e lo rimuove dalla stanza

                            output.append("Hai premuto il pulsante " + gameItem.getName() + ".\n"); //CAMBIARE

                        } else {

                            output.append("Premi... cosa?\n");

                        }
                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() == 2) {

                        output.append("Premi... cosa?\n");

                    } else if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.size() > 2) {

                        output.append("Uno alla volta...\n");

                    } else{
                        output.append("Premi... cosa?\n");
                    }
                    break;

                /*case QUIT:
                    if(!pOutput.containsWordType(WordType.ERROR)) {

                        //JOptionPane.showConfirmDialog(, "W", "w", JOptionPane.YES_NO_OPTION);

                        output.append("MESSAGGIO ABBANDONO\n");
                    }else
                        output.append("Forse intendevi \"abbandona\"?\n");
                    break;*/

                case INSERT:
                    //pOutput.size() è 5...
                    if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() == 5) {

                        GameItem iC = null; //contenitore
                        gameItem = null;
                        byte index = 0;

                        // Iteratore per ciclare sul ParserOutput
                        Iterator<WordType> it = pOutput.iterator();
                        it.next(); // Salta il comando iniziale, già conosciuto

                        // Salva gli oggetti che devono interagire nell'ordine prestabilito (e.g. "Apri baule con chiave")
                        while (it.hasNext()) {

                            //itemContainer. obbligatoriamente un oggetto della Room
                            if (index == 1 && it.next().equals(WordType.INVENTORY_OBJ)) {

                                gameItem = this.getGame().getInventory().searchItem(pOutput.getString(WordType.INVENTORY_OBJ));

                            }
                            else if (index == 2 && it.next().equals(WordType.ROOM_OBJ)) {

                                iC = this.getGame().getCurrentRoom().getItemList().searchItem(pOutput.getString(WordType.ROOM_OBJ));

                            }

                            index++;
                        }
                        if (iC instanceof GameItemContainer) {

                            // Se trova l'oggetto per aprirlo ed è corretto oppure se il contenitore non è bloccato lo apre
                            if (((GameItemContainer) iC).isUsableWithItem()) {
                                if (pOutput.containsWordType(WordType.ERROR)) {
                                    if (pOutput.containsWordType(WordType.IN)) {
                                        if(iC.getName().equals("telecomando") && gameItem.getName().equals("batterie")) {
                                            output.append("Hai inserito l'oggetto " + gameItem.getName() +
                                                    " in " + iC.getName() + "\n");

                                            ((GameItemContainer) iC).add(gameItem);
                                            this.getGame().getInventory().remove(gameItem); //Visualizzare o no la scritta che l'oggetto è stato rimosso?
                                        }else if(iC.getName().equals("radio") && gameItem.getName().equals("batterie") && ((GameItemContainer) iC).getLockedBy().equals("")) {
                                            output.append("Hai inserito l'oggetto " + gameItem.getName() +
                                                    " in " + iC.getName() + "\n");

                                            ((GameItemContainer) iC).add(gameItem);
                                            this.getGame().getInventory().remove(gameItem); //Visualizzare o no la scritta che l'oggetto è stato rimosso?
                                        }else if(iC.getName().equals("radio") && gameItem.getName().equals("batterie") && !((GameItemContainer) iC).getLockedBy().equals("")) {
                                            output.append("Impossibile inserire questo oggetto perchè il contenitore è chiuso!\n"); //CAMBIARE
                                        }
                                        //CONTINUARE DA QUI PER ALTRI OGGETTI
                                        else
                                            output.append("Non puoi inserire questo oggetto qui!\n");

                                    } else {

                                        output.append("Inserisci... cosa?\n");

                                    }
                                } else
                                    output.append("Inserisci... cosa?\n");
                            }
                            else {
                                if (pOutput.containsWordType(WordType.ERROR)) {
                                    if (pOutput.containsWordType(WordType.IN)) {
                                        if(((GameItemContainer) iC).getLockedBy().equals("")) {
                                            output.append("Hai inserito l'oggetto " + gameItem.getName() +
                                                    " in " + iC.getName() + "\n");

                                            ((GameItemContainer) iC).add(gameItem);
                                            this.getGame().getInventory().remove(gameItem); //Visualizzare o no la scritta che l'oggetto è stato rimosso dall'inventario?
                                        }
                                        else
                                            output.append("Impossibile inserire questo oggetto perchè il contenitore è chiuso!\n"); //CAMBIARE

                                    } else {

                                        output.append("Inserisci... cosa?\n");

                                    }
                                } else
                                    output.append("Inserisci... cosa?\n");
                            }
                        }
                        else
                            output.append("Non puoi inserire qualcosa qui!\n"); //CAMBIARE
                    }
                    else if(pOutput.size() == 1)
                        output.append("Specifica che oggetto vuoi inserire e dove!\n");
                    else if(pOutput.size() > 1 && pOutput.size() < 5 && pOutput.containsWordType(WordType.INVENTORY_OBJ))
                        output.append("Specifica correttamente dove vuoi inserire l'oggetto!\n");
                    else
                        output.append("Inserisci... cosa?\n");
                    break;

                default:
                    break;
            }
        } catch (NullPointerException e) {

            output.append("Sembra esserci qualcosa di strano in questa richiesta...\n");

        } finally {

            return output.toString();

        }
    }

    private GameItem findGameItem(ParserOutput pOutput) {

        GameItem item = null;

        /* Se l'azione sfrutta un oggetto salva l'oggetto e
           lo rinomina nel ParserOutput per fare riferimento al suo nome principale
           e non a potenziali alias, così da uniformare la gestione del comando.
         */
        if (pOutput.containsWordType(WordType.INVENTORY_OBJ)) { // Per gli oggetti dell'inventario

            // Cerca l'item
            item = this.getGame().getInventory().searchItem(pOutput.getString(WordType.INVENTORY_OBJ));

            // Se lo trova, lo rinomina nel ParserOutput
            if (!Objects.isNull(item)) {

                pOutput.add(WordType.INVENTORY_OBJ, item.getName());

            } else {// Se non ha trovato l'oggetto lo cerca in un contenitore non bloccato dell'inventario

                for (GameItem iC : this.getGame().getInventory().getInventoryList()) {

                    if (iC instanceof GameItemContainer && ((GameItemContainer) iC).getLockedBy().equals("")) {

                        if (!Objects.isNull(item = ((GameItemContainer) iC).getcItemList().searchItem(pOutput.getString(WordType.INVENTORY_OBJ)))) {
                            pOutput.add(WordType.INVENTORY_OBJ, item.getName());
                            break;
                        }

                    }

                }

            }

        } else if (pOutput.containsWordType(WordType.ROOM_OBJ)) { // Per gli oggetti della stanza

            item = this.getGame().getCurrentRoom().getItemList().searchItem(pOutput.getString(WordType.ROOM_OBJ));

            // Se lo trova, lo rinomina nel ParserOutput
            if (!Objects.isNull(item)) {

                pOutput.add(WordType.ROOM_OBJ, item.getName());

            } else {// Se non ha trovato l'oggetto lo cerca in un contenitore non bloccato della stanza

                for (GameItem iC : this.getGame().getCurrentRoom().getItemList().getInventoryList()) {

                    if (iC instanceof GameItemContainer && ((GameItemContainer) iC).getLockedBy().equals("")) {

                        if (!Objects.isNull(item = ((GameItemContainer) iC).getcItemList().searchItem(pOutput.getString(WordType.ROOM_OBJ)))) {
                            pOutput.add(WordType.ROOM_OBJ, item.getName());
                            break;
                        }

                    }

                }
            }

        }

        return item;
    }

    /**
     * Funzione per sbloccare le stanze chiuse, cerca tra tutte le stanze
     * adiacenti alla stanza attuale.
     *
     * @param iName nome dell'oggetto "chiave"
     * @return booleano, true se la stanza è stata sbloccata dall'item, false
     * altrimenti
     */
    private boolean unlockRoom(String iName) {

        boolean flag = false;

        /* Controlla se esiste una stanza in quella direzione, per ogni direzione
         * e controlla se è bloccata dall'item passato in input.
         * In caso affermativo, la sblocca e imposta il flag a true.
         */
        if (!Objects.isNull(this.getGame().getCurrentRoom().getSouth())
                && this.getGame().getCurrentRoom().getSouth().getLockedBy().equals(iName)) {

            this.getGame().getCurrentRoom().getSouth().setLockedBy("");
            flag = true;

        } else if (!Objects.isNull(this.getGame().getCurrentRoom().getNorth())
                && this.getGame().getCurrentRoom().getNorth().getLockedBy().equals(iName)) {

            this.getGame().getCurrentRoom().getNorth().setLockedBy("");
            flag = true;

        } else if (!Objects.isNull(this.getGame().getCurrentRoom().getEast())
                && this.getGame().getCurrentRoom().getEast().getLockedBy().equals(iName)) {

            this.getGame().getCurrentRoom().getEast().setLockedBy("");
            flag = true;

        } else if (!Objects.isNull(this.getGame().getCurrentRoom().getWest())
                && this.getGame().getCurrentRoom().getWest().getLockedBy().equals(iName)) {

            this.getGame().getCurrentRoom().getWest().setLockedBy("");
            flag = true;

        }

        return flag;
    }

    @Override
    public String showHelp() { return "\t     -- Come giocare a The Lost Hotel --\n"
            + "\n"
            + "E' possibile usare questi comandi testuali anche senza premere i relativi pulsanti:\n"
            + "\n"
            + ">> nord - Spostati in direzione nord\n"
            + ">> est - Spostati in direzione est\n"
            + ">> sud - Spostati in direzione sud\n"
            + ">> ovest - Spostati in direzione ovest\n"
            + ">> inventario - Consente di visualizzare l'inventario con i relativi oggetti\n"
            + ">> salva (valido solo tramite pulsante) - Salva la partita corrente\n"
            + ">> esci (valido solo tramite pulsante) - Permette di ritornare al menù principale ed eventualmente salvare una partita\n"
            + "\n"
            + "Altri comandi:\n"
            + "\n"
            + ">> aiuto - Consente di visualizzare l'elenco dei comando riconosciuti\n"
            + ">> osserva - Permette di guardarti intorno ed esaminare l'ambiente circostante\n"
            + ">> osserva [oggetto/oggetto contenitore] - Permette di esaminare un oggetto dell'inventario o della stanza corrente\n"
            + ">> usa [oggetto/oggetto contenitore] -  Usa un oggetto dell'inventario o della stanza corrente\n"
            + ">> apri [oggetto contenitore] - Apri un oggetto contenitore\n"
            + ">> apri [oggetto contenitore] con [oggetto] - Apri un oggetto contenitore bloccato con un oggetto\n"
            + ">> sposta [oggetto/oggetto contenitore] - Sposta un oggetto della stanza\n"
            + ">> prendi [oggetto] - Prendi un oggetto a terra nella stanza o in un contenitore\n"
            + ">> lascia [oggetto] - Lascia un oggetto nella stanza corrente\n"
            + ">> inserisci [oggetto] in [oggetto contenitore] - Permette di inserire un oggetto in un oggetto contenitore (non bloccato)\n\n"
            + "Altri comandi più specifici dovranno essere trovati dal giocatore.\n"
            + "N.B: Occhio agli oggetti non presenti nelle immagini!\n\n"
            + "SUGGERIMENTO:\nÈ possibile risalire ai comandi eseguiti posizionandosi sull'area di inserimento dei comandi.\n"
            + "   - Premendo la freccia in giù, è possibile scorrere i vari comandi eseguiti.\n\n"
            + "N.B: In caso in cui si carichi una partita esistente i comandi eseguiti verranno persi!\n"
            + "\n\n"
            + "Per salvare o caricare una partita, sovrascrivere il file TheLostHotel.dat\n";
    }

    /**
     * Funzione per spostarsi tra le stanze
     *
     * @param c tipo di comando che indica il movimento da compiere
     * @return stanza in cui spostarsi, se trovata, altrimenti null
     */
    private Room move(CommandType c) {

        // Se il comando corrisponde a quel movimento e la stanza in quella direzione esiste
        if (c.equals(CommandType.SUD) && !Objects.isNull(this.getGame().getCurrentRoom().getSouth())) {
            return this.getGame().getCurrentRoom().getSouth();
        } else if (c.equals(CommandType.NORD) && !Objects.isNull(this.getGame().getCurrentRoom().getNorth())) {
            return this.getGame().getCurrentRoom().getNorth();
        } else if (c.equals(CommandType.EST) && !Objects.isNull(this.getGame().getCurrentRoom().getEast())) {
            return this.getGame().getCurrentRoom().getEast();
        } else if (c.equals(CommandType.OVEST) && !Objects.isNull(this.getGame().getCurrentRoom().getWest())) {
            return this.getGame().getCurrentRoom().getWest();
        }

        return null;
    }


}
