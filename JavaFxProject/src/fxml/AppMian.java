package fxml;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppMian extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox();			  //h(horizon)box 컨테이너 생성
		root.setPadding(new Insets(10));  //안쪽 여백 설정
		root.setSpacing(10);			  //컨트롤 간의 수평간격 설정
		
		TextField textField = new TextField(); //텍스트필드 컨트롤 생성
		textField.setPrefWidth(200);		   //텍스트필드 폭 설정
		
		Button button = new Button();
		button.setText("확인");
//		button.setOnAction(event -> Platform.exit());

		ObservableList list = root.getChildren();  //root의 ObservableList(관찰가능목록) 얻기
		list.add(textField);
		list.add(button);
		
		Scene scene = new Scene(root); 		//scene 생성
		
		primaryStage.setTitle("AppMain");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
