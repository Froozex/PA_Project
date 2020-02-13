/**
 * 
 */
package pa.unicam.forza.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import pa.unicam.forza4.AlgoritmoIA;
import pa.unicam.forza4.GestorePartita;
import pa.unicam.forza4.Giocatore;
import pa.unicam.forza4.GiocatoreIA;
import pa.unicam.forza4.Griglia;
import pa.unicam.forza4.Parametri;
import pa.unicam.forza4.PartitaJavaFX;
/**
 * CLASSE PER L'AVVIO DEL GIOCO CON L'INTERFACCIA GRAFICA JAVAFX
 * @author Ludovico
 *
 */
public class JavaFXapp extends Application {
	
	private Stage stage;
	private Griglia campo = new Griglia();
	private GestorePartita arbitro;
	private Parametri parametri;
	private Giocatore[] giocatore;
	private GiocatoreIA giocatoreIA;
	
	private static final int CELL_SIZE = 80;
	private boolean redMove = true;
    private Disc[][] grid = new Disc[Griglia.COLONNE][Griglia.RIGHE];
    private Pane discRoot = new Pane();
    
	private BorderPane firstBorderWindow;
	private AnchorPane homeScene;
	private AnchorPane setGameIAWindow;
	private AnchorPane setGameWindow;
	private AnchorPane gameScene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.stage = primaryStage;
		primaryStage.setResizable(false);
		this.stage.setTitle("Forza 4");
		
		startWindow();
		homeStage();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
     * Inizializza la finestra del gioco.
     */
	
