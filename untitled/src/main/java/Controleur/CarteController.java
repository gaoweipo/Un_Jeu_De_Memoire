package Controleur;

import Modele.Carte;
import Modele.JeuCartes;
import Modele.Joueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class CarteController implements JeuCartes, ActionListener {
    private final static String COMMA_DELIMITER = ",";
    ArrayList<Integer> listValues = new ArrayList<Integer>();
    ArrayList<Carte> listCartes=new ArrayList<Carte>();
    ArrayList<Carte> paireCartes=new ArrayList<Carte>();

    TempsController compteur;

    ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    Joueur joueur;
    int tempsJouer = 0;
    int score = 0;
    public TempsController getCompteur() {
        return compteur;
    }

    public void setCompteur(TempsController timer) {
        this.compteur = compteur;
    }
    JLabel compteurLabel;

    public JLabel getCompteurLabel() {
        return compteurLabel;
    }

    public void setCompteurLabel(JLabel compteurLabel) {
        this.compteurLabel = compteurLabel;
    }

    // Modifier les codes...


    public ArrayList<Integer> getListValues() {
        return listValues;
    }

    public ArrayList<Carte> getListCartes() {
        return listCartes;
    }


    public CarteController(){
        initialiser();
    }

    public void initilizeListValues(){
        int pairs = 4*5 /2;
        Random rand = new Random();
        for(int i=1; i<=pairs; i++){
            int rand_value = rand.nextInt(10)+1;
            listValues.add(rand_value);
            listValues.add(rand_value);
        }
        Collections.shuffle(listValues);
    }
    public void showListValues(){
        System.out.println("List values");
        for (int i = 0; i < listValues.size(); i++)
            System.out.print(listValues.get(i) + " ");
        System.out.println();
    }
    public void initializeCarte(){
        System.out.println("Initialize cartes");
        for (int i = 0; i < listValues.size(); i++){
            int value = listValues.get(i);
            Carte carte = new Carte(0);
            listCartes.add(carte);
        }
    }
    public void showListCartes(){
        for (int i = 0; i < listCartes.size(); i++)
            System.out.print(listCartes.get(i).getValeur() + " ");
        System.out.println();
    }


    @Override
    public void initialiser() {
        // TODO Auto-generated method stub
        System.out.println("Bonjour !");
        initilizeListValues();
        showListValues();
        initializeCarte();

        compteur = new TempsController();
        compteurLabel = compteur.getTempsLabel();

        compteur.commencerCountdown();

        SoundEffect.init();
        SoundEffect.volume = SoundEffect.Volume.LOW;  // un-mute

        loadJoueurs();
    }


    @Override
    public void nouvellePartie() {
        // TODO Auto-generated method stub
        paireCartes.clear();
        listValues.clear();
        initilizeListValues();
        showListValues();
    }


    @Override
    public void retournerCarte(int index) {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean estTermine() {
        // TODO Auto-generated method stub
        return false;
    }

    public ArrayList<Joueur> loadJoueurs(){

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/players.csv"));
            String line;
            while ((line = br.readLine()) != null){
                String[] values = line.split(COMMA_DELIMITER);
                Joueur joueur = new Joueur(values[0], Integer.parseInt(values[1]));
                joueurs.add(joueur);
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return joueurs;
    }

    public void savePlayers(){
        try {
            FileWriter csvWriter = new FileWriter("src/players.csv");
            for (Joueur p: joueurs){
                csvWriter.append(String.join(COMMA_DELIMITER, p.getNomJoueur(), String.valueOf(p.getTempsJouer()) ));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void showPlayers(){//pas neccessaire
        for (Joueur p: joueurs){
            System.out.println("Nom de joueur " + p.getNomJoueur());
            System.out.println("Temps de jeu " + p.getTempsJouer());
        }
    }

    /**
     * Chercher un joueur par nom
     * @param username du joueur
     * @return un joueur
     */
    public Joueur findPlayerByUsername(String username){//trouver le jouer qui a deja joue,
        for (Joueur p: joueurs){
            if (p.getNomJoueur().equals(username)){
                return p;
            }
        }
        return null;
    }
    public void prepareToSave(Joueur player, int time){
        player.setTempsJouer(time);
        if (!joueurs.contains(player)){
            joueurs.add(player);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getActionCommand() == "Quit"){
            ImageIcon icon = new ImageIcon("src/images/re1.png");
            int confirm = JOptionPane.showConfirmDialog(null, "Do you want to quit a game?", "To be honest", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

            if (confirm==0){
                System.exit(0);
            }
        }
        else if (e.getActionCommand() == "Refaire"){

            ImageIcon icon = new ImageIcon("src/images/re1.png");
            int confirm = JOptionPane.showConfirmDialog(null, "Do you want to start a new game?", "To be honest", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

            if (confirm==0){
                nouvellePartie();
                ArrayList<Carte> currentList = this.getListCartes();
                for (int i = 0; i < currentList.size(); i++){
                    Carte carte = currentList.get(i);
                    carte.setValeur(listValues.get(i));

                    carte.setPhoto(0);
                    carte.setTrouvee(false);
                    carte.setRetournee(false);
                    carte.setEnabled(true);
                }


                compteur.resetTimer();
                score = 0;
            }
        }
        else if (e.getActionCommand() == "Player Info"){
            compteur.pauseTimer();
            if (joueur == null){
                String username = JOptionPane.showInputDialog("Enter your username");
                //System.out.println(username);
                joueur = findPlayerByUsername(username);
                if (joueur != null){
                    int playedTime = joueur.getTempsJouer();
                    JOptionPane.showMessageDialog(null, "Your last playing time was " + playedTime +" seconds");
                } else {
                    joueur = new Joueur(username);
                    JOptionPane.showMessageDialog(null, "Welcome " + joueur.getNomJoueur() + ". Enjoy this game");
                }
            } else {
                tempsJouer = 300 - compteur.getTempsReste();
                JOptionPane.showMessageDialog(null, joueur.getNomJoueur() + " played the game for " + tempsJouer + " seconds");

            }

            if (score < 10)
                compteur.playTimer();
        } else if (e.getActionCommand() == "Économisez du temps de jeu"){
            prepareToSave(joueur, tempsJouer);
            showPlayers();
            savePlayers();
        }
        else{

            SoundEffect.TRANSITION.play();
            Carte clickedCarte = (Carte) e.getSource();

            if (clickedCarte.isRetournee()){


                if (paireCartes.size()==1){
                    clickedCarte.setRetournee(false);
                    clickedCarte.setPhoto(0);
                    paireCartes.remove(paireCartes.get(0));
                }

                else {
                    if (paireCartes.get(0).equals(clickedCarte)){
                        paireCartes.get(1).setRetournee(false);
                        paireCartes.get(1).setPhoto(0);
                        paireCartes.remove(paireCartes.get(1));
                    }else{
                        paireCartes.get(0).setRetournee(false);
                        paireCartes.get(0).setPhoto(0);
                        paireCartes.remove(paireCartes.get(0));
                    }
                }
            }
            else {

                if (paireCartes.size()==2){
                    paireCartes.get(0).setRetournee(false);// down
                    paireCartes.get(1).setRetournee(false);// down
                    paireCartes.get(0).setPhoto(0);
                    paireCartes.get(1).setPhoto(0);
                    paireCartes.clear();
                }
                paireCartes.add(clickedCarte);
                clickedCarte.setPhoto(clickedCarte.getValeur());
                clickedCarte.setRetournee(true);
            }


            if (paireCartes.size() == 2){

                if (paireCartes.get(0).equals(paireCartes.get(1))){

                    SoundEffect.MATCH.play();
                    score++;

                    paireCartes.get(0).setTrouvee(true);
                    paireCartes.get(1).setTrouvee(true); //unused
                    paireCartes.get(0).setEnabled(false);
                    paireCartes.get(1).setEnabled(false);
                    paireCartes.clear();

                    if (score == 10) {
                        compteur.pauseTimer();
                        tempsJouer = 300 - compteur.getTempsReste();

                        ImageIcon icon = new ImageIcon("src/images/re2.png");
                        int confirm = JOptionPane.showConfirmDialog(null, "Do you want to save the playing time?", "Congratulations", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
                        // 0: yes; 1: no; 2: cancel
                        if (confirm==0){
                            if (joueur == null){
                                JOptionPane.showMessageDialog(null, "Please enter your username");
                            } else {

                                prepareToSave(joueur, tempsJouer);
                                showPlayers();
                            }
                        }
                    }
                }
                else{

                    paireCartes.get(0).setTrouvee(false);
                    paireCartes.get(1).setTrouvee(false); //unused

                }

            }

        }
    }

}
