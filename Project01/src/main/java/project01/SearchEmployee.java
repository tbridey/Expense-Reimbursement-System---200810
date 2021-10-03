package project01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class SearchEmployee
 */
public class SearchEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		
		String jsonString = request.getReader().readLine();
		
		ObjectMapper om = new ObjectMapper();
		EmployeeNumber emp_num = om.readValue(jsonString, EmployeeNumber.class);
		//System.out.println(emp_num.getEmp_num());
		
		if(session != null) {
			UserOracleDAO dao = new UserOracleDAO();
			User employee = dao.searchEmployee(emp_num.getEmp_num());
			String jsonStr = om.writeValueAsString(employee);
			out.append(jsonStr);
		}else {
			System.out.println("Session returned empty");
		}
	}

}
