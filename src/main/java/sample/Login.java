package sample;

import Database.User;
import Database.UserDOA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    public Button btnLogin;
    @FXML
    public Label txtPasswordResult;
    @FXML
    public Pane registerFormPane;
    @FXML
    public TextField txtUsernameNew;
    @FXML
    public PasswordField txtPasswordNew;
    @FXML
    public Label lblResultsRegister;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    private UserDOA userDOA = new UserDOA();

    public void onLoginClick() throws IOException {

        System.out.println("Login clicked" + txtPassword.getText()+txtUsername.getText());
        //when login is pressed check if the user typed correct, if Yes than show the other page otherwise a label with incorrect password;
        User user = userDOA.getUserByUsername(txtUsername.getText());//get the user

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

    public void onRegisterClick(MouseEvent mouseEvent) {

        registerFormPane.visibleProperty().set(true);
    }

    public void onRegisterUserClick(ActionEvent actionEvent) {

        String name = txtUsernameNew.getText().trim();
        String password = txtPasswordNew.getText().trim();
        //check if the fields are empty also check if the user exists if not than add it and redirect to login page
        if (!name.isEmpty() && !password.isEmpty()){
            if (userDOA.getUserByUsername(name) == null){
                userDOA.addUser(new User(name,password));
                registerFormPane.visibleProperty().set(false);
                txtPasswordResult.textFillProperty().setValue(Paint.valueOf("blue"));
                txtPasswordResult.setText("User registered.");
                clearTextFields(txtUsernameNew,txtPasswordNew);
            }else {
                lblResultsRegister.setText("User exists");
            }
        }else {
            lblResultsRegister.setText("The fields are empty");
        }
    }

    public void onGoBack(MouseEvent mouseEvent) {
        registerFormPane.visibleProperty().set(false);
    }

    private void clearTextFields(TextField name,TextField pass){
        name.clear();
        pass.clear();
    }
}
