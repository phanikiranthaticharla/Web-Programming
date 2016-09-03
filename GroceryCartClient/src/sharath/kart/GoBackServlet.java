package sharath.kart;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import sharath.kart.LoginBean;

/**
 * Servlet implementation class SessionControllerServlet
 */
@WebServlet("/GoBackServlet")
public class GoBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log= Logger.getLogger(GoBackServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoBackServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//		PrintWriter out=response.getWriter();

		log.info(request);
		HttpSession session = request.getSession();
		String user = session.getAttribute("user").toString();
		//			System.out.println(user);
		log.info(user);
		if(user==null || user.equals("devva")){
			RequestDispatcher rd=request.getRequestDispatcher("grocerykart.jsp");
			rd.forward(request, response);
		}
		else{
			session.setAttribute("prod", "false");
			LoginBean bean = new LoginBean();
			bean.setEmail(user);
			//				System.out.println(user);
			request.setAttribute("bean", bean);
			RequestDispatcher rd=request.getRequestDispatcher("user_login.jsp");
			rd.forward(request, response);
		}
	}

}


