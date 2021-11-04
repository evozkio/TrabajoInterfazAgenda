package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Agenda extends Application{
	

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		
		FXMLLoader loader = new FXMLLoader(Agenda.class.getResource("/vista/Agenda.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();

		
				
		Scene scene = new Scene(pane);
		stage.setScene(scene);
//		stage.setResizable(false);
		stage.setTitle("Agenda");
		stage.show();
	}

}
