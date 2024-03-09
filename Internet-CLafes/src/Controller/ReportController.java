package Controller;

import java.sql.Date;
import java.util.List;

import model.PC;
import model.Report;
import repository.ReportRepository;

public class ReportController {
	
	// untuk membuat report
	public String AddNewReport(String userRole, int pcId, String reportNote, Date date) {
//		if(Integer.toString(pcId).equals("") || reportNote.equals("")) {
//			return "DATA MUST BE FILLED";
//		}
		if(pcId == -1 || reportNote.equals("")) {
			return "DATA MUST BE FILLED";
		}
		if(getPc(pcId)== null) {
			return "PC IS NOT EXIST";
		}
		
		ReportRepository.AddNewReport(userRole, pcId, reportNote, date); // ADD NEW REPORT
		
		return "SUCCESS";
	}
	
	// untuk mengembalikan semua report
	public List<Report> GetAllReportData(){ // NULLABLE
		return ReportRepository.GetAllReportData(); // GET ALL REPORT DATA
	}
	
	// untuk mendapatpkan PC yang nanti kan digunakan untuk AddNewReport
	public PC getPc(int pcId) {	// NULLABLE
		PCController pcController = new PCController();
		
		return pcController.GetPcDetail(pcId); // GET PC BY ID
	}
	
}
