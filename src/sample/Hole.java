package sample;

//Dependencias de la clase
import javafx.scene.image.ImageView;

/**
 * Clase hoyo para dibuajr cuando los hoyos aparecen en pantalla
 */
public class Hole {
    /**
     * Imagen grafica para cada hoyo
     */
    ImageView holeImageView;
    /**
     *Flag para asegurarse que no haya dos hoyos en pantalla a la vez
     */
    Integer flag = 0;
}
