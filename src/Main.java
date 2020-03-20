import ForConnection.Connector;
import ForConnection.Names;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends JFrame{
    private boolean sortOnProgress = true;

    Main(){
        super("Базы данных");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 50, 700, 600);
        setLayout(null);
        ResultSet rs;

        try {
            final Connection connector = Connector.getConnection();
            Statement statement = connector.createStatement();

            DefaultTableModel firstModel = new DefaultTableModel();
            JTable firstTable = new JTable(firstModel);
            firstModel.setColumnIdentifiers(new String[]{"Код", "Факультет", "Курс", "Количество групп"});
            rs = statement.executeQuery("select * from Facultet");
            while (rs.next()){
                firstModel.addRow(new String[]{rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)});
            }
            JScrollPane firstScrollPane = new JScrollPane(firstTable);
            firstScrollPane.setBounds(25, 10, 650, 100);
            add(firstScrollPane);

            add(new HorisontalPanel(firstModel, firstTable, "Facultet", 25, 110));

            DefaultTableModel secondModel = new DefaultTableModel();
            JTable secondTable = new JTable(secondModel);
            secondModel.setColumnIdentifiers(new String[]{"Код", "Название группы", "Фамилия старосты", "Количество", "Факультет ID"});
            rs = statement.executeQuery("select * from Grups");
            while (rs.next()){
                secondModel.addRow(new String[]{rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5)});
            }
            JScrollPane secondScrollPane = new JScrollPane(secondTable);
            secondScrollPane.setBounds(25, 185, 650, 100);
            add(secondScrollPane);

            add(new HorisontalPanel(secondModel, secondTable,"Grups", 25, 285));

            DefaultTableModel thirdModel = new DefaultTableModel();
            JTable thirdTable = new JTable(thirdModel);
            thirdModel.setColumnIdentifiers(new String[]{"Код", "ФИО", "Адрес", "Телефон", "ID группы"});
            rs = statement.executeQuery("select * from Students");
            while (rs.next()){
                thirdModel.addRow(new String[]{rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5)});
            }
            JScrollPane thirdScrollPane = new JScrollPane(thirdTable);
            thirdScrollPane.setBounds(25, 360, 650, 100);
            add(thirdScrollPane);

            add(new HorisontalPanel(thirdModel, thirdTable, "Students", 25, 460));

            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setBounds(25, 500, 200, 60);
            leftPanel.add(new JLabel("Поиск по символам"));
            JTextField leftTextFiled = new JTextField();
            leftTextFiled.addActionListener(j -> {
                int end = thirdTable.getRowCount();
                thirdTable.removeRowSelectionInterval(0, end - 1);
                for(int i = 0; i < end; i++){
                    if(((String) thirdModel.getValueAt(i, 1)).contains(leftTextFiled.getText())){
                        thirdTable.addRowSelectionInterval(i, i);
                    }
                }
            });
            leftPanel.add(leftTextFiled);
            add(leftPanel);

            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setBounds(250, 500, 200, 60);
            rightPanel.add(new JLabel("Точный поиск"));
            JTextField rightTextFiled = new JTextField();
            rightTextFiled.addActionListener(j -> {
                int end = thirdTable.getRowCount();
                thirdTable.removeRowSelectionInterval(0, end - 1);
                for(int i = 0; i < end; i++){
                    if(thirdModel.getValueAt(i, 1).equals(rightTextFiled.getText())){
                        thirdTable.addRowSelectionInterval(i, i);
                    }
                }
            });
            rightPanel.add(rightTextFiled);
            add(rightPanel);

            JButton button = new JButton("Сортировать");
            button.setBounds(500, 500, 180, 25);
            button.addActionListener(l -> {
                while (thirdTable.getRowCount() > 0) {
                    thirdModel.removeRow(0);
                }
                try {
                    ResultSet resultSet = statement.executeQuery("select * from Students order by " + Names.Student.fio);
                    if(sortOnProgress){
                        while (resultSet.next()){
                            thirdModel.addRow(new String[]{resultSet.getString(1), resultSet.getString(2),
                                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)});
                        }
                    }
                    else{
                        while (resultSet.next()){
                            thirdModel.insertRow(0, new String[]{resultSet.getString(1), resultSet.getString(2),
                                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)});
                        }
                    }
                    sortOnProgress = !sortOnProgress;
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            });
            add(button);

            setVisible(true);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args){
        new Main();
    }
}
