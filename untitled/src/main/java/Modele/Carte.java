package Modele;

import javax.swing.*;
import java.awt.*;

public class Carte extends JButton{
    int valeur;
    boolean retournee;
    boolean trouvee;
    Icon dosCarte=new ImageIcon(this.getClass().getResource("../images/p0.jpg"));
    public Icon getDosCarte(){
        return dosCarte;
    }
    public int getValeur(){
        return valeur;
    }
    public void setValeur(int valeur){
        this.valeur=valeur;
    }
    public boolean isRetournee(){
        return retournee;
    }
    public void setRetournee(boolean retournee){
        this.retournee=retournee;
    }
    public boolean isTrouvee(){
        return trouvee;
    }
    public void setTrouvee(boolean trouvee){
        this.trouvee=trouvee;
    }

    public Carte(int valeur) {
        this.valeur = valeur;
        this.retournee=false;
        this.trouvee=false;
    }

    public boolean isEqual(Carte autre){
        return this.equals(autre);
    }

    public void setPhoto(int nombre){
        String chemin="/images/0"+nombre+".jpg";
        Icon icon=new ImageIcon(this.getClass().getResource(chemin));
        this.setIcon(icon);
    }

    public void setPhotoTransformation(Icon iconOriginal){
        Icon icon = transformImage(iconOriginal);
        this.setIcon(icon);
    }
    private Icon transformImage(Icon iconOriginal){
        Image image=((ImageIcon)iconOriginal).getImage();
        Image nouvelleImage=image.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        Icon nouveauIcon=new ImageIcon(nouvelleImage);

        return nouveauIcon;
    }


}
