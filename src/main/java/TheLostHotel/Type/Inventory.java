package TheLostHotel.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Usata per Inventario del giocatore, Inventario nella stanza
 * e Inventario dei contenitori (ItemContainer).
 *
 * E' una collezione di Item.
 *
 */
public class Inventory implements Serializable {

    private List<GameItem> inventoryList; //Lista di Item

    // Costruttore
    public Inventory() {
        inventoryList = new ArrayList<>();
    }

    //Metodi get e set

    public List<GameItem> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<GameItem> inventoryList) {
        this.inventoryList = inventoryList;
    }

    /**
     * Aggiunge un oggetto all'inventario
     * @param i oggetto(Item) da aggiungere
     */
    public void add(GameItem i) {
        inventoryList.add(i);
    }

    /**
     * Rimuove un oggetto dall'inventario
     * @param i oggetto da rimuovere
     * @return booleano che indica se l'oggetto è stato rimosso dall'inventario o meno
     */
    public boolean remove(GameItem i) {
        return inventoryList.remove(i);
    }

    /**
     * Elenca il contenuto dell'inventario del giocatore, della stanza o di un contenitore sotto forma di stringa
     * @return Stringa che indica il contenuto di un oggetto di tipo Inventory
     */
    @Override
    public String toString() {

        StringBuilder invList = new StringBuilder();

        if(!inventoryList.isEmpty()) {
            for (GameItem i : inventoryList) {

                invList.append("\n- ").append(i.getName());
            }
        }else
            invList.append("L'inventario è vuoto!\n");

        return invList.toString();
    }

    /**
     * Cerca un oggetto nella lista di oggetti contenuti dall'inventario
     * attraverso il nome passato in input
     *
     * @param iName nome oggetto
     * @return i nel caso l'oggetto sia presente nella lista e null altrimenti
     */
    public GameItem searchItem(String iName) {

        for (GameItem i : inventoryList) {

            // Controlla se combacia con il nome o con un alias degli oggetti
            if (i.getName().equals(iName) || i.getAlias().contains(iName)) {
                return i;
            }

        }

        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.inventoryList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Inventory other = (Inventory) obj;
        if (!Objects.equals(this.inventoryList, other.inventoryList)) {
            return false;
        }
        return true;
    }



}
