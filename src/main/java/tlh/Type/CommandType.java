package tlh.Type;

import java.io.Serializable;

/**
 * Classe enumerativa per definire i vari tipi di comandi presenti nel gioco.
 */
public enum CommandType implements Serializable {
    /**
     * Comando 'nord'.
     */
    NORD,
    /**
     * Comando 'sud'.
     */
    SUD,
    /**
     * Comando 'est'.
     */
    EST,
    /**
     * Comando 'ovest'.
     */
    OVEST,
    /**
     * Comando 'inventario'.
     */
    INVENTORY,
    /**
     * Comando 'aiuto'.
     */
    HELP,
    /**
     * Comando 'osserva'.
     */
    LOOK,
    /**
     * Comando 'usa'.
     */
    USE,
    /**
     * Comando 'apri'.
     */
    OPEN,
    /**
     * Comando 'sposta'.
     */
    MOVE,
    /**
     * Comando 'prendi'.
     */
    PICK_UP,
    /**
     * Comando 'lascia'.
     */
    DROP,
    /**
     * Comando 'spingi'.
     */
    PUSH,
    /**
     * Comando 'inserisci'.
     */
    INSERT

    //TALK_TO, GIVE,
    //INSERT_PASS_ARCHIVE, INSERT_HEX, QUIT
    //CAMBIARE
}
