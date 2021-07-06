package tlh.Type;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Classe utilizzata per rappresentare oggetti che contengono altri oggetti,
 * ovvero per rappresentare dei contenitori.
 *
 * ItemContainer estende Item, poiché sono anch'essi oggetti.
 *
 */
public class GameItemContainer extends GameItem implements Serializable {

    /**
     * Contenuto del contenitore.
     */
    private Inventory cItemList;

    /**
     * Indica da cosa è bloccato, se vuoto "" non è bloccato.
     */
    private String lockedBy = "";

    /**
     * Indica da che combinazione è bloccato, se vuoto "" non è bloccato.
     */
    private String passwordLockedBy = "";

    /**
     * Indica se è stato chiuso.
     */
    private boolean closed = true;

    /**
     * Indica se è stato spostato.
     */
    private boolean moved = false;

    /**
     * Indica se si può spostare.
     */
    private boolean movable = false;

    /**
     * Indica se si può utilizzare con determinati gameItem.
     */
    private boolean usableWithItem = false;

    /**
     * Indica se è utile o meno riferendosi al comando "Apri"
     * (Es. se non ci fosse, al comando "Apri tv"
     * verrebbe visualizzato il messaggio:
     * "L'oggetto è stato aperto, ma è vuoto!"
     */
    private boolean iCNotOpenable = false;

    /**
     * Indica se è possibile inserire oggetti
     * qui dentro.
     * (Es. "Inserisci [oggetto] in attaccapanni").
     */
    private boolean iCNotInsertable = false;

    /**
     * Indica se l'oggetto contenitore è
     * bloccato da una password.
     */
    private boolean passwordLocked = false;

    /**
     * Indica se l'oggetto contenitore è
     * stato sbloccato da una password.
     */
    private boolean passwordUnlocked = false;

    /**
     * Indica se l'oggetto contenitore
     * permette di accedere ad un posto segreto.
     */
    private boolean secretAccess = false;

    /**
     * Indica se è già stato inserito qualcosa in un contenitore.
     */
    private boolean itemAlreadyIntoIC = false;

    /**
     * Descrizione del contenitore (quando viene aperto).
     */
    private String openedDescription;

    /**
     * Descrizione del contenitore (quando viene spostato).
     */
    private String movedDescription;

    /**
     * Descrizione del contenitore (quando viene sbloccato
     * da una password).
     */
    private String passwordUnlockedDescription;

    /**
     * Costruttore.
     *
     * @param id
     * @param name
     * @param desc
     */
    public GameItemContainer(final int id, final String name,
                             final String desc) {
        super(id, name, desc);
        this.cItemList = new Inventory();
    }

    /**
     * Costruttore.
     *
     * @param cIList
     * @param id
     * @param name
     * @param desc
     */
    public GameItemContainer(final Inventory cIList, final int id,
                             final String name, final String desc) {
        super(id, name, desc);
        this.cItemList = cIList;
    }

    /**
     * Costruttore.
     *
     * @param cIList
     * @param id
     * @param name
     * @param desc
     * @param alias
     */
    public GameItemContainer(final Inventory cIList, final int id,
                             final String name, final String desc,
                             final Set<String> alias) {
        super(id, name, desc, alias);
        this.cItemList = cIList;
    }

    /**
     * @return contenuto del contenitore.
     */
    public Inventory getcItemList() {
        return cItemList;
    }

    /**
     * Imposta il contenuto del contenitore.
     *
     * @param cIList
     */
    public void setcItemList(final Inventory cIList) {
        this.cItemList = cIList;
    }

    /**
     * @return Stringa che rappresenta il nome dell'oggetto
     * che blocca il contenitore.
     */
    public String getLockedBy() {
        return lockedBy;
    }

    /**
     * Indica con quale oggetto (nome) il contenitore è chiuso.
     *
     * @param lBy
     */
    public void setLockedBy(final String lBy) {
        this.lockedBy = lBy;
    }

