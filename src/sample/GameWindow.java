package sample;

//Dependencias de la clase
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Pantalla principal del juego
 */
public class GameWindow {

    /**
     * Instancia unica de la clase
     */
    private static GameWindow instance = null;

    /**
     * Canvas para dibujar los elementos
     */
    private Canvas canvas;
    /**
     * Group de la clase
     */
    private Group root;
    /**
     * Escena de la clase
     */
    private Scene scene;
    /**
     * Imagen Grafico del carro azul para ser dibujada
     */
    private ImageView blueCar;
    /**
     * Imagen Grafico del carro rojo para ser dibujada
     */
    private ImageView redCar;
    /**
     * Imagen Grafico del hoyo para ser dibujada
     */
    private ImageView hole;


    /**
     * Obtiene la instancia unica de la clase
     * @return Instancia unica de la clase
     */
    public static GameWindow getInstance(){
        if (instance == null){
            instance = new GameWindow();
        }
        return instance;
    }

    /**
     * Constructor privado de la clase
     */
    private GameWindow() {
        this.canvas = getGameCanvas();
        this.root = new Group();
        this.scene = setScene(root);
        root.getChildren().add(canvas);
    }

    /**
     * Obtiene la imagen grafica de carro rojo para ser dibujada
     * @return imagen grafica del carro rojo
     */
    ImageView getRedCar(){
        Image car = new Image(getClass().getResource("Imagenes/carroNaranja.png").toString());
        redCar = new ImageView(car);
        redCar.setFitHeight(165);
        redCar.setFitWidth(170);
        redCar.setX(620);
        redCar.setY(450);
        return redCar;
    }

    /**
     * Obtiene la imagen grafica de carro azul para ser dibujada
     * @return imagen grafica del carro azul
     */
    ImageView getBlueCar(){
        Image car = new Image(getClass().getResource("Imagenes/carroAzul.png").toString());
        blueCar = new ImageView(car);
        blueCar.setFitHeight(200);
        blueCar.setFitWidth(200);
        blueCar.setX(200);
        blueCar.setY(450);
        return blueCar;
    }

    /**
     * Obtiene la imagen grafica de los hoyos
     * @return imagen grafica de los hoyos
     */
    ImageView getHoles(){
        Image holeImage = new Image(getClass().getResource("Imagenes/hole.png").toString());
        hole = new ImageView(holeImage);
        hole.setFitHeight(50);
        hole.setFitWidth(50);
        return hole;
    }

    /**
     * Obtiene El canvas dibujado con los elementos a dibujar y el fondo dinamico
     * @return canvas con los elementos dibujados
     */
    private Canvas getGameCanvas(){
        Canvas canvas = new Canvas( 1000, 750 );
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        final long startNanoTime = System.nanoTime();


        AnimatedImage fondo = AnimatedImage.getInstance();
        Image[] fondoArray = Util.getImageArray("recta",2);
        fondo.frames = fondoArray;
        fondo.duration = 0.85;


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

    //Getters and setters

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
