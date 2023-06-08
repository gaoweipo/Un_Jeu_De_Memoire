package Modele;

public interface JeuCartes {
    void initialiser();
    void nouvellePartie();
    void retournerCarte(int index);
    boolean estTermine();
}
