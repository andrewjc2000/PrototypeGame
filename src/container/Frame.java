//File created by Andrew Chafos on 9/14/16 @ 1:23 PM

package container;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Frame extends JFrame {
    
    private final int contentWidth, contentHeight;
    private final String title;
    private boolean init;
    private final AspectContainer contain;
    
    public Frame(String title, Aspect startingAspect){
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.title = title;
        init = false;
        contain = new AspectContainer(startingAspect,5);//used to be new MainMenu() as paramter
        contentWidth = contain.width;
        contentHeight = contain.height;
    }
    
    public void setup(){
        if(!init){
            init = true;
            setTitle(title);
            setMinimumSize(new Dimension(1000, 650));
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            contain.setup();
            getContentPane().add(contain);
            pack();
        }
    }
    
    public void start(){
        contain.start();
        setVisible(true);
    }
    
    
}
