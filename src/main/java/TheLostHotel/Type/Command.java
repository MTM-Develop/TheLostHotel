package TheLostHotel.Type;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * Classe che rappresenta l'entità di Comando
 */
public class Command implements Serializable {

    private final String name; // Nome del comando
    private final CommandType cType; // Tipo di comando
    private Set<String> alias; // Sinonimi del comando

    // Costruttori

    public Command(String name, CommandType cType) {
        this.name = name;
        this.cType = cType;
        this.alias = new HashSet<>();
    }

    public Command(String name, CommandType cType, Set<String> alias) {
        this.name = name;
        this.cType = cType;
        this.alias = alias;
    }

    // Metodi di get, set, equals e hash code

    public String getName() {
        return name;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public CommandType getcType() {
        return cType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Command other = (Command) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
