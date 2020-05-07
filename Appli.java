package com.company;

class Appli {
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Vue f = new Vue();
                FenetreS fenetre = new FenetreS();
                PanelS panel = new PanelS();

                System.out.println("Test");

                Wall wall1 = new Wall(0, 0);
                /*Wall wall2 = new Wall(50, 250);*/

                wall1.attribution(panel);
                /*wall2.attribution(panel);*/

                panel.attribution(fenetre);
            }
        });
    }
}

