package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.UUID;

public class Controller implements Initializable {

    @FXML
    private JFXButton createButton;

    @FXML
    private JFXButton populateButton;

    @FXML
    JFXListView studentListView;

    final String hostname = "derek-db.ckexfznrvq9e.us-east-1.rds.amazonaws.com";
    final String dbName = "derek_db";
    final String port = "3306";
    final String username = "derek";
    final String password = "D.welch96";
    final String AWS_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + username + "&password=" + password;



    private void createDatabase(String url) {
        try {
            Connection dbs = DriverManager.getConnection(url);
            Statement crt = dbs.createStatement();
            try {
                crt.execute("CREATE TABLE sample.Student(" +
                        "Name CHAR(25)," +
                        "ID VARCHAR(36)," +
                        "Age VARCHAR(36)" +
                        "Major CHAR(25)," +
                        "GPA VARCHAR(36)");
                System.out.println("TABLE CREATED");


                UUID id = UUID.randomUUID();
                String idString = id.toString();
                String Name = "Parker";

                String sql = "INSERT INTO Student VALUES " +
                        " (' " + Name + ',' + idString + " ,TRUE)";

                crt.executeUpdate(sql);

                id = UUID.randomUUID();
                idString = id.toString();
                String Name2 ="Ford";

                sql = "INSERT INTO Student VALUES" +
                        "('" + Name2 + "'," + idString + "', TRUE)";
                crt.executeUpdate(sql);
                System.out.println("TABLE POPULATED");

                crt.close();
                dbs.close();

            } catch (Exception ex) {
            }
            System.out.println("TABLE ALREADY EXISTS");
        } catch (SQLException e) {
            e.printStackTrace();
        }








    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createDatabase(AWS_URL);
            }
        });
        populateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadData(AWS_URL);

            }
        });
    }

    private void loadData(String url) {
        try{
            Connection dbs = DriverManager.getConnection(url);
            var crt = dbs.createStatement();
            String sqlStatement = "SELECT NAME, IsActive FROM Student";
            ResultSet result = crt.executeQuery(sqlStatement);
            ObservableList<Student> studentListView = FXCollections.observableArrayList();
            while (result.next()){
                Student student = new Student();
                student.ID = UUID.fromString(result.getString("ID"));
                student.Name = result.getString("Name");
                student.Age = UUID.fromString((result.getString("Age")));
                student.Major = result.getString("Major");
                student.GPA = UUID.fromString(result.getString("GPA"));
                derek_db.add(student);

            }
            if(url.equals);
        }
        catch (Exception ex){
            System.out.println("Loaded");


        }


    }


}




