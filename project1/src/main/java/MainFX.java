import Controller.ControllerFX;

import Repository.ActivityInFileRepository;
import Repository.ActivityRepository;
import Repository.RegistrationInFileRepository;
import Repository.RegistrationRepository;
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
    static public String activityFileName;
    static public String registrationFileName;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
            Parent root = loader.load();
            ControllerFX controller = loader.getController();
            controller.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Exam");
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
        ActivityRepository activityRepository = new ActivityInFileRepository(activityFileName);
        RegistrationRepository registrationRepository = new RegistrationInFileRepository(registrationFileName,activityRepository);
        Service service = new Service(activityRepository, registrationRepository);
        return service;
    }
    //--------------------read from file----------------------------
    private static void readFile() throws ServiceException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("App.properties"));
            activityFileName = properties.getProperty("ActivityFile");
            if (activityFileName == null){
                activityFileName = "ActivityTextFile.txt";
                System.err.println("Activity File not found. Using default" + activityFileName);
            }
            registrationFileName = properties.getProperty("RegistrationFile");
            if (registrationFileName == null){
                registrationFileName = "RegistrationTextFile.txt";
                System.err.println("Registration File not found. Using default" + registrationFileName);
            }
        }catch (IOException exception){
            throw new ServiceException("Error reading the configuration file" + exception);
        }

    }
    //


}

