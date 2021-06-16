package TheLostHotel.Type;

import java.io.Serializable;

/**
 *Classe enumerativa per definire i vari tipi di comandi presenti nel gioco
 *
 */
public enum CommandType implements Serializable {
    END, INVENTORY, HELP, NORD, SOUTH, EAST, WEST, CRAWL, OPEN, CLOSE, DROP, PUT, PUSH,
    ATTACK, EQUIP, UNEQUIP, MOVE, PICK_UP, TALK_TO, GIVE, INSERT_PASS_STRONGBOX,
    INSERT_PASS_ARCHIVE, INSERT_HEX, USE, LOOK, EXAMINE, SAVE, LOAD
    //CAMBIARE
}
