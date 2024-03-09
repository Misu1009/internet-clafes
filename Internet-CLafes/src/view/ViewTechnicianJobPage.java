package view;

import java.util.ArrayList;
import java.util.List;

import Controller.JobController;
import Controller.ReportController;
import app.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Job;
import model.Report;

public class ViewTechnicianJobPage {

	//Mendeklarasikan semua objek yang terdapat pada ViewTechnicianJobPage
	BorderPane container;
	Label label, lblJobId, lblNewJobStatus, lblError;
	TextField tfJobId;
	Button btnUpdateStatus;
	TableView<Job>tableJob;
	TableColumn<Job,Integer> col_JobID, col_UserID, col_PC_ID;
	TableColumn<Job,String> col_JobStatus;
	JobController jobController;
	VBox bottomContainer;
	Spinner<String>spinnerStatus;
	SpinnerValueFactory<String>spinnerStatusFactory;
	ObservableList<String> statusList;
	
	List<Integer> jobIdList;
	Scene scene;
	
	//Melakukan inisialiasi pada objek
	private void initialize() {
		container = new BorderPane();
		label = new Label("No Technician Job");	
		jobController = new JobController();
		
		jobIdList = new ArrayList<>();
		
		tableJob = new TableView<>();
		
		col_JobID = new TableColumn<>("Job ID");
		col_UserID = new TableColumn<>("User ID");
		col_PC_ID = new TableColumn<>("PC ID");
		col_JobStatus = new TableColumn<>("Job Status");
		
		statusList = FXCollections.observableArrayList(
	            "Complete",
	            "Uncomplete"
	    );
		spinnerStatus = new Spinner<String>();
		spinnerStatusFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(statusList);
		spinnerStatus.setValueFactory(spinnerStatusFactory);
		
		bottomContainer = new VBox();
		btnUpdateStatus = new Button("Update");
		lblJobId = new Label("Input JobID that You want to Update : ");
		tfJobId = new TextField();
		lblError = new Label("");
		lblNewJobStatus = new Label("New Job Status : ");
		bottomContainer.getChildren().addAll(lblJobId, tfJobId, lblNewJobStatus, spinnerStatus, btnUpdateStatus, lblError);
	}
	
	//Mengisi action pada buttonDelete dan buttonUpdate
	private void actions(Stage primaryStage) {
		btnUpdateStatus.setOnMouseClicked(e->{
			String status = spinnerStatus.getValue();
			int JobId;
			//Mengantisipasi terjadinya kegagalan pada saat mengubah angka dari textfield ke integer
			//Kegagalan diantisipasi melalui try-catch
			try {
				JobId = Integer.valueOf(tfJobId.getText().toString().trim());
				
				if(jobIdList.contains(JobId)) {
					lblError.setText(jobController.UpdateJobStatus(JobId, status));
					new ViewTechnicianJobPage(primaryStage);	
				}
				else {
					lblError.setText("Job ID is not In List");
				}
				
			}
			catch(Exception exc) {
				//Jika gagal, maka akan masuk ke bagian ini dan segera dimunculkan alert
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Input Error Alert");
				alert.setHeaderText(null);
				alert.setContentText("Invalid Input for PC ID");
				alert.showAndWait();
			}
		});
	}
	
	//Bagian utama kode berupa constructor yang menerima parameter berupa Stage
	public ViewTechnicianJobPage(Stage primaryStage) {
		// Inisialisasi dan pengisian action
		initialize();
		actions(primaryStage);
		
		//Membuat navigasi dan meletakkan navigasi di bagian atas Container
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());
	
		//Membuat struktur untuk tableJob
		col_JobID.setCellValueFactory(new PropertyValueFactory<>("JobID"));
		col_UserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
		col_PC_ID.setCellValueFactory(new PropertyValueFactory<>("PC_ID"));
		col_JobStatus.setCellValueFactory(new PropertyValueFactory<>("JobStatus"));
		
		tableJob.getColumns().add(col_JobID);
		tableJob.getColumns().add(col_UserID);
		tableJob.getColumns().add(col_PC_ID);
		tableJob.getColumns().add(col_JobStatus);		
		
		//Mengambil UserId yang disimpan di dalam class Singleton yaitu UserSession
		int UserId = UserSession.getInstance().getUser().getUserID(); 
		
		//Mengisi data yang berasal dari jobController ke dalam tabel
		//Mengantisipasi terjadinya error melalui antisipasi di try-catch
		try {
			ObservableList<Job> jobList = FXCollections.observableArrayList(jobController.GetTechnicianJob(UserId));
			List<Job> listJob = jobController.GetTechnicianJob(UserId);
			for(Job i : listJob) {
				jobIdList.add(i.getJobID());
			}
			
			tableJob.setItems(jobList);
			container.setCenter(tableJob);	
			container.setBottom(bottomContainer);
		}
		catch(Exception e) {
			container.setCenter(label);		
			container.setBottom(null);

		}
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		scene = new Scene(container,900,600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("View Technician Job Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();

	}

}