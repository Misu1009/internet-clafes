package view;

import Controller.PCController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeletePCPage {

	//Semua objek yang dibutuhkan dalam class DeletePC
	BorderPane container;
	VBox insideContainer;
	HBox PC_ID_Container;
	Label lblError, lblPC_ID, lblRealPC_ID, lblQuestion;
	Button btnDelete;
	int pcID;
	PCController pcController;
	Scene scene;
	
	//Melakukan inisiasi terhadap seluruh Objek yang dibutuhkan
	private void initiate(int pcID) {
		container = new BorderPane();
		insideContainer = new VBox();
		PC_ID_Container = new HBox();
		lblError = new Label("");
		lblPC_ID = new Label("PC ID : ");
		lblRealPC_ID = new Label(String.valueOf(pcID));
		lblQuestion = new Label("Are you sure want to delete this PC?");
		btnDelete = new Button("Delete");
		pcController = new PCController();
		this.pcID = pcID;
	}
	
	//Mengisi action pada Button Delete
	private void actions(Stage primaryStage) {
		btnDelete.setOnMouseClicked(e->{
			pcController.DeletePc(pcID);
			new ViewAllPCPage(primaryStage);
		});
	}
	
	//Constructor delete PC
	public DeletePCPage(Stage primaryStage, int pcID) {
		// Melakukan insiasi dan mengisi action terlebih dahulu
		initiate(pcID);
		actions(primaryStage);
		
		//Membuat navigasi dan memasukkan navigasi ke bagian atas Container
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());
		
		//Memasukkan objek yang berkaitan dengan PC ID ke dalam PC_ID_Container
		PC_ID_Container.getChildren().addAll(lblPC_ID, lblRealPC_ID);
		//Memasukkan PC_ID_Container beserta objek sisanya ke dalam insideContainer
		insideContainer.getChildren().addAll(PC_ID_Container, lblQuestion, btnDelete, lblError);
		
		//Memasukakn semua objek yang sudah digabungkan ke dalam insideContainer ke bagian center Container
		container.setCenter(insideContainer);
		scene = new Scene(container, 900, 600);
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		primaryStage.setScene(scene);
		primaryStage.setTitle("Delete PC Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}

}