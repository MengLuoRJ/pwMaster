import DataControl.*;
import UserCenter.*;
import UI.*;
public class pwMaster {
    public static void main(String[] args) {
        // Software init info
        GlobalValue.SF_NAME = "pwMaster";
        GlobalValue.VERSION = "1.0.0";
        // SQL Init
        SQLDB sql = new SQLDB();
        sql.initOnlineDB("username","password","database");
        // Query Init
        Query qr = new Query();
        // MainUser Init
        MainUser mUser = new MainUser();
        // Initialization Check
        qr.checkInitialization(GlobalValue.SF_NAME,GlobalValue.VERSION);
        if(!GlobalValue.INITIALIZATION) {
            // NOT INITIALIZED - create new
            mUser.creatMainUserDataTable();
            // Launch INITIALIZATION Panel
            new fristLogin();
        } else {
            // INITIALIZED - Launch Login Panel
            new Login();
        }
    }
}
