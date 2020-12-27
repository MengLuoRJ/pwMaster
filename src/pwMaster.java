
import DataControl.*;
import UserCenter.*;
import UI.*;

import java.util.ArrayList;

public class pwMaster {

    public static void main(String[] args) {

        // Software init info
        GlobalValue.SF_NAME = "pwMaster";
        GlobalValue.VERSION = "1.0.0";

        // SQL Init
        SQLDB sql = new SQLDB();
        sql.initOnlineDB("user","pass","db");

        // Query Init
        Query qr = new Query();

        // MainUser Init
        MainUser mUser = new MainUser();

        // Initialization Check
        qr.checkInitialization(GlobalValue.SF_NAME,GlobalValue.VERSION);

        if(!GlobalValue.INITIALIZATION) {

            mUser.creatMainUserDataTable();
            fristLogin fl = new fristLogin();

        } else {
            Login lg = new Login();
        }

    }
}
