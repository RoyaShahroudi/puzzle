package puzzle.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import puzzle.otherClassesAndStyles.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Levels implements Initializable {

    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int size;
   private int tileSize;

    @FXML
    private void easyBtn(ActionEvent event) throws IOException {

        size = 4;
        tileSize = 120;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlFiles/game.fxml"));
        Parent signInParent = loader.load();

        Game game = loader.getController();
        game.setSize(size, tileSize, "Easy", user);

        Scene signInScene = new Scene(signInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    @FXML
    private void normalBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlFiles/game.fxml"));
        Parent signInParent = loader.load();

        Game game = loader.getController();
        game.setSize(5, 96, "Normal", user);

        Scene signInScene = new Scene(signInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    @FXML
    private void hardBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlFiles/game.fxml"));
        Parent signInParent = loader.load();

        Game game = loader.getController();
        game.setSize(6, 80, "Hard", user);

        Scene signInScene = new Scene(signInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
