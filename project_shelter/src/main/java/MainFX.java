
import Controller.ControllerFX;
import Repository.AnimalInFileRepository;
import Repository.AnimalRepository;
import Repository.ShelterInFileRepository;
import Repository.ShelterRepository;
import Service.Service;
import Service.ServiceException;
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
    static public String shelterFileName;
    static public String animalFileName;

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
        ShelterRepository shelterRepository = new ShelterInFileRepository(shelterFileName);
        AnimalRepository animalRepository = new AnimalInFileRepository(animalFileName,shelterRepository);
        Service service = new Service(shelterRepository, animalRepository);
        return service;
    }
    //--------------------read from file----------------------------
    private static void readFile() throws ServiceException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("App.properties"));
            shelterFileName = properties.getProperty("ShelterFile");
            if (shelterFileName == null){
                shelterFileName = "ShelterTextFile.txt";
                System.err.println("Shelter File not found. Using default" + shelterFileName);
            }
            animalFileName = properties.getProperty("AnimalFile");
            if (animalFileName == null){
                animalFileName = "AnimalTextFile.txt";
                System.err.println("Animal File not found. Using default" + animalFileName);
            }
        }catch (IOException exception){
            throw new ServiceException("Error reading the configuration file" + exception);
        }

    }
    //

}
