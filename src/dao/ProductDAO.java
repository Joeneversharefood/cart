package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO {


	
	
	public Product getProduct(int id) throws SQLException {
		Product result=null;
		Connection connection=null;
		PreparedStatement ps=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
    		String url="jdbc:mysql://localhost:3306/cart?serverTimezone=UTC";
    		String user="root";
    		String password="root";
			
			connection=DriverManager.getConnection(url, user, password);
			
			String sql="select * from product where id=?";
			
			ps=connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet resultset=ps.executeQuery();
			
			if(resultset.next()) {
				result=new Product();
				result.setId(id);
				
				String name=resultset.getString("name");
				result.setName(name);
				
				float price=resultset.getFloat("price");
				result.setPrice(price);
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
				System.out.println("本次连接已关闭");
			}
			if(ps!=null) {
				ps.close();
				System.out.println("预编译Statement已关闭");
				
			}
		}
		
		return result;
	}
	
	public List<Product> ListProduct() throws SQLException{
		List<Product> products=new ArrayList<Product>();
		Connection connection=null;
		PreparedStatement ps=null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
    		String url="jdbc:mysql://localhost:3306/cart?serverTimezone=UTC";
    		String user="root";
    		String password="root";
			
			connection=DriverManager.getConnection(url,user,password);
			
			String sql="select * from product order by id desc";
			
			ps=connection.prepareStatement(sql);
			
			ResultSet resultset=ps.executeQuery();
			
			while(resultset.next()) {
				Product product=new Product();
				int id=resultset.getInt("id");
				String name=resultset.getString("name");
				float price=resultset.getFloat("price");
				
				product.setId(id);
				product.setName(name);
				product.setPrice(price);
				
				products.add(product);
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
				System.out.println("与数据库的连接已关闭");
			}
			
			if(ps!=null) {
				ps.close();
				System.out.println("预编译Statement已关闭");
			}
		}
		return products;
	}

}
