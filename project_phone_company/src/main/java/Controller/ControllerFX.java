package Controller;
import Model.Order;
import Model.Phone;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControllerFX {
    @FXML
    TableView<Phone> phoneTableView;
    @FXML
    TableView<Order> orderTableView;
    @FXML
    TextField nameField, yearField, priceField,phone_idField,name2Field,dateField;
    @FXML
    Label idLabel, id2Label;

    private final ObservableList<Phone> phoneList=FXCollections.observableArrayList();
    private final ObservableList<Order> orderList=FXCollections.observableArrayList();

    private Service service;

    public ControllerFX() {}

    @FXML
    public void initialize(){
        phoneTableView.setItems(phoneList);
        orderTableView.setItems(orderList);

        phoneTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem)-> showPhone(newItem));
        orderTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem)-> showOrder(newItem));
    }

    public void setService(Service service) {
        this.service = service;
        phoneList.addAll(service.getPhoneList());
        orderList.addAll(service.getOrderList());
        idLabel.setText("" + service.getPhoneIdGenerator());
        id2Label.setText("" + service.getOrderIdGenerator());
    }

    //-------------------------------phone--------------

    private void showPhone(Phone phone) {
        if (phone == null)
            clearFieldsPhone();
        else{
            idLabel.setText("" + phone.getId());
            nameField.setText(phone.getName());
            yearField.setText(""+phone.getYear());
            priceField.setText("" + phone.getPrice());
        }
    }

    @FXML
    private void clearFieldsPhone() {
        idLabel.setText("" + service.getPhoneIdGenerator());
        nameField.setText("");
        yearField.setText("");
        priceField.setText("");
    }

    @FXML
    public void addPhoneButton(){
        String name=nameField.getText();
        String yearS=yearField.getText();
        String priceS=priceField.getText();

        if (name.equals("") || yearS.equals("") || priceS.equals("")) {
            showErrorMessage("All fields must be completed");
            return;
        }
        try{
            int year=Integer.parseInt(yearS);
            float price=Float.parseFloat(priceS);
            int id=service.addPhone(name,year,price);
            phoneList.setAll(service.getPhoneList());
            idLabel.setText("" + (id + 1));
        } catch (Exception exception) {
            showNotification(exception.getMessage(), Alert.AlertType.ERROR);
        }

    }


    //-------------------------------order----------------------

    private void showOrder(Order o) {
        if (o == null)
            clearFieldsOrder();
        else{
            id2Label.setText("" + o.getId());
            phone_idField.setText(""+o.getPhone_id());
            name2Field.setText(o.getName());
            dateField.setText(o.getDate());
        }
    }
    @FXML
    private void clearFieldsOrder() {
        id2Label.setText("" + service.getOrderIdGenerator());
        phone_idField.setText("");
        name2Field.setText("");
        dateField.setText("");
    }

    @FXML
    public void addOrderButton(){
        String phone_idS=phone_idField.getText();
        String name=name2Field.getText();
        String date=dateField.getText();

        if (name.equals("") || phone_idS.equals("") || date.equals("")) {
            showErrorMessage("All fields must be completed");
            return;
        }
        try{
            int phone_id=Integer.parseInt(phone_idS);
            int id=service.addOrder(phone_id,name,date);
            orderList.setAll(service.getOrderList());
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
