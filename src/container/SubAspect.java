//File created by Andrew Chafos on 11/6/16 @ ~4:45 PM

package container;

import javax.swing.*;
import java.awt.*;

public abstract class SubAspect {
    
    private boolean doneLoadingBool;
    public boolean active;
    
    public boolean doneLoading(){
        return doneLoadingBool;
    }
    
    public SubAspect(){
        active = false;
        doneLoadingBool = true;
    }
    
    protected void updateObjects(){
    
    }
    
    protected void updateGraphics(Graphics g){
    }
    
}
