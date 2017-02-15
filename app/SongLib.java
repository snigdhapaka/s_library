/* Group 41: Niral Shah (nys7), Snigdha Paka (sp1088) */
package app;
	
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import view.ListController;


public class SongLib extends Application {
	@Override
	public void start(Stage primaryStage) 
	throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/List.fxml"));
		FlowPane root = (FlowPane) loader.load();
		
		ListController listController = loader.getController();
		listController.start(primaryStage);
		Scene scene  = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);  
		primaryStage.show(); 

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
