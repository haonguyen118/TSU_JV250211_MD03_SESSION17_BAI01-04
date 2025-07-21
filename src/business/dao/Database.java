package business.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/session17?createDatabaseIfNotExist=true&useUSL=false";
    private static final String user = "root";
    private static final String password = "Sanghao8488@";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            System.out.println("kết nối đến cơ sở dữ liệu thất bại.");
            e.printStackTrace();
            return  null;
        }
    }
    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        }catch (Exception e){
            System.out.println("Đóng cơ sở dữ liệu thất bại.");
        }
    }
}