package UI;

import DataControl.GlobalValue;
import DataControl.Query;
import UserCenter.MainUser;

import java.awt.*;
import java.awt.event.*;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Login {

    Query qr = new Query();

    MainUser mw = new MainUser();

    public Login(){
        BaseFrame login = new BaseFrame("登录", 350, 250);
        login.setResizable(false);

        JLabel Name = new JLabel("账号：");
        JLabel Password = new JLabel("密码：");
        

        JTextArea nameTextArea = new JTextArea();
        JTextArea passwordTextArea = new JTextArea();

        JButton okay = new JButton("确认");
        JButton forget = new JButton("忘记密码");
        JButton exit = new JButton("退出");

        Name.setFont(new Font(null,Font.BOLD,20));
        Name.setBounds(70,40,212,30);
        nameTextArea.setBounds(70, 70, 212, 20);
        nameTextArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        Password.setFont(new Font(null,Font.BOLD,20));
        Password.setBounds(70,91,212,30);
        passwordTextArea.setBounds(70, 121, 212, 20);
        passwordTextArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        okay.setBounds(70,152,60,30);
        okay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(nameTextArea.getText().equals("")|| nameTextArea.getText().contains(" ")){
                    BaseFrame nameError = new BaseFrame("错误",300, 200);
                    nameError.setResizable(false);
                    JLabel tipsName = new JLabel("请输入用户名");
                    JButton okay2 = new JButton("确认");
                    tipsName.setBounds(80, 49, 400, 50);
                    tipsName.setFont(new Font(null,Font.BOLD,20));
                    okay2.setBounds(100, 94, 70, 30);
                    okay2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            nameError.dispose();
                        }
                    });
                    nameError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    nameError.add(tipsName);
                    nameError.showMe(true);
                    nameError.add(okay2);
                }
                else if (passwordTextArea.getText().equals("")|| passwordTextArea.getText().contains(" ")){
                    BaseFrame passwordError = new BaseFrame("错误",300, 200);
                    passwordError.setResizable(false);
                    JLabel tipsName = new JLabel("请输入密码");
                    JButton okay2 = new JButton("确认");
                    tipsName.setBounds(90, 49, 400, 50);
                    tipsName.setFont(new Font(null,Font.BOLD,20));
                    okay2.setBounds(105, 94, 70, 30);
                    okay2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            passwordError.dispose();
                        }
                    });
                    passwordError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    passwordError.add(tipsName);
                    passwordError.add(okay2);
                    passwordError.showMe(true);
                }
                else{
                    if(mw.loginMainUser(nameTextArea.getText(),passwordTextArea.getText())){
                        login.dispose();
                        mainClass mc = new mainClass();
                        mc.createMainClass();
                    } else {
                        BaseFrame error = new BaseFrame("错误",350,200);
                        error.setResizable(false);
                        error.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        JLabel labError = new JLabel("输入账号或密码不匹配");
                        JButton btnSubmit = new JButton("确认");
                        labError.setBounds(15,50,350,30);
                        labError.setFont(new Font(null,Font.BOLD,30));
                        btnSubmit.setBounds(125,85,70,30);
                        btnSubmit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                error.dispose();
                            }
                        });
                        error.add(labError);
                        error.add(btnSubmit);
                        error.showMe(true);
                    }
                }
            }
        });

        forget.setBounds(131, 152, 90, 30);
        forget.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                BaseFrame forgetFrame = new BaseFrame("忘记密码", 350, 200);
                JLabel removePass = new JLabel("恢复码：");
                JTextArea removeArea = new JTextArea();
                JButton okay = new JButton("恢复");
                JButton removeAllinfo = new JButton("重置");
                JButton exit = new JButton("取消");

                removePass.setFont(new Font(null,Font.BOLD,20));
                removePass.setBounds(45, 30, 200, 50);

                removeArea.setBounds(45, 80, 250, 20);
                removeArea.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

                okay.setBounds(60,110,70,30);
                okay.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(mw.checkRecoverKey(removeArea.getText()))
                        {
                            mainClass mc = new mainClass();
                            mc.createMainClass();
                            forgetFrame.dispose();
                            login.dispose();
                        }
                    }
                });

                removeAllinfo.setBounds(132, 110, 70, 30);
                removeAllinfo.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        qr.resetAll();
                        forgetFrame.dispose();
                        System.exit(0);
                    }
                });

                exit.setBounds(204, 110, 70, 30);
                exit.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        forgetFrame.dispose();
                    }
                });

                forgetFrame.add(removePass);
                forgetFrame.add(removeArea);
                forgetFrame.add(okay);
                forgetFrame.add(removeAllinfo);
                forgetFrame.add(exit);
                forgetFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                forgetFrame.setResizable(false);
                forgetFrame.showMe(true);
            }
        });

        exit.setBounds(222, 152, 60, 30);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        login.add(Name);
        login.add(nameTextArea);
        login.add(passwordTextArea);
        login.add(Password);
        login.add(okay);
        login.add(forget);
        login.add(exit);
        login.showMe(true);
    }
}