    /**
     * @return Stringa che rappresenta la combinazione
     * che blocca il contenitore.
     */
    public String getPasswordLockedBy() {
        return passwordLockedBy;
    }

    /**
     * Indica quale combinazione blocca il contenitore.
     *
     * @param pswLockedBy
     */
    public void setPasswordLockedBy(final String pswLockedBy) {
        this.passwordLockedBy = pswLockedBy;
    }

    /**
     * @return booleano (vero se se il contenitore è stato già chiuso,
     * falso altrimenti).
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * Indica se il contenitore è stato già chiuso.
     *
     * @param c
     */
    public void setClosed(final boolean c) {
        this.closed = c;
    }

    /**
     * @return booleano (vero se se il contenitore è stato già spostato,
     * falso altrimenti).
     */
    public boolean isMoved() {
        return moved;
    }

    /**
     * Indica se il contenitore è stato già spostato.
     *
     * @param m
     */
    public void setMoved(final boolean m) {
        this.moved = m;
    }

    /**
     * @return booleano (vero se se il contenitore si può spostare,
     * falso altrimenti).
     */
    public boolean isMovable() {
        return movable;
    }

    /**
     * Indica se il contenitore si può spostare.
     *
     * @param m
     */
    public void setMovable(final boolean m) {
        this.movable = m;
    }

    /**
     * @return booleano (vero se è possibile usare il contenitore
     * con determinati gameItem, falso altrimenti).
     */
    public boolean isUsableWithItem() {
        return usableWithItem;
    }

    /**
     * Imposta lo stato del contenitore
     * (se è possibile usarlo con determinati gameItem o meno).
     *
     * @param uWithItem
     */
    public void setUsableWithItem(final boolean uWithItem) {
        this.usableWithItem = uWithItem;
    }

    /**
     * @return booleano (vero se è possibile aprire il contenitore,
     * falso altrimenti.
     */
    public boolean isiCNotOpenable() {
        return iCNotOpenable;
    }

    /**
     * Imposta lo stato del contenitore (se è possibile aprirlo o meno).
     *
     * @param iCNOpenable
     */
    public void setiCNotOpenable(final boolean iCNOpenable) {
        this.iCNotOpenable = iCNOpenable;
    }

    /**
     * @return booleano (vero se non è possibile inserire
     * oggetti qui dentro, falso altrimenti).
     */
    public boolean isiCNotInsertable() {
        return iCNotInsertable;
    }

    /**
     * Imposta lo stato del contenitore (se è possibile
     * inserire oggetti qui dentro o meno).
     *
     * @param iCNInsertable
     */
    public void setiCNotInsertable(final boolean iCNInsertable) {
        this.iCNotInsertable = iCNInsertable;
    }

    /**
     * @return booleano (vero se è bloccato da una password,
     * falso altrimenti).
     */
    public boolean isPasswordLocked() {
        return passwordLocked;
    }

    /**
     * Imposta lo stato del contenitore (se è bloccato
     * da una password o meno).
     *
     * @param passLocked
     */
    public void setPasswordLocked(final boolean passLocked) {
        this.passwordLocked = passLocked;
    }

    /**
     * @return booleano (vero se è sbloccato da una password,
     * falso altrimenti).
     */
    public boolean isPasswordUnlocked() {
        return passwordUnlocked;
    }

    /**
     * Imposta lo stato del contenitore (se è sbloccato
     * da una password o meno).
     *
     * @param pswUnlocked
     */
    public void setPasswordUnlocked(final boolean pswUnlocked) {
        this.passwordUnlocked = pswUnlocked;
    }

    /**
     * @return booleano (vero se ha un accesso segreto,
     * falso altrimenti).
     */
    public boolean isSecretAccess() {
        return secretAccess;
    }

    /**
     * Imposta lo stato del contenitore (se prevede un
     * accesso segreto o meno).
     *
     * @param secrAccess
     */
    public void setSecretAccess(final boolean secrAccess) {
        this.secretAccess = secrAccess;
    }