    public void startWindow() {
        
    	firstBorderWindow = new BorderPane();
    	Scene scene = new Scene(firstBorderWindow,600,600);
		stage.setScene(scene);
		
		//MenuBar
		MenuBar mb = new MenuBar();
		Menu topMenu = new Menu("Impostazioni");
		
		MenuItem regole = new MenuItem("Regole");
		MenuItem esci = new MenuItem("Esci");
		topMenu.getItems().addAll(esci, regole);
		mb.getMenus().add(topMenu);
		
		EventHandler<ActionEvent> MEHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String nome = ((MenuItem)event.getTarget()).getText();
				if (nome.equals("Esci")) Platform.exit();
			}
		};
		
		esci.setOnAction(MEHandler);
		
		firstBorderWindow.setTop(mb);
		// Show the scene
		stage.show();
    }
    
    public void homeStage() {
    	
    	homeScene = new AnchorPane();
    	
    	ImageView title = new ImageView("titleScreen.png");
    	title.toBack();
    	Button GvG = new Button();
    	GvG.toFront();
    	GvG.setText("GIOCA CONTRO UN AMICO");
    	Button GvIA = new Button();
    	GvIA.toFront();
    	GvIA.setText("GIOCA CONTRO LA CPU");
    	GvG.setPrefSize(200, 70);
    	GvIA.setPrefSize(200, 70);
    	AnchorPane.setLeftAnchor(GvG, 40.0);
    	AnchorPane.setBottomAnchor(GvG, 80.0);
    	AnchorPane.setRightAnchor(GvIA, 40.0);
    	AnchorPane.setBottomAnchor(GvIA, 80.0);
    	//funzionalità dei button
    	windowGvIA(GvIA);
    	windowGvG(GvG);
    	
    	homeScene.getChildren().addAll(title, GvG, GvIA);
    	firstBorderWindow.setCenter(homeScene);
    }
    
    public void windowGvIA(Button button) {
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				//Layout finestra impostazione partita v cpu
				AnchorPane settingsGvIA = new AnchorPane();
				GridPane grid = new GridPane();
				grid.setPadding(new Insets(20));
				grid.setHgap(55);
				grid.setVgap(35);
				Label title = new Label("Impostazioni vs CPU");
				Label labelDiffIA = new Label("Seleziona la difficoltà dell'IA: ");
				ComboBox<String> diffIA = new ComboBox<String>();
				diffIA.getItems().addAll(
						"Facile",
						"Medio",
						"Difficile",
						"Molto Difficile");
				Label labelNome1 = new Label("Nome Giocatore 1");
				TextField nome1 = new TextField();
				Label labelNome2 = new Label("Nome Giocatore 2 (CPU)");
				TextField nome2 = new TextField();
				Button conferma = new Button();
				conferma.setText("Inizia");
				Button annulla = new Button();
				annulla.setText("Annulla");
				//position in grid
				grid.add(title, 0, 0, 2, 1);
				grid.add(labelDiffIA, 0, 1);
				grid.add(diffIA, 1, 1);
				grid.add(labelNome1, 0, 2);
				grid.add(nome1, 1, 2);
				grid.add(labelNome2, 0, 3);
				grid.add(nome2, 1, 3);
				grid.add(conferma, 1, 4);
				grid.add(annulla, 0, 4);
				
				closeEvent(annulla);
				
				
				
				settingsGvIA.getChildren().add(grid);
				
				//set scena
				Scene sceneGvIA = new Scene(settingsGvIA, 450, 350);
				Stage settingsWindow = new Stage();
				settingsWindow.setResizable(false);
				settingsWindow.setTitle("IMPOSTAZIONI PARTITA VS CPU");
				settingsWindow.setScene(sceneGvIA);
				settingsWindow.initModality(Modality.WINDOW_MODAL);
				settingsWindow.setX(stage.getX()+200);
				settingsWindow.setY(stage.getY()+100);
				settingsWindow.show();
				conferma.setOnAction(new EventHandler<ActionEvent>() {
					 
				    @Override
				    public void handle(ActionEvent e) {
				    	if(diffIA.getSelectionModel().isEmpty() || diffIA.getSelectionModel()==null) {
				    		Alert alert = new Alert(AlertType.WARNING);
					    	alert.setTitle("Errore");
					    	alert.setHeaderText(null);
					    	alert.setContentText("Inserisci la difficoltà del tuo avversario!");
					    	alert.showAndWait();
					    	return;}
				    	else if (nome1.getText().trim().isEmpty() || nome1.getText().trim()==null || nome2.getText().trim().isEmpty() || nome2.getText().trim()==null){
				    		Alert alert = new Alert(AlertType.WARNING);
					    	alert.setTitle("Errore");
					    	alert.setHeaderText(null);
					    	alert.setContentText("Inserisci il nome dei giocatori!");
					    	alert.showAndWait();
					    	return;
					    	} 
				    	else
				    		gameStart(conferma);
				    	}
				    
				});
				
				
			}
		});    	
				
	}

    public void closeEvent(Button button) {
    	button.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			Stage stage = (Stage) button.getScene().getWindow();
		    stage.close();
		}
    	});
    }
    
    
    public void windowGvG(Button button) {
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				AnchorPane settingsGvG = new AnchorPane();
				GridPane grid = new GridPane();
				grid.setPadding(new Insets(20));
				grid.setHgap(55);
				grid.setVgap(35);
				Label title = new Label("Impostazioni Partita");
				Label labelNome1 = new Label("Nome Giocatore 1");
				TextField nome1 = new TextField();
				Label labelNome2 = new Label("Nome Giocatore 2");
				TextField nome2 = new TextField();
				Button conferma = new Button();
				//-> open new game vs cpu with parameters
				// set parameters for game
				conferma.setText("Inizia");
				Button annulla = new Button();
				annulla.setText("Annulla");
				
				//position in grid
				grid.add(title, 0, 0, 2, 1);
				grid.add(labelNome1, 0, 1);
				grid.add(nome1, 1, 1);
				grid.add(labelNome2, 0, 2);
				grid.add(nome2, 1, 2);
				grid.add(conferma, 1, 3);
				grid.add(annulla, 0, 3);
				settingsGvG.getChildren().add(grid);
				closeEvent(annulla);
				closeEvent(conferma);
				gameStart(conferma);
				//set scene
				Scene sceneGvG = new Scene(settingsGvG, 450, 350);
				Stage settingsWindow = new Stage();
				settingsWindow.setResizable(false);
				settingsWindow.setTitle("IMPOSTAZIONI PARTITA VS ALTRO GIOCATORE");
				settingsWindow.setScene(sceneGvG);
				settingsWindow.initModality(Modality.WINDOW_MODAL);
				settingsWindow.setX(stage.getX()+200);
				settingsWindow.setY(stage.getY()+100);
				settingsWindow.show();
				
				conferma.setOnAction(new EventHandler<ActionEvent>() {
					 
				    @Override
				    public void handle(ActionEvent e) {
				    	if (nome1.getText().trim().isEmpty() || nome1.getText().trim()==null || nome2.getText().trim().isEmpty() || nome2.getText().trim()==null){
		    		Alert alert = new Alert(AlertType.WARNING);
			    	alert.setTitle("Errore");
			    	alert.setHeaderText(null);
			    	alert.setContentText("Inserisci il nome dei giocatori!");
			    	alert.showAndWait();
			    	return;
			    	} else
		    	gameStart(conferma);
		    	}
				});
				
				
			}
    		
    	});
		
	}
    
   /* public void startGame (Button button) throws InterruptedException {
    	button.setOnAction(new SceneHandler<ActionEvent>() {
    	stage.setScene(new Scene(createContent()));
		stage.show();}
    }*/
    
    public void gameStart(Button button) {
    	button.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			try {
				stage.setScene(new Scene(createContent()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage.show();
		}
    	});
    }
    
