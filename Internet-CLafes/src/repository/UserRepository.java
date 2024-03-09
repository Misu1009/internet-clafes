package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.User;

public class UserRepository {
	private static Connect db = Connect.getInstance();
	private static ResultSet rs;
	
	// untuk mengambil semua data User dari DB
	public static List<User> GetAllUserData(){
		List<User> users = new ArrayList<>();
		
		String statement = "SELECT * FROM `user`";					// QUERY SELECT *
		rs = db.selectData(statement);
		User user;
		
		try {
			while(rs.next()) {
				user = new User(
							rs.getInt("id"), 
							rs.getString("name"), 
							rs.getString("password"), 
							rs.getInt("age"), 
							rs.getString("role")
						);
				users.add(user);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		if(users.isEmpty()) {
			return null;
		}
		
		return users;
	}
	
	// untuk mengambil semua data User yang bukan Customer dari DB
	public static List<User> GetAllStaff(){
        List<User> staffs = new ArrayList<>();

        String statement = "SELECT * FROM user WHERE role != 'Customer'";    // QUERY SELECT * WHERE ROLE 
        rs = db.selectData(statement);
        User user;

        try {
            while(rs.next()) {
                user = new User(
                            rs.getInt("id"), 
                            rs.getString("name"), 
                            rs.getString("password"), 
                            rs.getInt("age"), 
                            rs.getString("role")
                        );
                staffs.add(user);
            }
        } catch (SQLException e) {e.printStackTrace();}
        if(staffs.isEmpty()) {
            return null;
        }
        return staffs;
    }
	
	// untuk mengambil data User dengan username dan password tertentu dari DB
	public static User GetUserData(String username, String password) {
		String statement = "SELECT * FROM `user` WHERE `name` = '"+ username + "' AND `password` = '"+password+"'";					// QUERY SELECT USER WHERE USERNAME
		rs = db.selectData(statement);
		User user = null;
		
		try {
			if(rs.next()) {						// IF RESULT SET IS NOT EMPTY, IT WILL DO THE STATEMENT BELOW
				user = new User(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("password"), 
						rs.getInt("age"), 
						rs.getString("role")
					);
			}
			
		} catch(SQLException e) {e.printStackTrace();}
		
		return user;
	}
	
	// untuk menambahkan User ke DB
	public static void AddNewUser(String username, String password, int age) {
		String statement = "INSERT INTO `user`(`name`, `password`, `age`, `role`) VALUES ('"+username+"','"+password+"',"+String.valueOf(age)+",'Customer')";					// QUERY INSERT INTO
		db.execute(statement);
	}
	
	// untuk meng-update User Role pada DB
	public static void ChangeUserRole(int userId, String newRole) {
		String statement = "UPDATE `user` SET `role`='"+newRole+"' WHERE `id` = "+ String.valueOf(userId);					// QUERY UPDATE
		db.execute(statement);
	}
	
	// mengambil semua User dengan role Computer Technician dari DB
	public static List<User> GetAllTechnician(){
		List<User> technicians = new ArrayList<>();
		
		String statement = "SELECT * FROM `user` WHERE `role` = 'Computer Technician'";					// QUERY SELECT * WHERE ROLE TECHNICIAN
		rs = db.selectData(statement);
		User user;
		
		try {
			while(rs.next()) {
				user = new User(
							rs.getInt("id"), 
							rs.getString("name"), 
							rs.getString("password"), 
							rs.getInt("age"), 
							rs.getString("role")
						);
				technicians.add(user);
			}
		} catch (SQLException e) {e.printStackTrace();}
		if(technicians.isEmpty()) {
			return null;
		}
		return technicians;
	}
	
	// untuk mengambil User dengan ID tertentu dari DB
	public static User GetUserData(int userId) {
		String statement = "SELECT * FROM `user` WHERE `id` = " + String.valueOf(userId);					// QUERY SELECT USER BY ID
		rs = db.selectData(statement);
		User user = null;
		
		try {
			if(rs.next()) {						// IF RESULT SET IS NOT EMPTY, IT WILL DO THE STATEMENT BELOW
				user = new User(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("password"), 
						rs.getInt("age"), 
						rs.getString("role")
					);
			}
			
		} catch(SQLException e) {e.printStackTrace();}
		
		return user;
	}

	public static User GetUserData(String username) {
		String statement = "SELECT * FROM `user` WHERE `name` = +'"+ username +"'";					// QUERY SELECT USER WHERE USERNAME
		rs = db.selectData(statement);
		User user = null;
		
		try {
			if(rs.next()) {						// IF RESULT SET IS NOT EMPTY, IT WILL DO THE STATEMENT BELOW
				user = new User(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("password"), 
						rs.getInt("age"), 
						rs.getString("role")
					);
			}
			
		} catch(SQLException e) {e.printStackTrace();}
		
		return user;
	}
	
	
}
