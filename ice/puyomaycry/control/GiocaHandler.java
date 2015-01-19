package ice.puyomaycry.control;

import java.awt.event.*;
import javax.swing.*;
import ice.puyomaycry.view.*;

public class GiocaHandler implements ActionListener {
    
    Tamagotchi tama;
    Gui gui;
    
    public GiocaHandler(Tamagotchi tama, Gui gui) {
        
        this.tama = tama;
        this.gui = gui;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        tama.getPadrone().giocaCon(tama.getPuyo());
        gui.getAllegria().setText("Allegria="+tama.getPuyo().getAllegria());
        gui.getPeso().setText("Peso="+tama.getPuyo().getPeso()+" Kg");
        gui.getEta().setText("Eta="+tama.getPuyo().getEta());
        gui.getImg().setIcon(Gui.FELICE);
        
        Timer gioca = new Timer(Gui.FELICEoTRISTE,
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    gui.getImg().setIcon(Gui.DEFAULT);
                }
            });
        gioca.setRepeats(false);
        gioca.start();
    }
}
