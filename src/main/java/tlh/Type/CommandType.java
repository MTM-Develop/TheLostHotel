package tlh.Type;

import java.io.Serializable;

/**
 * Classe enumerativa per definire i vari tipi di comandi presenti nel gioco.
 *
 * @author MTM-Develop.
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
     * Comando 'inserisci'.
     */
    INSERT,
    /**
     * Comando 'sockend'.
     */
    SOCKEND
}
