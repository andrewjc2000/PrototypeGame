//File created by Andrew Chafos on 9/14/16 @ 1:23 PM

package container;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Aspect extends JComponent {
    
    protected boolean doneLoadingBool;
    protected ArrayList<container.SubAspect> subs;
    
    public boolean doneLoading(){
        return doneLoadingBool;
    }
    
    public Aspect(ArrayList<container.SubAspect> subAspects){
        doneLoadingBool = true;
        subs = subAspects;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        subs.stream().forEach((s) -> {
            if(s.active){
                s.updateGraphics(g);
            }
        });
    }
    
    protected void updateObjects(){
        subs.stream().forEach((s) -> {
            if(s.active){
                s.updateObjects();
            }
        });
    }
    
    protected void updateGraphics(){
        repaint();
    }
    
}
