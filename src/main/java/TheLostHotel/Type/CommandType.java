package TheLostHotel.Type;

import java.io.Serializable;

/**
 *Classe enumerativa per definire i vari tipi di comandi presenti nel gioco
 *
 */
public enum CommandType implements Serializable {
    NORD, SUD, EST, OVEST, INVENTORY, HELP, LOOK, USE, OPEN, MOVE, PICK_UP, DROP, PUSH, INSERT,

    TALK_TO, GIVE,
    INSERT_PASS_ARCHIVE, INSERT_HEX, QUIT
    //CAMBIARE
}
