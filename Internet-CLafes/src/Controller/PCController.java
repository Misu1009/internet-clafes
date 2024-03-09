package Controller;

import java.util.List;

import model.PC;
import repository.PCRepository;

public class PCController {
	
	// untuk mengganti PC Condition dengan ID yang dipiliah dan menjadi Condition yang dipilih
	public String UpdatePcCondition(int pcId, String condition) {
		if(!condition.equals("Usable") && !condition.equals("Maintenance") && !condition.equals("Broken")) {
			return "CONDITION IS WRONG";
		}
		PC pc = GetPcDetail(pcId);
		if(pc == null) {
			return "PC IS NOT FOUND";
		}
		PCRepository.UpdatePCCondition(pcId, condition); // UPDATE PC CONDITION
		return "SUCCESS";
	}
	
	// untuk mendelete PC dengan PC ID yang dipilih
	public void DeletePc(int pcId) {
		PC pc = PCRepository.GetPcDetail(pcId);
		if(pc != null) {
			PCRepository.DeletePc(pcId);; // DELETE PC DATA BY ID
		}
	}
	
	// untuk menambah PC dengan ID yang dipilih dengan kondisi yang secara otomatis Useable
	public String AddNewPc(String pcId) {	
		try {
			int PC_ID = Integer.valueOf(pcId);
			PC pc = PCRepository.GetPcDetail(PC_ID);
			if(pc == null) {
				PCRepository.AddNewPc(PC_ID);; // Add PC DATA BY ID
				return "SUCCESS";
			}
			return "ID MUST BE UNIQUE";	
		}
		catch(Exception e) {
			return "Invalid Input";
		}
	}

	// untuk mengambil data PC menggunakan ID yang dipilih
	public PC GetPcDetail(int pcId) { //NULLABLE
		return PCRepository.GetPcDetail(pcId); // GET PC DATA BY ID
	}
	
	// untuk mengambil semua data PC yang ada
	public List<PC> GetAllPcData(){ //NULLABLE
		return PCRepository.GetAllPCData(); // GET ALL PC DATA
	}
}
