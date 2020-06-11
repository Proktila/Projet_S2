import java.io.*;
import java.util.Random;

class MapTools {

	public static final String LAB_MAPS_DIR = "maps/lab/";

	/**
	 * Charge un labyrinthe dans une grille
	 * @param pathToCsv chemin du labyrinthe a charger
	 * @return un grille 36*36 des objets composant le labyrinthe
	 */
	public static String[][] readGrid(String pathToCsv) {
		File csvFile = new File(pathToCsv);
		String[][] grid = new String[36][36];

		if (csvFile.isFile()) {
			BufferedReader csvReader = null;
			try {
				csvReader = new BufferedReader(new FileReader(pathToCsv));

				String row;
				int rowNb = 0;
				while ((row = csvReader.readLine()) != null) {
					String[] data = row.split(",");
					for (int i = 0; i<grid[rowNb].length; i++) {
						grid[rowNb][i] = data[i];
					}
					rowNb++;
				}
				csvReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return grid;
	}

	/**
	 * Renvoie le nombre de fichiers de labyrinthe disponibles
	 * @param difficulty difficulte du labyrinthe
	 * @return
	 */
	public static long getNbMap(String difficulty) {
		return new File(LAB_MAPS_DIR + difficulty).list().length;
	}

	/**
	 * Choisi une map parmis les fichiers disponibles
	 * @param difficulty difficulte du labyrinthe
	 * @return chemin complet du fichier
	 */
	public static String getRandomMap(String difficulty) {
		int mapId = new Random().nextInt((int) getNbMap(difficulty));
		String mapName = "lab" + difficulty + mapId;
		String pathToMap = LAB_MAPS_DIR + difficulty  + "/" + mapName + ".csv";

		return pathToMap;
	}

	/**
	 * Affiche la grille dans la console
	 * @param grid
	 */
	public static void display(String[][] grid) {
		for (int i = 0; i<grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++)
				System.out.print(grid[i][j] +" ");
			System.out.println("");
		}
	}
}