package  Composition_Screen.CS_Elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MPiano extends JPanel
{
 Color color;
 
 int xScale;
 
 int yShift;
 int yScale;
 
 int height;
 public MPiano( int xScale, int yScale)
 {
  color = new Color(255, 255, 255);
  this.setBackground( color);
  
  this.xScale = xScale;
  
  this.yShift = 0;
  this.yScale = yScale;
 }
 
 @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);    
        this.setBackground( color);
        this.height = this.getSize().height;
        
        g.setColor( new Color( 0, 0, 0));

        int b = 7 * yScale / 12;
        for ( int i = 0; i*b + yShift < height; i++)
        {
         g.fillRect( 0, yShift + i*yScale - 1 , 125, 2);
         
        }
        
        
        for ( int i = 0; i* 7 * xScale/12 + yShift < height; i++)
        {
           g.drawString(Integer.toString(10 - i), 80, yShift + i * yScale * 7 - 5 ); //
           
           g.fillRect( 0, yShift + 1*b + i * yScale * 7, 70, b);
           g.fillRect( 0, yShift + 3*b + i * yScale * 7, 70, b);
           g.fillRect( 0, yShift + 5*b + i * yScale * 7, 70, b);
           
           g.fillRect( 0, yShift + 8*b + i * yScale * 7, 70, b);
           g.fillRect( 0, yShift + 10*b + i * yScale * 7, 70, b);
           
        }      
        g.setColor(new Color(50,50,200));
        g.fillRect( 0, 0, 8, height);
 }
 
 public void setYShift( int yShift)
 {
  this.yShift = yShift;
 }
 
 public void setYScale( int yScale)
 {
  this.yScale = yScale;
 }
}
