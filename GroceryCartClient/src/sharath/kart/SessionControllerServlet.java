package sharath.kart;

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

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.api.json.JSONConfiguration;
import sharath.kart.constants.*;

/**
 * Servlet implementation class SessionControllerServlet
 */
@WebServlet("/SessionControllerServlet")
public class SessionControllerServlet extends HttpServlet {
	
//	static
//	   {
//	      System.setProperty("javax.net.ssl.trustStore", "cert/client");
//	      System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
//	      System.setProperty("javax.net.ssl.keyStore", "cert/client");
//	      System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
//	   }
//	
	final static Logger log= Logger.getLogger(SessionControllerServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
//		PrintWriter out=response.getWriter();
		log.info("request : "+request.toString());
		String name=request.getParameter("login");
		String password=request.getParameter("password");
		
		LoginBean bean=new LoginBean();
		bean.setName(name);
		bean.setPassword(password);
		request.setAttribute("bean",bean);
		
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		
		Boolean status = false;
		try {
			 
			ClientConfig clientConfig = new DefaultClientConfig();
			 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			 
			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource(Constants.mongodb_login_url);
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("username", name);
			formData.add("password", password);
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			    .accept("application/json")
			    .post(ClientResponse.class, formData);
			
			log.info("response: "+restResponse);
			if (restResponse.getStatus() != 200) {
				log.error(restResponse.getStatus());
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
 
			String statusString = restResponse.getEntity(String.class);
			log.info("statusString: "+statusString);
			System.out.println("client: "+statusString);
			
			
			 json = (JSONObject) parser.parse(statusString);
//			System.out.println(json);
			
			status = Boolean.parseBoolean(json.get("success").toString());
		} catch (Exception e) {
			log.error(e);
		}
		
		if(status){
			bean.setLastfailed(json.get("lastfailed").toString());
			bean.setLastlogin(json.get("lastlogin").toString());
			bean.setFailed_attempts(json.get("failed_attempts").toString());
			bean.setName(json.get("first_name").toString());
			bean.setLname(json.get("lname").toString());
			bean.setEmail(json.get("email").toString());
			request.setAttribute("bean",bean);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", bean.getEmail());
			session.setAttribute("lname", bean.getLname());
			session.setAttribute("fname", bean.getName());
			session.setAttribute("lastlogin", bean.getLastlogin());
			session.setAttribute("failed_attempts", bean.getFailed_attempts());
			session.setAttribute("lastfailed", bean.getLastfailed());
			session.setAttribute("prod", "false");
			RequestDispatcher rd=request.getRequestDispatcher("user_login.jsp");
			log.info("request :" +rd);
			rd.forward(request, response);
		}
		else{
			
			RequestDispatcher rd=request.getRequestDispatcher("grocerykart.jsp");
			log.info("request :" +rd);
			rd.forward(request, response);
		}
	}

}
