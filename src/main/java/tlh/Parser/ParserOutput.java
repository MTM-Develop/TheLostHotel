package tlh.Parser;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe che memorizza e controlla le parole
 * contenute nella stringa inserita dall'utente.
 *
 * @author MTM-Develop.
 */
public class ParserOutput implements Iterable<WordType> {
    /**
     * Formato da coppie di tipi di parola e stringa che lo identifica
     * (es. (INEVNTORY_OBJ, "cellulare")).
     * Può contenere al più frasi formate da: un comando,
     * un oggetto dell'inventario e un oggetto della stanza.
     */
    private Map<WordType, String> parsedData = new LinkedHashMap<>();

    /**
     * Indica se il ParserOutput è vuoto.
     *
     * @return booleano che indica se la struttura
     * è vuota (true) o meno (false).
     */
    public boolean isEmpty() {
        return parsedData.isEmpty();
    }

    /**
     * Aggiunge una coppia formata da chiave (WordType) e valore (Stringa).
     *
     * @param w WordType.
     * @param s Stringa.
     */
    public void add(final WordType w, final String s) {
        parsedData.put(w, s);
    }

    /**
     * Restituisce la stringa associata alla chiave del WordType,
     * se esistente nella struttura.
     *
     * @param w WordType.
     * @return stringa, se w è presente nella struttura, null altrimenti.
     */
    public String getString(final WordType w) {
        return parsedData.get(w);
    }

    /**
     * Indica se il ParserOutput contiene il WordType w.
     *
     * @param w Wordtype.
     * @return booleano che indica se nella struttura è presente w.
     */
    public boolean containsWordType(final WordType w) {
        return parsedData.containsKey(w);
    }

    /**
     * Indica la grandezza del ParserOutput.
     *
     * @return intero.
     */
    public int size() {
        return parsedData.size();
    }

    /**
     * Restituisce un iteratore basato sull'insieme di chiavi della struttura.
     *
     * @return Iterator<WordType> iteratore basato sulla chiave WordType.
     */
    @Override
    public Iterator<WordType> iterator() {
        return parsedData.keySet().iterator();
    }

    /**
     * Crea una stringa formata dagli elementi
     * che compongono la struttura del ParserOutput.
     *
     * @return stringa.
     */
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        Iterator<WordType> i = this.iterator();

        // Finchè ci sono elementi, li concatena.
        while (i.hasNext()) {
            s.append(parsedData.get(i.next()) + " ");
        }

        return s.toString();
    }
}
