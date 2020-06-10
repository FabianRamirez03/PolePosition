package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SecondWindow {

    Button button1;
    Button button2;
    Image bg;
    ImageView mv;
    Group root = new Group();
    Scene scene;

    ImageView getImage(){
        bg = new Image("file:PantallaSeleccion.jpg");
        mv = new ImageView(bg);
        mv.setFitWidth(700);
        mv.setFitHeight(450);
        return mv;
    }

    Scene getScene(){
        scene = new Scene(root);
        return scene;
    }

    Button getButton1(){
        button1 = new Button("Blue");
        button1.setPrefSize(50,50);
        button1.setLayoutX(210);
        button1.setLayoutY(130);
        return button1;
    }

    Button getButton2(){
        button2 = new Button("Red");
        button2.setPrefSize(50,50);
        button2.setLayoutX(270);
        button2.setLayoutY(130);
        return button2;
    }

}
