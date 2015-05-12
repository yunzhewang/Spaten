package pois;

public class Review {

	private String rating;
	private String reviewTitle;
	private String review;
	
	public Review(String rate, String rTitle, String rev) {
		rating = rate;
		if (rTitle.contains("'"))
			reviewTitle = rTitle.replace("'", "''");
		else reviewTitle = rTitle;
		if (rev.contains("'"))
			review = rev.replace("'", "''");
		else review = rev;				
	}

	public void print() {
		String out = "Review:\t rating: " + rating + " \t Title: " + reviewTitle  
				+ " \t review: " + review;
		System.out.println(out);
	}
	
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}