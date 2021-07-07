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
    private boolean usableWithDrops = false; //METTERE IN GameItemContainer?

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
    private boolean indispensable = false; //CAMBIARE

    /**
     * Immagine raffigurante l'oggetto.
     */
    private ImageIcon itemImage;

    /**
     * Costruttore.
     *
     * @param gId
     * @param gIName
     * @param gIDescription
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
     * @param gId
     * @param gIName
     * @param gIDescription
     * @param gIAlias
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
     * @return nome del gameItem.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome del gameItem.
     *
     * @param gIName
     */
    public void setName(final String gIName) {
        this.name = gIName;
    }

    /**
     * @return descrizione base del gameItem.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione base del gameItem.
     *
     * @param desc
     */
    public void setDescription(final String desc) {
        this.description = desc;
    }

    /**
     * @return sinonimi del gameItem.
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * Imposta i sinonimi del gameItem.
     *
     * @param gIAlias
     */
    public void setAlias(final Set<String> gIAlias) {
        this.alias = gIAlias;
    }

    /**
     * Imposta i sinonimi del gameItem.
     *
     * @param gIAlias
     */
    public void setAlias(final String[] gIAlias) {
        this.alias = new HashSet<>(Arrays.asList(gIAlias));
    }

    /**
     * @return descrizione del gameItem (se è utilizzabile e,
     * dopo l'utilizzo, droppa altri gameItem).
     */
    public String getDescriptionUsableWithDrops() {
        return descriptionUsableWithDrops;
    }

    /**
     * Imposta la descrizione del gameItem (se è utilizzabile e,
     * dopo l'utilizzo, droppa altri gameItem).
     *
     * @param descriptionUWD
     */
    public void setDescriptionUsableWithDrops(final String descriptionUWD) {
        this.descriptionUsableWithDrops = descriptionUWD;
    }

    /**
     * @return descrizione del gameItem (se è stato già utilizzato
     * e ha droppato altri gameItem).
     */
    public String getDescriptionAlreadyUsedWithDrops() {
        return descriptionAlreadyUsedWithDrops;
    }

    /**
     * Imposta la descrizione del gameItem (se è stato già utilizzato
     * e ha droppato altri gameItem).
     *
     * @param descriptionAUWD
     */
    public void setDescriptionAlreadyUsedWithDrops(
            final String descriptionAUWD) {
        this.descriptionAlreadyUsedWithDrops = descriptionAUWD;
    }

    /**
     * @return descrizione del gameItem (se è utilizzabile
     * ma non ci sono oggetti dentro necessari per il suo funzionamento).
     */
    public String getDescriptionUsableButItemRemoved() {
        return descriptionUsableButItemRemoved;
    }

    /**
     * Imposta la descrizione del gameItem (se è utilizzabile
     * ma non ci sono oggetti dentro necessari per il suo funzionamento).
     *
     * @param descriptionUBIR
     */
    public void setDescriptionUsableButItemRemoved(
            final String descriptionUBIR) {
        this.descriptionUsableButItemRemoved = descriptionUBIR;
    }

    /**
     * @return numero di usi possibili del gameItem.
     */
    public byte getConsumable() {
        return consumable;
    }

    /**
     * Imposta il numero di usi possibili del gameItem.
     *
     * @param gIConsumable
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
     * @return booleano (vero se è possibile prendere il gameItem,
     * falso altrimenti).
     */
    public boolean isPickupable() {
        return pickupable;
    }

    /**
     * Imposta lo stato del gameItem (se è possibile prenderlo o meno).
     *
     * @param p
     */
    public void setPickupable(final boolean p) {
        this.pickupable = p;
    }

    /**
     * @return booleano (vero se il gameItem è stato preso, falso altrimenti).
     */
    public boolean isPicked() {
        return picked;
    }

    /**
     * Imposta lo stato del gameItem (se è stato preso o meno).
     *
     * @param p
     */
    public void setPicked(final boolean p) {
        this.picked = p;
    }

    /**
     * @return booleano (vero se il gameItem è usabile e,
     * non appena viene utilizzato, droppa uno o più gameItem,
     * falso altrimenti).
     */
    public boolean isUsableWithDrops() {
        return usableWithDrops;
    }

    /**
     * Imposta lo stato del gameItem (se è usabile e,
     * non appena viene utilizzato, droppa uno o più gameItem, o meno).
     *
     * @param uWithDrops
     */
    public void setUsableWithDrops(final boolean uWithDrops) {
        this.usableWithDrops = uWithDrops;
    }

    /**
     * @return booleano (vero se il gameItem è stato usato, falso altrimenti).
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Imposta lo stato del gameItem (se è stato usato o meno).
     *
     * @param u
     */
    public void setUsed(final boolean u) {
        this.used = u;
    }

    /**
     * @return booleano (vero se il gameItem
     * rappresenta una password, falso altrimenti).
     */
    public boolean isGIPassword() {
        return isGIPassword;
    }

    /**
     * Imposta lo stato del gameItem (se è una password o meno).
     *
     * @param gIPassword
     */
    public void setGIPassword(final boolean gIPassword) {
        isGIPassword = gIPassword;
    }

    /**
     * @return booleano (vero se il gameItem è indispensabile
     * al fine del proseguio del gioco, falso altrimenti).
     */
    public boolean isIndispensable() {
        return indispensable;
    }

    /**
     * Imposta lo stato del gameItem (se è indispensabile
     * al fine del proseguio del gioco o meno).
     *
     * @param indispensable
     */
    public void setIndispensable(boolean indispensable) {
        this.indispensable = indispensable;
    }

    /**
     * @return immagine del gameItem.
     */
    public ImageIcon getItemImage() {
        return itemImage;
    }

    /**
     * Imposta l'immagine del gameItem.
     *
     * @param iImage
     */
    public void setItemImage(final ImageIcon iImage) {
        this.itemImage = iImage;
    }

    /**
     *  Metodo generato automaticamente per confrontare
     *  se due oggetti di questa classe sono uguali.
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
     * @param o
     * @return booleano (vero se i due gameItem sono uguali, falso altrimenti).
     */
    @Override
    public boolean equals(Object o) {
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
