package userinterface;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Airport;
import model.Flight;
import model.SortLexicographically;
import model.Sorting;
import threads.threadClock;

public class AirportController {

	Airport airp = new Airport("APOII");
	Sorting sorting = new Sorting();
	SortLexicographically sortL;
	@FXML
	private Label lbTime;
	@FXML
	private TableView<Flight> tableView;
	@FXML
	private TableColumn<Flight, Date> colum_time;
	@FXML
	private TableColumn<Flight, String> colum_airline;
	@FXML
	private TableColumn<Flight, String> colum_flight;
	@FXML
	private TableColumn<Flight, String> colum_to;
	@FXML
	private TableColumn<Flight, String> colum_gate;
	@FXML
	private TableColumn<Flight, String> colum_date;
	@FXML
	private ToggleGroup order;
	@FXML
	private RadioButton order_time;
	@FXML
	private RadioButton order_gate;
	@FXML
	private RadioButton order_date;
	@FXML
	private RadioButton order_airline;
	@FXML
	private RadioButton order_flight;
	@FXML
	private RadioButton order_destination;
	@FXML
	private ToggleGroup search;
	@FXML
	private RadioButton search_time;
	@FXML
	private RadioButton search_airline;
	@FXML
	private RadioButton search_flight;
	@FXML
	private RadioButton search_destination;
	@FXML
	private TextField tfSearch;
	@FXML
	private TextField tfNumberOfFlights;
	@FXML
	private Button btGenerate;
	@FXML
	private ImageView img_back;
	@FXML
	private ImageView img_next;

	public void initialize() {
		colum_time.setCellValueFactory(new PropertyValueFactory<>("timeS"));
		colum_airline.setCellValueFactory(new PropertyValueFactory<>("airline"));
		colum_flight.setCellValueFactory(new PropertyValueFactory<>("flight"));
		colum_to.setCellValueFactory(new PropertyValueFactory<>("destination"));
		colum_gate.setCellValueFactory(new PropertyValueFactory<>("gate"));
		colum_date.setCellValueFactory(new PropertyValueFactory<>("dateS"));
		airp.generateFlights(25);
		Flight[] flights = null;
		flights = airp.getFlights().toArray(new Flight[airp.getFlights().size()]);
		List<Flight> list = Arrays.stream(sorting.flightSorting(flights)).collect(Collectors.toList());
		ObservableList<Flight> Obsflights = FXCollections.observableArrayList(list);
		airp.setFlights(Obsflights);
		tableView.setItems(airp.getFlights());
		threadClock th = new threadClock(this);
		th.setDaemon(true);
		th.start();
		order.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (order.getSelectedToggle() != null) {
					RadioButton selectedRadioButton = (RadioButton) order.getSelectedToggle();
					String toogleGroupValue = selectedRadioButton.getText();
					sortL = new SortLexicographically(toogleGroupValue);
					if (toogleGroupValue.equals("Airline") || toogleGroupValue.equals("Flight")
							|| toogleGroupValue.equals("Destination")) {
						Collections.sort(airp.getFlights(), sortL);
						tableView.setItems(airp.getFlights());
					} else {
						Flight[] flights = null;
						flights = airp.getFlights().toArray(new Flight[airp.getFlights().size()]);
						switch (toogleGroupValue) {
						case "Date":
							List<Flight> list = Arrays.stream(sorting.flightSorting(flights))
									.collect(Collectors.toList());
							ObservableList<Flight> Obsflights = FXCollections.observableArrayList(list);
							tableView.setItems(Obsflights);
							break;

						case "Time":
							tableView.setItems(sorting.orderByTime(airp.getFlights()));
							break;

						case "Gate":
							tableView.setItems(sorting.orderByGate(airp.getFlights()));
							break;

						}
					}
				}
			}
		});
	}

	@FXML
	public void GenerateFlights(ActionEvent event) {
		ObservableList<Flight> fli = FXCollections.observableArrayList();
		airp.setFlights(fli);
		try {
			int num = Integer.parseInt(tfNumberOfFlights.getText());
			airp.generateFlights(num);
			tableView.setItems(airp.getFlights());
			tfNumberOfFlights.setText("");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error", "Debe digitar un numero", JOptionPane.ERROR_MESSAGE);
			tfNumberOfFlights.requestFocus();
		}
	}

	public void clock() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Calendar cal = Calendar.getInstance();
				int second = cal.get(Calendar.SECOND);
				int minute = cal.get(Calendar.MINUTE);
				int hour = cal.get(Calendar.HOUR);
				lbTime.setText(hour + ":" + (minute) + ":" + second);
			}
		});
	}
}
