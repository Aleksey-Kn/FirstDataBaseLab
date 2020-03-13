package FiledFrames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FourFieldFrame extends JFrame {
    public FourFieldFrame(DefaultTableModel tableModel){
        super("Add");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(200, 200, 180, 200);
        setLayout(null);

        setVisible(true);
    }
}
