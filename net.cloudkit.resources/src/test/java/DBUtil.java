//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Set;
//
//import javax.sql.DataSource;
//
//public class DBUtil {
//
//    /**
//     * ThreadLocal是线程的局部变量，它的实例通常是类中的 private static 字段
//     */
//    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();
//
//    /**
//     * 定义一个数据连接池
//     */
//    private static DataSource dataSource;
//
//    private static String url = "jdbc:mysql://127.0.0.1:3306/wbdata?useUnicode=true&characterEncoding=UTF-8";
//    private static String username = "root";
//    private static String password = "root";
//    private static String driverName = "com.mysql.jdbc.Driver";
//
//    static {
//        Properties props = new Properties();
//        try {
//            // 从文件中取内容
//            // FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/jdbc.properties");
//            // properties.load(fis);
//
//            // props.load(DBUtil.class.getClassLoader().getResourceAsStream("dbcp.properties"));
//            // dataSource = BasicDataSourceFactory.createDataSource(props);
//
//            // 获取信息
//            /*
//            driverName = props.getProperty("jdbc.driver");
//            url = props.getProperty("jdbc.url");
//            username = props.getProperty("jdbc.username");
//            password = props.getProperty("jdbc.password");
//            */
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Connection
//     *
//     * @return
//     * @throws SQLException
//     */
//    public static Connection getConnection() throws SQLException {
//        Connection connection = connectionThreadLocal.get();
//        if (connection == null) {
//            try {
//                Class.forName(driverName);
//                if (username != null && password != null) {
//                    connection = DriverManager.getConnection(url, username, password);
//                } else {
//                    connection = DriverManager.getConnection(url);
//                }
//            } catch (ClassNotFoundException e) {
//                // logger.error(e.getMessage(), e);
//                throw new RuntimeException();
//            } catch (SQLException e) {
//                // logger.error(e.getMessage(), e);
//                throw new RuntimeException();
//            }
//            // connection = dataSource.getConnection();
//            // connection.setAutoCommit(true);
//            connectionThreadLocal.set(connection);
//        }
//        return connection;
//    }
//
//    public static void startTransaction() {
//        try {
//            Connection connection = getConnection();
//            if (connection != null) {
//                connection.setAutoCommit(false);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    }
//
//    public static void rollback() {
//        try {
//            Connection connection = getConnection();
//            if (connection != null) {
//                connection.rollback();
//                connection.commit();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    }
//
//    /**
//     * 提交事务
//     */
//    public static void commit() {
//        try {
//            Connection connection = getConnection();
//            if (connection != null) {
//                connection.commit();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    }
//
//    /**
//     * 关闭连接
//     */
//    public static void close() {
//        try {
//            Connection conn = getConnection();
//            // 清空线程局部变量的内容
//            connectionThreadLocal.set(null);
//            if (conn != null) {
//                try {
//                    conn.close();
//                } finally {
//                    connectionThreadLocal.remove();
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    }
//
//    /**
//     * 执行更新，插入，删除语句
//     */
//    private static int execute(String sql, Object... args) {
//        Connection connection = null;
//        int rowCount = 0;
//        PreparedStatement pstmt = null;
//        try {
//            connection = getConnection();
//            pstmt = connection.prepareStatement(sql);
//            for(int i = 0; i < args.length; i++){
//                pstmt.setObject(1, args[i]);
//            }
//            rowCount = pstmt.executeUpdate();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            if(pstmt != null) {
//                try {
//                    pstmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return rowCount;
//    }
//
//
//    private static List<Map<String, Object>> query(String sql, Object... args) {
//        List<Map<String, Object>> results = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement pstmt = null;
//        try {
//            connection = getConnection();
//            pstmt = connection.prepareStatement(sql);
//            ResultSet rs = pstmt.executeQuery();
//            // int col = rs.getMetaData().getColumnCount();
//            ResultSetMetaData meta = rs.getMetaData();
//            while (rs.next()) {
//                int columnCount = meta.getColumnCount();
//                for (int i = 1; i <= columnCount; i++) {
//                    String columnName = meta.getColumnName(i);
//                    rs.getObject(i);
//                    System.out.println(rs.getObject(columnName));
//                }
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }finally {
//            if(pstmt != null) {
//                try {
//                    pstmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        DBUtil.query("SELECT * FROM mould_drive");
//    }
//}
