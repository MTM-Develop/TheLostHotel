package tlh.Type;

import tlh.Other.Description;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.swing.ImageIcon;

/**
 * Classe utilizzata per rappresentare gli oggetti
 * che saranno presenti nel gioco.
 *
 * @author MTM-Develop.
 */
public class GameItem implements Serializable {

    /**
     * Id del gameItem.
     */
    private final int id;

    /**
     * Nome del gameItem.
     */
    private String name;

    /**
     * Descrizione base del gameItem.
     */
    private String description;

    /**
     * Sinonimi del gameItem.
     */
    private Set<String> alias;

    /**
     * Descrizione del gameItem (utilizzabile e, dopo l'utilizzo,
     * droppa altri gameItem).
     * Es. slot (dopo 'usa slot' cadrà la maniglia).
     */
    private String descriptionUsableWithDrops;

    /**
     * Descrizione del gameItem (se è stato già utilizzato
     * e ha droppato altri gameItem).
     */
    private String descriptionAlreadyUsedWithDrops;

    /**
     * Descrizione del gameItem (utilizzabile,
     * ma non ci sono oggetti dentro necessari per il suo funzionamento).
     * Es. radio senza pile.
     */
    private String descriptionUsableButItemRemoved;

    /**
     * Indica il numero di usi possibili, -1 significa che ha infiniti usi.
     */
    private byte consumable = -1;

    /**
     * Indica se si può raccogliere.
     */
    private boolean pickupable = false;

    /**
     * Indica se è stato preso.
     */
    private boolean picked = false;

    /**
     * Indica se droppa oggetti dopo il suo utilizzo.
     */
    private boolean usableWithDrops = false;

    /**
     * Indica se è stato usato.
     */
    private boolean used = false;

    /**
     * Indica se rappresenta una password.
     */
    private boolean isGIPassword = false;

    /**
     * Indica se è indispensabile
     * al fine del proseguio del gioco.
     */
    private boolean indispensable = false;

    /**
     * Indica se è stato correttamente
     * inserito nell'inventario.
     */
    private boolean itemCorrectlyAdded = false;

    /**
     * Indica se rappresenta
     * una persona.
     */
    private boolean isPerson = false;

    /**
     * Indica se rappresenta un oggetto
     * fondamentale per la vittoria del gioco.
     */
    private boolean isKeyToWin = false;

    /**
     * Immagine raffigurante l'oggetto.
     */
    private ImageIcon itemImage;

    /**
     * Costruttore.
     *
     * @param gId ID del gameItem.
     * @param gIName nome del gameItem.
     * @param gIDescription descrizione del gameItem.
     */
    public GameItem(final int gId, final String gIName,
                    final String gIDescription) {
        this.id = gId;
        this.name = gIName;
        this.description = gIDescription;
        this.alias = new HashSet<>();
    }

    /**
     * Costruttore.
     *
     * @param gId ID del gameItem.
     * @param gIName nome del gameItem.
     * @param gIDescription descrizione del gameItem.
     * @param gIAlias alias del gameItem.
     */
    public GameItem(final int gId, final String gIName,
                    final String gIDescription,
                    final Set<String> gIAlias) {
        this.id = gId;
        this.name = gIName;
        this.description = gIDescription;
        this.alias = gIAlias;
    }

    /**
     * Restituisce il nome del gameItem.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome del gameItem.
     *
     * @param gIName nome del gameItem.
     */
    public void setName(final String gIName) {
        this.name = gIName;
    }

    /**
     * Restituisce la descrizione base del gameItem.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione base del gameItem.
     *
     * @param desc descrizione del GameItem.
     */
    public void setDescription(final String desc) {
        this.description = desc;
    }

    /**
     * Restituisce i sinonimi del gameItem.
     *
     * @return alias.
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * Imposta i sinonimi del gameItem.
     *
     * @param gIAlias sinonimi del gameItem.
     */
    public void setAlias(final String[] gIAlias) {
        this.alias = new HashSet<>(Arrays.asList(gIAlias));
    }

    /**
     * Restituisce la descrizione del gameItem (se è utilizzabile e,
     * dopo l'utilizzo, droppa altri gameItem).
     *
     * @return descriptionUsableWithDrops.
     */
    public String getDescriptionUsableWithDrops() {
        return descriptionUsableWithDrops;
    }

