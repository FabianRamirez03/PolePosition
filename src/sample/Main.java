package sample;

import sample.MainWindow;
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

    FirstWindow firstWindow = FirstWindow.getInstance();
    SecondWindow secondWindow = new SecondWindow();
    Button button1;
    Button button2;
    ImageView imageView;
    Scene scene;
    Group root;



    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("PolePosition");
        this.setFirstWindow();
        primaryStage.setScene(scene);
        primaryStage.show();

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
                primaryStage.setTitle("Color Selection");
                root = secondWindow.root;
                scene = secondWindow.getScene();
                imageView = secondWindow.getImage();
                button1 = secondWindow.getButton1();
                button2 = secondWindow.getButton2();
                primaryStage.setScene(scene);
                root = getRoot(root,imageView,button1);
                root.getChildren().add(button2);
                primaryStage.show();

            }
        });
    }

    private void setFirstWindow(){
        button1 = firstWindow.getButton();
        imageView = firstWindow.getImageView();
        scene = firstWindow.getScene();
        root = firstWindow.getRoot();
    }

    public Group getRoot(Group root, ImageView imageView, Button button){
        root.getChildren().add(imageView);
        root.getChildren().add(button);
        return root;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
