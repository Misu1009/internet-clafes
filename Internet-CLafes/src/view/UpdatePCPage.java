package view;

import Controller.PCController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdatePCPage {

	//Mendeklarasikan semua objek yang akan digunakan pada class UpdatePCPage
	BorderPane container;
	VBox insideContainer;
	HBox pc_ID_Container;
	Label lblError, lblPC_ID, lblReal_PC_ID, lblPCCondition;
	TextField tfPC_Condition;
	Button btnUpdate;
	Scene scene;
	int pcId;
	PCController pcController;
	
	//Melakukan inisiasi terhadap objek yang digunakan pada class UpdatePCPage
	private void initiate(int pcId) {
		container = new BorderPane();
		insideContainer = new VBox();
		pc_ID_Container = new HBox();
		lblError = new Label("");
		lblPC_ID = new Label("PC ID : ");
		lblReal_PC_ID = new Label(String.valueOf(pcId));
		lblPCCondition = new Label("PC Condition : ");
		tfPC_Condition = new TextField();
		btnUpdate = new Button("Update");
		this.pcId = pcId;
		pcController = new PCController();
	}
	
	//Mengisi action pada buttonUpdate 
	private void actions(Stage primaryStage) {
		btnUpdate.setOnMouseClicked(e->{
			String condition = tfPC_Condition.getText().toString();
			String error = pcController.UpdatePcCondition(pcId, condition);
			lblError.setText(error);
			
			//Jika error message yang diterima dari Controller adalah "SUCCESS", 
			//maka artinya berhasil dan halaman akan berpindah ke ViewPCDetailPage
			if(error.equals("SUCCESS")) {
				new ViewPCDetailPage(primaryStage, pcId);
			}
		});
	}
	
	//Constructor UpdatePCPage yang menerima parameter primaryStage dan pcId
	public UpdatePCPage(Stage primaryStage, int pcId) {
		// Melakukan inisiasi dan pengisian action
		initiate(pcId);
		actions(primaryStage);
		
		//Mendeklarasikan navigasi dan meletakan di bagian atas container
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());
		
		//Memasukkan label yang berhubungan dengan PC ID ke pc_ID_Container
		pc_ID_Container.getChildren().addAll(lblPC_ID,lblReal_PC_ID);
		//Memasukkan pc_ID_Container dan objek sisanya ke dalam insideContainer
		insideContainer.getChildren().addAll(pc_ID_Container, lblPCCondition, tfPC_Condition, btnUpdate, lblError);
		
		//Memasukkan insideContainer ke bagian tengah Container
		container.setCenter(insideContainer);
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		scene = new Scene(container, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Update PC Condition Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	}

}