package sample;

import javafx.scene.image.Image;

public class AnimatedImage {
    /**
     * Singleton utilizado, debido a que solo se utiliza una imagen animada
     */
    private static AnimatedImage instance = null;

    /**
     * Singleton de la clase AnimatedImage
     * @return instancia unica de la clase
     */
    public static AnimatedImage getInstance(){
        if (instance == null){
            instance = new AnimatedImage();
        }
        return instance;
    }


    public Image[] frames;
    public double duration;


    /**
     *Obtiene el frame a mostrar por segundo
     * @param time tiempo actual en el que trabaja el programa
     * @return devuelve el exacto a mostrar
     */
    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }


    /**
     * Define la velocidad a lq que se intercambiaran los frames de la imagen animada
     * @param carSpeed la velocidad depende del auto referencia
     */
    public static void setDuration(double carSpeed) {
        if (carSpeed>0 && carSpeed <= 22) {
            AnimatedImage.getInstance().duration = (carSpeed*-0.065)+1.5;
        } else {
            AnimatedImage.getInstance().duration = 0;
        }
    }
}
