package view;

import java.util.Date;

import Controller.ReportController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Report;

public class ViewAllReportPage{
	
	//Mendeklarasikan objek yang dibutuhkan di ViewAllReportPage
	BorderPane container;
	Label label;
	TableView<Report> tableReport;
	TableColumn<Report,String> col_UserRole, col_ReportNote;
	TableColumn<Report,Integer> col_ReportID, col_PC_ID;
	TableColumn<Report,Date> col_ReportDate;
	ReportController report;
	Scene scene;

	//Melakukan inisiasi terhadap objek yang digunakan dalam ViewAllReportPage
	private void initialize() {
		container = new BorderPane();
		label = new Label("No Report");	
		report = new ReportController();
		
		tableReport = new TableView<>();
		
		col_UserRole = new TableColumn<>("User Role");
		col_ReportNote = new TableColumn<>("Report Note");
		col_ReportID = new TableColumn<>("Report ID");
		col_PC_ID = new TableColumn<>("PC ID");
		col_ReportDate = new TableColumn<>("Report Date");
	}

	//Bagian utama kode yang menerima input berupa primaryStage
	public ViewAllReportPage(Stage primaryStage) {
		// Inisialisasi code
		initialize();
		
		//Mendeklarasikan navigasi dan mengisi top container dengan navigasi
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());

		//Membuat struktur pada table
		tableReport.getColumns().add(col_ReportID);
		tableReport.getColumns().add(col_UserRole);
		tableReport.getColumns().add(col_PC_ID);
		tableReport.getColumns().add(col_ReportNote);
		tableReport.getColumns().add(col_ReportDate);
		
		col_UserRole.setCellValueFactory(new PropertyValueFactory<>("UserRole"));
		col_ReportNote.setCellValueFactory(new PropertyValueFactory<>("ReportNote"));
		col_ReportID.setCellValueFactory(new PropertyValueFactory<>("ReportID"));
		col_PC_ID.setCellValueFactory(new PropertyValueFactory<>("PC_ID"));
		col_ReportDate.setCellValueFactory(new PropertyValueFactory<>("ReportDate"));
		
		//Mengambil data dari ReportController dan mengisikan ke dalam tabel
		//Jika tidak terdapat data, maka akan ditampel label yang berisikan "No Report" di bagian tengah tabel
		try {
			ObservableList<Report> reportList = FXCollections.observableArrayList(report.GetAllReportData());
			tableReport.setItems(reportList);
			container.setCenter(tableReport);				
		}
		catch(Exception e) {
			container.setCenter(label);			
		}

		//Mengubah Scene dan mengubah atribut pada primaryStage
		scene = new Scene(container,900,600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("View All Report Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}

}
