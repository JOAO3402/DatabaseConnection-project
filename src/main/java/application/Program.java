package application;

import models.entities.Sell;
import services.BD;
import services.BdListAdd;
import services.CreateSell;
import services.EditDatabase;
import java.sql.Connection;
import java.util.*;

public class Program {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        Connection conn = BD.getConnection();
        Set<Sell> sellSet = new HashSet<>();

        try {
            boolean escolha = true;
            int control;

            do {

                System.out.println("\n\n||* REGISTRO DE VENDAS *||");

                System.out.println("Escolha uma Opção:\n");

                System.out.println("1 - Adicionar uma Venda");
                System.out.println("2 - Subir a Venda no BD");
                System.out.println("3 - Editar o nome de um Cliente");
                System.out.println("4 - Sair");

                control = Integer.parseInt(sc.nextLine());

                switch (control) {
                    case 1:
                        CreateSell.addSell(sellSet);
                        break;

                    case 2:
                        BdListAdd.listAdd(sellSet, conn);
                        sellSet.clear();
                        System.out.println("O set está vazio? " + sellSet.isEmpty() + "\n\n");
                        break;

                    case 3:
                        EditDatabase.editName(conn);
                        break;

                    case 4:
                        escolha = false;
                        break;

                    default:
                        System.out.println("Opção Inválida!");
                        break;
                }

            } while (escolha);

            System.out.println("Obrigado por usar o Nosso programa =]");
        }
        catch(InputMismatchException e){
            System.out.println("Error: " + e.getMessage());
        }
        catch(RuntimeException e){
            System.out.println("An unexpected error has occur.");
        }
        finally{
            BD.closeConnection(conn);
        }
    }
}
