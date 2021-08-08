package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.member;

public interface implDao{
	// java8 static 方法 --> 連線方法
	static Connection getDB(){
		Connection connection = null;
		String URL = "jdbc:mysql://localhost:3307/companypractice";
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("No driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No connection");
			e.printStackTrace();
		}
		
		return connection;
	}
	
	// 新增物件
	void add(String name, String username, String password, String address, String phone, String mobile);
	void add(member m); // Override add(member m)， 使用物件注入 Dependence injection，當客戶需求增加 member 的 fields 的資料時.只需修改 member.java
	// R 查詢物件
	member queryUserName(String username, String password); // 判斷帳號密碼
	
	
}
