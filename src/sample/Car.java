package sample;

import javafx.scene.image.ImageView;

/**
 * Clase carro que almacena los atributos necesarios para el funcionamiento de cada carro.
 */
public class Car {
    /**
     * Imagen grafica
     */
    ImageView carImageView;
    /**
     * Velocidad a la que se movera el carro
     */
    Integer velocity = 10;
    /**
     * Cada boost es un aumento de 3 en la velocidad de auto
     */
    Integer boost = 3;
    /**
     * La distancia recorrida por vehiculo
     */
    Integer distance = 0;
}
