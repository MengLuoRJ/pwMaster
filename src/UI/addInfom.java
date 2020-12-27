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
                            BaseFrame nameError = new BaseFrame("错误", 500, 200);
                            nameError.setResizable(false);
                            JLabel tipsName = new JLabel("请输入账号用途");
                            JButton okay2 = new JButton("确认");
                            tipsName.setBounds(178, 49, 400, 50);
                            tipsName.setFont(new Font(null, Font.BOLD, 20));
                            okay2.setBounds(215, 94, 50, 30);
                            okay2.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    nameError.dispose();
                                }
                            });
                            nameError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            nameError.add(tipsName);
                            nameError.showMe(true);
                            nameError.add(okay2);
                        } else if (acconArea.getText().equals("")) {
                            BaseFrame acconError = new BaseFrame("错误", 500, 200);
                            acconError.setResizable(false);
                            JLabel tipsAccon = new JLabel("请输入账号");
                            JButton okay2 = new JButton("ok");
                            tipsAccon.setBounds(178, 49, 400, 50);
                            tipsAccon.setFont(new Font(null, Font.BOLD, 20));
                            okay2.setBounds(215, 94, 50, 30);
                            okay2.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    acconError.dispose();
                                }
                            });
                            acconError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            acconError.add(tipsAccon);
                            acconError.showMe(true);
                            acconError.add(okay2);
                        } else if (passwordArea.getText().equals("")) {
                            BaseFrame passError = new BaseFrame("错误", 500, 200);
                            passError.setResizable(false);
                            JLabel tipsPass = new JLabel("请输入密码");
                            JButton okay2 = new JButton("ok");
                            tipsPass.setBounds(178, 49, 400, 50);
                            tipsPass.setFont(new Font(null, Font.BOLD, 20));
                            okay2.setBounds(215, 94, 50, 30);
                            okay2.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    passError.dispose();
                                }
                            });
                            passError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            passError.add(tipsPass);
                            passError.showMe(true);
                            passError.add(okay2);
                        } else {
                            qr.addAccount(new DCB(
                                    pageButton.nowPage,
                                    nameArea.getText(),
                                    acconArea.getText(),
                                    passwordArea.getText(),
                                    tipsArea.getText()
                            ));
                            bodyC.mainJPanel.removeAll();
                            ArrayList<DCB> dcb = qr.listAccount(pageButton.nowPage);
                            ArrayList<info> alInfo = new infoNum().setInfoJPanels(dcb.size(), pageButton.nowPage);
                            for (int i = 0; i < alInfo.size(); i++) {
                                JPanel jp = new JPanel();
                                jp.setLayout(null);
                                jp.add(alInfo.get(i).getName());
                                jp.add(alInfo.get(i).getAccount());
                                jp.add(alInfo.get(i).getPassword());
                                jp.add(alInfo.get(i).getTips());
                                jp.add(alInfo.get(i).getOther());
                                jp.setBackground(Color.white);

                                int o = i;
                                alInfo.get(o).getOther().addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JPopupMenu otherPopupMenu = new JPopupMenu();
                                        JMenuItem delete = new JMenuItem("删除");
                                        JMenuItem change = new JMenuItem("修改");
                                        otherPopupMenu.add(delete);
                                        otherPopupMenu.add(change);
                                        otherPopupMenu.setPopupSize(100, 40);
                                        otherPopupMenu.show(alInfo.get(o).getOther(), 0, 30);
                                        delete.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                JPanel infom;
                                                for (int i = 0; i < alInfo.size(); i++) {
                                                    if (alInfo.get(i).getTitle().equals(alInfo.get(o).getTitle())) {
                                                        qr.deleteAccount(alInfo.get(i).pageName, alInfo.get(i).getTitle());
                                                        for (int j = alInfo.size() - 1; j > i; j--) {
                                                            alInfo.get(j).setOther2(alInfo.get(j - 1).getOther());
                                                        }
                                                        alInfo.remove(i);
                                                    }
                                                }
                                                bodyC.mainJPanel.removeAll();
                                                for (int i = 0; i < alInfo.size(); i++) {
                                                    infom = new JPanel();
                                                    infom.setBounds(15, i * 30, 600, 30);
                                                    bodyC.getMainJPanel().add(infom);
                                                    infom.setLayout(null);
                                                    infom.add(alInfo.get(i).getName());
                                                    infom.add(alInfo.get(i).getAccount());
                                                    infom.add(alInfo.get(i).getPassword());
                                                    infom.add(alInfo.get(i).getTips());
                                                    infom.add(alInfo.get(i).getOther());
                                                    infom.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                                                    bodyC.getMainJPanel().setPreferredSize(new Dimension(630, (i + 1) * 30));
                                                }
                                                bodyC.mainJPanel.updateUI();
                                            }
                                        });
                                        change.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                JButton okay = new JButton("确认");
                                                okay.setBounds(520, 0, 80, 30);
                                                alInfo.get(o).getPassword().setEditable(true);
                                                alInfo.get(o).getAccount().setEditable(true);
                                                alInfo.get(o).getTips().setEditable(true);
                                                jp.remove(alInfo.get(o).getOther());

                                                jp.add(okay);
                                                jp.updateUI();
                                                okay.addActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent e) {
                                                        jp.remove(okay);
                                                        jp.add(alInfo.get(o).getOther());
                                                        qr.updateAccount(new DCB(
                                                                pageButton.nowPage,
                                                                alInfo.get(o).getTitle(),
                                                                alInfo.get(o).Account.getText(),
                                                                alInfo.get(o).Password.getText(),
                                                                alInfo.get(o).Tips.getText()
                                                        ));
                                                        alInfo.get(o).getPassword().setEditable(false);
                                                        alInfo.get(o).getAccount().setEditable(false);
                                                        alInfo.get(o).getTips().setEditable(false);
                                                        jp.updateUI();
                                                    }
                                                });

                                            }
                                        });
                                        jp.setBounds(15, 30 * o, 600, 30);
                                        jp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                                    }
                                });
                                bodyC.mainJPanel.add(jp);
                                jp.setBounds(15, o * 30, 600, 30);
                                jp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                            }
                            bodyC.mainJPanel.updateUI();
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
