package services;

import models.excpetions.BdException;
import models.excpetions.BdIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteRow {

    public static void delete(Connection conn){

        Scanner sc = new Scanner(System.in);

        PreparedStatement st = null;
        System.out.print("Enter the Id of sell to be Deleted: ");
        Integer delId = Integer.parseInt(sc.nextLine());

        try{
            conn.setAutoCommit(false);

            st = conn.prepareStatement(
                    "DELETE FROM vendas "
                    + "WHERE "
                    + "Id = ?");

            st.setInt(1, delId);

            int rowsAff = st.executeUpdate();

            if(rowsAff > 0){
                System.out.println("Done! Rows Affected: " + rowsAff);
            } else{
                System.out.println("This Id doesn`t exist.");
            }

            conn.commit();

        }
        catch (SQLException e){
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
