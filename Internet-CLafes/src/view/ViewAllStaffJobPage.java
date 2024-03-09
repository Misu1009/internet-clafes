package view;

import Controller.JobController;
import Controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Job;
import model.User;

public class ViewAllStaffJobPage {

	//Mendeklarasikan semua objek yang dibutuhkan pada class ViewAllStaffJobPage
	BorderPane container;
	VBox bottomContainer;
	HBox updateContainer;
	Label lblError, lblJobId, lblNoJob;
	TextField tfJobId;
	Button btnUpdate, btnAdd;
	JobController jobController;
	TableView<Job>tableStaffJob;
	TableColumn<Job,Integer> col_JobID, col_UserID, col_PC_ID;
	TableColumn<Job,String> col_JobStatus;
	Scene scene;
	
	//Melakukan inisialiasi pada objek
	private void initialize() {
		container = new BorderPane();
		bottomContainer = new VBox();
		updateContainer = new HBox();
		lblError = new Label("");
		lblJobId = new Label("Input Job Id : ");
		lblNoJob = new Label("No Job");
		btnUpdate = new Button("Update Job");
		btnAdd = new Button("Add New Job");
		jobController = new JobController();
		tableStaffJob = new TableView<>();
		tfJobId = new TextField();
		
		//Mengisi struktur pada tabel
		col_JobID = new TableColumn<>("Job ID");
		col_UserID = new TableColumn<>("User ID");
		col_PC_ID = new TableColumn<>("PC ID");
		col_JobStatus = new TableColumn<>("Job Status");
		
		col_JobID.setCellValueFactory(new PropertyValueFactory<>("JobID"));
		col_UserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
		col_PC_ID.setCellValueFactory(new PropertyValueFactory<>("PC_ID"));
		col_JobStatus.setCellValueFactory(new PropertyValueFactory<>("JobStatus"));
		
		tableStaffJob.getColumns().add(col_JobID);
		tableStaffJob.getColumns().add(col_UserID);
		tableStaffJob.getColumns().add(col_PC_ID);
		tableStaffJob.getColumns().add(col_JobStatus);		
	}
	
	//Mengisi action pada button Add dan button Update
	private void actions(Stage primaryStage) {
		//Mengisi action pada button Add
		btnAdd.setOnMouseClicked(e->{
			new AddStaffJobPage(primaryStage);
		});
		
		//Mengisi action pada button Update
		btnUpdate.setOnMouseClicked(e->{
			int JobId;
			//Mengambil JobId dari TextField
			//Mengantisipasi kegagalan dalam mengubah integer ke string, yaitu dengan membuat try catch
			try {
				JobId = Integer.valueOf(tfJobId.getText().toString().trim());
				new UpdateStaffJobPage(primaryStage, JobId);
				lblError.setText("");
			}
			catch(Exception exc) {
				lblError.setText("Invalid Id");
			}
		});
	}
	
	public ViewAllStaffJobPage(Stage primaryStage) {
		// Melakukan inisialisasi dan pengisian action
		initialize();
		actions(primaryStage);
		
		//Mengisi navigasi dan meletakkan navigasi di bagian atas container
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());
		
		//Memasukkan objek yang berkaitan dengan update ke updateContainer
		updateContainer.getChildren().addAll(lblJobId, tfJobId, btnUpdate);
		//Mengisi updateContainer dan objek-objek terkait ke bottomContainer
		bottomContainer.getChildren().addAll(btnAdd, updateContainer, lblError);
		
		//Mengambil data jobList dari jobController dan mengisi ke dalam tabel
		try {
			ObservableList<Job> jobList = FXCollections.observableArrayList(jobController.GetAllJobData());
			tableStaffJob.setItems(jobList);
			//Jika berhasil maka bagian center akan diisi dengan tabel 
			container.setCenter(tableStaffJob); 
		}
		catch(Exception e) {
			//Jika gagal maka bagian center diisi oleh No Job 
			container.setCenter(lblNoJob);  
		}
		container.setBottom(bottomContainer);
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		scene = new Scene(container,900,600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("View All Staff Job Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}
}