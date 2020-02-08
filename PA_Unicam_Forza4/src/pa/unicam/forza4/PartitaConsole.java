/**
 * 
 */
package pa.unicam.forza4;
import java.io.IOException;
import java.util.Scanner;

import pa.unicam.forza.eccezioni.Forza4Eccezioni;

/**
 * @author Ludovico
 *
 */
public class PartitaConsole {

	private Griglia campogioco;
	private GestorePartita partita;
	private Parametri parametri;
	private Giocatore[] giocatore;
	
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PartitaConsole p = new PartitaConsole();
		
		Menu();
		p.settings();
		p.avvia();

	}

	private void settings() {
		// TODO Auto-generated method stub
		
		int scelta = scanner.nextInt();
		if(scelta == 1) {
			parametri = new Parametri();
			this.campogioco = new Griglia();
			giocatore = new Giocatore[2];
			giocatore[0] = new GiocatoreReal(parametri.getNomeGiocatore1(), Griglia.ROSSO);
			giocatore[1] = new GiocatoreReal(parametri.getNomeGiocatore2(), Griglia.GIALLO);
			
			
			System.out.println(giocatore[0].getNome() + " hai le pedine Rosse ('R' nella griglia)\n");
		    System.out.println(giocatore[1].getNome() + " hai le pedine Gialle ('G' nella griglia)\n");
		    
		    campogioco.StampaGriglia();
		    
		} else if(scelta == 2){//TODO settings per partita vs IA
			
			System.out.println("partita vs IA!\n Selezionare la difficoltà di gioco:\n 1 - FACILE\n 2 - MEDIA\n 3 - DIFFICILE\n 4 - MOLTO DIFFICILE\n");
			int diff = scanner.nextInt();
			parametri = new Parametri(2, diff);
			this.campogioco = new Griglia();
			giocatore = new Giocatore[2];
			giocatore[0] = new GiocatoreReal(parametri.getNomeGiocatore1(), Griglia.ROSSO);
			giocatore[1] = new GiocatoreIA(parametri.getNomeGiocatore2(), diff, Griglia.GIALLO);
			
			System.out.println(giocatore[0].getNome() + " hai le pedine Rosse ('R' nella griglia)\n");
		    System.out.println(giocatore[1].getNome() + " ha le pedine Gialle ('G' nella griglia)\n");
		    
		    campogioco.StampaGriglia();
			
		} else if(scelta ==0) {
			System.exit(scelta);
			System.out.println("Uscita dal gioco!");
		}
	}
	
	private void avvia() throws IOException {
		// TODO Auto-generated method stub
		if(parametri != null) {
			
			partita = new GestorePartita(parametri, campogioco, giocatore);
			partita.inizia();
			partita.gioca();
			
		}
	}
	
	public static void Menu() {
		
		System.out.println("Benvenuto su Forza4 !\n");
		System.out.println("Scegli se vuoi giocare contro un altro giocatore -> DIGITA 1\n");
		System.out.println("Scegli se vuoi giocare contro l'IA -> DIGITA 2\n");
		System.out.println("Se vuoi uscire dal gioco -> DIGITA 0\n");
		
	}

}
