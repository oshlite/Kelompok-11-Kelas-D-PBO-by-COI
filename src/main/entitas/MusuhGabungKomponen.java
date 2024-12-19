package main.entitas;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.entitas.elemen.MusuhElemenGabung;
import main.konst.Konstanta;

import java.util.ArrayList;
import java.util.List;

public class MusuhGabungKomponen {
    private static final int STEP_TO_ADD_MUSUH = 20;
    private List<MusuhElemenGabung> musuhElemenGabungs;
    private int uniqueMusuhElemenSize;
    private AnimationTimer timer;
    private PerahuKomponen perahu;
    private int progress;
    private Canvas canvas;

    public MusuhGabungKomponen(int uniqueMusuhElemenSize, Canvas canvas) {
        this.uniqueMusuhElemenSize = uniqueMusuhElemenSize;
        this.canvas = canvas;
        this.inisAtribut();
        inisTimer();
    }

    private void inisAtribut() {
        musuhElemenGabungs = new ArrayList<>();
        progress = 0;
    }

    private void inisTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                move();
                if (progress > 0) {
                    progress--;
                } else {
                    addMusuhGabung();
                    progress = STEP_TO_ADD_MUSUH;
                }
                draw(canvas.getGraphicsContext2D());
            }
        };
    }

    public List<MusuhElemenGabung> getMusuhElemenGabungs() {
        return musuhElemenGabungs;
    }

    public void setMusuhElemenGabungs(List<MusuhElemenGabung> musuhElemenGabungs) {
        this.musuhElemenGabungs = musuhElemenGabungs;
    }

    public int getUniqueMusuhElemenSize() {
        return uniqueMusuhElemenSize;
    }

    public void setUniqueMusuhElemenSize(int uniqueMusuhElemenSize) {
        this.uniqueMusuhElemenSize = uniqueMusuhElemenSize;
    }

    public PerahuKomponen getPerahu() {
        return perahu;
    }

    public void setPerahu(PerahuKomponen perahu) {
        this.perahu = perahu;
    }

    public void aktif() {
        timer.start();
    }

    public void destroy() {
        timer.stop();
    }

    private void move() {
        List<MusuhElemenGabung> elemenToDeletes = new ArrayList<>();
        for (MusuhElemenGabung musuhElemenGabung : musuhElemenGabungs) {
            if (musuhElemenGabung.getY() + uniqueMusuhElemenSize < Konstanta.Y_END) {
                musuhElemenGabung.setY(musuhElemenGabung.getY() + uniqueMusuhElemenSize);
            } else {
                elemenToDeletes.add(musuhElemenGabung);
            }
        }
        musuhElemenGabungs.removeAll(elemenToDeletes);
    }

    public void draw(GraphicsContext gc) {
        for (MusuhElemenGabung musuhElemenGabung : musuhElemenGabungs) {
            musuhElemenGabung.drawBig(gc);
        }
    }

    private void addMusuhGabung() {
        MusuhElemenGabung musuhElemenGabung = new MusuhElemenGabung(perahu.getX(), Konstanta.Y_BEGIN,
                uniqueMusuhElemenSize, uniqueMusuhElemenSize);
        musuhElemenGabungs.add(musuhElemenGabung);
    }
}
