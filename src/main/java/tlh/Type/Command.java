package tlh.Type;

import tlh.Other.Description;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * Classe che rappresenta l'entità di Comando.
 *
 * @author MTM-Develop.
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
     * @param name nome del comando.
     * @param type tipo del comando.
     */
    public Command(final String name, final CommandType type) {
        this.cName = name;
        this.cType = type;
        this.cAlias = new HashSet<>();
    }

    /**
     * Costruttore.
     *
     * @param name nome del comando.
     * @param type tipo del comando.
     * @param alias alias del comando.
     */
    public Command(final String name, final CommandType type,
                   final Set<String> alias) {
        this.cName = name;
        this.cType = type;
        this.cAlias = alias;
    }

    /**
     * Restituisce il nome del comando.
     *
     * @return cName.
     */
    public String getName() {
        return cName;
    }

    /**
     * Restituisce i sinonimi del comando.
     *
     * @return cAlias.
     */
    public Set<String> getAlias() {
        return cAlias;
    }

    /**
     * Imposta gli alias del comando.
     *
     * @param alias alias del comando.
     */
    public void setAlias(final String[] alias) {
        this.cAlias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * Restituisce il tipo del comando.
     *
     * @return cType.
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
     * @param o oggetto da confrontare.
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
