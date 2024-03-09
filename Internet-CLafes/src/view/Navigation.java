package view;

import app.UserSession;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Navigation extends MenuBar{
	
	String role = UserSession.getInstance().getUser().getUserRole();
	
	MenuBar menuBar;
	Menu menu;
	
	//admin
	MenuItem adminTransactionHistory, viewAllStaffJob, viewAllReport, viewAllStaff;
	
	//computer technician
	MenuItem viewTechnicianJob; 
	
	//operator
	MenuItem bookedPc;
	
	//customer
	MenuItem bookPc;
	MenuItem customerTransactionHistory;
	
	//all
	MenuItem viewAllPc;
	MenuItem logout;
	
	
	private void actions(Stage primaryStage) {
		//customer
		bookPc.setOnAction(event -> new BookPCPage(primaryStage));
		customerTransactionHistory.setOnAction(event -> new CustomerTransactionHistoryPage(primaryStage));
		
		//admin
		adminTransactionHistory.setOnAction(event -> new AdminTransactionHistoryPage(primaryStage));
		viewAllStaffJob.setOnAction(event -> new ViewAllStaffJobPage(primaryStage));
		viewAllStaff.setOnAction(event -> new ViewAllStaffPage(primaryStage));
		viewAllReport.setOnAction(event -> new ViewAllReportPage(primaryStage));
		
		//operator
		bookedPc.setOnAction(event -> new BookedPCPage(primaryStage));
		
		//technician
		viewTechnicianJob.setOnAction(event -> new ViewTechnicianJobPage(primaryStage));
		
		//all
		viewAllPc.setOnAction(event -> new ViewAllPCPage(primaryStage));
		
		logout.setOnAction(event -> {
			UserSession.getInstance().setUser(null);
			new LoginPage().start(primaryStage);
		});
	}

	private void initialize() {
		menuBar = new MenuBar();
			
		menu = new Menu("Navigation");
		
		//admin
		adminTransactionHistory = new MenuItem("Admin Transaction History");
		viewAllStaffJob = new MenuItem("View All Staff Job");
		viewAllStaff = new MenuItem("View All Staff");
		viewAllReport = new MenuItem("View All Report");
		
		//operator
		bookedPc = new MenuItem("Booked PC");
		
		//technician
		viewTechnicianJob = new MenuItem("View Technician Job");
		
		//customer
		bookPc = new MenuItem("Book PC");
		customerTransactionHistory = new MenuItem("Customer Transaction History");
		
		//all
		viewAllPc = new MenuItem("View All PC");
		logout = new MenuItem("logout");
		
		
		// untuk menentukoan isi menu bar apa yang dapat diakses oleh setiap role
		if (role.equals("Admin")) { 
			menu.getItems().addAll(viewAllPc, adminTransactionHistory, viewAllStaffJob, viewAllStaff, viewAllReport, logout);
		}
		else if (role.equals("Computer Technician")) { 
			menu.getItems().addAll(viewAllPc, viewTechnicianJob, logout);
		}
		else if (role.equals("Operator")) { 
			menu.getItems().addAll(viewAllPc, bookedPc, logout);
		}
		else if (role.equals("Customer")) { 
			menu.getItems().addAll(viewAllPc, bookPc, customerTransactionHistory, logout);
		}
		
	 	
	 	
		menuBar.getMenus().addAll(menu);
	}

	    public Navigation(Stage primaryStage) {
	        initialize();
	        actions(primaryStage);
	    }

	    public MenuBar getMenuBar() {
	        return menuBar;
	    }

}
