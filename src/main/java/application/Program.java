package application;

import models.BdException;
import models.entities.Sell;
import services.BD;
import services.BdListAdd;
import services.CreateSell;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Program {
    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(System.in);
            Locale.setDefault(Locale.US);

            Connection conn = BD.getConnection();

            Set<Sell> sellSet = new HashSet<>();

            boolean continuar = false;
            System.out.print("Deseja registrar uma venda? (s/n) ");
            char c = sc.nextLine().toUpperCase().charAt(0);
            if(c == 'S'){
                continuar = true;
            }

            do{
                CreateSell.addSell(sellSet);

                System.out.print("Você deseja registrar mais uma venda no banco de Dados? (s/n) ");
                c = sc.nextLine().toUpperCase().charAt(0);
                if(c == 'N'){
                    continuar = false;
                }

            } while(continuar);

            System.out.print("Deseja subir esta(s) venda(s) no Banco de Dados? (s/n) ");
            c = sc.nextLine().toUpperCase().charAt(0);

            if(c == 'S'){
                BdListAdd.listAdd(sellSet, conn);
            } else{
                System.out.println("Registros Abortados!");
            }

            System.out.println("Obrigado por usar o nosso banco de dados =)");

            BD.closeConnection(conn);

        } catch(RuntimeException e){
            throw new BdException(e.getMessage());
        }
    }
}
