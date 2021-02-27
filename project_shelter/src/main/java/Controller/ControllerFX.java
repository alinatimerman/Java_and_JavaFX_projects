package Controller;

import Model.Animal;
import Model.Shelter;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerFX {
    @FXML
    TableView<Shelter> shelterTableView;
    @FXML
    TableView<Animal> animalTableView;
    @FXML
    TextField nameField, shelter_idField, ageField,speciesField, name2Field,addressField,volunteersField,animalsField,employeesField;
    @FXML
    Label idLabel, id2Label;

    private final ObservableList<Shelter> shelterList =FXCollections.observableArrayList();
    private final ObservableList<Animal> animalList =FXCollections.observableArrayList();

    private Service service;

    public ControllerFX() {}

    @FXML
    public void initialize(){
        shelterTableView.setItems(shelterList);
        animalTableView.setItems(animalList);

        shelterTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem)-> showShelter(newItem));
        animalTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem)-> showAnimal(newItem));
    }

    public void setService(Service service) {
        this.service = service;
        shelterList.addAll(service.getShelterList());
        animalList.addAll(service.getAnimalList());
        idLabel.setText("" + service.getShelterIdGenerator());
        id2Label.setText("" + service.getAnimalIdGenerator());
    }

    //-------------------------------wg----------------------------------------------------------------------

    private void showShelter(Shelter c) {
        if (c == null)
            clearFieldsShelter();
        else{
            idLabel.setText("" + c.getId());
            nameField.setText(c.getName());
            addressField.setText(""+c.getAddress());
            volunteersField.setText(""+c.getVolunteers());
            animalsField.setText(""+c.getAnimals());
            employeesField.setText(""+c.getEmployees());

        }
    }

    @FXML
    private void clearFieldsShelter() {
        idLabel.setText("" + service.getShelterIdGenerator());
        nameField.setText("");
        addressField.setText("");
        volunteersField.setText("");
        animalsField.setText("");
        employeesField.setText("");

    }

    @FXML
    public void addShelterButton(){
        String name=nameField.getText();
        String volunteersS= volunteersField.getText();
        String address= addressField.getText();
        String animalsS=animalsField.getText();
        String employeesS=employeesField.getText();

        if (name.equals("") || animalsS.equals("") || volunteersS.equals("")|| address.equals("")|| employeesS.equals("")) {
            showErrorMessage("All fields must be completed");
            return;
        }
        try{
            int volunteers=Integer.parseInt(volunteersS);
            int animals=Integer.parseInt(animalsS);
            int employeess=Integer.parseInt(employeesS);
            int id=service.addShelter(name,address,volunteers,animals,employeess);
            shelterList.setAll(service.getShelterList());
            idLabel.setText("" + (id + 1));
        } catch (Exception exception) {
            showNotification(exception.getMessage(), Alert.AlertType.ERROR);
        }

    }


    //-------------------------------animal------------------------------------------------------------------------

    private void showAnimal(Animal o) {
        if (o == null)
            clearFieldsAnimal();
        else{
            id2Label.setText("" + o.getId());
            name2Field.setText(o.getName());
            shelter_idField.setText(""+o.getShelter_id());
            ageField.setText(""+o.getAge());
            speciesField.setText(""+o.getSpecies());
        }
    }
    @FXML
    private void clearFieldsAnimal() {
        id2Label.setText("" + service.getAnimalIdGenerator());
        name2Field.setText("");
        shelter_idField.setText("");
        ageField.setText("");
        speciesField.setText("");}

    @FXML
    public void addAnimalButton(){
        String name=name2Field.getText();
        String ageS=ageField.getText();
        String species=speciesField.getText();
        String shelter_idS=shelter_idField.getText();

        if (name.equals("") || ageS.equals("") || species.equals("")|| shelter_idS.equals("")) {
            showErrorMessage("All fields must be completed");
            return;
        }
        try{
            int age=Integer.parseInt(ageS);
            int shelter_id=Integer.parseInt(shelter_idS);
            int id=service.addAnimal(name,shelter_id,age,species);
            animalList.setAll(service.getAnimalList());
            id2Label.setText("" + (id + 1));
        } catch (Exception exception) {
            showNotification(exception.getMessage(), Alert.AlertType.ERROR);
        }

    }
    //---------------------------------------show messages-------------------------------------------------
    void showErrorMessage (String text){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Error message");
        message.setContentText(text);
        message.showAndWait();
    }

    private void showNotification(String message, Alert.AlertType type){
        Alert alert=new Alert(type);
        alert.setTitle("Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
