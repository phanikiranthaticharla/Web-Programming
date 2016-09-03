package sharath.kart;

import java.util.Collection;

public class Success {
	
	public String Success;

	public String getSuccess() {
		return Success;
	}

	public Collection<? extends Review> setSuccess(String success) {
		Success = success;
		return null;
	}
	
	public String toString() {
		return new StringBuffer(" success : ").append(this.Success)
				.toString();
	}

}
