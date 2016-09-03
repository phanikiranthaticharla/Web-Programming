package sharath.kart;

public class LoginBean {

	private String name;
	private String password;
	private String lastlogin;
	private String lastfailed;
	private String failed_attempts;
	private String lname;
	private String email;
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

	public String getFailed_attempts() {
		return failed_attempts;
	}

	public void setFailed_attempts(String failed_attempts) {
		this.failed_attempts = failed_attempts;
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

	/**
     * Default constructor. 
     */
    public LoginBean() {
    	name = "";
    	password = "";
    }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
