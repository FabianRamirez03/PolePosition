package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

    FirstWindow firstWindow = FirstWindow.getInstance();
    SecondWindow secondWindow = SecondWindow.getInstance();
    GameWindow gameWindow = GameWindow.getInstance();
    Button button1;
    Button button2;
    ImageView imageView;
    Scene scene;
    Group root;



    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Pole Position");
        this.setFirstWindow();
        primaryStage.setScene(scene);
        primaryStage.show();

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
                primaryStage.setTitle("Color Selection");
                setSecondWindow();
                primaryStage.setScene(scene);
                primaryStage.show();

                button1.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        primaryStage.close();
                        primaryStage.setTitle("Pole Position");
                        setGameWindow();
                        primaryStage.setScene(scene);
                        primaryStage.show();

                    }
                });
            }
        });
    }

    private void setFirstWindow(){
        button1 = firstWindow.getButton();
        imageView = firstWindow.getImageView();
        scene = firstWindow.getScene();
        root = firstWindow.getRoot();
    }

    private void setSecondWindow(){
        root = secondWindow.getRoot();
        scene = secondWindow.getScene();
        imageView = secondWindow.getImageView();
        button1 = secondWindow.getButton1();
        button2 = secondWindow.getButton2();
    }

    private void setGameWindow(){
        root = gameWindow.getRoot();
        scene = gameWindow.getScene();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
