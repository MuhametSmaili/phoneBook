package sample;

import Database.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(Login.class.getResource("/login.fxml"));
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //add some data for testing purpose
        User user = new User();
        user.setUsername("Muhamet");
        user.setPassword("123456");

        UserDOA userDOA = new UserDOA();
        userDOA.addUser(user);

        PhoneBookUsers users = new PhoneBookUsers();
        users.setName("Test");
        users.setSurname("Surname");
        users.setNumber("045968");

        PhoneBookUsersDOA phoneBookUsersDOA = new PhoneBookUsersDOA();
        phoneBookUsersDOA.addUser(users);

        launch(args);
    }
}
