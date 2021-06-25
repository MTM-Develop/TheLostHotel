package tlh.File;

import tlh.Other.GameDescription;
import tlh.Type.Command;
import tlh.Type.Inventory;
import tlh.Type.Room;

import java.io.*;
import java.util.HashSet;

public class File {

    /**
     * Salva in un file binario le entità che compongono il gioco.
     *
     * @param path path del file in cui salvare i dati.
     * @param gameDescription gioco da salvare.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveFile(final String path, final GameDescription gameDescription)
            throws FileNotFoundException, IOException {

        // Crea l'output stream per salvare gli oggetti
        FileOutputStream fOut = new FileOutputStream(path);
        ObjectOutputStream objOut = new ObjectOutputStream(fOut);

        objOut.writeObject(gameDescription.getCommands());
        objOut.writeObject(gameDescription.getInventory());
        objOut.writeObject(gameDescription.getCurrentRoom());

        //objOut.writeInt(g.getGameTime().getSecondPassed());

        objOut.close();
        fOut.close();
    }

    /**
     * Carica da file le entità del gioco.
     *
     * @param path del file binario da cui caricare.
     * @param gameDescription gioco in cui caricare i dati.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readFile(final String path, final GameDescription gameDescription)
            throws IOException, ClassNotFoundException {

        // Crea l'input stream per leggere gli oggetti
        FileInputStream fIn = new FileInputStream(path);
        ObjectInputStream objIn = new ObjectInputStream(fIn);

        gameDescription.setCommands((HashSet<Command>) objIn.readObject());
        gameDescription.setInventory((Inventory) objIn.readObject());
        gameDescription.setCurrentRoom((Room) objIn.readObject());

        //g.getGameTime().setSecondPassed((int) objIn.readInt());

        objIn.close();
        fIn.close();
    }
}
