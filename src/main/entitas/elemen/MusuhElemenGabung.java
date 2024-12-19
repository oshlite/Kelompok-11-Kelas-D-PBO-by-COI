package main.entitas.elemen;

import main.konst.UrlKonstanta;
import main.util.Imageutil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MusuhElemenGabung extends GameUnitObject {
    private ImageView imageView;

    public MusuhElemenGabung(int x, int y, int lebar, int tinggi) {
        super(Imageutil.generateImageFromUrl(UrlKonstanta.API_URL), x, y, lebar, tinggi);
        Image image = getImage();
        if (image.isError()) {
            System.err.println("Error loading image from URL: " + UrlKonstanta.API_URL);
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
