package UI;

import DataControl.Query;
import DataControl.TCB;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class delPages {

    Query qr = new Query();

    public delPages(bodyClass bodyC, pageClass pageC){
        BaseFrame f = new  BaseFrame("删除账号分页", 400, 200);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel title = new JLabel("请输入删除的分页名称：");
        JTextArea inputArea = new JTextArea();
        JButton okay = new JButton("确认");
        JButton exit = new JButton("取消");

        title.setBounds(50, 50, 300, 30);
        title.setFont(new Font(null,Font.BOLD,20));
        inputArea.setBounds(50, 90, 300, 20);
        inputArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        okay.setBounds(120, 120, 70, 30);
        okay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(inputArea.getText().equals("")){
                   new Error("请输入页面名");
                }
                else{
                    qr.deleteTable(inputArea.getText());
                    bodyC.mainJPanel.removeAll();
                    pageC.pageJPanel.removeAll();
                    ArrayList<TCB> dataTable = qr.listTable();
                    pageButton pageB = new pageButton();
                    ArrayList<JButton> alJB = new ArrayList<>();
                    ArrayList<JPanel> alJP = new ArrayList<>();
                    alJB = pageB.setPageButton(dataTable, dataTable.size(), alJB, bodyC, alJP);

                    for(int i=0; i<alJB.size() ; i++){
                        pageC.pageJPanel.add(alJB.get(i));
                        pageC.pageJPanel.setPreferredSize(new Dimension(150,60*(i+1)));
                    }
                    pageC.pageJPanel.updateUI();
                    bodyC.mainJPanel.updateUI();
                    f.dispose();
                }
            }

        });
        exit.setBounds(205, 120, 70, 30);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
            }

        });

        f.add(title);
        f.add(inputArea);
        f.add(okay);
        f.add(exit);
        f.showMe(true);
    }
    
}
