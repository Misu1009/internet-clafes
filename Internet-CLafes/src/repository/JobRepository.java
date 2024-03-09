package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.Job;
import model.User;

public class JobRepository {
	private static Connect db = Connect.getInstance();
	private static ResultSet rs;
	
	// untuk memasukan Job baru ke DB
	public static void AddNewJob(int userId, int pcId) {
		String statement = "INSERT INTO job(`user_id`, `pc_id`, `status`) VALUES ('"+String.valueOf(userId)+"','"+String.valueOf(pcId)+"','Uncomplete')";					// QUERY INSERT INTO
		db.execute(statement);
	}
	
	// untuk meng-update Job Status dari Job dengan Job ID yang dipilih
	public static void UpdateJobStatus(int jobId, String jobStatus) {
		String statement = "UPDATE `job` SET `status`='"+jobStatus+"' WHERE `id` = '"+String.valueOf(jobId)+"'";
		db.execute(statement);
	}
	
	// untuk mengambil semua Job dari Computer Technician dengan ID yang dipilih dari DB
	public static List<Job> GetTechnicianJob(int userId) {
		List<Job> jobs = new ArrayList<>();
		
		String statement = "SELECT * FROM `job` WHERE `user_id` = '"+String.valueOf(userId)+"'";// QUERY SELECT JOB BY USER ID
		rs = db.selectData(statement);
		Job job = null;
		
		try {
			while(rs.next()) {
				job = new Job(
						rs.getInt("id"), 
						rs.getInt("user_id"), 
						rs.getInt("pc_id"),
						rs.getString("status")
						);
				jobs.add(job);
			}
		} catch (SQLException e) {e.printStackTrace();}
		return jobs;
	}
	
	// untuk mengambil semua Job dari DB
	public static List<Job> GetAllJobData(){
		List<Job> jobs = new ArrayList<>();
		
		String statement = "SELECT * FROM `job`";					// QUERY SELECT * JOB
		rs = db.selectData(statement);
		Job job;
		
		try {
			while(rs.next()) {
				job = new Job(
						rs.getInt("id"), 
						rs.getInt("user_id"), 
						rs.getInt("pc_id"),
						rs.getString("status")
						);
				jobs.add(job);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		if(jobs.isEmpty()) {
			return null;
		}
		return jobs;
	}
	
}