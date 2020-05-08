/**CLASS TABLEMODEL POUR TABLEAU DE SCORE**/

import javax.swing.table.AbstractTableModel;

/**class à commenter**/
class TableModel extends AbstractTableModel
{

    // Valeur d'une cellule
    private Object[][] object ;
    // Titre d'une colonne
    private String[] titrecolonne ;

    public TableModel(Object[][] object,String[] titrecolonne)
    {
        this.object = object ;
        this.titrecolonne = titrecolonne;
    }

    public int getColumnCount()
    {
        return 3;
    }

    public int getRowCount()
    {
        return 7;
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

    public Object getValueAt(int rowIndex, int columnIndex)
    {

        return object[rowIndex][columnIndex] ;

    }
}
/**FIN CLASS TABLEMODEL**/

