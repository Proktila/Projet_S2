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

class FenetreMenu extends JFrame {


    /**DEBUT COMMUN POUR TOUS**/
    //TEST
    private final Color BG_COLOR = new Color(50,99,23);
    private final Color FG_COLOR = new Color(99, 205, 42);
    private final Font TEXT_FONT = new Font("Monospaced", Font.BOLD, 50);
    private final Model model;

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
    private Object[] listeSerpent = new Object[]{"basic", "nyan", "Element 3", "Element 4", "Element 5"};

    // SKIN MAP
    private JPanel skinMapButtonPanel;
    private JComboBox cbMap;
    private Object[] listeMap = new Object[]{"Rouge", "Bleu", "Element 3", "Element 4", "Element 5"};

    // SKIN PSEUDO
    private JPanel skinPseudoButtonPanel;
    private JTextField tfPseudo;
    private JLabel lPseudo;


    // Elements du menu PARAMETRE
    private JLabel lParam;
    JPanel panTitleParam;
    JLabel lImgTitreDroite;
    JLabel lImgTitreGauche;
    protected JSlider slVolumeMusique;
    protected JSlider slVolumeBruits;
    protected JRadioButton rbFrench;
    protected JRadioButton rbEnglish;
    protected SnakeButton backParam;


    // variables a modifier par les listener du menu parametres
    private String lang;
    /**FIN MENU SKIN**/


    /**DEBUT MENU SCORE**/

    private String[][] data;
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

    /**MENU JOUER**/

    private SnakeButton backPlay;
    private SnakeButton butEasy;
    private SnakeButton butNormal;
    private SnakeButton butHard;
    private SnakeButton butTrad;
    private SnakeButton butLaby;
    private SnakeButton butChrono;
    private SnakeButton butDuo;
    private SnakeButton backDifficulty;
    private JLabel lPlay;

    private JPanel panPlay;
    private JPanel panDifficulty;

    /**FIN MENU JOUER**/

    /** Credits **/
    private SnakeButton backCredits;
    private JPanel panelCredits;
    private JLabel lCredits;
    /** Credits **/

    /**CONSTRUCTEUR de fenetre**/
    public FenetreMenu(Model model) {

        con = getContentPane();

        this.model = model;
        this.setLayout(null);

        this.initAttribut();

        this.creerWidgetMenuPrincipal();

        this.getContentPane().setBackground(green);
        //this.setLocation(100, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // gestion de la fermeture
        this.setLocation(100,0);
        this.setTitle("Snake");
        this.setSize(1280, 720);
        this.setResizable(false);

        this.setVisible(true); // affiche fenetre

    }

    /**DEBUT methodes pour tous les menus**/

    /**methode à commenter**/
    /**@param **/
    public void initAttribut() {
        initMenuPrincipal();
        initSkin();
        initScore();
        initParametre();
        initCredits();
        initJouer();
        backCredits = new SnakeButton("retour");
    }
    public void initMenuPrincipal(){
        lMenuPrincipal = new JLabel (" Menu principal ") ;
        setupTitle(lMenuPrincipal);
        creerTitre(lMenuPrincipal,null,null);

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
    }

