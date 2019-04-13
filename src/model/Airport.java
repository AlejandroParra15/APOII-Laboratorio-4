package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Airport {

	private String name;
	private ObservableList<Flight> flights;
	private List<String> airlines;
	private String[] abecedario = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	private List<String> destinations;
	private int[] gates = new int[20];
	GregorianCalendar gc = new GregorianCalendar();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public Airport(String name) {
		this.name = name;
		this.flights = FXCollections.observableArrayList();
		airlines = new ArrayList<>();
		destinations = new ArrayList<>();
		generateGates();
		loadFligths();
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

	public String generateNumFlights() {
		String num = "";
		int numAleatorio = (int) Math.floor(Math.random() * (100 - 999) + 999);
		num = abecedario[randBetween(0, 25)] + abecedario[randBetween(0, 25)] + "-" + numAleatorio;
		return num;
	}

	public void generateFlights(int size) {
		Date date = null;
		for (int i = 0; i < size; i++) {
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

			Flight fg = new Flight(date, dateS, start, displayTime,
					airlines.get((int) Math.floor(Math.random() * airlines.size())), generateNumFlights(),
					destinations.get((int) Math.floor(Math.random() * destinations.size())),
					gates[(int) Math.floor(Math.random() * 20)]);
			flights.add(fg);
		}
	}

	public int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public void loadFligths() {
		String path = "";
		int line = 0;
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				path = "data/airlines.txt";
				line = 1;
			} else if (i == 1) {
				path = "data/destinations.txt";
				line = 2;
			}

			BufferedReader br = null;
			FileReader fr = null;
			try {
				fr = new FileReader(path);
				br = new BufferedReader(fr);

				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					String[] parts = sCurrentLine.split(",");
					for (int j = 0; j < parts.length; j++) {
						if (line == 1)
							airlines.add(parts[j]);
						if (line == 2)
							destinations.add(parts[j]);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();

					if (fr != null)
						fr.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
