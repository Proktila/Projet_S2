import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;

class FenetreMenu extends JFrame {
    private final Model model;

    // menu principal
    private JLabel lMenuPrincipal;
    private SnakeButton boutonJouer;
    private SnakeButton boutonSkins;
    private SnakeButton boutonScores;
    private SnakeButton boutonParametres;
    private SnakeButton boutonCredits;

    ImageIcon imageGauche;
    ImageIcon imageDroite;

    // menu skin
    JPanel titreP;
    Container con;

    JLabel lSkin;
    private SnakeButton skinSerpent;
    private SnakeButton skinMap;
    private SnakeButton skinPseudo;
    private SnakeButton backFromSkin;
    private SnakeButton backFromSkinChild;
    private JPanel skinButtonPanel;

    private JPanel skinSerpentButtonPanel;
    private JComboBox cbSerpent;
    private final Object[] listeSerpent = new Object[]{"basique", "nyan", "hack", "gold", "fish"};

    private JPanel skinMapButtonPanel;
    private JComboBox cbMap;
    private final Object[] listeMap = new Object[]{"Classic", "Space", "Cloud", "Matrix", "Wall"};

    private JPanel skinPseudoButtonPanel;
    private JTextField tfPseudo;


    // menu parametre
    private JLabel lParam;
    JPanel panTitleParam;
    JLabel lImgTitreDroite;
    JLabel lImgTitreGauche;
    private JSlider slVolumeMusique;
    private JSlider slVolumeBruits;
    private JRadioButton rbFrench;
    private JRadioButton rbEnglish;
    protected SnakeButton backParam;

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

    private JPanel pantitre2;

    private SnakeButton bRetour;
    private JButton bClear;

    // joueur
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
    private String[][] data;

    private JPanel panPlay;
    private JPanel panDifficulty;

    // Credits
    private SnakeButton backCredits;
    private JLabel lCredits;

    public FenetreMenu(Model model) {
        con = getContentPane();

        this.model = model;
        this.setLayout(null);

        Image icone =Toolkit.getDefaultToolkit().getImage("img/icone.png");
        this.setIconImage(icone);

        this.initAttribut();
        this.addMainMenu();

        this.setTitle("Sn'hack");
        this.getContentPane().setBackground(model.getGREEN());
        this.setLocation(100,0);
        this.setSize(1280, 720);
        this.setResizable(false);

        this.setVisible(true); // affiche fenetre

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initialise tout les objets de chaque menu
     * Lancement de la musique du jeu
     */
    public void initAttribut() {
        initMenuPrincipal();
        initSkin();
        initScore();
        initParametre();
        initCredits();
        initJouer();

        try {
            model.setMusicClip(Sound.playMusic("sound/music.wav", model.getVolumeMusique()));
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            creerDialogErr(e.getMessage());
        }

        backCredits = new SnakeButton(model.textFromLang("retour", "back"));
    }

    /**
     * Initialisation des objets du menu principal
     */
    public void initMenuPrincipal(){
        lMenuPrincipal = new JLabel(model.textFromLang("Sn'hack", "Sn'hack"));
        setupTitle(lMenuPrincipal);
        createTitle(lMenuPrincipal,null,null);

        //je remplace myboutton par Snakeboutton
        boutonJouer = new SnakeButton(model.textFromLang("Jouer", "Play"));
        boutonSkins = new SnakeButton(model.textFromLang("Apparence", "Skin"));
        boutonScores = new SnakeButton(model.textFromLang("Tableau des scores", "Scoreboard"));
        boutonParametres = new SnakeButton(model.textFromLang("Parametres", "Settings"));
        boutonCredits = new SnakeButton("Credits");
    }

    /**
     * Initialisation des objets du menu jouer
     */
    public void initJouer() {
        butEasy = new SnakeButton(model.textFromLang("Facile", "Easy"));
        butNormal = new SnakeButton("Normal");
        butHard = new SnakeButton(model.textFromLang("Difficile", "Hard"));
        butTrad = new SnakeButton(model.textFromLang("Classique", "Classic"));
        butLaby = new SnakeButton(model.textFromLang("Labyrinthe", "Maze"));
        butChrono = new SnakeButton(model.textFromLang("Chrono", "Timer"));
        butDuo = new SnakeButton("Duo");
        backPlay = new SnakeButton(model.textFromLang("Retour", "Back"));
        backDifficulty = new SnakeButton(model.textFromLang("Retour", "Back"));
        lPlay = new JLabel(model.textFromLang("Jouer", "Play"));
        setupTitle(lPlay);
        createTitle(lPlay,null,null);
    }

    /**
     * Initialisation des objets du menu credits
     */
    public void initCredits(){
        lCredits = new JLabel("Credits");
        setupTitle(lCredits);
        createTitle(lCredits,null,null);
    }

    /**
     * Initialisation des objets du menu score
     */
    public void initScore(){
        //JLabel titre
        lscore = new JLabel(model.textFromLang("Tableau des scores", "Scoreboard"));
        //fonction seTup pour mettre à jout
        setupTitle(lscore);
        //initialisation du bouton retour et vide
        bRetour = new SnakeButton(model.textFromLang("Retour", "Back"));
        bClear = new SnakeButton(model.textFromLang("Vider le tableau", "Empty the table"));

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
        // score
        String[] title = new String[]{"Mode", model.textFromLang("Difficulte", "Difficulty"), "Pseudo", "Score"};

        //initialisation du tableau
        tableau = new JTable(data, title);

        // tableau qui appelle les classes TableModel et Tableau
        TableModel table = new TableModel(data, title);
        Tableau render = new Tableau();
        tableau.setModel(table);
        tableau.setDefaultRenderer(Object.class, render);

        // styles des titres des colonnes du tableau
        tableau.getTableHeader().setBackground(model.getLIGHT_GREEN());
        tableau.getTableHeader().setForeground(model.getBLUE());
        tableau.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 30));

