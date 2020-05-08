import javax.swing.*;
// Pour les composants graphiques que l'on
// ajoutera dans la méthode creerWidget

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;
import java.awt.*;
// Pour la JFrame
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Fenetre extends JFrame {


    /**DEBUT COMMUN POUR TOUS**/
    //TEST
    private final Color BG_COLOR = new Color(50,99,23);
    private final Color FG_COLOR = new Color(99, 205, 42);
    private final Font TEXT_FONT = new Font("Monospaced", Font.BOLD, 50);

    //COLOR
    private Color blue = new Color(47, 81, 103);
    private Color green = new Color(50,99,23);
    private Color lightGreen = new Color(99, 205, 42);
    //FIN COLOR

    //FONT
    private Font fTitre= new Font("Monospaced", Font.BOLD, 50);
    private Font fBtn= new Font("Monospaced", Font.BOLD, 18);
    //FIN FONT
    /**FIN COMMUN POUR TOUS**/


    /**DEBUT MENU PRINCIPAL**/

    private JLabel lMenuPrincipal;

    //j'ai remplacer mybuttonproj par JButton pour l'instant
    private SnakeButton boutonJouer;
    private SnakeButton boutonSkins;
    private SnakeButton  boutonScores;
    private SnakeButton  boutonParametres;
    private SnakeButton  boutonCredits;

    ImageIcon imageGauche;
    ImageIcon imageDroite;

    private JLabel lserpentGauche;
    private JLabel lserpentDroite;


    //je met ca en comentaire pour l'instant
    /**ControlBoutonMenuPrincipal cbJouer;
    ControlBoutonMenuPrincipal cbSkins;
    ControlBoutonMenuPrincipal cbScores;
    ControlBoutonMenuPrincipal cbParametres;
    ControlBoutonMenuPrincipal cbCredits;**/

    //savoir si on fait une classe de control
    // pour chaque bouton ou si on mets tout les traitements dans
    // la mm classe


    /**FIN MENU PRICIPAL**/


    /**DEBUT MENU SKIN**/

    JPanel titreP;
    Container con;

    //SKIN
    JLabel lSkin;
    private SnakeButton skinSerpent;
    private SnakeButton skinMap;
    private SnakeButton skinPseudo;
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


    // Elements du menu PARAMETRE
    JPanel panTitleParam;
    JLabel lImgTitreDroite;
    JLabel lImgTitreGauche;
    protected JSlider slVolumeMusique;
    protected JSlider slVolumeBruits;
    protected JRadioButton rbFrench;
    protected JRadioButton rbEnglish;
    protected JButton bBack;
    private final int VOL_MIN = 0;
    private final int VOL_MAX = 100;
    private final int VOL_INIT = 75;

    // variables a modifier par les listener du menu parametres
    private int volumeMusique = VOL_INIT;
    private int volumeBruits = VOL_INIT;
    private String lang;
    /**FIN MENU SKIN**/


    /**DEBUT MENU SCORE**/

    private Object[][] data;
    private String[] title;
    private JTable tableau;

    private JLabel lscore;
    private JLabel imgCoupeGauche;
    private JLabel imgCoupeDroite;
    private JLabel lMedaille1Droite;
    private JLabel lMedaille2Droite;
    private JLabel lMedaille3Droite;
    private JLabel lMedaille1Gauche;
    private JLabel lMedaille2Gauche;
    private JLabel lMedaille3Gauche;

    private ImageIcon imgCoupe;
    private ImageIcon imgMedaille1;
    private ImageIcon imgMedaille2;
    private ImageIcon imgMedaille3;

    private JPanel pantitre2;



    TableModel table;
    Tableau render;

    private SnakeButton bRetour;

    /**FIN MENU SCORE**/

    /** Credits **/
    private SnakeButton backCredits;
    private JPanel panelCredits;

    /**CONSTRUCTEUR de fenetre**/
    public Fenetre() {


        con = getContentPane();
        this.setLayout(null);

        this.initAttribut();

        this.creerParametresVue();
        //this.addCredit();
        // Menu Principal
        //this.creerTitre(lMenuPrincipal, imgCoupeGauche, imgCoupeDroite);
        //this.creerWidgetMenuPrincipal();

        // SKIN
        //this.creerTitre(lSkin, imgCoupeGauche, imgCoupeGauche);
        //this.creerParametresVue();
        //this.addSkin();

        //SCORE
        //this.creerTitreScore(lscore, imgCoupeGauche, imgCoupeGauche);
        //this.creerInterface(pantitre2);


        this.getContentPane().setBackground(green);
        this.setLocation(100, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //gestion de la fermeture

        this.setTitle("Snake");
        this.setSize(1280, 720);
        this.setResizable(false);

        this.setVisible(true); //afiche fenetre

    }

    /**DEBUT methodes pour tous les menus**/

    /**methode à commenter**/
    /**@param **/
    public void initAttribut() {
        initMenuPrincipal();
        initSkin();
        initScore();
        initParametre();
        backCredits = new SnakeButton("retour");
    }
    public void initMenuPrincipal(){
        lMenuPrincipal = new JLabel (" Menu principal ") ;
        setupTitle(lMenuPrincipal);

        imageGauche = new ImageIcon("img/menuPrinc/serpent1.png");
        imageDroite = new ImageIcon("img/menuPrinc/serpent2.png");

        lserpentGauche = new JLabel(imageGauche);
        lserpentDroite = new JLabel(imageDroite);

        //je remplace myboutton par Snakeboutton
        boutonJouer = new SnakeButton("Jouer");
        boutonSkins = new SnakeButton("Skin");
        boutonScores = new SnakeButton("Tableau des scores");
        boutonParametres = new SnakeButton("Parametres");
        boutonCredits = new SnakeButton("Credits");

        //mettre en commentaire pour l'instant
        /*cbJouer = new ControlBoutonMenuPrincipal(this);
        cbSkins = new ControlBoutonMenuPrincipal(this);
        cbScores = new ControlBoutonMenuPrincipal(this);
        cbParametres = new ControlBoutonMenuPrincipal(this);
        cbCredits = new ControlBoutonMenuPrincipal(this);

        boutonJouer.addActionListener(cbJouer);
        boutonSkins.addActionListener(cbSkins);
        boutonScores.addActionListener(cbScores);
        boutonParametres.addActionListener(cbParametres);
        boutonCredits.addActionListener(cbCredits);*/
    }

    public void initScore(){
        lscore = new JLabel("TABLEAU DES SCORES");
        setupTitle(lscore);
        bRetour = new SnakeButton("retour");


        //contenu des cellules
        data = new Object[][]{
                {"Normal", "serpent", "105"},
                {"Chrono", "serpent", "100"},
                {"...", "serpent", "100"},
                {"...", "serpent", "90"},
                {"...", "serpent", "80"},
                {"...", "serpent", "70"},
                {"...", "serpent", "50"},

        };

        //Les titres des colonnes
        title = new String[]{"Thème", "Nom du serpent", "Score"};

        tableau = new JTable(data, title);

        //tableau
        table = new TableModel(data, title);
        render = new Tableau();
        tableau.setModel(table);
        tableau.setDefaultRenderer(Object.class, render);

        //titre des colonnes
        tableau.getTableHeader().setBackground(lightGreen);
        tableau.getTableHeader().setForeground(blue);
        tableau.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 30));

        tableau.setRowHeight(30);

        tableau.getColumnModel().getColumn(0).setPreferredWidth(200);
        tableau.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableau.getColumnModel().getColumn(2).setPreferredWidth(200);

        //image coupe
        imgCoupe = new ImageIcon("img/score/coupe.png");

        imgCoupeGauche =  new JLabel(imgCoupe);
        imgCoupeDroite =  new JLabel(imgCoupe);
    }

    public void initSkin(){
        lSkin = new JLabel("Skin");
        setupTitle(lSkin);

        //SKIN Menu
        skinMap = new SnakeButton("Theme map");
        skinPseudo = new SnakeButton("pseudoSerpent");
        backFromSkin = new SnakeButton("Retour");


        skinSerpent = new SnakeButton("Serpent");
        skinMap = new SnakeButton("Map");
        skinPseudo = new SnakeButton("Pseudo");
        backFromSkin = new SnakeButton("Retour");

        backFromSkinChild = new SnakeButton("Retour");

        //SKIN SERPENT

        cbSerpent = new JComboBox(listeSerpent);
        setupCb(cbSerpent);

        //SKIN MAP
        cbMap = new JComboBox(listeMap);
        setupCb(cbMap);

        // SKIN PSEUDO
        tfPseudo = new JTextField(25);
        tfPseudo.setFont(fBtn);
        tfPseudo.setForeground(lightGreen);
        tfPseudo.setBackground(blue);
        tfPseudo.setText("Pseudo");
        tfPseudo.setPreferredSize(new Dimension(300,91));

        lPseudo = new JLabel();
        lPseudo.add(tfPseudo);
    }

    public void initParametre(){
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

        lImgTitreGauche =  new JLabel(new ImageIcon("img/paramIcon.png"));
        lImgTitreDroite =  new JLabel(new ImageIcon("img/paramIcon.png"));

        rbFrench = new JRadioButton(new ImageIcon("img/langFR.png"), true);
        rbEnglish = new JRadioButton(new ImageIcon("img/langEN.png"), false);
        ButtonGroup rbLangue = new ButtonGroup();
        rbLangue.add(rbFrench);
        rbLangue.add(rbEnglish);

        bBack = new SnakeButton("Retour");
    }


    /**Créer la bandelore au dessus du meni**/
    /**@param **/
    public void creerTitre(JLabel titre,  JLabel img, JLabel img2) {

        titreP = new JPanel();
        titreP.setLayout(new GridBagLayout());
        titreP.setBounds(0,25,1280,125);
        titreP.setBackground(blue);
        if(img != null){
            titreP.add(img);
        }
        titreP.add(titre);
        if(img2 != null){
            titreP.add(img2);
        }
        con.add(titreP);
    }
    /**FIN methodes pour tous les menus**/

    /**DEBUT Methodes menu principale : Marion**/

    public void creerWidgetMenuPrincipal(){

        JPanel panoLabelMenuPrincipal = new JPanel();
        panoLabelMenuPrincipal.setBackground(green);
        panoLabelMenuPrincipal.add(lMenuPrincipal);

        pantitre2 = new JPanel();
        pantitre2.setBackground(green);
        pantitre2.add(panoLabelMenuPrincipal);

        JPanel panoBoutonJouer = new JPanel();
        panoBoutonJouer.setBackground(green);
        panoBoutonJouer.add(boutonJouer);

        JPanel panoBoutonSkins = new JPanel();
        panoBoutonSkins.setBackground(green);
        panoBoutonSkins.add(boutonSkins);

        JPanel panoBoutonScores = new JPanel();
        panoBoutonScores.setBackground(green);
        panoBoutonScores.add(boutonScores);

        JPanel panoBoutonParametres = new JPanel();
        panoBoutonParametres.setBackground(green);
        panoBoutonParametres.add(boutonParametres);

        JPanel panoBoutonCredits = new JPanel();
        panoBoutonCredits.setBackground(green);
        panoBoutonCredits.add(boutonCredits);

        JPanel panoSerpentGauche = new JPanel();
        panoSerpentGauche.setBackground(green);
        panoSerpentGauche.add(lserpentGauche);

        JPanel panoSerpentDroite = new JPanel();
        panoSerpentDroite.setBackground(green);
        panoSerpentDroite.add(lserpentDroite);

        //ajout tous les panel des bouttons à un seul panel d'ensemble
        JPanel panoMenuPrincipalCentre = new JPanel();
        panoMenuPrincipalCentre.setLayout(new BoxLayout(panoMenuPrincipalCentre, BoxLayout.Y_AXIS));
        panoMenuPrincipalCentre.add(pantitre2);
        panoMenuPrincipalCentre.add(panoBoutonJouer);
        panoMenuPrincipalCentre.add(panoBoutonSkins);
        panoMenuPrincipalCentre.add(panoBoutonScores);
        panoMenuPrincipalCentre.add(panoBoutonParametres);
        panoMenuPrincipalCentre.add(panoBoutonCredits);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(1,3));
        grid.add(panoSerpentGauche);
        grid.add(panoMenuPrincipalCentre);
        grid.add(panoSerpentDroite);

        setContentPane(grid);
    }

    /**FIN methodes menu principale**/


    /**DEBUT methodes menu skin : Arthur**/

    /**methode à commenter**/
    /**@param **/
    public void applyStyle(JComponent obj) {
        obj.setBackground(BG_COLOR);
        obj.setFont(fBtn);
        obj.setForeground(FG_COLOR);
    }

    public void addCredit(){
        JLabel taText1 = new JLabel("Le jeu à été concu par");
        JLabel taText2 = new JLabel("Julien, Lisa, Marion, Theo, Nathan, Arthur");
        JLabel taText3 = new JLabel("La musique utilisee est celle de");
        JLabel taText4 = new JLabel("Harry potter, Nyan Cat etc");
        JLabel taText5 = new JLabel("Pour la realisation de ce jeu on tient a remercier ");
        JLabel taText6 = new JLabel("notre tuteur monsieur Couchot etc. ");


        JLabel lCredits = new JLabel("Credits");
        setupTitle(lCredits);
        creerTitre(lCredits,null,null);
        panelCredits = setupContent();

        JPanel content = new JPanel();
        content.setBackground(blue);
        content.setBounds(340,200,600,425);

        applyStyle(taText1);
        applyStyle(taText2);
        applyStyle(taText3);
        applyStyle(taText4);
        applyStyle(taText5);
        applyStyle(taText6);

        content.add(taText1);
        content.add(Box.createVerticalStrut(50));
        content.add(taText2);
        content.add(Box.createVerticalStrut(50));
        content.add(taText3);
        content.add(Box.createVerticalStrut(50));
        content.add(taText4);
        content.add(Box.createVerticalStrut(50));
        content.add(taText5);
        content.add(Box.createVerticalStrut(50));
        content.add(taText6);
        content.add(backCredits);


        panelCredits.add(content);
        setContentPane(panelCredits);


    }




    /**methode à commenter**/
    /**@param **/
    public void setupTitle(JLabel l){
        l.setHorizontalAlignment(SwingConstants.LEFT);
        l.setFont(new Font("Monospaced", Font.BOLD, 50));
        l.setForeground(lightGreen);
        l.setOpaque(true);
        l.setBackground(blue);
    }

    /**methode à commenter**/
    /**@param **/
    public void setupCb(JComboBox cb){
        cbSerpent.setPreferredSize(new Dimension(300,91));
        cbSerpent.setBackground(blue);
        cb.setForeground(lightGreen);
        cb.setFont(fBtn);
    }

    /**methode à commenter**/
    /**@param **/
    public void setupPanelBtn(JPanel p,int row){
        p.setLayout(new GridLayout(row,1,10,10));
        p.setBackground(null);
        p.setBounds(490,200,300,400);
    }

    public JPanel setupContent(){
        JPanel c = new JPanel();
        c.setLayout(null);
        c.setBackground(green);
        c.add(titreP);
        return c;
    }

    public void setBackSkin(){
        skinButtonPanel.setVisible(true);
        skinButtonPanel.add(titreP);
        setContentPane(skinButtonPanel);
    }

    public void creerParametresVue() {
        JLabel lTitre = new JLabel("Parametres");
        setupTitle(lTitre);
        creerTitre(lTitre, lImgTitreDroite, lImgTitreGauche);
        panTitleParam = setupContent();

        JLabel lSon = new JLabel("Parametres son");
        JLabel lMusique = new JLabel("Volume de la musique");
        JLabel lBruitages = new JLabel("Volume des bruitages");
        JLabel lLangue = new JLabel("Langue");

        // centre les textes dans leurs labels et applique la bonne police
        lMusique.setHorizontalAlignment(JLabel.CENTER);
        lBruitages.setHorizontalAlignment(JLabel.CENTER);
        lSon.setHorizontalAlignment(JLabel.CENTER);
        lLangue.setHorizontalAlignment(JLabel.CENTER);
        applyStyle(lSon);
        applyStyle(lMusique);
        applyStyle(lBruitages);
        applyStyle(lLangue);
        applyStyle(rbFrench);
        applyStyle(rbEnglish);
        lSon.setFont(fBtn);
        lLangue.setFont(fBtn);

        // applique une dimension de 400px par 25px au JSlider et donne la couleur du background
        Dimension slDimensions = new Dimension(400,25);
        slVolumeMusique.setPreferredSize(slDimensions);
        slVolumeBruits.setPreferredSize(slDimensions);
        slVolumeMusique.setBackground(BG_COLOR);
        slVolumeBruits.setBackground(BG_COLOR);

        JPanel panSonTitre = new JPanel(new GridLayout(1,1));
        panSonTitre.add(lSon);

        JPanel panSon = new JPanel(new GridLayout(2,2));
        panSon.add(lMusique);
        panSon.add(slVolumeMusique);
        panSon.add(lBruitages);
        panSon.add(slVolumeBruits);

        JPanel panLangueTitre = new JPanel(new GridLayout(1,1));
        panLangueTitre.add(lLangue);

        JPanel panRbLangue = new JPanel();
        panRbLangue.add(rbFrench);
        panRbLangue.add(rbEnglish);

        JPanel panButtonCenter = new JPanel(new GridLayout(1,1));
        panButtonCenter.add(bBack);

        JPanel panParametre = new JPanel();
        panParametre.setBackground(BG_COLOR);
        panParametre.setBounds(240,150,800,525);
        panParametre.setLayout(new BoxLayout(panParametre, BoxLayout.Y_AXIS));
        panParametre.add(panSonTitre);
        panParametre.add(panSon);
        panParametre.add(Box.createVerticalStrut(25));
        panParametre.add(panLangueTitre);
        panParametre.add(panRbLangue);
        panParametre.add(panButtonCenter);

        applyStyle(panSonTitre);
        applyStyle(panSon);
        applyStyle(panLangueTitre);
        applyStyle(panRbLangue);
        applyStyle(panButtonCenter);
        applyStyle(panParametre);

        panTitleParam.add(panParametre);
        setContentPane(panTitleParam);


        // listeners du menu parametre (a bouger dans le controleur et adapter)
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

    /**methode à commenter**/
    /**@param **/
    public void addSkin(){
        skinButtonPanel = setupContent();
        JPanel content = new JPanel();
        setupPanelBtn(content,4);
        content.add(skinSerpent);
        content.add(skinMap);
        content.add(skinPseudo);
        content.add(backFromSkin);
        skinButtonPanel.add(content);
        setContentPane(skinButtonPanel);
    }

    public void setupContainer(){
        this.getContentPane().removeAll();
        this.repaint();
        this.revalidate();
        con=getContentPane();
        this.setLayout(null);
        con.setBackground(green);
        this.creerTitre(lSkin, null,null);
    }
    /**methode à commenter**/
    /**@param **/
    public void addSkinSerpent(){

        skinSerpentButtonPanel = setupContent();
        JPanel content = new JPanel();
        content.setBackground(null);
        content.setBounds(490,200,300,400);
        content.add(cbSerpent);
        content.add(backFromSkinChild);
        skinSerpentButtonPanel.add(content);
        setContentPane(skinSerpentButtonPanel);



    }

    /**methode à commenter**/
    /**@param **/
    public void addSkinMap(){

        skinMapButtonPanel = setupContent();
        JPanel content = new JPanel();
        content.setBackground(null);
        content.setBounds(490,200,300,400);
        content.add(cbSerpent);
        content.add(backFromSkinChild);

        /*backFromSkinChild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                skinMapButtonPanel.setVisible(false);
                setBackSkin();
            }
        });*/
        skinMapButtonPanel.add(content);
        setContentPane(skinMapButtonPanel);
    }

    /**methode à commenter**/
    /**@param **/
    public void addSkinPseudo(){

        skinPseudoButtonPanel = setupContent();
        JPanel content = new JPanel();
        content.setBackground(null);
        content.setBounds(490,200,300,400);

        content.add(tfPseudo);
        content.add(backFromSkinChild);


        skinPseudoButtonPanel.add(content);
        setContentPane(skinPseudoButtonPanel);
    }

    /**FIN methodes menu skin**/



    /**DEBUT methodes menu score : Lisa**/

    //je cree une image(x2)
    //je cree un label dans l'image(x2)
    //je cree un label pour cette image(x2)
    //je cree un panel avec cette image(x2)
    //je creer un panel avec ces 2 panels d'image et le titre
    //ce panel va dans un autre panel
    //ce panel est ajouter dans un panel (avec contenu page)
    // ce panel est ajouter dans un panel final

    /**methode à commenter**/
    /**@param **/
    public void creerTitreScore(JLabel titre, JLabel img, JLabel img2){
        //panCoupe
        JPanel panImgDroite = new JPanel();
        panImgDroite.setBackground(blue);
        panImgDroite.add(img);

        JPanel panImgGauche = new JPanel();
        panImgGauche.setBackground(blue);
        panImgGauche.add(img2);

        // panel coupe + titre
        JPanel pantitre = new JPanel();
        pantitre.setLayout(new GridLayout(1,3));
        pantitre.add(panImgDroite);
        pantitre.add(titre);
        pantitre.add(panImgGauche);

        //pan titre2
        pantitre2 = new JPanel();
        pantitre2.setBackground(green);
        pantitre2.add(pantitre);

        this.setContentPane(pantitre2);

    }


    /**methode à commenter**/
    /**@param **/
    public void creerInterface(JPanel titre) {

        //panel tableau
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan.add(tableau.getTableHeader());
        pan.add(tableau);

        //images medailles
        imgMedaille1 = new ImageIcon("img/score/medaille1.png");
        imgMedaille2 = new ImageIcon("img/score/Medaille2.png");
        imgMedaille3 = new ImageIcon("img/score/Medaille3.png");


        lMedaille1Droite =  new JLabel(imgMedaille1);
        lMedaille2Droite =  new JLabel(imgMedaille2);
        lMedaille3Droite =  new JLabel(imgMedaille3);


        lMedaille1Gauche =  new JLabel(imgMedaille1);
        lMedaille2Gauche =  new JLabel(imgMedaille2);
        lMedaille3Gauche =  new JLabel(imgMedaille3);


        JPanel panImgMedailleDroite = new JPanel();
        panImgMedailleDroite.setLayout(new BoxLayout(panImgMedailleDroite, BoxLayout.Y_AXIS));
        panImgMedailleDroite.add(Box.createRigidArea(new Dimension(0, 25)));
        panImgMedailleDroite.setBackground(green);
        panImgMedailleDroite.add(lMedaille1Droite);
        panImgMedailleDroite.add(lMedaille2Droite);
        panImgMedailleDroite.add(lMedaille3Droite);


        JPanel panImgMedailleGauche = new JPanel();
        panImgMedailleGauche.setLayout(new BoxLayout(panImgMedailleGauche, BoxLayout.Y_AXIS));
        panImgMedailleGauche.add(Box.createRigidArea(new Dimension(0, 25)));
        panImgMedailleGauche.setBackground(green);
        panImgMedailleGauche.add(lMedaille1Gauche);
        panImgMedailleGauche.add(lMedaille2Gauche);
        panImgMedailleGauche.add(lMedaille3Gauche);

        JPanel panImgMedailleDroite2 = new JPanel();
        panImgMedailleDroite2.setBackground(green);
        panImgMedailleDroite2.add(panImgMedailleDroite);

        JPanel panImgMedailleGauche2 = new JPanel();
        panImgMedailleGauche2.setBackground(green);
        panImgMedailleGauche2.add(panImgMedailleGauche);

        // panel médaille + tableau
        JPanel panTable = new JPanel();
        panTable.setLayout(new BoxLayout(panTable, BoxLayout.X_AXIS));
        panTable.setBackground(green);
        panTable.add(panImgMedailleGauche2);
        panTable.add(pan);
        panTable.add(panImgMedailleDroite2);


        //pan tableau2
        JPanel pantable2 = new JPanel();
        pantable2.setBackground(green);
        pantable2.add(panTable);

        //pan boutton
        JPanel panBoutton = new JPanel();
        panBoutton.setBackground(green);
        panBoutton.add(Box.createRigidArea(new Dimension(0, 200)));
        panBoutton.add(bRetour);


        //panel tableau2 + titre2
        JPanel panCadre = new JPanel();
        panCadre.setLayout(new GridLayout(3, 2));
        panCadre.add(titre);
        panCadre.add(pantable2);
        panCadre.add(panBoutton);

        JPanel panEnsemble = new JPanel();
        panEnsemble.setLayout(new BoxLayout(panEnsemble, BoxLayout.Y_AXIS));
        panEnsemble.add(panCadre);

        JPanel panFinal = new JPanel();
        panFinal.setBackground(green);
        panFinal.add(panEnsemble);

        JPanel panFinal2 = new JPanel();
        panFinal2.add(panFinal);

        this.setContentPane(panFinal2);

    }
    /**FIN methodes menu score**/

    /**DEBUT methodes controller**/

    /**ajout tous les bouttons quand vues faites**/
    public void setControlButton(ControlBouton controlBut){
        this.boutonScores.addActionListener(controlBut);
        this.boutonSkins.addActionListener(controlBut);
        this.skinSerpent.addActionListener(controlBut);
        this.skinMap.addActionListener(controlBut);
        this.skinPseudo.addActionListener(controlBut);
        this.backFromSkin.addActionListener(controlBut);
        this.backFromSkinChild.addActionListener(controlBut);
        this.bRetour.addActionListener(controlBut);
    }

    /**FIN methodes controller*/

    /**getter des boutton pour changer les menus**/
    public SnakeButton getBoutonScores() {
        return boutonScores;
    }

    public SnakeButton getBoutonSkins() {
        return boutonSkins;
    }


    /**DEBUT getter des bouttons pour menu skin**/
    public SnakeButton getSkinSerpent() { return skinSerpent; }

    public SnakeButton getSkinMap() { return skinMap; }

    public SnakeButton getSkinPseudo() { return skinPseudo; }

    public SnakeButton getBackFromSkin() { return backFromSkin; }

    public SnakeButton getBackFromSkinChild() { return backFromSkinChild; }

    public JPanel getSkinButtonPanel() { return skinButtonPanel; }

    public JPanel getSkinSerpentButtonPanel() { return skinSerpentButtonPanel; }

    public JPanel getSkinPseudoButtonPanel() { return skinPseudoButtonPanel; }

    public JPanel getSkinMapButtonPanel() { return skinMapButtonPanel; }



    /**FIN getter bouttons pour menu SKIN**/


    public SnakeButton getbRetour() { return bRetour; }

    /**DEBUT méthodes changement de menu**/
    public void changerMenuScore(){
        this.getContentPane().removeAll();
        this.creerTitreScore(lscore,  imgCoupeGauche, imgCoupeDroite);
        this.creerInterface(pantitre2);
        this.setLocation(100,0);
        this.setVisible(true);
    }

    public void changerMenuSkin(){
        setupContainer();
        this.addSkin();
        setVisible(true);
    }

    public void changerMenuPrincipal(){
        this.getContentPane().removeAll();
        this.creerTitre(lMenuPrincipal, imgCoupeGauche, imgCoupeDroite);
        this.creerWidgetMenuPrincipal();
        this.setLocation(100,0);
        this.setVisible(true);
    }
    /**FIN methodes changement menu score**/
}




