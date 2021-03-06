package  Composition_Screen.CS_Elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import java.util.Timer;
import java.util.TimerTask;

import player.*;

public class MTimeline extends JPanel
{
    Color color;
    int width;
    
    int xShift;
    int xScale;
    Channels channels;
    
    MTimeline dis;
    TimeButton timeButton;
    
    MCanvas canvas;
    
    TimerTask timePlayer;  
    Timer timer;
    long period;
    MBar mBar;
    public MTimeline(int xScale, Channels channels)
    {
        setLayout(null);
        color = new Color(0, 200, 100);
        this.setBackground( color);
        this.xScale = xScale;
        this.channels = channels;
        
        canvas = this.canvas;
        
        timeButton = new TimeButton();        
        this.add(timeButton);
        click c = new click();
        this.addMouseListener(c);
        this.addMouseMotionListener(c);      
        
        
        dis = this;        
    }
    public void setMBar(MBar a)
    {
       mBar = a;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground( color);
        width = this.getSize().width;
        g.setColor( new Color(100,100,100));
        
        for ( int i = 1; i*xScale + xShift < width; i++)
            g.fillRect( xShift + i*xScale, 20, 2, 10);
        
        g.setColor( new Color(0,0,0));
        
        for ( int i = 0; i * xScale * 4 + xShift <width; i++)
        {
            g.fillRect( xShift + (i+1)*xScale*4, 10, 2, 20);
            g.drawString(Integer.toString(i + 1), xShift + i*xScale*4 + xScale/2 - 3*(Integer.toString(i + 1).length()), 15);
        } 
        
        timeButton.resetBounds();
    } 
    
    public void setXShift( int xShift)
    {
        this.xShift = xShift;
    }
    
    public void setXScale( int xShift)
    {
        this.xScale = xScale;
    }
    
    public void play(){
        timePlayer = new TimerTask() {

            public void run() {
                timeButton.setTime(timeButton.time + 1);
                //System.out.println(period);
            }
        };
        timer = new Timer("Play");        
        
                //System.out.println(period);
        timeButton.time--;
        timer.scheduleAtFixedRate(timePlayer, (long) 1, period/1000L);
    }
    
    public void stop()
    {
        timer.cancel();
    }
    
    public int getTime()
    {
        return timeButton.time;
    }
    public class TimeButton extends JButton
    {
        int time;
        int tempo = 60;
        
        TimeButton tb;
        boolean selected;
        
        int xSize;
        int ySize;
        
        public TimeButton()
        {
            this.time = 0;
            this.selected = false;
            
            xSize = 24;
            ySize = 20;
            this.setBounds( xShift + Math.round(time) * xScale - xSize/2, 10, xSize, ySize);
            //this.setVisible(false);
            this.addMouseListener(new clickButton());
            this.addMouseMotionListener(new clickButton());
            /*
             this.setOpaque(false);
             this.setContentAreaFilled(false);
             this.setBorderPainted(false);*/
            this.tb = this;
        }
        
        private class clickButton implements MouseListener, MouseMotionListener
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    
                   // System.out.println(tb.getBounds());
                    selected = true;
                    //tb.repaint();                    
                } 
            }
            
            @Override
            public void mouseReleased(MouseEvent e)
            {
               //tb.repaint(); 
               channels.getPlayer().updatePlayer( time);
               tb.resetBounds();
               selected = false;
            }
            
            
            @Override
            public void mouseDragged(MouseEvent e)
            {
                if (selected)
                {
                    setTime( (-xShift + tb.getBounds().x + e.getX() + xSize/2)/xScale);                    
                    tb.tempBounds(e.getX());
                    //dis.repaint();
                }
            }
            
            public void mouseMoved(MouseEvent event) { }
            public void mouseEntered(MouseEvent event) { }
            public void mouseExited(MouseEvent event) { }
            public void mouseClicked(MouseEvent event) { }
            
        }
        public void setTime( int t)
        {
           time = t;
           System.out.println("T: " + t + "Last index: " + channels.getLastIndex() );
           if (t > channels.getLastIndex() )
           {
              channels.getPlayer().setStartSwitch(true);
              mBar.setIsPlaying(false);
              mBar.setIsFirst(true);
              mBar.setStatus("Play");
              timeButton.time = 0;
              stop();
              //channels.getPlayer().setUpPlayer();
              
           }
           
           channels.getPlayer().updatePlayer( time);
           resetBounds();
        }
        
        public void resetBounds()
        {
            tb.setBounds( xShift + time*xScale - xSize/2, 10, xSize, ySize);
            //canvas.setTime(time*xScale - xSize/2);
           // System.out.println(tb.getBounds());
        }
        
        public void tempBounds(int X)
        {
            tb.setBounds( xShift + tb.getBounds().x + X - xSize/2, 10, xSize, ySize);
            //canvas.setTime( -xShift + tb.getBounds().x + X - xSize/2);
        }
    }
    
    public class click implements MouseListener, MouseMotionListener
    {
        boolean left;
        
        @Override
        public void mousePressed(MouseEvent e)
        {            
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                left = true;
                timeButton.setTime( (-xShift + e.getX() + timeButton.xSize/2)/xScale ); 
                timeButton.resetBounds();
            }
        }
        
        public void mouseReleased(MouseEvent e)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                left = false;
            }
        }
        
        @Override
        public void mouseDragged(MouseEvent e)
        {
            /*
            if (left)
            {
                timeButton.setTime( (-xShift + e.getX() + timeButton.xSize/2)/xScale ); 
                timeButton.tempBounds(e.getX());
            }
            */
        }
        
        public void mouseMoved(MouseEvent event) {  }        
        public void mouseClicked(MouseEvent event) {  }        
        public void mouseEntered(MouseEvent event) {  }
        public void mouseExited(MouseEvent event) {  }
    }
    
    public void setPeriod( float p)
    {
       period = (long) p;
    }
}
