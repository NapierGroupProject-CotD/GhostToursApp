package db;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
//import java.util.Date;

import dataObjects.Guide;
import dataObjects.Guides;
import dataObjects.Tour;
import dataObjects.Tours;

public class dbConnect {
	private Connection 	connection = null;
	private Statement 	statement = null;
	private ResultSet 	resultSet = null;

	public void readDataBase() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");		/* Load the MySQL driver. One per DB instance. */
			connection = DriverManager.getConnection( 	/* Setup the connection with the DB */
					"jdbc:mysql://localhost/ghost?" + "user=admin&password=admin"
			);
		      statement = connection.createStatement();
		      resultSet = statement.executeQuery( "select * from ghost.staff" );
		//      writeMetaData(resultSet);
		      getGuides(resultSet);

		} catch (SQLException e) {
			throw e;
		} 		
		statement.close();
		resultSet.close();
		
		try {
		      statement = connection.createStatement();
		      resultSet = statement.executeQuery( "select * from ghost.tour" );
		//      writeMetaData(resultSet);
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
			String type = typeQuery(resultSet.getInt("type_id"));
			Tour tour = new Tour(date, time, type);
			tours.add(tour);
		}
		System.out.println(tours.toString());		
	}

	private String typeQuery(int type_id) {
		String type = "";
		ResultSet newResultSet;
		try {
			Statement newStatement = connection.createStatement();
			newResultSet = newStatement.executeQuery( "select type_name from ghost.tour_type where type_id = " + type_id );
			while (newResultSet.next()){
				type = newResultSet.getString("type_name");
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return type;
	}

/*	private void writeMetaData(ResultSet resultSet) throws SQLException {
		System.out.println("The columns in the table are: ");
		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
		}
	}*/

	private void getGuides(ResultSet resultSet) throws SQLException {
		Guides guides = new Guides();
		while (resultSet.next()) {
			Guide guide = new Guide(resultSet.getString("name"), getAvailability(resultSet.getInt("staff_id")));
			guides.add(guide);
		}
		System.out.println(guides.toString());
	}

	private ArrayList<DayOfWeek> getAvailability(int staff_id) {
		ArrayList<DayOfWeek> availability = new ArrayList<DayOfWeek>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery( "select * from ghost.available_day where staff_id = " + staff_id );
		
			while (resultSet.next()) {	
				availability.add( DayOfWeek.valueOf(resultSet.getString("day")) );
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