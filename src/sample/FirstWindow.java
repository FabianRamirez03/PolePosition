package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FirstWindow {

    Button button;
    Image image;
    ImageView mv;
    Group root = new Group();
    Scene scene;


    ImageView getImage(){
        Image bg = new Image("file:BG.jpg");
        ImageView mv = new ImageView(bg);
        mv.setFitWidth(500);
        mv.setFitHeight(300);
        return mv;
    }

    Scene getScene(){
        scene = new Scene(root);
        return scene;
    }

    Button getButton(){
        button = new Button();
        button.setText("Play");
        button.setPrefSize(100,40);
        button.setLayoutX(210);
        button.setLayoutY(130);
        return button;
    }


}
