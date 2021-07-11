package tlh.Other;

import tlh.Parser.WordType;
import tlh.Type.CommandType;
import tlh.Parser.ParserOutput;
import tlh.Type.GameItem;
import tlh.Type.GameItemContainer;
import tlh.Type.Room;

import javax.swing.*;
import java.util.Iterator;
import java.util.Objects;

public class TheLostHotel extends GameManager {

    /**
     * Costruttore.
     *
     * @param game
     * @param url per caricare la musica.
     */
    public TheLostHotel(final GameDescription game, String url) {
        super(game, url);
    }

    /**
     * Metodo ereditato dalla classe astratta GameManager
     * per l'esecuzione del comando dell'utente.
     * Verifica se è corretto rispetto alla logica dello stato attuale del gioco.
     *
     * @param pOutput input dell'utente ridefinito e strutturato dal Parser.
     * @return stringa di risposta per l'utente.
     */
    @Override
    protected String executeCommand(final ParserOutput pOutput) {

        // Prende il tipo di comando in modo da gestire la richiesta
        CommandType command = this.getGame().getCommandType(pOutput.getString(WordType.COMMAND));

        Room room = null;
        GameItem gameItem = null;
        StringBuilder output = new StringBuilder();
        boolean endGame = false;

        // Se un singolo item viene sfruttato nella richiesta lo salva.
        gameItem = this.findGameItem(pOutput);

        try {
            switch (command) {

                case NORD:
                case EST:
                case SUD:
                case OVEST:

                    // Controlla se ci si può spostare in quella direzione o meno.
                    if (!pOutput.containsWordType(WordType.ERROR)) {
                        room = this.move(command);

                        if (Objects.isNull(room)) {
                            output.append("Non puoi andare lì!\n");
                        } else if (room.getLockedBy().equals("")) { // Controlla che non sia bloccata.

                            if (!room.isImpossibleToAccessDirectly()) {

                                if (this.getGame().getCurrentRoom().isAnOpenDoor() && command.name().equalsIgnoreCase("sud") && !room.isVisited()) {
                                    output.append("Questa stanza è chiusa!\n");
                                } else {
                                    // Si sposta nella stanza designata.
                                    boolean winGame = false;

                                    for (GameItem g : this.getGame().getInventory().getInventoryList()) {
                                        if (g.isKeyToWin()) {
                                            winGame = true;
                                        }
                                    }

                                    this.getGame().setCurrentRoom(room);

                                    if(!winGame)
                                    {
                                        if(this.getGame().getCurrentRoom().getName().equals("Stanza 13"))
                                        {
                                            this.getMusic().getClip().stop();
                                            this.getMusic().playSound("resources//music//soundtrackRoom13.wav");
                                            this.getGame().getCurrentRoom().setRoomImage(new ImageIcon(
                                                    Description.PATH_ROOM13_LOSE));
                                            this.getGame().getCurrentRoom().setDescription("Una volta entrato nella stanza vieni accerchiato "
                                                    + "da tre uomini armati. Noti con la coda dell'occhio John accasciato al suolo e Ethan "
                                                    + "circondato e con le pistole puntate addosso. \n"
                                                    + "\"Ci hanno scambiati per ladri e hanno ucciso il nostro amico.\", esclama Ethan.\n"
                                                    + "Con un fucile sarebbe andata diversamente...\n"
                                                    + "Venite portati via e arrestati dagli uomini in bianco.\n\n"
                                                    + "RICEVERAI UNA PENALIZZAZIONE (1 ora).");
                                            //fine gioco

                                            endGame = true;
                                            this.getGame().getgTime().setSecondPassed(this.getGame().getgTime().getSecondPassed() + 3600); //penalita di 1 ora

                                        }
                                    } else
                                    {
                                        if(this.getGame().getCurrentRoom().getName().equals("Stanza 13")) {
                                            this.getMusic().getClip().stop();
                                            this.getMusic().playSound("resources//music//soundtrackRoom13.wav");
                                        }
                                    }

                                    output.append("-- " + this.getGame().getCurrentRoom().getName() + " --" + "\n\n");

                                    if (!room.isVisited()) {
                                        output.append(this.getGame().getCurrentRoom().getDescription() + "\n");
                                    } else {
                                        output.append(this.getGame().getCurrentRoom().getVisitedDescription() + "\n");
                                    }
                                    room.setVisited(true);

                                    if(endGame) {
                                        output.append(endGame());
                                    }
                                }

                            } else {
                                if (room.getName().equals("Giardino") && command.name().equalsIgnoreCase("ovest") && this.getGame().getCurrentRoom().getName().equals("Stanza 63")) {
                                    this.getGame().setCurrentRoom(room);

                                    output.append("-- " + this.getGame().getCurrentRoom().getName() + " --" + "\n\n");

                                    if (!room.isVisited()) {
                                        output.append(this.getGame().getCurrentRoom().getDescription() + "\n");
                                    } else {
                                        output.append(this.getGame().getCurrentRoom().getVisitedDescription() + "\n");
                                    }
                                    room.setVisited(true);
                                } else {
                                    output.append("Non puoi andare lì!\n");
                                }
                            }

                        } else {
                            if (room.isAnOpenDoor() && command.name().equalsIgnoreCase("sud")) {
                                this.getGame().setCurrentRoom(room);

                                output.append("-- " + this.getGame().getCurrentRoom().getName() + " --" + "\n\n");

                                if (!room.isVisited()) {
                                    output.append(this.getGame().getCurrentRoom().getDescription() + "\n");
                                } else {
                                    output.append(this.getGame().getCurrentRoom().getVisitedDescription() + "\n");
                                }
                                room.setVisited(true);
                            } else {
                                output.append("Questa stanza è chiusa!\n");
                            }
                        }
                    } else {
                        String direction = command.toString();
                        output.append("Forse intendevi \"" + direction.toLowerCase() + "\"?\n");
                    }
                    break;

                // Comando per visualizzare gli oggetti presenti nell'inventario.
                case INVENTORY:
                    if (!pOutput.containsWordType(WordType.ERROR)) {
                        if (!this.getGame().getInventory().getInventoryList().isEmpty()) {
                            output.append("Oggetti presenti nell'inventario: " + this.getGame().getInventory().toString() + "\n");
                        } else {
                            output.append(this.getGame().getInventory().toString());
                        }
                    } else {
                        output.append("Forse intendevi \"inventario\"?\n");
                    }
                    break;

                // Comando per visualizzare i comandi riconosciuti dal programma.
                case HELP:
                    if (!pOutput.containsWordType(WordType.ERROR) && !pOutput.containsWordType(WordType.INVENTORY_OBJ) && !pOutput.containsWordType(WordType.ROOM_OBJ)) {
                        output.append(showHelp());
                    } else {
                        output.append("Forse intendevi \"aiuto\"?\n");
                    }
                    break;

                // Se si vuole guardare un oggetto.
                case LOOK:

                    if (!pOutput.containsWordType(WordType.ERROR)) {
                        if (pOutput.size() == 2) {
                            if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && !gameItem.isGIPassword()) {
                                output.append(gameItem.getDescription() + "\n");
                            } else if (pOutput.containsWordType(WordType.ROOM_OBJ)) {
                                if (gameItem instanceof GameItemContainer) {
                                    if (((GameItemContainer) gameItem).isMovable()) {
                                        if (!((GameItemContainer) gameItem).isMoved()) {
                                            output.append(gameItem.getDescription() + "\n");
                                        } else {
                                            output.append(((GameItemContainer) gameItem).getMovedDescription() + "\n");
                                        }
                                    } else {
                                        if (((GameItemContainer) gameItem).isClosed()) {
                                            if (((GameItemContainer) gameItem).isPasswordLocked()) {
                                                if (((GameItemContainer) gameItem).isPasswordUnlocked()) {
                                                    output.append("Hai trovato la combinazione. Che aspetti ad aprirla?\n");
                                                } else {
                                                    output.append(gameItem.getDescription() + "\n");
                                                }
                                            } else {
                                                if (gameItem.getName().equals("cambiamonete")) {
                                                    if (((GameItemContainer) gameItem).isItemAlreadyIntoIC()) {
                                                        output.append("Il cambiamonete in cui hai inserito il gettone. Hai sbancato!\n");
                                                    } else {
                                                        output.append(gameItem.getDescription() + "\n");
                                                    }
                                                } else {
                                                    output.append(gameItem.getDescription() + "\n");
                                                }
                                            }
                                        } else {
                                            if (((GameItemContainer) gameItem).isPasswordLocked()) {
                                                if (!((GameItemContainer) gameItem).isPasswordUnlocked()) {
                                                    output.append(((GameItemContainer) gameItem).getDescription() + "\n");
                                                }
                                                else {
                                                    output.append(((GameItemContainer) gameItem).getPasswordUnlockedDescription() + "\n");
                                                }
                                            } else
                                                output.append(((GameItemContainer) gameItem).getOpenedDescription() + "\n");
                                        }
                                    }

                                    if (gameItem.getName().equals("armadio") && ((GameItemContainer) gameItem).isClosed()) {
                                        output.append("\nN.B: Alcuni oggetti contenitori necessitano di un oggetto per essere aperti.\n"
                                                + "Per sbloccarli è necessario inserire il comando:\n\"apri [oggetto contenitore] con [oggetto]\"\n");
                                    }

                                    if (gameItem.getName().equals("quadro") && !((GameItemContainer) gameItem).isMoved() && this.getGame().getCurrentRoom().getName().equals("Stanza 79")) {
                                        output.append("\nN.B: Alcuni oggetti possono essere spostati con il comando:\n"
                                                + "sposta [oggetto da spostare]\n"
                                                + "Potresti trovarci qualcosa.\n");
                                    }
                                } else if (gameItem.isPickupable()) {
                                    output.append("Devi prendere l'oggetto prima di poterlo esaminare!\n");
                                }
                                else if(gameItem.isPerson())
                                {
                                    output.append(gameItem.getDescription() + "\n");
                                }
                                else {
                                    output.append("Specifica correttamente l'oggetto che vuoi esaminare, "
                                            + "altrimenti digita \"osserva\" per guardarti intorno.\n");
                                }
                            } else if (gameItem.isGIPassword()) {
                                output.append("Specifica correttamente l'oggetto che vuoi esaminare, "
                                        + "altrimenti digita \"osserva\" per guardarti intorno.\n");
                            }

                        } else if (pOutput.size() == 1) { // Se si vuole guardare la stanza.
                            output.append(this.getGame().getCurrentRoom().getLookDescription() + "\n");
                        } else if (pOutput.size() > 2) {
                            output.append("Uno alla volta...\n");
                        }
                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() > 2) {
                            output.append("Uno alla volta...\n");
                    } else {
                            output.append("Specifica correttamente l'oggetto che vuoi esaminare, "
                            + "altrimenti digita \"osserva\" per guardarti intorno.\n");
                    }
                    break;

                // Comandi per usare oggetti / oggetti contenitori.
                case USE:

                    if (pOutput.containsWordType(WordType.ROOM_OBJ) && !gameItem.isUsableWithDrops()) {
                        if (gameItem instanceof GameItemContainer) {
                            if (((GameItemContainer) gameItem).isUsableWithItem()) {
                                if (pOutput.size() == 2) {
                                    if (!gameItem.isUsed() && !((GameItemContainer) gameItem).getcItemList().getInventoryList().isEmpty()) {
                                        output.append(gameItem.getDescriptionUsableWithDrops() + "\n");
                                        gameItem.setUsed(true);
                                    } else if (!gameItem.isUsed() && ((GameItemContainer) gameItem).getcItemList().getInventoryList().isEmpty()) {
                                        output.append(gameItem.getDescriptionUsableButItemRemoved() + "\n");
                                    } else {
                                        output.append(gameItem.getDescriptionAlreadyUsedWithDrops() + "\n");
                                    }
                                } else {
                                    output.append("Specifica correttamente l'oggetto che vuoi usare.\n");
                                }

                            } else if (pOutput.size() == 2) {
                                if (((GameItemContainer) gameItem).isSecretAccess()) {
                                    if (((GameItemContainer) gameItem).isClosed()) {
                                        output.append("Non puoi usare questo oggetto (ora/così).\n");
                                    } else {
                                        int continueGame = 0; //verifica se ha i due oggetti principali per proseguire

                                        for (GameItem g : this.getGame().getInventory().getInventoryList()) {
                                            if (g.isIndispensable() && g.isItemCorrectlyAdded()) {
                                                continueGame ++;
                                            }
                                        }

                                        if (continueGame == 2) { //MAPPA DELLA CASSAFORTE DELLA HALL E FOTO DEL FISSO DELLA CCTV
                                            this.getGame().setCurrentRoom(this.getGame().getCurrentRoom().getNorth());

                                            output.append("-- " + this.getGame().getCurrentRoom().getName() + " --" + "\n\n");

                                            if (!this.getGame().getCurrentRoom().isVisited()) {
                                                output.append(this.getGame().getCurrentRoom().getDescription() + "\n");
                                            } else {
                                                output.append(this.getGame().getCurrentRoom().getVisitedDescription() + "\n");
                                            }

                                            this.getGame().getCurrentRoom().setVisited(true);
                                        } else {
                                            output.append("ATTENZIONE: Prima di proseguire sono necessari alcuni oggetti chiave. "
                                                    + "Controlla di aver tutto con te, non si potrà più tornare indietro!\n");
                                        }
                                    }
                                } else if(gameItem.getName().equals("pianoforte"))
                                {
                                    this.getMusic().getClip().stop();
                                    this.getMusic().playSound("resources//music//soundtrackPiano.wav");
                                    output.append("Ah, sei anche un pianista? Complimenti!\n");
                                }
                                else if(gameItem.getName().equals("chitarra"))
                                {
                                    this.getMusic().getClip().stop();
                                    this.getMusic().playSound("resources//music//soundtrackGuitar.wav");
                                    output.append("Hai avuto un passato da chitarrista?\n");
                                }
                                else {
                                    output.append("Non puoi usare questo oggetto (ora/così).\n");
                                }
                            } else {
                                output.append("Specifica correttamente l'oggetto che vuoi usare.\n");
                            }

                        } else if (gameItem.isPickupable()) {
                            output.append("Devi prendere l'oggetto prima di poterlo usare!\n");
                        } else {
                            output.append("Specifica correttamente l'oggetto che vuoi usare.\n");
                        }
                    } else if (pOutput.size() == 2 && pOutput.containsWordType(WordType.INVENTORY_OBJ)) { // Se si vuole aprire una stanza con un oggetto dell'inventario

                        //Apertura stanza.
                        if (!gameItem.isConsumed() && this.unlockRoom(gameItem.getName())) {
                            output.append("Hai sbloccato la stanza!\n");

                            gameItem.consume();

                            // Se l'oggetto è stato consumato, lo rimuove dall'inventario.
                            if (gameItem.isConsumed()) {
                                this.getGame().getInventory().remove(gameItem);
                                output.append("\nL'oggetto " + gameItem.getName() + " è stato rimosso.\n");
                            }

                            if (gameItem.getName().equals("chiave")) {
                                output.append("\nN.B: Il tutorial è terminato.\n"
                                        + "Adesso dipende solo da te!\n"); //oppure "Buona fortuna!"
                            }

                        } else if(gameItem.getName().equals("cellulare") && this.getGame().getCurrentRoom().getName().equals("CCTV"))
                        {
                            boolean continua = false;
                            //da vedere se hai inserito la chiavetta nel fisso
                            for (GameItem g : this.getGame().getCurrentRoom().getItemList().getInventoryList()) {
                                if(g instanceof GameItemContainer)
                                {
                                    if (g.getName().equals("fisso") && !((GameItemContainer) g).getcItemList().getInventoryList().isEmpty()) {
                                        continua = true;
                                    }
                                }
                            }
                            if(continua)
                            {
                                for (GameItem g : this.getGame().getInventory().getInventoryList()) {
                                    if (g.getName().equals("foto")) {
                                        g.setItemCorrectlyAdded(true);
                                    }
                                }

                                output.append("Hai scattato la foto allo schermo!\n");

                            }
                            else
                            {
                                output.append("Non puoi usare questo oggetto ora/così!\n");
                            }
                        }
                        else if(this.getGame().getCurrentRoom().getName().equals("Stanza 13") && pOutput.getString(WordType.INVENTORY_OBJ).equals("fucile"))
                        {
                            this.getGame().getCurrentRoom().setRoomImage(new ImageIcon(
                                    Description.PATH_ROOM13_WIN));
                            output.append("Hai sparato in aria e hai fatto scappare due dei tre uomini che tenevano in ostaggio Ethan. "
                                    + "L'altro, ha cercato di fare lo spavaldo della situazione ed hai vendicato il tuo amico. "
                                    + "Non potevano delle pistoline avere la meglio su un fucile del genere.\n\n");
                            //fine gioco.
                            output.append(endGame());
                        }
                        else if (gameItem.isGIPassword()) {
                            output.append("Specifica correttamente l'oggetto che vuoi usare.\n");
                        } else {
                            output.append("Non puoi usare questo oggetto ora/così!\n");
                        }

                    } else if (pOutput.containsWordType(WordType.ROOM_OBJ) && gameItem.isUsableWithDrops()) {
                        if (gameItem instanceof GameItemContainer) {
                            ((GameItemContainer) gameItem).getcItemList().getInventoryList().removeIf(GameItem::isPicked);

                            if (!gameItem.isUsed()) {
                                output.append(gameItem.getDescriptionUsableWithDrops() + "\n");
                            } else if (gameItem.isUsed() && ((GameItemContainer) gameItem).getcItemList().getInventoryList().isEmpty()) {
                                output.append(gameItem.getDescriptionAlreadyUsedWithDrops() + "\n");
                            } else {
                                output.append("Forse hai dimenticato qualcosa...\n");
                            }

                            for (GameItem g : ((GameItemContainer) gameItem).getcItemList().getInventoryList()) {
                                g.setPickupable(true);
                            }
                        }
                        gameItem.setUsed(true);

                    } else if (pOutput.size() == 1) {
                        output.append("Specifica l'oggetto che vuoi usare.\n");
                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() > 2) {
                        output.append("Uno alla volta...\n");
                    } else {
                        output.append("Specifica correttamente l'oggetto che vuoi usare.\n");
                    }
                    break;

                // Apertura di un contenitore da sbloccare.
                case OPEN:

                    if (pOutput.containsWordType(WordType.ROOM_OBJ)) {

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
                            } else if (index == 2 && it.next().equals(WordType.INVENTORY_OBJ)) { //Chiave. obbligatoriamente un oggetto dell'inv.
                                gameItem = this.getGame().getInventory().searchItem(pOutput.getString(WordType.INVENTORY_OBJ));
                            }
                            index++;
                        }

                        // Se l'oggetto è effettivamente un contenitore.
                        if (iC instanceof GameItemContainer) {

                            // Se trova l'oggetto per aprirlo ed è corretto oppure se il contenitore non è bloccato lo apre.
                            if (!((GameItemContainer) iC).isMovable()) {
                                if (((GameItemContainer) iC).getLockedBy().equals("")) {
                                    if (!pOutput.containsWordType(WordType.ERROR)) {
                                        if (((GameItemContainer) iC).getcItemList().getInventoryList().isEmpty()) {

                                            if (((GameItemContainer) iC).isiCNotOpenable()) {
                                                output.append("Non puoi aprire quest'oggetto!\n");
                                            } else {

                                                if (((GameItemContainer) iC).isSecretAccess()) {
                                                    output.append("Hai già aperto questo oggetto! \n");
                                                } else {
                                                    if (iC.getName().equals("finestra")) {
                                                        this.getGame().setCurrentRoom(this.getGame().getCurrentRoom().getWest());

                                                        output.append("-- " + this.getGame().getCurrentRoom().getName() + " --" + "\n\n");

                                                        if (!this.getGame().getCurrentRoom().isVisited()) {
                                                            output.append(this.getGame().getCurrentRoom().getDescription() + "\n");
                                                        } else {
                                                            output.append(this.getGame().getCurrentRoom().getVisitedDescription() + "\n");
                                                        }

                                                        this.getGame().getCurrentRoom().setVisited(true);
                                                    } else {
                                                        output.append("L'oggetto è stato aperto, ma è vuoto!\n");
                                                    }
                                                }

                                                if (iC.getName().equals("cestino") && ((GameItemContainer) iC).isClosed() && !this.getGame().getCurrentRoom().getEast().getNorth().isVisited()) {
                                                    output.append("\nN.B: Non in tutte le stanze sono presenti degli oggetti...\n"
                                                            + "In altre dovrai essere tu in grado di trovarli!");
                                                }

                                                ((GameItemContainer) iC).setClosed(false);
                                            }

                                        } else {

                                            if (!((GameItemContainer) iC).isiCNotOpenable()) {
                                                if(!((GameItemContainer) iC).isPasswordLocked()) {
                                                    if (((GameItemContainer) iC).isClosed()) {
                                                        output.append("Hai aperto l'oggetto " + iC.getName()
                                                                + "! Ecco il suo contenuto:" + iC.toString() + "\n");
                                                    } else {
                                                        output.append("Hai aperto l'oggetto " + iC.getName()
                                                                + " ma ci sono oggetti \n "
                                                                + "non ancora raccolti: " + iC.toString() + "\n");
                                                    }
                                                } else {
                                                    if (!((GameItemContainer) iC).isPasswordUnlocked()) {
                                                        output.append("È necessario inserire una password "
                                                                + "per sbloccare la " + iC.getName() + "!\n");
                                                    } else {
                                                        output.append("Hai aperto l'oggetto " + iC.getName()
                                                                + "! Ecco il suo contenuto:" + iC.toString() + "\n");
                                                    }
                                                }
                                            } else {
                                                output.append("Non puoi aprire quest'oggetto!\n");
                                            }

                                            if (iC.getName().equals("mobile") && ((GameItemContainer) iC).isClosed()) {
                                                output.append("\nN.B: Non sempre gli oggetti saranno visibili nelle varie stanze. Sfrutta nel migliore dei modi "
                                                        + "tutti i comandi per cercarli tutti... (Premi CTRL-L per visualizzare i comandi)\n");
                                            }

                                            if (((GameItemContainer) iC).isPasswordLocked()) {

                                                if (((GameItemContainer) iC).isPasswordUnlocked()) {
                                                    for (GameItem g : ((GameItemContainer) iC).getcItemList().getInventoryList()) {
                                                        g.setPickupable(true);
                                                    }
                                                }
                                            } else {
                                                for (GameItem g : ((GameItemContainer) iC).getcItemList().getInventoryList()) {
                                                    g.setPickupable(true);
                                                }
                                            }

                                            ((GameItemContainer) iC).setClosed(false);
                                        }
                                    } else if (((GameItemContainer) iC).isiCNotOpenable()) {
                                        output.append("Non puoi aprire quest'oggetto!\n");
                                    } else {
                                        if (((GameItemContainer) iC).isPasswordLocked()) {
                                            if (((GameItemContainer) iC).isPasswordUnlocked()) {
                                                output.append("L'oggetto " + iC.getName() + " è sbloccato!\n");
                                            } else {
                                                output.append("È necessario inserire una password "
                                                        + "per sbloccare la " + iC.getName() + "!\n");
                                            }
                                        } else {
                                            output.append("L'oggetto " + iC.getName() + " è sbloccato!\n");
                                        }
                                    }
                                } else {
                                    if (!pOutput.containsWordType(WordType.CON)) {
                                        output.append("Non puoi aprire quest'oggetto così!\n");
                                    } else if ((gameItem != null && !gameItem.isConsumed() && ((GameItemContainer) iC).unlockContainer(gameItem.getName()))) {
                                        if (((GameItemContainer) iC).getcItemList().getInventoryList().isEmpty()) {

                                            if (((GameItemContainer) iC).isSecretAccess()) {
                                                output.append("Chi lo avrebbe mai detto che un gancio aprisse una ventola?\n"
                                                        + "Un passaggio segreto appare davanti ai tuoi occhi.\n");
                                            } else {
                                                output.append("L'oggetto è stato aperto, ma è vuoto!\n");
                                            }

                                            ((GameItemContainer) iC).setClosed(false);

                                        } else {

                                            output.append("Hai aperto l'oggetto " + iC.getName()
                                                    + "! Ecco il suo contenuto:" + iC.toString() + "\n");

                                            for (GameItem g : ((GameItemContainer) iC).getcItemList().getInventoryList()) {
                                                g.setPickupable(true);
                                            }

                                            ((GameItemContainer) iC).setClosed(false);
                                        }
                                        gameItem.consume();

                                        // Se l'oggetto è stato consumato, lo rimuove dall'inventario.
                                        if (gameItem.isConsumed()) {
                                            this.getGame().getInventory().remove(gameItem);
                                            output.append("\nL'oggetto " + gameItem.getName() + " è stato rimosso.\n");
                                        }
                                    } else {
                                        output.append("Non puoi aprire quest'oggetto così!\n");
                                    }

                                }
                            } else {
                                output.append("Non puoi aprire quest'oggetto!\n");
                            }

                        } else {
                            output.append("Specifica correttamente l'oggetto che vuoi aprire.\n");
                        }

                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && !gameItem.isGIPassword()) {
                        output.append("Non puoi aprire quest'oggetto!\n");
                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && gameItem.isGIPassword()) {
                        output.append("Specifica correttamente l'oggetto che vuoi aprire.\n");
                    } else if (pOutput.size() == 1) {
                        output.append("Specifica l'oggetto che vuoi aprire.\n");
                    } else {
                        output.append("Specifica correttamente l'oggetto che vuoi aprire.\n");
                    }

