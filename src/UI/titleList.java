package UI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class titleList {
    JPanel title = new JPanel();
    public titleList() {
        this.title.setPreferredSize(new Dimension(640, 32));
        this.title.setLayout(null);

       JLabel userJLabel = new JLabel("账号用途",null,SwingConstants.LEFT);
       userJLabel.setBounds(15, 1, 70, 31);
       userJLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
       userJLabel.setBackground(Color.white);
       userJLabel.setOpaque(true);
       this.title.add(userJLabel);

       JLabel numJLabel = new JLabel("账号",null,SwingConstants.LEFT);
       numJLabel.setBounds(85, 1, 100, 31); 
       numJLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
       numJLabel.setBackground(Color.white);
       numJLabel.setOpaque(true);
       this.title.add(numJLabel);

       JLabel passJLabel = new JLabel("密码", null , SwingConstants.LEFT);
       passJLabel.setBounds(185, 1, 200, 31);
       passJLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
       passJLabel.setBackground(Color.white);
       passJLabel.setOpaque(true);
       this.title.add(passJLabel);

       JLabel tipsLabel = new JLabel("用途", null ,SwingConstants.LEFT);
       tipsLabel.setBounds(385, 1, 150, 31);
       tipsLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
       tipsLabel.setBackground(Color.white);
       tipsLabel.setOpaque(true);
       this.title.add(tipsLabel);

       JLabel otherLabel = new JLabel("编辑", null ,SwingConstants.LEFT);
       otherLabel.setBounds(535,1,80,31);
       otherLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
       otherLabel.setBackground(Color.white);
       otherLabel.setOpaque(true);
       this.title.add(otherLabel);
    }
    public JPanel getTitle() {
        return title;
    }
    
}
