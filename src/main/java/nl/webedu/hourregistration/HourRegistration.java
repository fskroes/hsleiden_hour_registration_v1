package nl.webedu.hourregistration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.dao.factory.MongoDAOFactory;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.DatabaseType;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

public class HourRegistration extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        DatabaseManager.getInstance().connectToDatabase(DatabaseType.MONGODB);
        // MongoDAOFactory mongoFactory = (MongoDAOFactory) DatabaseManager.getInstance().getDaoFactory();

        Parent root = FXMLLoader.load(getClass().getResource("/RegisterView.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        // create the required DAO Factory
//        DatabaseManager.getInstance().connectToDatabase(DatabaseType.MONGODB);
//        MongoDAOFactory mongoFactory = (MongoDAOFactory) DatabaseManager.getInstance().getDaoFactory();
//
//        // Create a DAO
//        IUserAuthenticationDAO mongoUserAuthenticationDAO = mongoFactory.getUserAuthenticationDAO();
//
//        // create a new Authentication model
//        UserAuthenticationModel aModel = mongoUserAuthenticationDAO.registerUser("root", "root");
//
//        // modify the values in the Transfer Object
//        aModel.setEmail("");
//        aModel.setPassword("");

    }
}
