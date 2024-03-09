package app;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;
import repository.ReportRepository;
import repository.UserRepository;
import view.AddPCPage;
import view.BookPCPage;
import view.CustomerTransactionHistoryPage;
import view.DeletePCPage;
import view.LoginPage;
import view.MakeReportPage;
import view.UpdatePCPage;
import view.ViewAllReportPage;
import view.ViewAllStaffJobPage;
import view.ViewPCDetailPage;
import view.ViewTechnicianJobPage;

public class Main extends Application{
	
	/*
	Class pusat dalam project Internet CLafes
	 */
	
	Scene scene;
	
	LoginPage loginPage;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Halaman pertama yang akan dibuka oleh User adalah Login Page
		new LoginPage().start(primaryStage);
	}
	

}