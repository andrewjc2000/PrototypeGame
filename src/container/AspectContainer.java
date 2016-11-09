//File created by Andrew Chafos on 9/14/16 @ 1:23 PM
package container;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import main.Globals;

public class AspectContainer extends JPanel implements ActionListener {
    
    public final int width, height;
    private Aspect currentAspect;
    private boolean inUse, init;
    private final Timer aspectUpdater;
    
    public AspectContainer(Aspect startingAspect, int updateRate){//updateRate == every x milliseconds
        width = 1000;//(int)Math.round(screenSize.getWidth());
        height = 650;//(int)Math.round(screenSize.getHeight());
        Globals.contentWidth = width;
        Globals.contentHeight = 650;
        inUse = false;
        init = false;
        currentAspect = startingAspect;
        aspectUpdater = new Timer(updateRate, this);
    }
    
    public AspectContainer(Aspect startingAspect, int updateRate, int width, int height){//updateRate == every x milliseconds
        this.width = width;
        this.height = height;
        Globals.contentWidth = width;
        Globals.contentHeight = height;
        inUse = false;
        init = false;
        currentAspect = startingAspect; 
        aspectUpdater = new Timer(updateRate, this);
    }
    
    public void switchAspect(Aspect newAspect){
        aspectUpdater.stop();
        currentAspect = newAspect;
        while(!newAspect.doneLoading()){}
        if(!inUse){
            inUse = true;
        }
        aspectUpdater.start();
    }
    
    public void endAspect(){
        if(inUse){
            inUse = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentAspect.updateObjects();
        currentAspect.updateGraphics();
    }
    
    public void setup(){
        if(!init){
            init = true;
            setPreferredSize(new Dimension(width,height));
        }
    }
    
    public void start(){
        setLayout(new BorderLayout());
        add(currentAspect, BorderLayout.CENTER);
        aspectUpdater.start();
    }
    
}
