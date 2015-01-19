package ice.puyomaycry.model;

import java.applet.*;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * Classe che definisce il suono emesso da Puyo per attirare la tua
 * attenzione.
 */
public class Richiamo {

	/** L'audioclip. */
	AudioClip richiamo;
	/** L'indirizzo completo e il nome del file. */
	public URL indirizzo;


	/** Oggetto con l'indirizzo e l'audioclip. */
    public Richiamo() {

		/* cerca il file */
		try {
			this.indirizzo = new URL(
				"file:"+System.getProperty("user.dir")+"/Richiamo.wav");
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		}
		/* Applet mi crea l'audioclip con il file trovato */
		this.richiamo = Applet.newAudioClip(indirizzo);
	}

	/** Metodo che interrompe la riproduzione dell'audioclip. */
	public void stop() {
		richiamo.stop();
	}

	/** Metodo che inizia la riproduzione dell'audioclip. */
	public void play() {
		richiamo.play();
	}

	/** Metodo main di prova. Attenzione: l'applicazione non termina! */
	public static void main(String[] args) {
		Richiamo suono = new Richiamo();
		suono.play();
	}
}
