package ice.puyomaycry.control;

import java.awt.event.*;
import javax.swing.*;
import ice.puyomaycry.view.*;

public class CoccolaHandler implements ActionListener {
    
    Tamagotchi tama;
    Gui gui;
    
    public CoccolaHandler(Tamagotchi tama, Gui gui) {
        
        this.tama = tama;
        this.gui = gui;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        tama.getPadrone().coccola(tama.getPuyo());
        gui.getAffetto().setText("Affetto="+tama.getPuyo().getAffetto());
        gui.getImg().setIcon(Gui.COCCOLA);
        
        Timer coccola = new Timer(Gui.COCCOLATA,
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    gui.getImg().setIcon(Gui.DEFAULT);
                }
            });
        coccola.setRepeats(false);
        coccola.start();
    }
}
