package sample;

import sample.MainWindow;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle( "Timeline Example" );
        Group root = new Group();
        Scene theScene = new Scene( root );
        primaryStage.setScene( theScene );

        MainWindow mainWindow = new MainWindow();

        root.getChildren().add(mainWindow.getGameCanvas());

        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
