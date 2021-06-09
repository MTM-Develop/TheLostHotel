package TheLostHotel.Parser;

import TheLostHotel.Type.*;

import java.util.Set;

public class Parser {
    /**
     * Prende la stringa dell'utente e le diverse entità che compongono il
     * gioco, come ad esempio l'inventario del giocatore e la stanza attuale, e
     * ritorna un ParserOutput in cui vengono associate alle diverse parole
     * "rilevanti" per il gioco un enumerativo che definisce se sono comandi,
     * oggetti dell'inventario oppure oggetti della stanza.
     *
     * Il Parser salta gli articoli, gli aggettivi e gli avverbi non rilevanti e
     * rimuove qualunque carattere speciale o numero.
     *
     * @param phrase comando preso in input dall'utente
     * @param currentRoom stanza attuale in cui si trova il giocatore
     * @param inv inventario del giocatore
     * @param commands comandi del gioco
     * @return oggetto di tipo ParserOutput
     *
     * //@throws InvalidStringException se trova una stringa non valida ad esempio
     * composta da 0 o 2+ comandi
     */
    public ParserOutput parse(String phrase, Room currentRoom, Inventory inv, Set<Command> commands) {

        ParserOutput pOutput = new ParserOutput();

        // Rimuove punteggiatura, caratteri speciali e cifre e tokenizza la stringa in base agli spazi
        String[] tokens = phrase.replaceAll("[^a-zA-Zùìèéàò]", " ").toLowerCase().split("\\s+");

        // Cerca un comando
        for (String t : tokens) {

            // Se è il primo e unico comando che trova, lo salva
            if (this.isCommand(t, commands) && pOutput.isEmpty()) {
                pOutput.add(WordType.COMMAND, t);

            } // Se trova un secondo comando, la stringa non è valida
            else if (this.isCommand(t, commands) && !pOutput.isEmpty()) {

                //throw new InvalidStringException(); "Stringa non valida"

            }

        }

        // Se ha trovato un comando, cerco gli oggetti con cui si vuole interagire, se presenti
        if (!pOutput.isEmpty()) {

            String s;

            // Ciclo con indice poiché serve mantenere il contatore
            for (short i = 0; i < tokens.length; i++) {

                // Controlla se il token corrispondente è un oggetto dell'inventario
                if (!(s = this.isItem(tokens[i], inv, tokens, i)).isEmpty()) {

                    pOutput.add(WordType.INVENTORY_OBJ, s);

                }
                
                // Controlla se il token corrispondente è un oggetto della stanza
                if (!(s = this.isItem(tokens[i], currentRoom.getItemList(), tokens, i)).isEmpty()) {

                    pOutput.add(WordType.ROOM_OBJ, s);

                }

            }

        } else { // Se non ha trovato nemmeno un comando
            //throw new InvalidStringException();
        }

        return pOutput;
    }

    /**
     * Controlla se il token è un comando valido per il gioco
     *
     * @param s token
     * @param commands comandi del gioco
     * @return booleano, true se è un comando, false altrimenti
     */
    private boolean isCommand(String s, Set<Command> commands) {

        for (Command c : commands) {

            if (c.getName().equals(s) || c.getAlias().contains(s)) {

                return true;

            }

        }

        return false;
    }

    /**
     * Controlla se il token passato è un oggetto dell'inventario ( dell'utente,
     * della stanza o di un contenitore ).
     *
     * Riconosce oggetti formati da una o due parole, ad esempio anche "chiave
     * rossa".
     *
     * @param s token
     * @param inv inventario
     * @param tokens insieme di tokens della stringa scritta dall'utente
     * @param counter indica a che punto della frase si è arrivati, è utilizzato
     * per il riconoscimento di oggetti il cui nome è formato da due parole.
     *
     * @return stringa che indica il nome dell'item
     */
    private String isItem(String s, Inventory inv, String[] tokens, short counter) {

        String s1;
        try {

            for (GameItem i : inv.getInventoryList()) {

                // Se il nome corrisponde precisamente al nome di i o è presente negli alias
                if (i.getName().equals(s) || i.getAlias().contains(s)) {

                    return s;

                } // Altrimenti se il nome di i inizia con il token, controlla se anche il successivo combacia
                else if (i.getName().startsWith(s)) {

                    // Controlla il token successivo
                    if (i.getName().contains(tokens[counter + 1])) {

                        // Ritorna la stringa formata dalla parte inziale del nome e dalla seconda
                        // (e.g. "chiave" + " " + "rossa")
                        return s + " " + tokens[counter + 1];

                    }

                } /* Altrimenti se l'oggetto i è un contenitore controlla che
                    il token non sia di un oggetto contenuto al suo interno
                 */ else if (i instanceof GameItemContainer) {

                    // Chiamata ricorsiva sul contenitore
                    if (!(s1 = isItem(s, ((GameItemContainer) i).getcItemList(), tokens, counter)).isEmpty()) {
                        return s1;
                    }
                }

            }

            return "";

        } // Se si cerca la parola successiva a quella di fine stringa si genera l'eccezione
        catch (ArrayIndexOutOfBoundsException e) {

            return "";

        }

    }
}
