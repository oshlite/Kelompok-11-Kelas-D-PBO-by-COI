package main.papan;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import main.konst.Konstanta;
import main.konst.UrlKonstanta;
import main.entitas.PerahuKomponen;
import main.entitas.PerahuMenu;
import main.entitas.MusuhKomponen;
import main.entitas.MusuhGabungKomponen;
import main.entitas.TembokKomponen;
import main.enums.DirectionEnum;
import main.util.Tombol;

public class GameMain extends StackPane {
    private TembokKomponen Tembok;
    private PerahuKomponen perahu;
    private MusuhKomponen musuh;
    private MusuhGabungKomponen api;
    private Tombol keyEventListener;
    private PerahuMenu menu;
    private boolean isPlayingGame;
    private Canvas canvas;

    public GameMain() {
        canvas = new Canvas(Konstanta.PAPAN_LEBAR, Konstanta.PAPAN_TINGGI);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);

        keyEventListener = new Tombol(DirectionEnum.NONE);
        this.setOnKeyPressed(this::handleKeyPress);
        this.setOnKeyReleased(this::handleKeyRelease);

        inisBoard();
        startGameLoop(gc);
    }

    private void inisBoard() {
        this.isPlayingGame = false;
        addTembok();
        addPerahu();
        addMusuh();
        addApi();
        addMenu();
    }

    private void startGameLoop(GraphicsContext gc) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                doDrawing(gc);
            }
        };
        timer.start();
    }

    private void doDrawing(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Konstanta.PAPAN_LEBAR, Konstanta.PAPAN_TINGGI);

        if (!menu.isReady()) {
            drawReadyGame(gc);
        } else {
            if (!isPlayingGame) {
                isPlayingGame = true;
                aktifGamePlay();
            }
            if (perahu.isGameOverStatus()) {
                api.destroy();
                musuh.destroy();
                isPlayingGame = false;
                menu.gameOver(gc, musuh.getskor(), Tembok, UrlKonstanta.GAME_OVER_URL);
            } else if (musuh.isWinStatus()) {
                perahu.getPeluruKomponen().destroy();
                perahu.destroy();
                api.destroy();
                isPlayingGame = false;
                menu.gameOver(gc, musuh.getskor(), Tembok, UrlKonstanta.MENANG_URL);
            } else {
                cekCollision();
                drawTembok(gc);
                drawPerahu(gc);
                drawMusuh(gc);
                drawApi(gc);
            }
        }
    }

    private void drawTembok(GraphicsContext gc) {
        Tembok.draw(gc);
    }

    private void drawPerahu(GraphicsContext gc) {
        perahu.draw(gc);
    }

    private void drawMusuh(GraphicsContext gc) {
        musuh.draw(gc);
    }

    private void drawApi(GraphicsContext gc) {
        api.draw(gc);
    }

    private void drawReadyGame(GraphicsContext gc) {
        menu.drawStartGame(gc, Tembok, perahu);
    }

    private void cekCollision() {
        musuh.cekCollision(perahu.getPeluruKomponen());
        perahu.cekCollision(musuh);
        perahu.cekCollisionApi(api);
    }

    private void addPerahu() {
        perahu = new PerahuKomponen(Konstanta.STEP_DISTANCE, canvas); 
        perahu.setLokasi(Konstanta.PERAHU_POSISI_X_BEGIN, Konstanta.PERAHU_POSISI_Y);
        perahu.inisPerahu();
        perahu.setKeyEventListener(this.keyEventListener);
    }
    
    private void addMusuh() {
        musuh = new MusuhKomponen(Konstanta.STEP_DISTANCE, canvas); 
        musuh.setLokasi(Konstanta.X_BEGIN, Konstanta.Y_BEGIN);
    }
    
    private void addApi() {
        api = new MusuhGabungKomponen(Konstanta.STEP_DISTANCE, canvas); 
        api.setPerahu(this.perahu);
    }
    
    private void addTembok() {
        Tembok = new TembokKomponen(Konstanta.Tembok_SIZE, canvas);
        
    }
    
    private void addMenu() {
        menu = new PerahuMenu(canvas); 
        menu.aktif();
    }
    
    private void aktifGamePlay() {
        perahu.aktif();
        musuh.aktif();
        api.aktif();
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !isPlayingGame && menu.isReady()) {
            inisAtribut();
        }
    }

    private void handleKeyRelease(KeyEvent event) {
    }

    private void inisAtribut() {
        this.isPlayingGame = false;
        addTembok();
        addPerahu();
        addMusuh();
        addApi();
        addMenu();
    }
}
