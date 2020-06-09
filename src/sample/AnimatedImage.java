package sample;

import javafx.scene.image.Image;

public class AnimatedImage {
    public Image[] frames;
    public double duration;

    public AnimatedImage() {

    }

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public static Image[] getImageArray(String name, int length){
        Image[] imageArray = new Image[length];
        imageArray[0] = new Image("recta0.png",true);
        imageArray[1] = new Image("recta1.png");
        return imageArray;
    }


}
