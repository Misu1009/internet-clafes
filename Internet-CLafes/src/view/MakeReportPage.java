package view;

import java.sql.Date;
import java.time.LocalDate;

import Controller.ReportController;
import Controller.UserController;
import app.UserSession;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MakeReportPage{
	
	//Deklarasi objek yang akan digunakan pada class MakeReportPage
	Navigation navigation;
	BorderPane container;
	VBox insideContainer;
	HBox PC_ID_Container;
	Label lblPC_ID, lblReal_PC_ID, lblReportNote,lblErrorMessage;
	TextField tfReportNote;
	Button button;
	ReportController reportController;
	UserController userController;
	Scene scene;
	
	//Melakukan inisiasi terhadap seluruh objek
	private void initialize(int PC_ID) {
		container = new BorderPane();
		insideContainer = new VBox();
		PC_ID_Container = new HBox();
		lblPC_ID = new Label("PC ID : ");
		lblReal_PC_ID = new Label(Integer.toString(PC_ID));
		lblReportNote = new Label("Report Note : ");
		lblErrorMessage = new Label("");
		tfReportNote = new TextField();
		button = new Button("Submit Report");
		reportController = new ReportController();
		userController = new UserController();
	}
	
	//Mengisi action pada Button di halaman Make Report
	private void actions(int pc_ID) {
		button.setOnMouseClicked(e->{
			
			String ReportNote = tfReportNote.getText().toString();
			
			String UserRole = UserSession.getInstance().getUser().getUserRole();
			
			LocalDate currDate = LocalDate.now();
					
			String message = reportController.AddNewReport(UserRole, pc_ID, ReportNote, Date.valueOf(currDate));
			lblErrorMessage.setText(message);
			
		});
	}

	//Bagian constructor yang menerima parameter berupa Stage dan pc_ID
	public MakeReportPage(Stage primaryStage, int pc_ID) {
		
		// Mendeklarasikan navigasi, menginisialisasi objek, dan mengisi action pada button
		navigation = new Navigation(primaryStage);
		initialize(pc_ID);
		actions(pc_ID);
		
		//Mengisi label yang berkaitan dengan PC ID ke PC_ID_Container
		PC_ID_Container.getChildren().addAll(lblPC_ID, lblReal_PC_ID);
		//Mengisi insideContainer dengan PC_ID_Container dan objek - objek lainnya
		insideContainer.getChildren().addAll(PC_ID_Container, lblReportNote, tfReportNote, button, lblErrorMessage);
		
		//Mengisi center dari container dengan insideContainer
		container.setCenter(insideContainer);
		//Mengisi top dari container dengan objek navigation
		container.setTop(navigation.getMenuBar());
		scene = new Scene(container, 900, 600);
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		primaryStage.setScene(scene);
		primaryStage.setTitle("Make Report Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}

}