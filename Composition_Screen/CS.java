package Composition_Screen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import Composition_Screen.CS_Elements.*;
import player.*;


public class CS extends JPanel
{
   Channels channels;
   JLabel name;
   int channel;
   
   JPanel north;
   JPanel center;
   JPanel south;
   
   // scroll
   
   JScrollBar xBar; JScrollBar yBar;
   
   MCanvas canvas;
   MTimeline time;
   MPiano piano;
   MBar mBar;
   
   
   int width;
   int height;
   
   int xShift;
   int yShift;
   
   int xScale;
   int yScale;
   
   public CS( int xScale, int yScale, Channels channels)
   {  
      this.setLayout(new BorderLayout());
      this.xScale = xScale;
      this.yScale = yScale;
      this.channels = channels;
      time = new MTimeline(xScale, channels);  
      name = new JLabel("N2");
      name.setFont(new Font(name.getFont().getName(), Font.PLAIN, 20));
      name.setPreferredSize(new Dimension(125, 30));
      JLabel block = new JLabel("");
      block.setPreferredSize(new Dimension(18, 30));
      
      north = new JPanel(new BorderLayout());  
      north.add(name, BorderLayout.WEST);
      north.add(time, BorderLayout.CENTER);
      north.add(block, BorderLayout.EAST);
      north.setPreferredSize(new Dimension(0,30));  
      
      
      
      center = new JPanel(new BorderLayout());
      yShift = -(7*yScale*127)/24;
      yBar = new JScrollBar(JScrollBar.VERTICAL, -yShift, 1, (7*yScale*4)/12, 7*(yScale*(127-11-14))/12);
      yBar.addAdjustmentListener( new AdjustmentListener(){
         public void adjustmentValueChanged(AdjustmentEvent e)
         {
            height = getSize().height;
            yShift = - yBar.getValue();
            canvas.setYShift(yShift);
            canvas.repaint();
            piano.setYShift(yShift);
            piano.repaint();
         }
      });
      canvas = new MCanvas( xScale, yScale, channels);
      piano = new MPiano( xScale, yScale);
      piano.setPreferredSize(new Dimension(125,0));
      piano.setYShift(yShift);
      piano.repaint();
      center.add(yBar, BorderLayout.EAST);
      center.add(canvas, BorderLayout.CENTER);
      center.add(piano, BorderLayout.WEST);
      
      
      JLabel block1 = new JLabel("");
      block1.setPreferredSize(new Dimension(125, 20));
      JLabel block2 = new JLabel("");
      block2.setPreferredSize(new Dimension(18, 20)); 
      xBar = new JScrollBar(JScrollBar.HORIZONTAL);
      xBar.addAdjustmentListener( new AdjustmentListener(){
         public void adjustmentValueChanged(AdjustmentEvent e)
         {
            width = getSize().width;
            xShift = - xBar.getValue() * (width - 200) / 100;
            
            time.setXShift(xShift);
            time.repaint();
            canvas.setXShift(xShift);
            canvas.repaint();
         }
      });  
      
      
      xBar.setPreferredSize(new Dimension(0, 20));
      south = new JPanel(new BorderLayout()); 
      
      JPanel y = new JPanel(new BorderLayout());          
      y.add(block1, BorderLayout.WEST);
      y.add(block2, BorderLayout.EAST);
      y.add(xBar, BorderLayout.CENTER); 
      
      mBar = new MBar( channels, time, this);
      mBar.setPreferredSize(new Dimension(0, 30) );
      time.setMBar(mBar);
      
      south.add(y, BorderLayout.NORTH);
      south.add(mBar, BorderLayout.SOUTH);
      south.setPreferredSize(new Dimension(0, 50));
      
      
      
      this.add(north, BorderLayout.NORTH); 
      this.add(center, BorderLayout.CENTER);
      this.add(south, BorderLayout.SOUTH);  
      
      this.addComponentListener(new ComponentAdapter() {
         public void componentResized(ComponentEvent componentEvent) {
            
         }
      });
      
      canvas.setYShift(yShift);
      canvas.repaint();
   }
   
   
   public void setXScale( int xScale)
   {
      this.xScale = xScale;
      
      canvas.setXScale(xScale);
      time.setXScale(xScale);
      
   }
   public void setYScale( int yScale)
   {
      this.yScale = yScale;
      
      canvas.setYScale(yScale);
      piano.setYScale(yScale);
   }
   
   public void repaintAll()
   {
      canvas.repaint();
      piano.repaint();
      time.repaint();
   }
   
   public void setName(String name)
   {
      this.name.setText(name);
   }
   
   public void setPeriod()
   {
      time.setPeriod(channels.getTempo() );
   }
   
   public void setMusicScale(int x)
   {
      canvas.setMusicScale(x);
   }
   
   public void setChannel(Channels c)
   {
      channels = c;
      canvas.setChannel(c);
   }
   
   public int getChannelNumber()
   {
      return channel;
   }
   
   public Channels getChannel()
   {
      return channels;
   }
   
   public int getNoteSize()
   {
      return canvas.getNoteSize();
   }
   
   public int getTime()
   {
       return time.getTime();
   }
   
   public Note getSelected()
   {
      return canvas.getSelected();
   }
   
   public MCanvas getCanvas()
   {
      return canvas;
   }
   
   public void setDefaultVelocity(int x)
   {
      canvas.setDefaultVelocity(x);
   }
   
}
