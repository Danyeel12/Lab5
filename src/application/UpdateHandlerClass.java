
package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateHandlerClass {
	  // Method to update player and playerandgame records in the database
	public static void updatePlayerRecord(String pPlayerID, String pProvince, String pPostalCode, String gGameScore) throws SQLException, ClassNotFoundException, SQLException
	{
		Statement stmt = null;
		String updatePStmt = "Begin\n" + "Update player\n" + 
			    "Set Province ='" + pProvince + "',\n" + 
			    "Postal_code = '" + pPostalCode + "'\n" +
			    "Where Player_ID =" + pPlayerID + ";\n"+
			    "Commit;\n" + 
			    "End;";

		
		String updateGStmt = "Begin\n" + "Update playerandgame\n" + 
			    "Set score = " + gGameScore + "\n" +
			    "Where Player_ID = " + pPlayerID + ";\n" +
			    "Commit;\n" +
			    "End;";
	
		try {
			Database.dbConnect();
			Connection connection = Database.conn;
			 // Execute the update statements
			stmt = connection.createStatement();
			stmt.executeUpdate(updatePStmt);
			stmt.executeUpdate(updateGStmt);
		}catch(SQLException e) {
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}finally {
			 // Close the statement and disconnect from the database
			if (stmt != null) {
				stmt.close();
			}
			Database.dbDisconnect();
		}
	}
}
