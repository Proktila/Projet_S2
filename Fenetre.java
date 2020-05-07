package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    private JButton skinSerpent;
    private JButton skinMap;
    private JButton pseudoSerpent;
    private JButton backFromSkin;

    private Container con;
    private JLabel titreLabel;

    private Font titreFont;
    private Font boutonFont;
    private Font labelFont;

    private JButton map1;
    private JButton map2;
    private JButton map3;
    private JButton backFromSkinMap;

    private JButton skin1;
    private JButton skin2;
    private JButton skin3;
    private JButton backFromSkinSerpent;

    private JTextField tfPseudo;
    private JButton backFromSkinPseudo;



    private JPanel titreP;
    private JPanel skinButtonPanel;
    private JPanel skinMapButtonPanel;
    private JPanel skinSerpentButtonPanel;
    private JPanel skinPseudoButtonPanel;

    private JLabel imgCoupeGauche;
    private JLabel imgCoupeDroite;
    private ImageIcon imgCoupe;


    public Fenetre() {
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
        getContentPane().setBackground(Color.black);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        con = getContentPane();
        initAttribut();

        addTitle();
        addButtonSkin();
    }

    public void initAttribut(){

        titreFont = new Font("Times New Roman", Font.PLAIN,90);
        boutonFont = new Font("Times New Roman", Font.PLAIN,28);
        labelFont = new Font("Times New Roman", Font.PLAIN,46);


        skinSerpent = new JButton("SkinSerpent");
        skinMap = new JButton("Theme map");
        pseudoSerpent = new JButton("pseudoSerpent");
        backFromSkin = new JButton("back");

        map1 = new JButton("IUT");
        map2 = new JButton("JUNGLE");
        map3 = new JButton("DESERT");
        backFromSkinMap = new JButton("back");

        skin1 = new JButton("Kawaii");
        skin2 = new JButton("Drug");
        skin3 = new JButton("Flash");
        backFromSkinSerpent = new JButton("back");

        tfPseudo = new JTextField(20);
        backFromSkinPseudo = new JButton("back");


        designButton(skinSerpent);
        designButton(skinMap);
        designButton(pseudoSerpent);
        designButton(backFromSkin);

        designButton(map1);
        designButton(map2);
        designButton(map3);
        designButton(backFromSkinMap);

        designButton(skin1);
        designButton(skin2);
        designButton(skin3);
        designButton(backFromSkinSerpent);

        designButton(backFromSkinPseudo);




        imgCoupe = new ImageIcon("coupe.png");

        imgCoupeGauche =  new JLabel(imgCoupe);
        imgCoupeDroite =  new JLabel(imgCoupe);




    }

    public void designButton(JButton b){
        b.setBackground(new Color(50,99,23));
        b.setForeground(Color.white);
        b.setFocusPainted(false);
        b.setFont(boutonFont);
    }
    public void setupPanelButton(JPanel p){
        p.setBounds(300,200,200,325);
        p.setBackground(Color.blue);
        p.setLayout(new GridLayout(4,1,10,10));
    }

    public void addTitle() {
        JPanel panImgCoupeDroite = new JPanel();
        panImgCoupeDroite.setBackground(new Color(47, 81, 103));
        panImgCoupeDroite.add(imgCoupeDroite);

        JPanel panImgCoupeGauche = new JPanel();
        panImgCoupeGauche.setBackground(new Color(47, 81, 103));
        panImgCoupeGauche.add(imgCoupeGauche);


       titreLabel = new JLabel("SN'HACK");
       titreLabel.setForeground(Color.white);
       titreLabel.setFont(titreFont);
       titreLabel.setBackground(new Color(47, 81, 103));

        JPanel pantitre = new JPanel();
        pantitre.setLayout(new GridLayout(1,3));
        pantitre.add(panImgCoupeDroite);
        pantitre.add(titreLabel);
        pantitre.add(panImgCoupeGauche);
        pantitre.setBounds(0,0,1280,125);

       con.add(pantitre);
    }
    public void addButtonSkin(){
        skinButtonPanel = new JPanel();
        setupPanelButton(skinButtonPanel);


        skinButtonPanel.add(skinSerpent);
        skinButtonPanel.add(skinMap);
        skinButtonPanel.add(pseudoSerpent);
        skinButtonPanel.add(backFromSkin);



        skinSerpent.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                skinButtonPanel.setVisible(false);
                addSkinSerpent();
            }
        });

        skinMap.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                skinButtonPanel.setVisible(false);
                addSkinMap();
            }
        });
        pseudoSerpent.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                getContentPane().removeAll();
                getContentPane().revalidate();
                getContentPane().repaint();
                addPseudo();
            }
        });
        con.add(skinButtonPanel);
    }

    public void addSkinSerpent(){

        skinSerpentButtonPanel = new JPanel();
        setupPanelButton(skinSerpentButtonPanel);

        skinSerpentButtonPanel.add(skin1);
        skinSerpentButtonPanel.add(skin2);
        skinSerpentButtonPanel.add(skin3);
        skinSerpentButtonPanel.add(backFromSkinSerpent);

        backFromSkinSerpent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                skinSerpentButtonPanel.setVisible(false);
                skinButtonPanel.setVisible(true);
            }
        });


        con.add(skinSerpentButtonPanel);
    }

    public void addSkinMap(){

        skinMapButtonPanel = new JPanel();
        setupPanelButton(skinMapButtonPanel);

        skinMapButtonPanel.add(map1);
        skinMapButtonPanel.add(map2);
        skinMapButtonPanel.add(map3);
        skinMapButtonPanel.add(backFromSkinMap);

        backFromSkinMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                skinMapButtonPanel.setVisible(false);
                skinButtonPanel.setVisible(true);
            }
        });


        con.add(skinMapButtonPanel);
    }
    public void addPseudo(){
        addTitle();
        con.removeAll();
        skinPseudoButtonPanel = new JPanel();
        skinPseudoButtonPanel.setBounds(150,200,500,100);
        skinPseudoButtonPanel.setBackground(Color.blue);
        skinPseudoButtonPanel.setLayout(new BoxLayout(skinPseudoButtonPanel,BoxLayout.PAGE_AXIS));

        JPanel test = new JPanel();
        test.setBackground(Color.blue);
        test.setLayout(new GridBagLayout());

        JLabel lPseudo = new JLabel("Pseudo");
        lPseudo.setFont(labelFont);
        lPseudo.setForeground(Color.white);
        test.add(lPseudo);
        test.add(tfPseudo);
        skinPseudoButtonPanel.add(test);
        skinPseudoButtonPanel.add(backFromSkinPseudo);

        con.add(titreP);
        con.add(skinPseudoButtonPanel);

        backFromSkinPseudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getContentPane().removeAll();
                getContentPane().revalidate();
                getContentPane().repaint();
                addButtonSkin();
                con.add(titreP);
            }
        });
    }
}
