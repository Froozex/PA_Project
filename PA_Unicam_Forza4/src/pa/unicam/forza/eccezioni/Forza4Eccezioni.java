/**
 * 
 */
package pa.unicam.forza.eccezioni;

import pa.unicam.forza4.Giocatore;

/**
 * @author Ludovico
 *
 */
public class Forza4Eccezioni extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Giocatore giocatore;
	
	public Forza4Eccezioni(Giocatore gioc, String messaggio) {
		super(messaggio);
		this.giocatore = gioc;
	}
	
	/**
	 * Restituisce l'indice del giocatore che ha generato l'eccezione.
	 * 
	 * @return l'indice del giocatore che ha generato l'eccezione.
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}	

}
