package view;

import java.util.Date;
import java.util.List;

import Controller.TransactionController;
import app.Main;
import app.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.TransactionDetail;

public class CustomerTransactionHistoryPage{
	
	String userName = UserSession.getInstance().getUser().getUserName();
	TransactionController transaction = new TransactionController();
	
	Navigation navigation;
	Scene scene;
	BorderPane container;
	VBox CustomerTransactionHistoryContainer;
	
	TableView<TransactionDetail> transactionTable;
	TableColumn<TransactionDetail, String> colTransaction_ID, colPC_ID, colCustomer_Name, colBooked_Time;
	
	private void initialize() {
		container = new BorderPane();
		
		// memperlihatkan table transaction detail milik customer
		CustomerTransactionHistoryContainer = new VBox();
		transactionTable  = new TableView<>();
		
		colTransaction_ID = new TableColumn<>("Transaction ID");
		colPC_ID = new TableColumn<>("PC ID");
		colCustomer_Name= new TableColumn<>("Customer Name");
		colBooked_Time= new TableColumn<>("Booked Time");

		transactionTable.getColumns().add(colTransaction_ID);
		transactionTable.getColumns().add(colPC_ID);
		transactionTable.getColumns().add(colCustomer_Name);
		transactionTable.getColumns().add(colBooked_Time);		
		
		colTransaction_ID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		colPC_ID.setCellValueFactory(new PropertyValueFactory<>("pC_ID"));
		colCustomer_Name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		colBooked_Time.setCellValueFactory(new PropertyValueFactory<>("bookedTime"));
	
		ObservableList<TransactionDetail> transactionList = null;
		List<TransactionDetail> list = transaction.getUserTransactionDetail(userName); 
		if(list != null) {
			transactionList = FXCollections.observableArrayList(list);
		}
		
		transactionTable.setItems(transactionList);
		
	}

	public CustomerTransactionHistoryPage(Stage primaryStage) {
		
		navigation = new Navigation(primaryStage);
		initialize();
		
		
		CustomerTransactionHistoryContainer.getChildren().addAll(transactionTable);
		container.setCenter(CustomerTransactionHistoryContainer);
		container.setTop(navigation.getMenuBar());
		scene = new Scene(container, 900, 600);
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Customer Transaction History Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}

}
