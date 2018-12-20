import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppStart extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    MainController controller;

    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("MainApp.fxml"));
        controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Duplicate Image Remover");
        stage.show();
        controller.mainStage = stage;
    }

}
