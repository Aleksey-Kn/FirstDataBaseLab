package FiledFrames;

import ForConnection.Names;
import javax.swing.*;

public class FiveFiledFrame extends JFrame {
    FiveFiledFrame(String type){
        super("Add element");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(200, 200, 180, 200);
        setLayout(null);

        JLabel[] labels = new JLabel[5];
        JPanel forLabels = new JPanel();
        forLabels.setLayout(new BoxLayout(forLabels, BoxLayout.Y_AXIS));
        JPanel forText = new JPanel();
        forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
        JPanel forButton = new JPanel();
        forButton.setLayout(new BoxLayout(forButton, BoxLayout.X_AXIS));

        labels[0] = new JLabel("Код");
        if(type == "Group"){
            labels[1] = new JLabel("Название группы");
            labels[2] = new JLabel("Фамилия старосты");
            labels[3] = new JLabel("Количество");
            labels[4] = new JLabel("Факультет ID");
        }
        else{
            labels[2] = new JLabel("ФИО");
            labels[3] = new JLabel("Адрес");
            labels[4] = new JLabel("Телефон");
            labels[5] = new JLabel("ID группы");
        }
        for(JLabel l: labels){
            forLabels.add(l);
        }

        setVisible(true);
    }
}
