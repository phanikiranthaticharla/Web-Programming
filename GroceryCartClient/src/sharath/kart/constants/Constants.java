package sharath.kart.constants;

public class Constants {
	
	public static String base_url = "https://localhost:9443/GroceryKartV1/";
	
	
	public static String login_url = base_url+"loginservices/checkuservalidity";
	
	public static String register_url = base_url+"registerservices/registeruser";
	
	public static String mongodb_register_url = base_url+"mongodbregisterservices/mongoregisteruser";
	
	public static String mongodb_review_url = base_url+"mongodbregisterservices/mongoreviews";
	
	public static String mongodb_addreview_url = base_url+"mongodbregisterservices/addmongoreviews";
	
	public static String mongodb_login_url = base_url+"mongodbloginservices/mongologinuser";
	
	public static String mongodb_get_orders_url = base_url+"mongodbloginservices/mongogetorders";
	
	public static String mongodb_checkout_url = base_url+"mongodbloginservices/mongoordercheckout";
	
	public static String mongodb_update_url = base_url+"mongodbloginservices/mongoupdateuser";
	
	public static String email_url = base_url+"mailservices/mailuser";

}
