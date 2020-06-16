import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	private static HashMap<Date, ArrayList<Customer>> customersForTourDate = new HashMap<>();
	private static int customerIDCounter = 1;

	public static void main(String[] args) {
		// explain what this does:
		// this program is meant to test collecting email address from customers
		// going on a ghost tour. It stores these in a database and uses that
		// database to remind customers the day before the tour and ask for
		// feedback the day after.
		System.out.println("this program is meant to test collecting email address from customers\r\n" + 
				"going on a ghost tour. It stores these in a database and uses that\r\n" + 
				"database to remind customers the day before the tour and ask for\r\n" + 
				"feedback the day after.");
		System.out.println();

		// the first part of the test is collecting email addresses from customers
		// in the checkout phase of booking a tour
		// for each customer, create a CheckoutInstance ci
		// after customer provides the necessary fields, they press "next" (ci.next())
		// enter an email address and the date (m/d/y) of their tour
		// add customer object to hashmap
		// enter -999 for the email address when you're done
		System.out.println("Would you like to enter another customer? (y/n)");
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		while (answer.equals("y")) {
			CheckoutInstance checkout = new CheckoutInstance(customerIDCounter++, getDateFromUser());
			boolean doneEnteringEmail = false;
			while (!doneEnteringEmail) {
				doneEnteringEmail = checkout.next(getEmailFromUser());
			}
			// customer is now initialized
			addCustomerToDatabase(checkout);

			System.out.println("Would you like to enter another customer? (y/n)");
			answer = scanner.nextLine();
		}

		// the second part of the test is entering a date D
		// enter a date (m/d/y) -- use helper
		// every email address one day after D will receive an email reminder
		// every email address one day before D will receive an email for feedback
		System.out.println("Would you like to enter a date to send out emails? (y/n)");
		answer = scanner.nextLine();
		while (answer.equals("y")) {
			Date date = getDateFromUser();
			ArrayList<Customer> customersAfterDay = customersForTourDate.get(date.getDayAfter());
			if (customersAfterDay != null) {
				for (Customer c : customersAfterDay) {
					c.sendReminder();
				}
			} else {
				System.out.println("No customers to remind for tours on " + date.getDayAfter() + ".");
			}
			ArrayList<Customer> customersBeforeDay = customersForTourDate.get(date.getDayBefore());
			if (customersBeforeDay != null) {
				for (Customer c : customersBeforeDay) {
					c.requestFeedback();
				}
			} else {
				System.out.println("No customers to request feedback for tours on " + date.getDayBefore() + ".");
			}

			System.out.println("Would you like to enter a date to send out emails? (y/n)");
			answer = scanner.nextLine();
		}

	}

	private static void addCustomerToDatabase(CheckoutInstance checkout) {
		// see if this customer has an email
		// if no email, don't add to database
		if (checkout.getCustomer().getEmail() == null) {
			return;
		}
		// if email, add to database

		// if the db doesn't contain this date, create a new list with just this
		// customer and add it to our db
		if (!customersForTourDate.containsKey(checkout.getCustomer().getDate())) {
			ArrayList<Customer> newList = new ArrayList<Customer>();
			newList.add(checkout.getCustomer());
			customersForTourDate.put(checkout.getCustomer().getDate(), newList);
		}
		// else, add this customer to the list of customers at this date
		else {
			ArrayList<Customer> custAtDate = customersForTourDate.get(checkout.getCustomer().getDate());
			custAtDate.add(checkout.getCustomer());
		}
	}

	private static String getEmailFromUser() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter you email address:");
		String email = scanner.nextLine();
		return email;
	}

	private static Date getDateFromUser() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the tour date you are going on.");
		System.out.println("First enter the month (1-12):");
		int m = scanner.nextInt();
		System.out.println("Next enter the day (1-31):");
		int d = scanner.nextInt();
		System.out.println("Next enter the year (2020+):");
		int y = scanner.nextInt();
		Date date = new Date(m, d, y);
		return date;
	}

}
