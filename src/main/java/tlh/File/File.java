package tlh.File;

import tlh.Other.GameDescription;
import tlh.Type.Command;
import tlh.Type.Inventory;
import tlh.Type.Room;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashSet;

/**
 * Classe che gestisce il salvataggio
 * e caricamento su file.
 *
 * @author MTM-Develop.
 */
public class File {

    /**
     * Salva in un file binario le entità che compongono il gioco.
     *
     * @param path path del file in cui salvare i dati.
     * @param gDescription gioco da salvare.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveFile(final String path, final GameDescription gDescription)
            throws FileNotFoundException, IOException {

        // Crea l'output stream per salvare gli oggetti.
        FileOutputStream fOut = new FileOutputStream(path);
        ObjectOutputStream objOut = new ObjectOutputStream(fOut);

        objOut.writeObject(gDescription.getCommands());
        objOut.writeObject(gDescription.getInventory());
        objOut.writeObject(gDescription.getCurrentRoom());
        objOut.writeUTF(gDescription.getPlayer());
        objOut.writeInt(gDescription.getgTime().getSecondPassed());

        objOut.close();
        fOut.close();
    }

    /**
     * Carica da file le entità del gioco.
     *
     * @param path del file binario da cui caricare.
     * @param gDescription gioco in cui caricare i dati.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readFile(final String path, final GameDescription gDescription)
            throws IOException, ClassNotFoundException {

        // Crea l'input stream per leggere gli oggetti.
        FileInputStream fIn = new FileInputStream(path);
        ObjectInputStream objIn = new ObjectInputStream(fIn);

        gDescription.setCommands((HashSet<Command>) objIn.readObject());
        gDescription.setInventory((Inventory) objIn.readObject());
        gDescription.setCurrentRoom((Room) objIn.readObject());
        gDescription.setPlayer((String) objIn.readUTF());
        gDescription.getgTime().setSecondPassed((int) objIn.readInt());

        objIn.close();
        fIn.close();
    }
}
