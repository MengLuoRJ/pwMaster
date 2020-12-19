
package DataControl;

import java.sql.*;
import java.util.ArrayList;

public class SQLDB{

    // 链接字及状态字
    private Connection conn = null;
    private Statement stmt = null;


    // 初始化本地数据库（默认本地：localhost -port 3306）
    public void initLocalDB(String user, String pw) {
        GlobalValue.DB_MAIN_URL = "localhost:3306";
        GlobalValue.DB_USER = user;
        GlobalValue.DB_PASS = pw;
    }

    // 初始化本地数据库
    public void initLocalDB(String user, String pw, String url) {
        GlobalValue.DB_MAIN_URL = url;
        GlobalValue.DB_USER = user;
        GlobalValue.DB_PASS = pw;
    }

    // 初始化远程数据库
    public void initOnlineDB(String user, String pw, String URL) {
        GlobalValue.DB_MAIN_URL = URL;
        GlobalValue.DB_USER = user;
        GlobalValue.DB_PASS = pw;
    }

    // 执行数据库更新操作 SQL
    public void executeSQLUpdate(String SQL) {
        try {
            Class.forName(GlobalValue.JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(GlobalValue.DB_URL, GlobalValue.DB_USER, GlobalValue.DB_PASS); // 建立数据库链接
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

    // 执行数据库数据插入操作 SQL
    public ArrayList<DCB> executeSQLQuery(String SQL) {
        ArrayList<DCB> dataBack = new ArrayList<>();
        try {
            Class.forName(GlobalValue.JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(GlobalValue.DB_URL, GlobalValue.DB_USER, GlobalValue.DB_PASS); // 建立数据库链接
            stmt = conn.createStatement(); // 实例化 Statement 对象
            // 执行传入 SQL 命令（仅 SELECT 命令）
            ResultSet rs = stmt.executeQuery(SQL);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                dataBack.add(new DCB(
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

    // 创建 账号数据 数据表方法
    public void creatAccountDataTable(String TABLE_NAME) {
        String SQL =
            "CREATE TABLE IF NOT EXISTS `" + GlobalValue.DB_PREFIX + TABLE_NAME + "`("
                + "`id` INT UNSIGNED AUTO_INCREMENT,"
                + "`title` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '账号用途',"
                + "`account` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '账号',"
                + "`password` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '密码',"
                + "`mark` VARCHAR(120) DEFAULT '' COMMENT '备注',"
                + "PRIMARY KEY(`account`)"
                + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        executeSQLUpdate(SQL);
    }

    // 删除数据表方法
    public void dropDataTable(String TABLE_NAME) {
        String SQL =
            "DROP TABLE IF NOT EXISTS `" + GlobalValue.DB_PREFIX + TABLE_NAME +"`;";
        executeSQLUpdate(SQL);
    }

    // 插入数据方法
    public void insertData(String TABLE_NAME, DCB []data) {

    }
    public void deleteData(String TABLE_NAME, DCB []data) {

    }

}