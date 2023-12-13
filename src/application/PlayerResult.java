
package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PlayerResult
{
	// Properties to store player and game information
	public Stage window;
	    private IntegerProperty playerID;
	    private StringProperty firstName;
	    private StringProperty lastName;
	    private StringProperty address;
	    private StringProperty province;
	    private StringProperty postalCode;
	    private StringProperty phoneNum;
	    private StringProperty gameTitle;
	    private StringProperty gameScore;
	    private StringProperty playingDate;

	 // Constructor to initialize properties with player and game information
	    public PlayerResult(int playerID, String firstName, String lastName, String address,
	                        String province, String postalCode, String phoneNum,
	                       String gameTitle, String gameScore, String playingDate) {
	    	// Initialize properties using SimpleIntegerProperty and SimpleStringProperty
	        this.playerID = new SimpleIntegerProperty(playerID);
	        this.firstName = new SimpleStringProperty(firstName);
	        this.lastName = new SimpleStringProperty(lastName);
	        this.address = new SimpleStringProperty(address);
	        this.province = new SimpleStringProperty(province);
	        this.postalCode = new SimpleStringProperty(postalCode);
	        this.phoneNum = new SimpleStringProperty(phoneNum);
	        this.gameTitle = new SimpleStringProperty(gameTitle);
	        this.gameScore = new SimpleStringProperty(gameScore);
	        this.playingDate = new SimpleStringProperty(playingDate);
	    }

	    //Getter and setter methods for properties

	    public Integer getPlayerID() {
	        return playerID.get();
	    }

	    public IntegerProperty playerIDProperty() {
	        return playerID;
	    }

	    public void setPlayerID(Integer playerID) {
	        this.playerID.set(playerID);
	    }

	    public String getFirstName() {
	        return firstName.get();
	    }

	    public StringProperty firstNameProperty() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName.set(firstName);
	    }
	    public String getLastName() {
	        return lastName.get();
	    }

	    public StringProperty lastNameProperty() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName.set(lastName);
	    }

	    public String getAddress() {
	        return address.get();
	    }

	    public StringProperty addressProperty() {
	        return address;
		}

	    public void setAddress(String address) {
	        this.address.set(address);
	    }
	    public String getProvince() {
	        return province.get();
	    }

	    public StringProperty provinceProperty() {
	        return province;
	    }

	    public void setProvince(String province) {
	        this.province.set(province);
	    }

	    public String getPostalCode() {
	        return postalCode.get();
	    }

	    public StringProperty postalCodeProperty() {
	        return postalCode;
	    }

	    public void setPostalCode(String postalCode) {
	        this.postalCode.set(postalCode);
	    }

	    public String getPhoneNum() {
	        return phoneNum.get();
	    }

	    public StringProperty phoneNumProperty() {
	        return phoneNum;
	    }

	    public void setPhoneNum(String phoneNum) {
	        this.phoneNum.set(phoneNum);
	    }

	    public String getGameTitle() {
	        return gameTitle.get();
	    }

	    public StringProperty gameTitleProperty() {
	        return gameTitle;
	    }

	    public void setGameTitle(String gameTitle) {
	        this.gameTitle.set(gameTitle);
	    }

	    // Add getter and setter methods for other properties...

	    public String getGameScore() {
	        return gameScore.get();
	    }

	    public StringProperty gameScoreProperty() {
	        return gameScore;
	    }

	    public void setGameScore(String gameScore) {
	        this.gameScore.set(gameScore);
	    }

	    public String getPlayingDate() {
	        return playingDate.get();
	    }

	    public StringProperty playingDateProperty() {
	         return playingDate;
	    }

	    public void setPlayingDate(String playingDate) {
	        this.playingDate.set(playingDate);
	    }
	

	
	public PlayerResult(Stage stage)
	{        
		this.window = stage;  
	}    
	// Method to create and configure the GridPane to display the table
	public GridPane displayPane()
	{        
		GridPane resultGrid = new GridPane();        
		//setting up player grid properties        
		resultGrid.setPadding(new Insets(0,10,0,10));       
		resultGrid.setMinSize(900,600);     
		//resultGrid.setAlignment(Pos.BASELINE_CENTER);     
		resultGrid.setVgap(5);      
		resultGrid.setHgap(4);      
		return resultGrid;    
	}   
	 // Method to add UI elements to the GridPane
	public void addUI(GridPane pane) throws SQLException, ClassNotFoundException 
	{        
		TableView<PlayerResult> displayTable = new TableView<>();        
		displayTable.prefHeightProperty().bind(window.maxHeightProperty());     
		displayTable.prefWidthProperty().bind(window.widthProperty());    
		displayTable.setEditable(false);       
		displayTable.setMaxSize(950,500);     
		displayTable.setMaxWidth(1200); 
		displayTable.setItems(PlayerResult.populateDisplayTable());
		       
		//setting the column name        
		TableColumn<PlayerResult, Integer> PLAYER_IDColumn = new TableColumn<>("ID");
		PLAYER_IDColumn.setCellValueFactory(new PropertyValueFactory<>("playerID"));
		TableColumn<PlayerResult, String> FIRST_NAMEColumn = new TableColumn<>("FIRST NAME");
		FIRST_NAMEColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		TableColumn<PlayerResult, String> LAST_NAMEColumn = new TableColumn<>("LAST NAME");
		LAST_NAMEColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		TableColumn<PlayerResult, String> ADDRESSColumn = new TableColumn<>("ADDRESS");
		ADDRESSColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		TableColumn<PlayerResult, String> POSTAL_CODEColumn = new TableColumn<>("POSTAL CODE");
		POSTAL_CODEColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode")); // Corrected to match the field name
		TableColumn<PlayerResult, String> PROVINCEColumn = new TableColumn<>("PROVINCE");
		PROVINCEColumn.setCellValueFactory(new PropertyValueFactory<>("province"));
		TableColumn<PlayerResult, String> PHONE_NUMBERColumn = new TableColumn<>("PHONE NUMBER");
		PHONE_NUMBERColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum")); // Corrected to match the field name
		TableColumn<PlayerResult, String> GAME_TITLEColumn = new TableColumn<>("GAME TITLE");
		GAME_TITLEColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle")); // Corrected to match the field name
		TableColumn<PlayerResult, String> SCOREColumn = new TableColumn<>("SCORE");
		SCOREColumn.setCellValueFactory(new PropertyValueFactory<>("gameScore")); // Corrected to match the field name
		TableColumn<PlayerResult, String> PLAYING_DATEColumn = new TableColumn<>("DATE PLAYED");
		PLAYING_DATEColumn.setCellValueFactory(new PropertyValueFactory<>("playingDate"));

		
		displayTable.refresh();        
		displayTable.getColumns().add(PLAYER_IDColumn);
		displayTable.getColumns().add(FIRST_NAMEColumn);
		displayTable.getColumns().add(LAST_NAMEColumn);
		displayTable.getColumns().add(ADDRESSColumn);
		displayTable.getColumns().add(POSTAL_CODEColumn);
		displayTable.getColumns().add(PROVINCEColumn);
		displayTable.getColumns().add(PHONE_NUMBERColumn);
		displayTable.getColumns().add(GAME_TITLEColumn);
		displayTable.getColumns().add(SCOREColumn);
		displayTable.getColumns().add(PLAYING_DATEColumn);      
		displayTable.setItems(PlayerResult.populateDisplayTable());      
		pane.add(displayTable,0,2);            
	}    
	// Method to show the TableView with player information
	public void show() throws SQLException, ClassNotFoundException 
	{       
		GridPane gridPane = displayPane();       
		addUI(gridPane);      
		window.setTitle("SHOW ALL THE PLAYER");    
		window.setScene(new Scene(gridPane,900 ,600));        
		window.show();    
	} 
	// Method to populate the TableView with player information
	private static ObservableList<PlayerResult> populateDisplayTable() throws SQLException, ClassNotFoundException 
	{    
		// Creating an ObservableList to hold player information
		ObservableList<PlayerResult> dataList = FXCollections.observableArrayList();
		try {
			// Connecting to the database
			Database.dbConnect();
		    Connection connection = Database.conn;
		    PreparedStatement pStatement = connection.prepareStatement
		    		("SELECT p.player_id, p.first_name, p.last_name, p.address, p.postal_code, p.province, p.phone_number, g.game_title, pg.score, pg.playing_date\n" +
		            "FROM player p JOIN playerandgame pg ON p.player_id = pg.player_id JOIN game g ON g.game_id = pg.game_id ORDER BY p.player_id");
		    ResultSet rSet = pStatement.executeQuery();
		 // Extracting data from the ResultSet and creating PlayerResult objects
		    while (rSet.next()) {
		        int playerID = rSet.getInt("player_id");
		        String firstName = rSet.getString("first_name");
		        String lastName = rSet.getString("last_name");
		        String address = rSet.getString("address");
		        String postalCode = rSet.getString("postal_code");
		        String province = rSet.getString("province");
		        String phoneNum = rSet.getString("phone_number");
		        String gameTitle = rSet.getString("game_title");
		        String gameScore = rSet.getString("score");
		        String playingDate = rSet.getString("playing_date");
		        
		        PlayerResult result = new PlayerResult(playerID, firstName, lastName, address, province, postalCode, phoneNum, gameTitle, gameScore, playingDate);
		        dataList.add(result);
		}
		Database.dbDisconnect(); 
		}catch(SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    return dataList;
	}
}