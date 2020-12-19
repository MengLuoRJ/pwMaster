package UserCenter;

import DataControl.*;

public class MainUser {
    static String TABLE_NAME = "mainUser";

    private static String MAIN_USER_NAME;
    private static String MAIN_USER_PASSWORD;

    public void creatMainUserDataTable() {
        SQLDB db = new SQLDB();
        String SQL =
                "CREATE TABLE IF NOT EXISTS `" + GlobalValue.DB_PREFIX + TABLE_NAME + "`("
                        + "`account` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '账号',"
                        + "`password` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '密码',"
                        + "PRIMARY KEY(`account`)"
                        + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        db.executeSQLUpdate(SQL);
    }

}
