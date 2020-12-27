package UI;

import java.awt.*;

import javax.swing.*;

public class BaseFrame extends JFrame{
    private static final long serialVersionUID = 1L;
    public BaseFrame(String title, int w, int h) {
        super();
        this.setTitle(title);
        this.setSize(w,h);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }
    public void showMe(boolean a){
        this.setVisible(a);
    }
    
}
