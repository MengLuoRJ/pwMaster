
import DataControl.*;
import UserCenter.*;

public class pwMaster {

    public static void main(String[] args) {

        // SQL init
        SQLDB sql = new SQLDB();
        sql.initOnlineDB("pw_master","pwMaster2020","pwmaster.aiur.tech");

        // DB test
        DBtest dbt = new DBtest();
        dbt.executeSQLTest();

        // Main User service
         MainUser mUser = new MainUser();
        // mUser.creatMainUserDataTable();

        try{
            System.out.println(mUser.generateRecoverCode());
            System.out.println(mUser.generateRecoverCode());
            System.out.println(mUser.generateRecoverCode());
            System.out.println(mUser.generateRecoverCode());

        } catch (Exception ignored) {}

    }
}
