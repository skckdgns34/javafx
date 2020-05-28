package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppMain extends Application{
	
	public AppMain(){
		System.out.println(Thread.currentThread().getName() + ": AppMain()실행"); //실행되고있는 현재 스레드
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(Thread.currentThread().getName() + ": start()실행");
		VBox root = new VBox();		//v(vertical)box
		root.setPrefWidth(350); 	//넓이
		root.setPrefHeight(150);	//높이
		root.setAlignment(Pos.CENTER); //컨트롤 중앙 정렬
		root.setSpacing(20); 	//컨트롤의 수직 간격
		
		Label label = new Label();
		label.setText("Hello, JavaFx");
		label.setFont(new Font(50));
		
		
		Button button = new Button();
		button.setText("확인");
		button.setOnAction(event -> Platform.exit());
		
		root.getChildren().add(label);
		root.getChildren().add(button);
	
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName() + ": init() 실행");
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println(Thread.currentThread().getName() + ": stop()실행");
	}
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + ": main()실행");
		launch(args);
	}
}
