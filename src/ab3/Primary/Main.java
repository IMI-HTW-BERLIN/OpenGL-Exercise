package ab3.Primary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Awesome Controller!!1!");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(screenBounds.getMinX() + screenBounds.getWidth() - 400);
        primaryStage.setY(screenBounds.getMinY() + screenBounds.getHeight()/10);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
