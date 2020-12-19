package UserCenter;

import DataControl.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainUser {
    static String TABLE_NAME = "mainUser";

    public void creatMainUserDataTable() {
        SQLDB db = new SQLDB();
        String SQL =
                "CREATE TABLE IF NOT EXISTS `" + GlobalValue.DB_PREFIX + TABLE_NAME + "`("
                    + "`username` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '账号',"
                    + "`password` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '密码',"
                    + "PRIMARY KEY(`account`)"
                    + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        db.executeSQLUpdate(SQL);
    }

    public void initMainUser(String userName, String passWord) {
        Encryption ep = new Encryption();
        SQLDB db = new SQLDB();
        String SQL =
                "INSERT INTO`" + GlobalValue.DB_PREFIX + TABLE_NAME
                    + "(`username`, `password`)"
                + "VALUES"
                    + "(`" + userName + "`, `" + ep.codeDES(passWord) + "`)";
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
        String verify = db.executeSQLQuerySingleData(SQL,target);
        if(passWord.equals(ep.decodeDES(verify))) {
            GlobalValue.USER_LOGGED = true;
            GlobalValue.USER_NAME = userName;
            return true;
        } else return false;
    }






}
