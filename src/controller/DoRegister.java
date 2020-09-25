package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

/**
 * Servlet implementation class DoRegister
 */
@WebServlet("/doRegister")
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerId = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender;
		if(request.getParameter("male")!=null)
			gender = "female";
		else
			gender = "male";	
		String email = request.getParameter("email");
		
		
		
		// Perform business logic. Return a bean as a result.
		
		//CustomerService service = new CustomerService(); 싱글턴 기법에선 이렇게 사용하지 않음
		
		CustomerService service = (CustomerService) CustomerService.getInstance(); // 싱글턴 기법
		
		//System.out.println(service);
		//Customer customer = service.findCustomer(customerId);
		Customer customer = new Customer(customerId,password,name,gender,email);
		service.addCustomer(customer);
		request.setAttribute("customer", customer);   //중요한 부분이다!!!!!
		
		String page = null;
		
		if(customer!=null) {
			page ="/view/registerSuccess.jsp";
			request.setAttribute("customer", customer);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
