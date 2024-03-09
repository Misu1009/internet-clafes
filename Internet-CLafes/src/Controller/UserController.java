package Controller;

import java.util.List;


import model.User;
import repository.UserRepository;

public class UserController {
	// untuk mengembalikam semua User
	public List<User> GetAllUserData() {	// NULLABLE
		return UserRepository.GetAllUserData();
	}
	
	// untuk mengembalikan semua User yang bukan Customer
	public List<User> GetAllStaff(){
        return UserRepository.GetAllStaff();
    }
	
	// untuk mengambil User dengan username dan password
	public User GetUserData(String username, String password) {	 // NULLABLE
		return UserRepository.GetUserData(username, password);	
	}
	
	// untuk mengambil User dengan ID yang dipilih
	public User GetUserData(int userId) {
		return UserRepository.GetUserData(userId);
	}
	
	// untuk menambahkan User dengan data yang dimasukan melalui register sebagai Customer dengan logic register pada soal
	public String AddNewUser(String username, String password, String confirmPassword, int age) {
		if(username.equals("") || password.equals("") || confirmPassword.equals("") || Integer.toString(age).equals(null)) {
			return "DATA CANNOT BE EMPTY";		
		}
		User user = UserRepository.GetUserData(username);
		int idx = 0;
		// USERNAME
		if(user != null) {
			return "USERNAME IS ALREADY EXIST";
		}
		if(username.length() <7) {
			return "PASSWORD MINIMAL 7 CHARACTER";
		}
		//PASSWORD
		for(char c: password.toCharArray()) {
			if(Character.isLetterOrDigit(c)) {
				idx++;
				break;
			}
		}
		if(idx != 1) {
			return "PASSWORD MUST CONTAINS ALPHANUMERIC";
		}
		if(password.length() <6) {
			return "PASSWORD MINIMAL 7 CHARACTER";
		}
		
		//CONFIRM PASSWORD
		if(!confirmPassword.equals(password)) {
			return "PASSWORD AND CONFIRM PASSWORD IS DIFFERENT";
		}
		
		//AGE
		if(age<13 || age>65) {
			return "AGE MUST BE BETWEN 13-65";
		}
		UserRepository.AddNewUser(username, password, age);;
		return "SUCCESS";
	}
	
	// untuk mengganti Role User yang bukan Customer
	public boolean ChangedUserRole(int userId, String newRole) {
		User user = UserRepository.GetUserData(userId); // GET USER DATA WITH ID
		if(user == null) {
			return false;
		}
		if(!newRole.equals("Admin") && !newRole.equals("Computer Technician") && !newRole.equals("Operator"))
			return false;
		UserRepository.ChangeUserRole(userId, newRole); // UPDATE ROLE WITH ID
		return true;
	}
	
	// Untuk mengembalikan semua User yang memiliki Role Computer Technician
	public List<User> GetAllTechnician() {	// NULLABLE
		return  UserRepository.GetAllTechnician();
	}
	
	
}
