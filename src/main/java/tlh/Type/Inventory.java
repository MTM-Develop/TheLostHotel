package tlh.Type;

import tlh.Other.Description;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe utilizzata per la gestione dell'Inventario del giocatore,
 * Inventario della stanza e
 * Inventario dei contenitori (GameItemContainer).
 *
 * È una collezione di GameItem.
 *
 */
public class Inventory implements Serializable {

    /**
     * Lista di GameItem.
     */
    private List<GameItem> inventoryList;

    /**
     * Costruttore.
     */
    public Inventory() {
        inventoryList = new ArrayList<>();
    }

    /**
     * @return lista di GameItem.
     */
    public List<GameItem> getInventoryList() {
        return inventoryList;
    }

    /**
     * Imposta la lista di gameItem.
     *
     * @param iList
     */
    public void setInventoryList(final List<GameItem> iList) {
        this.inventoryList = iList;
    }

    /**
     * Aggiunge un oggetto all'inventario.
     *
     * @param gI oggetto (GameItem) da aggiungere.
     */
    public void add(final GameItem gI) {
        inventoryList.add(gI);
    }

    /**
     * Rimuove un oggetto dall'inventario.
     *
     * @param gI oggetto da rimuovere.
     * @return booleano che indica se l'oggetto
     * è stato rimosso dall'inventario o meno.
     */
    public boolean remove(final GameItem gI) {
        return inventoryList.remove(gI);
    }

    /**
     * Elenca il contenuto dell'inventario del giocatore,
     * della stanza o di un contenitore sotto forma di stringa.
     *
     * @return Stringa che indica il contenuto di un oggetto di tipo Inventory.
     */
    @Override
    public String toString() {

        StringBuilder invList = new StringBuilder();

        if (!inventoryList.isEmpty()) {
            for (GameItem i : inventoryList) {

                invList.append("\n- ").append(i.getName());
            }
        } else {
            invList.append("L'inventario è vuoto!\n");
        }

        return invList.toString();
    }

    /**
     * Cerca un oggetto nella lista di oggetti contenuti dall'inventario
     * attraverso il nome passato in input.
     *
     * @param gIName nome oggetto.
     * @return i nel caso l'oggetto sia presente nella lista e null altrimenti.
     */
    public GameItem searchItem(final String gIName) {

        for (GameItem i : inventoryList) {

            // Controlla se combacia con il nome o con un alias degli oggetti
            if (i.getName().equals(gIName) || i.getAlias().contains(gIName)) {
                return i;
            }

        }

        return null;
    }

    /**
     * Controlla se l'inventario è pieno (ovvero se contiene già 6 oggetti).
     * //DA CAMBIARE
     *
     * @return booleano in base alla capienza dell'inventario
     * (vero se è pieno, falso altrimenti).
     */
    public boolean isFull() {
        return (this.inventoryList.size() >= Description.MAX_INV_CAPACITY);
    } //CAMBIARE

    /**
     * Metodo generato automaticamente per confrontare
     * se due oggetti di questa classe sono uguali.
     *
     * @return codice hash dell'oggetto.
     */
    @Override
    public int hashCode() {
        int hash = Description.INV_H1;
        hash = Description.INV_H2 * hash + Objects.hashCode(this.inventoryList);
        return hash;
    }

    /**
     * Permette di confrontare se due inventari sono uguali,
     * ovvero se hanno gli stessi gameItem.
     *
     * @param o
     * @return booleano (vero se i due inventari sono uguali, falso altrimenti).
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Inventory inventory = (Inventory) o;
        return Objects.equals(inventoryList, inventory.inventoryList);
    }
}
