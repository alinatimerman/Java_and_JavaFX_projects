package Controller;
import Model.Book;
import Model.Order;
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
    TableView<Book> bookTableView;
    @FXML
    TableView<Order> orderTableView;
    @FXML
    TextField nameField, authorField, priceField, ratingField,yearField,book_idField,customerField,dateField,statusField;
    @FXML
    Label idLabel, id2Label;

    private final ObservableList<Book> bookList =FXCollections.observableArrayList();
    private final ObservableList<Order> orderList =FXCollections.observableArrayList();

    private Service service;

    public ControllerFX() {}

    @FXML
    public void initialize(){
        bookTableView.setItems(bookList);
        orderTableView.setItems(orderList);

        bookTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem)-> showBook(newItem));
        orderTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem)-> showOrder(newItem));
    }

    public void setService(Service service) {
        this.service = service;
        bookList.addAll(service.getBookList());
        orderList.addAll(service.getOrderList());
        idLabel.setText("" + service.getBookIdGenerator());
        id2Label.setText("" + service.getOrderIdGenerator());
    }

    //-------------------------------wg----------------------------------------------------------------------

    private void showBook(Book c) {
        if (c == null)
            clearFieldsBook();
        else{
            idLabel.setText("" + c.getId());
            nameField.setText(c.getName());
            authorField.setText(""+c.getAuthor());
            priceField.setText(""+c.getPrice());
            ratingField.setText(""+c.getRating());
            yearField.setText(""+c.getYear());

        }
    }

    @FXML
    private void clearFieldsBook() {
        idLabel.setText("" + service.getBookIdGenerator());
        nameField.setText("");
        authorField.setText("");
        priceField.setText("");
        ratingField.setText("");
        yearField.setText("");

    }

    @FXML
    public void addBookButton(){
        String name=nameField.getText();
        String author=authorField.getText();
        String priceS= priceField.getText();
        String ratingS= ratingField.getText();
        String yearS= yearField.getText();

        if (name.equals("") || author.equals("") || priceS.equals("")|| ratingS.equals("")|| yearS.equals("")) {
            showErrorMessage("All fields must be completed");
            return;
        }
        try{
            int price=Integer.parseInt(priceS);
            int rating=Integer.parseInt(ratingS);
            int year=Integer.parseInt(yearS);
            int id=service.addBook(name,author,price,rating,year);
            bookList.setAll(service.getBookList());
            idLabel.setText("" + (id + 1));
        } catch (Exception exception) {
            showNotification(exception.getMessage(), Alert.AlertType.ERROR);
        }

    }


    //------------------------------Order---------------------------------------------------------------------------------------------------------------------

    private void showOrder(Order o) {
        if (o == null)
            clearFieldsOrder();
        else{
            id2Label.setText("" + o.getId());
            book_idField.setText(""+o.getBook_id());
            customerField.setText(""+o.getCustomer());
            dateField.setText(""+o.getDate());
            statusField.setText(""+o.getStatus());
        }
    }
    @FXML
    private void clearFieldsOrder() {
        id2Label.setText("" + service.getOrderIdGenerator());
        book_idField.setText("");
        customerField.setText("");
        dateField.setText("");
        statusField.setText("");
    }

    @FXML
    public void addOrderButton(){
        String customer=customerField.getText();
        String date=dateField.getText();
        String status=statusField.getText();
        String book_idS=book_idField.getText();

        if (customer.equals("") || date.equals("") || status.equals("")|| book_idS.equals("")) {
            showErrorMessage("All fields must be completed");
            return;
        }
        try{
            int book_id=Integer.parseInt(book_idS);
            int id=service.addOrder(book_id,customer,date,status);
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
