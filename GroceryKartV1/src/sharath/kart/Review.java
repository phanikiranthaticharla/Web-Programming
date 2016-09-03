package sharath.kart;

import java.util.List;


public class Review {

	private String user;
	private String review;
	private String success;
	private List<Review> review_list;
	public List<Review> getReview_list() {
		return review_list;
	}
	public void setReview_list(List<Review> review_list) {
		this.review_list = review_list;
	}

	private String purchased;
	
	public String getPurchased() {
		return purchased;
	}
	public void setPurchased(String purchased) {
		this.purchased = purchased;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return new StringBuffer(" user : ").append(this.user)
				.append(" review : ").append(this.review)
				.append(" purchased : ").append(this.purchased)
				.append(" success : ").append(this.success)
				.toString();
	}
	
}
