package UserCenter;

import DataControl.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainUser {
    static String TABLE_NAME = "mainUser";

    public void creatMainUserDataTable() {
        SQLDB db = new SQLDB();
        String SQL =
                "CREATE TABLE IF NOT EXISTS `" + GlobalValue.DB_PREFIX + TABLE_NAME + "`("
                    + "`username` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '账号',"
                    + "`password` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '密码',"
                    + "`recover` VARCHAR(160) NOT NULL DEFAULT '' COMMENT '恢复码',"
                    + "PRIMARY KEY(`username`)"
                    + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        db.executeSQLUpdate(SQL);
    }

    public void initMainUser(String userName, String passWord, String dataKey) {
        Encryption ep = new Encryption();
        SQLDB db = new SQLDB();
        String SQL =
                "INSERT INTO`" + GlobalValue.DB_PREFIX + TABLE_NAME
                    + "(`username`, `password`, `recover`)"
                + "VALUES"
                    + "(`" + userName + "`, `" + ep.codeDES(passWord) + "`, `" + dataKey + "`);";
        db.executeSQLUpdate(SQL);
    }

    public boolean loginMainUser(String userName, String passWord) {
        Encryption ep = new Encryption();
        SQLDB db = new SQLDB();
        String target = "password";
        String SQL =
                "SELECT " + target
                + "FROM" + TABLE_NAME
                + "WHERE" + "username='" + userName + "'";
        String verify = db.executeSQLQuerySingleSQL(SQL,target);
        if(passWord.equals(ep.decodeDES(verify))) {
            GlobalValue.USER_LOGGED = true;
            GlobalValue.USER_NAME = userName;
            return true;
        } else return false;
    }

    public String generateRecoverCode() throws IOException {
        Encryption ep = new Encryption();
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String DateKey = sdf.format(time);
        String Key = ep.encodeSHA512Hex(DateKey);

        RecoverFile rf = new RecoverFile(Key);
        rf.generateRecoverFile();

        return Key;
    }

    //public boolean checkRecoverKey(String Key) {

    //}
}
