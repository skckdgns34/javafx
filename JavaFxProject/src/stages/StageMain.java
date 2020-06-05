package stages;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Root.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
//		FXMLLoader loader = new FXMLLoader();
//		Parent root = loader.load(getClass().getResource("Root.fxml"));
		// Controller에 stage값 넘겨주기
//		StageController cont = loader.getController(); //컨트롤러만 가져오기
//		cont.setPrimaryStage(primaryStage);
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}