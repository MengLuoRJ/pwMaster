
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
        sql.initOnlineDB("XXX","XXX","XXX");

        // Query Init
        Query qr = new Query();

        // MainUser Init
        MainUser mUser = new MainUser();

        // Initialization Check
        qr.checkInitialization(GlobalValue.SF_NAME,GlobalValue.VERSION);

        if(!GlobalValue.INITIALIZATION) {

            mUser.creatMainUserDataTable();
            GlobalValue.DATA_KEY = mUser.generateRecoverCode();
            fristLogin fl = new fristLogin();

            // Software Global Init
            qr.initGlobal(GlobalValue.SF_NAME, GlobalValue.VERSION);

        } else {
            Login lg = new Login();
        }

    }
}
