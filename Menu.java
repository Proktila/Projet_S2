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
        container = getContentPane();
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        setTitle("SN'HACK");
        
        init();

        addTitle();
        addPlay();

        setLocation(100,0);

    }

    public void init() {

        butPlay = new JButton("Jouer");

        butEasy = new JButton("Facile");
        butNormal = new JButton("Normal");
        butHard = new JButton("Difficile");

        butTrad = new JButton("Traditionnel");
        butLaby = new JButton("Labyrinthe");
        butChrono = new JButton("Chrono");
        butDuo = new JButton("Duo");

        butBack = new JButton("Back");

        designButton(butPlay);
        designButton(butEasy);
        designButton(butNormal);
        designButton(butHard);
        designButton(butTrad);
        designButton(butLaby);
        designButton(butChrono);
        designButton(butDuo);
        designButton(butBack);

    }

    public void addTitle() {
        panTitle = new JPanel();
        panTitle.setBounds(0, 20, 1280, 100);
        panTitle.setBackground(blue);

        titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);

        panTitle.add(titleLabel);
        container.add(panTitle);

    }

    public void panButton(JPanel jp) {
        jp.setBounds(490,200,300,400);
        jp.setBackground(greenD);
        jp.setLayout(new GridLayout(5, 2, 10, 10));
    }

    public void designButton(JButton jb) {
        jb.setBackground(greenD);
        jb.setForeground(Color.WHITE);
        jb.setFocusPainted(false);
        jb.setFont(buttonFont);
    }

    public void addPlay() {
        panPlay = new JPanel();
        panButton(panPlay);

        panPlay.add(butEasy);
        panPlay.add(butNormal);
        panPlay.add(butHard);

        butEasy.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                panPlay.setVisible(false);
                difficulty = "Facile";
                addEasy();
            }
        });

        butNormal.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panPlay.setVisible(false);
                difficulty = "Normal";
                addNormal();
            }
        });

        butHard.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panPlay.setVisible(false);
                difficulty = "Difficile";
                addHard();
            }
        });

        container.add(panPlay);
    }


    public void addEasy(){
        panEasy = new JPanel();
        panButton(panEasy);

        panEasy.add(butTrad);
        panEasy.add(butLaby);
        panEasy.add(butChrono);
        panEasy.add(butDuo);
        panEasy.add(butBack);

        butTrad.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panEasy.setVisible(false);
                mode = "Traditionnel";
                addTrad();
            }
        });

        butLaby.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panEasy.setVisible(false);
                mode = "Labyrinthe";
                addLaby();
            }
        });

        butChrono.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panEasy.setVisible(false);
                mode = "Chrono";
                addChrono();
            }
        });

        butDuo.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panEasy.setVisible(false);
                mode = "Duo";
                addDuo();
            }
        });

        butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panEasy.setVisible(false);
                difficulty = null;
                addPlay();
            }
        });

        container.add(panEasy);

    }


    public void addNormal(){
        panNormal = new JPanel();
        panButton(panNormal);

        butTrad.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panNormal.setVisible(false);
                mode = "Traditionnel";
                addTrad();
            }
        });

        butLaby.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panNormal.setVisible(false);
                mode = "Labyrinthe";
                addLaby();
            }
        });

        butChrono.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panNormal.setVisible(false);
                mode = "Chrono";
                addChrono();
            }
        });

        butDuo.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panNormal.setVisible(false);
                mode = "Duo";
                addDuo();
            }
        });

        butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panNormal.setVisible(false);
                difficulty = null;
                addPlay();
            }
        });

        container.add(panNormal);
    }


    public void addHard(){
        panHard = new JPanel();
        panButton(panHard);

        panHard.add(butTrad);
        panHard.add(butLaby);
        panHard.add(butChrono);
        panHard.add(butDuo);

        butTrad.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panHard.setVisible(false);
                mode = "Traditionnel";
                addTrad();
            }
        });

        butLaby.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panHard.setVisible(false);
                mode = "Labyrinthe";
                addLaby();
            }
        });

        butChrono.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panHard.setVisible(false);
                mode = "Chrono";
                addChrono();
            }
        });

        butDuo.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panHard.setVisible(false);
                mode = "Duo";
                addDuo();
            }
        });

        butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panHard.setVisible(false);
                difficulty = null;
                addPlay();
            }
        });

        container.add(panHard);
    }


    public void addTrad(){
        panTrad = new JPanel();
        panButton(panTrad);
        panTrad.add(butBack);

        butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panTrad.setVisible(false);
                if( difficulty == "Facile" ){
                    addEasy();
                } else if( difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addHard();
                }
                difficulty = null;
            }
        });

        container.add(panTrad);
    }


    public void addLaby(){
        panLaby = new JPanel();
        panButton(panLaby);
        panLaby.add(butBack);

        butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panTrad.setVisible(false);
                if( difficulty == "Facile" ){
                    addEasy();
                } else if( difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addHard();
                }
                difficulty = null;
            }
        });

        container.add(panLaby);
    }


    public void addChrono(){
        panChrono = new JPanel();
        panButton(panChrono);
        panChrono.add(butBack);

        butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panTrad.setVisible(false);
                if( difficulty == "Facile" ){
                    addEasy();
                } else if( difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addHard();
                }
                difficulty = null;
            }
        });

        container.add(panChrono);
    }


    public void addDuo(){
        panDuo = new JPanel();
        panButton(panDuo);
        panDuo.add(butBack);

        butBack.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                panTrad.setVisible(false);
                if( difficulty == "Facile" ){
                    addEasy();
                } else if( difficulty == "Normal" ) {
                    addNormal();
                } else {
                    addHard();
                }
                difficulty = null;
            }
        });

        container.add(panDuo);
    }

}