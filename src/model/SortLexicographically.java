package model;
import java.util.Comparator;

public class SortLexicographically implements Comparator<Flight>{

	private String text;
	
	public SortLexicographically(String txt) {
		text = txt;
	}
	
	@Override
	public int compare(Flight one, Flight two) {
		int comparation;
		String nameOne = "";
		String nameTwo = "";
		switch (text) {
		case "Airline":
			nameOne = one.getAirline();
			nameTwo = two.getAirline();
			break;

		case "Flight":
			nameOne = one.getFlight();
			nameTwo = two.getFlight();
			break;
		case "Destination":
			nameOne = one.getDestination();
			nameTwo = two.getDestination();
			break;

		default:
			break;
		}

		if (nameOne.compareTo(nameTwo) > 0) {
			comparation = 1;
		} else if (nameOne.compareTo(nameTwo) < 0) {
			comparation = -1;
		} else {
			comparation = 0;
		}

		return comparation;
	}

}
