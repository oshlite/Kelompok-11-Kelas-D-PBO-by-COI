package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.bingkai.GameBingkai;

public class game extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    

    @Override
    public void start(Stage primerStage) {
        GameBingkai gameBingkai = new GameBingkai();
        gameBingkai.start(primerStage);
    }
}