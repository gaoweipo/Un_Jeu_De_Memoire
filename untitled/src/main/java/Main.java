import Controleur.CarteController;
import Vue.CarteView;
import Modele.Carte;

import javax.swing.*;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        new CarteView(new ArrayList<Carte>(),new JLabel(),new CarteController());
    }
}
