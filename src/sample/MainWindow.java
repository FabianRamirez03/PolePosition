package sample;


import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MainWindow {

    public MainWindow() {
    }

    public Canvas getGameCanvas(){
        Canvas canvas = new Canvas( 1000, 750 );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        final long startNanoTime = System.nanoTime();

        Image orangeCar = new Image(getClass().getResource("Imagenes/OrangeCar.png").toString(),true);

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




}
