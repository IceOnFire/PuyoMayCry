package ice.puyomaycry.control;

import java.awt.event.*;
import javax.swing.*;
import ice.puyomaycry.view.*;

public class CuraHandler implements ActionListener {
    
    Tamagotchi tama;
    Gui gui;
    
    public CuraHandler(Tamagotchi tama, Gui gui) {
        
        this.tama = tama;
        this.gui = gui;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if (tama.getPuyo().getEnergia()<90 &&
            tama.getPuyo().getPeso()<10) {
            tama.getPadrone().cura(tama.getPuyo());
            gui.getPeso().setText("Peso="+tama.getPuyo().getPeso()+" Kg");
            gui.getEnergia().setText("Energia="+tama.getPuyo().getEnergia()+"%");
            gui.getImg().setIcon(Gui.CURA);
            
            Timer nutri = new Timer(Gui.NUTRIoCURA, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    gui.getImg().setIcon(Gui.DEFAULT);
                }
            });
            nutri.setRepeats(false);
            nutri.start();
        } else {
            final ImageIcon prev = (ImageIcon) gui.getImg().getIcon();
            gui.getImg().setIcon(Gui.TRISTE);
            
            Timer no = new Timer(Gui.FELICEoTRISTE, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    gui.getImg().setIcon(prev);
                }
            });
            no.setRepeats(false);
            no.start();
        }
    }
}
