
package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateHandlerClass implements EventHandler<ActionEvent> {
    Text message = new Text();
    // TextFields to store user input
    TextField FIRST_NAME = new TextField();
    TextField LAST_NAME = new TextField();
    TextField ADDRESS = new TextField();
    TextField PROVINCE = new TextField();
    TextField POSTAL_CODE = new TextField();
    TextField PHONE_NUMBER = new TextField();
    TextField PLAYER_ID = new TextField();
    TextField GAME_TITLE = new TextField();
    TextField SCORE = new TextField();
    TextField PLAYING_DATE = new TextField();

    // SQL queries for inserting player, game, and player and game records
    String player = "INSERT INTO player (player_id, first_name, last_name, address, province, postal_code, phone_number) VALUES (PLAYER_seq.NEXTVAL,?,?,?,?,?,?)";
    String game = "INSERT INTO game values(GAME_seq.NEXTVAL,?)";
    String playerandgame = "INSERT INTO playerandgame (player_game_id, game_id, player_id, playing_date, score) values(PLAYER_GAME_seq.NEXTVAL,GAME_seq.CURRVAL,PLAYER_seq.CURRVAL,?,?)";

    @Override
    public void handle(ActionEvent actionEvent) {
        // Extracting user input from TextFields
        String pFirstName = FIRST_NAME.getText();
        String pLastName = LAST_NAME.getText();
        String pAddress = ADDRESS.getText();
        String pProvince = PROVINCE.getText();
        String pPostalCode = POSTAL_CODE.getText();
        String pPhoneNumber = PHONE_NUMBER.getText();
        String gGameTitle = GAME_TITLE.getText();
        int gGameScore = Integer.parseInt(SCORE.getText());
        String pDate = PLAYING_DATE.getText();
        try {
            // Establish database connection
            Database.dbConnect();
            Connection connection = Database.conn;
            
            // Insert player record
            PreparedStatement pStatement = connection.prepareStatement(player);
            pStatement.setString(1, pFirstName);
            pStatement.setString(2, pLastName);
            pStatement.setString(3, pAddress);
            pStatement.setString(4, pProvince);
            pStatement.setString(5, pPostalCode);
            pStatement.setString(6, pPhoneNumber);
            int i = pStatement.executeUpdate();
            message.setText(i + " player information inserted successfully!");
            
            // Insert game record
            PreparedStatement gStatement = connection.prepareStatement(game);
            gStatement.setString(1, gGameTitle);
            int j = gStatement.executeUpdate();
            message.setText(j + " game information inserted successfully!");
            
            // Insert playerandgame record
            PreparedStatement pGStatement = connection.prepareStatement(playerandgame);
            pGStatement.setString(1, pDate);
            pGStatement.setInt(2, gGameScore);
            int k = pGStatement.executeUpdate();
            message.setText(k + " player and game information inserted successfully!");

            // Close the database connection
            Database.dbDisconnect();

            // Set success message
            message.setText("All information inserted successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            // Set error message if an exception occurs
            message.setText("Failed to insert data to database. Please try again!");
            System.out.println(e);
        }
    }
} 