package view;

import Controller.JobController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateStaffJobPage {
	
	//Mendeklarasikan semua objek yang dibutuhkan pada class UpdateStaffJobPage
	BorderPane container;
	VBox insideContainer;
	Label lblError, lblJobId, lblJobStatus;
	TextField tfJobId;
	Button btnUpdate;
	JobController jobController;
	Spinner<String>spinnerStatus;
	SpinnerValueFactory<String>spinnerStatusFactory;
	ObservableList<String> statusList;
	Scene scene;
	
	//Melakukan inisiasi terhadap objek
	private void initiate() {
		container = new BorderPane();
		insideContainer = new VBox();
		lblError = new Label("");
		lblJobId = new Label("Job Id : ");
		lblJobStatus = new Label("New Job Status : ");
		tfJobId = new TextField();
		btnUpdate = new Button("Update");
		jobController = new JobController();
		
		
		statusList = FXCollections.observableArrayList(
	            "Complete",
	            "Uncomplete"
	    );
		spinnerStatus = new Spinner<String>();
		spinnerStatusFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(statusList);
		spinnerStatus.setValueFactory(spinnerStatusFactory);
	}
	
	//Mengisi buttonUpdate dengan action
	private void actions() {
		btnUpdate.setOnMouseClicked(e->{
			String status = spinnerStatus.getValue();
			int JobId;
			
			//Try-catch berfungsi untuk mengantisipasi pada saat mengubah angka di textfield menjadi string
			try {
				JobId = Integer.valueOf(tfJobId.getText().toString().trim());
				lblError.setText(jobController.UpdateJobStatus(JobId, status));
			}
			catch(Exception exc) {
				lblError.setText("Invalid Job Id");
			}
		});
	}
	
	//Bagian utama kode
	private void start(Stage primaryStage) {
		//Mendeklarasikan navigasi dan meletakkan di bagian atas container
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());
		
		//Memasukkan seluruh label,textfield, spinner, dan button ke dalam insideContainer
		insideContainer.getChildren().addAll(lblJobId, tfJobId, lblJobStatus, spinnerStatus, btnUpdate, lblError);
		//Memasukkan insideContainer ke bagian tengah Container
		container.setCenter(insideContainer);
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		scene = new Scene(container, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Update Staff Job Status Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}
	
	/*
	 * Karena halaman ChangeRole merupakan extend dari ViewAllStaffJob
	 * Maka User dapat diperbolehkan untuk mengakses melalui ViewAllStaffJob ataupun mengakses manual
	 * 
	 * Namun jika mengakses melalui ViewAllStaffJob, maka id yang dipilih di page ViewAllStaffJob akan terbawa ke halaman ini.
	 */
	
	//Halaman Jika User mengakses manual
	public UpdateStaffJobPage(Stage primaryStage) {
		initiate();
		actions();
		start(primaryStage);
	}

	//Halaman jika User mengakses dari ViewAllStaffJob
	public UpdateStaffJobPage(Stage primaryStage, int JobId) {
		// TODO Auto-generated constructor stub
		initiate();
		actions();
		tfJobId.setText(String.valueOf(JobId));
		start(primaryStage);
	}
	
}