package application;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Game extends Application {
    public Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        GridPane playerGrid = new GridPane();
        // Setting up the player properties
        playerGrid.setPadding(new Insets(0, 10, 0, 10));
        playerGrid.setMinSize(900, 600);
        playerGrid.setAlignment(Pos.BASELINE_LEFT);
        playerGrid.setVgap(5);
        playerGrid.setHgap(4);

        // For setting scene and stage for the player info
        Scene playerScene = new Scene(playerGrid);
        window.setTitle("Player Registration");
        window.setScene(playerScene);
        window.show();

        CreateHandlerClass create = new CreateHandlerClass();

        // Player Information Section
        Text playerHeader = new Text("Player Information");
        Text firstNameLabel = new Text("First Name: ");
        Text lastNameLabel = new Text("Last Name: ");
        Text addressLabel = new Text("Address: ");
        Text provinceLabel = new Text("Province: ");
        Text postalCodeLabel = new Text("Postal Code: ");
        Text phoneNumLabel = new Text("Phone Number: ");
        Text updatePIDLabel = new Text("Update Player by ID: ");

        TextField firstName = create.FIRST_NAME;
        TextField lastName = create.LAST_NAME;
        TextField address = create.ADDRESS;
        TextField province = create.PROVINCE;
        TextField postalCode = create.POSTAL_CODE;
        TextField phoneNum = create.PHONE_NUMBER;
        TextField updatePID = create.PLAYER_ID;

        // Game Information Section
        Text gameHeader = new Text("Game Information");
        Text gameTitleLabel = new Text("Game Title: ");
        Text gameScoreLabel = new Text("Game Score: ");
        Text datePlayedLabel = new Text("Date Played: ");

        TextField gameTitle = create.GAME_TITLE;
        TextField gameScore = create.SCORE;
        TextField datePlayed = create.PLAYING_DATE;
        Text popupMessage = create.message;

        Button createButton = new Button("Create Player");
        createButton.setOnAction(event -> create.handle(event));


        // Display all Players Button
        Button displayButton = new Button("Display all Players");
        displayButton.setOnAction(event -> {
            try {
            	PlayerResult playerResult = new PlayerResult(window);

                playerResult.show();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        // Update Button
        Button updateButton = new Button("Update");
        updateButton.setOnAction(event -> {
            try {
                UpdateHandlerClass.updatePlayerRecord(updatePID.getText(), province.getText(), postalCode.getText(), gameScore.getText());
                popupMessage.setText("The record has been updated for, Player ID: " + updatePID.getText() + "\n");
            } catch (SQLException e) {
                popupMessage.setText("Problem occurred while updating record: " + e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        // Adding nodes to the GridPane
        playerGrid.add(playerHeader, 0, 0);
        playerGrid.add(firstNameLabel, 0, 1);
        playerGrid.add(lastNameLabel, 0, 2);
        playerGrid.add(addressLabel, 0, 3);
        playerGrid.add(provinceLabel, 0, 4);
        playerGrid.add(postalCodeLabel, 0, 5);
        playerGrid.add(phoneNumLabel, 0, 6);
        playerGrid.add(updatePIDLabel, 4, 1);

        // Setting text boxes for Player section
        playerGrid.add(firstName, 1, 1);
        playerGrid.add(lastName, 1, 2);
        playerGrid.add(address, 1, 3);
        playerGrid.add(province, 1, 4);
        playerGrid.add(postalCode, 1, 5);
        playerGrid.add(phoneNum, 1, 6);
        playerGrid.add(updatePID, 5, 1);

        // Setting labels for Game section
        playerGrid.add(gameHeader, 4, 3);
        playerGrid.add(gameTitleLabel, 4, 4);
        playerGrid.add(gameScoreLabel, 4, 5);
        playerGrid.add(datePlayedLabel, 4, 6);

        // Setting text boxes for Game section
        playerGrid.add(gameTitle, 5, 4);
        playerGrid.add(gameScore, 5, 5);
        playerGrid.add(datePlayed, 5, 6);

        // Setting button nodes
        playerGrid.add(updateButton, 6, 1);
        playerGrid.add(createButton, 4, 10);
        playerGrid.add(displayButton, 5, 10);
        playerGrid.add(popupMessage, 2, 12);

        TableView<PlayerResult> displayTable = new TableView<>();
        // displayTable.setEditable(false);
        displayTable.setMaxSize(1000, 300);
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}






