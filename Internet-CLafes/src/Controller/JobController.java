package Controller;

import java.util.List;

import model.Job;
import model.PC;
import model.User;
import repository.JobRepository;

public class JobController {
	
	
	// Melakukan validasi dan logic dalam penambahan Job
	public String AddNewJob(int userId, int pcId) { // NULLABLE
		PCController pcController = new PCController();
		UserController userController = new UserController();
		
		PC pc = pcController.GetPcDetail(pcId); // GET PC DATA BY ID
		
		if(pc == null) {
			return "PC IS NOT EXIST";
		}
		if(Integer.toString(pcId).equals("")) {
			return "CANNOT BE EMPTY";
		}
		
		User user = userController.GetUserData(userId); // GET USER DATA BY ID
		if(user == null) {
			return "USER IS NOT EXIST";
		}
			
		if(!user.getUserRole().equals("Computer Technician")) {
			return "USER MUST BE TECHNICIAN";
		}
		
		JobRepository.AddNewJob(userId, pcId);; // ADD NEW JOB WITH DEFAULT STATUS
		return "SUCCESS";
	}
	
	// Untuk menyelesaikan status Job yang sudah ada menjadi Complete
	public String UpdateJobStatus(int jobId, String jobStatus) {
		if(!jobStatus.equals("Complete") && !jobStatus.equals("Uncomplete")) {
			return "WRONG STATUS";
		}
		JobRepository.UpdateJobStatus(jobId, jobStatus);; //UPDATE JOB STATUS
		return "SUCCESS";
	}
	
	// Untuk mengambil semua Job Data yang sudah di assign oleh admin untuk Computer Technician dengan id
	public List<Job> GetTechnicianJob(int userId){ 	// NULLABLE
		return JobRepository.GetTechnicianJob(userId);
	}
	
	// Untuk mengambil semua Job
	public List<Job> GetAllJobData(){ 				// NULLABLE
		return JobRepository.GetAllJobData();
	}
}
