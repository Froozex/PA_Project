/**
 * 
 */
package pa.unicam.forza.javafx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Ludovico
 *
 */
public class StartSceneController {
	
	private Stage stage;
	private Main main;
	
	@FXML
	private Button GvG;
	
	@FXML
	private Button GvIA;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setSettingsGvIA(Stage stage) {
        this.stage = stage;
    }
	
	@FXML
	private void openSettingsGvIA() {
		
		main.settingsGameIAWindow();
	}

}
