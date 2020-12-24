
package DataControl;

import java.sql.*;
import java.util.ArrayList;

public class SQLDB{

    // MySQL 数据库操作域
    public static String DB_URL;
    public static String DB_MAIN_URL;
    public static String DB_NAME = "pwmaster";
    public static String DB_PREFIX = GlobalValue.DB_PREFIX;

    // MySQL 数据库账号密码
    static String DB_USER;
    static String DB_PASS;

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL_HEADER = "jdbc:mysql://";
    static final String URL_SETTING = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    // 链接字及状态字
    private Connection conn = null;
    private Statement stmt = null;

    private static void initURL(){
        DB_URL = URL_HEADER + DB_MAIN_URL + "/" +DB_NAME + URL_SETTING;
    }

    // 初始化本地数据库（默认本地：localhost -port 3306）
    public void initLocalDB(String user, String pw) {
        DB_MAIN_URL = "localhost:3306";
        DB_USER = user;
        DB_PASS = pw;
        initURL();
    }

    // 初始化本地数据库
    public void initLocalDB(String user, String pw, String url) {
        DB_MAIN_URL = url;
        DB_USER = user;
        DB_PASS = pw;
        initURL();
    }

    // 初始化远程数据库
    public void initOnlineDB(String user, String pw, String URL) {
        DB_MAIN_URL = URL;
        DB_USER = user;
        DB_PASS = pw;
        initURL();
    }

    // 执行数据库更新操作 SQL
    public void executeSQLUpdate(String SQL) {
        try {
            Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); // 建立数据库链接
            stmt = conn.createStatement(); // 实例化 Statement 对象
            // 执行传入 SQL 命令
            stmt.executeUpdate(SQL);
            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            // 处理 JDBC 及 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 结束处理：关闭资源（状态字、链接字）
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    // 通用：删除数据表方法
    protected void dropDataTable(String TARGET) {
        String SQL =
                "DROP TABLE IF EXISTS `" + DB_PREFIX + TARGET +"`;";
        executeSQLUpdate(SQL);
    }

    // 创建 表单映射表 方法
    protected void createMapTable() {
        String SQL =
                "CREATE TABLE IF NOT EXISTS `" + DB_PREFIX + "Map` ("
                        + "`id` INT UNSIGNED AUTO_INCREMENT,"
                        + "`tableName` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '对应表单名',"
                        + "PRIMARY KEY(`id`)"
                        + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        executeSQLUpdate(SQL);
    }

    // 新建表 插入映射表 方法
    protected void newTableIntoMap(String TABLE_NAME) {
        String SQL =
                "INSERT INTO `" + DB_PREFIX + "Map` "
                        + "( tableName ) VALUES"
                        + "('" + TABLE_NAME + "');";
        executeSQLUpdate(SQL);
    }

    // 执行 表单 id 读取操作 SQL
    protected int getIDByMap(String TABLE_NAME) {
        int dataBack = -1;
        String SQL = "SELECT id FROM " + DB_PREFIX + "Map"
                +" WHERE tableName='"+ TABLE_NAME +"';";
        try {
            Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); // 建立数据库链接
            stmt = conn.createStatement(); // 实例化 Statement 对象
            // 执行传入 SQL 命令（仅 SELECT 命令）
            ResultSet rs = stmt.executeQuery(SQL);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                dataBack = rs.getInt("id");
            }
            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            // 处理 JDBC 及 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 结束处理：关闭资源（状态字、链接字）
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return dataBack;
    }

