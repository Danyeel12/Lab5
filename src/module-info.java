/**
 * 
 */
/**
 * @author Daniel
 *
 */
module DanielMiranda_Comp228Lab5 {
    exports application;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens application to javafx.fxml;
}
