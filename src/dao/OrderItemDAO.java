package dao;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import bean.OrderItem;
import bean.Product;


public class OrderItemDAO {
	
	public void insertOrderItem(OrderItem oi) throws SQLException {
		Connection connection=null;
		PreparedStatement ps=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
    		String url="jdbc:mysql://localhost:3306/cart?serverTimezone=UTC";
    		String user="root";
    		String password="root";
			
			connection=DriverManager.getConnection(url, user, password);
			
			String sql="insert into orderitem values(null,?,?,?)";
			
			ps=connection.prepareStatement(sql);
			
			ps.setInt(1, oi.getProduct().getId());
			ps.setInt(2, oi.getNum());
			ps.setInt(3, oi.getOrder().getId());
			
			ps.execute();
			
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
	}

}
