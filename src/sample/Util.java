package sample;

import javafx.scene.image.Image;

public abstract class Util {

    public static Image[] getImageArray(String name, int length){
        Image[] images = new Image[length];
        for(int i = 0; i<length; i++){
            images[i] = new Image(Util.class.getResource("Imagenes/"+name+i+".png").toString(),true);
        }
        return images;
    }


}
