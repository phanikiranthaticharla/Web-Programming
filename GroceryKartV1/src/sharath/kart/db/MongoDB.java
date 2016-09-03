package sharath.kart.db;

import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import sharath.kart.User;
import sharath.kart.constants.Constants;

@SuppressWarnings({ "deprecation", "resource" })
public class MongoDB {

	public static DB db;
	public MongoClient mongo;
	public MongoDB(){
		this.mongo = new MongoClient("localhost", 27017);
		MongoDB. db = mongo.getDB("grocerykart");
	}


	public static DB getConnection() throws Exception {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("grocerykart");
		return db;
	}

	public static DBCollection getCollection(String collection_name){

		return db.getCollection(collection_name);
	}

	public static String registerUser(MultivaluedMap<String, String> formParam){

		String pwd = formParam.getFirst("password");
		String fname = formParam.getFirst("fname");
		String lname = formParam.getFirst("lname");
		String email = formParam.getFirst("email");

		String status = null;

		try{
			DBCollection table = getCollection("user");
			BasicDBObject document = new BasicDBObject();
			document.put("fname", fname);
			document.put("lname", lname);
			document.put("email", email);
			document.put("password",pwd);
			document.put("failed_attempts",0);
			document.put("lastlogin",new Date());
			document.put("lastfailed",new Date());
			document.put("created_on", new Date());

			table.insert(document);
			status="true";
		}catch (MongoException e) {
			status="false";
			e.printStackTrace();
		}
		return status;
	}

	public static String deleteUser(String user){

		BasicDBObject document = new BasicDBObject();
		document.put("email",user);
		String status = null;
		try{
			DBCollection table = getCollection("user");
			table.remove(document);
			status = user+" removed";
		}catch (MongoException e) {
			status="Unable to remove "+user;
			e.printStackTrace();
		}
		return status;

	}

	public static User loginUser(MultivaluedMap<String, String> formParam){

		String user_name =  formParam.getFirst("username");
		String password =  formParam.getFirst("password");
		
		User user = new User();

		try{
			DBCollection table = getCollection("user");
			BasicDBObject document = new BasicDBObject();
			document.put("email", user_name);
			
			
			DBCursor cursor = table.find(document,new BasicDBObject("_id", 0));

			if(cursor.hasNext()){
				DBObject tobj = cursor.next();
				String pass = tobj.get("password").toString();

				if(password.equals(pass)){
					user.setLastlogin(tobj.get("lastlogin").toString());
					user.setLastfailed(tobj.get("lastfailed").toString());
					user.setFailed_attempts(tobj.get("failed_attempts").toString());
					user.setLname(tobj.get("lname").toString());
					user.setFirst_name(tobj.get("fname").toString());
					user.setEmail(tobj.get("email").toString());
					user.setSuccess("true");

					BasicDBObject newDocument = new BasicDBObject().append("lastlogin", new Date());
					System.out.println(newDocument);
					table.update(new BasicDBObject().append("email", user_name), new BasicDBObject("$set",newDocument));

				}else{

					BasicDBObject newDocument = 
							new BasicDBObject().append("$inc", 
									new BasicDBObject().append("failed_attempts", 1));

					table.update(new BasicDBObject().append("email", user_name), newDocument);
					user.setSuccess("false");

				}

			}else{
				user.setSuccess("false");
			}

		}catch (MongoException e) {
			user.setSuccess("false");
			e.printStackTrace();
		}
		return user;
	}

	public static String findUser(String user){

		BasicDBObject document = new BasicDBObject();
		document.put("email",user);
		String status = null;
		try{
			DBCollection table = getCollection("user");
			DBCursor cursor = table.find(document,new BasicDBObject("_id", 0));
			if(cursor.hasNext()){
				status = "Found User :"+user;
			}
			else{
				status = user+" not found";
			}
		}catch (MongoException e) {
			status="Exception "+user;
			e.printStackTrace();
		}
		return status;

	}
	public void closeconnection(){
		mongo.close();
	}

}
