package sample;
//Dependencias de la clase
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Ventana inicial donde se encuentra el menu del play y asi iniciar la aplicacion
 */
public class FirstWindow {

    /**
     * Istancia unica de la clase
     */
    private static FirstWindow instance = null;

    /**
     * Singleton aplicado
     * @return Istancia unica de la clase
     */
    public static FirstWindow getInstance(){
        if (instance == null){
            instance = new FirstWindow();
        }
        return instance;
    }

    /**
     * Constructor privado de la clase
     */
    private FirstWindow() {
        this.imageView = ImageView();
        this.button = Button();
        this.root = Root(imageView, button);
    }

    /**
     * Imagen de fondo a mostrar en la pantalla
     */
    private ImageView imageView;
    private Button button;
    private Group root;
    private Scene scene;


    /**
     * Crea el grupo y le agrega los elementos
     * @param imageView Fondo de la ventana
     * @param button Boton de la ventana
     * @return Group con los elementos agragados
     */
    private Group Root(ImageView imageView, Button button){
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(button);
        return root;
    }

    /**
     * Crea el ImageView de la imagen de fondo
     * @return El ImageView del fondo de la imagen
     */
    private ImageView ImageView(){
        Image bg = new Image(getClass().getResource("Imagenes/bg.jpg").toString());
        ImageView imageView = new ImageView(bg);
        imageView.setFitWidth(500);
        imageView.setFitHeight(300);
        return imageView;
    }

    /**
     * Crea la escena correspondiente a esta ventana
     * @return La escena de la ventana
     */
    Scene getScene(){
        scene = new Scene(root);
        return scene;
    }

    /**
     * Crea el boton para ser colocado en la ventana
     * @return boton para ingresar al juego
     */
    private Button Button(){
        button = new Button();
        button.setText("Play");
        button.setPrefSize(100,40);
        button.setLayoutX(210);
        button.setLayoutY(130);
        return button;
    }


    //Getters and Setters

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }
}
