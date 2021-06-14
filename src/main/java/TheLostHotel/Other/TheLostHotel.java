package TheLostHotel.Other;

import TheLostHotel.GUI.GameGUI;
import TheLostHotel.Parser.WordType;
import TheLostHotel.Type.CommandType;
import TheLostHotel.Parser.ParserOutput;
import TheLostHotel.Type.GameItem;
import TheLostHotel.Type.GameItemContainer;
import TheLostHotel.Type.Room;

import java.awt.*;
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
                case SOUTH:
                case NORD:
                case EAST:
                case WEST:

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
                        output.append("Temo di non aver capito la direzione...\n");
                    }
                    break;

                // Comando per guardare
                case HELP:
                    if(!pOutput.containsWordType(WordType.ERROR)) {
                        output.append(showHelp());
                    }
                    else
                        output.append("Forse intendevi \"aiuto\"?\n");
                    break;

                case LOOK:

                    // Se si vuole guardare un oggetto
                    if(!pOutput.containsWordType(WordType.ERROR)) {
                        if (pOutput.size() == 2) {

                            if(pOutput.containsWordType(WordType.INVENTORY_OBJ))
                                output.append(gameItem.getDescription() + "\n");
                            else if(pOutput.containsWordType(WordType.ROOM_OBJ)) {
                                if(gameItem instanceof GameItemContainer)
                                    output.append(gameItem.getDescription() + "\n");
                                else
                                    output.append("Osserva... cosa?\n");
                            }

                        } else if (pOutput.size() == 1) { // Se si vuole guardare la stanza

                            output.append(this.getGame().getCurrentRoom().getLookDescription() + "\n");

                        } /*else if (pOutput.size() > 2) { // Se si vogliono guardare troppi oggetti alla volta

                            output.append("Uno alla volta, ho una certa età.\n");

                        }*/
                    }
                    else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() > 2) {
                        output.append("Uno alla volta...\n");
                    }
                    else {
                        output.append("Osserva... cosa?\n");
                    }
                    break;

                case INVENTORY:
                    if(!pOutput.containsWordType(WordType.ERROR)) {
                        output.append("Oggetti presenti nell'inventario: " + this.getGame().getInventory().toString() + "\n");
                    }
                    else
                        output.append("Forse intendevi \"inventario\"?\n");
                    break;

                // Comandi per sbloccare stanze o contenitori
                case USE:

                    // Se si vuole aprire una stanza con un oggetto dell'inventario
                    if (pOutput.size() == 2 && pOutput.containsWordType(WordType.INVENTORY_OBJ)) {

                        //Apertura stanza
                        if (!gameItem.isConsumed() && this.unlockRoom(gameItem.getName())) {
                            output.append("Hai sbloccato la stanza!\n");

                            gameItem.consume();

                            // Se l'oggetto è stato consumato, lo rimuove dall'inventario
                            if (gameItem.isConsumed()) {

                                this.getGame().getInventory().remove(gameItem);
                                output.append("\nL'oggetto " + gameItem.getName() + " è stato rimosso.\n");

                            }

                        } else {
                            output.append("Non puoi usare questo oggetto ora/così!\n");
                        }

                    }
                    else
                        output.append("Usa... cosa?\n");
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
                            if ((gameItem != null && !gameItem.isConsumed() && ((GameItemContainer)iC).unlockContainer(gameItem.getName()))
                                    || (((GameItemContainer)iC).getLockedBy().equals(""))) {

                                if (((GameItemContainer)iC).getcItemList().getInventoryList().isEmpty()) {

                                    output.append("L'oggetto è stato aperto, ma è vuoto!\n");

                                } else {

                                    output.append("Hai aperto l'oggetto " + iC.getName()
                                            + "! Ecco il suo contenuto:" + iC.toString() + "\n");

                                }

                                if (gameItem != null) {

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

                        } else {
                            output.append("Apri... cosa?\n");
                        }

                     } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ)) {
                         output.append("Non puoi aprire quest'oggetto!\n"); //modificare (a volte può capitare che in
                         //altre stanze possa essere aperto
                     }
                     else {
                        output.append("Apri... cosa?\n");
                    }

                    break;

                // Comando per raccogliere oggetti
                case PICK_UP:

                    // Controlla che l'oggetto sia della stanza e non presente nell'inventario
                    if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.size() == 2) {

                        // Controlla che l'oggetto si possa raccogliere
                        if (!Objects.isNull(gameItem) && gameItem.isPickupable()) {

                            // Aggiunge l'oggetto all'inventario e lo rimuove dalla stanza
                            this.getGame().getInventory().add(gameItem);
                            this.getGame().getCurrentRoom().removeItem(gameItem);

                            output.append("L'oggetto è stato aggiunto al tuo inventario.\n");

                        } else {

                            output.append("Prendere... cosa?\n");

                        }
                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() == 2) {

                        output.append("L'oggetto è già presente nel tuo inventario!\n");

                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() > 2) {

                        output.append("Uno alla volta...\n");

                    } else{

                        output.append("Prendere... cosa?\n");

                    }
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

        } /*else if (!Objects.isNull(this.getGame().getCurrentRoom().getUp())
                && this.getGame().getCurrentRoom().getUp().getLockedBy().equals(iName)) {

            this.getGame().getCurrentRoom().getUp().setLockedBy("");
            flag = true;

        } else if (!Objects.isNull(this.getGame().getCurrentRoom().getDown())
                && this.getGame().getCurrentRoom().getDown().getLockedBy().equals(iName)) {

            this.getGame().getCurrentRoom().getDown().setLockedBy("");
            flag = true;

        }*/

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
            + ">> salva - Salva la partita corrente\n"
            + ">> esci - Permette di ritornare al menù principale ed eventualmente salvare una partita\n"
            + ">> inventario - Consente di visualizzare l'inventario con i relativi oggetti\n"
            + "\n"
            + "Altri comandi:\n"
            + "\n"
            + ">> aiuto - Consente di visualizzare l'elenco dei comando riconosciuti\n"
            + ">> osserva - Permette di guardarti intorno ed esaminare l'ambiente circostante\n"
            + ">> osserva [oggetto/oggetto contenitore] - Permette di esaminare un oggetto dell'inventario o della stanza corrente\n"
            + ">> usa [oggetto] -  Usa oggetti del tuo inventario\n"
            + ">> apri [oggetto contenitore] - Apri un oggetto contenitore\n"
            + ">> apri [oggetto contenitore] con [oggetto] - Apri un oggetto contenitore bloccato con un oggetto\n"
            + ">> prendi [oggetto] - Prendi un oggetto a terra nella stanza o in un contenitore\n"
            + ">> lascia [oggetto] - Lascia un oggetto in una stanza\n"
            + ">> metti [oggetto] in [oggetto contenitore] - Metti un oggetto in un contenitore valido\n\n" //da vedere se inserire o meno
            + "Altri comandi più specifici dovranno essere trovati dal giocatore.\n"
            + "\n"
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
        if (c.equals(CommandType.SOUTH) && !Objects.isNull(this.getGame().getCurrentRoom().getSouth())) {
            return this.getGame().getCurrentRoom().getSouth();
        } else if (c.equals(CommandType.NORD) && !Objects.isNull(this.getGame().getCurrentRoom().getNorth())) {
            return this.getGame().getCurrentRoom().getNorth();
        } else if (c.equals(CommandType.EAST) && !Objects.isNull(this.getGame().getCurrentRoom().getEast())) {
            return this.getGame().getCurrentRoom().getEast();
        } else if (c.equals(CommandType.WEST) && !Objects.isNull(this.getGame().getCurrentRoom().getWest())) {
            return this.getGame().getCurrentRoom().getWest();
        }

        return null;
    }


}
