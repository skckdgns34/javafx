package fxml;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppMian extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox();			  //h(horizon)box 컨테이너 생성
		root.setPadding(new Insets(10));  //안쪽 여백 설정
		root.setSpacing(10);			  //컨트롤 간의 수평간격 설정
		root.setPrefSize(700, 300);
		
		TextField textField = new TextField(); //텍스트필드 컨트롤 생성
		textField.setPrefWidth(200);		   //텍스트필드 폭 설정
		textField.setPrefHeight(100);
	
		Button button = new Button();
		button.setText("확인");
		button.setPrefSize(100,100);
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				textField.setText("확인을 눌렀습니다");
			}
		});
		
		Button button1 = new Button();
		button1.setText("취소");
		button1.setPrefSize(100,100);
		button1.setOnAction((event)->textField.setText(null));
//		button.setOnAction(event -> Platform.exit());

		
		ObservableList list = root.getChildren();  //root의 ObservableList(관찰가능목록) 얻기
		list.add(textField);
		list.add(button);
		list.add(button1);
		Scene scene = new Scene(root); 		//scene 생성
		
		primaryStage.setTitle("AppMain");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.out.println(event);
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
