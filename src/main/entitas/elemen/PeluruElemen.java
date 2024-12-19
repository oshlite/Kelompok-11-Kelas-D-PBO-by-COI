package main.entitas.elemen;

import main.konst.UrlKonstanta;
import main.util.Imageutil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PeluruElemen extends GameUnitObject {
    private ImageView imageView;

    public PeluruElemen(int x, int y, int lebar, int tinggi) {
        super(Imageutil.generateImageFromUrl(UrlKonstanta.PELURU), x, y, lebar, tinggi);
        Image image = getImage();
        if (image.isError()) {
            System.err.println("Error loading image from URL: " + UrlKonstanta.PELURU);
        }
        imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(lebar);
        imageView.setFitHeight(tinggi);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
