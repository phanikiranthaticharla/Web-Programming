package sharath.kart;

import java.io.IOException;
import java.util.Enumeration;

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
import org.json.simple.JSONArray;
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
@WebServlet("/GetOrderHistory")
public class GetOrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log= Logger.getLogger(GetOrderHistory.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderHistory() {
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
		String email = session.getAttribute("user").toString();
		boolean status = false;
		JSONParser parser = new JSONParser();
		
		JSONObject json = new JSONObject();
		try {
			 
			ClientConfig clientConfig = new DefaultClientConfig();
			 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			 
			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource(Constants.mongodb_get_orders_url);
//			String s =;
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("email", email);
			log.info(formData);
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
			log.info(statusString);
//			System.out.println(statusString);
			 json = (JSONObject) parser.parse(statusString);
//				System.out.println(json);
				
			 status = Boolean.parseBoolean(json.get("success").toString());

		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		if(status){
			session.setAttribute("prod", "true");
			System.out.println(json.get("result"));
			request.setAttribute("result", json.get("result"));
			LoginBean bean = new LoginBean();
			bean.setEmail(email);
			request.setAttribute("bean",bean);
			
			RequestDispatcher rd=request.getRequestDispatcher("user_login.jsp");
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
