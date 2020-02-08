/**
 * 
 */
package pa.unicam.forza4;

/**
 * interfaccia che implementa dei metodi utili per la mossa eseguita dall'IA e dei controlli utili per la sua partita
 * 
 * @author Ludovico
 *
 */
public interface CheckForIA {
	
	//verifica se la colonna dove fare la mossa è esistente (anche la riga di conseguenza)
	default boolean colonnaValida(int rig, int col) {

		if(col <= -1 || col > 6 || rig <= -1 || rig > 5) {
			//System.out.println("Errore!\n Scegli una colonna fra 1 e 7\n");
			return false;
		}else {
			return true;
		}
	}
	
	//controllo utile per la partita della cpu. Verifica ogni volta che ci sono delle pedina di fila almeno di 2 o 3 caselle
	//in modo da poter effettuare di conseguenza una mossa seguendo l'algoritmo
	// Check for 2 and 3 consecutive checkers -------------------
	default int[] checkConsecutive(int [][] campo, int giocatore) {
		int r;
		int c;
		int[] row = {0,0}; // times[0] for store times of 2 consecutive markers
							 // times[1] for store times of 3 consecutive markers
			
		for (r=5; r>=0; r--) {
			for (c=0; c<6; c++) { 
				if (colonnaValida(r, c+1) &&
					campo[r][c] == giocatore &&
					campo[r][c] == campo[r][c+1]) {
						row[0]++;
						if (colonnaValida(r, c+2) &&
							campo[r][c] == campo[r][c+2]) row[1]++;
				}
				if (colonnaValida(r-1, c) &&
					campo[r][c] == giocatore && 
					campo[r][c] == campo[r-1][c]) {
						row[0]++;
						if (colonnaValida(r-2, c) &&
							campo[r][c] == campo[r-2][c]) row[1]++;
				}
			}
		}

		for (r=0; r<6; r++) {
			for (c=0; c<6; c++) {
				if (colonnaValida(r+1, c+1) &&
					campo[r][c] == giocatore &&
					campo[r][c] == campo[r+1][c+1]) {
						row[0]++;
						if (colonnaValida(r+2, c+2) &&
							campo[r][c] == campo[r+2][c+2]) row[1]++;
				}
				if (colonnaValida(r-1, c+1) &&
					campo[r][c] == giocatore && 
					campo[r][c] == campo[r-1][c+1]) {
						row[0]++;
						if (colonnaValida(r-2, c+2) &&
							campo[r][c] == campo[r-2][c+2]) row[1]++;
				}
			}
		}
			
		return row;
	}
	
	default int valutaMossa(int[][] campo, Mossa mossa) {
		//
		// +100 'ROSSO' è il vincitore, -100 'GIALLO' è il vincitore, quindi ci sono 4 pedine di fila
		// +10 per 3 pedine consecutive 'ROSSE, -10 per 3 pedine 'GIALLE' consecutive
		// +1 per 2 pedine consecutive 'ROSSE', -1 per 2 pedina 'GIALLE' consecutive
		int Rrow = 0;
		int Grow = 0;
		
		if(mossa.getValore() == Griglia.ROSSO) {
			Rrow = Rrow + 100;
		} else if(mossa.getValore() == Griglia.GIALLO) {
			Grow = Grow + 100;
		}
		int[] consecutive = checkConsecutive(campo, Griglia.ROSSO);
		Rrow = Rrow + consecutive[0] + consecutive[1]*10;
		consecutive = checkConsecutive(campo, Griglia.GIALLO);
		Grow = Grow + consecutive[0] + consecutive[1]*10;
		
		return Rrow - Grow;
	}

}
