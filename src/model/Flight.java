package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight implements Comparable<Flight>{

	private Date date;
	private String dateS;
	private Date time;
	private String timeS;
	private String airline;
	private String flight;
	private String destination;
	private int gate;
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public Flight(Date date, String dateS, Date time, String timeS, String airline, String flight, String destination, int gate) {
		this.date = date;
		this.dateS = dateS;
		this.timeS = timeS;
		this.time = time;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
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
	public int compareTo(Flight o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