    /**
     * Imposta la descrizione del gameItem (se è utilizzabile e,
     * dopo l'utilizzo, droppa altri gameItem).
     *
     * @param descriptionUWD descrizione del GameItem.
     */
    public void setDescriptionUsableWithDrops(final String descriptionUWD) {
        this.descriptionUsableWithDrops = descriptionUWD;
    }

    /**
     * Restituisce la descrizione del gameItem (se è stato già utilizzato
     * e ha droppato altri gameItem).
     *
     * @return descriptionAlreadyUsedWithDrops.
     */
    public String getDescriptionAlreadyUsedWithDrops() {
        return descriptionAlreadyUsedWithDrops;
    }

    /**
     * Imposta la descrizione del gameItem (se è stato già utilizzato
     * e ha droppato altri gameItem).
     *
     * @param descriptionAUWD descrizione del GameItem.
     */
    public void setDescriptionAlreadyUsedWithDrops(
            final String descriptionAUWD) {
        this.descriptionAlreadyUsedWithDrops = descriptionAUWD;
    }

    /**
     * Restituisce la descrizione del gameItem (se è utilizzabile
     * ma non ci sono oggetti dentro necessari per il suo funzionamento).
     *
     * @return descriptionUsableButItemRemoved.
     */
    public String getDescriptionUsableButItemRemoved() {
        return descriptionUsableButItemRemoved;
    }

    /**
     * Imposta la descrizione del gameItem (se è utilizzabile
     * ma non ci sono oggetti dentro necessari per il suo funzionamento).
     *
     * @param descriptionUBIR descrizione del GameItem.
     */
    public void setDescriptionUsableButItemRemoved(
            final String descriptionUBIR) {
        this.descriptionUsableButItemRemoved = descriptionUBIR;
    }

    /**
     * Restituisce il numero di usi possibili del gameItem.
     *
     * @return consumable.
     */
    public byte getConsumable() {
        return consumable;
    }

    /**
     * Imposta il numero di usi possibili del gameItem.
     *
     * @param gIConsumable numero di usi possibili del gameItem.
     */
    public void setConsumable(final byte gIConsumable) {
        this.consumable = gIConsumable;
    }

    /**
     * Indica se l'oggetto non ha più usi possibili.
     *
     * @return booleano (vero se l'oggetto è consumato e non più usabile,
     * falso altrimenti).
     */
    public boolean isConsumed() {
        return this.getConsumable() == 0;
    }

    /**
     * Decrementa di uno il numero di utilizzi di un oggetto
     * se esso è consumabile.
     */
    public void consume() {
        if (consumable > 0) {
            this.setConsumable((byte) (consumable - 1));
        }
    }

    /**
     * Restituisce booleano (vero se è possibile prendere il gameItem,
     * falso altrimenti).
     *
     * @return pickupable.
     */
    public boolean isPickupable() {
        return pickupable;
    }

    /**
     * Imposta lo stato del gameItem (se è possibile prenderlo o meno).
     *
     * @param p booleano (vero / falso).
     */
    public void setPickupable(final boolean p) {
        this.pickupable = p;
    }

    /**
     * Restituisce booleano (vero se il gameItem è stato preso,
     * falso altrimenti).
     *
     * @return picked.
     */
    public boolean isPicked() {
        return picked;
    }

    /**
     * Imposta lo stato del gameItem (se è stato preso o meno).
     *
     * @param p booleano (vero / falso).
     */
    public void setPicked(final boolean p) {
        this.picked = p;
    }

    /**
     * Restituisce booleano (vero se il gameItem è usabile e,
     * non appena viene utilizzato, droppa uno o più gameItem,
     * falso altrimenti).
     *
     * @return usableWithDrops.
     */
    public boolean isUsableWithDrops() {
        return usableWithDrops;
    }

    /**
     * Imposta lo stato del gameItem (se è usabile e,
     * non appena viene utilizzato, droppa uno o più gameItem, o meno).
     *
     * @param uWithDrops booleano (vero / falso).
     */
    public void setUsableWithDrops(final boolean uWithDrops) {
        this.usableWithDrops = uWithDrops;
    }

    /**
     * Restituisce booleano (vero se il gameItem è stato usato,
     * falso altrimenti).
     *
     * @return used.
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Imposta lo stato del gameItem (se è stato usato o meno).
     *
     * @param u booleano (vero / falso).
     */
    public void setUsed(final boolean u) {
        this.used = u;
    }

