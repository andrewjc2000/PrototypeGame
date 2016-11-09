//File created by Andrew Chafos on 11/6/16 @ 4:55 PM

package menu;

import container.Aspect;
import container.SubAspect;
import java.awt.*;
import java.util.ArrayList;
import main.Globals;

public class MainMenu extends Aspect {
    
    public MainMenu(ArrayList<SubAspect> subAspects) {
        super(subAspects);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.fillRect(0,0, Globals.contentWidth, Globals.contentHeight);
    }
    
    protected void updateObjects(){
        super.updateObjects();
    }
    
}
