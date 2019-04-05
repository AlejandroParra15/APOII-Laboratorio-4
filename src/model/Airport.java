package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Airport {

	private String name;
	private ObservableList<Flight> flights;
	private String[] airlines = { "AVIANCA", "LATAM", "COPA AIRLINE", "VIVA COLOMBIA", "SATENA", "AEROASIS" };
	private String[] sub = { "AV", "LAT", "COP", "VVC", "SAT", "AEO" };
	private String[] destinations = { "CALI", "BOGOTÁ", "MEDELLIN", "CARTAGENA", "BARRANQUILLA", "LIMA", "QUITO",
			"BUENOS AIRES", "PATAGONIA" };
	private int[] gates = new int[20];
	GregorianCalendar gc = new GregorianCalendar();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public Airport(String name) {
		this.name = name;
		this.flights = FXCollections.observableArrayList();
		generateGates();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObservableList<Flight> getFlights() {
		return flights;
	}

	public void setFlights(ObservableList<Flight> fli) {
		this.flights = fli;
	}

	public void addFlight(Flight fly) {
		flights.add(fly);
	}

	public void generateGates() {
		for (int i = 0; i < gates.length; i++) {
			gates[i] = (int) Math.floor(Math.random() * (0 - 20) + 20);
		}
	}

	public String generateNumFlights(int index) {
		String num = "";
		int numAleatorio = (int) Math.floor(Math.random() * (100 - 999) + 999);
		num = sub[index] + "-" + numAleatorio;
		return num;
	}

	public void generateFlights(int size) {
		Date date = null;
		for (int i = 0; i < size; i++) {
			int index = (int) Math.floor(Math.random() * 6);
			int year = 2019;
			int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
			gc.set(Calendar.YEAR, year);
			gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
			String dateS = (gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-"
					+ gc.get(Calendar.DAY_OF_MONTH));
			try {
				date = sdf.parse(dateS);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			LocalTime start = LocalTime.of(randBetween(1, 23), randBetween(1, 59));
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
					.withLocale(Locale.US);
			String displayTime = start.format(timeFormatter);

			Flight fg = new Flight(date, dateS, null, displayTime, airlines[index], generateNumFlights(index),
					destinations[(int) Math.floor(Math.random() * 7)], gates[(int) Math.floor(Math.random() * 20)]);
			flights.add(fg);
		}
	}

	public int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public void sortbyAirline() {
		Comparator<Flight> comparatorByNameLength = new Comparator<Flight>() {

			@Override
			public int compare(Flight one, Flight two) {
				int comparation;

				String nameOne = one.getAirline();
				String nameTwo = two.getAirline();

				if (nameOne.compareTo(nameTwo) > 0) {
					comparation = 1;
				} else if (nameOne.compareTo(nameTwo) < 0) {
					comparation = -1;
				} else {
					comparation = 0;
				}

				return comparation;
			}

		};
		Collections.sort(flights, comparatorByNameLength);
	}
}
