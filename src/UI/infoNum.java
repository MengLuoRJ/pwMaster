package UI;
import DataControl.DCB;
import DataControl.Query;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.event.*;

public class infoNum {
    Query qr = new Query();
    JPanel infoJPanel;
    ArrayList<info> infos = new ArrayList<>();
    public ArrayList<info> setInfoJPanels(String pageName){
        ArrayList<DCB> data = qr.listAccount(pageName);
        for (DCB datum : data) {
            this.infos.add(new info(datum.getTitle(), datum.getAccount(), datum.getPassword(), datum.getMark(), pageName));
        }

        return infos;
    }
    public ArrayList<info> queryInfo(ArrayList<DCB> data){
        for (DCB datum : data) {
            this.infos.add(new info(datum.getTitle(), datum.getAccount(), datum.getPassword(), datum.getMark(), datum.getTable()));
        }
        return infos;
    }
    public  void refresh(bodyClass bodyC,ArrayList<info> alInfo){
        bodyC.mainJPanel.removeAll();

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
                                infom.setBackground(Color.white);
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
    }
}

