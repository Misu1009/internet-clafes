package Controller;

import java.sql.Date;
import java.util.List;

import model.PCBook;
import model.TransactionDetail;
import model.TransactionHeader;
import model.User;
import repository.TransactionDetailRepository;
import repository.TransactionHeaderRepository;

public class TransactionController {
	
	// untuk menambahkan 1 Transaction Header dan Transaction Detail sesuai berapa banyak PC Book yang diproses
	public void AddTransaction(List<PCBook> listPcBook, int staffId) {
		UserController userController = new UserController();
		
		User user = userController.GetUserData(staffId);
		
		Date bookDate = new Date(listPcBook.get(0).getBookDate().getTime());
		int transactionId = TransactionHeaderRepository.AddNewTransactionHeader(staffId, user.getUserName(), bookDate);
		
		for (PCBook pcBook : listPcBook) {
			user = userController.GetUserData(pcBook.getUserID());
			
			TransactionDetailRepository.AddTransactionDetail(transactionId, pcBook.getPC_ID(), user.getUserName(),  new java.sql.Date(pcBook.getBookDate().getTime()));
		}
	}
	
	// untuk mengambil semua Transaction Header
	public List<TransactionHeader> getAllTransactionHeaderData(){
		return TransactionHeaderRepository.GetAllTransactionHeaderData();
	}
	
	// untuk mengambil semua Transaction Detail
	public List<TransactionDetail> getAllTransactionDetail(){
		return TransactionDetailRepository.GetAllTransactionDetail();
	}
	
	// untuk engambil semuat transaction Detail dari User dengan nama tertentu
	public List<TransactionDetail> getUserTransactionDetail(String customerName) {
		return TransactionDetailRepository.GetUserTransactionDetail(customerName);
	}
	
	// untuk mendapatkan Transaction Detail dari ID Transaction Header
	public List<TransactionDetail> GetTransactionDetailById(int transactionId) {
		return TransactionDetailRepository.GetTransactionDetailById(transactionId);
	}
	
	// untuk mendapatkan Transaction Detail dari User dengan ID tertentu
	public TransactionDetail GetUserTransaction(int userId) {
		return TransactionDetailRepository.GetUserTransaction(userId);
	}
}
