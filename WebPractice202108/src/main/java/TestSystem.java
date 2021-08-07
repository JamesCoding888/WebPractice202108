import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSystem {

	public static void main(String[] args) {
		String Url = "jdbc:mysql://localhost:3307/companypractice";
		String user = "root";
		String password = "1234";
		String SQL = "insert into memberpractice(name, username, password, address, phone, mobile)"
									   + "values('JamesLiao', 'james', 'abc', 'Taipei', '098808908','0204050')";
		
		String SQL2 = "insert into memberpractice(name, username,password, address, phone, mobile)"
					+ "values(?, ?, ?, ?, ?, ?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(Url, user, password);
//			Statement statement = conn.createStatement();
//			statement.executeUpdate(SQL);
			
			PreparedStatement ps = conn.prepareStatement(SQL2);
			ps.setString(1, "Ryan");
			ps.setString(2, "ryan");
			ps.setString(3, "123");
			ps.setString(4, "123");
			ps.setString(5, "0988909");
			ps.setString(6, "020688775");
			
			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			System.out.println("no driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No connection");
			e.printStackTrace();
		}
	}
}

