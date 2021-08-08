package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.member;

public class memberDao implements implDao{

	public static void main(String[] args) {
		// 確認 SQL 的 Connection 成功
		System.out.println(new memberDao());
		
		// Override add method ，將 fields 的參數傳入 database
		new memberDao().add("abc", "james", "123", "1234", "1234", "1234");
		
		// Override add(member m)， 使用物件注入 Dependence injection
		member m = new member("abc", "james", "123", "1234", "1234", "1234");
		new memberDao().add(m);
		
		
	}
	
	@Override
	public void add(String name, String username, String password, String address, String phone, String mobile) {
		Connection connection = implDao.getDB();
		String sql = "insert into memberpractice(name, username, password, address, phone, mobile)"
					+"values(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, address);
			ps.setString(5, phone);
			ps.setString(6, mobile);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("No Connection");
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void add(member m) {
		Connection connection = implDao.getDB();
		String sql = "insert into memberpractice(name, username, password, address, phone, mobile)"
					+"values(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, m.getName());
			ps.setString(2, m.getUsername());
			ps.setString(3, m.getPassword());
			ps.setString(4, m.getAddress());
			ps.setString(5, m.getPhone());
			ps.setString(6, m.getMobile());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("No Connection");
			e.printStackTrace();
		}
		
		
	}

	//判斷使用者的帳戶和密碼，是否有在 database 註冊了
	@Override
	public member queryUserName(String username, String password) {
		Connection connection = implDao.getDB();
		String sql = "select * from memberpractice where username=? and password=?";
		member m = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				m = new member(
						rs.getString("name"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("address"),
						rs.getString("phone"),
						rs.getString("mobile"));
			}					
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return m;		
	}	
}
