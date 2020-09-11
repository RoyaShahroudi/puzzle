
package puzzle.controllers;

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

public class HighRank implements Initializable {
    private User user = new User();

    @FXML private Label highRankLabel1;

    @FXML
    private void playBtn(javafx.event.ActionEvent event) throws IOException {
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        highRankLabel1.setText(user.getHighRank() + "");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
