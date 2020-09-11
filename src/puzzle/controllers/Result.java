package puzzle.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import puzzle.otherClassesAndStyles.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Result implements Initializable {

    @FXML
    private Label highRankLabel;
    @FXML
    private Label guestLabel;

    private User user;


    @FXML
    private void backBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlFiles/levels.fxml"));
        Parent levelsParent = loader.load();

        Levels levels = loader.getController();
        levels.setUser(user);

        Scene levelsScene = new Scene(levelsParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(levelsScene);
        window.show();
    }

    public void setUser(User user) {
        this.user = user;
        highRankLabel.setText(user.getHighRank() + "");
        if (user.getEmail().equals("Guest")) {
            guestLabel.setText("Sign in to save your high rank");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
