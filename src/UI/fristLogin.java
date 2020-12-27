package UI;

import DataControl.GlobalValue;
import DataControl.Query;
import DataControl.StringMatcher;
import UserCenter.MainUser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class fristLogin {

    Query qr = new Query();

    StringMatcher sm = new StringMatcher();
    MainUser mu = new MainUser();

    public fristLogin() {
        BaseFrame frist = new  BaseFrame("初始化程式",300, 300);
        frist.setResizable(false);


        JLabel name = new JLabel("主体账号：");
        JLabel PassWord = new JLabel("主体密码：");
        JLabel againPassword = new JLabel("重复密码：");

        JTextArea nameArea = new JTextArea();
        JTextArea passwordJArea = new JTextArea();
        JTextArea againJArea = new JTextArea();

        JButton okay1 = new JButton("确认");
        JButton exit = new JButton("退出");

        okay1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(nameArea.getText().equals("")){
                    BaseFrame nameError = new BaseFrame("错误",350, 200);
                    nameError.setResizable(false);
                    JLabel tipsName = new JLabel("请设置用户名");
                    JButton okay2 = new JButton("确认");
                    tipsName.setBounds(100, 49, 400, 50);
                    tipsName.setFont(new Font(null,Font.BOLD,20));
                    okay2.setBounds(125, 94, 70, 30);
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
                else if(sm.AccountMatchCheck(nameArea.getText())){                                  //用户名非法字符检测
                    BaseFrame nameError = new BaseFrame("错误",350, 200);
                    nameError.setResizable(false);
                    JLabel tipsName = new JLabel("用户名含非法字符");
                    JButton okay2 = new JButton("确认");
                    tipsName.setBounds(100, 49, 400, 50);
                    tipsName.setFont(new Font(null,Font.BOLD,20));
                    okay2.setBounds(125, 94, 70, 30);
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
                else if (passwordJArea.getText().equals("")){
                    BaseFrame passwordError = new BaseFrame("错误",350, 200);
                    passwordError.setResizable(false);
                    JLabel tipsName = new JLabel("请设置密码");
                    JButton okay2 = new JButton("确认");
                    tipsName.setBounds(105, 49, 400, 50);
                    tipsName.setFont(new Font(null,Font.BOLD,20));
                    okay2.setBounds(125, 94, 70, 30);
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
                else if(sm.PasswordMatchCheck(passwordJArea.getText())){//密码非法字符检测
                    BaseFrame passwordError = new BaseFrame("错误",350, 200);
                    passwordError.setResizable(false);
                    JLabel tipsName = new JLabel("密码含非法字符");
                    JButton okay2 = new JButton("确认");
                    tipsName.setBounds(100, 49, 400, 50);
                    tipsName.setFont(new Font(null,Font.BOLD,20));
                    okay2.setBounds(125, 94, 70, 30);
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
                else if(!passwordJArea.getText().equals(againJArea.getText())){
                    BaseFrame againError = new BaseFrame("错误",350, 200);
                    againError.setResizable(false);
                    JLabel tipsName = new JLabel("两次密码不一致");
                    JButton okay2 = new JButton("确认");
                    tipsName.setBounds(100, 49, 200, 50);
                    tipsName.setFont(new Font(null,Font.BOLD,20));
                    okay2.setBounds(125, 94, 70, 30);
                    okay2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            againError.dispose();
                        }
                    });
                    againError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    againError.add(tipsName);
                    againError.add(okay2);
                    againError.showMe(true);
                }
                else if(passwordJArea.getText().equals(againJArea.getText())){

                    // Software Global Init
                    GlobalValue.DATA_KEY = mu.generateRecoverCode();
                    mu.initMainUser(nameArea.getText(), passwordJArea.getText(), GlobalValue.DATA_KEY);
                    qr.initGlobal(GlobalValue.SF_NAME, GlobalValue.VERSION);

                    Login lg = new Login();
                    frist.dispose();
                }
            }
        });
        okay1.setBounds(65,212,70,30);

        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        exit.setBounds(145,212,70,30);

        name.setBounds(40,50,200,30);
        name.setFont(new Font(null,Font.BOLD,20));
        nameArea.setBounds(40, 80, 210, 20);
        nameArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        PassWord.setBounds(40, 101, 200, 30);
        PassWord.setFont(new Font(null,Font.BOLD,20));
        passwordJArea.setBounds(40, 131, 210, 20);
        passwordJArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        againPassword.setBounds(40, 152, 200, 30);
        againPassword.setFont(new Font(null,Font.BOLD,20));
        againJArea.setBounds(40, 182, 210, 20);
        againJArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        frist.add(name);
        frist.add(nameArea);
        frist.add(PassWord);
        frist.add(passwordJArea);
        frist.add(againPassword);
        frist.add(againJArea);
        frist.add(okay1);
        frist.add(exit);

        frist.showMe(true);
    }
    
}
