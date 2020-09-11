package puzzle.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import puzzle.otherClassesAndStyles.User;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LogIn implements Initializable, Serializable {

    private User user = new User();

    public String fileName;
    public int highRank;
    public String username;


    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label errorLabel;


    @FXML
    private void highRankBtn(ActionEvent event) throws IOException {
        if (loadFile()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxmlFiles/highRank.fxml"));
            Parent parent = loader.load();

            HighRank highRank = loader.getController();
            highRank.setUser(user);

            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }
    }

    @FXML
    private void playBtn(ActionEvent event) throws IOException {
        if (loadFile()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxmlFiles/levels.fxml"));
            Parent parent = loader.load();

            Levels levels = loader.getController();
            levels.setUser(user);

            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    private boolean loadFile() throws IOException {

        if (usernameField.getText().equals(null)
                || passwordField.getText().equals(null)) {
            errorLabel.setText("Please fill the blank fields!");
            return false;
        }

        fileName = "../" + usernameField.getText() + ".txt";
        File newFile = new File(fileName);
        User tempUser = new User();

        if (newFile.exists()) {

            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(fileName));
            try {
                tempUser = (User) oi.readObject();
            } catch (ClassNotFoundException | IOException e) {
//                e.printStackTrace();
            }

            if (tempUser.getPassword().equals(passwordField.getText())) {

                user = tempUser;
                oi.close();
                return true;

            } else {
                errorLabel.setText("Invalid password");
                oi.close();
                return false;
            }

        } else {
            errorLabel.setText("Invalid username");
            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
