package ice.puyomaycry.control;

import java.awt.event.*;
import javax.swing.*;
import ice.puyomaycry.view.*;

public class NutriHandler implements ActionListener {
    
    Tamagotchi tama;
    Gui gui;
    
    public NutriHandler(Tamagotchi tama, Gui gui) {
        
        this.tama = tama;
        this.gui = gui;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if (tama.getPuyo().getCibo()<5 && tama.getPuyo().getPeso()<10) {
            tama.getPadrone().nutri(tama.getPuyo());
            gui.getCibo().setText("Cibo="+tama.getPuyo().getCibo());
            gui.getPeso().setText("Peso="+tama.getPuyo().getPeso()+" Kg");
            gui.getImg().setIcon(Gui.NUTRI);
        
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
