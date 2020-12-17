
package DataControl;

import java.sql.*;
import java.util.ArrayList;

public class SQLDB extends GlobalValue{

    // 链接字及状态字
    private Connection conn = null;
    private Statement stmt = null;


    // 初始化本地数据库（默认本地：localhost -port 3306）
    public void initLocalDB(String user, String pw) {
        DB_MAIN_URL = "localhost:3306";
        DB_USER = user;
        DB_PASS = pw;
    }

    // 初始化本地数据库
    public void initLocalDB(String user, String pw, String url) {
        DB_MAIN_URL = url;
        DB_USER = user;
        DB_PASS = pw;
    }

    // 初始化远程数据库
    public void initOnlineDB(String user, String pw, String URL) {
        DB_MAIN_URL = URL;
        DB_USER = user;
        DB_PASS = pw;
    }

    // 执行数据库更新操作 SQL
    private void executeSQLUpdate(String SQL) {
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
    private ArrayList<DCB> executeSQLQuery(String SQL) {
        ArrayList<DCB> dataBack = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); // 建立数据库链接
            stmt = conn.createStatement(); // 实例化 Statement 对象
            // 执行传入 SQL 命令（仅 INSERT 命令）
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

    // 创建数据表方法
    public void creatDataTable(String TABLE_NAME) {
        String SQL =
            "DROP TABLE IF NOT EXISTS `" + TABLE_NAME + "`("
                + "`" + DB_PREFIX + "id` INT UNSIGNED AUTO_INCREMENT,"
                + "`" + DB_PREFIX + "title` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '账号用途',"
                + "`" + DB_PREFIX + "account` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '账号',"
                + "`" + DB_PREFIX + "password` VARCHAR(40) NOT NULL DEFAULT '' COMMENT '密码',"
                + "`" + DB_PREFIX + "mark` VARCHAR(120) DEFAULT '' COMMENT '备注',"
                + "PRIMARY KEY(`" + DB_PREFIX + "id`)"
                + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        executeSQLUpdate(SQL);
    }

    // 删除数据表方法
    public void dropDataTable(String TABLE_NAME) {
        String SQL =
            "DROP TABLE IF NOT EXISTS `" + TABLE_NAME +"`;";
        executeSQLUpdate(SQL);
    }

    // 插入数据方法
    public void insertData(String TABLE_NAME, DCB []data) {

    }
    public void deleteData(String TABLE_NAME, DCB []data) {

    }

}