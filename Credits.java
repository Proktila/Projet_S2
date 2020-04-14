import java.awt.*;
import javax.swing.*;
import javax.swing.*;

public class Credits {
	public static void main (String[] args){

		public void Credits(Graphics g) {
			String remerciement = " Remerciement à notre tuteur JCC puis à machin machin";
			String musique = " Musique utilisées : Nyan Cat, Harry Potter etc ";
			String image = " images de Nyan Cat etc ";
			String auteur = " Ce projet a été produit par Julien, Théo, Marion, Lisa, Nathan et Arthur";
			String copyright = " Copyright machin truc ";

			  g.setColor(Color.WHITE);
		      g.drawString(remerciement,480, 20);
		      g.drawString(musique, 480, 40);
		      g.drawString(image,480, 20);
		      g.drawString(auteur, 480, 40);
		      g.drawString(copyright,480, 20);
		}
	}
}
