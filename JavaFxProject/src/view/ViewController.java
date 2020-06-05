package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewFocusModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewController implements Initializable{
	@FXML ListView listView;
	@FXML TableView<Phone> tableView;
	@FXML ImageView imageView;
	
	@Override //초기화 지정
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList("GalaxyS1"
				,"GalaxyS2","GalaxyS3","GalaxyS4","GalaxyS5","GalaxyS6");
		list.add("GalaxyS7");
		listView.setItems(list);
		
		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tableView.getSelectionModel().select(newValue.intValue());
				tableView.scrollTo(newValue.intValue());
			}
		});
		
		tableView.setItems(FXCollections.observableArrayList(
				new Phone("GalaxyS1", "phone01.png"),
				new Phone("GalaxyS2", "phone02.png"),
				new Phone("GalaxyS3", "phone03.png"),
				new Phone("GalaxyS4", "phone04.png"),
				new Phone("GalaxyS5", "phone05.png"),
				new Phone("GalaxyS6", "phone06.png"),
				new Phone("GalaxyS7", "phone07.png")
				));
		
		TableColumn<Phone, ?> tcSmartPhone = tableView.getColumns().get(0);
		tcSmartPhone.setCellValueFactory(new PropertyValueFactory("smartPhone"));
		
		TableColumn<Phone, ?> tcImage = tableView.getColumns().get(1);
		tcImage.setCellValueFactory(new PropertyValueFactory("image"));
	
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {

			@Override
			public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
				imageView.setImage(new Image("/images/"+newValue.getImage()));	
			}
		});
	}
	
	public void handleBtnOkAction() {
		
	}
	public void handleBtnCancelAction() {
		Platform.exit();
	}
}
