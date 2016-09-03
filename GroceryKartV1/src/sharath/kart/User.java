package sharath.kart;

public class User {

	private String lastlogin;
	private String lastfailed;
	private String success;
	private String failed_attempts;
	private String first_name;
	private String email;
	private String lname;
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getFailed_attempts() {
		return failed_attempts;
	}


	public void setFailed_attempts(String failed_attempts) {
		this.failed_attempts = failed_attempts;
	}


	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}


	/**
     * Default constructor. 
     */
    public User() {
    	lastlogin = "";
    	lastfailed = "";
    }
	
	
    public String getLastlogin() {
		return lastlogin;
	}



	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}



	public String getLastfailed() {
		return lastfailed;
	}



	public void setLastfailed(String lastfailed) {
		this.lastfailed = lastfailed;
	}




	
	
	@Override
	public String toString() {
		return new StringBuffer(" lastlogin : ").append(this.lastlogin)
				.append(" lastfailed : ").append(this.lastfailed)
				.append(" failed_attempts : ").append(this.failed_attempts)
				.append(" first_name : ").append(this.first_name)
				.append(" success : ").append(this.success)
				.toString();
	}
}