                    break;

                // Comando per spostare un oggetto.
                case MOVE:
                    if (pOutput.containsWordType(WordType.ROOM_OBJ)) {

                        GameItem iC = null; //contenitore
                        gameItem = null;
                        byte index = 0;

                        // Iteratore per ciclare sul ParserOutput
                        Iterator<WordType> it = pOutput.iterator();
                        it.next(); // Salta il comando iniziale, già conosciuto

                        // Salva gli oggetti che devono interagire nell'ordine prestabilito (e.g. "Apri baule con chiave").
                        while (it.hasNext()) {
                            //itemContainer. obbligatoriamente un oggetto della Room.
                            if (index == 1 && it.next().equals(WordType.ROOM_OBJ)) {
                                iC = this.getGame().getCurrentRoom().getItemList().searchItem(pOutput.getString(WordType.ROOM_OBJ));
                            }
                            index++;
                        }
                        if (iC instanceof GameItemContainer) {

                            // Se trova l'oggetto per aprirlo ed è corretto oppure se il contenitore non è bloccato lo apre.
                            if (((GameItemContainer) iC).isMovable() && pOutput.size() == 2) {
                                if (!pOutput.containsWordType(WordType.ERROR) && !pOutput.containsWordType(WordType.INVENTORY_OBJ)) {
                                    if (((GameItemContainer) iC).getcItemList().getInventoryList().isEmpty()) {
                                        output.append("L'oggetto è stato spostato ma non c'è nulla!\n");
                                    } else {

                                        if (!((GameItemContainer) iC).isMoved()) {
                                            output.append("Hai spostato l'oggetto " + iC.getName()
                                                    + "! Hai trovato:" + iC.toString() + "\n");
                                        } else {
                                            output.append("Hai spostato l'oggetto " + iC.getName()
                                                    + " ma ci sono oggetti\n"
                                                    + "non ancora raccolti: " + iC.toString() + "\n");
                                        }

                                        for (GameItem g : ((GameItemContainer) iC).getcItemList().getInventoryList()) {
                                            g.setPickupable(true);
                                        }

                                    }
                                    ((GameItemContainer) iC).setMoved(true);
                                } else {
                                    output.append("Specifica correttamente l'oggetto che vuoi spostare.\n");
                                }
                            } else if (pOutput.size() > 2) {
                                output.append("Specifica correttamente l'oggetto che vuoi spostare.\n");
                            } else {
                                output.append("Non puoi spostare questo oggetto!\n");
                            }
                        } else {
                            output.append("Specifica correttamente l'oggetto che vuoi spostare.\n");
                        }
                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && !gameItem.isGIPassword() && pOutput.size() == 2) {
                        output.append("Non puoi spostare questo oggetto!\n");
                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && gameItem.isGIPassword() && pOutput.size() == 2) {
                        output.append("Specifica correttamente l'oggetto che vuoi spostare.\n");
                    } else if (pOutput.size() == 1) {
                        output.append("Specifica l'oggetto che vuoi spostare.\n");
                    } else {
                        output.append("Specifica correttamente l'oggetto che vuoi spostare.\n");
                    }
                    break;

