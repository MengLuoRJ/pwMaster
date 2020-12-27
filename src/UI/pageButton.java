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

    public ArrayList<JButton> setPageButton(ArrayList<TCB> data, int pageSize, ArrayList<JButton> pageBtn, bodyClass bc, ArrayList<JPanel> alJP){
        for(int i = 0; i<pageSize ;i++){
            int t = i;
            pageBtn.add(new JButton(data.get(i).getTABLE_NAME()));
            pageBtn.get(i).setBounds(1, i * 60, 150, 60);
            pageBtn.get(i).addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    pageButton.nowPage = pageBtn.get(t).getText();
                    infoNum IFN = new infoNum();
                    IFN.refresh(bc,IFN.setInfoJPanels(pageButton.nowPage));

                }

            });
        }
        return pageBtn;
    }
}
