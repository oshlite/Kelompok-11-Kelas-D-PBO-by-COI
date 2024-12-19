package main.entitas.elemen;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;

public class PerahuElemen {
    private ImageView imageView;

    public PerahuElemen(int x, int y, int lebar, int tinggi) {
        imageView = new ImageView();
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(lebar);
        imageView.setFitHeight(tinggi);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setPosisi(int x, int y) {
        imageView.setX(x);
        imageView.setY(y);
    }

    public Bounds getBatasInParent() {
        return imageView.getBoundsInParent();
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(imageView.getImage(), imageView.getX(), imageView.getY(), imageView.getFitWidth(), imageView.getFitHeight());
    }
}
