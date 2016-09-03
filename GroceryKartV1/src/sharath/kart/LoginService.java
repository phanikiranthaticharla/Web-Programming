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

@Path("/loginservices")
public class LoginService {
	final static Logger log= Logger.getLogger(LoginService.class);
	@Path("/checkuservalidity")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)

	public Response isValidUser(MultivaluedMap<String, String> formParam) throws Exception {
//		boolean response = false;

		Connection conn = Dbconn.getConnection();
		log.info(formParam);
		conn.createStatement();
		Statement pass_st = conn.createStatement();
		Statement uid_st = conn.createStatement();
		ResultSet uid,pass;
		User user = new User();

		uid = uid_st.executeQuery("select * from members where uname='" + formParam.getFirst("username") +"'");
		log.info(uid);
		if(uid.next()){
			pass = pass_st.executeQuery("select * from members where uname='" + formParam.getFirst("username") + "' and pass='" + formParam.getFirst("password") + "'");
			log.info(pass);
			if (pass.next()) {

				user.setLastlogin(pass.getString("lastlogin"));
				user.setLastfailed(pass.getString("lastfailed"));
				user.setFailed_attempts(pass.getString("failed_attempts"));
				user.setSuccess("true");
				pass_st.executeUpdate("UPDATE members SET lastlogin=now() WHERE id="+pass.getString("id")+";");
			} else {
				user.setSuccess("false");
				uid_st.executeUpdate("UPDATE members SET lastfailed=now(), failed_attempts=failed_attempts+1 WHERE id="+uid.getString("id")+";");
			}
		}

		else{
			user.setSuccess("false");
//			response = false;
		}
		log.info(user);
		Dbconn.closeConnection(conn);
		return Response.ok().entity(user).build();
	}

	@GET
	@Path("/availableusername/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User availableUsername(@PathParam("username") String username) {
		User user = new User();
		user.setLastlogin("daada");
		user.setLastfailed("baaba");
		return user;
	}
	@GET
	@Path("/thika")
	public Response thika() {
		return Response.status(200).entity("Thika is called").build();
	}
	
}
