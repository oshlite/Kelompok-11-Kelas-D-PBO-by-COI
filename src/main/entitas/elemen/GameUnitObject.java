package main.entitas.elemen;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class GameUnitObject {
    private Image image;
    private int x;
    private int y;
    private int lebar;
    private int tinggi;

    public GameUnitObject(Image image, int x, int y, int lebar, int tinggi) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.lebar = lebar;
        this.tinggi = tinggi;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLebar() {
        return lebar;
    }

    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    public int getTinggi() {
        return tinggi;
    }

    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }

    /*
     * Draw with default size
     */
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.x, this.y, this.lebar, this.tinggi);
    }

    /*
     * Draw with increased size
     */
    public void drawBig(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.x - 15, this.y - 15, this.lebar + 30, this.tinggi + 30);
    }

    public Rectangle getBatas() {
        return new Rectangle(x, y, lebar, tinggi);
    }
}