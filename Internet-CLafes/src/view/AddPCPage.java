package view;

import Controller.PCController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddPCPage {
	
	//Objek yang digunakan dalam Class ini
	BorderPane container;
	VBox insideContainer;
	Label lblError, lblPC_ID;
	TextField tfPC_ID;
	Button button;
	Scene scene;
	PCController pcController;
	
	private void initiate() {
		//Melakukan inisiasi objek
		container = new BorderPane();
		insideContainer = new VBox();
		lblError = new Label("");
		lblPC_ID = new Label("PC ID : ");
		tfPC_ID = new TextField();
		button = new Button("Add");
		pcController = new PCController();
	}
	
	private void actions() {
		//Mengisi actions pada button
		button.setOnMouseClicked(e->{
			//Mengambil PC ID dalam bentuk String untuk dikirimkan ke Controller
			String PC_ID = tfPC_ID.getText().trim();
			lblError.setText(pcController.AddNewPc(PC_ID));
		});
	}
	
	public AddPCPage(Stage primaryStage) {
		// TODO Auto-generated constructor stub
		
		//Memanggil inisiasi dan action terlebih dahulu
		initiate();
		actions();
		
		//Memasang navigasi pada bagian top container
		Navigation navigation = new Navigation(primaryStage);
		container.setTop(navigation.getMenuBar());
		
		//Memasukkan semua elemen yang sudah diiniasikan kedalam sebuah container yang dinamakan "insideContainer"
		//insideContainer akan dimasukkan ke dalam bagian Center dari Container
		insideContainer.getChildren().addAll(lblPC_ID, tfPC_ID, button, lblError);
		container.setCenter(insideContainer);
		scene = new Scene(container, 900, 600);
		
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		primaryStage.setScene(scene);
		primaryStage.setTitle("Add PC Page");
		primaryStage.setHeight(600);
		primaryStage.setWidth(900);
		primaryStage.show();
	
	}

}
