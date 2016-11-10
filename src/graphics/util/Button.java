//File created by Andrew Chafos: 8/27/15 @ 4:46 PM
package graphics.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//This class is a rectangle which gives information
//so that another class can use it to do something,
//however it doesn't do anything but change & display itself
public class Button extends Component{
    
    private final int x, y, height;
    private int width;
    private Image background;
    private final ArrayList<String> text;
    private final Font font;
    private final boolean definedWidth, usingText;
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
    //DEFINED HEIGHT: text size is (height / # of lines), width changes to fit text
    
    public Button(int x, int y, int width, int textSize, Color buttonColor, ArrayList<String> text, String font, Color textColor){
        super(buttonColor);
        this.x = x;
        this.y = y;
        this.height = text.size() * textSize;
        this.width = width;
        this.text = text;
        this.font = new Font(font, Font.BOLD, textSize);
        this.definedWidth = true;
        usingText = true;
        //usingBorder = false;
        //hasBorder = false;
    }
    
    public Button(int x, int y, int height, Color buttonColor, ArrayList<String> text, String font, Color textColor){
        super(buttonColor);
        this.x = x;
        this.y = y;
        this.height = height;
        this.text = text;
        this.font = new Font(font, Font.BOLD, (text.size() == 0) ? 0 : height / text.size());
        int largestLength = -1;
        int pos = 0;
        for(int i = 0;i < text.size();i++){
            if(text.get(i).length() > largestLength){
                largestLength = text.get(i).length();
                pos = i;
            }
        }
        this.definedWidth = false;
        usingText = true;
        //usingBorder = false;
        //hasBorder = false;
    }
    
    public Button(int x, int y, int width, int textSize, BufferedImage background, ArrayList<String> text, String font, Color textColor){
        super(null);
        this.x = x;
        this.y = y;
        this.width = width;
        this.background = new Image(background, width, height, x, y);
        this.text = text;
        usingText = true;
        //usingBorder = false;
        //hasBorder = false;
    }
    
    public Button(int x, int y, int height, BufferedImage background, ArrayList<String> text, String font, Color textColor){
        super(null);
        this.x = x;
        this.y = y;
        this.height = height;
        this.background = new Image(background, width, height, x, y);
        this.text = text;
        usingText = true;
        //usingBorder = false;
        //hasBorder = false;
    }
    
    /*public void changeBorderUse(boolean use){
        usingBorder = use;
    }*/
    
    public void draw(Graphics g){
        int width = g.getFontMetrics().stringWidth(text.get(pos));
        if(background == null){
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
        else{
            background.draw(g);
        }
    }
    
    //this was originially created for mouse position, but you
    //can pretty much see if any coordinate is inside the rectangle
    //based on the rectangle's attributes
    public boolean containsCoords(int coordX, int coordY){
        return (coordX >= x && coordX <= x + width) &&
               (coordY >= y && coordY <= y + height);
    }
}//end of class
