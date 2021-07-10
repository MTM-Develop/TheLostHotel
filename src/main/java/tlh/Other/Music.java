/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlh.Other;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.JOptionPane;

/**
 * Classe per inserire la musica.
 */
public class Music {

    /**
     * CLip per la musica di sottofondo
     * del gioco.
     */
    private Clip clip;

    /**
     * Fa partire un thread che riproduce la musica, caricandola da file.
     *
     * @param url path del file.
     */
    public synchronized void playSound(final String url) {

        Thread musicThread = new Thread(() -> {
            try {

                clip = AudioSystem.getClip();

                try (AudioInputStream inputStream =
                             AudioSystem.getAudioInputStream(
                        new BufferedInputStream(new FileInputStream(url)))) {

                    clip.open(inputStream);
                    //acquisizione della risorsa di input
                    Thread.sleep(3000);  //thread fermo per 3 secondi

                    clip.start();        //parte la musica
                    clip.loop(Clip.LOOP_CONTINUOUSLY); //musica in loop

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            " File non trovato: Music Error");
                }
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, " Music Error");
            }
        });

        musicThread.setDaemon(true);
        musicThread.start();

    }

    /**
     * Metodo per mettere al massimo il volume della musica o per mutarla.
     *
     * @param valueToPlusMinus indica a quanto sarà settato il volume della
     * musica.
     */
    public void volumeAbsoluteControl(final Double valueToPlusMinus) {

        Mixer.Info[] mixers = AudioSystem.getMixerInfo();

        Mixer mixer = AudioSystem.getMixer(mixers[0]);
        Line.Info[] lineInfos = mixer.getTargetLineInfo();

        for (Line.Info lineInfo : lineInfos) {
            Line line = null;
            boolean opened = true;

            try {
                line = mixer.getLine(lineInfo);
                opened = line.isOpen() || line instanceof Clip;

                if (!opened) {
                    line.open();
                }

                FloatControl volControl = (FloatControl) line.getControl(
                        FloatControl.Type.VOLUME);

                Double volumeToCut = valueToPlusMinus;
                // se volumeToCut è 0.0 allora la musica verrà mutata,
                // se è 1.0 la musica verrà aumentata al massimo.
                float changedCalc = (float) ((double) volumeToCut);
                volControl.setValue(changedCalc);
                //imposta il volume della musica.

            } catch (LineUnavailableException | IllegalArgumentException e) {
            } finally {

                if (line != null && !opened) {
                    line.close();
                }
            }
        }
    }

    /**
     * @return clip per la musica del gioco.
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Imposta la clip per la musica del gioco.
     *
     * @param c
     */
    public void setClip(final Clip c) {
        this.clip = c;
    }
}
