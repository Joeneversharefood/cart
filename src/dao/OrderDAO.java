package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Order;

public class OrderDAO {
	
//	public int getOrderId(Order order) {
//		
//		return 0;
//		
//	}
	
	public void insertOrder(Order order){
		Connection connection=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
    		String url="jdbc:mysql://localhost:3306/cart?serverTimezone=UTC";
    		String user="root";
    		String password="root";
			
			connection=DriverManager.getConnection(url, user, password);
			
			String sql="insert into order_ values(null,?)";
			ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, order.getUser().getId());
			
			ps.execute();
			
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()) {
				int id=rs.getInt(1);
				System.out.println("orderID是"+id);
				order.setId(id);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("与数据库的这次连接已关闭");				
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("预编译Statement已关闭");
			}
		}
		
	}

}
