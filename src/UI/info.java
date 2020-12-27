package UI;


import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class info {
    JTextArea Name = new JTextArea();
    JTextArea Account = new JTextArea();
    JTextArea Password = new JTextArea();
    JTextArea Tips = new JTextArea();
    JButton Other = new JButton("改");
    String pageName;
    String Title;

    public info(String name,String account,String password,String tips,String pageName){
        this.setName(name);
        this.setAccount(account);
        this.setPassword(password);
        this.setTips(tips);
        this.setOther();
        this.setTitle(name);
        this.setPageName(pageName);
    }

    public void setName(String name) {
        this.Name.setText(name);
        this.Name.setBounds(0, 5, 70, 30);
        this.Name.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
        this.Name.setEditable(false);
        this.Name.setOpaque(false);
        this.Name.setBackground(Color.white);
    }
    public JTextArea getName() {
        return Name;
    }
    public void setAccount(String account) {
        this.Account.setText(account);
        this.Account.setBounds(70, 5, 100, 30);
        this.Account.setBorder(BorderFactory.createMatteBorder(0, 0, 0,1, Color.gray));
        this.Account.setEditable(false);
        this.Account.setOpaque(false);
        this.Account.setBackground(Color.white);
    }
    public JTextArea getAccount() {
        return Account;
    }
    public void setPassword(String password) {
        this.Password.setText(password);
        this.Password.setBounds(170, 5, 200, 30);
        this.Password.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
        this.Password.setEditable(false);
        this.Password.setOpaque(false);
        this.Password.setBackground(Color.white);
    }
    public JTextArea getPassword() {
        return Password;
    }
    public void setTips(String tips) {
        this.Tips.setText(tips);
        this.Tips.setBounds(370, 5, 150, 30);
        this.Tips.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
        this.Tips.setEditable(false);
        this.Tips.setOpaque(false);
        this.Tips.setBackground(Color.white);
    }
    public JTextArea getTips() {
        return Tips;
    }
    public void setTitle(String title) {
        this.Title = title;
    }
    public String getTitle() {
        return Title;
    }
    public void setOther() {
        this.Other.setBounds(520, 0, 80,30);
    }
    public void setOther2(JButton other){
        this.Other = other;
        this.Other.setBounds(520, 0, 80,30);
    }
    public JButton getOther() {
        return Other;
    }
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
    public String getPageName() {
        return pageName;
    }
}
