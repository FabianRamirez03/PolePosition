package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    FirstWindow firstWindow = FirstWindow.getInstance();
    SecondWindow secondWindow = SecondWindow.getInstance();
    GameWindow gameWindow = GameWindow.getInstance();
    Button button1;
    Button button2;
    ImageView imageView;
    ImageView redCar;
    ImageView blueCar;
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
                        root.getChildren().add(blueCar);
                        root.getChildren().add(redCar);
                        primaryStage.show();
                        scene.setOnKeyPressed(e -> {
                            switch (e.getCode()){
                                case A:
                                    if(redCar.getY() > 320){
                                        redCar.setFitWidth(redCar.getFitWidth() - 7);
                                        redCar.setFitHeight(redCar.getFitHeight() - 7);
                                        redCar.setY(redCar.getY() - 5);
                                        redCar.setX(redCar.getX() - 3.6);
                                    }
                                    if(blueCar.getX()==(redCar.getX() + 200)){
                                        break;
                                    }else {
                                        blueCar.setX(blueCar.getX() - 5);
                                    }
                                    break;
                                case D:
                                    if(redCar.getY() > 320){
                                        redCar.setFitWidth(redCar.getFitWidth() - 7);
                                        redCar.setFitHeight(redCar.getFitHeight() - 7);
                                        redCar.setY(redCar.getY() - 5);
                                        redCar.setX(redCar.getX() - 3.6);

                                    }
                                    if(blueCar.getX()==(redCar.getX() - 200)){
                                        break;
                                    }else {
                                        blueCar.setX(blueCar.getX() + 5);
                                    }
                                    break;
                            }
                        });

                    }
                });
                button2.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        primaryStage.close();
                        primaryStage.setTitle("Pole Position");
                        setGameWindow();
                        primaryStage.setScene(scene);
                        root.getChildren().add(redCar);
                        root.getChildren().add(blueCar);
                        primaryStage.show();
                        scene.setOnKeyPressed(e -> {
                            switch (e.getCode()){
                                case A:
                                    if(blueCar.getY() > 320){
                                        blueCar.setFitWidth(blueCar.getFitWidth() - 7);
                                        blueCar.setFitHeight(blueCar.getFitHeight() - 7);
                                        blueCar.setY(blueCar.getY() - 5);
                                        blueCar.setX(blueCar.getX() + 11);
                                    }
                                    if(redCar.getX()==(blueCar.getX() + 200)){
                                        break;
                                    }else {
                                        redCar.setX(redCar.getX() - 5);
                                    }
                                    break;
                                case D:
                                    if(blueCar.getY() > 320){
                                        blueCar.setFitWidth(blueCar.getFitWidth() - 7);
                                        blueCar.setFitHeight(blueCar.getFitHeight() - 7);
                                        blueCar.setY(blueCar.getY() - 5);
                                        blueCar.setX(blueCar.getX() + 11);

                                    }
                                    if(redCar.getX()==(blueCar.getX() - 200)){
                                        break;
                                    }else {
                                        redCar.setX(redCar.getX() + 5);
                                    }
                                    break;
                            }
                        });

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

        this.redCar = gameWindow.getRedCar();
        this.blueCar = gameWindow.getBlueCar();
        scene = gameWindow.getScene();
        root = gameWindow.getRoot();

    }

    public void KeyPressed(KeyEvent event){

    }



    public static void main(String[] args) {
        launch(args);
    }
}
