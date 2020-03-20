package FiledFrames;

import ForConnection.Connector;
import ForConnection.Names;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FourFieldFrame extends JFrame {
    public FourFieldFrame(DefaultTableModel tableModel){
        super("Add");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(200, 200, 380, 400);
        setLayout(null);

        JLabel[] labels = new JLabel[4];
        JPanel forLabels = new JPanel();
        forLabels.setLayout(new BoxLayout(forLabels, BoxLayout.Y_AXIS));
        forLabels.setBounds(15, 20, 120, 200);
        JPanel forText = new JPanel();
        forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
        forText.setBounds(190, 20, 160, 200);
        JPanel forButton = new JPanel();
        forButton.setLayout(new BoxLayout(forButton, BoxLayout.X_AXIS));
        forButton.setBounds(15, 300, 200, 20);

        labels[0] = new JLabel("Код");
        labels[1] = new JLabel("Факультет");
        labels[2] = new JLabel("Курс");
        labels[3] = new JLabel("Количество групп");
        for(JLabel l: labels){
            forLabels.add(l);
            forLabels.add(new JSeparator(JSeparator.HORIZONTAL));
        }

        JTextField[] textFields = new JTextField[4];
        for(int i = 0; i < 4; i++){
            textFields[i] = new JTextField();
            forText.add(textFields[i]);
        }

        JButton ok = new JButton("OK");
        ok.addActionListener(l -> {
            try {
                Connection connection = Connector.getConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("insert into Facultet.Facultet \n" +
                        "values (" + textFields[0].getText() + ", '" + textFields[1].getText() + "', '" + textFields[2].getText() +
                        "', " + textFields[3].getText() + ");");
                statement.close();
                connection.close();
                String[] strings = new String[4];
                for(int i = 0; i < 4; i++){
                    strings[i] = textFields[i].getText();
                }
                tableModel.addRow(strings);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
            }
            dispose();
        });
        forButton.add(ok);

        JButton close = new JButton("Close");
        close.addActionListener(l -> dispose());
        forButton.add(close);

        add(forButton);
        add(forLabels);
        add(forText);

        setVisible(true);
    }
}
