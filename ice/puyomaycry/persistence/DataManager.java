package ice.puyomaycry.persistence;

import java.io.*;
import ice.puyomaycry.model.*;

/**
 * Classe che memorizza lo stato di Puyo e lo aggiorna.
 */
public class DataManager {
    
    private static File data = new File("data.tam");
    
    /** Metodo che consente di scrivere su file lo stato di Puyo
     *  (da usare ad ogni iterazione del fato). */
    public static void memorizza(Puyo puyo) {
        
        /* scrive lo stato di Puyo su file */
        try {
            data.createNewFile();
        } catch (IOException e) {
            System.out.println("File gia esistente.");
        }
        try {
            FileOutputStream scrittore = new FileOutputStream(data);
            scrittore.write(puyo.getStato());
            scrittore.close();
        } catch (IOException e) {
            System.out.println("Impossibile scrivere su file!");
        }
    }
    
    /** Metodo che consente di aggiornare lo stato di Puyo leggendo
     *  da file (da usare solo ad avvio applicazione). */
     public static byte[] aggiorna(Puyo puyo) {
         
         byte[] statoAggiornato = puyo.getStato();
         
         try {
             FileInputStream lettore = new FileInputStream(data);
             try {
                 lettore.read(statoAggiornato);
                 lettore.close();
             } catch (IOException e) {
                 System.out.print("Errore in lettura, utilizzo");
                 System.out.println(" impostazioni di default...");
             }
         } catch (IOException e) {
             System.out.print("Non trovo il file, utilizzo");
             System.out.println(" impostazioni di default...");
         }
         
         return statoAggiornato;
     }
}
