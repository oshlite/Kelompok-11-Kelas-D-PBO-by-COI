package main.entitas.elemen;

import main.konst.UrlKonstanta;
import main.util.Imageutil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TembokElemen extends GameUnitObject {
    private ImageView imageView;

    public TembokElemen(int x, int y, int lebar, int tinggi) {
        super(Imageutil.generateImageFromUrl(UrlKonstanta.Tembok_URL), x, y, lebar, tinggi);
        Image image = getImage();
        if (image.isError()) {
            System.err.println("Error loading image from URL: " + UrlKonstanta.Tembok_URL);
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
