//File created by Andrew Chafos: 8/27/15 @ 4:46 PM
package graphics.util;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//This class is a rectangle which gives information
//so that another class can use it to do something,
//however it doesn't do anything but change & display itself
public class Button extends Component implements MouseListener, MouseMotionListener {
    
    private final int x, y, width, height, margin;
    private Color bgColor, textColor;
    private Image background;
    private ArrayList<String> text;
    private Font font;
    private final boolean usingText;
    private final Cursor def, hand;
    //private final boolean hasBorder;
    //private boolean usingBorder;
    //private final Border border;
    
    //I thought that a simple "get" method would be more efficient for 
    //several attributes of the same type which need to be accessed.
    public int get(String attribute){
        switch(attribute){
            case "x":
                return x;
            case "y":
                return y;
            case "width":
                return width;
            case "height":
                return height;
            default:
                throw new RuntimeException("Attribute '" + attribute + "' is not a member of this class.");
        }//end of switch
    }//end of method
    
    /*public boolean getBool(String attribute){
        switch(attribute){
            case "hasBorder":
                return hasBorder;
            case "usingBorder":
                return usingBorder;
            default:
                throw new RuntimeException("Attribute '" + attribute + "' is not a member of this class.");
        }
    }*/
    
    
    //FIGURE OUT TEXT SCALING
    //DEFINED WIDTH: take in text size as input, change height based on the space the text takes up
    //WE'RE ONLY DOING THE ABOVE BECAUSE THE OTHER ONE IS TOO HARD
    
    
    //set margin == 0 if no vertical margins
    public Button(int x, int y, int width, int textSize, Color buttonColor, ArrayList<String> text, String font, Color textColor, int margin){
        super(buttonColor);
        this.x = x;
        this.y = y;
        this.height = text.size() * textSize;
        this.width = width;
        this.text = text;
        this.font = new Font(font, Font.BOLD, textSize);
        bgColor = buttonColor;
        this.textColor = textColor;
        usingText = true;
        this.margin = margin;
        def = new Cursor(Cursor.DEFAULT_CURSOR);
        hand = new Cursor(Cursor.HAND_CURSOR);
        //usingBorder = false;
        //hasBorder = false;
    }
    
    public Button(int x, int y, int width, int textSize, BufferedImage background, ArrayList<String> text, String font, Color textColor, int margin){
        super(null);
        this.x = x;
        this.y = y;
        this.height = text.size() * textSize;
        this.width = width;
        this.background = new Image(background, width, height, x, y);
        this.text = text;
        this.font = new Font(font, Font.BOLD, textSize);
        this.textColor = textColor;
        usingText = true;
        this.margin = margin;
        def = new Cursor(Cursor.DEFAULT_CURSOR);
        hand = new Cursor(Cursor.HAND_CURSOR);
        //usingBorder = false;
        //hasBorder = false;
    }
    
    //These constructors are for no text whatsoever
    
    public Button(int x, int y, int width, int height, Color buttonColor){
        super(buttonColor);
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        bgColor = buttonColor;
        usingText = false;
        this.margin = 0;def = new Cursor(Cursor.DEFAULT_CURSOR);
        hand = new Cursor(Cursor.HAND_CURSOR);
        
        //usingBorder = false;
        //hasBorder = false;
    }
    
    public Button(int x, int y, int width, int height, BufferedImage background){
        super(null);
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.background = new Image(background, width, height, x, y);
        usingText = false;
        this.margin = 0;
        def = new Cursor(Cursor.DEFAULT_CURSOR);
        hand = new Cursor(Cursor.HAND_CURSOR);
        //usingBorder = false;
        //hasBorder = false;
    }
    
    /*public void changeBorderUse(boolean use){
        usingBorder = use;
    }*/
    
    public void draw(Graphics g){
        if(background == null){
            g.setColor(color);
            if(margin == 0){
                g.fillRect(x, y, width, height);
            }
            else{
                g.fillRect(x, y, width, height + (margin * 2));
            }
        }
        else{
            background.draw(g);
        }
        if(usingText){
            g.setFont(font);
            g.setColor(textColor);
            for(int i = 0;i < text.size();i++){
                int strWidth = g.getFontMetrics().stringWidth(text.get(i));
                int strHeight = (height / text.size());
                g.drawString(text.get(i), x + ((width - strWidth) / 2), y + margin + ((i + 1) * strHeight) - (strHeight / 5));
            }
        }
    }
    
    //this was originially created for mouse position, but you
    //can pretty much see if any coordinate is inside the rectangle
    //based on the rectangle's attributes
    public boolean containsCoords(int coordX, int coordY){
        return (coordX >= x && coordX <= x + width) &&
               (coordY >= y && coordY <= y + height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(containsCoords(e.getX(), e.getY())){
            main.Globals.mainFrame.setCursor(hand);
        }
        else{
            main.Globals.mainFrame.setCursor(def);
        }
    }
}//end of class
