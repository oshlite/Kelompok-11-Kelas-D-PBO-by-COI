package main.util;

import javafx.scene.image.Image;

public class Imageutil {
    private Imageutil() {
        super();
    }

    public static Image generateImageFromUrl(String url) {
        return new Image(url);
    }
}
