import java.util.Scanner;

public class CheckoutInstance {

	private Customer c;
	
	public CheckoutInstance(int cID, Date tourDate) {
		c = new Customer(cID, tourDate);
	}
	
	/**
	 * 
	 * @param email
	 * @return true if the customer is ready to move on to the next step, false to ask for email address again
	 */
	public boolean next(String email) {
		Scanner scanner = new Scanner(System.in);
		// TODO the customer clicks "next" after entering an email address
		// if the email is empty (or null), ask the customer if they'd like to provide one
		if(email == null || email.equals("")) {
			System.out.println("Are you sure you would like to not provide an email address? (y/n)");
			String reply = scanner.nextLine();
			if(reply.equals("y")) {
				// customer doesn't want to provide an email
				return true;
			}
			else if(reply.equals("n")) {
				// customer does want to provide an email
				return false;
			}
			else {
				// invalid input 
				System.out.println("You must enter 'y' or 'n' to answer.");
				return false;
			}
		}
		// if email is invalid, tell customer email was invalid
		if(!emailIsValid(email)) {
			System.out.println("Email was invalid: must contain '@' symbol.");
			return false;
		}
		// if email is valid, set the email in the customer object
		c.setEmail(email);
		return true;
	}
	
	/**
	 * Naiive implementation of checking if an email is valid. Checks that it contains the '@' symbol.
	 * @param email Email address to check
	 * @return true if email is valid, false otherwise
	 */
	public boolean emailIsValid(String email) {
		return email.contains("@");
	}

	public Customer getCustomer() {
		return c;
	}
}
