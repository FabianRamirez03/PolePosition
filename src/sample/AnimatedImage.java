package sample;

import javafx.scene.image.Image;

public class AnimatedImage {
    private static AnimatedImage instance = null;

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


    public static void setDuration(double carSpeed) {
        if (carSpeed>0 && carSpeed <= 22) {
            AnimatedImage.getInstance().duration = (carSpeed*-0.065)+1.5;
        } else {
            AnimatedImage.getInstance().duration = 0;
        }
    }
}
