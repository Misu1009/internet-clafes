package view;

import Controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterPage {
	
	//Mendeklarasikan objek yang diperlukan pada RegisterPage
	BorderPane bp;
	
	Scene registerScene;
	VBox registerContainer;
	HBox accountCheckContainer, userIdContainer;
	Button btnRegister;
	Label lblUsername, lblPassword, lblUserId, lblRealUserId, lblConfirmPassword, lblUserAge, lblLoginHere, lblAccountCheck, lblErrorMessage;
	TextField tfUsername;
	PasswordField pfPassword, pfConfirmPassword;
	
	Spinner<Integer>spinnerAge;
	SpinnerValueFactory<Integer> spinnerAgeFactory;
	
	LoginPage loginPage;
	Stage stage;

	UserController userController = new UserController();
	
	//Mengisi action pada button Login dan label Register
	void actions() {
		
		//Pengisian action pada button Register
		btnRegister.setOnMouseClicked(e->{
			//On Progress
			String Username = tfUsername.getText(), Password = pfPassword.getText(), ConfirmPassword = pfConfirmPassword.getText();
			int Age = spinnerAge.getValue();
			String respond = null;

			respond = userController.AddNewUser(Username, Password, ConfirmPassword, Age);
			if(respond.equals("SUCCESS")) {
				lblErrorMessage.setText("Login is Successful! Go to Login Page");
				
				int id = userController.GetUserData(Username, Password).getUserID();
				lblRealUserId.setText(String.valueOf(id));
			}
			else {
				lblErrorMessage.setText(respond);
			}
			
		});
		
		//Jika label LoginHere diklik, maka akan berpindah ke Halaman LoginPage
		lblLoginHere.setOnMouseClicked(e->{
			loginPage.start(stage);
		});
	}
	
	//Menginisiasi objek - objek yang diperlukan pada class RegisterHere
	void init(Stage arg0) {
		registerContainer = new VBox();
		userIdContainer = new HBox();
		accountCheckContainer = new HBox();
		btnRegister = new Button("Register");
		lblUsername = new Label("Username");
		lblPassword = new Label("Password");
		lblUserId = new Label("UserID : ");
		lblRealUserId = new Label("User ID Will Show After Register");
		
		lblConfirmPassword = new Label("Confirm Password");
		lblUserAge = new Label("User Age");
		lblAccountCheck = new Label("Already have an account?");
		lblLoginHere = new Label(" Login Here");
		lblErrorMessage = new Label("");
		
		tfUsername = new TextField();
		pfPassword = new PasswordField();
		pfConfirmPassword = new PasswordField();
		
		spinnerAge = new Spinner<>();
		spinnerAgeFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(13, 65, 18);
		spinnerAge.setValueFactory(spinnerAgeFactory);
		
		loginPage = new LoginPage();
		stage = arg0;
		
		bp = new BorderPane();
		
	}

	//Bagian Utama dari Code
	public void start(Stage arg0) {
		//Melakukan inisiasi objek dan pengisian action
		init(arg0);
		actions();
		
		//Memasukkan label yang berkaitan dengan userId ke UserIdContainer
		userIdContainer.getChildren().addAll(lblUserId, lblRealUserId);
		//Memasukkan label yang berkaitan dengan accountCheck ke accountCheckContainer
		accountCheckContainer.getChildren().addAll(lblAccountCheck, lblLoginHere);
		//Memasukkan userIdContainer, accountCheckContainer, dan objek tersisa ke registerContainer
		registerContainer.getChildren().addAll(userIdContainer, lblUsername, tfUsername, lblPassword, pfPassword,
				lblConfirmPassword, pfConfirmPassword, lblUserAge, spinnerAge, btnRegister, accountCheckContainer,
				lblErrorMessage);
		//Memasukkan registerContainer ke bagian tengah Container
		bp.setCenter(registerContainer);
		registerScene = new Scene(bp);
		
		//Mengubah Scene dan mengubah atribut pada primaryStageS
		arg0.setScene(registerScene);
		arg0.setTitle("Register Page");
		arg0.setHeight(600);
		arg0.setWidth(900);
		arg0.show();
	}
	
}