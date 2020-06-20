package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Ventana de seleccion del vehiculo
 */
public class SecondWindow {

    /**
     * Instancia unica de la clase second window
     */
    private static SecondWindow instance = null;

    /**
     * Obtiene la instancia unica de la clase
     * @return instancia unica de la clase
     */
    public static SecondWindow getInstance(){
        if (instance == null){
            instance = new SecondWindow();
        }
        return instance;
    }

    /**
     * Constructor privado de la clase
     */
    private SecondWindow() {
        this.button1 = Button1();
        this.button2 = Button2();
        this.backGround = new Image(getClass().getResource("Imagenes/PantallaSeleccion.jpg").toString());
        this.imageView = ImageView(backGround);
        this.root = Root(imageView, button1, button2);
        this.scene = Scene(root);
    }

    /**
     * Boton Del carro azul
     */
    private Button button1;
    /**
     * Boton del carro rojo
     */
    private Button button2;
    /**
     * Imagen del fondo
     */
    private Image backGround;

    /**
     *ImageView Aplicada a la imagen del fondo
     */
    private ImageView imageView;

    /**
     * Group de la ventana
     */
    private Group root;
    /**
     * Escena de la ventana
     */
    private Scene scene;

    /**
     * Obtiene la imageView de la imagen del fondo
     * @param backGround Imagen del fondo
     * @return ImageView del fondo
     */
    ImageView ImageView(Image backGround){
        imageView = new ImageView(backGround);
        imageView.setFitWidth(700);
        imageView.setFitHeight(450);
        return imageView;
    }

    /**
     * Obtiene la escena a partir de la Group root
     * @param root Group root
     * @return Scene de la ventana
     */
    Scene Scene(Group root){
        scene = new Scene(root);
        return scene;
    }

    /**
     * Obtiene el boton del carro azul
     * @return boton para seleccionar el carro azul
     */
    private Button Button1(){
        button1 = new Button("Blue");
        button1.setPrefSize(50,50);
        button1.setLayoutX(210);
        button1.setLayoutY(130);
        return button1;
    }

    /**
     * Obtiene el boton del carro rojo
     * @return boton para seleccionar el carro rojo
     */
    private Button Button2(){
        button2 = new Button("Red");
        button2.setPrefSize(50,50);
        button2.setLayoutX(270);
        button2.setLayoutY(130);
        return button2;
    }

    /**
     * Crea el Group root de la ventana, agregando los distintos elementos
     * @param imageView ImageView del fondo
     * @param button1 Boton del carro azul
     * @param button2 Boton del carro rojo
     * @return Group Root
     */
    private Group Root(ImageView imageView, Button button1, Button button2){
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(button1);
        root.getChildren().addAll(button2);
        return root;
    }

    //Getters and Setters

    public static void setInstance(SecondWindow instance) {
        SecondWindow.instance = instance;
    }

    public void setButton1(Button button1) {
        this.button1 = button1;
    }

    public void setButton2(Button button2) {
        this.button2 = button2;
    }

    public Image getBackGround() {
        return backGround;
    }

    public void setBackGround(Image backGround) {
        this.backGround = backGround;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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

    public Button getButton1() {
        return button1;
    }

    public Button getButton2() {
        return button2;
    }
}
