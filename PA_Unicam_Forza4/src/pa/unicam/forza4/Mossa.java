/**
 * 
 */
package pa.unicam.forza4;

/**
 * classe che identifica le informazioni di una mossa, quindi la colonna , la riga e il valore della pedina
 * @author Ludovico
 *
 */
public class Mossa {
	
	private int x;
	private int y;
	private int valore;
	
	
	public Mossa() {
		this.x = -1;
		this.y = -1;
		this.valore = 0;
	}
	
	public Mossa(int val) {
		this.x = -1;
		this.y = -1;
		this.valore = val;
	}
	
	public Mossa(int r, int c, int val) {
		this.x = r;
		this.y = c;
		this.valore = val;
	}
	
	public Mossa(Mossa mossa) {
		this.x = mossa.getRiga();
		this.y = mossa.getCol();
		this.valore = mossa.getValore();
	}
	
	int getRiga() {
		return this.x;
	}
	
	int getCol() {
		return this.y;
	}
	
	int getValore() {
		return this.valore;
	}
	
	void setRiga(int riga) {
		this.x = riga;
	}
	
	void setCol(int col) {
		this.y = col;
	}
	
	void setValore(int val) {
		this.valore = val;
	}

}
