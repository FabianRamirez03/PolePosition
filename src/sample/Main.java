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
    Hole holes = new Hole();
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
                        blueCar.velocity=10;
                        movement(blueCar,redCar);
                        /*gameWindow.updateCanvas(blueCar.carImageView.getX(),
                                                blueCar.carImageView.getY(),
                                                redCar.carImageView.getX(),
                                                redCar.carImageView.getY());
                        root = gameWindow.getRoot();
                        */
                    }

                });
                button2.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        primaryStage.close();
                        primaryStage.setTitle("Pole Position");
                        setGameWindow();
                        primaryStage.setScene(scene);
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
        root.getChildren().add(redCar.carImageView);
        root.getChildren().add(blueCar.carImageView);

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
                case W:
                    if (ClientCar.boost > 0) {
                        ClientCar.velocity += 3;
                        ClientCar.boost --;
                    }
                    System.out.println("El carro tomó un boost: " + ClientCar.velocity);
                    break;
                case S:
                    if(holes.flag == 0){
                        holes.holeImageView = gameWindow.getHoles();
                        holes.holeImageView.setX(495);
                        holes.holeImageView.setY(310);
                        root.getChildren().add(holes.holeImageView);
                        holes.flag = 1;
                    }

                    break;
            }
        });
    }

    void movementAux(Car ClientCar, Car EnemyCar, Integer dir){
        if(holes.flag == 1){
            holeSizeChange(ClientCar,holes,1);
            if((ClientCar.carImageView.getY() < (holes.holeImageView.getY() + 35))
            && inRange(ClientCar,holes)){
                ClientCar.velocity -= 3;
                holes.holeImageView.setX(5000);
                holes.holeImageView.setY(5000);
                System.out.println("Chocó, se redujo la velocidad: " + ClientCar.velocity);
                holes.flag = 0;
            }
            if(ClientCar.carImageView.getY() + ClientCar.carImageView.getFitHeight() < holes.holeImageView.getY()){
                System.out.println("No chocó");
                holes.flag = 0;
            }
        }
        if(EnemyCar.velocity != ClientCar.velocity){
            if(EnemyCar.velocity > ClientCar.velocity && EnemyCar.carImageView.getY() > 310) {
                sizeChange(ClientCar, EnemyCar, -1);
            }
            if(ClientCar.velocity > EnemyCar.velocity){
                sizeChange(ClientCar, EnemyCar, 1);
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

    void sizeChange(Car ClientCar, Car EnemyCar, Integer dir){
        Integer difference = dir*(ClientCar.velocity - EnemyCar.velocity);
        EnemyCar.carImageView.setFitWidth(EnemyCar.carImageView.getFitWidth() + dir*difference*(7/5));
        EnemyCar.carImageView.setFitHeight(EnemyCar.carImageView.getFitHeight() + dir*difference*(7/5));
        EnemyCar.carImageView.setY(EnemyCar.carImageView.getY() + dir*difference);
        EnemyCar.carImageView.setX(EnemyCar.carImageView.getX() + dir*difference * 0.72);
    }

    void holeSizeChange(Car ClientCar, Hole hole, Integer dir){
        Integer velocity = (ClientCar.velocity)/4;
        hole.holeImageView.setFitWidth(hole.holeImageView.getFitWidth() + velocity*(7/5));
        hole.holeImageView.setFitHeight(hole.holeImageView.getFitHeight() + velocity*(7/5));
        hole.holeImageView.setY(hole.holeImageView.getY() + velocity);
        hole.holeImageView.setX(hole.holeImageView.getX() - velocity * 0.72);
    }

    Boolean inRange(Car ClientCar, Hole holes){
        if((ClientCar.carImageView.getX() > holes.holeImageView.getX())
        && ClientCar.carImageView.getX() < (holes.holeImageView.getX() + holes.holeImageView.getFitWidth())){

            if((ClientCar.carImageView.getY() >= holes.holeImageView.getY())
                    && ClientCar.carImageView.getY() < (holes.holeImageView.getY() + holes.holeImageView.getFitHeight())){
                return true;

            }
        }

        return false;
    }

    public static void main(String[] args) {

        //launch(args);
        Cliente cliente = new Cliente();
        cliente.start();
    }
}
