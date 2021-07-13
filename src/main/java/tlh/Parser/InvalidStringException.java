package tlh.Parser;

/**
 * Classe per l'eccezione di stringa non valida incontrata nel Parser.
 *
 * @author MTM-Develop.
 */
public class InvalidStringException extends Exception {

    /**
     * Messaggio dell'eccezione.
     */
    public InvalidStringException() {
        super("Stringa non valida.");
    }
}
