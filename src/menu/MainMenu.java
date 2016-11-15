//File created by Andrew Chafos on 11/6/16 @ 4:55 PM

package menu;

import container.Aspect;
import container.SubAspect;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.Globals;
import main.Launcher;

public class MainMenu extends Aspect {
    
    graphics.util.Button startButton, quitButton;
    BufferedImage mahpoint;
    
    public MainMenu(ArrayList<SubAspect> subAspects) {
        super(subAspects);
        try{
        URL resource1 = main.Launcher.class.getClassLoader()
                .getResource("main/resources/mahpoint.jpg");
        mahpoint = ImageIO.read(resource1);
        }
        catch(IOException e){System.out.println("nsjkfnsjkanf");}
        startButton = new graphics.util.Button(450, 100, 100, 100, mahpoint);
        quitButton = new graphics.util.Button(450, 300, 100, 100, Color.red);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.fillRect(0,0, Globals.contentWidth, Globals.contentHeight);
        startButton.draw(g);
        quitButton.draw(g);
    }
    
    protected void updateObjects(){
        super.updateObjects();
    }
    
}
