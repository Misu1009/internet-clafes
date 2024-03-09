package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.PC;
import model.User;

public class PCRepository {
	private static Connect db = Connect.getInstance();
	private static ResultSet rs;
	
	// untuk menganti PC Condition di DB
	public static void UpdatePCCondition(int pcId, String condition) {
		String statement = "UPDATE `pc` SET `condition`='"+condition+"' WHERE `id` = '"+String.valueOf(pcId)+"'";					// QUERY UPDATE WHERE ID
		db.execute(statement);
	}
	
	// untuk mendelete PC dari DB
	public static void DeletePc(int pcId) {
		String statement = "DELETE FROM `pc` WHERE `id` = '"+String.valueOf(pcId)+"'";// QUERY DELETE WHERE ID
		db.execute(statement);
	}
	
	// untuk menambah PC dengan kondisi Useable di DB
	public static void AddNewPc(int pcId) {
		String statement = "INSERT INTO `pc`(`id`, `condition`) VALUES ('"+String.valueOf(pcId)+"','Usable')";					// QUERY INSERT INTO
		db.execute(statement);
	}
	
	// untuk mengembalikan PC dari DB menggunakan ID
	public static PC GetPcDetail(int pcId) {	// NULLABLE
		String statement = "SELECT * FROM `pc` WHERE id = " + String.valueOf(pcId);					// QUERY SELECT WHERE
		rs = db.selectData(statement);
		PC pc = null;
		
		try {
			if(rs.next()) {						// IF RESULT SET IS NOT EMPTY, IT WILL DO THE STATEMENT BELOW
				pc = new PC(
					rs.getInt("id"), 
					rs.getString("condition")
					);
			}
		} catch(SQLException e) {e.printStackTrace();}
		
		return pc;
	}
	
	// untuk mengembalikan semua PC dari DB
	public static List<PC> GetAllPCData(){		// NULLABLE
		List<PC> pcs = new ArrayList<>();
		String statement = "SELECT * FROM pc";					// QUERY SELECT *
		rs = db.selectData(statement);
		PC pc;
		try {
			while(rs.next()) {
				pc = new PC(
						rs.getInt("id"), 
						rs.getString("condition")
							);
				pcs.add(pc);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		if(pcs.isEmpty()) {						// IF THERE ARE NO DATA IN LIST, IT WILL GIVE NULL 
			return null;
		}
		
		
		return pcs;
	}
	
}
