import ForConnection.Connector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends JFrame{
    Main(){
        super("Базы данных");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 50, 700, 600);
        setLayout(null);
        ResultSet rs;

        try {
            Connection connector = Connector.getConnection();
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

            statement.close();
            connector.close();
            setVisible(true);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args){
        new Main();
    }
}
