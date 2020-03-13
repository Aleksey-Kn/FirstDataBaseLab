package FiledFrames;

import ForConnection.Connector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FiveFiledFrame extends JFrame {
    public FiveFiledFrame(DefaultTableModel tableModel, String type){
        super("Add element");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(200, 200, 180, 200);
        setLayout(null);

        JLabel[] labels = new JLabel[5];
        JPanel forLabels = new JPanel();
        forLabels.setLayout(new BoxLayout(forLabels, BoxLayout.Y_AXIS));
        forLabels.setBounds(15, 20, 60, 100);
        JPanel forText = new JPanel();
        forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
        forText.setBounds(95, 20, 80, 100);
        JPanel forButton = new JPanel();
        forButton.setLayout(new BoxLayout(forButton, BoxLayout.X_AXIS));
        forButton.setBounds(15, 150, 60, 20);

        labels[0] = new JLabel("Код");
        if(type.equals("Group")){
            labels[1] = new JLabel("Название группы");
            labels[2] = new JLabel("Фамилия старосты");
            labels[3] = new JLabel("Количество");
            labels[4] = new JLabel("Факультет ID");
        }
        else{
            labels[1] = new JLabel("ФИО");
            labels[2] = new JLabel("Адрес");
            labels[3] = new JLabel("Телефон");
            labels[4] = new JLabel("ID группы");
        }
        for(JLabel l: labels){
            forLabels.add(l);
        }

        JTextField[] textFields = new JTextField[5];
        for(int i = 0; i < 5; i++){
            textFields[i] = new JTextField();
            forText.add(textFields[i]);
        }

        JButton ok = new JButton("OK");
        ok.addActionListener(l -> {
            try {
                Connection connection = Connector.getConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("insert into Facultet." + type + " \n" +
                        "values (" + textFields[0].getText() + ", '" + textFields[1].getText() + "', '" + textFields[2].getText() +
                        "', '" + textFields[3].getText() + "', " + textFields[4].getText() + ");");
                statement.close();
                connection.close();
                String[] strings = new String[5];
                for(int i = 0; i < 5; i++){
                    strings[i] = textFields[i].getText();
                }
                tableModel.addRow(strings);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
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
