package model;

import java.util.List;

public class User {
	private int UserID;
	private String UserName;
	private String UserPassword;
	private int UserAge;
	private String UserRole;
	
	public User(int userID, String userName, String userPassword, int userAge, String userRole) {
		super();
		UserID = userID;
		UserName = userName;
		UserPassword = userPassword;
		UserAge = userAge;
		UserRole = userRole;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public int getUserAge() {
		return UserAge;
	}

	public void setUserAge(int userAge) {
		UserAge = userAge;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}
}
