package Composition_Screen;

import java.awt.*;
import javax.swing.*;


public class Main extends JFrame{
    CS screen;
    
    public Main()
    {
        screen = new CS( 36, 36);
        this.add(screen);
        
        setTitle( "Test Screen");
        setBounds( 300, 500, 500, 500);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        
        screen.repaintAll();
        setVisible(true);   
    }
    public static void main(String[] args)
    {
        JFrame f = new Main();
    }
}
