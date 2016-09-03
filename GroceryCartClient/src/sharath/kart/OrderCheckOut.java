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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
@WebServlet("/OrderCheckOut")
public class OrderCheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log= Logger.getLogger(OrderCheckOut.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderCheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//		PrintWriter out=response.getWriter();
		
		log.info(request);
		String subject="Your Order Status";
		String content = "Your order recieved!";
		
		
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) parser.parse(request.getParameter("item"));
		} catch (ParseException e) {
			log.error(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean status = false;
		HttpSession session = request.getSession();
		String email = session.getAttribute("user").toString();
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("email", email);
		formData.add("items", json.toString());
		
		MultivaluedMap<String, String> formData1 = new MultivaluedMapImpl();
		formData1.add("from", "sharath.the.cool@gmail.com");
		formData1.add("subject", subject);
		formData1.add("content", content);
		log.info(formData);
		try {

			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource(Constants.mongodb_checkout_url);
			ClientResponse restResponse = webResource
					.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.accept("application/json")
					.post(ClientResponse.class, formData);
			
			//send email
			WebResource webResource1 = client.resource(Constants.email_url);
			ClientResponse restResponse1 = webResource1
				    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				    .accept("application/json")
				    .post(ClientResponse.class, formData1);
			log.info(restResponse);
			log.info(restResponse1);
			if (restResponse.getStatus() != 200 || restResponse1.getStatus() != 200) {
				log.error(restResponse.getStatus());
				log.error(restResponse1.getStatus());
				throw new RuntimeException("Failed : HTTP error code : " + (restResponse.getStatus()!=200?restResponse.getStatus():restResponse1.getStatus()));
			}

			String statusString = restResponse.getEntity(String.class);
//			System.out.println("client: "+statusString);
			log.info(statusString);

			json = (JSONObject) parser.parse(statusString);
			//			System.out.println(json);

			status = Boolean.parseBoolean(json.get("success").toString());
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

		if(status){
//			System.out.println(request.getCookies().toString());
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
