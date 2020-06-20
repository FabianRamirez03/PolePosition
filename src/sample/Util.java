package sample;

import javafx.scene.image.Image;

/**
 * Clase abstracta util para funciones con alguna funcion especifica que se usan globalmente. metodos estaticos
 */
public abstract class Util {

    /**
     * Obtiene un array de imagenes para ser reproducidos y formar una imagen animada
     * @param name Path de la imagen
     * @param length cantidad de imagenes de las que se compone el array
     * @return Array de imagenes
     */
    public static Image[] getImageArray(String name, int length){
        Image[] images = new Image[length];
        for(int i = 0; i<length; i++){
            images[i] = new Image(Util.class.getResource("Imagenes/"+name+i+".png").toString(),true);
        }
        return images;
    }


}
