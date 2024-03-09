package view;

import Controller.UserController;
import app.UserSession;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class LoginPage{
	
	//Mendeklarasikan seluruh objek yang diperlukan dalam class LoginPage
	Scene loginScene;
	VBox loginContainer;
	HBox accountCheckContainer;
	Button btnLogin;
	Label lblUsername, lblPassword,lblRegisterHere, lblAccountCheck, lblErrorMessage;
	TextField tfUsername;
	PasswordField pfPassword;
	RegisterPage registerPage;
	Stage stage;
	UserController userController;
	
	//Mengisi action pada button Login dan lblRegisterHere
	void actions(Stage primaryStage) {
		//Pengisian action pada button Login
		btnLogin.setOnMouseClicked(e->{
			
			//Mengambil data username dan password. Lalu mencari data user yang relevan
			String Username = tfUsername.getText(), Password = pfPassword.getText();
			User user = userController.GetUserData(Username, Password);
			
			//Jika tidak ada user alias user==null berarti Login Fail
			if(user != null) {
				//Jika berhasil maka data User akan dimasukkan ke dalam class singleton yaitu UserSession
				UserSession.getInstance().setUser(user);
				//Lalu akan otomatis akan masuk ke halaman ViewAllPCPage
				new ViewAllPCPage(primaryStage);
			}
			else {
				lblErrorMessage.setText("Login Fail");
			}
			
		});
		
		//Jika label RegisterHere diklik, maka akan berpindah ke halaman Register
		lblRegisterHere.setOnMouseClicked(e->{
			registerPage.start(this.stage);
		});
	}
	
	//Melakukan inisiasi terhadap semua objek yang diperlukan dalam class
	void init(Stage arg0) {
		loginContainer = new VBox();
		accountCheckContainer = new HBox();
		btnLogin = new Button("Login");
		lblUsername = new Label("Username");
		lblPassword = new Label("Password");
		lblAccountCheck = new Label("Don't have an account?");
		lblRegisterHere = new Label(" Register Here");
		lblErrorMessage = new Label("");
		tfUsername = new TextField();
		pfPassword = new PasswordField();
		registerPage = new RegisterPage();
		userController = new UserController();
		stage = arg0;
	}

	public void start(Stage arg0) {
		// Melakukan insiasi dan pengisian action
		init(arg0);
		actions(arg0);
		
		//Memasukkan label yang berkaitan dengan account check ke dalam accountCheckContainer
		accountCheckContainer.getChildren().addAll(lblAccountCheck, lblRegisterHere);
		
		//Mengisi LoginContainer dengan objek - objek yang lain
		loginContainer.getChildren().addAll(lblUsername, tfUsername, lblPassword, pfPassword, btnLogin, accountCheckContainer, lblErrorMessage);
		loginScene = new Scene(loginContainer);
		
		//Mengubah Scene dan mengubah atribut pada primaryStage
		arg0.setScene(loginScene);
		arg0.setTitle("Login Page");
		arg0.setHeight(600);
		arg0.setWidth(900);
		arg0.show();
		
	}
}