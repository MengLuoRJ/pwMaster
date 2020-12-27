package UI;

import DataControl.DCB;
import DataControl.GlobalValue;
import DataControl.Query;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;

public class mainClass {

    Query qr = new Query();

    ArrayList<JButton> pageBtn = new  ArrayList<JButton>();
    ArrayList<info> infoList = new ArrayList<info>();
    ArrayList<JPanel> infoJPanels = new ArrayList<JPanel>();

    public ArrayList<info> delInfo(String title, ArrayList<info> infoArrayList,bodyClass bodypart){
        for(int i = 0; i<infoArrayList.size();i++){
            if(infoArrayList.get(i).getTitle().equals(title)){
                qr.deleteAccount(infoArrayList.get(i).pageName,title);
                for(int j = infoArrayList.size()-1; j>i;j--){
                    infoArrayList.get(j).setOther2(infoArrayList.get(j-1).getOther()); 
                }
                infoArrayList.remove(i);
                
            }
        }
        return infoArrayList;
    }
    public void createMainClass() {
        BaseFrame f = new BaseFrame(GlobalValue.SF_NAME+" "+GlobalValue.VERSION, 830, 830);
        f.setResizable(false);
        mainClass a = new mainClass();
        pageClass pageList = new pageClass();
        bodyClass bodyPart = new bodyClass();
        JPanel infoJPanel = new JPanel();
        titleList title = new titleList();
        topJPanel top = new topJPanel();
        JPanel infoPanel;
        f.setLayout(new BorderLayout(0,0));
        
        f.add(pageList.getPageJScrollPane(),BorderLayout.WEST);

        f.add(infoJPanel,BorderLayout.CENTER);
        infoJPanel.setLayout(new BorderLayout(0,0));
        infoJPanel.add(title.getTitle(),BorderLayout.NORTH);

        infoJPanel.add(bodyPart.getMainJScrollPane(),BorderLayout.CENTER);
        pageButton pageB = new pageButton();
        a.pageBtn = pageB.setPageButton(qr.listTable(),qr.listTable().size(), a.pageBtn, bodyPart, a.infoJPanels);

        for(int i = 0; i<a.pageBtn.size() ; i++){
            pageList.getPageJPanel().setPreferredSize(new Dimension(150, (i+1)*60));
            pageList.getPageJPanel().add(a.pageBtn.get(i));
        }

        infoNum InfoNum = new infoNum();
        if(a.pageBtn.size()!=0){
            a.infoList = InfoNum.setInfoJPanels(qr.listAccount(a.pageBtn.get(0).getText()).size(),a.pageBtn.get(0).getText());
            pageButton.nowPage = a.pageBtn.get(0).getText();
            for(int i = 0; i<a.infoList.size();i++){
                int o = i;
                a.infoList.get(i).getOther().addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.out.println( a.infoList.get(o).getTitle());
                        JPopupMenu otherPopupMenu = new JPopupMenu();
                        JMenuItem delete = new JMenuItem("删除");
                        JMenuItem change = new JMenuItem("修改");
                        otherPopupMenu.add(delete);
                        otherPopupMenu.add(change);
                        delete.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                JPanel infom;
                                a.infoList = a.delInfo(a.infoList.get(o).getTitle(),a.infoList,bodyPart);
                                bodyPart.mainJPanel.removeAll();
                                for(int i = 0;i<a.infoList.size();i++ ){
                                    infom = new JPanel();
                                    System.out.println(a.infoList.get(i).getTitle());
                                    infom.setBounds(15, i*30, 600, 30);
                                    bodyPart.getMainJPanel().add(infom);
                                    infom.setLayout(null);
                                    infom.add(a.infoList.get(i).getName());
                                    infom.add(a.infoList.get(i).getAccount());
                                    infom.add(a.infoList.get(i).getPassword());
                                    infom.add(a.infoList.get(i).getTips());
                                    infom.add(a.infoList.get(i).getOther());
                                    infom.setBackground(Color.white);
                                    infom.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                                    bodyPart.getMainJPanel().setPreferredSize(new Dimension(630, (i+1)*30));
                                    bodyPart.getMainJPanel().setBackground(Color.white);
                                    a.infoJPanels.add(infom);
                                }
                                bodyPart.mainJPanel.updateUI();
                            }
                        });
                        change.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                JButton okay = new JButton("确认");
                                okay.setBounds(520, 0, 80,30);
                                a.infoList.get(o).getPassword().setEditable(true);
                                a.infoList.get(o).getAccount().setEditable(true);
                                a.infoList.get(o).getTips().setEditable(true);
                                a.infoJPanels.get(o).remove(a.infoList.get(o).getOther());
                                a.infoJPanels.get(o).add(okay);
                                a.infoJPanels.get(o).updateUI();
                                okay.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent e){
                                        a.infoJPanels.get(o).remove(okay);
                                        a.infoJPanels.get(o).add(a.infoList.get(o).getOther());
                                        qr.updateAccount(new DCB(
                                                pageButton.nowPage,
                                                a.infoList.get(o).getTitle(),
                                                a.infoList.get(o).Account.getText(),
                                                a.infoList.get(o).Password.getText(),
                                                a.infoList.get(o).Tips.getText()
                                        ));
                                        a.infoList.get(o).getPassword().setEditable(false);
                                        a.infoList.get(o).getAccount().setEditable(false);
                                        a.infoList.get(o).getTips().setEditable(false);
                                        a.infoJPanels.get(o).updateUI();
                                    }
                                });

                            }

                        });
                        otherPopupMenu.setPopupSize(100, 40);
                        otherPopupMenu.show(a.infoList.get(o).getOther(), 0, 30);
                    }
                });
            }
            for(int i = 0; i<a.infoList.size();i++){
                infoPanel = new JPanel();
                infoPanel.setBounds(15, i*30, 600, 30);
                bodyPart.getMainJPanel().add(infoPanel);
                infoPanel.setLayout(null);
                infoPanel.add(a.infoList.get(i).getName());
                infoPanel.add(a.infoList.get(i).getAccount());
                infoPanel.add(a.infoList.get(i).getPassword());
                infoPanel.add(a.infoList.get(i).getTips());
                infoPanel.add(a.infoList.get(i).getOther());
                infoPanel.setBackground(Color.white);
                infoPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                bodyPart.getMainJPanel().setPreferredSize(new Dimension(630, (i+1)*30));
                a.infoJPanels.add(infoPanel);
            }

        }

        f.add(top.setTop(bodyPart,pageList,a.pageBtn, pageB),BorderLayout.NORTH);
        f.showMe(true);
    }
    
}
