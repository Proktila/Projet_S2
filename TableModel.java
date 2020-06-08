/**CLASS TABLEMODEL POUR TABLEAU DE SCORE**/

import javax.swing.table.AbstractTableModel;

/**class qui permet de gérer les données des cellules**/
class TableModel extends AbstractTableModel
{

    // Valeur d'une cellule
    private String[][] object ;
    // Titre d'une colonne
    private String[] titrecolonne ;

    public TableModel(String[][] object,String[] titrecolonne)
    {
        this.object = object ;
        this.titrecolonne = titrecolonne;
    }

    public int getColumnCount()
    {
        return 4;
    }

    public int getRowCount()
    {
        return 50;
    }
    public String getColumnName(int column)
    {
        // Titre de la colonne
        return titrecolonne[column];
    }

    @Override
    public Class getColumnClass(int columnIndex)
    {
        return String.class;
    }

    public String getValueAt(int rowIndex, int columnIndex)
    {
        return object[rowIndex][columnIndex];

    }
}
/**FIN CLASS TABLEMODEL**/

