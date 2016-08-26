import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Klasa CreateTopology sadrži samo konstruktor u kojem pozivamo java program run.jar: 
 * Program run.jar služi za generiranje .txt datoteke u kojoj se nalazi topologija.
 */
 
public class CreateTopology {
    private Process process;
    
    CreateTopology() {
        try {

            System.out.println("###### UNOS MRE�NE TOPOLOGIJE ######");
            System.out.println("Unesite mre�nu topologiju i spremite ju u projekt kao topologija.txt");

            process = Runtime.getRuntime().exec("java -jar run.jar"); //Pokretanje programa za generiranje datoteke.

            System.out.println("�ekanje unosa mre�ne toplogije ...");
            process.waitFor();
            System.out.println("Unos mre�ne toplogije zavr�en.\n");
        } 
        
        catch (InterruptedException | IOException e) {
            System.out.println("Dogodila se pogre�ka prilikom kreiranja topologije:" + e.getMessage());
        }
    }
}
