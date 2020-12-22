
import DataControl.*;
import UserCenter.*;

public class pwMaster {

    public static void main(String[] args) {

        // SQL init
        SQLDB sql = new SQLDB();
        sql.initOnlineDB("pw_master","pwMaster2020","mysql.aiur.tech");

        // DB test
        DBtest dbt = new DBtest();
        dbt.executeSQLTest();

        sql.creatAccountDataTable("1");
        DCB data = new DCB("Baidu","www666","w6666","百度账号");
        sql.insertData("1",data);
        //sql.dropDataTable("1");
        // Main User service
        MainUser mUser = new MainUser();
        mUser.creatMainUserDataTable();


    }
}
