package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


import Controller.PCBookController;
import Controller.PCController;
import Controller.TransactionController;
import app.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PC;
import model.PCBook;
import model.User;

public class BookedPCPage {
	
	Integer userId = UserSession.getInstance().getUser().getUserID();
	
	PCBookController pcBook = new PCBookController();
	TransactionController transaction = new TransactionController();
	PCController pc = new PCController();
	
	Scene scene;
	Navigation navigation;
	BorderPane container;
	VBox BookedPCContainer;
	
	VBox ViewPcBookedData;
	TableView<PCBook> tablePcBook;
	TableColumn<PCBook, String> colBookId, colPcId, colUserId, colBookDate;
	
	HBox CancelBook;
	Label lblCancelId;
	ChoiceBox<Integer> cbCancelBook;
	Button cancelButton;
	
	HBox FinishBook;
	DatePicker dpFinishBook;
	Button finishButton;
	
	VBox AssignToAnotherPc;
	HBox pcBookId, newPcId;
	Label lblPcBookId, lblChooseNewPc;
	ChoiceBox<Integer> cbPcBookId, cbNewPcId;
	Button btnAssignToOtherPc;

	
	

	private void initialize() {
		
		
		container = new BorderPane();
		
		
		// Show All PC Book Data
		tablePcBook = new TableView<>();
		colBookId = new TableColumn<>("Book ID");
		colPcId = new TableColumn<>("PC ID");
		colUserId = new TableColumn<>("User ID");
		colBookDate = new TableColumn<>("Book Date");
		
		
		tablePcBook.getColumns().add(colBookId);
		tablePcBook.getColumns().add(colPcId);
		tablePcBook.getColumns().add(colUserId);
		tablePcBook.getColumns().add(colBookDate);
		
		colBookId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
		colPcId.setCellValueFactory(new PropertyValueFactory<>("pC_ID"));
		colUserId.setCellValueFactory(new PropertyValueFactory<>("userID"));
		colBookDate.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
		
		
		ObservableList<PCBook> pcBookList = null;
		List<PCBook> listPcBook = pcBook.GetAllPcBookedData();
		
		List<Integer> cancelablePcBook = new ArrayList<>();
		cbCancelBook = new ChoiceBox<>();
		cbCancelBook.setItems(FXCollections.observableArrayList());
		
		List<Integer>  pcBookIdList = new ArrayList<>();
		List<Integer> newPcIdList = new ArrayList<>();
		cbPcBookId = new ChoiceBox<>();
		cbNewPcId = new ChoiceBox<>();
		
		if(listPcBook != null) {
			pcBookList = FXCollections.observableArrayList(listPcBook);
			
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate currentDate = LocalDate.now();
			
			for (PCBook pcBook2 : listPcBook) {
				String bookdate = String.valueOf(pcBook2.getBookDate());
				LocalDate bookdate2 = LocalDate.parse(bookdate, dateFormat);
				
				if(!bookdate2.isBefore(currentDate) ) {
					cancelablePcBook.add(pcBook2.getBookID());
				}
				
				pcBookIdList.add(pcBook2.getBookID());
			}
			
			List<PC> listPC = pc.GetAllPcData();
			
			for (PC listPc : listPC) {
				if(listPc.getPC_Condition().equals("Usable")) {
					newPcIdList.add(listPc.getPC_ID());
				}
			}
			
			cbCancelBook.setItems(FXCollections.observableArrayList(cancelablePcBook));
			
			cbPcBookId.setItems(FXCollections.observableArrayList(pcBookIdList));
			
			cbNewPcId.setItems(FXCollections.observableArrayList(newPcIdList));
		}
		
		
		
		tablePcBook.setItems(pcBookList);
		ViewPcBookedData = new VBox();
		ViewPcBookedData.getChildren().addAll(tablePcBook);
		
		
		// Cancel Book
		CancelBook = new HBox();
		lblCancelId = new Label("PC Book Id");
		cancelButton = new Button("Cancel Book");
		CancelBook.getChildren().addAll(lblCancelId, cbCancelBook, cancelButton);
		
		
		// Finish Book
		FinishBook = new HBox();
		finishButton = new Button("Finish Button");
		dpFinishBook = new DatePicker();
		dpFinishBook.setValue(LocalDate.now());
		FinishBook.getChildren().addAll(dpFinishBook, finishButton);
		
		// Assign User to Another PC
		AssignToAnotherPc = new VBox();
		pcBookId = new HBox(); 
		newPcId = new HBox();
		lblPcBookId = new Label("PC Book ID");
		lblChooseNewPc = new Label("New PC ID");
		pcBookId.getChildren().addAll(lblPcBookId, cbPcBookId);
		newPcId.getChildren().addAll(lblChooseNewPc, cbNewPcId);
		btnAssignToOtherPc = new Button("Assign to other pc");
		AssignToAnotherPc.getChildren().addAll(pcBookId, newPcId, btnAssignToOtherPc);
		
		
		BookedPCContainer = new VBox();
		BookedPCContainer.getChildren().addAll(tablePcBook, CancelBook, FinishBook, AssignToAnotherPc);
	}
	
