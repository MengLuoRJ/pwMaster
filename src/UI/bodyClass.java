package UI;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class bodyClass {
    JPanel mainJPanel = new JPanel();
    JScrollPane mainJScrollPane = new JScrollPane(mainJPanel);
    
    public bodyClass(){
        this.mainJScrollPane.setPreferredSize(new Dimension(620, 700));
        this.mainJScrollPane.setBorder(null);
        this.mainJPanel.setBounds(0, 0, 630, 710);
        this.mainJPanel.setLayout(null);
    }
    public JPanel getMainJPanel() {
        return mainJPanel;
    }
    public JScrollPane getMainJScrollPane() {
        return mainJScrollPane;
    }
    
}
