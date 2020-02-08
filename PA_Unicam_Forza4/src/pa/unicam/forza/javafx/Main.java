package pa.unicam.forza.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pa.unicam.forza4.Parametri;

/**
 * VECCHIA CLASSE DI RIFERIMENTO PER LA GRAFICA FX CON FILE FXML , NON CONSIDERARE
 * @author Ludovico
 *
 */
public class Main extends Application {
	
	private Stage stage;
	private BorderPane firstBorderWindow;
	private AnchorPane startScene;
	private AnchorPane setGameIAWindow;
	private AnchorPane setGameWindow;
	private AnchorPane gameScene;

	@Override
	public void start(Stage Stage) {
		this.stage = Stage;
		this.stage.setTitle("Forza 4");
		
		startWindow();
		startScene();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
     * Inizializza la finestra del gioco.
     */
    public void startWindow() {
        
    	firstBorderWindow = new BorderPane();
    	Scene scene = new Scene(firstBorderWindow,800,650);
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
    
    
    public void startScene() {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getClassLoader().getResource("pa/unicam/forza/javafx/StartScene.fxml"));
    		startScene = (AnchorPane) loader.load();
    		firstBorderWindow.setCenter(startScene);
    		
    		//TODO button per settings partita : no utilizzo fmxl
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void settingsGameIAWindow() {
    	
    	//nuovo stage
		Stage settingIA = new Stage();
		settingIA.setTitle("Impostazioni Partita vs CPU");
		settingIA.initModality(Modality.WINDOW_MODAL);
		settingIA.initOwner(stage);
		Scene scene =  new Scene(setGameIAWindow);
		settingIA.setScene(scene);
		//set controller
		//StartSceneController controller = loader.getController();
		//controller.setSettingsGvIA(settingIA);
		
		settingIA.show();
    }
    
    //TODO
    public void settingsGameWindow() {
    	
    }
    
    //TODO
    public void GameScene() {
    	
    }
}
