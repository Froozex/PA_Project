/**
 * 
 */
package pa.unicam.forza4;

import java.util.Scanner;

/**
 * classe che ha il compito di creare, gestire una partita mettendo
 * in comunicazione i parametri di una partita con la griglia e le regole del gioco;
 * si può considerare come un arbitro che fa interagire i giocatori con le regole e con il campo di gioco
 * @author Ludovico
 *
 *
 *CLASSE SIMILE ALLA CLASSE PARTITA (BISOGNA DEFINERE BENE UNA SOLA CLASSE)
 *QUESTA CLASSE PER ORA è UTILIZZA SOLO PER UNA PARTITA CONSOLE
 */
public class GestorePartita implements Forza4 {
	
	private Parametri param;
	private Griglia campo;
	private Giocatore[] gioc;
	private int turno;
	boolean Vittoria = false;
	
	private static Scanner scanner = new Scanner(System.in);
	
	public GestorePartita(Parametri param, Griglia campo, Giocatore[] gioc) {
		
		this.param = param;
		this.campo = campo;
		this.gioc = gioc;
	}
	
	public final void inizia() {
		
		for(int i=0; i < gioc.length; i++) {
			gioc[i].avvia(param);
		}
		turno = 0;
	}
	
	public final int gioca() {//TODO da modificare : inserire il turno per la cpu con mossa
		
		while(!Vittoria) {
			gioc[turno] = tuoTurno();//giocatore di turno
			System.out.println(gioc[turno].getNome() + " è il tuo turno:\nDigita il numero della colonna per effettuare la mossa [0-6]\n");
			int col = scanner.nextInt();
			while(campo.posizionaPedina(col, turno) != campo.getUltimaMossa()) {
	    		System.out.println("Riprova!");
	    		col = scanner.nextInt();
	    	}
			campo.StampaGriglia();
			fineTurno();
		}
		return -1;
	}
	
	public Giocatore tuoTurno() {
		if(gioc[0].tuoTurno()) return gioc[0];
		else return gioc[1];
	}
	
	public final int fineTurno() {
		
		Giocatore vincitore = checkRisultato(campo);
		StampaVincitore(vincitore);
		if(vincitore != gioc[turno]) {
			Vittoria = false;
			turno = sucessivo();//il turno aumenta di 1 e quindi passa al giocatore successivo
		} else {
			Vittoria = true;
			System.out.println("fine partita");
			//PartitaConsole.Menu();
		}
		return -1;
	}
	
	//turno attuale
	public int attuale() {
		return turno;
	}
	
	//turno sucessivo
	public int sucessivo() {
		return (turno+1)%2;
	}
	
	public Giocatore checkRisultato(Griglia campo) {
		
		if(campo.grigliaPiena()) {
			System.out.println("Pareggio! \nla griglia è tutta piena");
			return null;
		}
		int check_righe = checkOrizzontale(campo.getGriglia());
		int check_col = checkVerticale(campo.getGriglia());
		int check_diag = checkDiagonale(campo.getGriglia());
		
		if(check_righe == 0 || check_col == 0 || check_diag == 0) {
			return gioc[0];
		} else if(check_righe == 1 || check_col == 1 || check_diag == 1) {
			return gioc[1];
		} else {
			return null;
		}
	}
	
	public boolean finePartita() {
		if(checkRisultato(campo) == gioc[0] || checkRisultato(campo) == gioc[1]) {
			System.out.println("Fine Partita!\n");
			return true;
		} else {
			return false;
		}
			
	}
	
	public void StampaVincitore(Giocatore vincitore) {
		
		if(vincitore == null ) {
			System.out.println("Ancora nessun Vincitore!\n");
		}
		else if(param.getModGioco() == Parametri.GvG) {
			System.out.println(vincitore.getNome()+" è il Vincitore!\n");
		}
		else {
			if(vincitore.giocIA()) 
				System.out.println("Hai Perso!\n");
				else System.out.println("Hai Vinto!\n");
			}
		}

}
