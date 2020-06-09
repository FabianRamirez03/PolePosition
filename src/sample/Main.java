package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.concurrent.atomic.LongAccumulator;

public class Main extends Application {

    FirstWindow initial = new FirstWindow();
    SecondWindow secundary = new SecondWindow();
    Button button1;
    Button button2;
    ImageView mv;
    Scene scene;
    Group root;

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("PolePosition");

        root = initial.root;
        scene = initial.getScene();
        mv = initial.getImage();
        button1 = initial.getButton();
        primaryStage.setScene(scene);

        root = this.getRoot(root,mv,button1);
        primaryStage.show();

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
                primaryStage.setTitle("Color Selection");
                root = secundary.root;
                scene = secundary.getScene();
                mv = secundary.getImage();
                button1 = secundary.getButton1();
                button2 = secundary.getButton2();
                primaryStage.setScene(scene);
                root = getRoot(root,mv,button1);
                root.getChildren().add(button2);
                primaryStage.show();

            }
        });
    }

    public Group getRoot(Group root, ImageView mv, Button button){
        root.getChildren().add(mv);
        root.getChildren().add(button);
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
