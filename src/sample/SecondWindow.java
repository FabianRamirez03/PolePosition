package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SecondWindow {

    private static SecondWindow instance = null;

    public static SecondWindow getInstance(){
        if (instance == null){
            instance = new SecondWindow();
        }
        return instance;
    }

    private SecondWindow() {
        this.button1 = Button1();
        this.button2 = Button2();
        this.backGround = new Image(getClass().getResource("Imagenes/PantallaSeleccion.jpg").toString());
        this.imageView = ImageView(backGround);
        this.root = Root(imageView, button1, button2);
        this.scene = Scene(root);
    }

    private Button button1;
    private Button button2;
    private Image backGround;
    private ImageView imageView;
    private Group root;
    private Scene scene;

    ImageView ImageView(Image backGround){
        imageView = new ImageView(backGround);
        imageView.setFitWidth(700);
        imageView.setFitHeight(450);
        return imageView;
    }

    Scene Scene(Group root){
        scene = new Scene(root);
        return scene;
    }

    private Button Button1(){
        button1 = new Button("Blue");
        button1.setPrefSize(50,50);
        button1.setLayoutX(210);
        button1.setLayoutY(130);
        return button1;
    }

    private Button Button2(){
        button2 = new Button("Red");
        button2.setPrefSize(50,50);
        button2.setLayoutX(270);
        button2.setLayoutY(130);
        return button2;
    }

    private Group Root(ImageView imageView, Button button1, Button button2){
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(button1);
        root.getChildren().addAll(button2);
        return root;
    }

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
