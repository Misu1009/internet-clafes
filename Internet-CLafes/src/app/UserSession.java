package app;

import model.User;

public class UserSession {
	//User Session merupakan Class yang berjenis Singleton
	
	//User Session diawali dengan static dari classUserSession
	private static UserSession instance;
	
	//Data utama yang disimpan dalam class ini aqdalah User
	private User user;
	
	//Mendeklarasikan constructor UserSession sebagai Private agar tidak bisa dibuat berulang-ulang
	private UserSession() {
		// TODO Auto-generated constructor stub
	}
	
	//Setter dan Getter untuk objek User
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	//GetInstance untuk mengambil instance dari UserSession ataupun menciptakan Instance baru jika bernilai null
	public static UserSession getInstance() {
		if(instance == null) {
			instance = new UserSession();
		}
		return instance;
	}
}