package main.entitas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.entitas.elemen.PeluruElemen;

import java.util.ArrayList;
import java.util.List;

public class PeluruKomponen {
    private int peluruSize;
    private List<PeluruElemen> peluruElemens;
    public PeluruKomponen(int peluruSize, Canvas canvas) {
        this.peluruSize = peluruSize;
        peluruElemens = new ArrayList<>();
    }

    public void setLokasi(int x, int y) {
    }

    public void aktif() {
    }

    public void add(int x, int y) {
        PeluruElemen peluruElemen = new PeluruElemen(x, y, peluruSize, peluruSize);
        peluruElemens.add(peluruElemen);
    }

    public void draw(GraphicsContext gc) {
        for (PeluruElemen peluruElemen : peluruElemens) {
            peluruElemen.draw(gc);
        }
    }

    public void destroy() {
        peluruElemens.clear();
    }
}
