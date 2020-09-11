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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Guest implements Initializable {

    private User user = new User();

    @FXML private TextField usernameField;


    @FXML
    private void playBtn(ActionEvent event) throws IOException {

        user.setHighRank(0);
        
         user.setUsername(usernameField.getText() + "/Guest");
         user.setEmail("Guest");

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
