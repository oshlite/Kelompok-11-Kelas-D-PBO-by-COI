package main.bingkai;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.papan.GameMain;
import main.konst.UrlKonstanta;
import javafx.scene.image.Image;

public class GameBingkai extends Application {

    @Override
    public void start(Stage primerStage) {
        GameMain mainGame = new GameMain();

        Scene scene = new Scene(mainGame); 
        primerStage.setScene(scene);

        primerStage.setTitle("Perahu Penyihir");
        primerStage.setResizable(false);
        
        Image icon = new Image(UrlKonstanta.PERAHU_PART);
        primerStage.getIcons().add(icon);

        primerStage.setAlwaysOnTop(true);

        primerStage.setOnShown(event -> {
            primerStage.setX((javafx.stage.Screen.getPrimary().getVisualBounds().getWidth() - primerStage.getWidth()) / 2);
            primerStage.setY((javafx.stage.Screen.getPrimary().getVisualBounds().getHeight() - primerStage.getHeight()) / 2);
        });

        primerStage.setOnCloseRequest(event -> {
        });

        primerStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
