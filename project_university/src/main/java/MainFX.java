import Controller.ControllerFX;
import Repository.StudentInFileRepository;
import Repository.StudentRepository;
import Repository.ClassInFileRepository;
import Repository.ClassRepository;
import Service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MainFX extends Application {
    static public String classFileName;
    static public String studentFileName;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
            Parent root = loader.load();
            ControllerFX controller = loader.getController();
            controller.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("University");
            primaryStage.show();
        }catch(Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error while starting app " + exception);
            alert.showAndWait();
            System.err.println(exception);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    //---------------------get service--------------------------
    static Service getService() throws ServiceException {
        readFile();
        ClassRepository classRepository = new ClassInFileRepository(classFileName);
        StudentRepository studentRepository = new StudentInFileRepository(studentFileName,classRepository);
        Service service = new Service(classRepository, studentRepository);
        return service;
    }
    //--------------------read from file----------------------------
    private static void readFile() throws ServiceException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("App.properties"));
            classFileName = properties.getProperty("ClassFile");
            if (classFileName == null){
                classFileName = "ClassTextFile.txt";
                System.err.println("Class File not found. Using default" + classFileName);
            }
            studentFileName = properties.getProperty("StudentFile");
            if (studentFileName == null){
                studentFileName = "StudentTextFile.txt";
                System.err.println("Student File not found. Using default" + studentFileName);
            }
        }catch (IOException exception){
            throw new ServiceException("Error reading the configuration file" + exception);
        }

    }
    //

}
