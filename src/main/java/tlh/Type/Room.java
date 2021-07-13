package tlh.Type;

import tlh.Other.Description;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import javax.swing.ImageIcon;

/**
 * Classe che gestisce ciascuna stanza dell'avventura.
 *
 * @author MTM-Develop.
 */
public class Room implements Serializable {

    /**
     * Id della stanza.
     */
    private final int id;

    /**
     * Nome della stanza.
     */
    private String name;

    /**
     * Indica la descrizione base della stanza.
     */
    private String description;

    /**
     * Fornisce una descrizione più dettagliata della stanza.
     */
    private String lookDescription;

    /**
     * Descrizione della stanza già visitata.
     */
    private String visitedDescription;

    /**
     * Indica se la stanza è stata già visitata.
     */
    private boolean visited = false;

    /**
     * Indica la stanza che si trova a nord.
     */
    private Room north = null;

    /**
     * Indica la stanza che si trova a sud.
     */
    private Room south = null;

    /**
     * Indica la stanza che si trova a ovest.
     */
    private Room west = null;

    /**
     * Indica la stanza che si trova a est.
     */
    private Room east = null;

    /**
     * Lista di oggetti presenti nella stanza.
     */
    private Inventory itemList;

    /**
     * Indica l'oggetto che permette di sbloccare la stanza.
     */
    private String lockedBy = "";

    /**
     * Indica se è impossibile accedere alla stanza
     * direttamente (tramite nord, sud, est, ovest).
     */
    private boolean impossibleToAccessDirectly = false;

    /**
     * Indica se si può accedere in una stanza
     * bloccata tramite una porta (sbloccata).
     */
    private boolean anOpenDoor = false;

    /**
     * Immagine raffigurante la stanza
     * che verrà visualizzata nell'interfaccia grafica.
     */
    private ImageIcon roomImage;

    /**
     * Costruttore.
     *
     * @param rId ID della stanza.
     * @param rName nome della stanza.
     * @param rDescription descrizione della stanza.
     */
    public Room(final int rId, final String rName, final String rDescription) {
        this.id = rId;
        this.name = rName;
        this.description = rDescription;
        itemList = new Inventory();
    }

    /**
     * Restituisce il nome della stanza.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome della stanza.
     *
     * @param n nome della stanza.
     */
    public void setName(final String n) {
        this.name = n;
    }

    /**
     * Restituisce la descrizione base della stanza.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione base della stanza.
     *
     * @param desc descrizione base della stanza.
     */
    public void setDescription(final String desc) {
        this.description = desc;
    }

    /**
     * Restituisce la descrizione più dettagliata della stanza.
     *
     * @return lookDescription.
     */
    public String getLookDescription() {
        return lookDescription;
    }

    /**
     * Imposta una descrizione più dettagliata della stanza.
     *
     * @param lookDesc descrizione più dettagliata della stanza.
     */
    public void setLookDescription(final String lookDesc) {
        this.lookDescription = lookDesc;
    }

    /**
     * Restituisce la descrizione della stanza (se è stata già visitata).
     *
     * @return visitedDescription.
     */
    public String getVisitedDescription() {
        return visitedDescription;
    }

    /**
     * Imposta la descrizione della stanza (se è stata già visitata).
     *
     * @param visitedDesc descrizione della stanza.
     */
    public void setVisitedDescription(final String visitedDesc) {
        this.visitedDescription = visitedDesc;
    }

    /**
     * Restituisce la Stringa che rappresenta il nome dell'oggetto
     * che permette di sbloccare la stanza.
     *
     * @return lockedBy.
     */
    public String getLockedBy() {
        return lockedBy;
    }

    /**
     * Indica quale oggetto (nome) permette di sbloccare la stanza.
     *
     * @param lBy nome dell'oggetto.
     */
    public void setLockedBy(final String lBy) {
        this.lockedBy = lBy;
    }

    /**
     * Restituisce booleano (vero se la stanza è stata visitata,
     * falso altrimenti).
     *
     * @return visited.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Indica se la stanza è stata visitata.
     *
     * @param v booleano (vero / falso).
     */
    public void setVisited(final boolean v) {
        this.visited = v;
    }

    /**
     * Restituisce la stanza che si trova a nord.
     *
     * @return north.
     */
    public Room getNorth() {
        return north;
    }

    /**
     * Imposta la stanza che si trova a nord.
     *
     * @param rNorth stanza che si trova a nord.
     */
    public void setNorth(final Room rNorth) {
        this.north = rNorth;
    }

    /**
     * Restituisce la stanza che si trova a sud.
     *
     * @return south.
     */
    public Room getSouth() {
        return south;
    }

