package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FirstWindow {

    private static FirstWindow instance = null;

    public static FirstWindow getInstance(){
        if (instance == null){
            instance = new FirstWindow();
        }
        return instance;
    }

    private FirstWindow() {
        this.imageView = ImageView();
        this.button = Button();
        this.root = Root(imageView, button);
    }

    private ImageView imageView;
    private Button button;
    private Group root;
    private Scene scene;


    private Group Root(ImageView imageView, Button button){
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(button);
        return root;
    }
    private ImageView ImageView(){
        Image bg = new Image(getClass().getResource("Imagenes/bg.jpg").toString());
        ImageView imageView = new ImageView(bg);
        imageView.setFitWidth(500);
        imageView.setFitHeight(300);
        return imageView;
    }

    Scene getScene(){
        scene = new Scene(root);
        return scene;
    }

    private Button Button(){
        button = new Button();
        button.setText("Play");
        button.setPrefSize(100,40);
        button.setLayoutX(210);
        button.setLayoutY(130);
        return button;
    }


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
