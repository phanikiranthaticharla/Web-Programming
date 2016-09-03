package sharath.kart.mail;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import sharath.kart.LoginBean;
import sharath.kart.constants.Constants;

import com.sun.jersey.api.json.JSONConfiguration;

/**
 * Servlet implementation class SessionControllerServlet
 */
@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailSendingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
//		PrintWriter out=response.getWriter();
		
		String from=request.getParameter("email");
		String subject=request.getParameter("subject");
		String content = request.getParameter("content");
		
		
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		
		Boolean status = false;
		try {
			 
			ClientConfig clientConfig = new DefaultClientConfig();
			 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			 
			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource(Constants.email_url);
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("from", from);
			formData.add("subject", subject);
			formData.add("content", content);
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .accept("application/json")
			    .post(ClientResponse.class, formData);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
 
			String statusString = restResponse.getEntity(String.class);
			System.out.println(statusString);
			
			
			 json = (JSONObject) parser.parse(statusString);
//			System.out.println(json);
			
			status = Boolean.parseBoolean(json.get("success").toString());
			System.out.println(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(status){

			HttpSession session = request.getSession();
			String user = session.getAttribute("user").toString();
//			System.out.println(user);
			
			if(user==null || user.equals("devva")){
				RequestDispatcher rd=request.getRequestDispatcher("grocerykart.jsp");
				rd.forward(request, response);
			}
			else{
				LoginBean bean = new LoginBean();
				bean.setName(user);
//				System.out.println(user);
				request.setAttribute("bean", bean);
				RequestDispatcher rd=request.getRequestDispatcher("user_login.jsp");
				rd.forward(request, response);
			}
		}
		else{
			System.out.println(status+" FUCK!!!");
			RequestDispatcher rd=request.getRequestDispatcher("generic-error.jsp");
			rd.forward(request, response);
		}
	}

}
