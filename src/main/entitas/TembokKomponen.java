package main.entitas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.entitas.elemen.TembokElemen;

import java.util.ArrayList;
import java.util.List;

public class TembokKomponen {
    private List<TembokElemen> TembokElemens;
    private int unitTembokElemenSize;
    private Canvas canvas;

    public TembokKomponen(int unitTembokElemenSize, Canvas canvas) {
        this.unitTembokElemenSize = unitTembokElemenSize;
        this.canvas = canvas;
        this.inisAtribut();
        inisTembok();
    }

    private void inisAtribut() {
        this.TembokElemens = new ArrayList<>();
    }

    private void inisTembok() {
        double tinggi = canvas.getHeight();
        double lebar = canvas.getWidth();

        for (int i = 0; i < tinggi / this.unitTembokElemenSize; i++) {
            TembokElemens.add(new TembokElemen(0, i * this.unitTembokElemenSize, this.unitTembokElemenSize, this.unitTembokElemenSize));
            TembokElemens.add(new TembokElemen((int) (lebar - this.unitTembokElemenSize), i * this.unitTembokElemenSize, this.unitTembokElemenSize, this.unitTembokElemenSize));
        }
        for (int j = 1; j < lebar / this.unitTembokElemenSize - 1; j++) {
            TembokElemens.add(new TembokElemen(j * this.unitTembokElemenSize, 0, this.unitTembokElemenSize, this.unitTembokElemenSize));
            TembokElemens.add(new TembokElemen(j * this.unitTembokElemenSize, (int) (tinggi - this.unitTembokElemenSize), this.unitTembokElemenSize, this.unitTembokElemenSize));
        }
    }

    public void draw(GraphicsContext gc) {
        for (TembokElemen TembokElemen : TembokElemens) {
            TembokElemen.draw(gc);
        }
    }
}
