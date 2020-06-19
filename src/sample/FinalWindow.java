package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


    public static FinalWindow getInstance(){
        if (instance == null){
            instance = new FinalWindow();
        }
        return instance;
    }

    public FinalWindow() {
        this.whoWin = "Keep playing";
        this.backGround = ImageView();
        this.root = Root(backGround);
        this.scene = new Scene(root);
    }

    private ImageView ImageView(){
        Image bg = new Image(getClass().getResource("Imagenes/bg.jpg").toString());
        ImageView imageView = new ImageView(bg);
        imageView.setFitWidth(500);
        imageView.setFitHeight(300);
        return imageView;
    }


    private Group Root(ImageView imageView){
        Group root = new Group();
        root.getChildren().add(imageView);
        return root;
    }

    public void updateText(){
        //Creating a Text object
        Text text = new Text();
        //Setting the text to be added.
        text.setText(this.whoWin);
        //setting the position of the text
        text.setX(50);
        text.setY(50);

        FinalWindow.getInstance().text = text;

        FinalWindow.getInstance().root.getChildren().add(text);
    }

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