private Parent createContent() throws InterruptedException {
		
		Pane root = new Pane();
		ImageView gridShape = creaGriglia();
		gridShape.toFront();
		root.getChildren().add(gridShape);
		root.getChildren().add(discRoot);
		root.getChildren().addAll(selectColonna());
		
		return root;
	}
    
private ImageView creaGriglia() {
	
	ImageView image = new ImageView("Griglia_img.png");
		
		/*Shape field = new Rectangle((Griglia.COLONNE + 1) * CELL_SIZE, (Griglia.RIGHE + 1) * CELL_SIZE);
		Shape bg = new Rectangle((Griglia.COLONNE + 1) * CELL_SIZE, (Griglia.RIGHE + 1) * CELL_SIZE);
		bg.setFill(Color.BLUE);
		//creazione della griglia vuota
		for(int y=0; y<Griglia.RIGHE; y++) {
			for(int x=0; x<Griglia.COLONNE; x++) {
				Circle cella = new Circle(CELL_SIZE / 2);
				cella.setCenterX(CELL_SIZE / 2);
				cella.setCenterY(CELL_SIZE / 2);
				cella.setTranslateX(x * (CELL_SIZE + 5) + CELL_SIZE / 4);
				cella.setTranslateY(y* (CELL_SIZE + 5) + CELL_SIZE / 4);
				cella.setFill(Color.BLACK);
				field = field.intersect(field, cella);
			}
		}*/
		//effetto luci sulla griglia
		Light.Distant light = new Light.Distant();
		light.setAzimuth(45.0);
		light.setElevation(30.0);
		
		Lighting lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(5.0);
		return image;
		
		
	}
	
	//mostra in trasparenza la colonna quando si passa con il mouse
	private List<Rectangle> selectColonna() throws InterruptedException {
		List<Rectangle> listCol = new ArrayList<Rectangle>();

		// block events
		
		for(int x=0; x < Griglia.COLONNE; x++) {
			Rectangle rett = new Rectangle(CELL_SIZE, (Griglia.RIGHE + 1) * CELL_SIZE);
			rett.setTranslateX(x * (CELL_SIZE + 5) + CELL_SIZE / 4);
			rett.setFill(Color.TRANSPARENT);
			
			rett.setOnMouseEntered(e -> rett.setFill(Color.rgb(200, 200, 50, 0.3)));
			
			rett.setOnMouseExited(e -> rett.setFill(Color.TRANSPARENT));
			
			
			//TODO: inserimento pedina
			final int column = x;
			
			rett.setOnMouseClicked(e -> {
				try {
					placeDisc(new Disc(redMove), column);
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				});
				
			
			listCol.add(rett);
			
		}
		return listCol;
		
	}
	
	private void placeDisc(Disc disc, int column) throws InterruptedException {
        int row = Griglia.RIGHE - 1;
        do {
            if (!getDisc(column, row).isPresent())
                break;

            row--;
        } while (row >= 0);

        if (row < 0)
            return;

        grid[column][row] = disc;
        discRoot.toBack();
        discRoot.getChildren().add(disc);
        
        disc.setTranslateX(column * (CELL_SIZE + 5) + CELL_SIZE / 4);

        final int currentRow = row;

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (CELL_SIZE + 5) + CELL_SIZE / 4);
        
        animation.setOnFinished(e -> {
        	
            if (gameEnded(column, currentRow)) {
                try {
					gameOver();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
           

            redMove = !redMove;
            
        });
        animation.play();
        //INSERIRE DISABILITAZIONE MOUSE E RIATTIVAZIONE IN 500 MILLISECONDI
        
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
	private void gameOver() throws Exception {
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("VITTORIA!");
    	alert.setHeaderText(null);
    	alert.setContentText("Il vincitore è " + (redMove ? "RED" : "YELLOW"));
    	alert.setOnHidden(evt -> {
			try {
				start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

    	alert.show();
    	
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
            toBack();
            setCenterX(CELL_SIZE / 2);
            setCenterY(CELL_SIZE / 2);
            
        }
    }
   
}
