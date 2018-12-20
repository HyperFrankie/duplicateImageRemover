import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainController {

    public static Stage mainStage;

    @FXML
    public TreeView<CheckBoxTreeCell> fileTree;

    public void loadFileTree() {
        BorderPane b = new BorderPane();
        Button c = new Button("Load Folder");
        c.setOnAction(e -> {
            DirectoryChooser dc = new DirectoryChooser();
            dc.setInitialDirectory(new File(System.getProperty("user.home")));
            File choice = dc.showDialog(mainStage);
            if(choice == null || ! choice.isDirectory()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Could not open directory");
                alert.setContentText("The file is invalid.");

                alert.showAndWait();
            } else {
                fileTree.setRoot(getNodesForDirectory(choice));
            }
        });
        b.setTop(c);
        b.setCenter(fileTree);
        mainStage.setScene(new Scene(b, 600, 400));
        mainStage.setTitle("Duplicate Image Remover");
        mainStage.show();
    }

    public CheckBoxTreeItem<String> getNodesForDirectory(File directory) {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>(directory.getName());
        for(File f : directory.listFiles()) {
            System.out.println("Loading " + f.getName());
            if(f.isDirectory()) { //Then we call the function recursively
                root.getChildren().add(getNodesForDirectory(f));
            } else {
                root.getChildren().add(new CheckBoxTreeItem(f.getName()));
            }
        }
        return root;
    }

}
