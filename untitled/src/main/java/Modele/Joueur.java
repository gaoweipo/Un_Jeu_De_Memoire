package Modele;

public class Joueur {
    private String nomJoueur;
    private int tempsJouer;
    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public int getTempsJouer() {
        return tempsJouer;
    }
    public void setTempsJouer(int tempsJouer) {
        this.tempsJouer = tempsJouer;
    }

    public Joueur() {
        this.nomJoueur="Non de joueur";
        this.tempsJouer=0;
    }

    public Joueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
        this.tempsJouer=0;
    }

    public Joueur(String nomJoueur, int tempsJouer) {
        this.nomJoueur = nomJoueur;
        this.tempsJouer = tempsJouer;
    }
}