    /**
     * Restituisce booleano (vero se il gameItem
     * rappresenta una password, falso altrimenti).
     *
     * @return isGIPassword.
     */
    public boolean isGIPassword() {
        return isGIPassword;
    }

    /**
     * Imposta lo stato del gameItem (se è una password o meno).
     *
     * @param gIPassword booleano (vero / falso).
     */
    public void setGIPassword(final boolean gIPassword) {
        isGIPassword = gIPassword;
    }

    /**
     * Restituisce booleano (vero se il gameItem è indispensabile
     * al fine del proseguio del gioco, falso altrimenti).
     *
     * @return indispensable.
     */
    public boolean isIndispensable() {
        return indispensable;
    }

    /**
     * Imposta lo stato del gameItem (se è indispensabile
     * al fine del proseguio del gioco o meno).
     *
     * @param gIndispensable booleano (vero / falso).
     */
    public void setIndispensable(final boolean gIndispensable) {
        this.indispensable = gIndispensable;
    }

    /**
     * Restituisce booleano (vero se il gameItem è stato correttamente
     * inserito nell'inventario, falso altrimenti).
     *
     * @return itemCorrectlyAdded.
     */
    public boolean isItemCorrectlyAdded() {
        return itemCorrectlyAdded;
    }

    /**
     * Imposta lo stato del gameItem (se è stato
     * correttamente inserito nell'inventario o meno).
     *
     * @param iCorrectlyAdded booleano (vero / falso).
     */
    public void setItemCorrectlyAdded(final boolean iCorrectlyAdded) {
        this.itemCorrectlyAdded = iCorrectlyAdded;
    }

    /**
     * Restituisce booleano (vero se il gameItem rappresenta
     * una persona, falso altrimenti).
     *
     * @return isPerson.
     */
    public boolean isPerson() {
        return isPerson;
    }

    /**
     * Imposta lo stato del gameItem (se rappresenta
     * una persona o meno).
     *
     * @param p booleano (vero / falso).
     */
    public void setPerson(final boolean p) {
        isPerson = p;
    }

    /**
     * Restituisce booleano (vero se il gameItem rappresenta
     * un oggetto fondamentale per la vittoria del gioco, falso altrimenti).
     *
     * @return isKeyToWin.
     */
    public boolean isKeyToWin() {
        return isKeyToWin;
    }

    /**
     * Imposta lo stato del gameItem (se rappresenta
     * un oggetto fondamentale per la vittoria del gioco o meno).
     *
     * @param kToWin booleano (vero / falso).
     */
    public void setKeyToWin(final boolean kToWin) {
        isKeyToWin = kToWin;
    }

    /**
     * Restituisce l'immagine del gameItem.
     *
     * @return itemImage.
     */
    public ImageIcon getItemImage() {
        return itemImage;
    }

    /**
     * Imposta l'immagine del gameItem.
     *
     * @param iImage immagine del gameItem.
     */
    public void setItemImage(final ImageIcon iImage) {
        this.itemImage = iImage;
    }

    /**
     * Metodo generato automaticamente per confrontare
     * se due oggetti di questa classe sono uguali.
     *
     * @return codice hash dell'oggetto.
     */
    @Override
    public int hashCode() {
        int hash = Description.GAME_ITEM_H1;
        hash = Description.GAME_ITEM_H2 * hash + this.id;
        return hash;
    }

    /**
     * Permette di confrontare se due gameItem sono uguali.
     *
     * @param o oggetto da confrontare.
     * @return booleano (vero se i due gameItem sono uguali, falso altrimenti).
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameItem gameItem = (GameItem) o;
        return id == gameItem.id
                && consumable == gameItem.consumable
                && pickupable == gameItem.pickupable
                && picked == gameItem.picked
                && usableWithDrops == gameItem.usableWithDrops
                && used == gameItem.used
                && isGIPassword == gameItem.isGIPassword
                && Objects.equals(name, gameItem.name)
                && Objects.equals(description, gameItem.description)
                && Objects.equals(alias, gameItem.alias)
                && Objects.equals(descriptionUsableWithDrops,
                gameItem.descriptionUsableWithDrops)
                && Objects.equals(descriptionAlreadyUsedWithDrops,
                gameItem.descriptionAlreadyUsedWithDrops)
                && Objects.equals(descriptionUsableButItemRemoved,
                gameItem.descriptionUsableButItemRemoved)
                && Objects.equals(itemImage, gameItem.itemImage);
    }
}
