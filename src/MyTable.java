import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MyTable extends JTable {
    MyTable(DefaultTableModel defaultTableModel){
        super(defaultTableModel);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