    public void initJouer() {

        butEasy = new SnakeButton("Facile");
        butNormal = new SnakeButton("Normal");
        butHard = new SnakeButton("Difficile");
        butTrad = new SnakeButton("Traditionnel");
        butLaby = new SnakeButton("Labyrinthe");
        butChrono = new SnakeButton("Chrono");
        butDuo = new SnakeButton("Duo");
        backPlay = new SnakeButton("Retour");
        backDifficulty = new SnakeButton("Retour");
        lPlay = new JLabel("Jouer");
        setupTitle(lPlay);
        creerTitre(lPlay,null,null);
    }
    public void initCredits(){
        lCredits = new JLabel("Credits");
        setupTitle(lCredits);
        creerTitre(lCredits,null,null);
    }
    public void initScore(){

        //JLabel titre
        lscore = new JLabel("TABLEAU DES SCORES");
        //fonction seTup pour mettre à jout
        setupTitle(lscore);
        //initialisation du bouton retour
        bRetour = new SnakeButton("retour");

        // contenu des cellules
        //tableau d'objet à 2 dimension
        data = new String[][]{{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},
                {"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},
                {"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},
                {"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},
                {"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},
                {"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},
                {"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},
                {"","","",""},{"","","",""}
        };
        // Les titres des colonnes
        title = new String[]{"Mode","Difficulté", "Pseudo", "Score"};

        //initialisation du tableau
        tableau = new JTable(data, title);

        // tableau qui appelle les classes TableModel et Tableau
        table = new TableModel(data, title);
        render = new Tableau();
        tableau.setModel(table);
        tableau.setDefaultRenderer(Object.class, render);

        // styles des titres des colonnes du tableau
        tableau.getTableHeader().setBackground(lightGreen);
        tableau.getTableHeader().setForeground(blue);
        tableau.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 30));

        // espacement entre les celulles du tableau
        tableau.setRowHeight(30);

        //largeur des cellulles du tableau
        tableau.getColumnModel().getColumn(0).setPreferredWidth(200);
        tableau.getColumnModel().getColumn(1).setPreferredWidth(250);
        tableau.getColumnModel().getColumn(2).setPreferredWidth(200);
        tableau.getColumnModel().getColumn(3).setPreferredWidth(150);

        // image coupe
        imgCoupe = new ImageIcon("img/score/coupe.png");

        //label coupe (gauche et droite)
        imgCoupeGauche =  new JLabel(imgCoupe);
        imgCoupeDroite =  new JLabel(imgCoupe);

        // images medailles
        imgMedaille1 = new ImageIcon("img/score/medaille1.png");
        imgMedaille2 = new ImageIcon("img/score/Medaille2.png");
        imgMedaille3 = new ImageIcon("img/score/Medaille3.png");

        // label avec images de médailles
        //médailles de droite
        lMedaille1Droite =  new JLabel(imgMedaille1);
        lMedaille2Droite =  new JLabel(imgMedaille2);
        lMedaille3Droite =  new JLabel(imgMedaille3);

        //médailles à gauche
        lMedaille1Gauche =  new JLabel(imgMedaille1);
        lMedaille2Gauche =  new JLabel(imgMedaille2);
        lMedaille3Gauche =  new JLabel(imgMedaille3);
    }

    public void initSkin(){
        lSkin = new JLabel("Skin");
        setupTitle(lSkin);

        // SKIN Menu
        skinMap = new SnakeButton("Theme map");
        skinPseudo = new SnakeButton("pseudoSerpent");
        backFromSkin = new SnakeButton("Retour");


        skinSerpent = new SnakeButton("Serpent");
        skinMap = new SnakeButton("Map");
        skinPseudo = new SnakeButton("Pseudo");
        backFromSkin = new SnakeButton("Retour");

        backFromSkinChild = new SnakeButton("Retour");

        // SKIN SERPENT

        cbSerpent = new JComboBox(listeSerpent);
        setupCb(cbSerpent);

        // SKIN MAP
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
        slVolumeMusique = new JSlider(JSlider.HORIZONTAL,model.getVOL_MIN(), model.getVOL_MAX(), model.getVOL_INIT());
        slVolumeBruits = new JSlider(JSlider.HORIZONTAL, model.getVOL_MIN(), model.getVOL_MAX(), model.getVOL_INIT());

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

        lParam = new JLabel("Parametres");
        setupTitle(lParam);
        creerTitre(lParam, lImgTitreDroite, lImgTitreGauche);

        rbFrench = new JRadioButton(new ImageIcon("img/langFR.png"), true);
        rbEnglish = new JRadioButton(new ImageIcon("img/langEN.png"), false);
        ButtonGroup rbLangue = new ButtonGroup();
        rbLangue.add(rbFrench);
        rbLangue.add(rbEnglish);

        backParam = new SnakeButton("Retour");
    }


    /**Créer la banderole au dessus du menu**/
    /**@param **/
    public void creerTitre(JLabel titre,  JLabel img, JLabel img2) {

        titreP = new JPanel();
        titreP.setLayout(new GridBagLayout());
        titreP.setBounds(0,25,1280,125);
        titreP.setBackground(blue);
        if(img != null){
            titreP.add(img);
            titreP.add(Box.createHorizontalStrut(50));
        }
        titreP.add(titre);
        if(img2 != null){
            titreP.add(Box.createHorizontalStrut(50));
            titreP.add(img2);
        }
        con.add(titreP);
    }
    /**FIN methodes pour tous les menus**/

    /**DEBUT Methodes menu principale : Marion**/

    public void creerWidgetMenuPrincipal(){
        creerTitre(lMenuPrincipal,null,null);

        pantitre2 = setupContent();
        JPanel content = new JPanel();
        setupPanelBtn(content,5);

        content.add(boutonJouer);
        content.add(boutonSkins);
        content.add(boutonScores);
        content.add(boutonParametres);
        content.add(boutonCredits);

        pantitre2.add(content);
        setContentPane(pantitre2);

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

    public void addPlay() {
        panPlay = setupContent();
        JPanel content = new JPanel();
        setupPanelBtn(content,4);

        content.add(butEasy);
        content.add(butNormal);
        content.add(butHard);
        content.add(backPlay);

        panPlay.add(content);
        setContentPane(panPlay);
    }

    public void addDifficulty(){
        panDifficulty = setupContent();
        JPanel content = new JPanel();
        setupPanelBtn(content,5);

        content.add(butTrad);
        content.add(butLaby);
        content.add(butChrono);
        content.add(butDuo);
        content.add(backDifficulty);

        panDifficulty.add(content);
        setContentPane(panDifficulty);
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
        cb.setPreferredSize(new Dimension(300,91));
        cb.setBackground(blue);
        cb.setForeground(lightGreen);
        cb.setFont(fBtn);
    }

    /**methode à commenter**/
    /**@param **/
    public void setupPanelBtn(JPanel p,int row){
        p.setLayout(new GridLayout(row,1,5,5));
        p.setBackground(null);
        p.setBounds(490,160,300,475);
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
    public void setBackPlay(){
        panPlay.setVisible(true);
        panPlay.add(titreP);
        setContentPane(panPlay);
    }

    public void creerParametresVue() {
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
        panButtonCenter.add(backParam);

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
        content.add(cbMap);
        content.add(backFromSkinChild);

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

    // je cree une image(x2)
    // je cree un label dans l'image(x2)
    // je cree un label pour cette image(x2)
    // je cree un panel avec cette image(x2)
    // je creer un panel avec ces 2 panels d'image et le titre
    // ce panel va dans un autre panel
    // ce panel est ajouter dans un panel (avec contenu page)
    // ce panel est ajouter dans un panel final

    /**méthode création du titre**/
    /**@param titre titre et JLabel de 2 images**/
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

        // pan titre2
        pantitre2 = new JPanel();
        pantitre2.setBackground(green);
        pantitre2.add(pantitre);

        this.setContentPane(pantitre2);

    }


    /**création du contenu de la page score**/
    /**@param titre titre qui est ajouter à la page**/
    public void creerInterface(JPanel titre) {

        // panel tableau (tableau + titres colonnes)
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan.add(tableau.getTableHeader());
        JScrollPane scrollPane = new JScrollPane(tableau, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setBackground(BG_COLOR);
        scrollPane.setBorder(BorderFactory.createLineBorder(BG_COLOR));
        pan.add(scrollPane);
        pan.setPreferredSize(new Dimension(700, 250));
        //création Jpanel avec tous les label des médailles de droite
        JPanel panImgMedailleDroite = new JPanel();
        panImgMedailleDroite.setLayout(new BoxLayout(panImgMedailleDroite, BoxLayout.Y_AXIS));
        //création d'une box area pour ajuster la position des médailles
        panImgMedailleDroite.add(Box.createRigidArea(new Dimension(0, 25)));
        panImgMedailleDroite.setBackground(green);
        panImgMedailleDroite.add(lMedaille1Droite);
        panImgMedailleDroite.add(lMedaille2Droite);
        panImgMedailleDroite.add(lMedaille3Droite);

        //création Jpanel avec tous les label des médailles de gauche
        JPanel panImgMedailleGauche = new JPanel();
        //création d'une box area pour ajuster la position des médailles
        panImgMedailleGauche.setLayout(new BoxLayout(panImgMedailleGauche, BoxLayout.Y_AXIS));
        panImgMedailleGauche.add(Box.createRigidArea(new Dimension(0, 25)));
        panImgMedailleGauche.setBackground(green);
        panImgMedailleGauche.add(lMedaille1Gauche);
        panImgMedailleGauche.add(lMedaille2Gauche);
        panImgMedailleGauche.add(lMedaille3Gauche);

        //panel supplémentaire regroupant le panel qui contient les médaille de droite
        JPanel panImgMedailleDroite2 = new JPanel();
        panImgMedailleDroite2.setBackground(green);
        panImgMedailleDroite2.add(panImgMedailleDroite);

        //panel supplémentaire regroupant le panel qui contient les médaille de gauche
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

        // pan tableau2
        JPanel pantable2 = new JPanel();
        pantable2.setBackground(green);
        pantable2.add(panTable);

        // pan boutton
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

        //ajoutu d'un panel dans un panel
        JPanel panEnsemble = new JPanel();
        panEnsemble.setLayout(new BoxLayout(panEnsemble, BoxLayout.Y_AXIS));
        panEnsemble.add(panCadre);

        //la panel final est affiché
        JPanel panFinal = new JPanel();
        panFinal.setBackground(green);
        panFinal.add(panEnsemble);

        this.setContentPane(panFinal);

    }
    /**FIN methodes menu score**/

    /**DEBUT methodes controller**/

    /**ajout tous les bouttons quand vues faites**/
    public void setControlButton(ControlBouton controlBut){
        // Menu
        this.boutonJouer.addActionListener(controlBut);
        this.boutonScores.addActionListener(controlBut);
        this.boutonSkins.addActionListener(controlBut);
        this.boutonCredits.addActionListener(controlBut);
        this.boutonParametres.addActionListener(controlBut);
        // Score
        this.bRetour.addActionListener(controlBut);
        // Skin
        this.skinSerpent.addActionListener(controlBut);
        this.skinMap.addActionListener(controlBut);
        this.cbSerpent.addActionListener(controlBut);
        this.cbMap.addActionListener(controlBut);
        this.skinPseudo.addActionListener(controlBut);
        this.backFromSkin.addActionListener(controlBut);
        this.backFromSkinChild.addActionListener(controlBut);
        // Credits
        this.backCredits.addActionListener(controlBut);
        // Paramètre
        this.backParam.addActionListener(controlBut);
        this.slVolumeBruits.addChangeListener(controlBut);
        this.slVolumeMusique.addChangeListener(controlBut);
        // Jouer
        this.backPlay.addActionListener(controlBut);
        this.butEasy.addActionListener(controlBut);
        this.butNormal.addActionListener(controlBut);
        this.butHard.addActionListener(controlBut);
        this.butTrad.addActionListener(controlBut);
        this.butLaby.addActionListener(controlBut);
        this.butChrono.addActionListener(controlBut);
        this.butDuo.addActionListener(controlBut);
        this.backDifficulty.addActionListener(controlBut);
        
    }

    /**FIN methodes controller*/


    /**Méthode pour afficher les messages d'erreur
     * @param messageErr pour intialiser un message spécifique**/
    public void creerDialogErr(String messageErr) {
        //création OptionPane, le message est de type erreur
        JOptionPane messErr = new JOptionPane();
        messErr.showMessageDialog(this,messageErr, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    /**getter des boutton pour changer les menus**/
    public SnakeButton getBoutonScores() {
        return boutonScores;
    }

    public SnakeButton getBoutonSkins() {
        return boutonSkins;
    }

    public SnakeButton getBoutonJouer() { return boutonJouer; }

    public SnakeButton getBoutonParametres() { return boutonParametres; }

    public SnakeButton getBoutonCredits() { return boutonCredits; }

    /**DEBUT getter des bouttons pour menu skin**/
    public SnakeButton getSkinSerpent() { return skinSerpent; }

    public SnakeButton getSkinMap() { return skinMap; }

    public SnakeButton getSkinPseudo() { return skinPseudo; }

    public SnakeButton getBackFromSkinChild() { return backFromSkinChild; }


    /**FIN getter bouttons pour menu SKIN**/

    /**DEBUT getter des bouttons back des menu**/

    public SnakeButton getBackFromSkin() { return backFromSkin; }

    public SnakeButton getBackCredits() { return backCredits; }

    public SnakeButton getBackParam() { return backParam; }

    public SnakeButton getBackPlay() { return backPlay; }

    /**FIN getter des bouttons back des menu**/

    /**DEBUT getter des bouttons pour menu jouer**/
    public SnakeButton getButEasy() { return butEasy; }

    public SnakeButton getButNormal() {return butNormal; }

    public SnakeButton getButHard() { return butHard; }

    public SnakeButton getButTrad() { return butTrad; }

    public SnakeButton getButLaby() { return butLaby; }

    public SnakeButton getButChrono() { return butChrono; }

    public SnakeButton getButDuo() { return butDuo; }

    public SnakeButton getBackDifficulty() { return backDifficulty; }

    /**FIN getter des boutons pour menu jouer**/

    /**DEBUT getter des Panel pour menu skin**/

    public JPanel getSkinButtonPanel() { return skinButtonPanel; }

    public JPanel getSkinSerpentButtonPanel() { return skinSerpentButtonPanel; }

    public JPanel getSkinPseudoButtonPanel() { return skinPseudoButtonPanel; }

    public JPanel getSkinMapButtonPanel() { return skinMapButtonPanel; }

    /**FIN getter des Panel pour menu skin**/

    /**DEBUT getter des Panel pour menu jouer**/

    public JPanel getPanPlay() { return panPlay; }

    public JPanel getPanDifficulty() {
        return panDifficulty;
    }
    /**FIN getter des Panel pour menu jouer**/

    /**DEBUT getter des Slider pour menu param**/

    public JSlider getSlVolumeMusique() { return slVolumeMusique; }

    public JSlider getSlVolumeBruits() { return slVolumeBruits; }

    /**FIN getter des Slider pour menu param**/


    public SnakeButton getbRetour() { return bRetour; }

    public JComboBox getCbMap() { return cbMap; }

    /**DEBUT méthodes changement de menu**/

    public void changerMenuJouer(){
        setupContainer();
        this.creerTitre(lPlay,null,null);
        this.addPlay();
        setVisible(true);
    }
    public void changerMenuParam(){
        setupContainer();
        this.creerTitre(lParam,lImgTitreDroite,lImgTitreGauche);
        this.creerParametresVue();
        setVisible(true);
    }
    public void changerMenuCredit(){
        setupContainer();
        this.creerTitre(lCredits,null,null);
        this.addCredit();
        setVisible(true);
    }

    public void changerMenuScore(){
        setupContainer();
        this.creerTitreScore(lscore,  imgCoupeGauche, imgCoupeDroite);
        this.creerInterface(pantitre2);
        this.setVisible(true);
    }

    public void changerMenuSkin(){
        setupContainer();
        this.creerTitre(lSkin, null,null);
        this.addSkin();
        setVisible(true);
    }

    public void changerMenuPrincipal(){
        setupContainer();
        this.creerTitre(lMenuPrincipal, imgCoupeGauche, imgCoupeDroite);
        this.creerWidgetMenuPrincipal();
        this.setVisible(true);
    }

    public void setupContainer(){
        this.getContentPane().removeAll();
        this.repaint();
        this.revalidate();
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public JTable getTableau() {
        return tableau;
    }

    public void setTableau(JTable tableau) {
        this.tableau = tableau;
    }

    public TableModel getTable() {
        return table;
    }

    public void setTable(TableModel table) {
        this.table = table;
    }

    public Tableau getRender() {
        return render;
    }

    public void setRender(Tableau render) {
        this.render = render;
    }

    public Model getModel() { return model; }

    public JTextField getTfPseudo() {
        return tfPseudo;
    }

    public void setTfPseudo(JTextField tfPseudo) {
        this.tfPseudo = tfPseudo;
    }

    public JComboBox getCbSerpent() { return cbSerpent; }
}




