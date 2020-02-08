/**
 * 
 */
package pa.unicam.forza4;

/**
 * @author Ludovico
 *
 */
public class Pedina {
	
	private int col;
	private int rig;
	private String colore;
	
	public Pedina(Mossa mossa, String colore) {
		
		this.col = mossa.getCol();
		this.rig = mossa.getRiga();
		this.colore = colore;
	}
	
	public int getCol() {
		return this.col;
	}

	public int getRiga() {
		return this.rig;
	}

}
