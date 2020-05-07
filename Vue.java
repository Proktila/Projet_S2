package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vue extends JFrame {


    JLabel imgCoupeGauche;
    JLabel imgCoupeDroite;


    JPanel titreP;

    Container con;

    //SKIN
    JLabel lSkin;
    private SnakeButton skinSerpent;
    private SnakeButton skinMap;
    private SnakeButton pseudoSerpent;
    private SnakeButton backFromSkin;
    private SnakeButton backFromSkinChild;
    private JPanel skinButtonPanel;


    // SKIN SERPENT
    private JPanel skinSerpentButtonPanel;
    private JComboBox cbSerpent;
    private Object[] listeSerpent = new Object[]{"Element 1", "Element 2", "Element 3", "Element 4", "Element 5"};

    // SKIN MAP
    private JPanel skinMapButtonPanel;
    private JComboBox cbMap;
    private Object[] listeMap = new Object[]{"Element 1", "Element 2", "Element 3", "Element 4", "Element 5"};

    // SKIN PSEUDO
    private JPanel skinPseudoButtonPanel;
    private JTextField tfPseudo;
    private JLabel lPseudo;

    // IMAGE ICON

    ImageIcon btn;
    ImageIcon btnhover;


    //FONT
    private Font fTitre= new Font("Monospaced", Font.BOLD, 50);
    private Font fBtn= new Font("Monospaced", Font.BOLD, 18);



    //FONT

    //COLOR
    private Color blue = new Color(47, 81, 103);
    private Color green = new Color(50,99,23);
    private Color lightGreen = new Color(99, 205, 42);
    //

    //TEST
    private final Color BG_COLOR = new Color(50,99,23);
    private final Color FG_COLOR = new Color(99, 205, 42);
    private final Font TEXT_FONT = new Font("Monospaced", Font.BOLD, 50);

    // Elements du menu PARAMETRE
    protected JSlider slVolumeMusique;
    protected JSlider slVolumeBruits;
    protected JRadioButton rbFrancais;
    protected JRadioButton rbAnglais;
    protected SnakeButton bBack;
    private final int VOL_MIN = 0;
    private final int VOL_MAX = 100;
    private final int VOL_INIT = 75;

    // vars listener parametres
    private int volumeMusique = VOL_INIT;
    private int volumeBruits = VOL_INIT;
    private String lang;

    public Vue() {

        con = getContentPane();
        this.setLayout(null);
        this.initAttribut();
        this.creerTitre(lSkin,imgCoupeGauche,imgCoupeDroite);
        //this.creerParametresVue();
        this.addSkin();
        this.getContentPane().setBackground(green);

        this.setLocation(100, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Snake");
        this.setSize(1280, 720);
        this.setResizable(false);

        this.setVisible(true);

    }

    public void applyStyle(JComponent obj) {
        obj.setBackground(BG_COLOR);
        obj.setFont(fBtn);
        obj.setForeground(FG_COLOR);
    }

    public void creerParametresVue() {

        JLabel lSon = new JLabel("Son");
        JLabel lMusique = new JLabel("Volume de la musique");
        JLabel lBruitages = new JLabel("Volumes des bruitages");
        JLabel lLangue = new JLabel("Langue");

        applyStyle(lSon);
        applyStyle(lMusique);
        applyStyle(lBruitages);
        applyStyle(lLangue);

       lLangue.setHorizontalAlignment(JLabel.CENTER);

        Dimension slDimensions = slVolumeMusique.getPreferredSize();
        slDimensions.width = 400;
        slVolumeMusique.setPreferredSize(slDimensions);
        slVolumeBruits.setPreferredSize(slDimensions);
        slVolumeMusique.setBackground(BG_COLOR);
        slVolumeBruits.setBackground(BG_COLOR);

        JPanel panRbLangue = new JPanel();
        panRbLangue.setBackground(null);
        panRbLangue.setBounds(440,200,400,600);
        panRbLangue.setLayout(new GridLayout(6,1,5,5));

        JPanel v1 = new JPanel();
        v1.add(lMusique);
        v1.add(slVolumeMusique);
        applyStyle(v1);

        JPanel v2 = new JPanel();
        v2.add(lBruitages);
        v2.add(slVolumeBruits);
        applyStyle(v2);

        JPanel drapeau = new JPanel();
        drapeau.add(rbFrancais);
        drapeau.add(rbAnglais);
        applyStyle(drapeau);

        panRbLangue.add(v1);
        panRbLangue.add(v2);
        panRbLangue.add(lLangue);
        panRbLangue.add(drapeau);
        panRbLangue.add(bBack);

        con.add(panRbLangue);

        bBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                // fonction a faire
                // changeMenu("menu");
            }
        });

        slVolumeMusique.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    volumeMusique = source.getValue();
            }
        });

        slVolumeBruits.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    volumeBruits = source.getValue();
            }
        });

    }


    public void initAttribut() {


        lSkin = new JLabel("Skin");
        setupTitle(lSkin);

        //SKIN
        skinMap = new SnakeButton("Theme map");
        pseudoSerpent = new SnakeButton("pseudoSerpent");
        backFromSkin = new SnakeButton("back");

        btn = new ImageIcon("img/btnBasic.png");
        btnhover = new ImageIcon("img/btnHover.png");

        skinSerpent = new SnakeButton("Serpent");
        skinMap = new SnakeButton("Map");
        pseudoSerpent = new SnakeButton("Pseudo");
        backFromSkin = new SnakeButton("back");

        backFromSkinChild = new SnakeButton("back");

        //SKIN SERPENT

        cbSerpent = new JComboBox(listeSerpent);
        setupCb(cbSerpent);

        //SKIN MAP
        cbMap = new JComboBox(listeMap);
        setupCb(cbMap);

        // SKIN PSEUDO
        tfPseudo = new JTextField(10);
        tfPseudo.setFont(fBtn);
        tfPseudo.setForeground(lightGreen);
        tfPseudo.setBackground(blue);
        tfPseudo.setText("Pseudo");

        lPseudo = new JLabel(btn);
        lPseudo.setLayout( new BorderLayout() );
        lPseudo.add(tfPseudo);

        //TEST
        slVolumeMusique = new JSlider(JSlider.HORIZONTAL, VOL_MIN, VOL_MAX, VOL_INIT);
        slVolumeBruits = new JSlider(JSlider.HORIZONTAL, VOL_MIN, VOL_MAX, VOL_INIT);

        slVolumeMusique.setMajorTickSpacing(10);
        slVolumeMusique.setMinorTickSpacing(5);
        slVolumeMusique.setPaintTicks(true);
        slVolumeMusique.setPaintLabels(true);
        slVolumeMusique.setSnapToTicks(true);

        slVolumeBruits.setMajorTickSpacing(10);
        slVolumeBruits.setMinorTickSpacing(5);
        slVolumeBruits.setPaintTicks(true);
        slVolumeBruits.setPaintLabels(true);
        slVolumeBruits.setSnapToTicks(true);

        ImageIcon frFlag = new ImageIcon("fr.png");
        ImageIcon enFlag = new ImageIcon("en.png");

        rbFrancais = new JRadioButton(frFlag, true);
        rbAnglais = new JRadioButton(enFlag, false);
        ButtonGroup rbLangue = new ButtonGroup();
        rbLangue.add(rbFrancais);
        rbLangue.add(rbAnglais);

        bBack = new SnakeButton("BACK");
    }

    public void setupTitle(JLabel l){
        l.setHorizontalAlignment(SwingConstants.LEFT);
        l.setFont(new Font("Monospaced", Font.BOLD, 50));
        l.setForeground(lightGreen);
        l.setOpaque(true);
        l.setBackground(blue);
    }


    public void setupCb(JComboBox cb){
        cbSerpent.setPreferredSize(new Dimension(300,91));
        cbSerpent.setBackground(blue);
        cb.setForeground(lightGreen);
        cb.setFont(fBtn);
    }

    public void setupPanelBtn(JPanel p,int row){
        p.setLayout(new GridLayout(row,1,10,10));
        p.setBackground(null);
        p.setBounds(490,200,300,400);
    }

    public void creerTitre(JLabel titre, JLabel img, JLabel img2){
        titreP = new JPanel();
        titreP.setLayout(new GridBagLayout());
        titreP.setBounds(0,25,1280,125);
        titreP.setBackground(blue);
        // titreP.add(new JLabel("                 "));
        titreP.add(titre);
        // titreP.add(new JLabel("                 "));
        con.add(titreP);
    }

    public void addSkin(){
        skinButtonPanel = new JPanel();
        setupPanelBtn(skinButtonPanel,4);
        skinButtonPanel.add(skinSerpent);
        skinButtonPanel.add(skinMap);
        skinButtonPanel.add(pseudoSerpent);
        skinButtonPanel.add(backFromSkin);
        con.add(skinButtonPanel);
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
                skinButtonPanel.setVisible(false);
                addSkinPseudo();
            }
        });
    }



    public void addSkinSerpent(){

        skinSerpentButtonPanel = new JPanel();
        skinSerpentButtonPanel.setBackground(null);
        skinSerpentButtonPanel.setBounds(490,200,300,400);
        skinSerpentButtonPanel.add(cbSerpent);
        skinSerpentButtonPanel.add(backFromSkinChild);


        backFromSkinChild.addActionListener(new ActionListener() {
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
        skinMapButtonPanel.setBackground(null);
        skinMapButtonPanel.setBounds(490,200,300,400);
        skinMapButtonPanel.add(cbSerpent);
        skinMapButtonPanel.add(backFromSkinChild);

        backFromSkinChild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                skinMapButtonPanel.setVisible(false);
                skinButtonPanel.setVisible(true);
            }
        });
        con.add(skinMapButtonPanel);
    }

    public void addSkinPseudo(){

        skinPseudoButtonPanel = new JPanel();
        skinPseudoButtonPanel.setBackground(null);
        skinPseudoButtonPanel.setBounds(490,200,300,400);
        skinPseudoButtonPanel.add(lPseudo);
        skinPseudoButtonPanel.add(backFromSkinChild);

        backFromSkinChild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                skinPseudoButtonPanel.setVisible(false);
                skinButtonPanel.setVisible(true);
            }
        });

        con.add(skinPseudoButtonPanel);
    }
}