package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.Report;
import model.User;

public class ReportRepository {
	private static Connect db = Connect.getInstance();
	private static ResultSet rs;
	
	// untuk menambahkan Report pada DB
	public static void AddNewReport(String userRole, int pcId, String reportNote, Date date) {
		String statement = "INSERT INTO `report`(`user_role`, `pc_id`, `note`, `date`) VALUES ('"+userRole+"'," +String.valueOf(pcId)+",'"+reportNote+"','"+String.valueOf(date)+"')";					// QUERY INSERT INTO
		db.execute(statement);
	}
	
	// untuk mengambil semua Report dari DB
	public static List<Report> GetAllReportData() {	// NULLABLE
		List<Report> reports = new ArrayList<>();
		
		String statement = "SELECT * FROM report";					// QUERY SELECT *
		rs = db.selectData(statement);
		Report report;
		
		try {
			while(rs.next()) {
				report = new Report(
							rs.getInt("id"), 
							rs.getString("user_role"), 
							rs.getInt("pc_id"), 
							rs.getString("note"), 
							rs.getDate("date")
							);
				reports.add(report);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		if(reports.isEmpty()) {
			return null;
		}
		
		return reports;
	}
}
