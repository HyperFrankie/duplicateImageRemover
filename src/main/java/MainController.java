import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class MainController {

    public void loadFileTree() {
        TreeView<String> a = new TreeView<String>();
        BorderPane b = new BorderPane();
        Button c = new Button("Load Folder");
        c.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                DirectoryChooser dc = new DirectoryChooser();
                dc.setInitialDirectory(new File(System.getProperty("user.home")));
                File choice = dc.showDialog(primaryStage);
                if(choice == null || ! choice.isDirectory()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Could not open directory");
                    alert.setContentText("The file is invalid.");

                    alert.showAndWait();
                } else {
                    a.setRoot(getNodesForDirectory(choice));
                }
            }
        });
        b.setTop(c);
        b.setCenter(a);
        primaryStage.setScene(new Scene(b, 600, 400));
        primaryStage.setTitle("Folder View");
        primaryStage.show();
    }

}
