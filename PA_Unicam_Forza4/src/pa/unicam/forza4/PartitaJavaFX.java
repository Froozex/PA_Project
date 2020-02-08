/**
 * 
 */
package pa.unicam.forza4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.geometry.Point2D;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Ludovico
 *
 */
public class PartitaJavaFX extends Application {
	
	private Griglia campo = new Griglia();
	private GestorePartita arbitro;
	private Parametri parametri;
	private Giocatore[] giocatore;
	
	private static final int CELL_SIZE = 80;
	private boolean redMove = true;
    private Disc[][] grid = new Disc[Griglia.COLONNE][Griglia.RIGHE];
    private Pane discRoot = new Pane();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setScene(new Scene(createContent()));
		stage.show();
	}
	
	private Parent createContent() throws InterruptedException {
		
		Pane root = new Pane();
		Shape gridShape = creaGriglia();
		root.getChildren().add(gridShape);
		root.getChildren().add(discRoot);
		root.getChildren().addAll(selectColonna());
		
		return root;
	}
	
	private Shape creaGriglia() {
		
		Shape field = new Rectangle((Griglia.COLONNE + 1) * CELL_SIZE, (Griglia.RIGHE + 1) * CELL_SIZE);
		
		//creazione della griglia vuota
		for(int y=0; y<Griglia.RIGHE; y++) {
			for(int x=0; x<Griglia.COLONNE; x++) {
				Circle cella = new Circle(CELL_SIZE / 2);
				cella.setCenterX(CELL_SIZE / 2);
				cella.setCenterY(CELL_SIZE / 2);
				cella.setTranslateX(x * (CELL_SIZE + 5) + CELL_SIZE / 4);
				cella.setTranslateY(y* (CELL_SIZE + 5) + CELL_SIZE / 4);
				
				field = field.subtract(field, cella);
			}
		}
		//effetto luci sulla griglia
		Light.Distant light = new Light.Distant();
		light.setAzimuth(45.0);
		light.setElevation(30.0);
		
		Lighting lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(5.0);
		
		field.setFill(Color.BLUE);
		return field;
	}
	
	//mostra in trasparenza la colonna quando si passa con il mouse
	private List<Rectangle> selectColonna() throws InterruptedException {
		List<Rectangle> listCol = new ArrayList<Rectangle>();
		
		for(int x=0; x < Griglia.COLONNE; x++) {
			Rectangle rett = new Rectangle(CELL_SIZE, (Griglia.RIGHE + 1) * CELL_SIZE);
			rett.setTranslateX(x * (CELL_SIZE + 5) + CELL_SIZE / 4);
			rett.setFill(Color.TRANSPARENT);
			
			rett.setOnMouseEntered(e -> rett.setFill(Color.rgb(200, 200, 50, 0.3)));
			
			rett.setOnMouseExited(e -> rett.setFill(Color.TRANSPARENT));
			
			
			//TODO: inserimento pedina
			final int column = x;
			rett.setOnMouseClicked(e -> placeDisc(new Disc(redMove), column));
			
			listCol.add(rett);
		}
		
		return listCol;
	}
	
	private void placeDisc(Disc disc, int column) {
        int row = Griglia.RIGHE - 1;
        do {
            if (!getDisc(column, row).isPresent())
                break;

            row--;
        } while (row >= 0);

        if (row < 0)
            return;

        grid[column][row] = disc;
        discRoot.getChildren().add(disc);
        disc.setTranslateX(column * (CELL_SIZE + 5) + CELL_SIZE / 4);

        final int currentRow = row;

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (CELL_SIZE + 5) + CELL_SIZE / 4);
        animation.setOnFinished(e -> {
            if (gameEnded(column, currentRow)) {
                gameOver();
            }

            redMove = !redMove;
        });
        animation.play();
    }
	
	private boolean gameEnded(int column, int row) {
        List<Point2D> vertical = IntStream.rangeClosed(row - 3, row + 3)
                .mapToObj(r -> new Point2D(column, r))
                .collect(Collectors.toList());

        List<Point2D> horizontal = IntStream.rangeClosed(column - 3, column + 3)
                .mapToObj(c -> new Point2D(c, row))
                .collect(Collectors.toList());

        Point2D topLeft = new Point2D(column - 3, row - 3);
        List<Point2D> diagonal1 = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> topLeft.add(i, i))
                .collect(Collectors.toList());

        Point2D botLeft = new Point2D(column - 3, row + 3);
        List<Point2D> diagonal2 = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> botLeft.add(i, -i))
                .collect(Collectors.toList());

        return checkRange(vertical) || checkRange(horizontal)
                || checkRange(diagonal1) || checkRange(diagonal2);
    }
	
	private boolean checkRange(List<Point2D> points) {
        int chain = 0;

        for (Point2D p : points) {
            int column = (int) p.getX();
            int row = (int) p.getY();

            Disc disc = getDisc(column, row).orElse(new Disc(!redMove));
            if (disc.red == redMove) {
                chain++;
                if (chain == 4) {
                    return true;
                }
            } else {
                chain = 0;
            }
        }

        return false;
    }
	
	//metodo stampa vincitore
	private void gameOver() {
        System.out.println("Winner: " + (redMove ? "RED" : "YELLOW"));
    }
	
	private Optional<Disc> getDisc(int column, int row) {
        if (column < 0 || column >= Griglia.COLONNE
                || row < 0 || row >= Griglia.RIGHE)
            return Optional.empty();

        return Optional.ofNullable(grid[column][row]);
    }
	
	private static class Disc extends Circle {
        private final boolean red;
        public Disc(boolean red) {
            super(CELL_SIZE / 2, red ? Color.RED : Color.YELLOW);
            this.red = red;

            setCenterX(CELL_SIZE / 2);
            setCenterY(CELL_SIZE / 2);
        }
    }

}
