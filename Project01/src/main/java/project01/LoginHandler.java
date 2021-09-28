package project01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LoginHandler
 */
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandler() {
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
		
		PrintWriter out=response.getWriter();
		
		String jsonString = request.getReader().readLine();
		ObjectMapper om = new ObjectMapper();
		User u = om.readValue(jsonString, User.class);
		
//		System.out.println(jsonString);
		
		UserOracleDAO dao = new UserOracleDAO();
		boolean login = dao.loginValidator(u.getUsername(), u.getPassword());
		
		if(login==true) {
			u = dao.getUserObj(u);
			
			String jsonStr = om.writeValueAsString(u);
			
			response.setContentType("plain/text");
			
			out.append(jsonStr);
			
			//System.out.println(jsonStr); 
			
		}
		
//		System.out.println(u.getUsername());
//		System.out.println(u.getPassword());
		
		
	}

}
