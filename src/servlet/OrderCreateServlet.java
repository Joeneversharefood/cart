package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import bean.Order;
import bean.OrderItem;
import bean.User;
import dao.OrderDAO;
import dao.OrderItemDAO;


public class OrderCreateServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=(User) request.getSession().getAttribute("user");
		
		if(null==user) {
			response.sendRedirect("/login.jsp");
			return;
		}
		
		Order order=new Order();
		
		order.setUser(user);
		
		new OrderDAO().insertOrder(order);

		
		//将订单项插入表orderitem中
		List<OrderItem> oil=(List<OrderItem>) request.getSession().getAttribute("oil");
		
		for(OrderItem oi:oil) {
			oi.setOrder(order);
			try {
				new OrderItemDAO().insertOrderItem(oi);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		oil.clear();
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println("订单创建成功");
		
		
	}

}
