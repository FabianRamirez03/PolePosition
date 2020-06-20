package sample;

//Dependencias de la clase

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class FinalWindow {
    private static FinalWindow instance = null;

    private ImageView backGround;
    private String whoWin;
    private Text text;
    private Group root;
    private Scene scene;


    /**
     * Singlenton utilizado en la clase
     * @return devuelve la instancia unica de la clase
     */
    public static FinalWindow getInstance(){
        if (instance == null){
            instance = new FinalWindow();
        }
        return instance;
    }

    /**
     * Constructor
     */
    public FinalWindow() {
        this.whoWin = "Keep playing";
        this.backGround = ImageView();
        this.root = Root(backGround);
        this.scene = new Scene(root);
    }

    /**
     * Obtiene la imagen de fondo de la imagen final
     * @return imagen del fondo
     */
    private ImageView ImageView(){
        Image bg = new Image(getClass().getResource("Imagenes/bg.jpg").toString());
        ImageView imageView = new ImageView(bg);
        imageView.setFitWidth(500);
        imageView.setFitHeight(300);
        return imageView;
    }

    /**
     * Agrega el fondo al Group de la ventana
     * @param imageView Imagen del fondo
     * @return El group de la ventana
     */
    private Group Root(ImageView imageView){
        Group root = new Group();
        root.getChildren().add(imageView);
        return root;
    }


    //Getters and setters
    public static void setInstance(FinalWindow instance) {
        FinalWindow.instance = instance;
    }

    public ImageView getBackGround() {
        return backGround;
    }

    public void setBackGround(ImageView backGround) {
        this.backGround = backGround;
    }

    public String getWhoWin() {
        return whoWin;
    }

    public void setWhoWin(String whoWin) {
        this.whoWin = whoWin;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
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
}
