/**
 * 
 */
package pa.unicam.forza4;

/**
 * classe che va a definire i parametri di gioco di una partita: la modalità di gioco -> può essere umano v umano
 * oppure umano v IA. Inoltre sono definiti i parametri della difficoltà della IA e i nomi dei giocatori
 * @author Ludovico
 *
 */
public class Parametri {
	
	private int modGioco;
	private int diffIA;
	private String nomeGiocatore1;
	private String nomeGiocatore2;
	public static final int GvG = 1;//modalità di gioco player v player
	public static final int GvIA = 2;// modalità di gioco player v IA
	
	//parametri per un partita umano v umano
	public Parametri() {
		
		this.modGioco = GvG;
		this.diffIA = 4;//difficoltà massima
		this.setNomeGiocatore1("Giocatore_1");
		this.setNomeGiocatore2("Giocatore_2");
	}
	
	//parametri per una partita umano v IA
	public Parametri(int mod, int diff) {
		
		this.modGioco = mod;
		this.diffIA = diff;
		this.setNomeGiocatore1("Giocatore_1");
		this.setNomeGiocatore2("Giocatore_2");
		
	}
	
	public void setNomeGiocatore1(String nomeg1) {
		
		//System.out.println("Inserisci nome del giocatore_1: \n");
		//Scanner input = new Scanner(System.in);
		this.nomeGiocatore1 = nomeg1;
	}
	
	public void setNomeGiocatore2(String nomeg2) {
		
		//System.out.println("Inserisci nome del giocatore_2: \n");
		//Scanner input = new Scanner(System.in);
		this.nomeGiocatore2 = nomeg2;
	}
	
	public String getNomeGiocatore1() {
		return this.nomeGiocatore1;
	}
	public String getNomeGiocatore2() {
		return this.nomeGiocatore2;
	}
	
	public int getModGioco() {
		return this.modGioco;
	}
	public void setModGioco(int mod) {
		this.modGioco = mod;
	}
	public int getDiffIA() {
		return this.diffIA;
	}
	public void setDiffIA(int diff) {
		this.diffIA = diff;
	}

}
