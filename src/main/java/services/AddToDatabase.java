package services;

import models.excpetions.BdException;
import models.entities.Sell;

import java.sql.*;

public class AddToDatabase {

    public static void addDb(Connection conn, Sell sell){

        PreparedStatement st = null;

        try{
            conn.setAutoCommit(false);

            st = conn.prepareStatement(
                    "INSERT INTO vendas "
                    + "(Product_Name, Quantity, Client_Name, Subtotal)"
                    + "VALUES "
                    + "(?, ?, ?, ?)",
                    + Statement.RETURN_GENERATED_KEYS);

            st.setString(1, sell.getProduct().getProductName());
            st.setInt(2, sell.getQuantity());
            st.setString(3, sell.getClient().getClientName());
            st.setDouble(4, sell.subtotal());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id + "\n");
                }
                BD.closeResultSet(rs);
            }

            conn.commit();

        } catch(SQLException e){
            try{
                conn.rollback();
                throw new BdException("Transaction rolled back! Caused by: " + e.getMessage());
            }
            catch (SQLException e1){
                throw new BdException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        }
        finally{
            BD.closeStatement(st);
        }
    }
}
