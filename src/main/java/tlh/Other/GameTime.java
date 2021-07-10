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
 * Classe realizzata per calcolare il tempo
 * impiegato dal giocatore per concludere il gioco.
 */
public class GameTime implements Serializable {

    /**
     * Numero di versione utilizzato per la deserializzazione.
     */
    static final long serialVersionUID = 42L;

    /**
     * Tempo calcolato in secondi.
     */
    private int secondPassed = 0;

    /**
     * Timer per lo scorrere del tempo.
     */
    private transient Timer time;

    /**
     * Task che fa avanzare il Timer.
     */
    private transient TimerTask task;

    /**
     * Indica se il timer è attivo o meno.
     */
    private transient boolean active = false;

    /**
     * Istanzia timer e task, imposta lo stato
     * ad attivo e fa partire il timer.
     */
    public void start() {
        time = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                // Ad ogni secondo il contatore dovrà incrementarsi.
                secondPassed++;

            }

        };
        active = true;
        //Avvia il task dopo 1 secondo e lo ripete ogni secondo.
        time.scheduleAtFixedRate(task, Description.GAME_TIME_DELAY_TIMER,
                Description.GAME_TIME_PERIOD_TIMER);

    }

    /**
     * Ferma il contatore del tempo.
     *
     * @return booleano che indica se si è fermato correttamente.
     */
    public boolean cancel() {

        // Se l'ha fermato correttamente
        if (task.cancel()) {
            this.setActive(false); // lo stato non è più attivo
            return true;
        }

        return false;
    }

    /**
     * @return tempo calcolato in secondi.
     */
    public int getSecondPassed() {
        return secondPassed;
    }

    /**
     * Imposta il tempo calcolato in secondi.
     *
     * @param sPassed
     */
    public void setSecondPassed(final int sPassed) {
        this.secondPassed = sPassed;
    }

    /**
     * @return Timer per lo scorrere del tempo.
     */
    public Timer getTimer() {
        return time;
    }

    /**
     * Imposta il Timer per lo scorrere del tempo.
     *
     * @param t
     */
    public void setTimer(final Timer t) {
        this.time = t;
    }

    /**
     * @return Task che fa avanzare il Timer.
     */
    public TimerTask getTask() {
        return task;
    }

    /**
     * Imposta il task che fa avanzare il Timer.
     *
     * @param t
     */
    public void setTask(final TimerTask t) {
        this.task = t;
    }

    /**
     * @return booleano (vero se il timer è attivo,
     * falso altrimenti).
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Imposta il timer (se attivo o meno).
     *
     * @param tActive
     */
    public void setActive(final boolean tActive) {
        this.active = tActive;
    }

    /**
     * Conversione del tempo calcolato in secondi nel formato
     * ore(h), minuti(m), secondi(s).
     *
     * @return Stringa che indica il tempo.
     */
    public String getTime() {

        int hours = 0;
        int remainderOfHours;
        int minutes = 0;
        int seconds = 0;
        String strHours;
        String strMinutes;
        String strSeconds;
        String gameTime;

        if (secondPassed >= Description.GAME_TIME_SECOND_PASSED_3600) {

            hours = secondPassed / Description.GAME_TIME_SECOND_PASSED_3600;
            remainderOfHours = secondPassed
                    % Description.GAME_TIME_SECOND_PASSED_3600;

            if (remainderOfHours >= Description.GAME_TIME_SECOND_PASSED_60) {

                minutes = remainderOfHours
                        / Description.GAME_TIME_SECOND_PASSED_60;
                seconds = remainderOfHours
                        % Description.GAME_TIME_SECOND_PASSED_60;

            } else {
                seconds = remainderOfHours;
            }

        } else if (secondPassed >= Description.GAME_TIME_SECOND_PASSED_60) {

            hours = 0;
            minutes = secondPassed / Description.GAME_TIME_SECOND_PASSED_60;
            seconds = secondPassed % Description.GAME_TIME_SECOND_PASSED_60;

        } else if (secondPassed < Description.GAME_TIME_SECOND_PASSED_60) {

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

        gameTime = strHours + "h" + strMinutes + "m" + strSeconds + "s";

        return gameTime;
    }
}
