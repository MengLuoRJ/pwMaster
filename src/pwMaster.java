
import DataControl.*;
import UserCenter.*;

import java.util.ArrayList;

public class pwMaster {
    static String SF_NAME = "pwMaster";
    static String SF_VERSION = "1.0.0";

    public static void main(String[] args) {

        // SQL Init
        SQLDB sql = new SQLDB();

        // Query Init
        Query qr = new Query();

        // MainUser Init
        MainUser mUser = new MainUser();

        // Initialization Check
        qr.checkInitialization(SF_NAME,SF_VERSION);
        if(!GlobalValue.INITIALIZATION) {

            // Main User Service Init
            mUser.creatMainUserDataTable();
            String dateKey = mUser.generateRecoverCode();
            mUser.initMainUser("admin","admin123456",dateKey);

            // Software Global Init
            qr.initGlobal(SF_NAME, SF_VERSION);

        }




    }
}
