package Controleur;

import javax.swing.*;
import javax.swing.Timer;

public class TempsController {
    private int tempsReste;
    private Timer countdownTemps;
    private JLabel tempsLabel;

    public TempsController() {
        this.tempsReste = 300;
        tempsLabel = new JLabel(String.valueOf(this.tempsReste));
    }

    public TempsController(int tempsReste) {
        this.tempsReste = tempsReste;
    }

    public void commencerCountdown() {
        countdownTemps = new Timer(1000, e -> {
            tempsReste--;
            updateTempsLabel();

            if (tempsReste <= 0) {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(null, "Le temps est écoulé!");
            }
        }
        );
        countdownTemps.start();
    }

    public void updateTempsLabel() {
        int minutes = tempsReste / 60;
        int secondes = tempsReste % 60;
        String tempsString = String.format("%02d", minutes, secondes);
        tempsLabel.setText((tempsString));
    }
    public void setTimer(int tempsParSeconde) {
        tempsReste = tempsParSeconde;
        updateTempsLabel();
    }
    public void resetTimer() {
        countdownTemps.stop();
        setTimer(300);
        commencerCountdown();
    }

    public void pauseTimer(){
        countdownTemps.stop();
    }
    public void playTimer(){
        countdownTemps.start();
    }

    public int getTempsReste() {
        return tempsReste;
    }

    public void setTempsReste(int tempsReste) {
        this.tempsReste = tempsReste;
    }

    public Timer getCountdownTimer() {
        return countdownTemps;
    }

    public void setCountdownTimer(Timer countdownTimer) {
        this.countdownTemps = countdownTimer;
    }

    public JLabel getTempsLabel() {
        return tempsLabel;
    }

    public void setTempsLabel(JLabel tempsLabel) {
        this.tempsLabel = tempsLabel;
    }
}
