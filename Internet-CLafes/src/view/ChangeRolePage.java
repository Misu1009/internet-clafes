package view;

import Controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeRolePage {
	
	//Seluruh Object yang digunakan pada class ChangeRolePage
	BorderPane container;
	VBox insideContainer;
	Label lblError, lblUserID, lblUserRole;
	TextField tfUserID;
	Spinner<String>spinnerRole;
	SpinnerValueFactory<String>spinnerFactoryRole;
	ObservableList<String> roleList;
	Button btnSubmit;
	UserController userController;
	Scene scene;
	
	//Melakukan inisiasi terhadap Objek yang akan digunakan
	private void initialize() {
		
		container = new BorderPane();
		insideContainer = new VBox();
		
		lblError = new Label("");
		lblUserID = new Label("User ID :");
		lblUserRole = new Label("User Role :");
		tfUserID = new TextField();
		
		roleList = FXCollections.observableArrayList(
	            "Admin",
	            "Operator",
	            "Computer Technician"
	    );
		
		spinnerRole = new Spinner<>();
		spinnerFactoryRole = new SpinnerValueFactory.ListSpinnerValueFactory<String>(roleList);
		spinnerRole.setValueFactory(spinnerFactoryRole);
		btnSubmit = new Button("Submit");
		
		userController = new UserController();
	}
	
	//Memasukkan data actions ke dalam button
	private void actions() {
		btnSubmit.setOnMouseClicked(e->{
			String UserRole = spinnerRole.getValue();
			int UserId;
			
			try {
				UserId=Integer.valueOf(tfUserID.getText().toString());
			}
			catch(Exception ex) {
				UserId=-1;
			}
			
			if(userController.ChangedUserRole(UserId, UserRole)) {
				lblError.setText("SUCCESS");
			}
			else {
				lblError.setText("Failed to Change Role");
			}
		});
	}
	
	//Bagian utama yang menjalankan mayoritas kode
	private void start(Stage primaryStage) {
		//Memasukkan semua objek ke dalam insideContainer
		//Setelah itu, dilanjutkan dengan memasukkan insideContainer ke bagian Center dari Container
		insideContainer.getChildren().addAll(lblUserID, tfUserID, lblUserRole, spinnerRole, btnSubmit, lblError);
		container.setCenter(insideContainer);
		actions();
		
		//Membuat navigasi dan memasukkan navigasi ke bagian atas Container
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		scene = new Scene(container, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Change Role Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}
	
	
	/*
	 * Karena halaman ChangeRole merupakan extend dari ViewAllStaff
	 * Maka User dapat diperbolehkan untuk mengakses melalui ViewAllStaff ataupun mengakses manual
	 * 
	 * Namun jika mengakses melalui ViewAllStaff, maka id yang dipilih di page ViewAllStaff akan terbawa ke halaman ini.
	 */
	
	//Halaman Jika User mengakses dari ViewAllStaff
	public ChangeRolePage(Stage primaryStage, int userId) {
		initialize();
		tfUserID.setText(String.valueOf(userId));
		start(primaryStage);
	}

	//Halaman Jika User mengakses secara mandiri
	public ChangeRolePage(Stage primaryStage) {
		// TODO Auto-generated constructor stub		
		initialize();
		start(primaryStage);
	}

}