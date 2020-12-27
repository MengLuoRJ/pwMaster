package UI;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.*;
import javax.swing.event.*;

import DataControl.DCB;
import DataControl.Query;
import DataControl.TCB;

import java.awt.*;
import java.awt.event.*;

public class pageButton {

    Query qr = new Query();

    static String nowPage;


    public ArrayList<info> delInfo(String title, ArrayList<info> infoArrayList,bodyClass bodypart){
        for(int i = 0; i<infoArrayList.size();i++){
            if(infoArrayList.get(i).getTitle().equals(title)){
                for(int j = infoArrayList.size()-1; j>i;j--){
                    infoArrayList.get(j).setOther2(infoArrayList.get(j-1).getOther());
                }
                infoArrayList.remove(i);

            }
        }
        return infoArrayList;
    }

    public ArrayList<JButton> setPageButton(ArrayList<TCB> data, int pageSize, ArrayList<JButton> pageBtn, bodyClass bc, ArrayList<JPanel> alJP){
        for(int i = 0; i<pageSize ;i++){
            int t = i;
            pageBtn.add(new JButton(data.get(i).getTABLE_NAME()));
            pageBtn.get(i).setBounds(1, i * 60, 150, 60);
            pageBtn.get(i).addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    nowPage = pageBtn.get(t).getText();
                    ArrayList<DCB> dcb = qr.listAccount(data.get(t).getTABLE_NAME());
                    ArrayList<info> alInfo = new ArrayList<>();
                    for(int j=0; j<dcb.size(); j++) {
                        info inf = new info(dcb.get(j).getTitle(),dcb.get(j).getAccount(),dcb.get(j).getPassword(),dcb.get(j).getMark(),data.get(t).getTABLE_NAME());
                        alInfo.add(inf);
                    }
                    bc.mainJPanel.removeAll();
                    for(int j=0; j<alInfo.size(); j++) {
                        JPanel jp = new JPanel();
                        jp.setLayout(null);
                        jp.add(alInfo.get(j).getName());
                        jp.add(alInfo.get(j).getAccount());
                        jp.add(alInfo.get(j).getPassword());
                        jp.add(alInfo.get(j).getTips());
                        jp.add(alInfo.get(j).getOther());
                        jp.setBackground(Color.white);
                        int o = j;
                        alInfo.get(j).getOther().addActionListener(new ActionListener() {
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
                                        bc.mainJPanel.removeAll();
                                        for (int i = 0; i < alInfo.size(); i++) {
                                            infom = new JPanel();
                                            System.out.println(alInfo.get(i).getTitle());
                                            infom.setBounds(15, i * 30, 570, 30);
                                            bc.getMainJPanel().add(infom);
                                            infom.setLayout(null);
                                            infom.add(alInfo.get(i).getName());
                                            infom.add(alInfo.get(i).getAccount());
                                            infom.add(alInfo.get(i).getPassword());
                                            infom.add(alInfo.get(i).getTips());
                                            infom.add(alInfo.get(i).getOther());
                                            infom.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                                            bc.getMainJPanel().setPreferredSize(new Dimension(630, (i + 1) * 30));
                                            bc.getMainJPanel().setBackground(Color.white);
                                        }
                                        bc.mainJPanel.updateUI();
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
                                                System.out.println(alInfo.get(o).Tips.getText());
                                                qr.updateAccount(new DCB(
                                                        pageButton.nowPage,
                                                        alInfo.get(o).Name.getText(),
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

                            }
                        });
                        bc.mainJPanel.add(jp);
                        jp.setBounds(15, o * 30, 600, 30);
                        jp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

                    }
                    bc.mainJPanel.updateUI();
                    System.out.println( pageBtn.get(t).getText());
                }

            });
        }
        return pageBtn;
    }
}
