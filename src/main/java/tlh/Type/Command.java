package tlh.Type;

import tlh.Other.Description;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * Classe che rappresenta l'entità di Comando.
 */
public class Command implements Serializable {

    /**
     * Nome del comando.
     */
    private final String cName;

    /**
     * Tipo di comando.
     */
    private final CommandType cType;

    /**
     * Sinonimi del comando.
     */
    private Set<String> cAlias;

    /**
     * Costruttore.
     *
     * @param name
     * @param type
     */
    public Command(final String name, final CommandType type) {
        this.cName = name;
        this.cType = type;
        this.cAlias = new HashSet<>();
    }

    /**
     * Costruttore.
     *
     * @param name
     * @param type
     * @param alias
     */
    public Command(final String name, final CommandType type,
                   final Set<String> alias) {
        this.cName = name;
        this.cType = type;
        this.cAlias = alias;
    }

    /**
     * @return nome del comando.
     */
    public String getName() {
        return cName;
    }

    /**
     * @return sinonimi del comando.
     */
    public Set<String> getAlias() {
        return cAlias;
    }

    /**
     * Imposta i sinonimi del comando.
     *
     * @param alias
     */
    public void setAlias(final Set<String> alias) {
        this.cAlias = alias;
    }

    /**
     * Imposta gli alias del comando.
     *
     * @param alias
     */
    public void setAlias(final String[] alias) {
        this.cAlias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * @return tipo del comando.
     */
    public CommandType getcType() {
        return cType;
    }

    /**
     * Metodo generato automaticamente per confrontare
     * se due oggetti di questa classe sono uguali.
     *
     * @return codice hash dell'oggetto.
     */
    @Override
    public int hashCode() {
        int hash = Description.COMMAND_H1;
        hash = Description.COMMAND_H2 * hash + Objects.hashCode(this.cName);
        return hash;
    }

    /**
     *  Permette di confrontare se due comandi sono uguali.
     *
     * @param o
     * @return booleano (vero se i due comandi sono uguali, falso altrimenti).
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Command command = (Command) o;
        return Objects.equals(cName, command.cName) && cType == command.cType
                && Objects.equals(cAlias, command.cAlias);
    }
}
