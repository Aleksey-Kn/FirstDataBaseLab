import com.mysql.cj.protocol.Resultset;

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

        try {
            Statement statement = Connector.getConnection().createStatement();

            DefaultTableModel firstModel = new DefaultTableModel();
            JTable firstTable = new JTable(firstModel);
            firstModel.setColumnIdentifiers(new String[]{"Код", "Факультет", "Курс", "Количество групп"});
            ResultSet rs = statement.executeQuery("select * from Facultet");
            while (rs.next()){
                firstModel.addRow(new String[]{rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)});
            }
            JScrollPane firstScrollPane = new JScrollPane(firstTable);
            firstScrollPane.setBounds(25, 10, 650, 100);
            add(firstScrollPane);

            DefaultTableModel secondModel = new DefaultTableModel();
            JTable secondTable = new JTable(firstModel);
            firstModel.setColumnIdentifiers(new String[]{"Код", "Название группы", "Фамилия старосты", "Количество"});
            rs = statement.executeQuery("select * from Group");
            while (rs.next()){
                firstModel.addRow(new String[]{rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)});
            }
            JScrollPane firstScrollPane = new JScrollPane(firstTable);
            firstScrollPane.setBounds(25, 10, 650, 100);
            add(firstScrollPane);

            setVisible(true);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args){
        new Main();
    }
}
