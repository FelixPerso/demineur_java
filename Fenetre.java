/**
* @version 26/04/2022
* @author Baptiste Nevejans
*/
/**inclusion des bibliothèques*/
import java.awt.*;
import javax.swing.*;
import java.io.*;
 
public class Fenetre {
    private JFrame fenetre = new JFrame();
    private int ligne = 30;
    private int colonne = 30;
    private int nbrMines = 899;
    private boolean[] presencemines = new boolean[ligne * colonne];
    private boolean[] clickable = new boolean[ligne * colonne];
    private int[] numbers = new int[ligne * colonne];
    private boolean[] clickdone = new boolean[ligne * colonne];
    private boolean lost;
    JMenuItem quitter2 = new JMenuItem("quitter");
    JButton[] buttons = new JButton[ligne * colonne];
    JMenuItem newGameButton = new JMenuItem("nouvelle partie");
    JMenuItem reglage = new JMenuItem("option");
    JPanel p = new JPanel();
    boolean nouveau;
    private JButton sauvquit= new JButton("Sauvegarder Quitter");

    
   public void setFenetre(int ligne,int colonne,int nbrMines,boolean nouveau) {
       this.ligne=ligne;
       this.colonne=colonne;
       this.nbrMines=nbrMines;
       this.nouveau=nouveau;
   }

   public void reprisepartie(JLabel mineLabel){

        int n = 0;
                    try{
                FileInputStream fichier = new FileInputStream("save.dat");
                DataInputStream data = new DataInputStream(fichier);
                colonne=data.readInt();
                ligne=data.readInt(); 

                for(int x=0;x<ligne ;x++){
                    for(int y= 0;y<colonne;y++) {
                        numbers[(ligne*y+x)] = data.readByte();
                        System.out.println("nombre");
                    }
                }
                for(int x=0;x<ligne ;x++){
                    for(int y= 0;y<colonne;y++) {
                         if(data.readBoolean() == true){
                            presencemines[ligne*y+x]=true;
                            System.out.println("true");
                        } else {
                            presencemines[ligne*y+x]=false;
                            System.out.println("false");
                        }
                    }
                }
                    for(int x=0;x<ligne ;x++){
                    for(int y= 0;y<colonne;y++) {
                        if(data.readBoolean() == true){
                            clickable[ligne*y+x]=true;
                            System.out.println("true");
                        } else {
                            clickable[ligne*y+x]=false;
                            System.out.println("false");
                        }
                    }
                }
                   for(int x=0;x<ligne ;x++){
                    for(int y= 0;y<colonne;y++) {
                        clickdone[(ligne * y) + x] = false;
                        buttons[(ligne * y) + x] = new JButton( );
            buttons[(ligne * y) + x].setPreferredSize(new Dimension(35, 35));
            ActionButton newbut = new ActionButton(ligne, colonne, clickdone, clickable, buttons, presencemines, 
        nbrMines, numbers,quitter2, newGameButton, mineLabel, fenetre,reglage,lost);
            buttons[(ligne * y) + x].addActionListener(newbut);
            buttons[(ligne * y) + x].addMouseListener(newbut);
            System.out.println("ajout");
        }
    }


                    for(int x=0;x<ligne ;x++){
                    for(int y= 0;y<colonne;y++) {
                        if(data.readBoolean() == true && presencemines[ligne * y+x]== false){
                            buttons[ligne * y+x].doClick();
                            System.out.println("clique");
                        }
                        System.out.println("pas clique");
                    }
                }
                for(int x=0;x<ligne ;x++){
                    for(int y= 0;y<colonne;y++) {
                    if(   data.readByte() == 9){
                        buttons[(ligne*y+x)].setText("");
                        System.out.println("espace");
                    } else if(   data.readByte() == 10){
                        buttons[(ligne*y+x)].setText("?");
                        System.out.println("?");
                    } else if(   data.readByte() == 11){
                        buttons[ligne *y+x].setText("★");
                        System.out.println("★");
                        n++;
                    }
                    }       
                }
                nbrMines = nbrMines - n;
                nbrMines = data.readByte();

                mineLabel.setText("nombre de mines restante : " + nbrMines );
                data.close();
        }catch(FileNotFoundException e3){
            System.err.println("FileNotFoundException");
        }catch(IOException e2){
            System.err.println("IOException");
        }
        } 

   public void generationgrille(){
    JLabel mineLabel = new JLabel("nombre de mines restante : " + nbrMines );
    for (int x = 0; x < ligne; x++) {
        for (int y = 0; y < colonne; y++) {
            presencemines[(ligne * y) + x] = false;
            clickdone[(ligne * y) + x] = false;
            clickable[(ligne * y) + x] = true;
            buttons[(ligne * y) + x] = new JButton( );
            buttons[(ligne * y) + x].setPreferredSize(new Dimension(35, 35));
                    ActionButton newbut = new ActionButton(ligne, colonne, clickdone, clickable, buttons, presencemines, 
        nbrMines, numbers,quitter2, newGameButton, mineLabel, fenetre,reglage,lost);
            buttons[(ligne * y) + x].addActionListener(newbut);
            buttons[(ligne * y) + x].addMouseListener(newbut);
        }
    } 
Case b = new Case();
b.setCase(colonne,ligne,presencemines,numbers,nbrMines);
b.Mine();
b.fillnumbers();
    }
 
    public void fenetre1() {
        JLabel mineLabel = new JLabel("nombre de mines restante : " + nbrMines);

         GridLayout layout = new GridLayout(ligne, colonne);
        p.setLayout(layout);
       if(!nouveau){
        reprisepartie(mineLabel);
       }else{
        generationgrille();
        }

        ActionButton newbut = new ActionButton(ligne, colonne, clickdone, clickable, buttons, presencemines, 
        nbrMines, numbers,quitter2, newGameButton, mineLabel, fenetre,reglage,lost);
        for (int i = 0; i < (ligne * colonne); i++) {
            p.add(buttons[i]);
        }
        JMenuBar menubar = new JMenuBar();
        JMenu menupara = new JMenu("Paramettre");
        
        reglage.addActionListener(newbut);
        newGameButton.addActionListener(newbut);
        quitter2.addActionListener(newbut);
        sauvquit.addActionListener(newbut);
        menupara.add(reglage);
        menupara.add(newGameButton);
        menupara.add(quitter2);
        menubar.add(menupara);
        ActionFenetre doFenetre = new ActionFenetre(fenetre,buttons,numbers,presencemines,clickable,clickdone,ligne,colonne,nbrMines);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  
        fenetre.addWindowListener(doFenetre);
        fenetre.setJMenuBar(menubar);
        fenetre.add(p);
        fenetre.add(mineLabel, BorderLayout.SOUTH);
        fenetre.add(sauvquit,BorderLayout.NORTH);
        fenetre.pack();
        fenetre.setVisible(true);
    }
    public static void main(String[] args) {
     Fenetre u = new Fenetre();
    u.fenetre1();
    }

}
