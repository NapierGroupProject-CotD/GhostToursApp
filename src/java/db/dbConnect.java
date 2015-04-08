package db;

import genetics.Genome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import dataObjects.Guide;
import dataObjects.Guides;
import dataObjects.Tour;
import dataObjects.Tours;

public class DBConnect {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public void write(Genome week) throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost/ghost2?"
							+ "user=root&password=23wexcsd");

			TreeMap<Tour, Guide> forWriting = week.getAll();

			for (Entry<Tour, Guide> entry : forWriting.entrySet()) {
				Tour tour = entry.getKey();
				Guide guide = entry.getValue();

				statement = connection.createStatement();
				statement.executeUpdate("update ghost2.tour set staff_id = "
						+ guide.get_idi() + " where id = " + tour.get_id()
						+ ";");
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			close();
		}
	}

	public void readDataBase() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			connection = DriverManager.getConnection(
			"jdbc:mysql://localhost/ghost2?" + "user=root&password=23wexcsd");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from ghost2.staff_role where role_id = 2");

			getGuides(resultSet);

		} catch (SQLException e) {
			throw e;
		}
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from ghost2.tour");
			getTours(resultSet);

		} catch (SQLException e) {
			throw e;
		} finally {
			close();
		}
	}

	private void getTours(ResultSet resultSet2) throws SQLException {
		Tours tours = new Tours();
		while (resultSet2.next()) {
			LocalDate date = resultSet.getDate("datetime").toLocalDate();
			LocalTime time = resultSet.getTime("datetime").toLocalTime();
			String type = typeQuery(resultSet.getInt("tour_type_id"));
			int id = resultSet.getInt("id");
			Tour tour = new Tour(date, time, type, id);
			tours.add(tour);
		}
	}

	private String typeQuery(int type_id) {
		String type = "";
		ResultSet newResultSet;
		try {
			Statement newStatement = connection.createStatement();
			newResultSet = newStatement
					.executeQuery("select type_name from ghost2.tour_type where id = "
							+ type_id);
			while (newResultSet.next()) {
				type = newResultSet.getString("type_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

	private void getGuides(ResultSet resultSet) throws SQLException {
		Guides guides = new Guides();
		while (resultSet.next()) {
			
			Statement statementTwo = connection.createStatement();
			ResultSet resultSetTwo = statementTwo.executeQuery("select * from ghost2.staff where id = " + resultSet.getInt("staff_id") + ";");
			while (resultSetTwo.next()) {
				Guide guide = new Guide(resultSetTwo.getString("name"),
						getAvailability(resultSetTwo.getInt("id")),
						resultSetTwo.getInt("id"));
						guides.add(guide);
			}
		}
	}

	private ArrayList<DayOfWeek> getAvailability(int staff_id) {
		ArrayList<DayOfWeek> availability = new ArrayList<DayOfWeek>();
		try {
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select * from ghost2.available_day where staff_id = "
							+ staff_id);

			while (resultSet.next()) {
				availability.add(DayOfWeek.valueOf(resultSet.getString("day")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (availability.size() == 0) {
			availability.add(DayOfWeek.MONDAY);
			availability.add(DayOfWeek.TUESDAY);
			availability.add(DayOfWeek.WEDNESDAY);
			availability.add(DayOfWeek.THURSDAY);
			availability.add(DayOfWeek.FRIDAY);
			availability.add(DayOfWeek.SATURDAY);
			availability.add(DayOfWeek.SUNDAY);
		}
		return availability;
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}