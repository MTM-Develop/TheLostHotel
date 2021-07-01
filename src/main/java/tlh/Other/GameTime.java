/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlh.Other;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe realizzata per calcolare il tempo impiegato dal giocatore per
 * concludere il gioco
 */
public class GameTime implements Serializable {

    private int secondPassed = 0; // Tempo passato in secondi
    private Timer time;           // Timer per lo scorrere del tempo
    private TimerTask task;       // Task che fa avanzare il Timer
    private boolean active = false; // Indica se il timer è attivo o meno

    /**
     * Istanzia timer e task, imposta lo stato ad attivo e fa partire il timer
     */
    public void start() {
        time = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                // ad ogni secondo il contatore dovrà incrementarsi
                secondPassed++;

            }

        };
        active = true;
        time.scheduleAtFixedRate(task, 1000, 1000);    //avvia il task dopo 1 secondo e lo ripete ogni secondo

    }

    /**
     * Ferma il contatore del tempo
     *
     * @return booleano che indica se si è fermato correttamente
     */
    public boolean cancel() {

        // Se l'ha fermato correttamente
        if (task.cancel()) {

            this.setActive(false); // lo stato non è più attivo
            return true;

        }
        return false;
    }

    // Metodi get e set

    public int getSecondPassed() {
        return secondPassed;
    }

    public void setSecondPassed(int secondPassed) {
        this.secondPassed = secondPassed;
    }

    public Timer getTimer() {
        return time;
    }

    public void setTimer(Timer time) {
        this.time = time;
    }

    public TimerTask getTask() {
        return task;
    }

    public void setTask(TimerTask task) {
        this.task = task;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Conversione del tempo calcolato in secondi nel formato
     * ore(h),minuti(m),secondi(s)
     *
     * @return Stringa che indica il tempo
     */
    public String getTime() {

        int hours = 0;
        int remainderOfHours;
        int minutes = 0;
        int seconds = 0;
        String strHours;
        String strMinutes;
        String strSeconds;
        String time;

        if (secondPassed >= 3600) {

            hours = secondPassed / 3600;
            remainderOfHours = secondPassed % 3600;

            if (remainderOfHours >= 60) {

                minutes = remainderOfHours / 60;
                seconds = remainderOfHours % 60;

            } else {

                seconds = remainderOfHours;
            }

        } else if (secondPassed >= 60) {

            hours = 0;
            minutes = secondPassed / 60;
            seconds = secondPassed % 60;

        } else if (secondPassed < 60) {

            hours = 0;
            minutes = 0;
            seconds = secondPassed;
        }

        if (seconds < Description.GAME_TIME_ZERO_FIRST_NUMBER) {

            strSeconds = "0" + Integer.toString(seconds);

        } else {

            strSeconds = Integer.toString(seconds);
        }

        if (minutes < Description.GAME_TIME_ZERO_FIRST_NUMBER) {

            strMinutes = "0" + Integer.toString(minutes);

        } else {

            strMinutes = Integer.toString(minutes);
        }

        if (hours < Description.GAME_TIME_ZERO_FIRST_NUMBER) {

            strHours = "0" + Integer.toString(hours);

        } else {

            strHours = Integer.toString(hours);
        }

        time = strHours + "h" + strMinutes + "m" + strSeconds + "s";

        return time;
    }

}