    /**
     * @return booleano (vero se è stato già inserito
     * qualcosa nel contenitore, falso altrimenti).
     */
    public boolean isItemAlreadyIntoIC() {
        return itemAlreadyIntoIC;
    }

    /**
     * Imposta lo stato del contenitore
     * (se è stato già inserito qualcosa o meno).
     *
     * @param iAlreadyIntoIC
     */
    public void setItemAlreadyIntoIC(final boolean iAlreadyIntoIC) {
        this.itemAlreadyIntoIC = iAlreadyIntoIC;
    }

    /**
     * @return descrizione del contenitore (quando è stato aperto).
     */
    public String getOpenedDescription() {
        return openedDescription;
    }

    /**
     * Imposta la descrizione del contenitore (quando è stato aperto).
     *
     * @param oDescription
     */
    public void setOpenedDescription(final String oDescription) {
        this.openedDescription = oDescription;
    }

    /**
     * @return descrizione del contenitore (quando è stato spostato).
     */
    public String getMovedDescription() {
        return movedDescription;
    }

    /**
     * Imposta la descrizione del contenitore (quando è stato spostato).
     *
     * @param mDescription
     */
    public void setMovedDescription(final String mDescription) {
        this.movedDescription = mDescription;
    }

    /**
     * @return descrizione del contenitore
     * (quando è stato sbloccato da una password).
     */
    public String getPasswordUnlockedDescription() {
        return passwordUnlockedDescription;
    }

    /**
     * Imposta la descrizione del contenitore
     * (quando è stato sbloccato da una password).
     *
     * @param pswUDescription
     */
    public void setPasswordUnlockedDescription(final String pswUDescription) {
        this.passwordUnlockedDescription = pswUDescription;
    }

    /**
     * Aggiunge un oggetto al contenitore.
     *
     * @param i item da aggiungere.
     */
    public void add(final GameItem i) {
        this.cItemList.add(i);
    }

    /**
     * Rimuove un oggetto dal contenitore.
     *
     * @param i item da rimuovere.
     * @return booleano (vero se l'oggetto è stato rimosso, falso altrimenti).
     */
    public boolean remove(final GameItem i) {
        return this.cItemList.remove(i);
    }

    /**
     * Metodo per sbloccare un contenitore.
     *
     * @param cName oggetto che blocca l'apertura di un contenitore.
     * @return flag (indica se si può aprire o meno
     * con l'oggetto identificato da cName).
     */
    public boolean unlockContainer(final String cName) {

        boolean flag = false;

        // Se cName è la chiave del contenitore
        if (getLockedBy().equals(cName)) {

            // Sblocca il contenitore
            this.setLockedBy("");
            flag = true;

        }

        return flag;

    }

    /**
     * Elenca il contenuto del contenitore sotto forma di stringa.
     *
     * @return Stringa che indica il contenuto
     * di un oggetto di tipo GameItemContainer.
     */
    @Override
    public String toString() {
        return cItemList.toString();
    }

    /**
     * Metodo generato automaticamente per confrontare
     * se due oggetti di questa classe sono uguali.
     *
     * @return codice hash dell'oggetto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cItemList, lockedBy);
    }

    /**
     * Permette di confrontare se due contenitori sono uguali.
     *
     * @param o
     * @return booleano (vero se i due contenitori sono uguali,
     * falso altrimenti).
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        GameItemContainer that = (GameItemContainer) o;
        return closed == that.closed && moved == that.moved
                && movable == that.movable
                && usableWithItem == that.usableWithItem
                && iCNotOpenable == that.iCNotOpenable
                && Objects.equals(cItemList, that.cItemList)
                && Objects.equals(lockedBy, that.lockedBy)
                && Objects.equals(openedDescription, that.openedDescription)
                && Objects.equals(movedDescription, that.movedDescription);
    }
}
