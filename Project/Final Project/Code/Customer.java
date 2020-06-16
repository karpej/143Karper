
// TODO combine the EmailAddress and Customer classes

public class Customer {

	private int id;
	private String email;
	private Date date;
	
	public Customer(int id, Date d) {
		this.id = id;
		date = d;
	}

	public void sendReminder() {
		// send email to customer day before
		System.out.println("Reminder sent to customer " + id + " with email address " + 
				email + " about their tour on " + date);
	}
	
	public void requestFeedback() {
		// send email to customer day after tour
		System.out.println("Request for feedback send to customer " + id + " with email address " + email +
				" about their tour on " + date);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public Date getDate() {
		return date;
	}
	
}
