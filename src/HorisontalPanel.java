import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HorisontalPanel extends JPanel {
    HorisontalPanel(DefaultTableModel tableModel, JTable table, String nameOfBase, int x, int y){
        setBounds(x, y, 400, 20);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JTextField textField = new JTextField();
        textField.addActionListener(l -> {
            try {
                int var = Integer.parseInt(textField.getText());
                if (var > 0) {
                    if (var < table.getRowCount()) {
                        table.setRowSelectionInterval(var - 1, var - 1);
                    } else {
                        int n = table.getRowCount() - 1;
                        table.setRowSelectionInterval(n, n);
                    }
                }
            }catch (NumberFormatException e){
                textField.setText("");
            }
        });

        JButton first = new JButton(new ImageIcon("first.jpeg"));
        first.addActionListener(l -> {
            table.setRowSelectionInterval(0, 0);
            textField.setText("1");
        });
        add(first);

        JButton previous = new JButton(new ImageIcon("prev.png"));
        previous.addActionListener(l -> {
            int index = table.getSelectedRow() - 1;
            if(index >= 0) {
                table.setRowSelectionInterval(index, index);
                textField.setText(Integer.toString(index + 1));
            }
        });
        add(previous);
        add(new JSeparator(JSeparator.VERTICAL));

        add(textField);

        JLabel label = new JLabel("из " + table.getRowCount());
        add(label);

        add(new JSeparator(JSeparator.VERTICAL));
        JButton next = new JButton(new ImageIcon("next.png"));
        next.addActionListener(l -> {
            int index = table.getSelectedRow() + 1;
            if(index < table.getRowCount()) {
                table.setRowSelectionInterval(index, index);
                textField.setText(Integer.toString(index + 1));
            }
        });
        add(next);

        JButton last = new JButton(new ImageIcon("last.jpeg"));
        last.addActionListener(l -> {
            int grc = table.getRowCount();
            table.setRowSelectionInterval(grc, grc);
            textField.setText(Integer.toString(grc));
        });
        add(last);
        add(new JSeparator(JSeparator.VERTICAL));

        JButton add = new JButton(new ImageIcon("add.png"));
        add(add);

        JButton delete = new JButton(new ImageIcon("delete.png"));
        delete.addActionListener(l -> {
            int index = table.getSelectedRow();
            String target = (String)tableModel.getValueAt(index, 0);
            tableModel.removeRow(index);
            label.setText("из " + table.getRowCount());
            try {
                Connection connection = Connector.getConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("delete from " + nameOfBase + " where " + nameOfBase + ".key = " + target);
                statement.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
        add(delete);
    }
}
