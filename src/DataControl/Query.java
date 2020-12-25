package DataControl;

import java.util.ArrayList;

public class Query extends SQLDB{

    public void newTable(String TABLE_NAME) {
        newTableIntoMap(TABLE_NAME);
        creatAccountDataTable(getIDByMap(TABLE_NAME));
    }

    public void deleteTable(String TABLE_NAME) {
        dropDataTable(""+getIDByMap(TABLE_NAME));
        deleteTableFromMap(TABLE_NAME);
    }

    public boolean checkTitleUsed(String TABLE_NAME, String title) {
       return title.equals(queryTitle(getIDByMap(TABLE_NAME), title));
    }

    public void addAccount(DCB data) {
        insertData(getIDByMap(data.getTable()), data);
    }

    public void deleteAccount(String TABLE_NAME, String title) {
        deleteData(getIDByMap(TABLE_NAME), title);
    }

    public void updateAccount(DCB data) {
        updateData(getIDByMap(data.getTable()), data);
    }

    public ArrayList<DCB> queryAccount(String TABLE_NAME, String target) {
        return queryData(getIDByMap(TABLE_NAME), target);
    }

    public ArrayList<DCB> listAccount(String TABLE_NAME) {
        return queryWholeTable(getIDByMap(TABLE_NAME));
    }

    public ArrayList<TCB> listTable() {
        return queryWholeMap();
    }

    public void initGlobal(String sfName, String version) {
        createGlobalTable();
        newVersionIntoGlobal(sfName, version);
        createMapTable();
        checkInitialization(sfName, version);
    }

    public void resetAll() {
        ArrayList<TCB> tableTarget = listTable();
        for (TCB tcb : tableTarget) {
            dropDataTable(Integer.toString(tcb.getID()));
        }
        dropDataTable("map");
        dropDataTable("mainuser");
        dropDataTable("Admin");
        dropDataTable("Global");
        GlobalValue.INITIALIZATION = false;
        GlobalValue.USER_LOGGED = false;
    }

}
