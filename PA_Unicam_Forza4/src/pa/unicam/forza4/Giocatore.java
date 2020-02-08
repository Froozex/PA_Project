/**
 * 
 */
package pa.unicam.forza4;

/**
 * @author Ludovico
 *
 */
public interface Giocatore {
	
	public Giocatore checkRisultato(Griglia campo);
	public void avvia(Parametri param);
	abstract boolean giocIA();
	public String getNome();
	public boolean tuoTurno();

}
