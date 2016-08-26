import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * U klasi ReadTopology učitavamo generiranu topologiju 
 */
public class ReadTopology {
    private int nnode = 0;
    private File fileName;
    private FileReader inputFile;
    private BufferedReader bufferReader;
    private String line;
    
   /**
    * U konstruktoru učitavamo generiranu topologiju, zatim učitane podatke spremamo u
    * varijable objekta Edge, te računamo ukupan broj čvorova.
    * @param edges - polje objekata klase Edge
    * @param path - apsolutni put do datoteke u kojoj se nalazi topologija koju želimo učitati
    */
    ReadTopology(ArrayList<Edge> edges, String path) {
        
        System.out.println("###### U�ITAVANJE KREIRANE MRE�NE TOPOLOGIJE ######");
        System.out.println("U�itavanje kreirane topologije");

        fileName = new File(path);
        try {
            //Kreiranje objekta klase FileReader
            inputFile = new FileReader(fileName);

            //Kreiranje objekta klase BufferReader
            bufferReader = new BufferedReader(inputFile);

            //Citanje datoteke topologija.txt linija po linija
            while ((line = bufferReader.readLine()) != null) {
                String[] var = line.split(", ");
                edges.add(new Edge(Integer.parseInt(var[0]), Integer.parseInt(var[1]), Integer.parseInt(var[2])));
               
                //ODREDIVANJE BROJA CVOROVA
                if (nnode < Integer.parseInt(var[0])) {
                    nnode = Integer.parseInt(var[0]);
                }
                if (nnode < Integer.parseInt(var[1])) {
                    nnode = Integer.parseInt(var[1]);
                }
            }
            //zatavaranje bufferReader
            bufferReader.close();
            System.out.println("Topologija je u�itana!\n");
        } catch (IOException e) {
            System.out.println("Dogodila se pogre�ka prilikom �itanja datoteke:" + e.getMessage());
        }

        //Zato jer svaka topologija pocinje sa 0, pa moramo dodati 1.
        nnode = nnode + 1; 
    }
    
    /**
     * Metoda koja vraća broj čvorova učitane topologije.
     * @return nnode - integer koji sadrži broj čvorova iz topologije
     */
    public int getTopology(){
        return nnode;
    }
}
