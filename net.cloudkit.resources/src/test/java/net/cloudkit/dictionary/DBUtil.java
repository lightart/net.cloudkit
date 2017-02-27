/*
 * Copyright (C) 2015 The CloudKit Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.cloudkit.dictionary;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * DBUtil.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2016年12月27日 上午11:38:34
 */
public class DBUtil {

    /**
     * ThreadLocal是线程的局部变量，它的实例通常是类中的 private static 字段
     */
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

    /**
     * 定义一个数据连接池
     */
    private static DataSource dataSource;

    private static String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
    private static String username = "root";
    private static String password = "root";
    private static String driverName = "com.mysql.jdbc.Driver";

    private static Connection connection = null;

    static {
        Properties props = new Properties();
        try {
            // 从文件中取内容
            // FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/jdbc.properties");
            // FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory() + "/jdbc.properties");
            // props.load(fis);

            // props.load(DBUtil.class.getClassLoader().getResourceAsStream("dbcp.properties"));
            // dataSource = BasicDataSourceFactory.createDataSource(props);

            // 获取信息
            /*
            driverName = props.getProperty("jdbc.driver");
            url = props.getProperty("jdbc.url");
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // Connection connection = connectionThreadLocal.get();
        if (connection == null) {
            try {
                Class.forName(driverName);
                if (username != null && password != null) {
                    connection = DriverManager.getConnection(url, username, password);
                } else {
                    connection = DriverManager.getConnection(url);
                }
            } catch (ClassNotFoundException e) {
                // logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            } catch (SQLException e) {
                // logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
            // connection = dataSource.getConnection();
            // connection.setAutoCommit(true);
            connectionThreadLocal.set(connection);
        }
        return connection;
    }

    /**
     * 开始事务
     */
    public static void startTransaction() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事务
     */
    public static void rollback() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                connection.rollback();
                connection.commit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事务
     */
    public static void commit() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                connection.commit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭连接
     */
    public static void close() {
        try {
            Connection conn = getConnection();
            // 清空线程局部变量的内容
            connectionThreadLocal.set(null);
            if (conn != null) {
                try {
                    conn.close();
                } finally {
                    connectionThreadLocal.remove();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 执行更新，插入，删除语句
     *
     * @param sql
     * @param args
     * @return
     */
    public static int execute(String sql, Object... args) {
        Connection connection = null;
        int rowCount = 0;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            rowCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        return rowCount;
    }

    /**
     * 查询方法
     *
     * @param sql
     * @param args
     * @return
     */
    public static List<Map<String, Object>> query(String sql, Object... args) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            // int col = rs.getMetaData().getColumnCount();
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> rowMap = new LinkedHashMap<String, Object>();
                int columnCount = meta.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = meta.getColumnName(i);
                    // rs.getObject(i);
                    rowMap.put(columnName, rs.getObject(i));
                    // System.out.println(rs.getObject(columnName));
                }
                results.add(rowMap);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        return results;
    }


}
