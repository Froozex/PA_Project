/**
 * 
 */
package pa.unicam.forza4;

import java.util.Scanner;

/**
 * CLASSE CHE DEFINISCE UNA PARTITA (FORSE CLASSE PROVVISORIA)
 * @author Ludovico
 *
 */
public class Partita {
	
	private Parametri param;
	private Griglia campo;
	private Giocatore[] gioc;
	
	public Partita() {
		
		this.campo = new Griglia();
		this.param = new Parametri();
		this.gioc = new Giocatore[2];
		this.gioc[0] = new GiocatoreReal(param.getNomeGiocatore1(), Griglia.ROSSO);
		this.gioc[1] = new GiocatoreReal(param.getNomeGiocatore2(), Griglia.GIALLO);
	}
	
	public Partita(Parametri param) {
		
		this.campo = new Griglia();
		this.param = param;
		this.gioc = new Giocatore[2];
		this.gioc[0] = new GiocatoreReal(param.getNomeGiocatore1(), Griglia.ROSSO);
		this.gioc[1] = new GiocatoreReal(param.getNomeGiocatore2(), Griglia.GIALLO);
		
		if(param.getModGioco() == Parametri.GvIA) {
			this.gioc[1] = new GiocatoreIA(param.getNomeGiocatore2(),param.getDiffIA(), Griglia.GIALLO);
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
