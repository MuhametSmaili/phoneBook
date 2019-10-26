package sample;

import Database.PhoneBookUsers;
import Database.PhoneBookUsersDOA;
import Database.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    @FXML
    public TableView userListTable;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtNumber;

    public void btnSearch(){

    }

    public void onClearClick(){
        txtName.clear();
        txtNumber.clear();
        txtSurname.clear();
    }

    public void onSaveClick(){

        PhoneBookUsers user = new PhoneBookUsers(txtName.getText(),txtSurname.getText(),txtNumber.getText());
        PhoneBookUsersDOA createUser = new PhoneBookUsersDOA();
        createUser.addUser(user);

//        userListTable.getItems().removeAll(createUser.getAllPhoneBookUsers());

        populateTheTable(createUser);

        //TODO get all users and show to the list

        //TODO refresh the list, also add the search functionality

        onClearClick();
    }

    void populateTheTable(PhoneBookUsersDOA createUser){

        TableColumn<String, PhoneBookUsers> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<String, PhoneBookUsers> creationTime = new TableColumn<>("Creation Date");
        creationTime.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<String, PhoneBookUsers> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<String, PhoneBookUsers> surname = new TableColumn<>("Surname");
        surname.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<String, PhoneBookUsers> number = new TableColumn<>("Number");
        number.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        userListTable.getColumns().add(id);
        userListTable.getColumns().add(creationTime);
        userListTable.getColumns().add(name);
        userListTable.getColumns().add(surname);
        userListTable.getColumns().add(number);

        userListTable.getItems().add(createUser.getAllPhoneBookUsers().get(0));
    }
}