/**
 * 
 */
package pa.unicam.forza4;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe che implementa l'intelligenza del giocatore automatico tramite l 'algoritmo di minimax
 * @author Ludovico
 *
 */
public class AlgoritmoIA implements CheckForIA {
	
	private int maxDepth;//massima profondità
	private int colore;
	
	public AlgoritmoIA() {
		
		maxDepth = 4;
		colore = Griglia.GIALLO;
	}
	
	public AlgoritmoIA(int maxDepth, int colore) {
		
		this.maxDepth = maxDepth;
		this.colore = colore;
	}
	
	public int getMaxDepth() {
		return maxDepth;
	}
	
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	
	Mossa MiniMax(Griglia campo) {
		if(colore == Griglia.ROSSO) {
			return Max(new Griglia(campo), 0);//giocatore 1 massimizza
		} else {
			return Min(new Griglia(campo), 0);// giocatore 2 minimizza
		}
	}
	
	public Mossa Max(Griglia campo, int depth) {
		
		Random r = new Random();
		Mossa ultimaMossa = campo.getUltimaMossa();
		//TODO sistemare ' if
		if(campo.grigliaPiena() || depth == maxDepth) {
			return new Mossa(ultimaMossa.getRiga(), ultimaMossa.getCol(), valutaMossa(campo.getGriglia(), ultimaMossa));
		}
		
		ArrayList<Griglia> child = new ArrayList<Griglia>(campo.getChildren(Griglia.ROSSO));
		Mossa maxMossa = new Mossa(Integer.MIN_VALUE);
		for(Griglia children : child) {
			Mossa mossa = Min(children, depth +1);
			if(mossa.getValore() >= maxMossa.getValore()) {
				if(mossa.getValore() == maxMossa.getValore()) {
					if(r.nextInt(2) == 0) {
						maxMossa.setRiga(children.getUltimaMossa().getRiga());
						maxMossa.setCol(children.getUltimaMossa().getCol());
						maxMossa.setValore(mossa.getValore());
						}
					} else {
						maxMossa.setRiga(children.getUltimaMossa().getRiga());
						maxMossa.setCol(children.getUltimaMossa().getCol());
						maxMossa.setValore(mossa.getValore());
					}
				}
			}
			return maxMossa;	
	}
	
	public Mossa Min(Griglia campo, int depth) {
		
		Random r = new Random();
		Mossa ultimaMossa = campo.getUltimaMossa();
		
		if(campo.grigliaPiena() || depth == maxDepth) {
			return new Mossa(ultimaMossa.getRiga(), ultimaMossa.getCol(), valutaMossa(campo.getGriglia(), ultimaMossa));
		}
		
		ArrayList<Griglia> child = new ArrayList<Griglia>(campo.getChildren(Griglia.ROSSO));
		Mossa minMossa = new Mossa(Integer.MAX_VALUE);
		for(Griglia children : child) {
			Mossa mossa = Min(children, depth +1);
			if(mossa.getValore() <= minMossa.getValore()) {
				if(mossa.getValore() == minMossa.getValore()) {
					if(r.nextInt(2) == 0) {
						minMossa.setRiga(children.getUltimaMossa().getRiga());
						minMossa.setCol(children.getUltimaMossa().getCol());
						minMossa.setValore(mossa.getValore());
						}
					} else {
						minMossa.setRiga(children.getUltimaMossa().getRiga());
						minMossa.setCol(children.getUltimaMossa().getCol());
						minMossa.setValore(mossa.getValore());
					}
				}
			}
			return minMossa;
	}

	private boolean FinePartita(Griglia campo, int depth) {
		
		
		return false;
	}
	
}