        // espacement entre les celulles du tableau
        tableau.setRowHeight(30);

        // largeur des cellulles du tableau
        tableau.getColumnModel().getColumn(0).setPreferredWidth(200);
        tableau.getColumnModel().getColumn(1).setPreferredWidth(250);
        tableau.getColumnModel().getColumn(2).setPreferredWidth(250);
        tableau.getColumnModel().getColumn(3).setPreferredWidth(150);

        // image coupe
        ImageIcon imgCoupe = new ImageIcon("img/score/coupe.png");

        //label coupe (gauche et droite)
        imgCoupeGauche =  new JLabel(imgCoupe);
        imgCoupeDroite =  new JLabel(imgCoupe);

        // images medailles
        ImageIcon imgMedaille1 = new ImageIcon("img/score/medaille1.png");
        ImageIcon imgMedaille2 = new ImageIcon("img/score/Medaille2.png");
        ImageIcon imgMedaille3 = new ImageIcon("img/score/Medaille3.png");

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

    /**
     * Initialisation des objets du menu apparence
     */
    public void initSkin(){
        lSkin = new JLabel(model.textFromLang("Apparence", "Skin"));
        setupTitle(lSkin);

        // SKIN Menu
        skinMap = new SnakeButton(model.textFromLang("Theme plateau", "Map theme"));
        skinPseudo = new SnakeButton("Pseudo");
        backFromSkin = new SnakeButton(model.textFromLang("Retour", "Back"));


        skinSerpent = new SnakeButton(model.textFromLang("Serpent", "Snake"));
        skinMap = new SnakeButton(model.textFromLang("Plateau", "Map"));
        skinPseudo = new SnakeButton("Pseudo");
        backFromSkin = new SnakeButton(model.textFromLang("Retour", "Back"));
        backFromSkinChild = new SnakeButton(model.textFromLang("Retour", "Back"));

        // SKIN SERPENT
        cbSerpent = new JComboBox(listeSerpent);
        setupCb(cbSerpent);

        // SKIN MAP
        cbMap = new JComboBox(listeMap);
        setupCb(cbMap);

        // SKIN PSEUDO
        tfPseudo = new JTextField(25);
        tfPseudo.setFont(model.getFONT_18());
        tfPseudo.setForeground(model.getLIGHT_GREEN());
        tfPseudo.setBackground(model.getBLUE());
        tfPseudo.setText("Pseudo");
        tfPseudo.setPreferredSize(new Dimension(300,91));

        JLabel lPseudo = new JLabel();
        lPseudo.add(tfPseudo);
    }

