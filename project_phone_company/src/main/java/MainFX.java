import Controller.ControllerFX;
import Repository.OrderInFileRepository;
import Repository.OrderRepository;
import Repository.PhoneInFileRepository;
import Repository.PhoneRepository;
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
    static public String phoneFileName;
    static public String orderFileName;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
            Parent root = loader.load();
            ControllerFX controller = loader.getController();
            controller.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Phones");
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
        PhoneRepository phoneRepository = new PhoneInFileRepository(phoneFileName);
        OrderRepository orderRepository = new OrderInFileRepository(orderFileName,phoneRepository);
        Service service = new Service(phoneRepository, orderRepository);
        return service;
    }
//--------------------read from file----------------------------
    private static void readFile() throws ServiceException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("PhoneApp.properties"));
            phoneFileName = properties.getProperty("PhoneFile");
            if (phoneFileName == null){
                phoneFileName = "PhoneTextFile.txt";
                System.err.println("Phone File not found. Using default" + phoneFileName);
            }
            orderFileName = properties.getProperty("OrderFile");
            if (orderFileName == null){
                orderFileName = "OrderTextFile.txt";
                System.err.println("Order File not found. Using default" + orderFileName);
            }
        }catch (IOException exception){
            throw new ServiceException("Error reading the configuration file" + exception);
        }

    }
    //

}
