package sharath.kart;


import java.sql.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import javax.ws.rs.PathParam;
import sharath.kart.db.Dbconn;

@Path("/registerservices")
public class RegisterService {
	final static Logger log= Logger.getLogger(RegisterService.class);
	@Path("/registeruser")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)

	public Response isValidUser(MultivaluedMap<String, String> formParam) throws Exception {
//		boolean response = false;
		log.info(formParam);
		Connection conn = Dbconn.getConnection();
		
		String user = formParam.getFirst("uname");    
	    String pwd = formParam.getFirst("pass");
	    String fname = formParam.getFirst("fname");
	    String lname = formParam.getFirst("lname");
	    String email = formParam.getFirst("email");
	    
		conn.createStatement();
		
		
		Statement st = conn.createStatement();
		int rs;
		

		rs = st.executeUpdate("insert into members(first_name, last_name, email, uname, pass, regdate) values ('" + fname + "','" + lname + "','" + email + "','" + user + "','" + pwd + "', CURDATE())");
		log.info(rs);
		Success s = new Success();
		if(rs > 0){
			s.setSuccess("true");
		}

		else{
			s.setSuccess("false");
//			response = false;
		}
		Dbconn.closeConnection(conn);
		log.info(s);
		return Response.ok().entity(s).build();
	}

	@GET
	@Path("/registerservice/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User availableUsername(@PathParam("username") String username) {
		User user = new User();
		user.setLastlogin("daada");
		user.setLastfailed("baaba");
		return user;
	}
}
