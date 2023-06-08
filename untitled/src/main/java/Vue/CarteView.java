package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Controleur.CarteController;
import Modele.Carte;

public class CarteView extends JFrame {
    private JPanel panel;
    private JButton nouveauJeu;
    private JMenuBar menuBar;
    private JMenu jeuMenu, aideMenu, tempsMenu, joueurMenu;
    private JMenuItem jeuRefaire, jeuQuitter, aideInstructions, aideAPropos, joueurInfo, joueurEnregistrer;
    private JLabel compteurLabel;

    public CarteView(ArrayList<Carte> listCartes, JLabel compteurLabel,CarteController controller) {

        //Create le menuBar:
        menuBar=new JMenuBar();

        // Ajouter le menu de "Jeu":
        jeuMenu=new JMenu("Jeu");
        jeuRefaire=new JMenuItem("Redémarrer");
        jeuQuitter=new JMenuItem("Quitter");

        jeuMenu.add(jeuRefaire);
        jeuMenu.add(jeuQuitter);

        menuBar.add(jeuMenu);

        //Ajouter le menu de "Aide":
        aideMenu=new JMenu("Aide");
        aideInstructions=new JMenuItem("Instructions");
        aideAPropos=new JMenuItem("À propos");

        aideMenu.add(aideInstructions);
        aideMenu.add(aideInstructions);

        menuBar.add(aideMenu);

        //Ajouter le menu de "tempsMenu":
        tempsMenu=new JMenu("Temps");
        menuBar.add(tempsMenu);

        //Ajouter le menu de "joueur":
        joueurMenu=new JMenu("Joueur");
        joueurInfo=new JMenuItem("Info de joueur");
        joueurEnregistrer=new JMenuItem("Gagner du temps");

        joueurMenu.add(joueurInfo);
        joueurMenu.add(joueurEnregistrer);

        menuBar.add(joueurMenu);

        setJMenuBar(menuBar);

        // Ajouter les actions:
        jeuRefaire.addActionListener(controller);
        jeuQuitter.addActionListener(controller);
        joueurInfo.addActionListener(controller);
        joueurEnregistrer.addActionListener(controller);

        //Temps Controller

        //Create le panel:
        panel=new JPanel();
        setSize(1000,800);
        setLocationRelativeTo(null);

        GridLayout gridLayout=new GridLayout(4,5);
        setLayout(gridLayout);

//        CarteController listCartes=new CarteController();
        for (int i = 0; i < listCartes.size(); i++) {
            Carte carte=listCartes.get(i);
            carte.setPreferredSize((new Dimension(200,200)));
            carte.setPhotoTransformation(carte.getDosCarte());
            carte.setRetournee(false);
            carte.addActionListener(controller);
            panel.add(carte);
        }

        //Ajouter les éléments dans le panel:
        add(panel, BorderLayout.CENTER);

        //Ajouter le compteur:
        this.compteurLabel=compteurLabel;
        add(this.compteurLabel,BorderLayout.SOUTH);

        //Configurer le JFrame:
        setTitle("Mémoire Jeu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
}
