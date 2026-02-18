package services;

import models.excpetions.BdException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EditDatabase {

    public static void editName(Connection conn) {

        Scanner sc = new Scanner(System.in);
        PreparedStatement st = null;

        try {
            System.out.print("Enter the ID of sell: ");
            Integer Id = Integer.parseInt(sc.nextLine());
            System.out.print("Enter the new name of the client: ");
            String newName = sc.nextLine();

            st = conn.prepareStatement(
                    "UPDATE vendas "
                            + "SET Client_Name = ? "
                            + "WHERE "
                            + "(Id = ?)");

            st.setString(1, newName);
            st.setInt(2, Id);

            int rowsAff = st.executeUpdate();

            if(rowsAff > 0){
                System.out.println("Done! Row updated.");
            } else{
                System.out.println("This Id doesn`t exist.");
            }

            System.out.println();

        }
        catch(SQLException e) {
            throw new BdException(e.getMessage() + "\n");
        }
        catch(InputMismatchException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        finally {
            BD.closeStatement(st);
        }
    }
}
