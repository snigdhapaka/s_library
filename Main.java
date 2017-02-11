package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.ListController;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
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
