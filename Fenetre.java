/**
* @version 26/04/2022
* @author Baptiste Nevejans
*/
//inclusion des bibliothèques
import java.awt.*;
import javax.swing.*;
 
public class Fenetre extends JFrame  {
 
    private int ligne;
    private int colonne;
    private int nbrMines;
    GridLayout layout = new GridLayout(ligne, colonne);
    /*type[][] name = new type[ligne][colonne];
     * type[x][y];
     * is 1d
     * type[] name = new type[ligne*colonne];
     * type[(ligne*y)+x];*/
    private boolean[] presencemines = new boolean[ligne * colonne];
    private boolean[] clickable = new boolean[ligne * colonne];
    //private boolean lost = false;
    //private boolean won = false;
    private int[] numbers = new int[ligne * colonne];
    private boolean[] clickdone = new boolean[ligne * colonne];
    JButton[] buttons = new JButton[ligne * colonne];
    JMenuItem newGameButton = new JMenuItem("nouvelle partie");
    JMenuItem reglage = new JMenuItem("options");
    JLabel mineLabel = new JLabel("mines: " + nbrMines + " marqué: 0" + " suposition: 0");
    JPanel p = new JPanel();
 
    public Fenetre() {
        p.setLayout(layout);
        Setup1 grille1 = new Setup1();
        grille1.setSetup1(ligne, colonne, clickdone, clickable, buttons, presencemines, nbrMines, numbers);
        grille1.setupI();
        for (int i = 0; i < (ligne * colonne); i++) {
            p.add(buttons[i]);
        }
        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Menu");

        //newGameButton.addActionListener(this);
        m.add(newGameButton);
        //reglage.addActionListener(this);
        m.add(reglage);
        mb.add(m);
        this.setJMenuBar(mb);
        this.add(p);
        this.add(mineLabel, BorderLayout.SOUTH);
        this.pack();
        //reglage.addActionListener(this);
        this.setVisible(true);
    }

}