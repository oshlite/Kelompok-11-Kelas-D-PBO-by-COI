package main.entitas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import main.konst.Konstanta;
import main.konst.UrlKonstanta;
import main.util.Imageutil;

public class PerahuMenu {
    private static final int PROGRESS = 5;
    private Image readyImage;
    private Image gameOverImage;
    private int progress;
    private boolean ready;
    private Canvas canvas;
    private AnimationTimer timer;

    public PerahuMenu(Canvas canvas) {
        this.canvas = canvas;
        this.readyImage = Imageutil.generateImageFromUrl(UrlKonstanta.READY_IMAGE_URL);
        this.ready = false;
        this.progress = PROGRESS;
        inisTimer();
    }

    private void inisTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (progress > 0) {
                    progress--;
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    drawStartGame(gc, new TembokKomponen(Konstanta.Tembok_SIZE, canvas), new PerahuKomponen(Konstanta.STEP_DISTANCE, canvas));
                } else {
                    setReady(true);
                    stop();
                }
            }
        };
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean isReady) {
        this.ready = isReady;
    }

    public void aktif() {
        timer.start();
    }

    public void gameOver(GraphicsContext gc, int skor, TembokKomponen TembokKomponen, String urlImage) {
        this.gameOverImage = Imageutil.generateImageFromUrl(urlImage);
        String playAgain = Konstanta.PLAY_AGAIN_MESSAGE;
        gc.setFill(javafx.scene.paint.Color.YELLOW);
        gc.drawImage(this.gameOverImage, (Konstanta.PAPAN_LEBAR - Konstanta.READY_LENGTH) / 2, Konstanta.PAPAN_TINGGI / 2 - 30);
        gc.fillText(String.valueOf(skor), (Konstanta.PAPAN_LEBAR - gc.getFont().getSize()) / 2, Konstanta.PAPAN_TINGGI / 2 - 110);
        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.fillText(playAgain, (Konstanta.PAPAN_LEBAR - gc.getFont().getSize()) / 2, Konstanta.PAPAN_TINGGI - 60);
        TembokKomponen.draw(gc);
    }

    public void drawStartGame(GraphicsContext gc, TembokKomponen TembokKomponen, PerahuKomponen perahuKomponen) {
        gc.drawImage(this.readyImage, (Konstanta.PAPAN_LEBAR - Konstanta.READY_LENGTH) / 2, Konstanta.PAPAN_TINGGI / 2 - 30);
        TembokKomponen.draw(gc);
        perahuKomponen.draw(gc);
    }
}