                // Comando per raccogliere oggetti
                case PICK_UP:

                    // Controlla che l'oggetto sia della stanza e non presente nell'inventario.
                    if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.size() == 2) {

                        // Controlla che l'oggetto si possa raccogliere.
                        if (!Objects.isNull(gameItem) && gameItem.isPickupable()) {

                            if (!this.getGame().getInventory().isFull()) {
                                // Aggiunge l'oggetto all'inventario e lo rimuove dalla stanza.
                                this.getGame().getInventory().add(gameItem);
                                this.getGame().getCurrentRoom().removeItem(gameItem);

                                if (gameItem.isIndispensable()) {
                                    gameItem.setItemCorrectlyAdded(true);
                                }

                                gameItem.setPicked(true);
                                output.append("L'oggetto è stato aggiunto al tuo inventario.\n");
                            } else {
                                output.append("L'inventario è pieno!\n");
                            }

                        } else {

                            if (gameItem instanceof GameItemContainer) {
                                output.append("Non puoi prendere questo oggetto!\n");
                            } else {
                                output.append("Specifica correttamente l'oggetto che vuoi prendere.\n");
                            }
                        }

                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && !gameItem.isGIPassword() && pOutput.size() == 2) {
                        output.append("L'oggetto è già presente nel tuo inventario!\n");
                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && gameItem.isGIPassword() && pOutput.size() == 2) {
                        output.append("Specifica correttamente l'oggetto che vuoi prendere.\n");
                    } else if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.size() > 2) {

                        if (gameItem.isPickupable()) {
                            output.append("Uno alla volta...\n");
                        } else {
                            output.append("Specifica correttamente l'oggetto che vuoi prendere.\n");
                        }

                    } else if (pOutput.size() == 1) {
                        output.append("Specifica l'oggetto che vuoi prendere.\n");
                    } else {
                        output.append("Specifica correttamente l'oggetto che vuoi prendere.\n");
                    }
                    break;

                case DROP:
                    // Controlla che l'oggetto sia presente nell'inventario.
                    if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && !gameItem.isGIPassword() && pOutput.size() == 2) {

                        // Controlla che l'oggetto si possa lasciare.
                        if (!Objects.isNull(gameItem)) {

                            // Aggiunge l'oggetto all'inventario e lo rimuove dalla stanza.
                            this.getGame().getInventory().remove(gameItem);
                            this.getGame().getCurrentRoom().addItem(gameItem);

                            gameItem.setPickupable(true);

                            output.append("Hai lasciato l'oggetto " + gameItem.getName() + ".\n");

                        } else {
                            output.append("Specifica correttamente l'oggetto che vuoi lasciare.\n");
                        }
                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && gameItem.isGIPassword() && pOutput.size() == 2) {
                        if(gameItem.getName().equals("foto"))
                        {
                            this.getGame().getInventory().remove(gameItem);
                            this.getGame().getCurrentRoom().addItem(gameItem);

                            gameItem.setPickupable(true);

                            output.append("Hai lasciato l'oggetto " + gameItem.getName() + ".\n");
                        }
                        else
                            output.append("Specifica correttamente l'oggetto che vuoi lasciare.\n");
                    } else if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.size() == 2) {

                        if (gameItem instanceof GameItemContainer) {
                            output.append("Non puoi lasciare questo oggetto!\n");
                        } else {
                            output.append("Specifica correttamente l'oggetto che vuoi lasciare.\n");
                        }

                    } else if (pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() > 2) {
                        output.append("Uno alla volta...\n");
                    } else if (pOutput.size() == 1) {
                        output.append("Specifica l'oggetto che vuoi lasciare.\n");
                    } else {
                        output.append("Specifica correttamente l'oggetto che vuoi lasciare.\n");
                    }
                    break;

                case INSERT:
                    if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.containsWordType(WordType.INVENTORY_OBJ) && pOutput.size() == 5) {

                        GameItem iC = null; //contenitore
                        gameItem = null;
                        byte index = 0;

                        // Iteratore per ciclare sul ParserOutput
                        Iterator<WordType> it = pOutput.iterator();
                        it.next(); // Salta il comando iniziale, già conosciuto

                        // Salva gli oggetti che devono interagire nell'ordine prestabilito (e.g. "Apri baule con chiave")
                        while (it.hasNext()) {
                            //itemContainer, obbligatoriamente un oggetto della Room.
                            if (index == 1 && it.next().equals(WordType.INVENTORY_OBJ)) {
                                gameItem = this.getGame().getInventory().searchItem(pOutput.getString(WordType.INVENTORY_OBJ));
                            } else if (index == 2 && it.next().equals(WordType.ROOM_OBJ)) {
                                iC = this.getGame().getCurrentRoom().getItemList().searchItem(pOutput.getString(WordType.ROOM_OBJ));
                            }
                            index++;
                        }
                        if (iC instanceof GameItemContainer) {
                            // Se trova l'oggetto per aprirlo ed è corretto
                            // oppure se il contenitore non è bloccato lo apre.
                            if (((GameItemContainer) iC).isUsableWithItem()) {
                                if (pOutput.containsWordType(WordType.ERROR)) {
                                    if (pOutput.containsWordType(WordType.IN)) {
                                        if (iC.getName().equals("telecomando") && gameItem.getName().equals("batterie")) {
                                            output.append("Hai inserito l'oggetto " + gameItem.getName()
                                                    + " in " + iC.getName() + ".\n");

                                            ((GameItemContainer) iC).add(gameItem);
                                            this.getGame().getInventory().remove(gameItem);

                                            gameItem.consume();

                                            // Se l'oggetto è stato consumato, lo rimuove dall'inventario.
                                            if (gameItem.isConsumed()) {
                                                this.getGame().getInventory().remove(gameItem);
                                                output.append("\nL'oggetto " + gameItem.getName() + " è stato rimosso.\n");
                                            }

                                        } else if (iC.getName().equals("radio") && gameItem.getName().equals("batterie") && ((GameItemContainer) iC).getLockedBy().equals("")) {
                                            output.append("Hai inserito l'oggetto " + gameItem.getName()
                                                    + " in " + iC.getName() + ".\n");

                                            ((GameItemContainer) iC).add(gameItem);
                                            this.getGame().getInventory().remove(gameItem);

                                            gameItem.consume();

                                            // Se l'oggetto è stato consumato, lo rimuove dall'inventario
                                            if (gameItem.isConsumed()) {
                                                this.getGame().getInventory().remove(gameItem);
                                                output.append("\nL'oggetto " + gameItem.getName() + " è stato rimosso.\n");
                                            }

                                        } else if (iC.getName().equals("radio") && gameItem.getName().equals("batterie") && !((GameItemContainer) iC).getLockedBy().equals("")) {
                                            output.append("Impossibile inserire questo oggetto perchè la " + iC.getName() + " è chiusa!\n");
                                        } else if (iC.getName().equals("computer") && gameItem.getName().equals("usb") && ((GameItemContainer) iC).getcItemList().getInventoryList().isEmpty()) {
                                            output.append("Hai inserito l'oggetto " + gameItem.getName()
                                                    + " in " + iC.getName() + ".\n");

                                            ((GameItemContainer) iC).add(gameItem);
                                            this.getGame().getInventory().remove(gameItem);

                                        } else if (iC.getName().equals("fisso") && gameItem.getName().equals("usb") && ((GameItemContainer) iC).getcItemList().getInventoryList().isEmpty()) {
                                            output.append("Hai inserito l'oggetto " + gameItem.getName()
                                                    + " in " + iC.getName() + ".\n");

                                            ((GameItemContainer) iC).add(gameItem);


                                            gameItem.consume();

                                            // Se l'oggetto è stato consumato, lo rimuove dall'inventario.
                                            if (gameItem.isConsumed()) {
                                                this.getGame().getInventory().remove(gameItem);
                                                output.append("\nL'oggetto " + gameItem.getName() + " è stato rimosso.\n");
                                            }

                                            //CONTINUARE DA QUI PER ALTRI OGGETTI
                                        } else if(gameItem.isGIPassword()) {
                                            output.append("Specifica correttamente l'oggetto da inserire.\n");
                                        } else {
                                            output.append("Non puoi inserire questo oggetto qui!\n");
                                        }

                                    } else {
                                        output.append("Specifica correttamente l'oggetto da inserire.\n");
                                    }
                                } else {
                                    output.append("Specifica correttamente l'oggetto da inserire.\n");
                                }
                            } else {
                                if (pOutput.containsWordType(WordType.ERROR)) {
                                    if (pOutput.containsWordType(WordType.IN)) {
                                        if (((GameItemContainer) iC).getLockedBy().equals("") && !((GameItemContainer) iC).isiCNotInsertable() && !gameItem.isGIPassword() && !((GameItemContainer) iC).isPasswordLocked()) {

                                            output.append("Hai inserito l'oggetto " + gameItem.getName()
                                                    + " in " + iC.getName() + ".\n");

                                            ((GameItemContainer) iC).add(gameItem);
                                            this.getGame().getInventory().remove(gameItem);

                                            gameItem.consume();

                                            // Se l'oggetto è stato consumato, lo rimuove dall'inventario.
                                            if (gameItem.isConsumed()) {
                                                this.getGame().getInventory().remove(gameItem);
                                                output.append("\nL'oggetto " + gameItem.getName() + " è stato rimosso.\n");
                                            }
                                        } else if (((GameItemContainer) iC).isPasswordLocked()) {
                                            if ((this.getGame().getCurrentRoom().getName().equals("CCTV") && gameItem.getName().equals(Description.PASSWORD_STRONGBOX_CCTV))
                                                    || (this.getGame().getCurrentRoom().getName().equals("Hall") && gameItem.getName().equals(Description.PASSWORD_STRONGBOX_HALL))) {
                                                if (!((GameItemContainer) iC).isPasswordUnlocked()) {
                                                    output.append("Password esatta. Hai sbloccato la " + iC.getName() + "!\n");
                                                    ((GameItemContainer) iC).setPasswordUnlocked(true);
                                                } else {
                                                    output.append("L'oggetto " + iC.getName() + " è sbloccato!\n");
                                                }
                                            } else {
                                                if (!((GameItemContainer) iC).isPasswordUnlocked()) {
                                                    output.append("Password errata!\n");
                                                } else {
                                                    output.append("L'oggetto " + iC.getName() + " è sbloccato!\n");
                                                }
                                            }
                                        } else if (((GameItemContainer) iC).isiCNotInsertable()) {
                                            if (iC.getName().equals("cambiamonete")) {

                                                if (gameItem.getName().equals("gettone")) {
                                                    output.append("Hai inserito l'oggetto " + gameItem.getName()
                                                            + " nel " + iC.getName() + ".\n È caduto l'oggetto: \n" +
                                                            "- tessera2\n");

                                                    for (GameItem g : ((GameItemContainer) iC).getcItemList().getInventoryList()) {
                                                        g.setPickupable(true);
                                                    }

                                                    ((GameItemContainer) iC).setItemAlreadyIntoIC(true);
                                                    this.getGame().getInventory().remove(gameItem);
                                                } else {
                                                    output.append("Specifica correttamente l'oggetto da inserire.\n");
                                                }

                                            } else {
                                                output.append("Non puoi inserire qualcosa qui!\n");
                                            }
                                        } else if(gameItem.isGIPassword()) {
                                            output.append("Specifica correttamente l'oggetto da inserire.\n");
                                        } else {
                                            output.append("Impossibile inserire questo oggetto qui\n"
                                                    + "perchè il contenitore è chiuso.\n");
                                        }

                                    } else {
                                        output.append("Specifica correttamente l'oggetto da inserire.\n");
                                    }
                                } else {
                                    output.append("Specifica correttamente l'oggetto da inserire.\n");
                                }
                            }
                        } else {
                            output.append("Non puoi inserire qualcosa qui!\n");
                        }
                    } else if (pOutput.containsWordType(WordType.ROOM_OBJ) && pOutput.size() > 1 && pOutput.size() < 5) {
                        GameItem iC = null; //contenitore
                        gameItem = null;
                        byte index = 0;

                        // Iteratore per ciclare sul ParserOutput
                        Iterator<WordType> it = pOutput.iterator();
                        it.next(); // Salta il comando iniziale, già conosciuto

                        // Salva gli oggetti che devono interagire nell'ordine prestabilito (e.g. "Apri baule con chiave")
                        while (it.hasNext()) {
                            //itemContainer, obbligatoriamente un oggetto della Room.
                            if (index == 3 && it.next().equals(WordType.ROOM_OBJ)) {
                                iC = this.getGame().getCurrentRoom().getItemList().searchItem(pOutput.getString(WordType.ROOM_OBJ));
                            }
                            index++;
                        }

                        if(pOutput.size() == 4) {
                            if (iC instanceof GameItemContainer) {
                                if (((GameItemContainer) iC).isPasswordLocked()) {
                                    if (!((GameItemContainer) iC).isPasswordUnlocked()) {
                                        output.append("Password errata!\n");
                                    } else {
                                        output.append("L'oggetto " + iC.getName() + " è sbloccato!\n");
                                    }
                                } else {
                                    output.append("Specifica correttamente l'oggetto da inserire.\n");
                                }
                            } else {
                                output.append("Specifica correttamente l'oggetto da inserire.\n");
                            }
                        }
                        else {
                            if (iC instanceof GameItemContainer) {
                                if(((GameItemContainer) iC).isPasswordLocked()) {
                                    output.append("Specifica correttamente la password e/o la sintassi del comando.\n"); //CAMBIARE
                                } else {
                                    output.append("Specifica correttamente l'oggetto da inserire.\n");
                                }
                            } else {
                                output.append("Non puoi inserire qualcosa qui!\n");
                            }
                        }

                    } else if (pOutput.size() == 1) {
                        output.append("Specifica che oggetto vuoi inserire e dove!\n");
                    } else if (pOutput.size() > 1 && pOutput.size() < 5 && pOutput.containsWordType(WordType.INVENTORY_OBJ) && !gameItem.isGIPassword()) {
                        output.append("Specifica correttamente dove vuoi inserire l'oggetto!\n");
                    } else {
                        output.append("Specifica correttamente l'oggetto da inserire.\n");
                    }
                    break;

                // Comando per "svegliarsi", fa partire un finale nascosto
                /*case WAKE_UP:

                    output.append("Ti guardi intorno e controlli l'orario: sono le 12! Non è suonata la sveglia... \n"
                            + "L'esame era alle 9. Ti sei giocato l'ultimo appello della sessione...\n"
                            + "Ora dovrai farlo a settembre... Ti sei rovinato le vacanze! "
                            + "\n \n HAI SCOPERTO IL FINALE ALTERNATIVO DEL GIOCO IN : "
                            + this.getGame().getGameTime().getTime()
                            + "\nNon avendo concluso il gioco portando a termine gli obiettivi, "
                            + "nella scoreboard avrai una penitenza. ");

                    // Ferma il timer che tiene traccia del tempo di completamento del gioco
                    this.getGame().getGameTime().cancel();

                    // La penitenza è pari al tempo di completamento + 3 ore (10800 secondi)
                    this.getGame().getGameTime().setSecondPassed(this.getGame().getGameTime().getSecondPassed() + 10800);

                    // Mostra all'utente una immagine di congratulazioni
                    r = this.getGame().getCurrentRoom();
                    r.setRoomImage(new ImageIcon("resources//img//stanze//congratulations.jpg"));

                    break;*/

                default:
                    break;
            }
        } catch (NullPointerException e) {
            output.append("Sembra esserci qualcosa "
                    + "di strano in questa richiesta...\n");
        } finally {
            return output.toString();
        }
    }

    private String endGame() {
        //JOptionPane.showMessageDialog();
        this.getGame().getgTime().cancel();
        return "TEMPO COMPLETAMENTO GIOCO: " + this.getGame().getgTime().getTime();
    }

    private GameItem findGameItem(final ParserOutput pOutput) {

        GameItem item = null;

        /* Se l'azione sfrutta un oggetto salva l'oggetto e
           lo rinomina nel ParserOutput per fare riferimento
           al suo nome principale
           e non a potenziali alias, così da uniformare la gestione del comando.
         */
        if (pOutput.containsWordType(WordType.INVENTORY_OBJ)) {
            // Per gli oggetti dell'inventario.

            // Cerca l'item.
            item = this.getGame().getInventory().
                    searchItem(pOutput.getString(WordType.INVENTORY_OBJ));

            // Se lo trova, lo rinomina nel ParserOutput.
            if (!Objects.isNull(item)) {

                pOutput.add(WordType.INVENTORY_OBJ, item.getName());

            } else {
                // Se non ha trovato l'oggetto lo cerca
                // in un contenitore non bloccato dell'inventario.

                for (GameItem iC : this.getGame().getInventory().
                        getInventoryList()) {

                    if (iC instanceof GameItemContainer
                            && ((GameItemContainer) iC).
                            getLockedBy().equals("")) {

                        item = ((GameItemContainer) iC).getcItemList().
                                searchItem(pOutput.
                                        getString(WordType.INVENTORY_OBJ));

                        if (!Objects.isNull(item)) {
                            pOutput.add(WordType.INVENTORY_OBJ, item.getName());
                            break;
                        }

                    }

                }

            }

        } else if (pOutput.containsWordType(WordType.ROOM_OBJ)) {
            // Per gli oggetti della stanza.

            item = this.getGame().getCurrentRoom().getItemList().
                    searchItem(pOutput.getString(WordType.ROOM_OBJ));

            // Se lo trova, lo rinomina nel ParserOutput.
            if (!Objects.isNull(item)) {

                pOutput.add(WordType.ROOM_OBJ, item.getName());

            } else {
                // Se non ha trovato l'oggetto lo cerca
                // in un contenitore non bloccato della stanza.

                for (GameItem iC : this.getGame().getCurrentRoom().
                        getItemList().getInventoryList()) {

                    if (iC instanceof GameItemContainer
                            && ((GameItemContainer) iC).
                            getLockedBy().equals("")) {

                        item = ((GameItemContainer) iC).getcItemList().
                                searchItem(pOutput.
                                        getString(WordType.ROOM_OBJ));

                        if (!Objects.isNull(item)) {
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
     * @param iName nome dell'oggetto "chiave".
     * @return booleano, true se la stanza è stata sbloccata dall'item, false
     * altrimenti.
     */
    private boolean unlockRoom(final String iName) {

        boolean flag = false;

        // Controlla se esiste una stanza in quella direzione,
        // per ogni direzione e controlla se è bloccata
        // dall'item passato in input.
        // In caso affermativo, la sblocca e imposta il flag a true.
        if (!Objects.isNull(this.getGame().getCurrentRoom().getSouth())
                && this.getGame().getCurrentRoom().getSouth().
                getLockedBy().equals(iName)) {

            this.getGame().getCurrentRoom().getSouth().setLockedBy("");
            flag = true;

        } else if (!Objects.isNull(this.getGame().getCurrentRoom().getNorth())
                && this.getGame().getCurrentRoom().getNorth().
                getLockedBy().equals(iName)) {

            this.getGame().getCurrentRoom().getNorth().setLockedBy("");
            flag = true;

        } else if (!Objects.isNull(this.getGame().getCurrentRoom().getEast())
                && this.getGame().getCurrentRoom().getEast().
                getLockedBy().equals(iName)) {

            this.getGame().getCurrentRoom().getEast().setLockedBy("");
            flag = true;

        } else if (!Objects.isNull(this.getGame().getCurrentRoom().getWest())
                && this.getGame().getCurrentRoom().getWest().
                getLockedBy().equals(iName)) {

            this.getGame().getCurrentRoom().getWest().setLockedBy("");
            flag = true;

        }

        return flag;
    }

    /**
     * @return Stringa contenente
     * tutti i comandi riconosciuti dal programma.
     */
    @Override
    public String showHelp() {
        return "\t     -- Come giocare a The Lost Hotel --\n"
                + "\n"
                + "E' possibile usare questi comandi testuali "
                + "anche senza premere i relativi pulsanti:\n"
                + "\n"
                + ">> nord - Spostati in direzione nord\n"
                + ">> est - Spostati in direzione est\n"
                + ">> sud - Spostati in direzione sud\n"
                + ">> ovest - Spostati in direzione ovest\n"
                + ">> inventario - Consente di visualizzare "
                + "l'inventario con i relativi oggetti\n"
                + ">> salva (valido solo tramite pulsante) - "
                + "Salva la partita corrente\n"
                + ">> esci (valido solo tramite pulsante) - "
                + "Permette di ritornare al menù principale "
                + "ed eventualmente salvare la partita corrente\n"
                + "\n"
                + "Altri comandi:\n"
                + "\n"
                + ">> aiuto - Consente di visualizzare "
                + "l'elenco dei comando riconosciuti\n"
                + ">> osserva - Permette di guardarti intorno "
                + "ed esaminare l'ambiente circostante\n"
                + ">> osserva [oggetto/oggetto contenitore] - "
                + "Permette di esaminare un oggetto dell'inventario "
                + "o un oggetto contenitore della stanza corrente\n"
                + ">> usa [oggetto/oggetto contenitore] - "
                + "Usa un oggetto dell'inventario o della stanza corrente\n"
                + ">> apri [oggetto contenitore] - "
                + "Apri un oggetto contenitore\n"
                + ">> apri [oggetto contenitore] con [oggetto] - "
                + "Apri un oggetto contenitore bloccato con un oggetto\n"
                + ">> sposta [oggetto/oggetto contenitore] - "
                + "Sposta un oggetto della stanza\n"
                + ">> prendi [oggetto] - Prendi un oggetto a terra "
                + "nella stanza o in un contenitore\n"
                + ">> lascia [oggetto] - Lascia un oggetto "
                + "nella stanza corrente\n"
                + ">> inserisci [oggetto] in [oggetto contenitore] - "
                + "Permette di inserire un oggetto in un oggetto contenitore "
                + "(non bloccato)\n\n"
                + "Altri comandi più specifici dovranno "
                + "essere trovati dal giocatore.\n"
                + "N.B: Occhio agli oggetti non presenti nelle immagini!\n\n"
                + "SUGGERIMENTO:\n"
                + "È possibile risalire ai comandi eseguiti posizionandosi "
                + "sull'area di inserimento dei comandi.\n"
                + "   - Premendo la freccia in giù, "
                + "è possibile scorrere i vari comandi eseguiti.\n\n"
                + "N.B: In caso in cui si carichi una partita esistente, "
                + "i comandi eseguiti verranno persi!\n"
                + "\n\n"
                + "Per salvare o caricare una partita, sovrascrivere il file "
                + "TheLostHotel.dat\n";
    }

    /**
     * Funzione per spostarsi tra le stanze.
     *
     * @param c tipo di comando che indica il movimento da compiere.
     * @return stanza in cui spostarsi, se trovata, altrimenti null.
     */
    private Room move(final CommandType c) {

        // Se il comando corrisponde a quel movimento
        // e la stanza in quella direzione esiste.
        if (c.equals(CommandType.SUD)
                && !Objects.isNull(
                this.getGame().getCurrentRoom().getSouth())) {
            return this.getGame().getCurrentRoom().getSouth();
        } else if (c.equals(CommandType.NORD)
                && !Objects.isNull(
                this.getGame().getCurrentRoom().getNorth())) {
            return this.getGame().getCurrentRoom().getNorth();
        } else if (c.equals(CommandType.EST)
                && !Objects.isNull(
                this.getGame().getCurrentRoom().getEast())) {
            return this.getGame().getCurrentRoom().getEast();
        } else if (c.equals(CommandType.OVEST)
                && !Objects.isNull(
                this.getGame().getCurrentRoom().getWest())) {
            return this.getGame().getCurrentRoom().getWest();
        }
        return null;
    }
}
