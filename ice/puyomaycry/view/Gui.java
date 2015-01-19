package ice.puyomaycry.view;

import ice.puyomaycry.view.Tamagotchi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ice.puyomaycry.control.*;

/**
 * La GUI di Puyo.
 *
 * @author IceMan
 * @version 1.0
 */
public class Gui {
    
    /** Tempo impiegato da Puyo per nascere. */
    public final static int NASCITA = 7800;    // chk: tempo in msec
    /** Tempo impiegato da Puyo per andarsene. */
    public final static int USCITA = 10400;    //chk: tempo in msec
    /** Tempo impiegato da Puyo per mangiare o ingoiare medicine. */
    public final static int NUTRIoCURA = 5200;    // chk: tempo in msec
    /** Tempo impiegato da Puyo per esultare. */
    public final static int FELICEoTRISTE = 1200;    // chk: tempo in msec
    /** Tempo impiegato da Puyo per essere coccolato. */
    public final static int COCCOLATA = 2400;    // chk: tempo in msec
    
    /** Gif animate di Puyo. */
    public final static ImageIcon END = new ImageIcon("Gifs/Puyo-End.gif");
    public final static ImageIcon ADDIO = new ImageIcon("Gifs/Puyo-Addio.gif");
    public final static ImageIcon CATTIVO = new ImageIcon("Gifs/Puyo-Cattivo.gif");
    public final static ImageIcon MALATO = new ImageIcon("Gifs/Puyo-Malato.gif");
    public final static ImageIcon COCCOLA = new ImageIcon("Gifs/Puyo-Coccola.gif");
    public final static ImageIcon CURA = new ImageIcon("Gifs/Puyo-Cura.gif");
    public final static ImageIcon ANNOIATO = new ImageIcon("Gifs/Puyo-Annoiato.gif");
    public final static ImageIcon DEFAULT = new ImageIcon("Gifs/Puyo-Default.gif");
    public final static ImageIcon FAME = new ImageIcon("Gifs/Puyo-Fame.gif");
    public final static ImageIcon FELICE = new ImageIcon("Gifs/Puyo-Felice.gif");
    public final static ImageIcon GRASSO = new ImageIcon("Gifs/Puyo-Grasso.gif");
    public final static ImageIcon NASCE = new ImageIcon("Gifs/Puyo-Nasce.gif");
    public final static ImageIcon NUTRI = new ImageIcon("Gifs/Puyo-Nutri.gif");
    public final static ImageIcon TRISTE = new ImageIcon("Gifs/Puyo-Triste.gif");
    
	/** Etichetta con l'immagine di Puyo. */
    private JLabel img;
    /** Etichette di testo con lo status di Puyo. */
    private JLabel cibo, allegria, affetto, peso, energia, eta;
    /** I 4 pulsanti di azione. */
    private JButton nutri, cura, gioca, coccola;
    /** Pannelli con l'immagine di Puyo, lo status e i pulsanti. */
    private JPanel pannImg, status, pulsanti;
    /** Il frame contenente il gioco. */
    private JFrame frame;
    
    private Tamagotchi tama;
    
