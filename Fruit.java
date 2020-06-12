import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.*;
import javax.swing.*;
import java.io.IOException;
import java.lang.Math;
import java.util.TimerTask;
import java.util.Timer;


public class Fruit {

	private static final long serialVersionUID = 1L;
	private String typeFruit;
	private BufferedImage bImgFruit = null;
	private ImageIcon imgFruit;
	private String effet;
	private int posX;
	private int posY;
	private boolean bonus;
	private Timer timer;
	private int time;
	private Model model;
	
	public Fruit(){}

	public Fruit(String type,Model model){
		this.model = model;
		this.typeFruit = type;
		this.setFruit();
		this.setTimer();
	}

	public void setFruit(){
		if (typeFruit == "banane") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/banane.png"));
            	effet = "taille+3";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=true;
		}

		else if (typeFruit == "framboise") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/framb.png"));
            	effet = "enleve un mur";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=true;
	    }

		else if (typeFruit == "pasteque") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/pasteque.png"));
            	effet = "3 fruits bonus";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=true;
	    }

		else if (typeFruit == "mure") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/mure.png"));
            	effet = "ralentissement";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=true;
	    }

		else if (typeFruit == "peche") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/peche.png"));
            	effet = "murs immobiles 5sec";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=true;
	    }

		else if (typeFruit == "raisin") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/raisin.png"));
            	effet = "+60Score";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=true;
		}

		else if (typeFruit == "pomme") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/pomme.png"));
            	effet = "taille+1";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=true;
	    }

		else if (typeFruit == "asperge") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/asperges.png"));
            	effet = "vitesse+20";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=false;
	    }

	    else if (typeFruit == "ananas") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/ananas.png"));
            	effet = "chrono+10";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=true;
	    }

	    else if (typeFruit == "cerise") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/cerises.png"));
            	effet = "joueur immobile";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=true;
	    }

		else if (typeFruit == "carotte") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/carotte.png"));
            	effet = "ajout d'un mur";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=false;
		}

		else if (typeFruit == "piment") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/piment.png"));
            	effet = "mort";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=false;
	    }

		else if (typeFruit == "poivron") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/poivron.png"));
            	effet = "3 légumes malus";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=false;
	    }

		else if (typeFruit == "radis") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/radis.png"));
            	effet = "-40score";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=false;
	    }

		else if (typeFruit == "chou-fleur") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/choufleur.png"));
            	effet = "taille-2";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=false;
	    }

	    else if (typeFruit == "aubergine") {
			try {
            	bImgFruit = ImageIO.read(new File("img/fruits/aubergine.png"));
            	effet = "diminution chrono";
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			this.bonus=false;
	    }

		imgFruit = new ImageIcon(bImgFruit);
        imgFruit = new ImageIcon(imgFruit.getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH));
	}

	public void effect(Snake snake,Model model){
		switch(typeFruit){
			default:
				break;
			case "peche":
				break;
			case "pomme":
				snake.setTaille(snake.getTaille()+1);
				break;
			case "banane" :
				snake.setTaille(snake.getTaille()+2);
				break;
			case "raisin":
				snake.setScore(snake.getScore()+50);
				break;
			case "mure":
				snake.setDelay(snake.getDelay()+25);
				break;
			case "framboise":
				if(model.getListeWall().size() != 0){
					model.getListeWall().remove(model.getListeWall().size()-1);
				}
				break;
			case "pasteque":
				for(int i=0; i<2; i++){
					Fruit f;
					do{
						f=model.choisirFruit();
					}while(!f.bonus);
					f.validFruit(model.getJ1(),model);
					model.getToAdd().add(f);
				}
				break;
			case "ananas":
				break;
			case "cerise":
				break;
			case "asperge" :
				snake.setDelay(snake.getDelay()-25);
				break;
			case "chou-fleur":
				snake.setTaille(snake.getTaille()-2);
				break;
			case "carotte":
				model.getListeWall().add(new Wall(model,model.getJ1(),model.getListeFruit().get(0)));
				break;
			case "poivron":
				for(int i=0; i<2; i++){
					Fruit f;
					do{
						f=model.choisirFruit();
					}while(f.bonus);
					f.validFruit(model.getJ1(),model);
					model.getToAdd().add(f);
				}
				break;
			case "piment":
				if(model.getJ1().getSnake()[0][0] == this.posX && model.getJ1().getSnake()[0][1] == this.posY ){
					model.getJ1().setDead(true);
				}else if(model.getJ2().getSnake()[0][0] == this.posX && model.getJ2().getSnake()[0][1] == this.posY){
					model.getJ2().setDead(true);
				}
				break;
			case "radis":
				snake.setScore(snake.getScore()-50);
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


	public void validFruit(Snake snake,Model model){
		int x,y;
		do {
			x = randomX();
			y = randomY();
		}while(fruitIsOnSnake(x,y,snake,model));
		this.posX=x;
		this.posY=y;
	}

	/*
    Renvoie un entier random entre les bornes de la largeur de la fenetre
     */
	public int randomX(){
		int random = (int)(Math.random()*((700)+1));
		while(random%20 != 0){
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
	public boolean fruitIsOnSnake(int x, int y,Snake snake,Model model){
		for(int i = 0; i < snake.getTaille();i++){
			if((x == snake.getSnake()[i][0]) && (y == snake.getSnake()[i][1])){
				return true;
			}
		}
		for(Wall w : model.getListeWall()){
			if((x == w.getX()) && (y == w.getY())){
				return true;
			}
		}
		for(Objet obj : model.getListeObjetsLaby()){
			if((x == obj.getX()) && (y == obj.getY())){
				return true;
			}
		}
		return false;
	}

	public int getPosX() { return posX; }

	public int getPosY() { return posY; }

	public void setPosX(int posX) { this.posX = posX; }

	public void setPosY(int posY) { this.posY = posY; }

	public String getTypeFruit() {
		return typeFruit;
	}

	public String getEffet() {
		return effet;
	}

	public void setTimer(){
		this.timer = new Timer();
		this.time = 10;
		Fruit f = this;

		timer.schedule(new TimerTask() {
			public void run(){
				if(time == 0){
					model.getListeFruit().remove(f);
					if(model.getListeFruit().size() == 0 ){
						Fruit newFruit;
						newFruit = model.choisirFruit();
						newFruit.validFruit(model.getJ1(),model);
						model.getToAdd().add(newFruit);
					}
					cancel();
				}
				time--;
			}
		}, 1000, 1000);

	}


}
