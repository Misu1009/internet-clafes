package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Controller.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.TransactionDetail;
import model.TransactionHeader;

public class AdminTransactionHistoryPage {
	
	TransactionController transaction = new TransactionController();
	ObservableList<TransactionDetail> transactionDetailList = null;
	List<TransactionDetail> listTransactionDetail;
	
	Navigation navigation;
	Scene scene;
	BorderPane container;
	VBox AdminTransactionHistoryContainer;
	
	TableView<TransactionHeader> transactionHeaderTable;
	TableColumn<TransactionHeader, String> colTransactionHeaderId, colStaffId, colStaffName, colTransactionDate;
	VBox transactionHeaderContainer;
	
	
	ChoiceBox<Integer> cbTransactionId;
	TableView<TransactionDetail> transactionDetailTable;
	TableColumn<TransactionDetail, String> colTransactionDetailID, colPcId, colCustomerName, colBookedTime;
	VBox transactionDetailContainer;
	HBox transactionId;
	Button btnTransactionId;
	
	private void initialize() {
		container = new BorderPane();
		AdminTransactionHistoryContainer = new VBox();
		
		
		// untuk menampilkan data Transaction Header
		transactionHeaderContainer = new VBox();
		transactionHeaderTable  = new TableView<>();
		
		colTransactionHeaderId = new TableColumn<>("Transaction Header ID");
		colStaffId = new TableColumn<>("Transaction Header Staff ID");
		colStaffName = new TableColumn<>("Transaction Header Staff Name");
		colTransactionDate = new TableColumn<>("Transaction Header Date");
		
		transactionHeaderTable.getColumns().add(colTransactionHeaderId);
		transactionHeaderTable.getColumns().add(colStaffId);
		transactionHeaderTable.getColumns().add(colStaffName);
		transactionHeaderTable.getColumns().add(colTransactionDate);

		colTransactionHeaderId.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		colStaffId.setCellValueFactory(new PropertyValueFactory<>("staffID"));
		colStaffName.setCellValueFactory(new PropertyValueFactory<>("staffName"));
		colTransactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
		
		ObservableList<TransactionHeader> transactionHeaderList = null;
		List<TransactionHeader> listTransactionHeader = transaction.getAllTransactionHeaderData();
		
		
		// untuk menampilkan isi Transaction Detail
		transactionDetailContainer = new VBox();
		transactionDetailTable = new TableView<>();
		transactionId = new HBox();
		btnTransactionId = new Button("Choose Transaction ID");
		cbTransactionId = new ChoiceBox<>();
		cbTransactionId.setItems(FXCollections.observableArrayList());
		List<Integer> listTransactionId = new ArrayList<>();
		
		colTransactionDetailID = new TableColumn<>("Transaction ID");
		colPcId = new TableColumn<>("PC ID");
		colCustomerName = new TableColumn<>("Customer Name");
		colBookedTime = new TableColumn<>("Transaction Date");
		
		transactionDetailTable.getColumns().add(colTransactionDetailID);
		transactionDetailTable.getColumns().add(colPcId);
		transactionDetailTable.getColumns().add(colCustomerName);
		transactionDetailTable.getColumns().add(colBookedTime);
		
		colTransactionDetailID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		colPcId.setCellValueFactory(new PropertyValueFactory<>("pC_ID"));
		colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		colBookedTime.setCellValueFactory(new PropertyValueFactory<>("bookedTime"));
		
		
		
		// untuk mengisi data Transaction Header dan Transaction Detail
		if(listTransactionHeader != null) {
			transactionHeaderList = FXCollections.observableArrayList(listTransactionHeader);
			for (TransactionHeader transactionHeader : listTransactionHeader) {
				listTransactionId.add(transactionHeader.getTransactionID());
			}
			
		}
		cbTransactionId.setItems(FXCollections.observableArrayList(listTransactionId));
		
		transactionHeaderTable.setItems(transactionHeaderList);
		
		
	}
	
	private void actions() {
		// untuk menampilkan Transaction Detail dari Transaction Header yang dipilih
		btnTransactionId.setOnMouseClicked(event -> {
			Integer transactionID = cbTransactionId.getValue();
			
			if(transactionID != null) {
				listTransactionDetail = transaction.GetTransactionDetailById(transactionID);
				transactionDetailTable.setItems(FXCollections.observableArrayList(listTransactionDetail));
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Must Choose Transaction ID");
				alert.show();
			}
			
			
		});
	}

	public AdminTransactionHistoryPage(Stage primaryStage) {
		
		navigation = new Navigation(primaryStage);
		initialize();
		actions();
		
		transactionHeaderContainer.getChildren().addAll(transactionHeaderTable);

		transactionId.getChildren().addAll(cbTransactionId, btnTransactionId);
		transactionDetailContainer.getChildren().addAll(transactionId, transactionDetailTable);
		
		AdminTransactionHistoryContainer.getChildren().addAll(transactionHeaderContainer, transactionDetailContainer);
		
		container.setCenter(AdminTransactionHistoryContainer);
		container.setTop(navigation.getMenuBar());
		scene = new Scene(container, 900, 600);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Admin Transaction History Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}

}
