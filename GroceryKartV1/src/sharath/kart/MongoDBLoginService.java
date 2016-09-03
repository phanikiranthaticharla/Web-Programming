package sharath.kart;


import java.net.InetSocketAddress;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

import net.spy.memcached.MemcachedClient;

import javax.ws.rs.PathParam;
import sharath.kart.db.MongoDB;

@Path("/mongodbloginservices")
public class MongoDBLoginService {
	final static Logger log= Logger.getLogger(MongoDBLoginService.class);
	static MemCacheUtil m = new MemCacheUtil();
	@Path("/mongologinuser")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)

	public Response registerUser(MultivaluedMap<String, String> formParam) throws Exception {
		log.info(formParam);
		MongoDB db = new MongoDB();
		User user = null;
		user = MongoDB.loginUser(formParam);
		db.closeconnection();
		log.info(user);
		return Response.ok().entity(user).build();
	}

	@Path("/mongoupdateuser")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)

	public Response updateUser(MultivaluedMap<String, String> formParam) throws Exception {
		log.info(formParam);
		MongoDB db = new MongoDB();
		System.out.println("server");
		String fname = formParam.getFirst("fname").toString();
		String lname = formParam.getFirst("lname").toString();
		String email = formParam.getFirst("email").toString();
		Success s = new Success();
		DBCollection table = MongoDB.getCollection("user");
		try{
			BasicDBObject query = new BasicDBObject();
			query.put("email", email);

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("fname", fname);
			newDocument.put("lname", lname);

			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);
			log.info(query);
			log.info(newDocument);
			log.info(updateObj);
			table.update(query, updateObj);
			s.setSuccess("true");
			db.closeconnection();
		}catch (MongoException e) {
			log.error(e);
			s.setSuccess("false");
			e.printStackTrace();
		}
		log.info(s);
		return Response.ok().entity(s).build();
	}

	@Path("/mongoordercheckout")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)

	public Response OrderCheckout(MultivaluedMap<String, String> formParam) throws Exception {
		MongoDB db = new MongoDB();
		System.out.println("server");
		log.info(formParam);

		String email = formParam.getFirst("email").toString();
		String items = formParam.getFirst("items").toString();

		if(m.getFromCache(email)!=null){
			log.info("cache hit! Removing from Cache");
			log.info(m.removeFromCache(email));
			//			json = (JSONObject)parser.parse(m.getFromCache(email).toString());
			//			k = false;
		}
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		json = (JSONObject) parser.parse(items);
		DBObject orderdata = (DBObject)JSON.parse(json.toString());
		orderdata.put("email", email);

		log.info(orderdata.toString());

		Success s = new Success();

		DBCollection table = MongoDB.getCollection("orders");
		try{
			table.insert(orderdata);
			s.setSuccess("true");
			db.closeconnection();
		}catch (MongoException e) {
			log.error(e);
			s.setSuccess("false");
			e.printStackTrace();
		}
		log.info(s);
		return Response.ok().entity(s).build();
	}

	@SuppressWarnings("unchecked")
	@Path("/mongogetorders")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)

	public Response GetOrders(MultivaluedMap<String, String> formParam) throws Exception {
		MongoDB db = new MongoDB();
//		System.out.println("server");
		log.info(formParam);
		JSONObject json = new JSONObject();
		JSONParser parser = new JSONParser();
		String email = formParam.getFirst("email").toString();
		Success s = new Success();
		DBCursor cursor=null;
		//		boolean k = true;
		if(m.getFromCache(email)!=null){
			log.info("cache hit!");
			log.info(m.getFromCache(email).toString());
			json = (JSONObject)parser.parse(m.getFromCache(email).toString());
			//			k = false;
		}
		else{
			DBCollection table = MongoDB.getCollection("orders");
			JSONArray jsonarray = new JSONArray();
			try{
				BasicDBObject andQuery = new BasicDBObject();
				BasicDBObject allQuery = new BasicDBObject();
				allQuery.put("email", email);
				andQuery.put("_id", 0);
				log.info(allQuery);
				log.info(andQuery);
				cursor = table.find(allQuery,andQuery);
				log.info(cursor);
				int i=0;
				for(DBObject c : cursor){
					JSONObject jsonobj = new JSONObject();
					jsonobj.put(i, c);
					jsonarray.add(jsonobj);
					i++;
				}
				s.setSuccess("true");
				db.closeconnection();
			}catch (MongoException e) {
				log.error(e);
				s.setSuccess("false");
				e.printStackTrace();
			}

			json.put("success", s.getSuccess());
			json.put("result", jsonarray);
			//		System.out.println(json);
//			System.out.println("here in server");

			m.putInCache(email,json.toString());

		}
		log.info(json.toString());
		return Response.ok().entity(json.toString()).build();
	}


	@GET
	@Path("/mongologinservice/finduser/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String availableUsername(@PathParam("username") String username) {
		MongoDB db = new MongoDB();
		//		db.closeconnection();
		return MongoDB.findUser(username);


	}
}
