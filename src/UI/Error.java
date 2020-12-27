package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Error {
    public  Error(String S){
        BaseFrame nameError = new BaseFrame("错误", 500, 200);
        nameError.setResizable(false);
        JLabel tipsName = new JLabel(S);
        JButton okay2 = new JButton("确认");
        tipsName.setBounds(178, 49, 400, 50);
        tipsName.setFont(new Font(null, Font.BOLD, 20));
        okay2.setBounds(205, 94, 70, 30);
        okay2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameError.dispose();
            }
        });
        nameError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nameError.add(tipsName);
        nameError.showMe(true);
        nameError.add(okay2);
    }
}
