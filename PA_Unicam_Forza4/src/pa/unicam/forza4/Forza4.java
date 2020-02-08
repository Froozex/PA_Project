/**
 * 
 */
package pa.unicam.forza4;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ludovico
 *
 */
public interface Forza4 {
	
	/**
	 * metodo che controlla se si ha una vittoria per orizzontale:
	 * per ogni riga prendiamo una sezione di 4 colonne e inseriamo i valori interni
	 * in un hashset; siccome i set di hashset non consentono l'inserimento nella sua lista di elementi duplicati,
	 * si verifica più velocemente se il gruppo di 4 pedine orizzontali sono dello stesso colore(tipo).
	 * Poi si ripete questo controllo per ogni gruppo di 4 colonne
	 * @return
	 */
	default public int checkOrizzontale(int[][]campo) {
		
		for(int r=0; r<6; r++) {
			for(int c=0; c<7-3; c++) {
				Set<Integer> hashPedina = new HashSet<Integer>();
				hashPedina.add(campo[r][c]);
				hashPedina.add(campo[r][c+1]);
				hashPedina.add(campo[r][c+2]);
				hashPedina.add(campo[r][c+3]);
				
				if(hashPedina.size()==1) {
				
				if(hashPedina.contains(Griglia.ROSSO)) {
					return Griglia.ROSSO;//giocatore1 vincitore
				} else if(hashPedina.contains(Griglia.GIALLO)) {
					return Griglia.ROSSO;//giocatore2 vincitore
					}
				}
			}
		}
		return -1;
	}
	
	/**
	 * metodo che controlla se si ha una vittoria in verticale:
	 * per ogni colonna prendiamo una sezione di 4 righe e inseriamo i valori in un hashset;
	 * l'hashset non consente duplicati quindi si verifica velocemente se i valori sono tutti dello stesso tipo
	 * Si ripete poi per ogni sezione di 4 righe
	 * @return
	 */
	default public int checkVerticale(int[][]campo) {
		
		for(int c=0; c<7; c++) {
			for(int r=6-1; r>=3; r-- ) {
				Set<Integer> hashPedina = new HashSet<Integer>();
				hashPedina.add(campo[r][c]);
				hashPedina.add(campo[r-1][c]);
				hashPedina.add(campo[r-2][c]);
				hashPedina.add(campo[r-3][c]);
				
				if(hashPedina.size()==1) {
				//esiste un vincitore
				if(hashPedina.contains(Griglia.ROSSO)) {
					return Griglia.ROSSO;//vince il rosso(giocatore1)
				} else if(hashPedina.contains(Griglia.GIALLO)) {
					return Griglia.GIALLO;//vince il giallo(giocatore2)
					}
				}
			}
		}
		return -1;
	}
	
	/**
	 * metodo che controlla la vittoria in diagonale
	 * @return
	 */
	default public int checkDiagonale(int[][]campo) {
		
		//durante i cicli per il controllo , i valori sono a volte ricontrollati perchè fanno parte di più diagonali distinte
		
		//Controllo diagonale da SX a DX
		
		//controlla i valori compresi delle prime 4 diagonali disponibili ( le prime due vengono ignorate perchè non si puo verificare la condizione di vittoria)
		
		for(int i=3; i<7; i++) {
			int r = 0;
			int c = i;
			while(c-3 >= 0 && r+3 < 6) {
				Set<Integer> hashPedina = new HashSet<>();
				hashPedina.add(campo[r][c]);
				hashPedina.add(campo[r+1][c-1]);
				hashPedina.add(campo[r+2][c-2]);
				hashPedina.add(campo[r+3][c-3]);
				
				if(hashPedina.size()==1) {
					//esiste un vincitore
				if(hashPedina.contains(Griglia.ROSSO)) {
					return Griglia.ROSSO;
				} else if(hashPedina.contains(Griglia.GIALLO)) {
					return Griglia.GIALLO;
				}
			}
			r++;	
			c--;	
		}
	}
	
	//controllo delle ultime due diagonali (Sempre da SX verso DX)
	for(int i=1; i<3; i++) {
		int r=0;
		int c= 7-1;
		while(c+3 < 6 && r-3 >= 0) {
			Set<Integer> hashPedina = new HashSet<>();
			hashPedina.add(campo[r][c]);
			hashPedina.add(campo[r+1][c-1]);
			hashPedina.add(campo[r+2][c-2]);
			hashPedina.add(campo[r+3][c-3]);
			
			if(hashPedina.size()==1) {
				
				if(hashPedina.contains(Griglia.ROSSO)) {
					return Griglia.ROSSO;
				} else if(hashPedina.contains(Griglia.GIALLO)) {
					return Griglia.GIALLO;
				}
			}
			r++;
			c--;
		}
	}
	
	//Controllo diagonali da DX verso SX
	for(int i= 7-4; i>=0; i--) {
		int r = 0;
		int c = i;
		while(c+3 < 6 && r+3 < 7) {
			Set<Integer> hashPedina = new HashSet<>();
			hashPedina.add(campo[r][c]);
			hashPedina.add(campo[r+1][c+1]);
			hashPedina.add(campo[r+2][c+2]);
			hashPedina.add(campo[r+3][c+3]);
			
			if(hashPedina.size()==1) {
				
				if(hashPedina.contains(Griglia.ROSSO)) {
					return Griglia.ROSSO;
				} else if(hashPedina.contains(Griglia.GIALLO)) {
					return Griglia.GIALLO;
				}
			}
			r++;
			c++;
		}
	}
	
	//controllo ultime due diagonali (sempre DX verso SX)
	for(int i=1; i<3; i++) {
		int r = i;
		int c = 0;
		while(r+3 < 6 && c+3 < 7) {
			Set<Integer> hashPedina = new HashSet<>();
			hashPedina.add(campo[r][c]);
			hashPedina.add(campo[r+1][c+1]);
			hashPedina.add(campo[r+2][c+2]);
			hashPedina.add(campo[r+3][c+3]);
			
			if(hashPedina.size()==1) {
				
				if(hashPedina.contains(Griglia.ROSSO)) {
					return Griglia.ROSSO;
				} else if(hashPedina.contains(Griglia.GIALLO)) {
					return Griglia.GIALLO;
				}
			}
			r++;
			c++;
		}
	  }
	  return -1;
	}

}
