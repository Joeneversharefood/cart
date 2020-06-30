package dao;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;

public class UserDAO {
//	public static void main(String []args) throws SQLException {
//		System.out.println(new UserDAO().getUser("joey", "182550yyw").getId());
//	}
	
	public User getUser(String name,String password) throws SQLException{
		User result=null;
		Connection connection=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
    		String url="jdbc:mysql://localhost:3306/cart?serverTimezone=UTC";
    		String user="root";
    		String passWord="root";
			
			connection=DriverManager.getConnection(url, user, passWord);
			
			String sql="select * from user where name=? and password=?";
			
			ps=connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			
			ResultSet resultset=ps.executeQuery();
			
			if(resultset.next()) {
				result=new User();
				result.setId(resultset.getInt("id"));
				result.setName(resultset.getString("name"));
				result.setPassword(resultset.getString("password"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
				System.out.println("与数据库的这次连接已关闭");				
			}
			if(ps!=null) {
				ps.close();
				System.out.println("预编译Statement已关闭");
			}
		}
		
		return result;
		
	}

}
