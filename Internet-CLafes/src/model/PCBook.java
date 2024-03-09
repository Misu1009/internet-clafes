package model;

import java.util.Date;
import java.util.List;

public class PCBook {
	private int BookID;
	private int PC_ID;
	private int UserID;
	private Date BookDate;
	
	public PCBook(int bookID, int pC_ID, int userID, Date bookDate) {
		super();
		BookID = bookID;
		PC_ID = pC_ID;
		UserID = userID;
		BookDate = bookDate;
	}

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public int getPC_ID() {
		return PC_ID;
	}

	public void setPC_ID(int pC_ID) {
		PC_ID = pC_ID;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public Date getBookDate() {
		return BookDate;
	}

	public void setBookDate(Date bookDate) {
		BookDate = bookDate;
	}

}