	private void actions(Stage primaryStage) {
		
		// untuk mendelete Up-Coming PC Book yang sudah dibuat
		cancelButton.setOnMouseClicked(event -> {
			Alert alert = new Alert(AlertType.ERROR);
			Integer id = cbCancelBook.getValue();
			if(id != null) {
				pcBook.DeleteBookData(id);
				new BookedPCPage(primaryStage);
				alert.setAlertType(AlertType.INFORMATION);
				alert.setHeaderText("Cancel Book Success");
				
			}
			else {
				alert.setHeaderText("Failed to Cancel Book");
			}
			alert.show();
		});
		
		// untuk menyelesaikan booking yang sudah sampai atau lewat tanggal
		finishButton.setOnMouseClicked(event -> {
			Alert alert = new Alert(AlertType.ERROR);
			LocalDate dateChosen = dpFinishBook.getValue();
			LocalDate currDate = LocalDate.now();
			if(dateChosen != null && !currDate.isBefore(dateChosen)) {
				Date date =  Date.valueOf(dateChosen);
				List<PCBook> pcBookList = pcBook.GetPcBookedByDate(date);
				if(pcBookList != null) {
					transaction.AddTransaction(pcBookList, userId);
					pcBook.FinishBook(pcBookList);
					new BookedPCPage(primaryStage);
					alert.setAlertType(AlertType.INFORMATION);
					alert.setHeaderText("Finish Book Success");
				}
				else {
					alert.setHeaderText("There Are No Book at That Day");
				}
			}
			else {
				alert.setHeaderText("Cant Finish Book Up-Coming Book");
			}
			alert.show();
		});
		
		// untuk mengantikan PC pada PC Book dengan PC yang available pada tanggal itu
		btnAssignToOtherPc.setOnMouseClicked(event -> {
			Alert alert = new Alert(AlertType.ERROR);
			Integer bookId = cbPcBookId.getValue();
			Integer newPcId = cbNewPcId.getValue();
			
			if(bookId != null && newPcId != null) {
				String bookDate =  String.valueOf(pcBook.GetPCBookedDetail(bookId).getBookDate()) ;
				Date choosenBookDate = Date.valueOf(bookDate);
				
				if(pcBook.GetPCBookData(newPcId, choosenBookDate) == null) {
					
					pcBook.AssignUserToNewPC(bookId, newPcId);
					
					new BookedPCPage(primaryStage);
					alert.setAlertType(AlertType.INFORMATION);
					alert.setHeaderText("Success Assigning Book to Other PC");
				}
				else {
					alert.setHeaderText("PC Already Booked That Day");
				}
			}
			else {
				alert.setHeaderText("Must Choose the Book ID and New PC ID");
			}
			alert.show();
		});
	}
	
	
	public BookedPCPage(Stage primaryStage) {
		navigation = new Navigation(primaryStage);
		initialize();
		actions(primaryStage);
		
		container = new BorderPane();
		container.setCenter(BookedPCContainer);
		container.setTop(navigation.getMenuBar());
		
		scene = new Scene(container, 900, 600);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Booked PC Data Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
		
	}

}
