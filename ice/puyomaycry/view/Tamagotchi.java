package ice.puyomaycry.view;

import java.awt.event.*;
import javax.swing.*;
import ice.puyomaycry.model.*;
import ice.puyomaycry.persistence.*;

/**
 * Il Tamagotchi!!! Questo non � un pupazzo che ti rompe ogni
 * mezz'ora, bens� un pupazzo che ti rompe ogni frazione di secondo!
 * Devi accontentarlo interpretando le sue espressioni, altrimenti
 * gli farai male e se ne andr�. Se vuoi terminare il gioco
 * prematuramente, basta riempirlo di cibo. Scoppier� a breve.
 */
public class Tamagotchi {
    
    /** Un minuto. */
    final int UNMINUTO = 60000;    // chk: tempo in msec
    
    private Puyo puyo;
    private Padrone padrone;
    private Gui gui;
    
    /** Costruttore del gioco. */
    public Tamagotchi() {
        
        this.puyo = new Puyo();
        this.padrone = new Padrone();
        this.gui = new Gui(this);
    }
    
    /** Metodi get. */
    public Puyo getPuyo() { return this.puyo; }
    public Padrone getPadrone() {return this.padrone;}
    public Gui getGui() { return this.gui; }
    
    /** Metodi set. */
    public void setPuyo(Puyo puyo) { this.puyo = puyo; }
    public void setPadrone(Padrone padrone) { this.padrone = padrone; }
    public void setGui(Gui gui) { this.gui = gui; }
    
    /** Questo � il gioco vero e proprio. Il pupazzo non � neanche
     *  nato e gi� inizia a rompere. */
    public void gioca() {
        
        /* il fato aggiorna lo stato di Puyo ad ogni minuto */
        final Timer fato = new Timer(UNMINUTO, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                /* i comandi che seguono rappresentano
                 * l'ineluttabile fato: dopo un po' di tempo
                 * a Puyo viene fame, gli mancano le forze
                 * e si incattivisce, e se � grasso si ammala pure.
                 * Ma non � colpa sua. E' colpa mia... */
                
                /* ogni minuto aumenta l'esperienza di Puyo */
                puyo.setEsperienza(true);
                /* fa morire Puyo (sigh...) */
                if (puyo.getEsperienza()>50 ||
                    gui.getImg().getIcon()==Gui.MALATO) {
                    gui.getImg().setIcon(Gui.ADDIO);
                    gui.timerUscita();
                    puyo.resetta();
                    DataManager.memorizza(puyo);
                }
                /* lo stato di Puyo cambia in base
                 * allo stato precedente */
                if (gui.getImg().getIcon()==Gui.MALATO)
                    puyo.setEnergia(false);
                if (gui.getImg().getIcon()==Gui.FAME ||
                    gui.getImg().getIcon()==Gui.ANNOIATO ||
                    gui.getImg().getIcon()==Gui.CATTIVO ||
                    gui.getImg().getIcon()==Gui.GRASSO) {
                    puyo.ammala();
                    gui.getImg().setIcon(Gui.MALATO);
                }
                if (gui.getImg().getIcon()==Gui.DEFAULT) {
                    puyo.setCibo(false);
                    puyo.setAllegria(false);
                    puyo.setAffetto(false);
                }
                /* stabilisce l'et� di Puyo in base
                 * all'esperienza acquisita */
                if (puyo.getEsperienza()>40)
                    puyo.setEta("Anziano");
                else if (puyo.getEsperienza()>30)
                    puyo.setEta("Adulto");
                else if (puyo.getEsperienza()>20)
                    puyo.setEta("Giovane");
                else if (puyo.getEsperienza()>10)
                    puyo.setEta("Cucciolo");
                /* Puyo cambia espressione in base allo status */
                if (puyo.getCibo()==1)
                    gui.getImg().setIcon(Gui.FAME);
                else if (puyo.getAllegria()==1)
                    gui.getImg().setIcon(Gui.ANNOIATO);
                else if (puyo.getAffetto()==1)
                    gui.getImg().setIcon(Gui.CATTIVO);
                else if (puyo.getPeso()==9)
                    gui.getImg().setIcon(Gui.GRASSO);
                /* Puyo chiama se ha bisogno di aiuto */
                if (puyo.getCibo()==1 || puyo.getAllegria()==1 ||
                    puyo.getAffetto()==1 || puyo.getPeso()==9)
                    puyo.chiama();
                
                gui.aggiorna();
                DataManager.memorizza(puyo);
            }
        });
        fato.start();
    }
    
    /**
     * Metodo main per giocare. C'era da chiederlo?
     */
    public static void main(String[] args) {
        
        Tamagotchi PuyoMayCry = new Tamagotchi();
        
        PuyoMayCry.gioca();
    }
}