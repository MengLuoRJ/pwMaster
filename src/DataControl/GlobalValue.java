package DataControl;

import javax.swing.*;

public class GlobalValue {

    // Software Info
    public static String SF_NAME;
    public static String VERSION;

    // pwMaster 全局状态参数
    public static boolean INITIALIZATION = false;

    // MainUser 主体用户参数
    public static String USER_NAME;
    public static boolean USER_LOGGED = false;
    public static String DATA_KEY;

    // MySQL 数据库参数
    public static String DB_PREFIX = "pwm_";

}
