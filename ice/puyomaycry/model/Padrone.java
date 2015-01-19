package ice.puyomaycry.model;

import ice.puyomaycry.model.Puyo;

/**
 * Anche se a volte pu� non sembrare, Il padrone sei tu! Questa classe
 * contiene tutte le azioni che puoi eseguire sul tuo cuccioletto
 * preferito. Il costruttore � implicito ma c'�, quindi non usare i metodi
 * come se fossero statici!
 *
 * @author IceMan
 * @version 1.0
 */
public class Padrone {

	/** Il nutrimento incrementa la scorta di cibo e il peso. */
    public void nutri(Puyo puyo) {
        
        puyo.setCibo(true);
        puyo.setPeso(true);
    }

    /** Le vitamine ripristinano un po' di energia,
     *  ma fanno ingrassare. */
    public void cura(Puyo puyo) {
        
        if (puyo.getEnergia()<90)
            puyo.setEnergia(true);
        puyo.setPeso(true);
    }

    /** Il gioco incrementa l'allegria di Puyo e lo fa dimagrire. */
    public void giocaCon(Puyo puyo) {
        
        if (puyo.getAllegria()<5)
            puyo.setAllegria(true);
        if (puyo.getPeso()>5)
            puyo.setPeso(false);
                
        
    }
    
    /** Le coccole incrementano l'affetto di Puyo. */
    public void coccola(Puyo puyo) {
        
        if (puyo.getAffetto()<5)
            puyo.setAffetto(true);
    }
}
