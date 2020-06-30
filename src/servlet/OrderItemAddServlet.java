package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderItem;
import bean.Product;
import dao.ProductDAO;

public class OrderItemAddServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num=Integer.parseInt(request.getParameter("num"));
		int pid=Integer.parseInt(request.getParameter("pid"));
		
		OrderItem orderitem=null;
		List<OrderItem> oil=null;
		
		try {
			Product product=new ProductDAO().getProduct(pid);
			
			orderitem=new OrderItem();
			orderitem.setProduct(product);
			orderitem.setNum(num);
			
			oil=(List<OrderItem>) request.getSession().getAttribute("oil");
			
			if(null==oil) {
				oil=new ArrayList<OrderItem>();
				request.getSession().setAttribute("oil", oil);
			}
			
			boolean found=false;
			for(OrderItem orderItem: oil) {
				if(orderItem.getProduct().getId()==orderitem.getProduct().getId()) {
					orderItem.setNum(orderItem.getNum()+orderitem.getNum());
					found=true;
					break;
				}
			}
			
			if(!found) {
				oil.add(orderitem);
			}
			
//			oil.add(orderitem);
			
			response.sendRedirect("/listOrderItem");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
