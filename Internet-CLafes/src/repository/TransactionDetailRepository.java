package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.TransactionDetail;

public class TransactionDetailRepository {
	private static Connect db = Connect.getInstance();
	private static ResultSet rs;
	
	
	// untuk mengambil Transaction Detail milik Customer dengan nama tertentu dari DB
	public static List<TransactionDetail> GetUserTransactionDetail(String customerName) {
		List<TransactionDetail> transactionDetails = new ArrayList<>();
		String statement = "SELECT * FROM transaction_detail WHERE customer_name = '" + customerName + "'";
		rs = db.selectData(statement);
		TransactionDetail transactionDetail;
		try {
			while(rs.next()) {
				transactionDetail = new TransactionDetail(
						rs.getInt("id"), 
						rs.getInt("pc_id"), 
						rs.getString("customer_name"), 
						rs.getDate("booked_time")
						);
				transactionDetails.add(transactionDetail);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(transactionDetails.isEmpty()) return null;
		
		return transactionDetails;
	}
	
	// untuk mengambil Transaction Detail milik Customer dengan ID tertentu dari DB
	public static TransactionDetail GetUserTransaction(int userId) {
		String statement = "SELECT * FROM `transaction_detail` WHERE id = "+ String.valueOf(userId);					// QUERY SELECT job WHERE userId / staffId
		rs = db.selectData(statement);
		TransactionDetail transactionDetail = null;
		
		try {
			if(rs.next()) {					
				transactionDetail = new TransactionDetail(
						rs.getInt("id"), 
						rs.getInt("pc_id"), 
						rs.getString("customer_name"), 
						rs.getDate("booked_time")
					);
			}
			
		} catch(SQLException e) {e.printStackTrace();}
		
		return transactionDetail;
	}
	
	// untuk mengambil semua Transaction Detail dari DB
	public static List<TransactionDetail> GetAllTransactionDetail() {
		List<TransactionDetail> transactionDetails = new ArrayList<>();
			
		String statement = "SELECT * FROM `transaction_detail`";					// QUERY SELECT *
		rs = db.selectData(statement);
		TransactionDetail transactionDetail;
		
		try {
			while(rs.next()) {
				transactionDetail = new TransactionDetail(
						rs.getInt("id"), 
						rs.getInt("pc_id"), 
						rs.getString("customer_name"), 
						rs.getDate("booked_time")
							);
				transactionDetails.add(transactionDetail);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		if(transactionDetails.isEmpty()) {
			return null;
		}
		return transactionDetails;
	}
	
	// untuk menambahkan Transaction Detail ke DB
	public static void AddTransactionDetail(int transactionID, int pC_ID, String customerName, Date bookedTime) {
		String statement = "INSERT INTO `transaction_detail`(`id`, `pc_id`, `customer_name`, `booked_time`) VALUES ("+transactionID+","+pC_ID+",'"+customerName+"','"+bookedTime+"')";					// QUERY INSERT INTO
		db.execute(statement);
	}
	
	// untuk mengambil list Transaction Detail dari ID Transaction Header dari DB
	public static List<TransactionDetail> GetTransactionDetailById(int transactionId) {
		List<TransactionDetail> transactionDetails = new ArrayList<>();
		
		String statement = "SELECT * FROM `transaction_detail` WHERE `id` = "+ String.valueOf(transactionId);					// QUERY SELECT *
		rs = db.selectData(statement);
		TransactionDetail transactionDetail;
		
		try {
			while(rs.next()) {
				transactionDetail = new TransactionDetail(
						rs.getInt("id"), 
						rs.getInt("pc_id"), 
						rs.getString("customer_name"), 
						rs.getDate("booked_time")
							);
				transactionDetails.add(transactionDetail);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		if(transactionDetails.isEmpty()) {
			return null;
		}
		return transactionDetails;
	}
}
