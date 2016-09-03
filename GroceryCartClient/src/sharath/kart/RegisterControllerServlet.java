package sharath.kart;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import sharath.kart.constants.Constants;

import com.sun.jersey.api.json.JSONConfiguration;

/**
 * Servlet implementation class SessionControllerServlet
 */
@WebServlet("/RegisterControllerServlet")
public class RegisterControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log= Logger.getLogger(RegisterControllerServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
//		PrintWriter out=response.getWriter();
		
		String fname=request.getParameter("firstname");
		String lname=request.getParameter("lastname");
		String email=request.getParameter("emailId");
		String password=request.getParameter("pass");
		
//		System.out.println("client");
//		System.out.println(fname);
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		log.info(request);
		Boolean status = false;
		try {
			 
			ClientConfig clientConfig = new DefaultClientConfig();
			 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			 
			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource(Constants.mongodb_register_url);
//			String s =;
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("fname", fname);
			formData.add("lname", lname);
			formData.add("email", email);
			formData.add("password", password);
			
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .accept("application/json")
			    .post(ClientResponse.class, formData);
//			System.out.println("Client");
			log.info(restResponse);
			if (restResponse.getStatus() != 200) {
				log.error(restResponse.getStatus());
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
 
			String statusString = restResponse.getEntity(String.class);
			System.out.println(statusString);
			log.info(statusString);
			
			 json = (JSONObject) parser.parse(statusString);
//			System.out.println(json);
			
			status = Boolean.parseBoolean(json.get("Success").toString());
			
			System.out.println(status);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		if(status){
			RequestDispatcher rd=request.getRequestDispatcher("grocerykart.jsp");
			log.info(rd);
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
			log.info(rd);
			rd.forward(request, response);
		}
	}

}
