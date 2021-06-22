package TheLostHotel.Type;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;

/**
 * Classe utilizzata per rappresentare gli oggetti che saranno presenti nel gioco
 */
public class GameItem implements Serializable {

    private final int id;
    private String name;
    private String description; // descrizione dell'oggetto
    private Set<String> alias; // sinonimi dell'oggetto
    private String descriptionUsableWithDrops;
    private String descriptionAlreadyUsedWithDrops;
    private String descriptionUsableButItemRemoved;

    private byte consumable = -1; // indica il numero di usi possibili, -1 significa che ha infiniti usi
    private boolean pickupable = false; //indica se si può raccogliere
    private boolean picked = false;
    private boolean pushable = false; // indica se si può spingere
    private boolean push = false; // indica se è già stato spinto/premuto
    private boolean useless = false; //indica se è utile o meno
    private boolean usableWithDrops = false; //indica che un oggetto contenitore si può usare e droppa oggetti
    private boolean used = false; //indica se è stato usato o meno

    private ImageIcon itemImage;   //immagine raffigurante l'oggetto

    // Costruttori


    public GameItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GameItem(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = new HashSet<>();
    }

    public GameItem(int id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    // Metodi di get, set, equals e hash code

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public String getDescriptionUsableWithDrops() {
        return descriptionUsableWithDrops;
    }

    public void setDescriptionUsableWithDrops(String descriptionUsableWithDrops) {
        this.descriptionUsableWithDrops = descriptionUsableWithDrops;
    }

    public String getDescriptionAlreadyUsedWithDrops() {
        return descriptionAlreadyUsedWithDrops;
    }

    public void setDescriptionAlreadyUsedWithDrops(String descriptionAlreadyUsedWithDrops) {
        this.descriptionAlreadyUsedWithDrops = descriptionAlreadyUsedWithDrops;
    }

    public String getDescriptionUsableButItemRemoved() {
        return descriptionUsableButItemRemoved;
    }

    public void setDescriptionUsableButItemRemoved(String descriptionUsableButItemRemoved) {
        this.descriptionUsableButItemRemoved = descriptionUsableButItemRemoved;
    }

    public byte getConsumable() {
        return consumable;
    }

    /**
     * Indica se l'oggetto non ha più usi possibili
     * @return booleano, true se l'oggetto è consumato e non più usabile
     *          falso altrimenti
     */
    public boolean isConsumed() {
        return this.getConsumable() == 0;
    }

    public void setConsumable(byte consumable) {
        this.consumable = consumable;
    }

    /**
     * Decrementa di uno il numero di utilizzi di un oggetto se esso è consumabile
     */
    public void consume() {
        if (consumable > 0) {
            this.setConsumable((byte) (consumable - 1));
        }
    }

    public boolean isPickupable() {
        return pickupable;
    }

    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    public boolean isPushable() {
        return pushable;
    }

    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public boolean isUseless() {
        return useless;
    }

    public void setUseless(boolean useless) {
        this.useless = useless;
    }

    public boolean isUsableWithDrops() {
        return usableWithDrops;
    }

    public void setUsableWithDrops(boolean usableWithDrops) {
        this.usableWithDrops = usableWithDrops;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public ImageIcon getItemImage() {
        return itemImage;
    }

    public void setItemImage(ImageIcon itemImage) {
        this.itemImage = itemImage;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
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
        final GameItem other = (GameItem) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
