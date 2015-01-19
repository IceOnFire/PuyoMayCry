package ice.puyomaycry.model;

import ice.puyomaycry.persistence.*;

/**
 * Puyo, il bastardo che dovrai accudire.
 * Il suo stato � caratterizzato da:
 * 0. la scorta di cibo (-1/5);
 * 1. la sua allegria (-1/5);
 * 2. il suo peso (5/11);
 * 3. la sua energia (0/100);
 * 4. l'affetto ricevuto (-1/5);
 * 5. la sua esperienza (0/50).
 * Inoltre Puyo ha una descrizione della sua et�,
 * basata sull'esperienza acquisita.
 *
 * @author IceMan
 * @version 25.12.2003
 */
public class Puyo {

    /** Un array contenente lo stato di Puyo. I valori sono, in ordine,
     *  quelli riportati nel commento della classe. */
    private byte[] stato;
    /** L'et� di Puyo. */
    private String eta;
    /** Il suono che emette Puyo per attirare l'attenzione. */
    final Richiamo richiamo = new Richiamo();
    
    /** L'oggetto Puyo � un essere virtuale che ha costante
     *  bisogno di cure. */
    public Puyo() {
    
        this.stato = new byte[] {0, 0, 5, 100, 0, 0};
        this.stato = DataManager.aggiorna(this);
        this.eta = "Neonato";
    }
    
    /** Metodi get. */
    public byte[] getStato() {return this.stato;}
    public byte getCibo() { return this.stato[0]; }
    public byte getAllegria() { return this.stato[1]; }
    public byte getPeso() { return this.stato[2]; }
    public byte getEnergia() { return this.stato[3]; }
    public byte getAffetto() { return this.stato[4]; }
    public byte getEsperienza() { return this.stato[5]; }
    public String getEta() { return this.eta; }
    
    /** Metodi set. */
    public void setStato(byte[] stato) { this.stato = stato; }
    public void setCibo(boolean more) {
        if (more) this.stato[0]++;
        else this.stato[0]--;
    }
    public void setAllegria(boolean more) {
        if (more) this.stato[1]++;
        else this.stato[1]--;
    }
    public void setPeso(boolean more) {
        if (more) this.stato[2]++;
        else this.stato[2]--;
    }
    public void setEnergia(boolean more) {
        if (more) this.stato[3]+=10;
        else this.stato[3]-=10;
    }
    public void setAffetto(boolean more) {
        if (more) this.stato[4]++;
        else this.stato[4]--;
    }
    public void setEsperienza(boolean more) {
        if (more) this.stato[5]++;
        else this.stato[5]--;
    }
    public void setEta(String eta) { this.eta = eta; }
    
    /** Metodi crolla. Vengono invocati quando Puyo si ammala. */
    private void crollaCibo() { this.stato[0] = 0; }
    private void crollaAllegria() { this.stato[1] = 0; }
    private void crollaEnergia() { this.stato[3] = 10; }
    private void crollaAffetto() { this.stato[4] = 0; }
    
    /** Metodo che fa ammalare Puyo. */
    public void ammala() {
        this.crollaCibo();
        this.crollaAllegria();
        this.crollaAffetto();
        this.crollaEnergia();
    }
    
    /** Metodo che resetta lo stato di Puyo
     *  (da usare solo alla sua morte) */
    public void resetta() {
        
        this.stato = new byte[] {0, 0, 5, 100, 0, 0};
    } 
    
    /** Metodo che fa gridare Puyo. */
    public void chiama() { richiamo.play(); }
}
