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
                    + "`password` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '密码',"
                    + "`recover` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '恢复码',"
                    + "PRIMARY KEY(`username`)"
                    + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        db.executeSQLUpdate(SQL);
    }

    public void initMainUser(String userName, String passWord, String dateKey) {
        Encryption ep = new Encryption();
        SQLDB db = new SQLDB();
        String SQL =
                "INSERT INTO `" + GlobalValue.DB_PREFIX + TABLE_NAME + "`"
                    + "( username, password, recover) VALUES"
                    + "( '" + userName + "', '" + ep.encodeSHA512Hex(passWord) + "', '" + dateKey + "');";
        db.executeSQLUpdate(SQL);
    }

    public boolean loginMainUser(String userName, String passWord) {
        Encryption ep = new Encryption();
        SQLDB db = new SQLDB();
        String target = "password";
        String SQL =
                "SELECT " + target
                + " FROM " + GlobalValue.DB_PREFIX + TABLE_NAME
                + " WHERE " + "username='" + userName + "';";
        String verify = db.executeSQLQuerySingleSQL(SQL,target);
        if(ep.encodeSHA512Hex(passWord).equals(verify)) {
            GlobalValue.USER_LOGGED = true;
            GlobalValue.USER_NAME = userName;
            return true;
        } else return false;
    }

    public String generateRecoverCode() {
        Encryption ep = new Encryption();
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dataKey = sdf.format(time);
        String recoverKey = ep.encodeSHA512Hex(dataKey);
        System.out.println(recoverKey);
        RecoverFile rf = new RecoverFile(recoverKey);
        try {
            rf.generateRecoverFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataKey;
    }

    public boolean checkRecoverKey(String userName, String recoverKey) {
        Encryption ep = new Encryption();
        SQLDB db = new SQLDB();
        String target = "recover";
        String SQL =
                "SELECT " + target
                        + " FROM " + GlobalValue.DB_PREFIX + TABLE_NAME
                        + " WHERE " + "username='" + userName + "';";
        String dateKey = db.executeSQLQuerySingleSQL(SQL,target);
        return ep.encodeSHA512Hex(dateKey).equals(recoverKey);
    }
}
