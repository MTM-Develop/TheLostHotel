package tlh.Parser;

/**
 * Enumerativo che definisce il tipo di parola tra:
 * comando, oggetto dell'inventario, oggetto della stanza,
 * errore e parole chiave ('con' e 'in').
 *
 * È di supporto per il Parser, ParserOutput e per la gestione del gioco.
 *
 * @author MTM-Develop.
 */
public enum WordType {
    /**
     * Definisce un comando.
     */
    COMMAND,
    /**
     * Definisce un oggetto dell'inventario.
     */
    INVENTORY_OBJ,
    /**
     * Definisce un oggetto della stanza.
     */
    ROOM_OBJ,
    /**
     * Definisce una parola errata.
     */
    ERROR,
    /**
     * Definisce la parola chiave 'con'.
     */
    CON,
    /**
     * Definisce la parola chiave 'in'.
     */
    IN
}
