package DataControl;

import java.security.Key;

public class GlobalValue {
    // MainUser 主体用户参数
    public static String USER_NAME;
    public static String USER_AVATAR;
    public static boolean USER_LOGGED = false;

    // MySQL 数据库操作域
    public static String DB_URL;
    public static String DB_MAIN_URL;
    public static String DB_NAME = "pwMaster";
    public static String DB_PREFIX = "pwm_";

    // MySQL 数据库账号密码
    static String DB_USER;
    static String DB_PASS;

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL_HEADER = "jdbc:mysql://";
    static final String URL_SETTING = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    // Encryption
    static Key ENCRYPTION_KEY;

    public void initGlobal(){
        DB_URL = URL_HEADER + DB_MAIN_URL + DB_NAME + URL_SETTING;
    }               
}
