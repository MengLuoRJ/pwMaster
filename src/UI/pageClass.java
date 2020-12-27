package UI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class pageClass {
    
    JPanel pageJPanel = new JPanel();
    JScrollPane pageJScrollPane = new JScrollPane(pageJPanel);
    public pageClass(){
        this.pageJScrollPane.setBackground(Color.white);
        this.pageJScrollPane.setPreferredSize(new Dimension(170, 800));
        //this.pageJPanel.setSize(150, 800);
        //this.pageJScrollPane.setLayout(null);
        this.pageJPanel.setBackground(Color.WHITE);
        this.pageJPanel.setPreferredSize(new Dimension(170, 800));
        //this.pageJScrollPane.add(this.pageJPanel);
        this.pageJPanel.setLayout(null);
    }
    public JScrollPane getPageJScrollPane() {
        return pageJScrollPane;
    }
    public JPanel getPageJPanel() {
        return pageJPanel;
    }
}
