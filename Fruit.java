import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.*;
import javax.swing.*;
import java.io.IOException;
import java.lang.Math;


public class Fruit {

	private static final long serialVersionUID = 1L;
	private String typeFruit;
	private BufferedImage bImgFruit = null;
	private ImageIcon imgFruit;
	private String effet;
	private int posX;
	private int posY;
	
	public Fruit(){}

	public Fruit(String type){
		this.typeFruit = type;
		this.setFruit();
	}

	public void setFruit(){
		if (typeFruit == "banane") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/banane.png"));
            	effet = "taille+3";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}

		else if (typeFruit == "framboise") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/framb.png"));
            	effet = "eraseWall";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

		else if (typeFruit == "pasteque") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/pasteque.png"));
            	effet = "+3fruits";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

		else if (typeFruit == "mure") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/mure.png"));
            	effet = "slow";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

		else if (typeFruit == "peche") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/peche.png"));
            	effet = "neutralisation des murs pendant 5 secondes";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

		else if (typeFruit == "raisin") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/raisin.png"));
            	effet = "+10Score";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }	
		}

		else if (typeFruit == "pomme") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/pomme.png"));
            	effet = "taille+1";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

		else if (typeFruit == "asperge") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/asperges.png"));
            	effet = "speed+20";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

	    else if (typeFruit == "ananas") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/ananas.png"));
            	effet = "chrono+10";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

	    else if (typeFruit == "cerise") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/cerises.png"));
            	effet = "player!Move";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

		else if (typeFruit == "carotte") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/carotte.png"));
            	effet = "addWall";
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 	
		}

		else if (typeFruit == "piment") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/piment.png"));
            	effet = "dead";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

		else if (typeFruit == "poivron") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/poivron.png"));
            	effet = "apparition de 3 elements malus";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

		else if (typeFruit == "radis") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/radis.png"));
            	effet = "-10score";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

		else if (typeFruit == "chou-fleur") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/choufleur.png"));
            	effet = "taille-3";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

	    else if (typeFruit == "aubergine") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/aubergine.png"));
            	effet = "diminue le chrono de XX secondes";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
	    }

		imgFruit = new ImageIcon(bImgFruit);
        imgFruit = new ImageIcon(imgFruit.getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH));
	}

	public void effect(Model model){
		switch(typeFruit){
			default:
				break;
			case "peche":
				break;
			case "pomme":
				model.setTaille(model.getTaille()+1);
				break;
			case "banane" :
				model.setTaille(model.getTaille()+3);
				break;
			case "raisin":
				model.getScore().setActualScore(model.getScore().getActualScore()+50);
				break;
			case "mure":
				model.setDelay(model.getDelay()+50);
				break;
			case "framboise":
				break;
			case "pasteque":
				break;
			case "ananas":
				break;
			case "cerise":
				break;
			case "asperge" :
				model.setDelay(model.getDelay()-15);
				break;
			case "chou-fleur":
				model.setTaille(model.getTaille()-3);
				break;
			case "carotte":
				break;
			case "poivron":
				break;
			case "piment":
				break;
			case "radis":
				model.getScore().setActualScore(model.getScore().getActualScore()-50);
				break;
			case "aubergine":
				break;
		}
	}




	public String toString(){
		return ("le fruit est de type "+ typeFruit+", son effet est : "+ effet);
	}

	public ImageIcon getImgFruit(){
		return this.imgFruit;
	}


	public void validFruit(int[][] tab,int taille){
		int x,y;
		do {
			x = randomX();
			y = randomY();
		}while(fruitIsOnSnake(x,y,tab,taille));
		this.posX=x;
		this.posY=y;
	}

	/*
    Renvoie un entier random entre les bornes de la largeur de la fenetre
     */
	public int randomX(){
		int random = (int)(Math.random()*((700)+1));
		while(random%20 !=0){
			random = (int)(Math.random()*((700)+1));
		}
		return random;
	}

	/*
    Renvoie un entier random entre les bornes de la hauteur de la fenetre
     */
	public int randomY(){
		int random = (int) (Math.random() * ((660) + 1));
		while(random % 20 != 0){
			random = (int) (Math.random() * ((660) + 1));
		}
		return random;
	}
	// renvoie true si le fruit se trouve sur une partie du serpent
	public boolean fruitIsOnSnake(int x, int y,int[][] snake, int taille ){
		for(int i = 0; i < taille;i++){
			if((x == snake[i][0]) && (y == snake[i][1])){
				return true;
			}
		}
		return false;
	}

	public int getPosX() { return posX; }

	public int getPosY() { return posY; }
}