    /**
     * Imposta la stanza che si trova a sud.
     *
     * @param rSouth stanza che si trova a sud.
     */
    public void setSouth(final Room rSouth) {
        this.south = rSouth;
    }

    /**
     * Restituisce la stanza che si trova a ovest.
     *
     * @return west.
     */
    public Room getWest() {
        return west;
    }

    /**
     * Imposta la stanza che si trova a ovest.
     *
     * @param rWest stanza che si trova ad ovest.
     */
    public void setWest(final Room rWest) {
        this.west = rWest;
    }

    /**
     * Restituisce la stanza che si trova a est.
     *
     * @return east.
     */
    public Room getEast() {
        return east;
    }

    /**
     * Imposta la stanza che si trova a est.
     *
     * @param rEast stanza che si trova ad est.
     */
    public void setEast(final Room rEast) {
        this.east = rEast;
    }

    /**
     * Restituisce booleano (vero se è impossibile
     * accedere direttamente alla stanza, falso altrimenti).
     *
     * @return impossibleToAccessDirectly.
     */
    public boolean isImpossibleToAccessDirectly() {
        return impossibleToAccessDirectly;
    }

    /**
     * Imposta lo stato della stanza (impossibile da accedere direttamente).
     *
     * @param impossibleToAD booleano (vero / falso).
     */
    public void setImpossibleToAccessDirectly(final boolean impossibleToAD) {
        this.impossibleToAccessDirectly = impossibleToAD;
    }

    /**
     * Restituisce booleano (vero se si può accedere alla stanza bloccata
     * tramite una porta sbloccata, falso altrimenti).
     *
     * @return anOpenDoor.
     */
    public boolean isAnOpenDoor() {
        return anOpenDoor;
    }

    /**
     * Imposta lo stato della stanza (bloccata, se si può accedere
     * tramite una porta sbloccata).
     *
     * @param anODoor booleano (vero / falso).
     */
    public void setAnOpenDoor(final boolean anODoor) {
        this.anOpenDoor = anODoor;
    }

    /**
     * Restituisce la lista di oggetti presenti nella stanza.
     *
     * @return itemList.
     */
    public Inventory getItemList() {
        return itemList;
    }

    /**
     * Aggiunge un oggetto alla stanza.
     *
     * @param i item da aggiungere.
     */
    public void addItem(final GameItem i) {
        itemList.add(i);
    }

    /**
     * Rimuove un oggetto dalla stanza.
     *
     * @param i item da rimuovere.
     * @return booleano (vero se l'oggetto è stato rimosso, falso altrimenti).
     */
    public boolean removeItem(final GameItem i) {

        // Se prova a rimuovere l'oggetto dalla stanza ma non lo trova
        if (!itemList.remove(i)) {

            // Cerca in un ItemContainer della stanza
            Iterator<GameItem> it = itemList.getInventoryList().iterator();
            GameItem item;

            while (it.hasNext()) {

                item = it.next();

                // Se il contenitore contiene quell'elemento lo rimuove
                if (item instanceof GameItemContainer
                        && ((GameItemContainer) item).remove(i)) {
                    return true;
                }
            }

        } else {
            // Se l'ha trovato nella stanza allora l'ha rimosso
            return true;
        }
        return false;
    }

    /**
     * Restituisce l'immagine della stanza.
     *
     * @return roomImage.
     */
    public ImageIcon getRoomImage() {
        return roomImage;
    }

    /**
     * Imposta l'immagine della stanza.
     *
     * @param rImage immagine della stanza.
     */
    public void setRoomImage(final ImageIcon rImage) {
        this.roomImage = rImage;
    }

    /**
     * Metodo generato automaticamente per confrontare
     * se due oggetti di questa classe sono uguali.
     *
     * @return codice hash dell'oggetto.
     */
    @Override
    public int hashCode() {
        int hash = Description.ROOM_H1;
        hash = Description.ROOM_H2 * hash + this.id;
        return hash;
    }

    /**
     *  Permette di confrontare se due stanze sono uguali.
     *
     * @param o oggetto da confrontare.
     * @return booleano (vero se le due stanze sono uguali, falso altrimenti).
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return id == room.id && visited == room.visited
                && Objects.equals(name, room.name)
                && Objects.equals(description, room.description)
                && Objects.equals(lookDescription, room.lookDescription)
                && Objects.equals(visitedDescription, room.visitedDescription)
                && Objects.equals(north, room.north)
                && Objects.equals(south, room.south)
                && Objects.equals(west, room.west)
                && Objects.equals(east, room.east)
                && Objects.equals(itemList, room.itemList)
                && Objects.equals(lockedBy, room.lockedBy)
                && Objects.equals(roomImage, room.roomImage);
    }
}