    /**
     * Initialisation des objets du menu parametre
     */
    public void initParametre(){
        slVolumeMusique = new JSlider(JSlider.HORIZONTAL,model.getVOL_MIN(), model.getVOL_MAX(), model.getVOL_INIT_MUSIC());
        slVolumeBruits = new JSlider(JSlider.HORIZONTAL, model.getVOL_MIN(), model.getVOL_MAX(), model.getVOL_INIT_BRUITS());

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

        lParam = new JLabel(model.textFromLang("Parametres", "Settings"));
        setupTitle(lParam);
        createTitle(lParam, lImgTitreDroite, lImgTitreGauche);

        rbFrench = new JRadioButton(new ImageIcon("img/langFR.png"), true);
        rbEnglish = new JRadioButton(new ImageIcon("img/langEN.png"), false);
        ButtonGroup rbLangue = new ButtonGroup();
        rbLangue.add(rbFrench);
        rbLangue.add(rbEnglish);

        backParam = new SnakeButton(model.textFromLang("Retour", "Back"));
    }

    /**
     * Creer le menu principal
     */
    public void addMainMenu(){
        createTitle(lMenuPrincipal,null,null);

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

    /**
     * Creer le menu credits
     */
    public void addCredit(){
        JLabel taText1 = new JLabel("Ce jeu à été conçu par DUHOUX Arthur, DOYEN Lisa, ");
        JLabel taText2 = new JLabel("DUBAIL Marion, GANARD theo, SCHNEIDER Nathan, VERWAERDE Julien,");
        JLabel taText3 = new JLabel("dans le cadre de notre projet tuteuré de S2.");
        JLabel taText4 = new JLabel("La musique utilisée sur le menu et en jeu est");
        JLabel taText5 = new JLabel("Blippy Trance de MACLEOD Kevin.");
        JLabel taText6 = new JLabel("Pour la realisation de ce jeu nous tenons a remercier ");
        JLabel taText7 = new JLabel("notre tuteur monsieur CHARR Jean Claude.");

        JPanel panelCredits = setupContent();

        JPanel content = new JPanel();
        content.setBackground(model.getBLUE());
        content.setBounds(280,170,720,425);

        applyStyle(taText1);
        applyStyle(taText2);
        applyStyle(taText3);
        applyStyle(taText4);
        applyStyle(taText5);
        applyStyle(taText6);
        applyStyle(taText7);

        content.add(Box.createVerticalStrut(50));
        content.add(taText1);
        content.add(taText2);
        content.add(taText3);
        content.add(Box.createVerticalStrut(50));
        content.add(taText4);
        content.add(taText5);
        content.add(Box.createVerticalStrut(50));
        content.add(taText6);
        content.add(taText7);
        content.add(Box.createVerticalStrut(50));
        content.add(backCredits);

        panelCredits.add(content);
        setContentPane(panelCredits);
    }

    /**
     * Creer le menu joueur
     */
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

    /**
     * Creer le menu de choix de difficulte
     */
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

    /**
     * Creer le menu parametre
     */
    public void addParamMenu() {
        panTitleParam = setupContent();

        JLabel lSon = new JLabel(model.textFromLang("Parametres son", "Sound settings"));
        JLabel lMusique = new JLabel(model.textFromLang("Volume de la musique", "Music volume"));
        JLabel lBruitages = new JLabel(model.textFromLang("Volume des bruitages", "Sounds volume"));
        JLabel lLangue = new JLabel(model.textFromLang("Langage", "Language"));

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
        lSon.setFont(model.getFONT_18());
        lLangue.setFont(model.getFONT_18());

        // applique une dimension de 400px par 25px au JSlider et donne la couleur du background
        Dimension slDimensions = new Dimension(400,25);
        slVolumeMusique.setPreferredSize(slDimensions);
        slVolumeBruits.setPreferredSize(slDimensions);
        slVolumeMusique.setBackground(model.getGREEN());
        slVolumeBruits.setBackground(model.getGREEN());

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
        panParametre.setBackground(model.getGREEN());
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
    }

    /**
     * Creer le menu apparence
     */
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

    /**
     * Creer le menu du choix de l'apparence du serpent
     */
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

    /**
     * Creer le menu du choix de l'apparence du plateau
     */
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

    /**
     * Creer le menu du choix du pseudo du serpent
     */
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

    /**
     * Creer le titre du menu score
     * @param titre
     * @param img icon de droite
     * @param img2 icon de gauche
     */
    public void createTitleScoreMenu(JLabel titre, JLabel img, JLabel img2){
        //panCoupe
        JPanel panImgDroite = new JPanel();
        panImgDroite.setBackground(model.getBLUE());
        panImgDroite.add(img);

        JPanel panImgGauche = new JPanel();
        panImgGauche.setBackground(model.getBLUE());
        panImgGauche.add(img2);

        // panel coupe + titre
        JPanel pantitre = new JPanel();
        pantitre.setLayout(new GridLayout(1,3));
        pantitre.add(panImgDroite);
        pantitre.add(titre);
        pantitre.add(panImgGauche);

        // pan titre2
        pantitre2 = new JPanel();
        pantitre2.setBackground(model.getGREEN());
        pantitre2.add(pantitre);

        this.setContentPane(pantitre2);
    }

    /**
     * Interface menu score
     * @param titre Jpanel du titre
     */
    public void createInterface(JPanel titre) {
        // panel tableau (tableau + titres colonnes)+ scrollPane
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan.add(tableau.getTableHeader());
        JScrollPane scrollPane = new JScrollPane(tableau, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setBackground(model.getGREEN());
        scrollPane.setBorder(BorderFactory.createLineBorder(model.getGREEN()));
        pan.add(scrollPane);
        pan.setPreferredSize(new Dimension(700, 250));
        //création Jpanel avec tous les label des médailles de droite
        JPanel panImgMedailleDroite = new JPanel();
        panImgMedailleDroite.setLayout(new BoxLayout(panImgMedailleDroite, BoxLayout.Y_AXIS));
        //création d'une box area pour ajuster la position des médailles
        panImgMedailleDroite.add(Box.createRigidArea(new Dimension(0, 25)));
        panImgMedailleDroite.setBackground(model.getGREEN());
        panImgMedailleDroite.add(lMedaille1Droite);
        panImgMedailleDroite.add(lMedaille2Droite);
        panImgMedailleDroite.add(lMedaille3Droite);

        //création Jpanel avec tous les label des médailles de gauche
        JPanel panImgMedailleGauche = new JPanel();
        //création d'une box area pour ajuster la position des médailles
        panImgMedailleGauche.setLayout(new BoxLayout(panImgMedailleGauche, BoxLayout.Y_AXIS));
        panImgMedailleGauche.add(Box.createRigidArea(new Dimension(0, 25)));
        panImgMedailleGauche.setBackground(model.getGREEN());
        panImgMedailleGauche.add(lMedaille1Gauche);
        panImgMedailleGauche.add(lMedaille2Gauche);
        panImgMedailleGauche.add(lMedaille3Gauche);

        //panel supplémentaire regroupant le panel qui contient les médaille de droite
        JPanel panImgMedailleDroite2 = new JPanel();
        panImgMedailleDroite2.setBackground(model.getGREEN());
        panImgMedailleDroite2.add(panImgMedailleDroite);

        //panel supplémentaire regroupant le panel qui contient les médaille de gauche
        JPanel panImgMedailleGauche2 = new JPanel();
        panImgMedailleGauche2.setBackground(model.getGREEN());
        panImgMedailleGauche2.add(panImgMedailleGauche);

        // panel médaille + tableau
        JPanel panTable = new JPanel();
        panTable.setLayout(new BoxLayout(panTable, BoxLayout.X_AXIS));
        panTable.setBackground(model.getGREEN());
        panTable.add(panImgMedailleGauche2);
        panTable.add(pan);
        panTable.add(panImgMedailleDroite2);

        //panel bouton vide tableau
        JPanel panBouttonClear = new JPanel();
        panBouttonClear.setBackground(model.getGREEN());
        panBouttonClear.add(bClear);

        // pan tableau2
        JPanel pantable2 = new JPanel();
        pantable2.setBackground(model.getGREEN());
        pantable2.add(panTable);
        pantable2.add(panBouttonClear);

        // pan boutton
        JPanel panBoutton = new JPanel();
        panBoutton.setBackground(model.getGREEN());
        panBoutton.add(Box.createRigidArea(new Dimension(0, 200)));
        panBoutton.add(bRetour);


        //panel tableau2 + titre2
        JPanel panCadre = new JPanel();
        panCadre.setLayout(new GridLayout(3, 2));
        panCadre.add(titre);
        panCadre.add(pantable2);
        panCadre.add(panBoutton);

        //ajout d'un panel dans un panel
        JPanel panEnsemble = new JPanel();
        panEnsemble.setLayout(new BoxLayout(panEnsemble, BoxLayout.Y_AXIS));
        panEnsemble.add(panCadre);

        //la panel final est affiché
        JPanel panFinal = new JPanel();
        panFinal.setBackground(model.getGREEN());
        panFinal.add(panEnsemble);

        this.setContentPane(panFinal);
    }

    /**
     * Creer la banderole de titre au dessus du menu
     * @param titre titre du menu
     * @param img icon de gauche
     * @param img2 icon de droite
     */
    public void createTitle(JLabel titre, JLabel img, JLabel img2) {
        titreP = new JPanel();
        titreP.setLayout(new GridBagLayout());
        titreP.setBounds(0,25,1280,125);
        titreP.setBackground(model.getBLUE());
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

    /**
     * Applique le style d'un combobox
     * @param cb combobox a editer
     */
    public void setupCb(JComboBox cb){
        cb.setPreferredSize(new Dimension(300,91));
        cb.setBackground(model.getBLUE());
        cb.setForeground(model.getLIGHT_GREEN());
        cb.setFont(model.getFONT_18());
    }

    /**
     * Creer un Jpanel grille
     * @param p Jpanl a editer
     * @param row nombre de lignes
     */
    public void setupPanelBtn(JPanel p,int row){
        p.setLayout(new GridLayout(row,1,5,5));
        p.setBackground(null);
        p.setBounds(490,160,300,475);
    }

    /**
     * Reaffiche le menu skin depuis un de ses enfants
     */
    public void setBackSkin(){
        skinButtonPanel.setVisible(true);
        skinButtonPanel.add(titreP);
        setContentPane(skinButtonPanel);
    }

    /**
     * Reaffiche le menu skin depuis un de ses enfants
     */
    public void setBackPlay(){
        panPlay.setVisible(true);
        panPlay.add(titreP);
        setContentPane(panPlay);
    }

    /**
     * Applique le style du titre au label
     * @param l label a editer
     */
    public void setupTitle(JLabel l){
        l.setHorizontalAlignment(SwingConstants.LEFT);
        l.setFont(model.getFONT_50());
        l.setForeground(model.getLIGHT_GREEN());
        l.setBackground(model.getBLUE());
        l.setOpaque(true);
    }


    /**
     * Applique le style des objets du menu param
     * @param obj objet a editer
     */
    public void applyStyle(JComponent obj) {
        obj.setBackground(model.getGREEN());
        obj.setFont(model.getFONT_18());
        obj.setForeground(model.getLIGHT_GREEN());
    }

    /**
     * Creer un JPanel avec un style par defaut
     * @return JPanel
     */
    public JPanel setupContent(){
        JPanel c = new JPanel();
        c.setLayout(null);
        c.setBackground(model.getGREEN());
        c.add(titreP);
        return c;
    }

    /**
     * Reinitialise le container
     */
    public void setupContainer(){
        this.getContentPane().removeAll();
        this.repaint();
        this.revalidate();
    }

    /**
     * Affiche le menu joueur
     */
    public void changerMenuJouer(){
        setupContainer();
        this.createTitle(lPlay,null,null);
        this.addPlay();
        setVisible(true);
    }

    /**
     * Affiche le menu parametre
     */
    public void changerMenuParam(){
        setupContainer();
        this.createTitle(lParam,lImgTitreDroite,lImgTitreGauche);
        this.addParamMenu();
        setVisible(true);
    }

    /**
     * Affiche le menu credit
     */
    public void changerMenuCredit(){
        setupContainer();
        this.createTitle(lCredits,null,null);
        this.addCredit();
        setVisible(true);
    }

    /**
     * Affiche le menu score
     */
    public void changerMenuScore(){
        setupContainer();
        this.createTitleScoreMenu(lscore,  imgCoupeGauche, imgCoupeDroite);
        this.createInterface(pantitre2);
        this.setVisible(true);
    }

    /**
     * Affiche le menu apparence
     */
    public void changerMenuSkin(){
        setupContainer();
        this.createTitle(lSkin, null,null);
        this.addSkin();
        setVisible(true);
    }

    /**
     * Affiche le menu principal
     */
    public void changerMenuPrincipal(){
        setupContainer();
        this.createTitle(lMenuPrincipal, imgCoupeGauche, imgCoupeDroite);
        this.addMainMenu();
        this.setVisible(true);
    }

    /**
     * MVC, lie les objets avec la classe contenant les listeners
     * @param controlBut classe contenant les listeners
     */
    public void setControlButton(ControlBouton controlBut){
        // Menu
        this.boutonJouer.addActionListener(controlBut);
        this.boutonScores.addActionListener(controlBut);
        this.boutonSkins.addActionListener(controlBut);
        this.boutonCredits.addActionListener(controlBut);
        this.boutonParametres.addActionListener(controlBut);
        // Score
        this.bRetour.addActionListener(controlBut);
        this.bClear.addActionListener(controlBut);
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
        this.rbEnglish.addActionListener(controlBut);
        this.rbFrench.addActionListener(controlBut);
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

    /**
     * Affiche un message d'erreur
     * @param messageErr message
     */
    public void creerDialogErr(String messageErr) {
        //création OptionPane, le message est de type erreur
        JOptionPane messErr = new JOptionPane();
        JOptionPane.showMessageDialog(this,messageErr, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public SnakeButton getBoutonScores() {
        return boutonScores;
    }
    public SnakeButton getBoutonSkins() {
        return boutonSkins;
    }
    public SnakeButton getBoutonJouer() { return boutonJouer; }
    public SnakeButton getBoutonParametres() { return boutonParametres; }
    public SnakeButton getBoutonCredits() { return boutonCredits; }

    public SnakeButton getSkinSerpent() { return skinSerpent; }
    public SnakeButton getSkinMap() { return skinMap; }
    public SnakeButton getSkinPseudo() { return skinPseudo; }

    public SnakeButton getBackFromSkinChild() { return backFromSkinChild; }
    public SnakeButton getBackFromSkin() { return backFromSkin; }
    public SnakeButton getBackCredits() { return backCredits; }
    public SnakeButton getBackParam() { return backParam; }
    public SnakeButton getBackDifficulty() { return backDifficulty; }
    public SnakeButton getBackPlay() { return backPlay; }

    public SnakeButton getButEasy() { return butEasy; }
    public SnakeButton getButNormal() {return butNormal; }
    public SnakeButton getButHard() { return butHard; }
    public SnakeButton getButTrad() { return butTrad; }
    public SnakeButton getButLaby() { return butLaby; }
    public SnakeButton getButChrono() { return butChrono; }
    public SnakeButton getButDuo() { return butDuo; }
    public JButton getbClear() { return bClear; }

    public JPanel getSkinButtonPanel() { return skinButtonPanel; }
    public JPanel getSkinSerpentButtonPanel() { return skinSerpentButtonPanel; }
    public JPanel getSkinPseudoButtonPanel() { return skinPseudoButtonPanel; }
    public JPanel getSkinMapButtonPanel() { return skinMapButtonPanel; }
    public JComboBox getCbSerpent() { return cbSerpent; }

    public JPanel getPanPlay() { return panPlay; }
    public JPanel getPanDifficulty() {
        return panDifficulty;
    }

    public JSlider getSlVolumeMusique() { return slVolumeMusique; }
    public JSlider getSlVolumeBruits() { return slVolumeBruits; }
    public JRadioButton getRbFrench() { return rbFrench; }
    public JRadioButton getRbEnglish() { return rbEnglish; }

    public SnakeButton getbRetour() { return bRetour; }
    public JComboBox getCbMap() { return cbMap; }

    public String[][] getData() {
        return data;
    }
    public void setData(String[][] data) {
        this.data = data;
    }

    public JTextField getTfPseudo() {
        return tfPseudo;
    }
}




