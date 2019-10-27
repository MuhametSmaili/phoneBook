package presenter;

import Database.User;
import Database.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    public Button btnLogin;
    @FXML
    public Label txtPasswordResult;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    public void onLoginClick() throws IOException {

        System.out.println("Login clicked" + txtPassword.getText()+txtUsername.getText());

        //when login is pressed check if the user typed correct, if Yes than show the other page otherwise a label with incorrect password;
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(txtUsername.getText());//get the user

        if (user != null && user.getPassword().equals(txtPassword.getText())){
            //if password and user matches than get the scene to add users
            Parent root= FXMLLoader.load(getClass().getResource("/mainApplication.fxml"));
            Scene scene = new Scene(root);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            //get the current scene and hide it
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.hide();
            //show the first scene
            secondStage.show();
        }else {
            txtPasswordResult.setText("Incorrect Password!");
        }
    }
}
