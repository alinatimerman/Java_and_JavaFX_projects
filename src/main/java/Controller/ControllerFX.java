package Controller;
import Model.Registration;
import Model.Activity;
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
    TableView<Activity> activityTableView;
    @FXML
    TableView<Registration> registrationTableView;
    @FXML
    TextField nameField;
    @FXML
    TextField min_ageField;
    @FXML
    TextField max_ageField;
    @FXML
    TextField dateField;
    @FXML
    TextField personField;
    @FXML
    TextField ageField;
    @FXML
    TextField phoneField;
    @FXML
    TextField activity_idField;
    @FXML
    Label idLabel, id2Label;

    private final ObservableList<Activity> activityList =FXCollections.observableArrayList();
    private final ObservableList<Registration> enrollmentList =FXCollections.observableArrayList();

    private Service service;

    public ControllerFX() {}

    @FXML
    public void initialize(){
        activityTableView.setItems(activityList);
        registrationTableView.setItems(enrollmentList);

        activityTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem)-> showActivity(newItem));
        registrationTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem)-> showRegistration(newItem));
    }

    public void setService(Service service) {
        this.service = service;
        activityList.addAll(service.getActivityList());
        enrollmentList.addAll(service.getRegistrationList());
        idLabel.setText("" + service.getActivityIdGenerator());
        id2Label.setText("" + service.getRegistrationIdGenerator());
    }

    //-------------------------------activity----------------------------------------------------------------------

    private void showActivity(Activity c) {
        if (c == null)
            clearFieldsActivity();
        else{
            idLabel.setText("" + c.getId());
            nameField.setText(c.getName());
            min_ageField.setText(""+c.getMin_age());
            max_ageField.setText(""+c.getMax_age());
            dateField.setText(c.getDate());

        }
    }

    @FXML
    private void clearFieldsActivity() {
        idLabel.setText("" + service.getActivityIdGenerator());
        nameField.setText("");
        min_ageField.setText("");
        max_ageField.setText("");
        dateField.setText("");

    }

    @FXML
    public void addActivityButton(){
        String name=nameField.getText();
        String min_ageS= min_ageField.getText();
        String max_ageS= max_ageField.getText();
        String date=dateField.getText();

        if (name.equals("") || max_ageS.equals("") || min_ageS.equals("")|| date.equals("")) {
            showErrorMessage("All fields must be completed");
            return;
        }
        try{
            int min_age=Integer.parseInt(min_ageS);
            int max_age=Integer.parseInt(max_ageS);
            int id=service.addActivity(name,min_age,max_age,date);
            activityList.setAll(service.getActivityList());
            idLabel.setText("" + (id + 1));
        } catch (Exception exception) {
            showNotification(exception.getMessage(), Alert.AlertType.ERROR);
        }

    }


    //-------------------------------registration------------------------------------------------------------------------

    private void showRegistration(Registration o) {
        if (o == null)
            clearFieldsRegistration();
        else{
            id2Label.setText("" + o.getId());
            personField.setText(""+o.getPerson());
            ageField.setText(""+o.getAge());
            phoneField.setText(""+o.getPhone());
            activity_idField.setText(""+o.getActivity_id());
        }
    }
    @FXML
    private void clearFieldsRegistration() {
        id2Label.setText("" + service.getRegistrationIdGenerator());
        personField.setText("");
        ageField.setText("");
        phoneField.setText("");
        activity_idField.setText("");
    }

    @FXML
    public void addRegistrationButton(){
        String person= personField.getText();
        String ageS=ageField.getText();
        String phone= phoneField.getText();
        String wg_idS= activity_idField.getText();

        if (person.equals("") || ageS.equals("") || phone.equals("")|| wg_idS.equals("")) {
            showErrorMessage("All fields must be completed");
            return;
        }
        try{
            int age=Integer.parseInt(ageS);
            int activity_id=Integer.parseInt(wg_idS);
            int id=service.addRegistration(person,age,phone,activity_id);
            enrollmentList.setAll(service.getRegistrationList());
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
