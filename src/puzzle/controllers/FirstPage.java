package puzzle.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import puzzle.otherClassesAndStyles.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstPage implements Initializable {
    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Button signInBtn;


    @FXML
    private void guestBtn(ActionEvent event) throws IOException {
        // set user as a guest
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlFiles/guest.fxml"));
        Parent signInParent = loader.load();

        Scene signInScene = new Scene(signInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();

    }

    @FXML
    private void highRankBtn(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../fxmlFiles/highRank.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void signInBtn(ActionEvent event) throws IOException {

        // go to sign in page
        Parent parent = FXMLLoader.load(getClass().getResource("../fxmlFiles/signIn.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void logInBtn(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../fxmlFiles/logIn.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void exitBtn(ActionEvent event) throws IOException {

        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
