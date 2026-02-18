package services;

import models.excpetions.BdException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class BD {

    public static Connection getConnection(){

        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url(), user(), password());

        } catch(SQLException e){
            throw new BdException(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Connection conn){
        if(conn != null){
            try{
                conn.close();
            } catch(SQLException e){
                throw new BdException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            } catch(SQLException e){
                throw new BdException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st){
        if(st != null){
            try{
                st.close();
            } catch(SQLException e){
                throw new BdException(e.getMessage());
            }

        }
    }

    private static String url(){
        try(BufferedReader br = new BufferedReader(new FileReader("src/db.properties"))){

            String line = br.readLine();
            while(line != null){

                String[] vector = line.split("=");
                line = br.readLine();
                if(vector[0].equals("dburl")){
                    return vector[1];
                }
            }
        } catch(IOException e){
            throw new BdException(e.getMessage());
        }
        return null;
    }

    private static String user(){
        try(BufferedReader br = new BufferedReader(new FileReader("src/db.properties"))){

            String line = br.readLine();
            while(line != null){

                String[] vector = line.split("=");
                line = br.readLine();
                if(vector[0].equals("user")){
                    return vector[1];
                }
            }
        } catch(IOException e){
            throw new BdException(e.getMessage());
        }
        return null;
    }

    private static String password(){
        try(BufferedReader br = new BufferedReader(new FileReader("src/db.properties"))){

            String line = br.readLine();
            while(line != null){

                String[] vector = line.split("=");
                line = br.readLine();
                if(vector[0].equals("password")){
                    return vector[1];
                }
            }
        } catch(IOException e){
            throw new BdException(e.getMessage());
        }
        return null;
    }
}
