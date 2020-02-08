/**
 * 
 */
package pa.unicam.forza4;

import java.util.ArrayList;

/**
 * Classe che definisce il campo di gioco
 * @author Ludovico
 *
 */
public class Griglia {
	
	private final int[][] campo;
	//dimensioni griglia di gioco
	public static final int RIGHE = 6;
	public static final int COLONNE = 7;
	static final int ROSSO = 0; //giocatore1 identificato da 'R' (colore rosso)
	static final int GIALLO = 1; //giocatore2 identificato da 'G' (colore giallo)
	private Mossa ultimaMossa;
	private GestorePartita arbitro;
	
	//metodo che crea la griglia
	public Griglia() {
		
		this.ultimaMossa = new Mossa();
		campo = new int[RIGHE][COLONNE];
		for(int r=0; r<RIGHE; r++) {
			for(int c=0; c<COLONNE; c++) {
				campo[r][c] = -1;
			}
		}
	}
	
	public Griglia(Griglia griglia) {
		
		this.ultimaMossa = griglia.getUltimaMossa();
		campo = new int[RIGHE][COLONNE];
		for(int r=0; r<RIGHE; r++) {
			for(int c=0; c<COLONNE; c++) {
				campo[r][c] = -1;
			}
		}
	}
	
	 public int[][] getGriglia() {
		return campo;
	}
	 
	 boolean grigliaPiena() {
			for(int r=0; r<RIGHE; r++) {
				for(int c=0; c<COLONNE; c++) {
					if(campo[r][c] == -1) {
						return false;
					}
				}
			}
			//altrimenti è piena
			return true;
		}
	 
	public void StampaGriglia() {
			System.out.println("==============================");
	        
	        System.out.println("1 2 3 4 5 6 7");
	        for (int i = 0; i < 6; i++) {
	            for (int j = 0; j < 7; j++) {
	                if (campo[i][j] == Griglia.ROSSO) {
	                    System.out.print("R ");
	                } else if (campo[i][j] == Griglia.GIALLO) {
	                    System.out.print("G ");
	                } else {
	                    System.out.print("- ");
	                }
	            }
	            System.out.println();
	        }
	        System.out.println("==============================");
		}
	
	public void ResetGriglia() {
		for(int r=0; r<RIGHE; r++) {
			for(int c=0; c<COLONNE; c++) {
				campo[r][c]=-1;
			}
		}
	}
	
	Mossa posizionaPedina(int col, int val) {
		
		try {
			this.campo[getRigaLibera(col)][col] = val;
			this.ultimaMossa = new Mossa(getRigaLibera(col), col, val);
			return getUltimaMossa();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Errore! la colonna " + (col) + " è inesistente!");
			return null;
		}//TODO add inputmissmatch exception, manage the exception for griglia piena , colonna piena 
		
	
		
		/*if(colonnaValida(colonna)) {
			getRigaLibera(colonna);
			
		}
		
		else if(colonnaPiena(colonna)) {
			System.out.println("Errore!\n La colonna è piena.\n");
			
		}
		else {
			return false;
			
		}
		return true;*/
	}
	
	public Mossa getUltimaMossa() {
		return this.ultimaMossa;
	}
	
	public boolean getFinePartita() {
		return arbitro.finePartita();
	}
	
	int getRigaLibera(int colonna) {
		for(int r= RIGHE-1; r>=0; r--) {
			if(campo[r][colonna] == -1) {
				return r;
			}
		}
		return -1;
	}
	
	public boolean colonnaPiena(int col) {
		
		if(campo[0][col] == -1) {
			return false;
		}
		else {
			return true;
		}
	}
	
	// Generate 7 grid childs --------------------------------
	ArrayList<Griglia> getChildren(int letter) {
		ArrayList<Griglia> children = new ArrayList<Griglia>();
			
		for (int col=0; col<7; col++) {
			if (!colonnaPiena(col)) {
				Griglia child = new Griglia(this);
				child.posizionaPedina(col, letter);
				children.add(child);
			}
		}
			
		return children;
	}

}
