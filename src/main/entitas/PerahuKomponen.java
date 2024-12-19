package main.entitas;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.konst.Konstanta;
import main.entitas.elemen.PerahuElemen;
import main.entitas.elemen.MusuhElemen;
import main.entitas.elemen.MusuhElemenGabung;
import main.enums.DirectionEnum;
import main.util.Tombol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PerahuKomponen {
    private static final int ADD_Peluru_TIME = 2;
    private List<PerahuElemen> perahuElemens;
    private int uniquePerahuElemenSize;
    private PeluruKomponen PeluruKomponen;
    private int time = ADD_Peluru_TIME;
    private boolean gameOverStatus;
    private Canvas canvas;
    private Tombol keyEventListener;
    private AnimationTimer timer;

    public PerahuKomponen(int uniquePerahuElemenSize, Canvas canvas) {
        this.uniquePerahuElemenSize = uniquePerahuElemenSize;
        this.canvas = canvas;
        this.inisAtribut();
    }

    private void inisAtribut() {
        perahuElemens = new ArrayList<>();
        PeluruKomponen = new PeluruKomponen(Konstanta.STEP_DISTANCE, canvas);
    }

    private void addPeluruKomponen() {
        PeluruKomponen.setLokasi(this.getX(), this.getY());
        PeluruKomponen.aktif();
    }

    public void setKeyEventListener(Tombol keyEventListener) {
        this.keyEventListener = keyEventListener;
    }

    public List<PerahuElemen> getPerahuElemens() {
        return perahuElemens;
    }

    public void inisPerahu() {
        perahuElemens.add(new PerahuElemen(this.getX(), this.getY(), this.uniquePerahuElemenSize, this.uniquePerahuElemenSize));
        perahuElemens.add(new PerahuElemen(this.getX() - this.uniquePerahuElemenSize, this.getY(), this.uniquePerahuElemenSize, this.uniquePerahuElemenSize));
        perahuElemens.add(new PerahuElemen(this.getX() + this.uniquePerahuElemenSize, this.getY(), this.uniquePerahuElemenSize, this.uniquePerahuElemenSize));
        perahuElemens.add(new PerahuElemen(this.getX(), this.getY() - this.uniquePerahuElemenSize, this.uniquePerahuElemenSize, this.uniquePerahuElemenSize));
    }

    public void aktif() {
        gameOverStatus = false;
        addPeluruKomponen();
        startGameLoop();
    }

    private void startGameLoop() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                move();
                if (time > 0) {
                    time--;
                } else {
                    addPeluru();
                    time = ADD_Peluru_TIME;
                }
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                draw(gc);
            }
        };
        timer.start();
    }

    public void draw(GraphicsContext gc) {
        for (PerahuElemen perahuElemen : perahuElemens) {
            perahuElemen.draw(gc);
        }
        PeluruKomponen.draw(gc);
    }

    public void move() {
        if (keyEventListener != null) {
            if (keyEventListener.getDirection() == DirectionEnum.LEFT && this.getX() - uniquePerahuElemenSize >= Konstanta.X_BEGIN) {
                this.setLokasi(this.getX() - uniquePerahuElemenSize, this.getY());
            } else if (keyEventListener.getDirection() == DirectionEnum.RIGHT && this.getX() + 2 * uniquePerahuElemenSize <= Konstanta.X_END) {
                this.setLokasi(this.getX() + uniquePerahuElemenSize, this.getY());
            }
        }
        updatePeluruLokasi();
    }

    private void addPeluru() {
        PeluruKomponen.add(this.getX(), this.getY());
    }

    public void destroy() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void cekCollision(MusuhKomponen musuhKomponen) {
        Iterator<PerahuElemen> perahuIterator = perahuElemens.iterator();
        while (perahuIterator.hasNext()) {
            PerahuElemen perahuElemen = perahuIterator.next();
            for (MusuhElemen musuhElemen : musuhKomponen.getMusuhElemens()) {
                if (musuhElemen.getBatas().intersects(perahuElemen.getBatasInParent())) {
                    musuhKomponen.getMusuhElemens().remove(musuhElemen);
                    PeluruKomponen.destroy();
                    this.destroy();
                    this.gameOverStatus = true;
                    break;
                }
            }
        }
    }

    public void cekCollisionApi(MusuhGabungKomponen api) {
        for (PerahuElemen perahuElemen : perahuElemens) {
            for (MusuhElemenGabung musuhElemen : api.getMusuhElemenGabungs()) {
                if (musuhElemen.getBatas().intersects(perahuElemen.getBatasInParent())) {
                    PeluruKomponen.destroy();
                    this.destroy();
                    this.gameOverStatus = true;
                    break;
                }
            }
        }
    }

    public boolean isGameOverStatus() {
        return gameOverStatus;
    }

    public void setGameOverStatus(boolean gameOverStatus) {
        this.gameOverStatus = gameOverStatus;
    }

    public int getX() {
        if (!perahuElemens.isEmpty()) {
            return (int) perahuElemens.get(0).getImageView().getX();
        }
        return 0;
    }

    public int getY() {  
        if (!perahuElemens.isEmpty()) {
            return (int) perahuElemens.get(0).getImageView().getY();
        }
        return 0;
    }

    // private void setLokasi(int x, int y) {
    //     for (PerahuElemen elemen : perahuElemens) {
    //         elemen.setPosisi(x, y);
    //     }
    // }

    private void updatePeluruLokasi() {
        PeluruKomponen.setLokasi(this.getX(), this.getY());
    }

    public PeluruKomponen getPeluruKomponen() {
        return PeluruKomponen;
    }
    
    public void setLokasi(int x, int y) {
        for (PerahuElemen elemen : perahuElemens) {
            elemen.setPosisi(x, y);
        }
    }
    
}
