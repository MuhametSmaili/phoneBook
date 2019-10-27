package controller;

import Database.PhoneBookUsers;
import Database.PhoneBookUsersDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.util.Date;

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

    public void onClearClick(){
        clearTextFields(txtName,txtSurname,txtNumber);
    }

    public void onSaveClick(){
        //check if the inputs are not null
        if (checkTheInputs(txtName,txtName,txtNumber,lblFailed)){
            Long number = Long.valueOf(txtNumber.getText().trim());
            PhoneBookUsers user = new PhoneBookUsers(txtName.getText().trim(),txtSurname.getText().trim(),number);
            PhoneBookUsersDAO createUser = new PhoneBookUsersDAO();

            createUser.addUser(user);
            populateTheTable(createUser);
            lblFailed.setTextFill(Paint.valueOf("Blue"));
            lblFailed.setText("User Registered.");
        }
        onClearClick();
    }

    public Boolean checkTheInputs(TextField name,TextField surname,TextField number,Label lbl){
        boolean check = false;
        //CHECK IF THE INPUTS ARE NULL OR THE NUMBER IS NOT A NUMBER
        if (!name.getText().trim().isEmpty() && !surname.getText().trim().isEmpty() && !number.getText().trim().isEmpty()){
            try {
                //check if the user writes a number
                Long.parseLong(number.getText().trim());
                check= true;
            } catch (NumberFormatException nfe) {
                lbl.setText("Write a number!");
            }
        }else {
            lbl.setText("The field is empty!");
        }
        return check;
    }

    private void populateTheTable(PhoneBookUsersDAO createUser){
        //create the table columns
        tableColumnName.setCellValueFactory(new PropertyValueFactory<PhoneBookUsers, Date>("Name"));
        tableColumnSurname.setCellValueFactory(new PropertyValueFactory<PhoneBookUsers,String>("Surname"));
        tableColumnNumber.setCellValueFactory(new PropertyValueFactory<PhoneBookUsers,String>("Number"));

        //get the results from database and pass to an Observable List
        ObservableList<PhoneBookUsers> data = FXCollections.observableArrayList();
        data.addAll(createUser.getAllPhoneBookUsers());

        userListTable.setItems(data);
    }


    public PhoneBookUsers tableClicked(MouseEvent mouseEvent) {
        PhoneBookUsers user = (PhoneBookUsers) userListTable.getSelectionModel().getSelectedItem();

        resetLabel();
        setTexts(user);
        return user;
    }

    private void setTexts(PhoneBookUsers user) {
        txtNameOld.setText(user.getName());
        txtSurnameOld.setText(user.getSurname());
        txtNumberOld.setText(String.valueOf(user.getNumber()));
    }
    private void resetLabel() {
        lblStatus.setText("");//clear the status label
    }

    public void onUpdateClick(MouseEvent mouseEvent) {
        PhoneBookUsers user = (PhoneBookUsers) userListTable.getSelectionModel().getSelectedItem();

        if (checkTheInputs(txtNameOld,txtSurnameOld,txtNumberOld,lblStatus)){
            user.setName(txtNameOld.getText());
            user.setSurname(txtSurnameOld.getText());
            user.setNumber(Long.parseLong(txtNumberOld.getText()));
            PhoneBookUsersDAO pu =new PhoneBookUsersDAO();
            pu.updateUser(user);
            lblStatus.setText("User updated.");

            clearTextFields(txtNameOld,txtSurnameOld,txtNumberOld);
            userListTable.refresh();
        }
    }

    private void clearTextFields(TextField name, TextField surname, TextField number) {
        name.clear();
        surname.clear();
        number.clear();
    }

    public void onDeleteClicked(MouseEvent mouseEvent) {
        if (checkTheInputs(txtNameOld,txtSurnameOld,txtNumberOld,lblStatus)){

            PhoneBookUsers user = (PhoneBookUsers) userListTable.getSelectionModel().getSelectedItem();
            PhoneBookUsersDAO pu =new PhoneBookUsersDAO();
            pu.deleteUser(user);
            lblStatus.setText("User Deleted.");

            clearTextFields(txtNameOld,txtSurnameOld,txtNumberOld);
            populateTheTable(pu);
        }
    }
}