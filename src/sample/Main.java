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
    Cliente cliente = new Cliente();
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
    int posDir;
    Process process;
    public class Process extends Thread{
        private volatile boolean exit = false;
        Car ClientCar;
        Car EnemyCar;
        Integer posDir;

        Process(Car ClientCar, Car EnemyCar, Integer posDir){
            this.ClientCar = ClientCar;
            this.EnemyCar = EnemyCar;
            this.posDir = posDir;
        }

        @Override
        public void run(){
            while (!exit){
                movementEnemys(ClientCar,EnemyCar, posDir);
                cliente.update();
                try{
                    Process.sleep(75);
                }catch (Exception e){
                    System.out.println("Error thread");
                }
            }
        }
        public void stopThread() {
            exit = true;
        }
    }


    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Pole Position");
        this.setFirstWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(E -> {
            process.stopThread();
            cliente.cleanFiles();
        });
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
                        cliente.setMyCar(blueCar, "blue");
                        cliente.setRivalCar(redCar);
                        redCar.carImageView = gameWindow.getRedCar();
                        blueCar.carImageView = gameWindow.getBlueCar();
                        blueCar.velocity=10;
                        cliente.update();
                        if (cliente.checkBothCars()) {
                            primaryStage.close();
                            primaryStage.setTitle("Pole Position");
                            setGameWindow();
                            primaryStage.setScene(scene);
                            primaryStage.show();
                            posDir = -1;
                            process = new Process(blueCar,redCar,posDir);
                            process.start();
                            movement(blueCar,redCar);
                        } else {
                            System.out.println("Espera al otro jugador");
                        }
                    }
                });
                button2.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        cliente.setMyCar(redCar, "red");
                        cliente.setRivalCar(blueCar);
                        redCar.carImageView = gameWindow.getRedCar();
                        blueCar.carImageView = gameWindow.getBlueCar();
                        redCar.velocity=10;
                        cliente.update();
                        if (cliente.checkBothCars()) {
                            primaryStage.close();
                            primaryStage.setTitle("Pole Position");
                            setGameWindow();
                            primaryStage.setScene(scene);
                            primaryStage.show();
                            posDir = 3;
                            process = new Process(redCar,blueCar,posDir);
                            process.start();
                            movement(redCar,blueCar);
                        } else {
                            System.out.println("Espera al otro jugador");
                        }
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
            }
        });
    }

    void movementAux(Car ClientCar, Car EnemyCar, Integer dir){
        if(((ClientCar.carImageView.getX()==(EnemyCar.carImageView.getX() - dir*200)
                || ClientCar.carImageView.getX() + dir*200 == EnemyCar.carImageView.getX())
                && ClientCar.carImageView.getY() == EnemyCar.carImageView.getY())
                || ClientCar.carImageView.getX() == - dir*30
                || ClientCar.carImageView.getX() == dir*770) {
            if(ClientCar.velocity > 0) {
                ClientCar.velocity -= 1;
                System.out.println("Disminuyó la velocidad: " + ClientCar.velocity);
            }else{
                ClientCar.velocity = 0;
                System.out.println("La velocidad es mínima: " + ClientCar.velocity);
            }
        }else {
            ClientCar.carImageView.setX(ClientCar.carImageView.getX() + dir * 5);
        }
    }

    void movementEnemys(Car ClientCar, Car EnemyCar,Integer posDir){
        if(holes.flag == 1){
            holeSizeChange(ClientCar,holes,1, posDir);
            if((ClientCar.carImageView.getY() <= (holes.holeImageView.getY() + 35))
                    && inRange(ClientCar,holes)){
                holes.holeImageView.setX(5000);
                holes.holeImageView.setY(5000);
                ClientCar.velocity -= 3;
                if(ClientCar.velocity <0 ){
                    ClientCar.velocity = 0;
                }
                System.out.println("La velocidad es: " + ClientCar.velocity);
                holes.flag = 0;
            }
            if(ClientCar.carImageView.getY() + ClientCar.carImageView.getFitHeight() < holes.holeImageView.getY()){
                holes.holeImageView.setX(5000);
                holes.holeImageView.setY(5000);
                holes.flag = 0;
            }
        }
        if(EnemyCar.velocity != ClientCar.velocity){
            if(EnemyCar.velocity > ClientCar.velocity && EnemyCar.carImageView.getY() > 310) {
                sizeChange(ClientCar, EnemyCar, -1,posDir);
            }
            if(EnemyCar.carImageView.getY() <= 310){
                EnemyCar.distance += EnemyCar.velocity - ClientCar.velocity;
            }
            if(ClientCar.velocity > EnemyCar.velocity){
                if(EnemyCar.distance > 0){
                    EnemyCar.distance -= ClientCar.velocity - ClientCar.velocity;
                }else {
                    sizeChange(ClientCar, EnemyCar, 1, -posDir);
                }
            }
        }
    }

    void sizeChange(Car ClientCar, Car EnemyCar, Integer dir, Integer posDir){
        Integer difference = abs(ClientCar.velocity - EnemyCar.velocity);
        EnemyCar.carImageView.setFitWidth(EnemyCar.carImageView.getFitWidth() + dir*difference*(7/5));
        EnemyCar.carImageView.setFitHeight(EnemyCar.carImageView.getFitHeight() + dir*difference*(7/5));
        EnemyCar.carImageView.setY(EnemyCar.carImageView.getY() + dir*difference);
        EnemyCar.carImageView.setX(EnemyCar.carImageView.getX() + posDir*difference * 0.72);
    }

    void holeSizeChange(Car ClientCar, Hole hole, Integer dir, Integer posDir){
        Integer velocity = dir*(ClientCar.velocity);
        hole.holeImageView.setFitWidth(hole.holeImageView.getFitWidth() + velocity*(7/5));
        hole.holeImageView.setFitHeight(hole.holeImageView.getFitHeight() + velocity*(7/5));
        hole.holeImageView.setY(hole.holeImageView.getY() + velocity);
        //hole.holeImageView.setX(hole.holeImageView.getX() + posDir*velocity);
    }

    Boolean inRange(Car ClientCar, Hole holes){
        if((holes.holeImageView.getX() >= ClientCar.carImageView.getX())
        && holes.holeImageView.getX() <= ClientCar.carImageView.getX() + ClientCar.carImageView.getFitWidth()){

            if((ClientCar.carImageView.getY() >= holes.holeImageView.getY())){
                return true;
            }
        }
        if((holes.holeImageView.getX() <= ClientCar.carImageView.getX())
                && holes.holeImageView.getX() >= ClientCar.carImageView.getX()){
            if((ClientCar.carImageView.getY() >= holes.holeImageView.getY())){
                return true;
            }
        }

        return false;
    }

    Integer abs(Integer x){
        if(x>0){
            return x;
        }else{
            return -1*x;
        }
    }

    public static void main(String[] args) {
        launch(args);

    }
}
