/**CLASS TABLEMODEL POUR TABLEAU DE SCORE**/

import javax.swing.table.AbstractTableModel;

/**class qui permet de gérer les données des cellules**/
class TableModel extends AbstractTableModel
{

    //attributs
    // Valeur d'une cellule
    private String[][] object ;
    // Titre d'une colonne
    private String[] titreColonne ;

    /**Constructeur
     *@param object : tableau à 2 dimensions de String :
     *              données dans le tableau
     * @param titreColonne : tableau à 1 dimension de String :
     *              titre des colonnes du tableau
     */
    public TableModel(String[][] object,String[] titreColonne) {
        this.object = object ;
        this.titreColonne = titreColonne;
    }

    /** getter
     * @return int*/
    public int getColumnCount() { return 4; }
    public int getRowCount() { return 50; }

    /**getter
     * @param column : numero de la colonne
     * @return String : le nom de la colonne
     */
    public String getColumnName(int column) {
        // Titre de la colonne
        return titreColonne[column];
    }

    /** getter
     * @param columnIndex
     * @return Class
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }

    /*** valeur d'un donnée dans une cellules
     * @param rowIndex : index de la ligne
     * @param columnIndex : index de la colonne
     * @return String
     */
    public String getValueAt(int rowIndex, int columnIndex) {
        return object[rowIndex][columnIndex];
    }
}
/**FIN CLASS TABLEMODEL**/

