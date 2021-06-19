package TheLostHotel.Type;

import java.io.Serializable;

/**
 *Classe enumerativa per definire i vari tipi di comandi presenti nel gioco
 *
 */
public enum CommandType implements Serializable {
    NORD, SOUTH, EAST, WEST, INVENTORY, HELP, LOOK, USE, OPEN, MOVE, PICK_UP, DROP, PUSH, QUIT,

    TALK_TO, GIVE, INSERT_PASS_STRONGBOX,
    INSERT_PASS_ARCHIVE, INSERT_HEX,
    //CAMBIARE
}