    // 执行 表单 name 读取操作 SQL
    protected String getNameByMap(int ID) {
        String dataBack = null;
        String SQL = "SELECT tableName FROM " + DB_PREFIX + "Map"
                +" WHERE id='"+ ID +"';";
        try {
            Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); // 建立数据库链接
            stmt = conn.createStatement(); // 实例化 Statement 对象
            // 执行传入 SQL 命令（仅 SELECT 命令）
            ResultSet rs = stmt.executeQuery(SQL);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                dataBack = rs.getString("tableName");
            }
            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            // 处理 JDBC 及 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 结束处理：关闭资源（状态字、链接字）
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return dataBack;
    }

    // 查询 所有表单 SQL
    protected ArrayList<TCB> queryWholeMap() {
        ArrayList<TCB> dataBack = new ArrayList<>();
        String SQL = "SELECT * FROM " + DB_PREFIX + "Map;";
        try {
            Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); // 建立数据库链接
            stmt = conn.createStatement(); // 实例化 Statement 对象
            // 执行传入 SQL 命令（仅 SELECT 命令）
            ResultSet rs = stmt.executeQuery(SQL);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                dataBack.add(new TCB(
                        rs.getInt("id"),
                        rs.getString("tableName")
                ));
            }
            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            // 处理 JDBC 及 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 结束处理：关闭资源（状态字、链接字）
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return dataBack;
    }

    // 创建 账号数据 数据表方法
    protected void creatAccountDataTable(int COUNT) {
        String SQL =
                "CREATE TABLE IF NOT EXISTS `" + DB_PREFIX + COUNT + "`("
                        + "`id` INT UNSIGNED AUTO_INCREMENT,"
                        + "`title` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '账号用途',"
                        + "`account` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '账号',"
                        + "`password` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '密码',"
                        + "`mark` VARCHAR(120) DEFAULT '' COMMENT '备注',"
                        + "PRIMARY KEY(`id`)"
                        + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        executeSQLUpdate(SQL);
    }

    // 插入 账号数据 方法
    protected void insertData(int COUNT, DCB data) {
        String SQL =
                "INSERT INTO `" + DB_PREFIX + COUNT + "`"
                    + "( title, account, password, mark) VALUES"
                    + "('" + data.getTitle() + "','" + data.getAccount() + "','"
                    + data.getPassword() + "','" + data.getMark() + "');";
        executeSQLUpdate(SQL);
    }

    // 删除 账号数据 方法
    protected void deleteData(int COUNT, String title) {
        String SQL =
                "DELETE FROM `" + DB_PREFIX + COUNT + "`"
                + " WHERE title='" + title + "';";
        executeSQLUpdate(SQL);
    }

    // 更新 账号数据 方法
    protected void updateData(int COUNT, DCB data) {
        String SQL =
                "UPDATE `" + DB_PREFIX + COUNT + "` SET "
                        + "account='" + data.getAccount() + "',"
                        + "password'" + data.getPassword() + "',"
                        + "mark='" + data.getMark() + "'"
                        + " WHERE title='" + data.getTitle() + "';";
        executeSQLUpdate(SQL);
    }

    // 查询 账号数据 方法
    protected ArrayList<DCB> queryData(int COUNT, String target) {
        String SQL =
                "SELECT title, account, password, mark"
                    + " FROM `" + DB_PREFIX + COUNT + "`"
                    + " WHERE CONCAT(title, mark) LIKE '%" + target + "%';";
        return executeSQLQuery(COUNT, SQL);
    }

    // 查询 所有数据账号 方法
    protected ArrayList<DCB> queryWholeTable(int COUNT) {
        String SQL =
                "SELECT title, account, password, mark"
                        + " FORM `" + DB_PREFIX + COUNT + "`;";
        return executeSQLQuery(COUNT, SQL);
    }

    // 执行 账号数据 读取操作 SQL
    protected ArrayList<DCB> executeSQLQuery(int COUNT, String SQL) {
        ArrayList<DCB> dataBack = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); // 建立数据库链接
            stmt = conn.createStatement(); // 实例化 Statement 对象
            // 执行传入 SQL 命令（仅 SELECT 命令）
            ResultSet rs = stmt.executeQuery(SQL);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                dataBack.add(new DCB(
                                getNameByMap(COUNT),
                                rs.getString("title"),
                                rs.getString("account"),
                                rs.getString("password"),
                                rs.getString("mark")
                        )
                );
            }
            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            // 处理 JDBC 及 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 结束处理：关闭资源（状态字、链接字）
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return dataBack;
    }

    // 查询 目标 title 方法
    protected String queryTitle(int COUNT, String title) {
        String SQL = "SELECT title FROM " + DB_PREFIX + COUNT
                + " WHERE title='" + title + "';";
        return executeSQLQuerySingleSQL(SQL,"title");
    }

    // 执行数据库数据读取操作 SQL 单数据专用
    public String executeSQLQuerySingleSQL(String SQL, String targetLabel) {
        String dataBack = null;
        try {
            Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); // 建立数据库链接
            stmt = conn.createStatement(); // 实例化 Statement 对象
            // 执行传入 SQL 命令（仅 SELECT 命令）
            ResultSet rs = stmt.executeQuery(SQL);
            // 展开结果集数据库
            while(rs.next())
            {
                dataBack = rs.getString(targetLabel);
            }
            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            // 处理 JDBC 及 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 结束处理：关闭资源（状态字、链接字）
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return dataBack;
    }

    // 创建 软件全局表 方法
    public void createGlobalTable() {
        String SQL =
                "CREATE TABLE IF NOT EXISTS `" + DB_PREFIX + "Global` ("
                        + "`name` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '软件名',"
                        + "`version` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '版本号',"
                        + "`initialization` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '初始化状态',"
                        + "PRIMARY KEY(`name`)"
                        + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        executeSQLUpdate(SQL);
    }

    // 新建表 插入映射表 方法
    protected void newVersionIntoGlobal(String sfName, String version) {
        String SQL =
                "INSERT INTO `" + DB_PREFIX + "Global` "
                        + "( name, version, initialization) VALUES"
                        + "('" + sfName + "', '" + version + "', '1');";
        executeSQLUpdate(SQL);
    }

    // 查询 初始化状态 方法
    public void checkInitialization(String sfName, String version) {
        boolean dataBack = false;
        String SQL = "SELECT initialization FROM " + DB_PREFIX + "Global"
                +" WHERE name='"+ sfName +"' AND version='" + version + "';";
        try {
            Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); // 建立数据库链接
            stmt = conn.createStatement(); // 实例化 Statement 对象
            // 执行传入 SQL 命令（仅 SELECT 命令）
            ResultSet rs = stmt.executeQuery(SQL);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                dataBack = rs.getInt("initialization") == 1;
            }
            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            // 处理 JDBC 及 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 结束处理：关闭资源（状态字、链接字）
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        GlobalValue.INITIALIZATION  = dataBack;
    }


}