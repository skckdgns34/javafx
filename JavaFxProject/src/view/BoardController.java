package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewFocusModel;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.util.Callback;

public class BoardController implements Initializable {
	@FXML
	TableView<Board> tableView;
	@FXML
	TextField textTitle, textDate;
	@FXML
	ComboBox comboBox;
	@FXML
	TextArea textArea;
	@FXML
	Button btn1, btn2, btn3;
	Connection conn;
	PreparedStatement pstmt = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<Board> boardList = getBoardList();
		TableColumn<Board, String> tcTitle = new TableColumn<Board, String>();
		tcTitle.setCellValueFactory(new Callback<CellDataFeatures<Board, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<Board, String> param) {
				return param.getValue().titleProperty();
			}
		});
		// exitDate
		TableColumn<Board, String> tcExitDate = new TableColumn<>();
		tcExitDate.setCellValueFactory(new PropertyValueFactory<Board, String>("exitDate"));
		
		tcTitle.setText("제목");
		tcExitDate.setText("종료일자");
		tableView.getColumns().add(tcTitle);
		tableView.getColumns().add(tcExitDate);
		tableView.setItems(boardList);
	
		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Board>() {
			@Override
			public void changed(ObservableValue<? extends Board> arg0, Board oldVal, Board newVal) {
				textTitle.setText(newVal.getTitle());
				textArea.setText(newVal.getContent());
				textDate.setText(newVal.getExitDate());
				comboBox.setValue(newVal.getPublicity().toString());
			}
		});
		
		//이전 버튼
		btn1.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent event) {
				tableView.getSelectionModel().selectPrevious();
			}
		});
		
		//다음 버튼
		btn2.setOnAction (event->tableView.getSelectionModel().selectNext());
		
		
		//수정 버튼
		btn3.setOnAction (new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				updateBoard(textArea.getText(), textTitle.getText());
				textArea.setText(textArea.getText());
				textTitle.setText(textTitle.getText());

			}
		});
	}

	//db에 넣는거
	public ObservableList<Board> getBoardList() {
		ObservableList<Board> list = FXCollections.observableArrayList();
		conn = getConnect();
		String sql = "select title, publicity, exit_date, content from board2";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board(rs.getString("title"), null, rs.getString("publicity"),
						rs.getString("exit_Date"), rs.getString("content"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	//수정
	public void updateBoard(String content, String title) {
		conn = getConnect();
		String sql = "update board2 set content = ? where title = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, title);
			int r = pstmt.executeUpdate();
			System.out.println(r + "건 변경됨");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//connect
	public Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hrr");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
