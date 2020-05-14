/**CLASS TABLERENDERER POUR TABLEAU DE SCORE**/

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**class qui permet de mettre un fond de couleur sur les 3 premières lignes du tableau**/
class Tableau extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        this.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(this);

        }

        if (row == 0) {
            Color clr = new Color(229, 220, 192);
            this.setBackground(clr);
            setFont(new Font("Monospaced", Font.PLAIN, 15));
            setText((String)value);
        }else if(row == 1) {
            Color clr = new Color(192, 229, 226);
            this.setBackground(clr);
            setFont(new Font("Monospaced", Font.PLAIN, 15));
            setText((String)value);
        }
        else if (row == 2) {
            Color clr = new Color(217, 192, 229);
            this.setBackground(clr);
            setFont(new Font("Monospaced", Font.PLAIN, 15));
            setText((String)value);

        }else{
            Color clr = new Color(255, 255, 255);
            this.setBackground(clr);
            setFont(new Font("Monospaced", Font.PLAIN, 15));
            setText((String)value);
        }

        return this;
    }

}

/**FIN CLASS TABLERENDERER**/