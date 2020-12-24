
import DataControl.*;
import UserCenter.*;

import java.io.IOException;

public class pwMaster {

    public static void main(String[] args) {

        // Encryption init
        Encryption ep = new Encryption();

        // SQL init
        SQLDB sql = new SQLDB();

        sql.createMapTable();

        //sql.creatAccountDataTable("1");
        //DCB data = new DCB("Baidu","www666","w6666","百度账号");
        //sql.insertData("1",data);
        //sql.dropDataTable("1");

        // Main User service
        MainUser mUser = new MainUser();
        mUser.creatMainUserDataTable();
        String dateKey = "";
        try {
            dateKey = mUser.generateRecoverCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mUser.initMainUser("admin","admin123456",dateKey);
        boolean S = mUser.loginMainUser("admin","admin123456");

    }
}
