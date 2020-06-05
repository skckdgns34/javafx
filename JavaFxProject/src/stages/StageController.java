package stages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageController implements Initializable{
	@FXML TableView<Student> tableView;
	@FXML Button btnAdd,btnChart;
	ObservableList<Student> scores;
	
	//stage의 주체화?를 위한 것들?
//	Stage primaryStage;
//	void setPrimaryStage(Stage primaryStage) {
//		this.primaryStage = primaryStage;
//	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		scores = FXCollections.observableArrayList();
		btnAdd.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				buttonAddAction(event);
			}
		});
		btnChart.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				buttonChartAction(event);
			}
		});
		
		//1번째 컬럼 name이라고 지정?
		TableColumn<Student, ?> tcName = tableView.getColumns().get(0);
		tcName.setCellValueFactory(new PropertyValueFactory("name"));
		
		TableColumn<Student, ?> tckorean = tableView.getColumns().get(1);
		tckorean.setCellValueFactory(new PropertyValueFactory("korean"));
		
		TableColumn<Student, ?> tcMath = tableView.getColumns().get(2);
		tcMath.setCellValueFactory(new PropertyValueFactory("math"));
		
		TableColumn<Student, ?> tcEnglish = tableView.getColumns().get(3);
		tcEnglish.setCellValueFactory(new PropertyValueFactory("english"));
		
		
		
		

		tableView.setItems(scores);
		
	} // end of initialize

	
	public void buttonAddAction(ActionEvent ae) {
		Stage addStage = new Stage(StageStyle.UTILITY);
		addStage.initModality(Modality.WINDOW_MODAL);
		addStage.initOwner(btnAdd.getScene().getWindow()); //해당 id가 있는 window에 새로만드는 윈도우를 뿌리겠다는 거
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm.fxml"));
			Scene scene = new Scene(parent);
			addStage.setScene(scene);
			addStage.setResizable(false); //윈도우 크기 변경 불가능하게 하는거.
			addStage.show();		
			
			Button btnFormAdd = (Button)parent.lookup("#btnFormAdd"); //fx:id말고 그냥 id로 선언되어있는 거 땡겨오는 법
			Button btnFormCancel = (Button)parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(e-> addStage.close());
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					TextField txtName = (TextField)parent.lookup("#txtName");
					TextField txtKorean = (TextField)parent.lookup("#txtKorean");
					TextField txtMath = (TextField)parent.lookup("#txtMath");
					TextField txtEnglish = (TextField)parent.lookup("#txtEnglish");
					Student student = new Student(
							txtName.getText(),
							Integer.parseInt(txtKorean.getText()),
							Integer.parseInt(txtMath.getText()),
							Integer.parseInt(txtEnglish.getText())
					);
					scores.add(student);
					addStage.close();
				}
			});
			

			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	} //end of buttonAddAction
	public void buttonChartAction(ActionEvent ae) {
		Stage chartStage = new Stage(StageStyle.UTILITY);
		chartStage.initModality(Modality.WINDOW_MODAL);
		chartStage.initOwner(btnAdd.getScene().getWindow());
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("ScoreChart.fxml"));
			BarChart barChart = (BarChart)parent.lookup("#barChart");
			
			//국어
			XYChart.Series<String,Integer> seriesKorean = new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasKorean = FXCollections.observableArrayList();
			for(int i=0; i<scores.size(); i++) {
				datasKorean.add(new XYChart.Data(scores.get(i).getName(), scores.get(i).getKorean())
				);
				//"이름", 국어점수 <- 이런모양 원함
			}
			seriesKorean.setData(datasKorean);
			seriesKorean.setName("국어");
			//영어
			XYChart.Series<String,Integer> seriesMath = new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasMath = FXCollections.observableArrayList();
			for(int i=0; i<scores.size(); i++) {
				datasMath.add(new XYChart.Data(scores.get(i).getName(), scores.get(i).getMath())
				);
				//"이름", 국어점수 <- 이런모양 원함
			}
			seriesMath.setData(datasMath);
			seriesMath.setName("수학");

			//수학
			XYChart.Series<String,Integer> seriesEnglish = new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasEnglish = FXCollections.observableArrayList();
			for(int i=0; i<scores.size(); i++) {
				datasEnglish.add(new XYChart.Data(scores.get(i).getName(), scores.get(i).getEnglish())
				);
				//"이름", 국어점수 <- 이런모양 원함
			}
			seriesEnglish.setData(datasEnglish);
			seriesEnglish.setName("영어");

			barChart.setData(FXCollections.observableArrayList(seriesKorean,seriesMath,seriesEnglish));
			
			
			Scene scene = new Scene(parent);
			chartStage.setScene(scene);
			chartStage.setResizable(false);
			chartStage.show();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} //end of buttonChartAction
	
} // end of class
