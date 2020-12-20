package DataControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBtest extends SQLDB {
    private Connection conn = null;
    private Statement stmt = null;
    public void executeSQLTest() {
        try {
            Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); // 建立数据库链接
            stmt = conn.createStatement(); // 实例化 Statement 对象
            System.out.println("Connected...");
            // 完成后关闭
            stmt.close();
            conn.close();
            System.out.println("Closed...");
        } catch (SQLException | ClassNotFoundException e) {
            // 处理 JDBC 及 Class.forName 错误
            e.printStackTrace();
            System.out.println("Error...");
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
}
