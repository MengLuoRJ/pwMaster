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

    ArrayList<JButton> pageBtn = new ArrayList<>();
    ArrayList<info> infoList = new ArrayList<>();
    ArrayList<JPanel> infoJPanels = new ArrayList<>();
    public void createMainClass() {
        BaseFrame f = new BaseFrame(GlobalValue.SF_NAME+" "+GlobalValue.VERSION, 830, 830);
        f.setResizable(false);
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
        pageBtn = pageB.setPageButton(qr.listTable(),qr.listTable().size(), pageBtn, bodyPart, infoJPanels);

        for(int i = 0; i<pageBtn.size() ; i++){
            pageList.getPageJPanel().setPreferredSize(new Dimension(150, (i+1)*60));
            pageList.getPageJPanel().add(pageBtn.get(i));
        }
        pageButton.nowPage = pageBtn.get(0).getText();
        infoNum InfoNum = new infoNum();
        if(pageBtn.size()!=0){
            InfoNum.refresh(bodyPart,InfoNum.setInfoJPanels(pageButton.nowPage));
        }
        f.add(top.setTop(bodyPart,pageList,pageBtn, pageB),BorderLayout.NORTH);
        f.showMe(true);
    }
    
}
