package sharath.kart;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

import javax.ws.rs.PathParam;
import sharath.kart.db.MongoDB;

@Path("/mongodbregisterservices")
public class MongoDBRegisterService {
	final static Logger log= Logger.getLogger(MongoDBRegisterService.class);
	@Path("/mongoregisteruser")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)

	public Response registerUser(MultivaluedMap<String, String> formParam) throws Exception {
		log.info(formParam);
		MongoDB db = new MongoDB();
		Success s = new Success();
		s.setSuccess(MongoDB.registerUser(formParam));
		db.closeconnection();
		log.info(s);
		return Response.ok().entity(s).build();
	}

	@Path("/mongoreviews")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)

	public Response mongoreviews(MultivaluedMap<String, String> formParam) throws Exception {
		MongoDB db = new MongoDB();
		log.info(formParam);
		String pname =formParam.getFirst("pname").toString();
		String uname = formParam.getFirst("user").toString();
		Review r = new Review();

		//check whether user has purchased item
		DBCollection table1 = db.getCollection("orders");

		BasicDBObject andQuery1 = new BasicDBObject();

		BasicDBObject allQuery1 = new BasicDBObject();
		allQuery1.put("email", uname);
		allQuery1.put("itemlist", new BasicDBObject("$elemMatch",new BasicDBObject("item_name",pname)));

		andQuery1.put("_id", 0);
		log.info(allQuery1);
		DBCursor cursor1 = table1.find(allQuery1,andQuery1);
		log.info(cursor1);
		if(cursor1.count()>0){
			r.setPurchased("true");
		}else{
			//			System.out.println("Server : ELSE");
			r.setPurchased("false");
		}
		//		System.out.println("Server: "+pid);

		BasicDBObject andQuery = new BasicDBObject();
		BasicDBObject allQuery = new BasicDBObject();
		allQuery.put("item_name",pname);
		andQuery.put("_id", 0);
		//		System.out.println(allQuery.toString());
		log.info(andQuery);
		log.info(allQuery);
		List<Review> review_list = new ArrayList<Review>();
		try{
			DBCollection table = db.getCollection("reviews");

			DBCursor cursor = table.find(allQuery,andQuery);
			log.info(cursor);
			//			int i=0;
			for(DBObject c : cursor) {
				Review rv = new Review();
				String user = c.get("user").toString();
				String review = c.get("review").toString();
				rv.setUser(user);
				rv.setReview(review);
				rv.setSuccess(r.getSuccess());
				review_list.add(rv);

			}
			if(cursor.size()>0){
				r.setReview_list(review_list);
				r.setSuccess("true");
			}else{
				r.setSuccess("false");
			}
			//			System.out.println("Server : "+review_list);
		}catch (MongoException e) {
			log.error(e);
			r.setSuccess("false");
			e.printStackTrace();
		}
		db.closeconnection();
		log.info(r);
		return Response.ok().entity(r).build();
	}

	@Path("/addmongoreviews")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)

	public Response addmongoreviews(MultivaluedMap<String, String> formParam) throws Exception {
		MongoDB db = new MongoDB();
		log.info(formParam);
		String pname =formParam.getFirst("pname").toString();
		String user =formParam.getFirst("user").toString();
		String review =formParam.getFirst("review").toString();

		//		System.out.println("Server: "+pid);

		BasicDBObject Query = new BasicDBObject();

		Query.put("item_name", pname);
		Query.put("user", user);
		Query.put("review", review);
		log.info(Query);
		Success s = new Success();
		try{
			DBCollection table = db.getCollection("reviews");

			table.insert(Query);
			s.setSuccess("true");

		}catch (MongoException e) {
			log.error(e);
			s.setSuccess("false");
			e.printStackTrace();
		}
		db.closeconnection();
		log.info(s);
		return Response.ok().entity(s).build();
	}




	@GET
	@Path("/mongoregisterservice/deleteuser/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String availableUsername(@PathParam("username") String username) {
		MongoDB db = new MongoDB();
		db.closeconnection();
		return MongoDB.deleteUser(username);


	}
}
