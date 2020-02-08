/**
 * 
 */
package pa.unicam.forza4;

/**
 * @author Ludovico
 *
 */
public class GiocatoreIA implements Giocatore {
	
	String nome;
	String colore;
	int valore;
	boolean attivo = false;//serve per iniziare un nuovo turno
	private AlgoritmoIA IA;
	
	public GiocatoreIA(String nome, int diff, int val) {
		
		this.nome = nome;
		this.valore = val;
		setColore(val);
		this.IA = new AlgoritmoIA(diff, Griglia.GIALLO);
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
	
	public boolean giocIA() {
		return true;
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
	public boolean tuoTurno() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Mossa mossaGiocIA(Griglia campo, int colore) {
		Mossa mossa = IA.MiniMax(campo);
		campo.posizionaPedina(mossa.getCol(), Griglia.GIALLO);
		return mossa;
	}

}
