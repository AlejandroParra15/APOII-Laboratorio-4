package model;

import java.time.LocalTime;
import java.util.Date;

public class Flight implements Comparable<Flight> {

	private Date date;
	private String dateS;
	private LocalTime time;
	private String timeS;
	private String airline;
	private String flight;
	private String destination;
	private int gate;

	public Flight(Date date, String dateS, LocalTime start, String timeS, String airline, String flight,
			String destination, int gate) {
		this.date = date;
		this.dateS = dateS;
		this.timeS = timeS;
		this.time = start;
		this.airline = airline;
		this.flight = flight;
		this.destination = destination;
		this.gate = gate;
	}

	public String getDateS() {
		return dateS;
	}

	public void setDateS(String dateS) {
		this.dateS = dateS;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getGate() {
		return gate;
	}

	public void setGate(int gate) {
		this.gate = gate;
	}

	public String getTimeS() {
		return timeS;
	}

	public void setTimeS(String timeS) {
		this.timeS = timeS;
	}

	@Override
	public int compareTo(Flight other) {

		int comparation;
		if (date.after(other.date)) {
			comparation = 1;
		} else if (date.before(other.date)) {
			comparation = -1;
		} else {
			comparation = 0;
		}

		return comparation;
	}

	public int compareToGate(Flight other) {

		int comparation;
		if (gate > other.gate) {
			comparation = 1;
		} else if (gate < other.gate) {
			comparation = -1;
		} else {
			comparation = 0;
		}

		return comparation;
	}
	
	public int compareToTime(Flight other) {

		int comparation;
		if (time.isAfter(other.time)) {
			comparation = 1;
		} else if (time.isBefore(other.time)) {
			comparation = -1;
		} else {
			comparation = 0;
		}

		return comparation;
	}

}
