package main.entitas;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.entitas.elemen.MusuhElemen;
import main.konst.Konstanta;
import main.konst.UrlKonstanta;
import main.util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MusuhKomponen {
    private static final int TIME_STEP_DOWN = 4;
    private List<MusuhElemen> musuhElemens;
    private int uniqueMusuhElemenSize;
    private AnimationTimer timer;
    private boolean leftDirection;
    private int distanceElemen;
    private int distanceElemenDown;
    private int componentSize;
    private int timeToDown = TIME_STEP_DOWN;
    private List<String> musuhInput;
    private int musuhOrder;
    private boolean winStatus;
    private int skor;
    private Canvas canvas;

    public MusuhKomponen(int uniqueMusuhElemenSize, Canvas canvas) {
        this.uniqueMusuhElemenSize = uniqueMusuhElemenSize;
        this.canvas = canvas;
        inisAtribut();
        inisTimer();
    }

    private void inisAtribut() {
        skor = 0;
        musuhElemens = new ArrayList<>();
        leftDirection = false;
        musuhInput = new ArrayList<>();
        musuhOrder = 0;
        generateMusuhInput();
    }

    private void generateMusuhInput() {
        musuhInput.add(UrlKonstanta.MUSUH_MATRIX_1);
        musuhInput.add(UrlKonstanta.MUSUH_MATRIX_2);
        musuhInput.add(UrlKonstanta.MUSUH_MATRIX_3);
        musuhInput.add(UrlKonstanta.MUSUH_MATRIX_4);
        musuhInput.add(UrlKonstanta.MUSUH_MATRIX_5);
    }

    private void inisTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                move();
                if (timeToDown > 0) {
                    timeToDown--;
                } else {
                    moveDown();
                    timeToDown = TIME_STEP_DOWN;
                }
                cekDestroy();
                draw(canvas.getGraphicsContext2D());
            }
        };
    }

    public List<MusuhElemen> getMusuhElemens() {
        return musuhElemens;
    }

    public void setMusuhElemens(List<MusuhElemen> musuhElemens) {
        this.musuhElemens = musuhElemens;
    }

    private void populateMusuhElemens(String musuhFileUrl) {
        List<List<Integer>> musuhMatrix = FileUtil.getObjectMatrix(musuhFileUrl);
        for (int i = 0; i < musuhMatrix.size(); i++) {
            List<Integer> matrixLine = musuhMatrix.get(i);
            for (int j = 0; j < matrixLine.size(); j++) {
                if (Objects.equals(matrixLine.get(j), 1)) {
                    MusuhElemen musuhElemen = new MusuhElemen(this.getX() + j * uniqueMusuhElemenSize,
                            this.getY() + i * uniqueMusuhElemenSize, uniqueMusuhElemenSize,
                            uniqueMusuhElemenSize);
                    musuhElemens.add(musuhElemen);
                }
            }
        }
        distanceElemen = this.musuhElemens.get(0).getX() - this.getX();
        distanceElemenDown = this.musuhElemens.get(0).getY() - this.getY();
        componentSize = musuhMatrix.get(0).size();
    }

    private void updateMusuhElemensLokasi() {
        int distance = this.getX() + this.distanceElemen - this.musuhElemens.get(0).getX();
        for (MusuhElemen musuhElemen : musuhElemens) {
            musuhElemen.setX(musuhElemen.getX() + distance);
        }
    }

    private void updateMusuhElemensLokasiDown() {
        int distance = this.getY() + this.distanceElemenDown - this.musuhElemens.get(0).getY();
        for (MusuhElemen musuhElemen : musuhElemens) {
            musuhElemen.setY(musuhElemen.getY() + distance);
        }
    }

    private void moveDown() {
        this.setLokasi(this.getX(), this.getY() + uniqueMusuhElemenSize);
        updateMusuhElemensLokasiDown();
    }

    private void move() {
        if (leftDirection) {
            this.setLokasi(this.getX() - uniqueMusuhElemenSize, this.getY());
        } else {
            this.setLokasi(this.getX() + uniqueMusuhElemenSize, this.getY());
        }

        updateMusuhElemensLokasi();

        if (this.getX() <= Konstanta.X_BEGIN) {
            this.setLeftDirection(false);
        } else if (this.getX() >= canvas.getWidth() - componentSize * uniqueMusuhElemenSize) {
            this.setLeftDirection(true);
        }
    }

    private void cekDestroy() {
        List<MusuhElemen> elemensToRemove = new ArrayList<>();
        for (MusuhElemen musuhElemen : musuhElemens) {
            if (musuhElemen.getY() > Konstanta.Y_END) {
                elemensToRemove.add(musuhElemen);
            }
        }
        musuhElemens.removeAll(elemensToRemove);
        if (musuhElemens.isEmpty()) {
            if (musuhOrder < musuhInput.size() - 1) {
                musuhOrder++;
                this.setLokasi(Konstanta.X_BEGIN, Konstanta.Y_BEGIN);
                populateMusuhElemens(musuhInput.get(musuhOrder));
            } else {
                timer.stop();
                winStatus = true;
            }
        }
    }

    public void draw(GraphicsContext gc) {
        for (MusuhElemen musuhElemen : musuhElemens) {
            musuhElemen.draw(gc);
        }
    }

    public void start() {
        winStatus = false;
        populateMusuhElemens(musuhInput.get(musuhOrder));
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public int getX() {
        return 0; 
    }

    public int getY() {
        return 0; 
    }

    public void setLokasi(int x, int y) {
    }

    public boolean isLeftDirection() {
        return leftDirection;
    }

    public void setLeftDirection(boolean leftDirection) {
        this.leftDirection = leftDirection;
    }

    public boolean isWinStatus() {
        return winStatus;
    }

    public int getskor() {
        return skor;
    }

    public void setskor(int skor) {
        this.skor = skor;
    }

    public void destroy() {
    }

    public void aktif() {
    }

    public void cekCollision(PeluruKomponen peluruKomponen) {
        throw new UnsupportedOperationException("Unimplemented method 'cekCollision'");
    }
}