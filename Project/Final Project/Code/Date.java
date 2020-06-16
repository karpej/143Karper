
public class Date {

	private int month;
	private int day;
	private int year;
	
	public Date(int m, int d, int y) {
		month = m;
		day = d;
		year = y;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getYear() {
		return year;
	}
	
	@Override
	public boolean equals(Object o) {
		if(((Date) o).getMonth() == month && ((Date) o).getDay() == day && ((Date) o).getYear() == year) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		String dateString = "" + month + day + year;
		return Integer.valueOf(dateString);
	}
	
	public Date getDayAfter() {
		// TODO make this work for days that are at the end of the month
		return new Date(month, day + 1, year);
	}
	
	public Date getDayBefore() {
		// TODO make this work for days that are at the beginning of the month
		return new Date(month, day - 1, year);
	}
	
	@Override
	public String toString() {
		return month + "/" + day + "/" + year;
	}
}
