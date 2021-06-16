package TheLostHotel.Parser;

/**
 * Enumerativo che definisce il tipo di parola tra:
 * comando, oggetto dell'inventario, oggetto della stanza
 *
 * è di supporto per il parser, parseroutput e per la gestione del gioco.
 */
public enum WordType{
    INVENTORY_OBJ, ROOM_OBJ, COMMAND, CON, ERROR
}
