package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Stage primaryStage;
    private double xOffset = 0.0;
    private double yOffset = 0.0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Student Manager");

        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setX(mouseEvent.getScreenX() - xOffset);
                primaryStage.setY(mouseEvent.getScreenY() - yOffset);

            }
        });

        primaryStage.setScene(new Scene(root));

        Stage loginStage = new Stage();
        Parent pane = FXMLLoader.load(getClass().getResource("waiting.fxml"));
        loginStage.initStyle(StageStyle.DECORATED.UNDECORATED);

        loginStage.setScene(new Scene(pane));

        loginStage.show();
        loginStage.centerOnScreen();

        //On ferme la fenêtre de chargement après 3 secondes
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                Platform.runLater(() -> loginStage.close());
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //On retarde la fenêtre principale de 3 secondes
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                Platform.runLater(() -> {
                    primaryStage.show();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        primaryStage.centerOnScreen();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
