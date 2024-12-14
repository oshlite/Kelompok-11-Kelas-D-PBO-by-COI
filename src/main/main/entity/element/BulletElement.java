package main.entity.element;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import main.constants.UrlConstants;
import main.utils.ImageUtils;


public class BulletElement extends UnitGameObject {
  
  public BulletElement(int x, int y, int width, int height)
  {
    super(ImageUtils.generateImageFromUrl(UrlConstants.BULLET), x, y, width, height);
  }
  @Override
    public void draw(Graphics g, ImageObserver container) {
        // Implementasi spesifik BulletElement
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
     @Override
    public void drawBig(Graphics g, ImageObserver container) {
        g.drawImage(this.getImage(), this.getX() - 15, this.getY() - 15, this.getWidth() + 30, this.getHeight() + 30, container);
    }
}



