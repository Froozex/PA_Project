/**
 * 
 */
package pa.unicam.forza4;

/**
 * @author Ludovico
 *
 */
public class GiocatoreReal implements Giocatore {
	
	String nome;
	String colore;
	int valore;
	boolean tuoTurno = false;//serve per iniziare un nuovo turno
	Griglia griglia;
	Parametri param;
	
	public GiocatoreReal(String nome, int pedina) {
		this.nome = nome;
		this.valore = pedina;
		setColore(pedina);
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getValore() {
		return valore;
	}
	
	public void setColore(int valore) {
		if(valore == Griglia.ROSSO) this.colore = "ROSSO";
		else this.colore = "GIALLO";
	}
	
	public boolean tuoTurno() {
		setTurno();
		return this.tuoTurno;
	}
	public void setTurno() {
		this.tuoTurno = !this.tuoTurno;
	}

	@Override
	public Giocatore checkRisultato(Griglia campo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void avvia(Parametri param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean giocIA() {
		// TODO Auto-generated method stub
		return false;
	}

}
