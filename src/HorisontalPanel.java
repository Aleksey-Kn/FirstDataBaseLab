import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HorisontalPanel extends JPanel {
    HorisontalPanel(DefaultTableModel tableModel, JTable table, String nameOfBase, int x, int y){
        setBounds(x, y, 400, 20);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JButton first = new JButton(new ImageIcon("first.jpeg"));
        first.addActionListener(l -> table.setRowSelectionInterval(0, 0));
        add(first);

        JButton previous = new JButton(new ImageIcon("prev.png"));
        previous.addActionListener(l -> {
            int index = table.getSelectedRow() - 1;
            table.setRowSelectionInterval(index, index);
        });
        add(previous);
        add(new JSeparator(JSeparator.VERTICAL));

        add(new JSeparator(JSeparator.VERTICAL));
        JButton next = new JButton(new ImageIcon("next.png"));
        next.addActionListener(l -> {
            int index = table.getSelectedRow() + 1;
            table.setRowSelectionInterval(index, index);
        });
        add(next);

        JButton last = new JButton(new ImageIcon("last.jpeg"));
        last.addActionListener(l -> table.setRowSelectionInterval(table.getRowCount(), table.getRowCount()));
        add(last);
        add(new JSeparator(JSeparator.VERTICAL));

        JButton add = new JButton(new ImageIcon("add.png"));
        add(add);

        JButton delete = new JButton(new ImageIcon("delete.png"));
        delete.addActionListener(l -> {
            int index = table.getSelectedRow();
            String target = (String)tableModel.getValueAt(index, 0);
            tableModel.removeRow(index);
            try {
                Connection connection = Connector.getConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("delete from " + nameOfBase + " where " + nameOfBase + ".key = " + target);
                statement.close();
                connection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        add(delete);
    }
}
