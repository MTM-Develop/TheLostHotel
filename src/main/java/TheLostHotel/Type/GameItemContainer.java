package TheLostHotel.Type;

import java.io.Serializable;
import java.util.Set;

/**
 * Classe utilizzata per rappresentare oggetti che contengono altri oggetti,
 * ovvero per rappresentare dei contenitori.
 *
 * ItemContainer estende Item, poiché sono anch'essi oggetti.
 *
 */
public class GameItemContainer extends GameItem implements Serializable {

    private Inventory cItemList;    //contenuto del contenitore
    private String lockedBy = "";   //indica da cosa è bloccato, se vuoto "" non è bloccato

    // Costruttori

    public GameItemContainer(int id, String name, String desc) {
        super(id, name, desc);
        this.cItemList = new Inventory();
    }

    public GameItemContainer(Inventory cItemList, int id, String name, String desc) {
        super(id, name, desc);
        this.cItemList = cItemList;
    }

    public GameItemContainer(Inventory cItemList, int id, String name, String desc, Set<String> alias) {
        super(id, name, desc, alias);
        this.cItemList = cItemList;
    }

    // Metodi di get, set, equals e hash code

    public Inventory getcItemList() {
        return cItemList;
    }

    public void setcItemList(Inventory cItemList) {
        this.cItemList = cItemList;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }

    /**
     * Aggiunge un oggetto al contenitore
     * @param i item da aggiungere
     */
    public void add(GameItem i) {
        this.cItemList.add(i);
    }

    /**
     * Rimuove un oggetto dal contenitore
     * @param i item da rimuovere
     * @return booleano, true se l'oggetto è stato rimosso, false altrimenti
     */
    public boolean remove(GameItem i) {
        return this.cItemList.remove(i);
    }

    /**
     * Metodo per sbloccare un contenitore.
     * @param cName oggetto che blocca l'apertura di un contenitore
     * @return flag indica se si può aprire o meno con l'oggetto identificato da cName
     */
    public boolean unlockContainer(String cName) {

        boolean flag = false;

        // Se cName è la chiave del contenitore
        if (getLockedBy().equals(cName)) {

            // Sblocca il contenitore
            this.setLockedBy("");
            flag = true;

        }

        return flag;

    }

    @Override
    public String toString() {
        return cItemList.toString();
    }
}
