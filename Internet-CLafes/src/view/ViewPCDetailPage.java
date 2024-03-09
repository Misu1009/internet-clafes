package view;

import Controller.PCController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PC;

public class ViewPCDetailPage {

	//Mendeklarasikan Objek yang terdapat pada ViewPCDetailPage
	BorderPane container;
	VBox insideContainer;
	HBox pc_id_container, pc_condition_container, btnContainer;
	Label lblError, lblPC_ID, lblReal_PC_ID, lblPC_Condition, lblRealPC_Condition;
	Button btnDelete, btnUpdate;
	Scene scene;
	PC pc;
	PCController pcController;
	
	
	//Melakukan inisialiasi pada objek
	private void initiate(int pcId) {
		pcController = new PCController();
		pc = pcController.GetPcDetail(pcId);
		container = new BorderPane();
		insideContainer = new VBox();
		pc_id_container = new HBox();
		pc_condition_container = new HBox();
		btnContainer = new HBox();
		lblError = new Label("");
		lblPC_ID = new Label("PC ID : ");
		lblReal_PC_ID = new Label(String.valueOf(pc.getPC_ID()));
		lblPC_Condition = new Label("PC Condition : ");
		lblRealPC_Condition = new Label(pc.getPC_Condition());
		btnDelete = new Button("Delete");
		btnUpdate = new Button("Update");
	}
	
	//Mengisi action pada buttonDelete dan buttonUpdate
	private void actions(Stage primaryStage) {
		btnDelete.setOnMouseClicked(e->{
			new DeletePCPage(primaryStage, pc.getPC_ID());
		});
		
		btnUpdate.setOnMouseClicked(e->{
			new UpdatePCPage(primaryStage, pc.getPC_ID());
		});
	}
	
	//Constructr ViewPCDetailPage yang menerima parameter primaryStage dan pcId
	public ViewPCDetailPage(Stage primaryStage, int pcId) {
		// Melakukan inisiasi dan pengisian action
		initiate(pcId);		
		actions(primaryStage);
		
		//Mendeklarasikan navigation dan mengisi navigation pada bagian atas container
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());

		//Memasukkan seluruh button ke dalam btnContainer
		btnContainer.getChildren().addAll(btnDelete, btnUpdate);
		//Memasukkan seluruh bagian yang berhubungan dengan pc_id ke pc_id_container
		pc_id_container.getChildren().addAll(lblPC_ID, lblReal_PC_ID);
		//Memasukkan seluruh bagian yang berhubungan dengan pc_condition ke pc_condition_container
		pc_condition_container.getChildren().addAll(lblPC_Condition, lblRealPC_Condition);
		//Memaasukkan ketiga container diatas ke dalam insideContainer
		insideContainer.getChildren().addAll(pc_id_container, pc_condition_container, btnContainer);
		
		//Memasukkan insideContainer ke bagian tengah Container
		container.setCenter(insideContainer);
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		scene = new Scene(container, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("View PC Detail Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}

}