package Controller;

import java.sql.Date;
import java.util.List;

import model.PCBook;
import repository.PCBookRepository;

public class PCBookController {
	
	// untuk melakukan delete PC Book yang dapat ditemukan menggunakan Book ID
	public void DeleteBookData(int bookId) {
		PCBook pcbook = PCBookRepository.GetPCBookedDetail(bookId);
		if(pcbook != null) {
			PCBookRepository.DeleteBookData(bookId); // DELETE PCBOOK DATA BY ID
		}	
	}
	
	// untuk melakukan select terhadap 1 PC dengan PC ID dan Tanggal yang sudah dipilih
	public PCBook GetPCBookData(int pcId, Date date) { // NULLABLE
		return PCBookRepository.GetPCBookedData(pcId, date); // GET PC BOOK DATA BY PC ID AND DATE
	}
	
	// untuk mengembalikan 1 PC Book dengan Book ID yang dimasukan
	public PCBook GetPCBookedDetail(int bookId) {	  // NULLABLE
		return PCBookRepository.GetPCBookedDetail(bookId); // GET PC BOOK DATA BY BOOK ID
	}
	
	// untuk menambahkan PC Book dengan memasukan PC ID, User yang membuat PC Book dan Tanggal yang ingin di Booking
	public void AddNewBook(int pcId, int userId, Date bookedDate) {
		PCBookRepository.AddnewBook(pcId, userId, bookedDate); // ADD NEW BOOK DATA 
	}
	
	// untuk menyelesaikan list PC Book pada suatu tanggal yang dipilih
	public void FinishBook(List<PCBook> pcBooks) {
		PCBookRepository.FinishBook(pcBooks);
	}
	
	// untuk melakukan update PC Book dengan id yang sudah dimasukan dimana kita akan mengubah PC ID yang ada dengan yang baru
	public void AssignUserToNewPC(int bookId, int newPcId) {
		PCBookRepository.AssignUserToNewPC(bookId, newPcId);
	}
	
	// untuk mengambil semua PC Book
	public List<PCBook> GetAllPcBookedData() {  	// NULLABLE
		return PCBookRepository.GetAllPCBookedData();
	}
	
	// untuk mengambil semua PC Book pada suatu tanggal
	public List<PCBook> GetPcBookedByDate(Date date) {	 // NULLABLE
		return PCBookRepository.GetPCBookedByDate(date);
	}
	
	// untuk mengambil semua PC Book yang membooking suatu PC di tanggal tersebut
	public List<PCBook> GetPcBookedByDate(int pcId, Date date) {	 // NULLABLE
		return PCBookRepository.GetPCBookedByDate(date);
	}
}
