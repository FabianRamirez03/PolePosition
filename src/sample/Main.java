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
    Car redCar = new Car();
    Car blueCar = new Car();
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
                        root.getChildren().add(blueCar.carImageView);
                        root.getChildren().add(redCar.carImageView);
                        primaryStage.show();
                        blueCar.velocity=10;
                        movement(blueCar,redCar);
                    }
                });
                button2.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        primaryStage.close();
                        primaryStage.setTitle("Pole Position");
                        setGameWindow();
                        primaryStage.setScene(scene);
                        root.getChildren().add(redCar.carImageView);
                        root.getChildren().add(blueCar.carImageView);
                        primaryStage.show();
                        scene.setOnKeyPressed(e -> {
                            switch (e.getCode()){
                                case A:
                                    if(blueCar.carImageView.getY() > 300){
                                        blueCar.carImageView.setFitWidth(blueCar.carImageView.getFitWidth() - 7);
                                        blueCar.carImageView.setFitHeight(blueCar.carImageView.getFitHeight() - 7);
                                        blueCar.carImageView.setY(blueCar.carImageView.getY() - 5);
                                        blueCar.carImageView.setX(blueCar.carImageView.getX() + 11);
                                    }
                                    if(redCar.carImageView.getX()==(blueCar.carImageView.getX() + 200)){
                                        break;
                                    }else {
                                        redCar.carImageView.setX(redCar.carImageView.getX() - 5);
                                    }
                                    break;
                                case D:
                                    if(blueCar.carImageView.getY() > 300){
                                        blueCar.carImageView.setFitWidth(blueCar.carImageView.getFitWidth() - 7);
                                        blueCar.carImageView.setFitHeight(blueCar.carImageView.getFitHeight() - 7);
                                        blueCar.carImageView.setY(blueCar.carImageView.getY() - 5);
                                        blueCar.carImageView.setX(blueCar.carImageView.getX() + 11);

                                    }
                                    if(redCar.carImageView.getX()==(blueCar.carImageView.getX() - 200)){
                                        break;
                                    }else {
                                        redCar.carImageView.setX(redCar.carImageView.getX() + 5);
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
        redCar.carImageView = gameWindow.getRedCar();
        blueCar.carImageView = gameWindow.getBlueCar();
        scene = gameWindow.getScene();
        root = gameWindow.getRoot();

    }

    public void KeyPressed(KeyEvent event){

    }

    public void movement(Car ClientCar,Car EnemyCar){
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case A:
                    movementAux(ClientCar, EnemyCar, -1);
                    break;
                case D:
                    movementAux(ClientCar, EnemyCar, 1);
                    break;
            }
        });
    }

    void movementAux(Car ClientCar, Car EnemyCar, Integer dir){
        if(EnemyCar.carImageView.getY() > 300
                && EnemyCar.velocity != ClientCar.velocity){
            if(EnemyCar.velocity > ClientCar.velocity) {
                Integer difference = EnemyCar.velocity - ClientCar.velocity;
                EnemyCar.carImageView.setFitWidth(EnemyCar.carImageView.getFitWidth() - difference*(7/5));
                EnemyCar.carImageView.setFitHeight(EnemyCar.carImageView.getFitHeight() - difference*(7/5));
                EnemyCar.carImageView.setY(EnemyCar.carImageView.getY() - difference);
                EnemyCar.carImageView.setX(EnemyCar.carImageView.getX() - difference * 0.72);
            }else{
                Integer difference = ClientCar.velocity - EnemyCar.velocity;
                EnemyCar.carImageView.setY(EnemyCar.carImageView.getY() + difference);
            }
        }
        if(((ClientCar.carImageView.getX()==(EnemyCar.carImageView.getX() - dir*200)
                || ClientCar.carImageView.getX() + dir*200 == EnemyCar.carImageView.getX())
                && ClientCar.carImageView.getY() == EnemyCar.carImageView.getY())
                || ClientCar.carImageView.getX() == - dir*30
                || ClientCar.carImageView.getX() == dir*770) {
            ClientCar.velocity -= 1;
            System.out.println("Disminuyó la velocidad: " + ClientCar.velocity);
        }else {
            ClientCar.carImageView.setX(ClientCar.carImageView.getX() + dir * 5);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
