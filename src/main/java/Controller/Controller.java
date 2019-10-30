package Controller;

import Database.PhoneBookUsers;
import Database.PhoneBookUsersDOA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.util.Date;
import java.util.List;

public class Controller {

    @FXML
    public TextField txtNameOld;
    @FXML
    public TextField txtSurnameOld;
    @FXML
    public TextField txtNumberOld;
    @FXML
    public Label lblStatus;
    @FXML
    public Label lblFailed;
    @FXML
    public TextField txtSearch;
    @FXML
    private TableView userListTable;
    @FXML
    private TableColumn tableColumnName;
    @FXML
    private TableColumn tableColumnSurname;
    @FXML
    private TableColumn tableColumnNumber;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtNumber;
    private PhoneBookUsersDOA createUser = new PhoneBookUsersDOA();

    public void onClearClick(){
        clearTextFields(txtName,txtSurname,txtNumber);
    }

    public void onSaveClick(){
        //check for valid inputs and refister the user
        if (checkTheInputs(txtName,txtName,txtNumber,lblFailed)){
            PhoneBookUsers user = new PhoneBookUsers(txtName.getText().trim(),txtSurname.getText().trim(),txtNumber.getText().trim());
            createUser.addUser(user);
            populateTheTable(createUser.getAllPhoneBookUsers());
            lblFailed.setTextFill(Paint.valueOf("Blue"));
            lblFailed.setText("User Registered.");
        }
        onClearClick();
    }

    public Boolean checkTheInputs(TextField name,TextField surname,TextField number,Label lbl){
        boolean check = false;
        //CHECK IF THE INPUTS ARE NULL OR THE NUMBER IS A NUMBER
        if (!name.getText().trim().isEmpty() && !surname.getText().trim().isEmpty() && !number.getText().trim().isEmpty()){
            //check if the user writes a number
            if (isNumeric(number.getText().trim()))
                check= true;
            else
                lbl.setText("Write a number!");
        }else {
            lbl.setText("The field is empty!");
        }
        return check;
    }

    private void populateTheTable(List<PhoneBookUsers> users){
        //create the table columns
        tableColumnName.setCellValueFactory(new PropertyValueFactory<PhoneBookUsers, Date>("Name"));
        tableColumnSurname.setCellValueFactory(new PropertyValueFactory<PhoneBookUsers,String>("Surname"));
        tableColumnNumber.setCellValueFactory(new PropertyValueFactory<PhoneBookUsers,String>("Number"));
        //get the results from database and pass to an Observable List
        ObservableList<PhoneBookUsers> data = FXCollections.observableArrayList();
        data.addAll(users);
        userListTable.setItems(data);
    }

    public PhoneBookUsers tableClicked(MouseEvent mouseEvent) {

        PhoneBookUsers user = selectedUser();
        fillTextFields(user);//fill the text filed of the selected user and get the selected user
        return user;
    }

    private void fillTextFields(PhoneBookUsers user){
        lblStatus.setText("");//clear the status label
        txtNameOld.setText(user.getName());
        txtSurnameOld.setText(user.getSurname());
        txtNumberOld.setText(String.valueOf(user.getNumber()));
    }

    public void onUpdateClick(MouseEvent mouseEvent) {

        if (checkTheInputs(txtNameOld,txtSurnameOld,txtNumberOld,lblStatus)){
            updateUser(selectedUser());//update the user with the new values
            clearTextFields(txtNameOld,txtSurnameOld,txtNumberOld);
            userListTable.refresh();
        }
    }

    private void updateUser(PhoneBookUsers user){
        user.setName(txtNameOld.getText().trim());
        user.setSurname(txtSurnameOld.getText().trim());
        user.setNumber(txtNumberOld.getText().trim());
        createUser.updateUser(user);
        lblStatus.setText("User updated.");
    }

    private void clearTextFields(TextField name, TextField surname, TextField number) {
        name.clear();
        surname.clear();
        number.clear();
    }

    public void onDeleteClicked(MouseEvent mouseEvent) {
        if (checkTheInputs(txtNameOld,txtSurnameOld,txtNumberOld,lblStatus)){
            createUser.deleteUser(selectedUser());//delete the selected user
            lblStatus.setText("User Deleted.");
            clearTextFields(txtNameOld,txtSurnameOld,txtNumberOld);
            populateTheTable(createUser.getAllPhoneBookUsers());
        }
    }

    private PhoneBookUsers selectedUser(){
        return (PhoneBookUsers) userListTable.getSelectionModel().getSelectedItem();
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }


    public void onSearchText(KeyEvent keyEvent) {
        List<PhoneBookUsers> phoneBookUsers = createUser.getAllPhoneBookUserByNameOrPhone(txtSearch.getText());
        populateTheTable(phoneBookUsers);
        userListTable.refresh();
        System.out.println(txtSearch.getText());
    }
}