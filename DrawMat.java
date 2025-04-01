import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawMat extends Application {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DrawMat.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("War Game");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String [] args) {
        launch(args);
    }
}