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

    private JButton butPlay;
    private JButton butEasy;
    private JButton butNormal;
    private JButton butHard;
    private JButton butTrad;
    private JButton butLaby;
    private JButton butChrono;
    private JButton butDuo;
    private JButton butBack;

    private JPanel panTitle;
    private JPanel panPlay;
    private JPanel panEasy;
    private JPanel panNormal;
    private JPanel panHard;
    private JPanel panTrad;
    private JPanel panLaby;
    private JPanel panChrono;
    private JPanel panDuo;

    private JLabel titleLabel;
    private Container container;

    private Color greenD = new Color(50, 99, 23);
    private Color greenL = new Color(99, 205, 42);
    private Color blue = new Color(47, 81, 103);

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
        this.addPlay();

        this.setLocation(100,0);

    }

    public void init() {

        this.butPlay = new JButton("Jouer");

        this.butEasy = new JButton("Facile");
        this.butNormal = new JButton("Normal");
        this.butHard = new JButton("Difficile");

        this.butTrad = new JButton("Traditionnel");
        this.butLaby = new JButton("Labyrinthe");
        this.butChrono = new JButton("Chrono");
        this.butDuo = new JButton("Duo");

        this.butBack = new JButton("Back");

        this.designButton(this.butPlay);
        this.designButton(this.butEasy);
        this.designButton(this.butNormal);
        this.designButton(this.butHard);
        this.designButton(this.butTrad);
        this.designButton(this.butLaby);
        this.designButton(this.butChrono);
        this.designButton(this.butDuo);
        this.designButton(this.butBack);

    }

    public void addTitle() {
        this.panTitle = new JPanel();
        this.panTitle.setBounds(0, 20, 1280, 100);
        this.panTitle.setBackground(this.blue);

        this.titleLabel = new JLabel(this.title);
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setFont(this.titleFont);

        this.panTitle.add(this.titleLabel);
        this.container.add(this.panTitle);

    }

    public void panButton(JPanel jp) {
        jp.setBounds(490,200,300,400);
        jp.setBackground(this.greenD);
        jp.setLayout(new GridLayout(5, 2, 10, 10));
    }

    public void designButton(JButton jb) {
        jb.setBackground(this.greenD);
        jb.setForeground(Color.WHITE);
        jb.setFocusPainted(false);
        jb.setFont(this.buttonFont);
    }

    public void addPlay() {
        this.panPlay = new JPanel();
        this.panButton(this.panPlay);

        this.panPlay.add(this.butEasy);
        this.panPlay.add(this.butNormal);
        this.panPlay.add(this.butHard);

        this.butEasy.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                this.panPlay.setVisible(false);
                this.difficulty = "Facile";
                addEasy();
            }
        });

        this.butNormal.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panPlay.setVisible(false);
                this.difficulty = "Normal";
                addNormal();
            }
        });

        this.butHard.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panPlay.setVisible(false);
                this.difficulty = "Difficile";
                addHard();
            }
        });

        this.container.add(this.panPlay);
    }


    public void addEasy(){
        this.panEasy = new JPanel();
        this.panButton(this.panEasy);

        this.panEasy.add(this.butTrad);
        this.panEasy.add(this.butLaby);
        this.panEasy.add(this.butChrono);
        this.panEasy.add(this.butDuo);
        this.panEasy.add(this.butBack);

        this.butTrad.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panEasy.setVisible(false);
                this.mode = "Traditionnel";
                addTrad();
            }
        });

        this.butLaby.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panEasy.setVisible(false);
                this.mode = "Labyrinthe";
                addLaby();
            }
        });

        this.butChrono.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panEasy.setVisible(false);
                this.mode = "Chrono";
                addChrono();
            }
        });

        this.butDuo.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panEasy.setVisible(false);
                this.mode = "Duo";
                addDuo();
            }
        });

        this.butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panEasy.setVisible(false);
                this.difficulty = null;
                addPlay();
            }
        });

        this.container.add(this.panEasy);

    }


    public void addNormal(){
        this.panNormal = new JPanel();
        this.panButton(this.panNormal);

        this.panNormal.add(this.but4);
        this.panNormal.add(this.but5);
        this.panNormal.add(this.but6);
        this.panNormal.add(this.but7);

        this.butTrad.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panNormal.setVisible(false);
                this.mode = "Traditionnel";
                addTrad();
            }
        });

        this.butLaby.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panNormal.setVisible(false);
                this.mode = "Labyrinthe";
                addLaby();
            }
        });

        this.butChrono.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panNormal.setVisible(false);
                this.mode = "Chrono";
                addChrono();
            }
        });

        this.butDuo.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panNormal.setVisible(false);
                this.mode = "Duo";
                addDuo();
            }
        });

        this.butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panNormal.setVisible(false);
                this.difficulty = null;
                addPlay();
            }
        });

        this.container.add(this.panNormal);
    }


    public void addHard(){
        this.panHard = new JPanel();
        this.panButton(this.panHard);

        this.panHard.add(this.butTrad);
        this.panHard.add(this.butLaby);
        this.panHard.add(this.butChrono);
        this.panHard.add(this.butDuo);

        this.butTrad.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panHard.setVisible(false);
                this.mode = "Traditionnel";
                addTrad();
            }
        });

        this.butLaby.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panHard.setVisible(false);
                this.mode = "Labyrinthe";
                addLaby();
            }
        });

        this.butChrono.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panHard.setVisible(false);
                this.mode = "Chrono";
                addChrono();
            }
        });

        this.butDuo.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panHard.setVisible(false);
                this.mode = "Duo";
                addDuo();
            }
        });

        this.butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panHard.setVisible(false);
                this.difficulty = null;
                addPlay();
            }
        });

        this.container.add(this.panHard);
    }


    public void addTrad(){
        this.panTrad = new JPanel();
        this.panButton(this.panTrad);
        this.panTrad.add(this.butBack);

        this.butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panTrad.setVisible(false);
                if( this.difficulty == "Facile" ){
                    addEasy();
                } else if( this.difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addHard();
                }
                this.difficulty = null;
            }
        });

        this.container.add(this.panTrad);
    }


    public void addLaby(){
        this.panLaby = new JPanel();
        this.panButton(this.panLaby);
        this.panLaby.add(this.butBack);

        this.butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panTrad.setVisible(false);
                if( this.difficulty == "Facile" ){
                    addEasy();
                } else if( this.difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addHard();
                }
                this.difficulty = null;
            }
        });

        this.container.add(this.panLaby);
    }


    public void addChrono(){
        this.panChrono = new JPanel();
        this.panButton(this.panChrono);
        this.panChrono.add(this.butBack);

        this.butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panTrad.setVisible(false);
                if( this.difficulty == "Facile" ){
                    addEasy();
                } else if( this.difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addHard();
                }
                this.difficulty = null;
            }
        });

        this.container.add(this.panChrono);
    }


    public void addDuo(){
        this.panDuo = new JPanel();
        this.panButton(this.panDuo);
        this.panDuo.add(this.butBack);

        this.butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                this.panTrad.setVisible(false);
                if( this.difficulty == "Facile" ){
                    addEasy();
                } else if( this.difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addHard();
                }
                this.difficulty = null;
            }
        });

        this.container.add(this.panDuo);
    }

}