    /** L'interfaccia grafica del gioco. */
	public Gui(Tamagotchi tama) {
        
        this.tama = tama;
        
        /* creazione oggetti */
        img = new JLabel(NASCE);
        pannImg = new JPanel();
        status = new JPanel();
        pulsanti = new JPanel();
        frame = new JFrame("Puyo May Cry");
        
        /* aggiunge l'immagine della nascita di Puyo al suo pannello */
        pannImg.add(img);
        
        /* imposta le etichette che descrivono lo status di Puyo */
        setCibo(new JLabel("Cibo="+tama.getPuyo().getCibo()));
        setAllegria(new JLabel("Allegria="+tama.getPuyo().getAllegria()));
        setAffetto(new JLabel("Affetto="+tama.getPuyo().getAffetto()));
        setPeso(new JLabel("Peso="+tama.getPuyo().getPeso()+" Kg"));
        setEnergia(new JLabel("Energia="+tama.getPuyo().getEnergia()+"%"));
        setEta(new JLabel("Eta="+tama.getPuyo().getEta()));
        
        /* aggiunge le etichette al loro pannello */
        status.add(cibo);
        status.add(allegria);
        status.add(affetto);
        status.add(peso);
        status.add(energia);
        status.add(eta);
        status.setLayout(new BoxLayout(status, BoxLayout.Y_AXIS));
        
        /* crea i pulsanti e associa loro i corrispettivi eventi */
		nutri = new JButton("Nutri");
		nutri.addActionListener(new NutriHandler(tama, this));
		cura = new JButton("Cura");
		cura.addActionListener(new CuraHandler(tama, this));
		gioca = new JButton("Gioca");
		gioca.addActionListener(new GiocaHandler(tama, this));
		coccola = new JButton("Coccola");
		coccola.addActionListener(new CoccolaHandler(tama, this));
        
        /* aggiunge i pulsanti al loro pannello e li dispone a griglia */
        pulsanti.add(nutri);
        pulsanti.add(cura);
        pulsanti.add(gioca);
        pulsanti.add(coccola);
        pulsanti.setLayout(new GridLayout(2, 2));
        
        /* imposta la chiusura e mette i pannelli nel frame */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(pannImg, BorderLayout.WEST);
        frame.getContentPane().add(pulsanti, BorderLayout.SOUTH);
        frame.getContentPane().add(status, BorderLayout.EAST);
		frame.setResizable(false);
		frame.setSize(new Dimension(200, 200));
		frame.pack();
		frame.setVisible(true);
        
        /* fa partire l'immagine di default, sperando che
         * non ci metta troppo a caricare... */
        timerNascita();
	}
    
    /** Metodi get. */
    public JLabel getImg() { return this.img; }
    public JLabel getCibo() { return this.cibo; }
    public JLabel getAllegria() {return this.allegria; }
    public JLabel getAffetto() { return this.affetto; }
    public JLabel getPeso() { return this.peso; }
    public JLabel getEnergia() { return this.energia; }
    public JLabel getEta() { return this.eta; }
    public JButton getNutri() { return this.nutri; }
    public JButton getCura() { return this.cura; }
    public JButton getGioca() { return this.gioca; }
    public JButton getCoccola() { return this.coccola; }
    public JPanel getPannImg() { return this.pannImg; }
    public JPanel getStatus() { return this.status; }
    public JPanel getPulsanti() { return this.pulsanti; }
    public JFrame getFrame() { return this.frame; }
    
    /** Metodi set. */
    public void setImg(JLabel img) { this.img = img; }
    public void setCibo(JLabel cibo) { this.cibo = cibo; }
    public void setAllegria(JLabel allegria) { this.allegria = allegria; }
    public void setAffetto(JLabel affetto) { this.affetto = affetto; }
    public void setPeso(JLabel peso) { this.peso = peso; }
    public void setEnergia(JLabel energia) { this.energia = energia; }
    public void setEta(JLabel eta) { this.eta = eta; }
    public void setNutri(JButton nutri) { this.nutri = nutri; }
    public void setCura(JButton cura) { this.cura = cura; }
    public void setGioca(JButton gioca) { this.gioca = gioca; }
    public void setCoccola(JButton coccola) { this.coccola = coccola; }
    public void setPannImg(JPanel pannImg) { this.pannImg = pannImg; }
    public void setStatus(JPanel status) { this.status = status; }
    public void setPulsanti(JPanel pulsanti) { this.pulsanti = pulsanti; }
    public void setFrame(JFrame frame) { this.frame = frame; }
    
    /** Metodo che aggiorna le etichette dello status
     *  di Puyo con i valori attuali. */
    public void aggiorna() {
        
        cibo.setText("Cibo="+tama.getPuyo().getCibo());
        allegria.setText("Allegria="+tama.getPuyo().getAllegria());
        affetto.setText("Affetto="+tama.getPuyo().getAffetto());
        peso.setText("Peso="+tama.getPuyo().getPeso()+" Kg");
        energia.setText("Energia="+tama.getPuyo().getEnergia()+"%");
        eta.setText("Eta="+tama.getPuyo().getEta());
    }
    
    public void timerNascita() {
        
        Timer nascita = new Timer(NASCITA, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                img.setIcon(DEFAULT);
            }
        });
        nascita.setRepeats(false);
        nascita.start();
    }
    
    public void timerUscita() {
        
        Timer addio = new Timer(USCITA, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                img.setIcon(END);
            }
        });
        addio.setRepeats(false);
        addio.start();
    }
}