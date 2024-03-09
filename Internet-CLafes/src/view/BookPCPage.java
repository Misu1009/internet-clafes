package view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Controller.PCBookController;
import Controller.PCController;
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

public class BookPCPage {
	
	
	
	Integer userId = UserSession.getInstance().getUser().getUserID();
	
	PCBookController bookPC = new PCBookController();
	PCController pc = new PCController();
	
	
	
	Scene scene;
	Navigation navigation;
	BorderPane container;
	VBox homeContainer;
	
	Label lblBookDate, lblPcId;
	Button btnBookPc;
	DatePicker dpBookDate;
	ChoiceBox<Integer> cbPcId;
	VBox bookPcContainer;
	HBox bookDateContainer, pcIdContainer;
	TableView<PC> tablePC;
	TableColumn<PC, String> colPC_ID, colPC_Condition;
	
	private void initialize() {
		container = new BorderPane();
		homeContainer = new VBox();
		
		// menampilkan table pc
		tablePC  = new TableView<>();;
		
		colPC_ID = new TableColumn<>("PC ID");
		
		colPC_Condition = new TableColumn<>("PC Condition");
		
		tablePC.getColumns().add(colPC_ID);
		colPC_ID.setCellValueFactory(new PropertyValueFactory<>("pC_ID"));
		
		tablePC.getColumns().add(colPC_Condition);
		colPC_Condition.setCellValueFactory(new PropertyValueFactory<>("pC_Condition"));

		ObservableList<PC> pcList = FXCollections.observableArrayList(pc.GetAllPcData());
	
		tablePC.setItems(pcList);

		
		pcList = null;
		List<PC> list=pc.GetAllPcData();
		List<Integer> pcIdList = new ArrayList<>();
		cbPcId = new ChoiceBox<>();
		cbPcId.setItems(FXCollections.observableArrayList());
		if(list != null) {
			pcList = FXCollections.observableArrayList(list);
			tablePC.setItems(pcList);
			
			
			for (PC pc1 : list) {
				if(pc1.getPC_Condition().equals("Usable")) {
					pcIdList.add(pc1.getPC_ID());
				}
			}
			cbPcId.setItems(FXCollections.observableArrayList(pcIdList));
		}
		
		// Book PC
		bookPcContainer = new VBox();
		bookDateContainer = new HBox();
		pcIdContainer = new HBox();
		
		btnBookPc = new Button("Book PC");
		lblBookDate = new Label("Book Date");
		lblPcId = new Label("PC ID");
		dpBookDate = new DatePicker();
		dpBookDate.setValue(LocalDate.now());
		
		
	}
	
	private void actions() {
		// untuk membuat PC Book pada tanggal dan pc yang sudah di-input
		btnBookPc.setOnMouseClicked(e->{
			Alert alert = new Alert(AlertType.ERROR);
			LocalDate localDate = dpBookDate.getValue();
			Integer id = cbPcId.getValue();
			if(localDate != null && id != null) {
				Date date =  Date.valueOf(localDate);
				PCBook pcBook = bookPC.GetPCBookData(id, date);
				if(pcBook == null) {
					bookPC.AddNewBook(id, userId, date);
					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText("Book Successful");
				}
				else {
					alert.setHeaderText("PC already booked that day!");
				}
			}
			else {
				alert.setHeaderText("All must be filled");
			}
			alert.show();
		});
	}

	public BookPCPage(Stage primaryStage) {

		navigation = new Navigation(primaryStage);
		initialize();
		actions();
		
		bookDateContainer.getChildren().addAll(lblPcId, cbPcId);
		pcIdContainer.getChildren().addAll(lblBookDate, dpBookDate);
		bookPcContainer.getChildren().addAll(bookDateContainer, pcIdContainer, btnBookPc);
		
		homeContainer.getChildren().addAll(tablePC, bookPcContainer);
		
		container.setCenter(homeContainer);
		container.setTop(navigation.getMenuBar());
		scene = new Scene(container, 900, 600);
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Book PC Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}
	

}
