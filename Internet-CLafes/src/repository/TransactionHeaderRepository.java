package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.PCBook;
import model.TransactionHeader;

public class TransactionHeaderRepository {
	private static Connect db = Connect.getInstance();
	private static ResultSet rs;
	
	// untuk mengambil semua Transaction Header dari DB
	public static List<TransactionHeader> GetAllTransactionHeaderData(){
		List<TransactionHeader> transactionHeaders = new ArrayList<>();
		
		String statement = "SELECT * FROM `transaction_header`";					// QUERY SELECT *
		rs = db.selectData(statement);
		TransactionHeader transactionHeader;
		
		try {
			while(rs.next()) {
				transactionHeader = new TransactionHeader(
							rs.getInt("id"),
							rs.getInt("staff_id"), 
							rs.getString("staff_name"),
							rs.getDate("date")
							);
				transactionHeaders.add(transactionHeader);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		if(transactionHeaders.isEmpty()) {
			return null;
		}
		return transactionHeaders;
	}
	
	// untuk menambah Transaction Header ke DB
	public static int AddNewTransactionHeader(int staffId, String staffName, Date date) {
		String statement = "INSERT INTO `transaction_header`(`staff_id`, `staff_name`, `date`) VALUES (" +String.valueOf(staffId) + ",'" + staffName+ "','"+String.valueOf(date)+"')";					// QUERY INSERT INTO
		db.execute(statement);
		
		statement = "SELECT `id` FROM `transaction_header` ORDER BY `id` DESC LIMIT 1";
		rs = db.selectData(statement);
		
		Integer transactionHeader = null;
		
		
		try {
			if(rs.next()) {
				transactionHeader =  rs.getInt("id");
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		return transactionHeader;
	}
	
}
