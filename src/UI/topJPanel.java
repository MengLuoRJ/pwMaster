package UI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class topJPanel {
    ArrayList<JButton> pageBtn = new  ArrayList<JButton>();
    ArrayList<info> infoList = new ArrayList<info>();
    public void setInfoList(ArrayList<info> infoList) {
        this.infoList = infoList;
    }
    public ArrayList<info> getInfoList() {
        return infoList;
    }
    public void setPageBtn(ArrayList<JButton> pageBtn) {
        this.pageBtn = pageBtn;
    }
    public ArrayList<JButton> getPageBtn() {
        return pageBtn;
    }
    public JPanel setTop(bodyClass bodyPart, pageClass pageC, ArrayList<JButton> alBtn, pageButton pageB) {
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(830, 30));

        top.setLayout(null);

        /*顶部容器小组件*/
        JMenuBar menuBar1 = new JMenuBar();
        JMenu menuJMenu1 = new JMenu("        编辑");
        menuJMenu1.setPreferredSize(new DimensionUIResource(80, 30));
        menuBar1.add(menuJMenu1);
        JMenu menuJMenu3 = new JMenu("       帮助");
        menuJMenu3.setPreferredSize(new DimensionUIResource(80, 30));
        menuBar1.add(menuJMenu3);

        menuBar1.setBounds(5, 0, 166, 30);
        top.add(menuBar1);


        JTextField check = new JTextField("请输入您要搜索的内容",390);
        check.setBounds(175, 0, 480, 30);
        top.add(check);
        JButton Btn1 = new JButton("搜素");
        Btn1.setBounds(655, 0, 80, 30);

        

        /*按钮一监听事件*/
        Btn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });
        /************************************************/

        top.add(Btn1);

        JButton Btn2 = new JButton("重置");
        Btn2.setBounds(735, 0, 80, 30);

        /*按钮二监听事件*/
        Btn2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });
        /************************************************/

        top.add(Btn2);

        /**菜单具体内容**/
        /*菜单F1*/
        JMenuItem addPage = new JMenuItem("增加分页");
        addPage.setPreferredSize(new DimensionUIResource(150, 20));
        addPage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                addPages addPagesFrame = new addPages(bodyPart, pageC, alBtn);
            }
        });
        menuJMenu1.add(addPage);

        JMenuItem deletePage = new JMenuItem("删除分页");
        deletePage.setPreferredSize(new DimensionUIResource(150, 20));
        deletePage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                delPages delPageFrame = new delPages(bodyPart,pageC);
            }
        });
        menuJMenu1.add(deletePage);

        menuJMenu1.addSeparator();

        JMenuItem addInfo = new JMenuItem("添加账号密码条目");
        addInfo.setPreferredSize(new DimensionUIResource(150, 20));
        addInfo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                addInfom addFrame = new addInfom(pageB, bodyPart);
            }
                
        });
        menuJMenu1.add(addInfo);


        menuJMenu1.addSeparator();

        JMenuItem Exit = new JMenuItem("退出");
        Exit.setPreferredSize(new DimensionUIResource(150, 20));
        Exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        menuJMenu1.add(Exit);


        /*菜单F3*/
        JMenuItem aboutusItem = new JMenuItem("关于");
        aboutusItem.setPreferredSize(new DimensionUIResource(100, 20));
        aboutusItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String url = "https://github.com/MengLuoRJ/pwMaster";
                java.net.URI uri = java.net.URI.create(url);
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                try {
                    dp.browse(uri);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        menuJMenu3.add(aboutusItem);

        return top;
    }
    
}
