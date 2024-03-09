package view;

import java.util.ArrayList;
import java.util.List;

import Controller.PCBookController;
import Controller.PCController;
import app.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PC;

public class ViewAllPCPage {
	
	String userRole = UserSession.getInstance().getUser().getUserRole();
	
	PCBookController bookPC = new PCBookController();
	PCController pc = new PCController();
	
	Scene scene;
	Navigation navigation;
	BorderPane container;
	VBox homeContainer;
	
	VBox buttonContainer;
	Button btnAddPc, btnBookPc, btnMakeReport, btnBookedPc;
	
	TableView<PC> tablePC;
	TableColumn<PC, String> colPC_ID, colPC_Condition;
	
	//Tambahan dari Arrick
	VBox makeReportContainer, viewPCDetail_Container;
	Label lblInputPC_Id, lblInputPC_Id2;
	TextField tfPC_Id, tfPC_Id2;
	Button btnViewPCDetail;

	private void initialize() {		
		container = new BorderPane();
		homeContainer = new VBox();
		
		btnAddPc = new Button("Add PC");
		btnBookPc = new Button("Book PC");
		btnMakeReport = new Button("Make Report");
		btnBookedPc = new Button("Booked PC");
		
		
		tablePC  = new TableView<>();;
		
		colPC_ID = new TableColumn<>("PC ID");
		
		colPC_Condition = new TableColumn<>("PC Condition");
		
		tablePC.getColumns().add(colPC_ID);
		colPC_ID.setCellValueFactory(new PropertyValueFactory<>("pC_ID"));
		
		tablePC.getColumns().add(colPC_Condition);
		colPC_Condition.setCellValueFactory(new PropertyValueFactory<>("pC_Condition"));

		List<PC> list=pc.GetAllPcData();
		ObservableList<PC> pcList = null;

		
		//TAMBAHAN dari Arrick
		makeReportContainer = new VBox();
		lblInputPC_Id = new Label("Input PC ID For Report : ");
		tfPC_Id = new TextField();
		makeReportContainer.getChildren().addAll(lblInputPC_Id, tfPC_Id,btnMakeReport);
		
		//Tambahan dari Arrick 2
		viewPCDetail_Container = new VBox();
		btnViewPCDetail = new Button("View PC Detail");
		lblInputPC_Id2 = new Label("Input PC ID For Report : ");
		tfPC_Id2 = new TextField();
		viewPCDetail_Container.getChildren().addAll(lblInputPC_Id2, tfPC_Id2, btnViewPCDetail);
		
		if(list != null) {
			pcList = FXCollections.observableArrayList(list);
			tablePC.setItems(pcList);
		}
		
		buttonContainer = new VBox();
		if(userRole.equals("Admin")) {
			buttonContainer.getChildren().addAll(btnAddPc, viewPCDetail_Container);
		}
		else if(userRole.equals("Operator")) {
			buttonContainer.getChildren().addAll(btnBookedPc, makeReportContainer);
		}
		else if(userRole.equals("Customer")) {
			buttonContainer.getChildren().addAll(btnBookPc, makeReportContainer);
		}
	}
	
	private void actions(Stage primaryStage) {
		btnAddPc.setOnMouseClicked(event -> new AddPCPage(primaryStage));
		
		btnBookPc.setOnMouseClicked(event -> new BookPCPage(primaryStage));
		
		
		btnMakeReport.setOnMouseClicked(event->{
			try {
				int pc_id = Integer.valueOf(tfPC_Id.getText().toString().trim());
				new MakeReportPage(primaryStage, pc_id);
			}
			catch(Exception e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Input Error Alert");
				alert.setHeaderText(null);
				alert.setContentText("Invalid Input for PC ID");
				alert.showAndWait();
			}			
		});
		
		btnBookedPc.setOnMouseClicked(event -> new BookedPCPage(primaryStage));
		
		btnViewPCDetail.setOnMouseClicked(event->{
			try {
				int pc_id = Integer.valueOf(tfPC_Id2.getText().toString().trim());
				new ViewPCDetailPage(primaryStage, pc_id);
			}
			catch(Exception e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Input Error Alert");
				alert.setHeaderText(null);
				alert.setContentText("Invalid Input for PC ID");
				alert.showAndWait();
			}			
		});
	}
	
	public ViewAllPCPage(Stage primaryStage) {
		navigation = new Navigation(primaryStage);
		initialize();
		actions(primaryStage);
		
		homeContainer.getChildren().addAll(tablePC, buttonContainer);
		
		container.setCenter(homeContainer);
		container.setTop(navigation.getMenuBar());
		scene = new Scene(container, 900, 600);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("View All PC Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}

}