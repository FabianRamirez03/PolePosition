package sample;


import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameWindow {

    private static GameWindow instance = null;

    private Canvas canvas;
    private Group root;
    private Scene scene;

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

    private Canvas getGameCanvas(){
        Canvas canvas = new Canvas( 1000, 750 );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        final long startNanoTime = System.nanoTime();

        AnimatedImage fondo = new AnimatedImage();
        Image[] imageArray = new Image[2];
        imageArray[0] = new Image(getClass().getResource("Imagenes/recta0.png").toString(),true);
        imageArray[1] = new Image(getClass().getResource("Imagenes/recta1.png").toString(),true);
        fondo.frames = imageArray;
        fondo.duration = 0.100;
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;


                gc.drawImage( fondo.getFrame(t), 0, 0 );

            }
        }.start();
        return canvas;
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
