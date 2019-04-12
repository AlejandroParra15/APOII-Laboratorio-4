package model;

import javafx.collections.ObservableList;

public class Sorting {

	// selection method
	public Flight[] flightSorting(Flight[] flights) {
		for (int I = 0; I < flights.length - 1; I++) {
			int min = I;
			for (int J = I + 1; J < flights.length; J++) {
				Flight minValue = flights[min];
				Flight current = flights[J];
				if (current.compareTo(minValue) < 0) {
					min = J;
				}
			}
			Flight temp = flights[min];
			flights[min] = flights[I];
			flights[I] = temp;
		}
		return flights;
	}

	// bubble method
	public ObservableList<Flight> orderByGate(ObservableList<Flight> flights) {
		for (int i = flights.size(); i > 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				Flight f1 = (Flight) flights.get(j);
				Flight f2 = (Flight) flights.get(j + 1);

				if (f1.compareToGate(f2) > 0) {
					flights.set(j, f2);
					flights.set(j + 1, f1);
				}
			}
		}
		return flights;
	}
	
	// insertion method
	public ObservableList<Flight> orderByTime (ObservableList<Flight> flights) {
	      for (int i=1; i < flights.size(); i++) {
	         Flight aux = flights.get(i);
	         int j;
	         for (j=i-1; j >= 0 && flights.get(j).compareToTime(aux) > 0; j--){
	        	 flights.set(j+1, flights.get(j));
	          }
	         flights.set(j+1,aux);
	      }
		return flights;
	   }

}
