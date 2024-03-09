package view;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import Controller.JobController;
import Controller.PCBookController;
import Controller.PCController;
import Controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PC;
import model.PCBook;
import model.User;
import repository.JobRepository;

public class AddStaffJobPage {
	
	//Seluruh objek yang diperlukan dalam class AddStaffJobPage
	BorderPane container;
	VBox insideContainer;
	Label lblError, lblUserId, lblPC_Id, lblDate;
	TextField tfUserId, tfPC_Id;
	Button btnAdd;
	
	PCController pcController;
	PCBookController pcBookController;
	UserController userController;
	JobController jobController;
	Scene scene;
	
	DatePicker datePicker;

	//Inisiasi Objek yang akan digunakan
	private void initialize() {
		container = new BorderPane();
		insideContainer = new VBox();
		lblError = new Label("");
		lblUserId = new Label("Input User Id : ");
		lblPC_Id = new Label("Input PC Id : ");
		lblDate = new Label("Select Date : ");
		tfUserId = new TextField();
		tfPC_Id = new TextField();
		btnAdd = new Button("Add New Job");
		pcController = new PCController();
		pcBookController = new PCBookController();
		jobController = new JobController();
		userController = new UserController();
		datePicker = new DatePicker();
	}
	
	
	//Mengisi action pada tombol Button Add
	private void actions(Stage primaryStage) {
		btnAdd.setOnMouseClicked(e->{
			
			//Menggunakan Try Catch untuk mengantisipasi input non-angka yang diberikan oleh User
			try {
				//Mengambil data dari UserId yang telah diinput oleh User 
				int userId = Integer.valueOf(tfUserId.getText().toString().trim());
				//Mengambil objek user berdasarkan data UserId
				User user = userController.GetUserData(userId);
				
				//Memastikan jika user tersebut merupakan role Computer Technician
				if(user.getUserRole().equals("Computer Technician")) {
					
					//Mengantisipasi error saat mengubah string dari textField ke Integer dengan bantuan try-catch
					try {
						//Mengubah data string menjadi integer
						int pcId = Integer.valueOf(tfPC_Id.getText().toString().trim());
						//Mengambil data PC berdasarkan PC ID
						PC pc = pcController.GetPcDetail(pcId);
						
						//Jika PC tidak ditemukan maka tampilkan error
						if(pc == null) {
							lblError.setText("PC ID  is Not Found");
						}
						else {
							
							//Mengantisipasi jika User tidak mengisi tanggal dengan try-catch
							try {
								LocalDate localDate = datePicker.getValue();
						        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
								Date date = Date.from(instant);
								
								List<PCBook>list = pcBookController.GetPcBookedByDate(pcId, null);
								
								//Jika list tidak null, maka ada teknisi lain yang sedang mengerjakan
								if(list == null) {
									pcBookController.AssignUserToNewPC(userId, pcId);
									
									//Menambahkan Job ke database dengan melewati JobController
									String error = jobController.AddNewJob(userId, pcId);
									if(error.equals("SUCCESS")) {
										new ViewAllStaffJobPage(primaryStage);
										
									}
									else {
										lblError.setText(error);	
									}
								}
								else {
									lblError.setText("There is Some Technician Doing the Job");
								}
						
							}
							catch(Exception exce) {
								lblError.setText("Invalid Date");
							}
							
						}
						
					}
					catch(Exception exc) {
						lblError.setText("Invalid PC Id");
					}
					
				}
				else {
					lblError.setText("User Role Must be Computer Technician");
				}
				
			}
			catch(Exception ex) {
				lblError.setText("Invalid User Id");
			}
		});
	}
	
	public AddStaffJobPage(Stage primaryStage) {
		// TODO Auto-generated constructor stub
		
		//Inisiasi dan mengisi Button Add dengan aksi
		initialize();
		actions(primaryStage);
		
		//Mendeklarasikan navigasi dan mengisi bagian atas container dengan objek navigasi
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());
		
		//Memasukkan semua objek yang sudah dibuat ke dalam sebuah Container yang dinamakan "insideContainer"
		insideContainer.getChildren().addAll(lblUserId, tfUserId, lblPC_Id, tfPC_Id, lblDate, datePicker, btnAdd, lblError);
		//Memasukkan insideContainer ke bagian tengah Container
		container.setCenter(insideContainer);
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		scene = new Scene(container, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Add Staff Job Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}

}