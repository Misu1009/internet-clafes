package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.PCBook;
import model.User;

public class PCBookRepository {
	private static Connect db = Connect.getInstance();
	private static ResultSet rs;
	
	// untuk melakukan delete PC Book dengan ID tertentu di DB
	public static void DeleteBookData(int bookId) {
		String statement = "DELETE FROM `pcbook` WHERE `id` = " + String.valueOf(bookId);					// QUERY DELETE
		db.execute(statement);
	}
	
	// untuk mengakses DB dan mengembalikan PC Book dengan PC ID dan Date yang dipilih
	public static PCBook GetPCBookedData(int pcId, Date date) {
		String statement = "SELECT * FROM `pcbook` WHERE pc_id = "+String.valueOf(pcId)+" AND date = '"+String.valueOf(date)+"'";	// QUERY SELECT PCBOOK DATA BY ID AND DATE
		PCBook pcbook = null;
		rs = db.selectData(statement);
		try {
			if(rs.next()) {						// IF RESULT SET IS NOT EMPTY, IT WILL DO THE STATEMENT BELOW
				pcbook = new PCBook(
						rs.getInt("id"),
						rs.getInt("pc_id"),
						rs.getInt("user_id"),
						rs.getDate("date")
					);
			}
		} catch(SQLException e) {e.printStackTrace();}
		return pcbook;
	}

	// untuk mengakses DB dan mengambil PC Book dengan ID tertentu
	public static PCBook GetPCBookedDetail(int bookId) {
		String statement = "SELECT * FROM `pcbook` WHERE id = "+ String.valueOf(bookId);					// QUERY SELECT PCBOOK DATA BY ID
		rs = db.selectData(statement);
		PCBook pcbook = null;
		
		try {
			if(rs.next()) {						// IF RESULT SET IS NOT EMPTY, IT WILL DO THE STATEMENT BELOW
				pcbook = new PCBook(
						rs.getInt("id"),
						rs.getInt("pc_id"),
						rs.getInt("user_id"),
						rs.getDate("date")
					);
			}
			
		} catch(SQLException e) {e.printStackTrace();}
		
		return pcbook;
	}
	
	// untuk memasukan PC Book baru ke DB
	public static void AddnewBook(int pcId, int userId, Date bookDate) {
		String statement = "INSERT INTO `pcbook`(`pc_id`, `user_id`, `date`) VALUES ("+String.valueOf(pcId)+", "+String.valueOf(userId)+",'"+String.valueOf(bookDate)+"')";					// QUERY INSERT INTO
		db.execute(statement);
	}
	
	// untuk menyelesaikan PC Book dari DB
	public static void FinishBook(List<PCBook> pcbook) {
		String statement = "DELETE FROM `pcbook` WHERE id = " ;
		int bookId;
		for (PCBook pcBook2 : pcbook) {
			bookId = pcBook2.getBookID();
			db.execute(statement + String.valueOf(bookId));
		}
	}
	
	// untuk mengambil semua PC Book dari DB
	public static List<PCBook> GetAllPCBookedData(){
		List<PCBook> pcbooks = new ArrayList<>();
		
		String statement = "SELECT * FROM `pcbook`";					// QUERY SELECT *
		rs = db.selectData(statement);
		PCBook pcbook;
		
		try {
			while(rs.next()) {
				pcbook = new PCBook(
						rs.getInt("id"),
						rs.getInt("pc_id"),
						rs.getInt("user_id"),
						rs.getDate("date")
						);
				pcbooks.add(pcbook);
			}
		} catch (SQLException e) {e.printStackTrace();}
		if(pcbooks.isEmpty()) {
			return null;
		}
		
		return pcbooks;
	}
	
	// untuk mengambil semua PC Book dengan Date tertentu dari DB
	public static List<PCBook> GetPCBookedByDate(Date date){
		List<PCBook> pcbooks = new ArrayList<>();
		
		String statement = "SELECT * FROM `pcbook` WHERE `date` = '" +String.valueOf(date) + "'";					// QUERY SELECT PCBOOK BY DATE, RETURN LIST
		rs = db.selectData(statement);
		PCBook pcbook;
		
		try {
			while(rs.next()) {
				pcbook = new PCBook(
						rs.getInt("id"),
						rs.getInt("pc_id"),
						rs.getInt("user_id"),
						rs.getDate("date")
						);
				pcbooks.add(pcbook);
			}
		} catch (SQLException e) {e.printStackTrace();}
		if(pcbooks.isEmpty()) {
			return null;
		}
		
		return pcbooks;
	}
	
	// Untuk meng-update PC yang akan digunakan pada PC Book
	public static void AssignUserToNewPC(int bookId, int newPcId) {
		String statement = "UPDATE `pcbook` SET `pc_id`= " + newPcId + " WHERE `id`= "+String.valueOf(bookId);
		db.execute(statement);
	}
	
	
}
