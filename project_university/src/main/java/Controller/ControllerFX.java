package Controller;
import Model.Student;
import Model.Class;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControllerFX {
    @FXML
    TableView<Class> classTableView;
    @FXML
    TableView<Student> studentTableView;
    @FXML
    TextField nameField, creditsField, teacherField,hoursField,name2Field,class_idField,gradeField,ageField,passedField;
    @FXML
    Label idLabel, id2Label;

    private final ObservableList<Class> classList=FXCollections.observableArrayList();
    private final ObservableList<Student> studentList=FXCollections.observableArrayList();

    private Service service;

    public ControllerFX() {}

    @FXML
    public void initialize(){
        classTableView.setItems(classList);
        studentTableView.setItems(studentList);

        classTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem)-> showClass(newItem));
        studentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem)-> showStudent(newItem));
    }

    public void setService(Service service) {
        this.service = service;
        classList.addAll(service.getClassList());
        studentList.addAll(service.getStudentList());
        idLabel.setText("" + service.getClassIdGenerator());
        id2Label.setText("" + service.getStudentIdGenerator());
    }

    //-------------------------------phone----------------------------------------------------------------------

    private void showClass(Class c) {
        if (c == null)
            clearFieldsClass();
        else{
            idLabel.setText("" + c.getId());
            nameField.setText(c.getName());
            creditsField.setText(""+c.getCredits());
            teacherField.setText(c.getTeacher());
            hoursField.setText(""+c.getHours());

        }
    }

    @FXML
    private void clearFieldsClass() {
        idLabel.setText("" + service.getClassIdGenerator());
        nameField.setText("");
        creditsField.setText("");
        teacherField.setText("");
        hoursField.setText("");

    }

    @FXML
    public void addClassButton(){
        String name=nameField.getText();
        String teacher=teacherField.getText();
        String creditsS=creditsField.getText();
        String hoursS=hoursField.getText();

        if (name.equals("") || creditsS.equals("") || hoursS.equals("")|| teacher.equals("")) {
            showErrorMessage("All fields must be completed");
            return;
        }
        try{
            int credits=Integer.parseInt(creditsS);
            int hours=Integer.parseInt(hoursS);
            int id=service.addClass(name,credits,teacher,hours);
            classList.setAll(service.getClassList());
            idLabel.setText("" + (id + 1));
        } catch (Exception exception) {
            showNotification(exception.getMessage(), Alert.AlertType.ERROR);
        }

    }


    //-------------------------------order------------------------------------------------------------------------

    private void showStudent(Student o) {
        if (o == null)
            clearFieldsStudent();
        else{
            id2Label.setText("" + o.getId());
            name2Field.setText(""+o.getName());
            class_idField.setText(""+o.getClass_id());
            gradeField.setText(""+o.getGrade());
            ageField.setText(""+o.getAge());
            passedField.setText(o.getPassed());
        }
    }
    @FXML
    private void clearFieldsStudent() {
        id2Label.setText("" + service.getStudentIdGenerator());
        name2Field.setText("");
        class_idField.setText("");
        gradeField.setText("");
        ageField.setText("");
        passedField.setText("");
    }

    @FXML
    public void addStudentButton(){
        String name=name2Field.getText();
        String class_idS=class_idField.getText();
        String gradeS=gradeField.getText();
        String ageS=ageField.getText();
        String passed=passedField.getText();

        if (name.equals("") || class_idS.equals("") || gradeS.equals("")|| ageS.equals("")|| passed.equals("")) {
            showErrorMessage("All fields must be completed");
            return;
        }
        try{
            int class_id=Integer.parseInt(class_idS);
            int age=Integer.parseInt(ageS);
            int grade=Integer.parseInt(gradeS);
            int id=service.addStudent(name,class_id,grade,age,passed);
            studentList.setAll(service.getStudentList());
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
