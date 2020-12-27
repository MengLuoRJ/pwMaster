package UI;
import DataControl.DCB;
import DataControl.Query;

import java.awt.Color;
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
        ArrayList<info> infos = new ArrayList<info>();
        public ArrayList<info> setInfoJPanels(int i, String pageName){
            ArrayList<DCB> data = qr.listAccount(pageName);
            for(int j = 0; j<i ; j++){
                this.infos.add(new info(data.get(j).getTitle(),data.get(j).getAccount(),data.get(j).getPassword(),data.get(j).getMark(),pageName));
        }
        return infos;
    }
}

