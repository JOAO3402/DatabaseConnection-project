package services;

import models.BdException;
import models.entities.Sell;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Set;

public class BdListAdd {

    public static void listAdd(Set<Sell> sells, Connection conn){
        try{
            for(Sell s: sells){
                AddToDatabase.addDb(conn, s);
            }

        } catch(InputMismatchException e){
            throw new BdException(e.getMessage());
        }
    }
}
