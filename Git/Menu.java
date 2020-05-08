// NatSch
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
font titre : monospaced
taille du titre : 50
vert foncé (fond) : (50,99,23)
vert clair (pour le titre) : (99, 205, 42)
bleu (bandeau titre) : (47, 81, 103)
*/

public class Menu extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String title = "SN'HACK";

    private JButton but0;
    private JButton but1;
    private JButton but2;
    private JButton but3;
    private JButton but4;
    private JButton but5;
    private JButton but6;
    private JButton but7;
    private JButton but8;

    private JPanel panTitle;
    private JPanel panJouer;
    private JPanel panFacile;
    private JPanel panNormal;
    private JPanel panDifficile;

    private JLabel titleLabel;
    private Container container;

    private Color vertF = new Color(50, 99, 23);
    private Color vertC = new Color(99, 205, 42);
    private Color bleu = new Color(47, 81, 103);

    private Font buttonFont = new Font("Monospaced", Font.PLAIN, 28);
    private Font titleFont = new Font("Monospaced", Font.PLAIN, 28);

    private String difficulty;
    private String mode;

    public Menu() {
        this.container = getContentPane();
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.setTitle("SN'HACK");
        
        this.init();

        this.addTitle();
        this.addJouer();

        this.setLocation(100,0);

    }

    public void init() {

        this.but0 = new JButton("Jouer");

        this.but1 = new JButton("Facile");
        this.but2 = new JButton("Normal");
        this.but3 = new JButton("Difficile");

        this.but4 = new JButton("Traditionnel");
        this.but5 = new JButton("Labyrinthe");
        this.but6 = new JButton("Chrono");
        this.but7 = new JButton("Duo");

        this.but8 = new JButton("Back");
/*
        designButton(but0);
        designButton(but1);
        designButton(but2);
        designButton(but3);
        designButton(but4);
        designButton(but5);
        designButton(but6);
        designButton(but7);
        designButton(but8);
*/
    }

    public void addTitle() {
        this.panTitle = new JPanel();
        this.panTitle.setBounds(0, 20, 1280, 100);
        this.panTitle.setBackground(this.bleu);

        this.titleLabel = new JLabel(this.title);
        // titleLabel.setForeground(Color.white);
        titleLabel.setFont(this.titleFont);

        this.panTitle.add(this.titleLabel);
        this.container.add(this.panTitle);

    }

    public void panButton(JPanel jp) {
        jp.setBounds(490,200,300,400);
        jp.setBackground(this.vertC);
        jp.setLayout(new GridLayout(5, 2, 10, 10));
    }

    public void designButton(JButton jb) {
        jb.setBackground(this.vertF);
        jb.setForeground(Color.white);
        jb.setFocusPainted(false);
        jb.setFont(this.buttonFont);
    }

    public void addJouer() {
        this.panJouer = new JPanel();
        this.panButton(this.panJouer);

        this.panJouer.add(this.but1);
        this.panJouer.add(this.but2);
        this.panJouer.add(this.but3);

        this.but1.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                this.panJouer.setVisible(false);
                this.difficulty = "Facile";
                addFacile();
            }
        });

        this.but2.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panJouer.setVisible(false);
                this.difficulty = "Normal";
                addNormal();
            }
        });

        this.but3.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panJouer.setVisible(false);
                this.difficulty = "Difficile";
                addDifficile();
            }
        });
    }


    public void addFacile(){
        this.panFacile = new JPanel();
        this.panButton(this.panFacile);

        this.panFacile.add(this.but4);
        this.panFacile.add(this.but5);
        this.panFacile.add(this.but6);
        this.panFacile.add(this.but7);
        this.panFacile.add(this.but8);

        this.but4.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panFacile.setVisible(false);
                this.mode = "Traditionnel";
                addTrad();
            }
        });

        this.but5.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panFacile.setVisible(false);
                this.mode = "Labyrinthe";
                addLaby();
            }
        });

        this.but6.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panFacile.setVisible(false);
                this.mode = "Chrono";
                addChrono();
            }
        });

        this.but7.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panFacile.setVisible(false);
                this.mode = "Duo";
                addDuo();
            }
        });

        this.but8.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panFacile.setVisible(false);
                this.difficulty = null;
                addJouer();
            }
        });

    }


    public void addNormal(){
        this.panNormal = new JPanel();
        this.panButton(this.panNormal);

        this.panNormal.add(this.but4);
        this.panNormal.add(this.but5);
        this.panNormal.add(this.but6);
        this.panNormal.add(this.but7);

        this.but4.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panNormal.setVisible(false);
                this.mode = "Traditionnel";
                addTrad();
            }
        });

        this.but5.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panNormal.setVisible(false);
                this.mode = "Labyrinthe";
                addLaby();
            }
        });

        this.but6.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panNormal.setVisible(false);
                this.mode = "Chrono";
                addChrono();
            }
        });

        this.but7.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panNormal.setVisible(false);
                this.mode = "Duo";
                addDuo();
            }
        });

        this.but8.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panNormal.setVisible(false);
                this.difficulty = null;
                addJouer();
            }
        });
    }


    public void addDifficile(){
        this.panDifficile = new JPanel();
        this.panButton(this.panDifficile);

        this.panDifficile.add(this.but4);
        this.panDifficile.add(this.but5);
        this.panDifficile.add(this.but6);
        this.panDifficile.add(this.but7);

        this.but4.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panDifficile.setVisible(false);
                this.mode = "Traditionnel";
                addTrad();
            }
        });

        this.but5.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panDifficile.setVisible(false);
                this.mode = "Labyrinthe";
                addLaby();
            }
        });

        this.but6.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panDifficile.setVisible(false);
                this.mode = "Chrono";
                addChrono();
            }
        });

        this.but7.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panDifficile.setVisible(false);
                this.mode = "Duo";
                addDuo();
            }
        });

        this.but8.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panDifficile.setVisible(false);
                this.difficulty = null;
                addJouer();
            }
        });
    }


    public void addTrad(){
        this.panTrad = new JPanel();
        this.panButton(this.panTrad);
        this.panTrad.add(this.but8);

        this.but8.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panTrad.setVisible(false);
                if( this.difficulty == "Facile" ){
                    addFacile();
                } else if( this.difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addDifficile();
                }
                this.difficulty = null;
            }
        });
    }


    public void addLaby(){
        this.panLaby = new JPanel();
        this.panButton(this.panLaby);
        this.panLaby.add(this.but8);

        this.but8.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panTrad.setVisible(false);
                if( this.difficulty == "Facile" ){
                    addFacile();
                } else if( this.difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addDifficile();
                }
                this.difficulty = null;
            }
        });
    }


    public void addChrono(){
        this.panChrono = new JPanel();
        this.panButton(this.panChrono);
        this.panChrono.add(this.but8);

        this.but8.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panTrad.setVisible(false);
                if( this.difficulty == "Facile" ){
                    addFacile();
                } else if( this.difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addDifficile();
                }
                this.difficulty = null;
            }
        });
    }


    public void addDuo(){
        this.panDuo = new JPanel();
        this.panButton(this.panDuo);
        this.panDuo.add(this.but8);

        this.but8.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panTrad.setVisible(false);
                if( this.difficulty == "Facile" ){
                    addFacile();
                } else if( this.difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addDifficile();
                }
                this.difficulty = null;
            }
        });
    }

}