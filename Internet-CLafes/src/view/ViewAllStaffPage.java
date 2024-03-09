package view;

import Controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class ViewAllStaffPage {
	
	//Mendeklarasikan semua objek yang dibutuhkan pada class ViewAllStaffJobPage
	BorderPane container;
	Label lblError, lblNote;
	TableView<User>tableStaff;
	TableColumn<User,Integer> col_UserID, col_UserAge;
	TableColumn<User,String> col_UserName, col_UserPassword, col_UserRole;
	UserController userController;
	Scene scene;
	ObservableList<User> userList;
	
	//Melakukan inisialiasi pada objek
	private void initialize() {
		container = new BorderPane();
		lblError = new Label("No Report");	
		lblNote = new Label("Click the Row to Change the Selected User Role");
		userController = new UserController();
		
		tableStaff = new TableView<>();
		
		col_UserID = new TableColumn<>("User ID");
		col_UserAge = new TableColumn<>("User Age");
		col_UserName = new TableColumn<>("Username");
		col_UserPassword = new TableColumn<>("User Password");
		col_UserRole = new TableColumn<>("User Role");
	}
	
	//Mengisi action pada button pada tableStaff
	private void actions(Stage primaryStage) {
		//Jika salah satu baris dari tableStaff diklik, maka akan langsung berpindah ke halaman ChangeRolePage
		tableStaff.setOnMouseClicked(e->{
			try {
	            int rowIndex = tableStaff.getSelectionModel().selectedIndexProperty().get();
	            int sz = userList.size();
	            
	            if(rowIndex<sz) {
	            	new ChangeRolePage(primaryStage, userList.get(rowIndex).getUserID());
	            }				
			}
			catch(Exception exp) {
				
			}
		});
	}
	
	//Bagian utama code yang menerima parameter Stage
	public ViewAllStaffPage(Stage primaryStage) {
		// Melakukan inisialisasi
		initialize();
		
		//Mendeklarasikan navigation dan mengisi bagian atas Container dengan navigation
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());
		
		//Membangun stukrur dari tableStaff
		tableStaff.getColumns().add(col_UserID);
		tableStaff.getColumns().add(col_UserAge);
		tableStaff.getColumns().add(col_UserName);
		tableStaff.getColumns().add(col_UserPassword);		
		tableStaff.getColumns().add(col_UserRole);
		
		col_UserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
		col_UserAge.setCellValueFactory(new PropertyValueFactory<>("UserName"));
		col_UserPassword.setCellValueFactory(new PropertyValueFactory<>("UserPassword"));
		col_UserName.setCellValueFactory(new PropertyValueFactory<>("UserAge"));
		col_UserRole.setCellValueFactory(new PropertyValueFactory<>("UserRole"));		
		
		//Mengisi tableStaff dengan data yang diberikan dari UserController
		//Error diantisipasi dengan menggunakan try - catch
		try {
			userList = FXCollections.observableArrayList(userController.GetAllStaff());	
			tableStaff.setItems(userList);
			
			container.setCenter(tableStaff);
			container.setBottom(lblNote);
			container.setAlignment(lblNote, Pos.CENTER);
			actions(primaryStage);
		}
		catch(Exception e) {
			container.setCenter(lblError);			
		}
		
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		scene = new Scene(container,900,600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("View All Staff Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}

}