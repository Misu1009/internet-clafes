package model;

import java.util.Date;

public class Report {
	private int ReportID;
	private String UserRole;
	private int PC_ID;
	private String ReportNote;
	private Date ReportDate;
	
	public Report(int reportID, String userRole, int pC_ID, String reportNote, Date reportDate) {
		super();
		ReportID = reportID;
		UserRole = userRole;
		PC_ID = pC_ID;
		ReportNote = reportNote;
		ReportDate = reportDate;
	}

	public Integer getReportID() {
		return ReportID;
	}

	public void setReportID(int reportID) {
		ReportID = reportID;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}

	public Integer getPC_ID() {
		return PC_ID;
	}

	public void setPC_ID(int pC_ID) {
		PC_ID = pC_ID;
	}

	public String getReportNote() {
		return ReportNote;
	}

	public void setReportNote(String reportNote) {
		ReportNote = reportNote;
	}

	public Date getReportDate() {
		return ReportDate;
	}

	public void setReportDate(Date reportDate) {
		ReportDate = reportDate;
	}
	
	
	
}
