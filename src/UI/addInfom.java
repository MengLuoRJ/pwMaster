package UI;
import DataControl.DCB;
import DataControl.Query;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class addInfom {

    Query qr = new Query();


    public addInfom(pageButton pageBtn, bodyClass bodyC){
        BaseFrame addInfom = new BaseFrame("添加账号密码条目", 400, 300);
        addInfom.setResizable(false);
        addInfom.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel nameJLabel = new JLabel("新增账号用途");
        JLabel accontJLabel = new JLabel("新增账号");
        JLabel password = new JLabel("新增密码");
        JLabel tips = new JLabel("新增备注");

        JTextArea nameArea = new JTextArea();
        JTextArea acconArea = new JTextArea();
        JTextArea passwordArea = new JTextArea();
        JTextArea tipsArea = new JTextArea();

        JButton okay = new JButton("确认");
        JButton exit = new JButton("取消");
                
        nameJLabel.setBounds(50,15,300,30);
        nameJLabel.setFont(new Font(null,Font.BOLD,20));
        nameArea.setBounds(50,45,300,20);
        nameArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        accontJLabel.setBounds(50,65,300,30);
        accontJLabel.setFont(new Font(null,Font.BOLD,20));
        acconArea.setBounds(50,95,300,20);
        acconArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        password.setBounds(50,115,300,30);
        password.setFont(new Font(null,Font.BOLD,20));
        passwordArea.setBounds(50,145,300,20);
        passwordArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        tips.setBounds(50, 165, 300, 30);
        tips.setFont(new Font(null,Font.BOLD,20));
        tipsArea.setBounds(50, 195, 300, 20);
        tipsArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        okay.setBounds(120, 225, 70, 30);
        exit.setBounds(195,225, 70, 30);

        okay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nameArea.getText().equals("")) {
                    new Error("请输入账号用途");
                } else if (acconArea.getText().equals("")) {
                    new Error("  请输入账号");
                } else if (passwordArea.getText().equals("")) {
                   new Error("  请输入密码");
                } else if(qr.checkTitleUsed(pageButton.nowPage,nameArea.getText())) {
                    new Error("  名称已存在");

                } else {
                    qr.addAccount(new DCB(
                            pageButton.nowPage,
                            nameArea.getText(),
                            acconArea.getText(),
                            passwordArea.getText(),
                            tipsArea.getText()
                    ));
                    infoNum IFN =  new infoNum();
                    IFN.refresh(bodyC,IFN.setInfoJPanels(pageButton.nowPage));
                    addInfom.dispose();
                }
            }
        });
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addInfom.dispose();
            }
        });

        addInfom.add(nameJLabel);
        addInfom.add(nameArea);
        addInfom.add(accontJLabel);
        addInfom.add(acconArea);
        addInfom.add(password);
        addInfom.add(passwordArea);
        addInfom.add(tips);
        addInfom.add(tipsArea);
        addInfom.add(okay);
        addInfom.add(exit);
        addInfom.showMe(true);
            
    }
}
