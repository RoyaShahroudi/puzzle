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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import puzzle.otherClassesAndStyles.User;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SignIn implements Initializable, Serializable {

    private User user = new User();

    public String fileName;

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField emailField;
    @FXML private Label errorLabel;


    @FXML
    private void playBtn(ActionEvent event) throws IOException {
        if (usernameField.getText().isEmpty()
                || emailField.getText().isEmpty()
                || passwordField.getText().isEmpty()) {
            errorLabel.setText("Please fill the blank fields!");

        } else if (emailField.getText().endsWith("@gmail.com") ||
            emailField.getText().endsWith("@yahoo.com")) {

            if (saveInFile()) {
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

        } else {
            errorLabel.setText("Please fill the fields correctly!");
        }
    }

    private boolean saveInFile() {
        fileName = "../" + usernameField.getText() + ".txt";
        File newFile = new File(fileName);
        try {
            if (newFile.exists()) {
                errorLabel.setTextFill(Color.RED); ////////
                errorLabel.setText("This username is used before." +
                        "Please enter another one.");
                return false;

            } else {
                user.setUsername(usernameField.getText());
                user.setEmail(emailField.getText());
                user.setPassword(passwordField.getText());
                user.setHighRank(0);

                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
                os.writeObject(user);

                os.flush();
                return true;

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
