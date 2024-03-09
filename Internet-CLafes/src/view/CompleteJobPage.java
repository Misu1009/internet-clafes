package view;

import Controller.JobController;
import Controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Job;

public class CompleteJobPage {

	BorderPane container;
	
	Label lblListJob;
	
	VBox insideContainer;
	Button btnComplete;
	
	JobController jobController;
	UserController userController;
	
	Spinner<Job>spinnerJob;
	SpinnerValueFactory<Job>spinnerFactoryJob;
	
	Scene scene;
	
	
	private void initialize() {
		container = new BorderPane();
		lblListJob = new Label("Choosen Job : ");
		insideContainer = new VBox();
		btnComplete = new Button("Complete Job");
		jobController = new JobController();
		spinnerJob = new Spinner<>();
		
	}
	
	
	
	public CompleteJobPage(Stage primaryStage) {
		// TODO Auto-generated constructor stub
		initialize();
		
		
	}

}
