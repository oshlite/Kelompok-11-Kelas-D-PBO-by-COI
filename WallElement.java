package main.entity.element;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import main.constants.UrlConstants;
import main.utils.ImageUtils;


public class WallElement extends UnitGameObject{

  public WallElement(int x, int y, int width, int height)
  {
    super(ImageUtils.generateImageFromUrl(UrlConstants.WALL_URL), x, y, width, height);
  }
  @Override
    public void draw(Graphics g, ImageObserver container) {
        // Menggambar dinding dengan warna biru dan tampilan gambar
        g.setColor(Color.BLUE);
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), container);
    }
	public void drawBig(Graphics g, ImageObserver container) {
        g.setColor(Color.BLUE);
        g.fillRect(this.getX() - 15, this.getY() - 15, this.getWidth() + 30, this.getHeight() + 30);
        g.drawImage(this.getImage(), this.getX() - 15, this.getY() - 15, this.getWidth() + 30, this.getHeight() + 30, container);
    }

}
