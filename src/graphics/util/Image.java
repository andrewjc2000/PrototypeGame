//File created by Andrew Chafos: 9/7/15 @ 3:15 PM
package graphics.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

public class Image implements ImageObserver {
    
    private final BufferedImage image;
    private final IMG_TYPE type;
    private final int width, height, sX, sY, eX, eY;//s = starting, e = ending
    private int posX, posY;
    private boolean highlighted;
    public boolean selected;
    private Color highlight;
    
    public int getX(){
        return posX;
    }
    
    public int getY(){
        return posY;
    }
    
    @Override
    public boolean imageUpdate(java.awt.Image img, int infoflags, int x, int y, int width, int height) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    private enum IMG_TYPE {
        ONLY_IMAGE,
        DIMENSIONS,//snipit of the image that starts from the left-hand corner
        SNIPIT //snipit means that you take a piece of an image that doesn't start at the 
               //left hand corner; it starts and ends at two points in the image.
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public boolean getHighlighted(){
        return highlighted;
    }
    
    public Image(BufferedImage img, int posX, int posY){
        image = img;
        width = img.getWidth();
        height = img.getHeight();
        type = IMG_TYPE.ONLY_IMAGE;
        
        sX = 0;
        sY = 0;
        eX = width;
        eY = height;
        this.posX = posX;
        this.posY = posY;
        this.highlighted = false;
        this.selected = false;
    }
    
    public Image(BufferedImage img, int width, int height, int posX, int posY){
        image = scale(img, width, height);
        this.width = width;
        this.height = height;
        type = IMG_TYPE.DIMENSIONS;
        
        sX = 0;
        sY = 0;
        eX = width;
        eY = height;
        
        this.posX = posX;
        this.posY = posY;
        this.highlighted = false;
        this.selected = false;
    }
    
    public Image(BufferedImage img, int startingX, int startingY, int endingX, int endingY,
            int posX, int posY
            ){
        image = img;
        type = IMG_TYPE.SNIPIT;

        sX = startingX;
        sY = startingY;
        eX = endingX;
        eY = endingY;
        
        this.width = endingX - startingX;
        this.height = endingY - startingY;
        
        this.posX = posX;
        this.posY = posY;
        this.highlighted = false;
        this.selected = false;
    }
    
    public void draw(Graphics g){
        g.drawImage(image, posX, posY, posX + width, posY + height, sX, sY, eX, eY, null);
        drawBorder(g);
        if(this.highlighted){
            g.setColor(highlight);
            g.fillRect(posX, posY, width, height);
        }
    }
    
    public void drawAtAngle(Graphics g, double angle){
        AffineTransform identity = new AffineTransform();
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform trans = new AffineTransform();
        trans.setTransform(identity);
        trans.rotate(Math.toRadians(angle), width/2, height/2); 
        g2d.drawImage(image, trans, this);
    }
    
    public void draw(Graphics g, int x, int y){
        if(x != posX){
            posX = x;
        }
        if(y != posY){
            posY = y;
        }
        if(image != null){
            g.drawImage(image, x, y, x + width, y + height, sX, sY, eX, eY, null);
        }
        drawBorder(g);
        if(this.highlighted || this.selected){
            g.setColor(highlight);
            g.fillRect(x, y, width, height);
        }
    }
    
    public void drawBorder(Graphics g){
        g.setColor(Color.black);
        for(int i = 0;i < 3;i++){
            g.drawRect(posX - i, posY - i, width + (i * 2), height + (i * 2));
        }
    }
    
    public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }
    
    public void highlight(Color h){
        highlight = h;
        highlighted = true;
    }
    
    public void deHighlight(){
        highlight = new Color(0, 0, 0, 0);
        highlighted = false;
    }
    
    public boolean containsCoords(int coordX, int coordY){
        return (coordX >= posX && coordX <= posX + width) &&
               (coordY >= posY && coordY <= posY + height);
    }
    
    public void changeX(int newX){
        this.posX = newX;
    }
    
    public void changeY(int newY){
        this.posY = newY;
    }
    
}
    