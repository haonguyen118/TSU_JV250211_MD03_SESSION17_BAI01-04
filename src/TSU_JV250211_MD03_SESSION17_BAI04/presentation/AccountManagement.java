package TSU_JV250211_MD03_SESSION17_BAI04.presentation;

import business.dao.Database;

import java.sql.*;

public class AccountManagement {

    public void transfer (int id_from, int id_to, double amount) {
        Connection connection = null;
        CallableStatement call = null;
        try {
            connection = Database.getConnection();
            if(connection != null) {
                connection.setAutoCommit(false);
                call = connection.prepareCall("{call transfer_funds (?, ?, ?, ?)}");
                call.setInt(1, id_from);
                call.setInt(2, id_to);
                call.setDouble(3, amount);
                call.registerOutParameter(4, Types.VARCHAR);
                call.executeUpdate();
                connection.commit();
                String rs = call.getString(4);
                System.out.println(rs);
            }

        }catch (Exception e){
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            System.out.println(e.getMessage());
        }finally {
            try{
                if(call != null)call.close();
                if(connection != null)connection.close();

            }catch (SQLException e){
                throw new RuntimeException(e);
            }

        }
    }
}
