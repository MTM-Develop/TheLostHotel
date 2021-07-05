package tlh.Type;

import tlh.Other.Description;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import javax.swing.ImageIcon;

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
     * Immagine raffigurante la stanza
     * che verrà visualizzata nell'interfaccia grafica.
     */
    private ImageIcon roomImage;

    /**
     * Costruttore.
     *
     * @param rId
     * @param rName
     * @param rDescription
     */
    public Room(final int rId, final String rName, final String rDescription) {
        this.id = rId;
        this.name = rName;
        this.description = rDescription;
        itemList = new Inventory();
    }

    /**
     * @return nome della stanza.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome della stanza.
     *
     * @param n
     */
    public void setName(final String n) {
        this.name = n;
    }

    /**
     * @return descrizione base della stanza.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione base della stanza.
     *
     * @param desc
     */
    public void setDescription(final String desc) {
        this.description = desc;
    }

    /**
     * @return descrizione più dettagliata della stanza.
     */
    public String getLookDescription() {
        return lookDescription;
    }

    /**
     * Imposta una descrizione più dettagliata della stanza.
     *
     * @param lookDesc
     */
    public void setLookDescription(final String lookDesc) {
        this.lookDescription = lookDesc;
    }

    /**
     * @return descrizione della stanza (se è stata già visitata).
     */
    public String getVisitedDescription() {
        return visitedDescription;
    }

    /**
     * Imposta la descrizione della stanza (se è stata già visitata).
     *
     * @param visitedDesc
     */
    public void setVisitedDescription(final String visitedDesc) {
        this.visitedDescription = visitedDesc;
    }

    /**
     * @return Stringa che rappresenta il nome dell'oggetto
     * che permette di sbloccare la stanza.
     */
    public String getLockedBy() {
        return lockedBy;
    }

    /**
     * Indica quale oggetto (nome) permette di sbloccare la stanza.
     *
     * @param lBy
     */
    public void setLockedBy(final String lBy) {
        this.lockedBy = lBy;
    }

    /**
     * @return booleano (vero se la stanza è stata visitata, falso altrimenti).
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Indica se la stanza è stata visitata.
     *
     * @param v
     */
    public void setVisited(final boolean v) {
        this.visited = v;
    }

    /**
     * @return stanza che si trova a nord.
     */
    public Room getNorth() {
        return north;
    }

    /**
     * Imposta la stanza che si trova a nord.
     *
     * @param rNorth
     */
    public void setNorth(final Room rNorth) {
        this.north = rNorth;
    }

    /**
     * @return stanza che si trova a sud.
     */
    public Room getSouth() {
        return south;
    }

    /**
     * Imposta la stanza che si trova a sud.
     *
     * @param rSouth
     */
    public void setSouth(final Room rSouth) {
        this.south = rSouth;
    }

    /**
     * @return stanza che si trova a ovest.
     */
    public Room getWest() {
        return west;
    }

    /**
     * Imposta la stanza che si trova a ovest.
     *
     * @param rWest
     */
    public void setWest(final Room rWest) {
        this.west = rWest;
    }

    /**
     * @return stanza che si trova a est.
     */
    public Room getEast() {
        return east;
    }

    /**
     * Imposta la stanza che si trova a est.
     *
     * @param rEast
     */
    public void setEast(final Room rEast) {
        this.east = rEast;
    }

    /**
     * @return booleano (vero se è impossibile
     * accedere direttamente alla stanza, falso altrimenti).
     */
    public boolean isImpossibleToAccessDirectly() {
        return impossibleToAccessDirectly;
    }

    /**
     * Imposta lo stato della stanza (impossibile da accedere direttamente).
     *
     * @param impossibleToAccessDirectly
     */
    public void setImpossibleToAccessDirectly(boolean impossibleToAccessDirectly) {
        this.impossibleToAccessDirectly = impossibleToAccessDirectly;
    }

    /**
     * @return lista di oggetti presenti nella stanza.
     */
    public Inventory getItemList() {
        return itemList;
    }

    /**
     * Imposta la lista di oggetti presenti nella stanza.
     *
     * @param iList
     */
    public void setItemList(final Inventory iList) {
        this.itemList = iList;
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
     * @return immagine della stanza.
     */
    public ImageIcon getRoomImage() {
        return roomImage;
    }

    /**
     * Imposta l'immagine della stanza.
     *
     * @param rImage
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
     * @param o
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
