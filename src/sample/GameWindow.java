package sample;


import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameWindow {

    private static GameWindow instance = null;

    private Canvas canvas;
    private Group root;
    private Scene scene;
    private ImageView blueCar;
    private ImageView redCar;
    private ImageView hole;


    public static GameWindow getInstance(){
        if (instance == null){
            instance = new GameWindow();
        }
        return instance;
    }

    private GameWindow() {
        this.canvas = getGameCanvas();
        this.root = new Group();
        this.scene = setScene(root);
        root.getChildren().add(canvas);
    }

    ImageView getRedCar(){

        Image car = new Image(getClass().getResource("Imagenes/carroNaranja.png").toString());
        redCar = new ImageView(car);
        redCar.setFitHeight(165);
        redCar.setFitWidth(170);
        redCar.setX(620);
        redCar.setY(450);
        return redCar;
    }

    ImageView getBlueCar(){
        Image car = new Image(getClass().getResource("Imagenes/carroAzul.png").toString());
        blueCar = new ImageView(car);
        blueCar.setFitHeight(200);
        blueCar.setFitWidth(200);
        blueCar.setX(200);
        blueCar.setY(450);
        return blueCar;
    }

    ImageView getHoles(){
        Image holeImage = new Image(getClass().getResource("Imagenes/hole.png").toString());
        hole = new ImageView(holeImage);
        hole.setFitHeight(50);
        hole.setFitWidth(50);
        return hole;
    }

    private Canvas getGameCanvas(){
        Canvas canvas = new Canvas( 1000, 750 );
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        final long startNanoTime = System.nanoTime();


        AnimatedImage fondo = new AnimatedImage();
        Image[] fondoArray = Util.getImageArray("recta",2);
        fondo.frames = fondoArray;
        fondo.duration = 0.100;


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                graphicsContext.drawImage( fondo.getFrame(t), 0, 0 );
            }
        }.start();
        return canvas;
    }

    public void updateCanvas(double oneX, double oneY, double twoX, double twoY){
        Canvas canvas = new Canvas( 1000, 750 );
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        final long startNanoTime = System.nanoTime();


        AnimatedImage fondo = new AnimatedImage();
        Image[] fondoArray = Util.getImageArray("recta",2);
        fondo.frames = fondoArray;
        fondo.duration = 0.100;

        AnimatedImage car1 = new AnimatedImage();
        Image[] car1Array = Util.getImageArray("carro", 2);
        car1.frames = car1Array;
        car1.duration = 0.100;


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                graphicsContext.drawImage( fondo.getFrame(t), 0, 0 );
                graphicsContext.drawImage(car1.getFrame(t), oneX, oneY);
            }
        }.start();
        this.root.getChildren().add(canvas);
    }

    private Scene setScene(Group root){
        scene = new Scene(root);
        return scene;
    }


    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
