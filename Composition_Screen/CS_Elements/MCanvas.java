package  Composition_Screen.CS_Elements;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import java.util.ArrayList;

import player.*;
import GUI_STUFF.*;


/**
 * 
 * Music Composer
 * AutoTester class
 * 
 * @author Ali Kasim Cetinkaya
 * 21501293 
 * @version v0.1 
 * 12.05.2019
 * 7:00am
 */ 

public class MCanvas extends JPanel
{
    // PROPERTIES
    
    // Pathway to midi manager
    Channels channels;
    
    // graphics color
    Color color;
    
    // Bits and pieces use these
    int height;
    int width;   
    int xShift;
    int yShift;    
    int xScale;
    int yScale;
    
    // GUI Note Storage
    ArrayList<NoteButton> notes;
    NoteButton newNote;
    
    // Temporary values
    int x, y;    
    
    // Left / Right clickers
    boolean left, right;
    
    // Keep a refence of itself due to all the many innerclasses
    MCanvas canvas;    
    
    // 
    int time;
    
    // LeftPanel Refence   
    LeftPanel leftPanel;
    // Default velocity (loudness) set by left panel
    int velocity;   
    
    // Integer representation of scale. <12 minor, >12 major (made up for convenience)
    int musicScale = 0;
    
    // CONSTRUCTOR
    public MCanvas( int xScale, int yScale, Channels channels)
    {
        color = new Color(250, 250, 250);
        this.setBackground( color );
        this.xScale = xScale;
        this.yScale = yScale;
        this.channels = channels;        
        
        notes = new ArrayList<NoteButton>();
        
        click c = new click();
        addMouseListener(c);
        addMouseMotionListener(c);      
        
        velocity = 100;
        
        canvas = this; // Too many innerclasses that need reference
    }
    
    /*
     * Prints all the visual in the canvas
     * @param g
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);     
        this.setBackground( color);
        height = this.getSize().height;
        width = this.getSize().width;
        
        // Horizontal Lines
        g.setColor( new Color( 230, 230, 230));
        
        int b = (7*yScale)/12;
        for ( int i = 0; i*xScale + yShift < height; i++)
        {
            g.fillRect( 0, yShift + i*yScale + 1 , width, 2);            
        }
        
        // Horizontal Black Keys
        for ( int i = 0; i* (7 * yScale/12) + yShift < height; i++)
        {
            g.fillRect( 0, yShift + (1*7*yScale)/12 + i * yScale * 7, width, b);
            g.fillRect( 0, yShift + (3*7*yScale)/12 + i * yScale * 7, width, b);
            g.fillRect( 0, yShift + (5*7*yScale)/12 + i * yScale * 7, width, b);
            
            g.fillRect( 0, yShift + (8*7*yScale)/12 + i * yScale * 7, width, b);
            g.fillRect( 0, yShift + (10*7*yScale)/12 + i * yScale * 7, width, b);
            
        }  
        
        // Vertical Line Grey
        g.setColor( new Color(100,100,100));
        for ( int i = 1; i * xScale + xShift < width; i++)
            g.fillRect( xShift + i*xScale, 0, 2, height);
        
        // Vertical Line Dark
        g.setColor( new Color(0,0,0));
        for ( int i = 1; i * xScale * 4 + xShift < width; i++)
        {
            g.fillRect( xShift + i*xScale*4, 0, 2, height);
        } 
        
        // Newly placed button draw
        g.setColor( new Color(50,50,250));
        if (newNote != null)
        {
            g.fillRect( xShift + xScale * newNote.start, yShift + (yScale * newNote.note * 7 )/ 12, xScale * newNote.length, 7*yScale/12);
            g.setColor( new Color(0,0,10));
            g.drawRect( xShift + xScale * newNote.start, yShift + (yScale * newNote.note * 7 )/ 12, xScale * newNote.length, 7*yScale/12);
            g.drawRect( xShift + xScale * newNote.start + 1, yShift + (yScale * newNote.note * 7 )/ 12 + 1, xScale * newNote.length - 2, 7*yScale/12 - 2);
        } 
        
        // Musical Scales (Guidence)
        if (musicScale != 0)
        {
            // Minor Scale
            if (musicScale <= 12)
            {
                g.setColor( new Color(100,100,200));
                for ( int i = 1 - musicScale; i* (7 * yScale/12) + yShift < height; i++)
                {
                    g.fillRect( 0, yShift + ((musicScale + 1) * 7 * yScale)/12 + i * yScale * 7, width, b);
                    g.fillRect( 0, yShift + ((musicScale + 3) * 7 * yScale)/12 + i * yScale * 7, width, b);
                    g.fillRect( 0, yShift + ((musicScale + 5) * 7 * yScale)/12 + i * yScale * 7, width, b);
                    
                    g.fillRect( 0, yShift + ((musicScale + 8) * 7 * yScale)/12 + i * yScale * 7, width, b);
                    g.fillRect( 0, yShift + ((musicScale + 10) * 7 * yScale)/12 + i * yScale * 7, width, b);
                }
            }
            // Major Scale
            if (musicScale > 12)
            {
                g.setColor( new Color(100,100,200));
                for ( int i = 1 - musicScale; i* (7 * yScale/12) + yShift < height; i++)
                {
                    g.fillRect( 0, yShift + ((musicScale + 1) * 7 * yScale)/12 + i * yScale * 7, width, b);
                    g.fillRect( 0, yShift + ((musicScale + 4) * 7 * yScale)/12 + i * yScale * 7, width, b);
                    g.fillRect( 0, yShift + ((musicScale + 6) * 7 * yScale)/12 + i * yScale * 7, width, b);
                    
                    g.fillRect( 0, yShift + ((musicScale + 9) * 7 * yScale)/12 + i * yScale * 7, width, b);
                    g.fillRect( 0, yShift + ((musicScale + 11) * 7 * yScale)/12 + i * yScale * 7, width, b);
                }
            }
        }
        
        // Draw stored notes in color
        for ( int i = 0; i < notes.size(); i++)
        {
            if (notes.get(i).selected)
                g.setColor( new Color(0,250,0));
            else
                g.setColor( new Color(250*notes.get(i).velocity/100,0,250*(100- notes.get(i).velocity)/200));
            if (notes.get(i).resizing)
                g.setColor( new Color(250,250,100));
            
            g.fillRect( xShift + xScale * notes.get(i).start, yShift + (yScale * notes.get(i).note * 7) / 12, xScale * notes.get(i).length, 7*yScale/12);
            g.setColor( new Color(0,0,10));
            g.drawRect( xShift + xScale * notes.get(i).start, yShift + (yScale * notes.get(i).note * 7) / 12 , xScale * notes.get(i).length , 7*yScale/12 );            
            g.drawRect( xShift + xScale * notes.get(i).start + 1, yShift + (yScale * notes.get(i).note * 7) / 12 + 1, xScale * notes.get(i).length - 2, 7*yScale/12 - 2);
        }
        
        // Resize the Buttons
        for(int i = 0; i < notes.size(); i++)
        {
            notes.get(i).resetBounds();
        }
        
        g.setColor( new Color(255,100,100));
        g.drawRect( xShift + time, 0, 2, height); 
        
    }
    
    // SETTERS
    public void setXShift( int xShift)
    {
        this.xShift = xShift;
    }
    
    public void setYShift( int yShift)
    {
        this.yShift = yShift;
    }
    
    public void setXScale( int xShift)
    {
        this.xScale = xScale;
    }
    
    public void setYScale( int yScale)
    {
        this.yScale = yScale;
    }
    
    /*
     * Deniz's code, he uses this to pull his note data into the gui
     * @param noteArray
     */
    public void setNoteData(ArrayList<Note> noteArray )
    {
        // Remove current notes from GUI
        canvas.removeAll();
        notes.removeAll(notes);      
        
        // New NoteButtons depending on import size
        for(int i = 0; i < noteArray.size(); i++)
        {
            notes.add( new NoteButton(0,0,0,0) );
        }
        
        // Set imported notes to NoteButtons 
        for(int i = 0; i < noteArray.size(); i++)
        {
            notes.get(i).note = 118 - noteArray.get(i).getKey();
            notes.get(i).start = noteArray.get(i).getTickInit();
            notes.get(i).length = (noteArray.get(i).getTickEnd() - noteArray.get(i).getTickInit());
            notes.get(i).velocity = noteArray.get(i).getVelocity();
            notes.get(i).resetBounds();
            add(notes.get(i));        
        }
        repaint();
    }
    
    /* 
     * This is a Button that stores all note properties.
     * It is invisible but clickable, the note it represents is drawn by the canvas instead
     */
    public class NoteButton extends JButton
    {
        // Note Properties
        int note;
        int start;
        int length;
        int velocity;
        
        // Mouse Operations
        boolean selected;
        boolean resizing;      
        
        // For resets in case incorrect operations
        int oldNote;
        int oldStart;
        int oldLength;      
        
        // Reference for inner classes
        NoteButton dis;
        
        // CONSTRUCTOR
        public NoteButton( int note, int start, int length, int velocity)
        {
            this.note = note;
            this.start = start;
            this.length = length;    
            
            oldLength = length;
            oldStart = start;
            oldNote = note;
            
            this.velocity = velocity;
            this.selected = false;
            this.resizing = false;
            this.setBounds( xShift + start * xScale, yShift + 7*note*yScale/12, xScale * length, 7*yScale/12);
            //this.setVisible(false);
            this.addMouseListener(new clickButton());
            this.addMouseMotionListener(new clickButton());
            
            this.setOpaque(false);
            this.setContentAreaFilled(false);
            this.setBorderPainted(false);
            this.dis = this;
        }
        
        // Listener
        private class clickButton implements MouseListener, MouseMotionListener
        {
            
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    if ( e.getX() > length * xScale - xScale * 3 / 5)
                    {
                        dis.resizing = true;
                        oldLength = length;
                    }
                    
                    oldStart = start;
                    oldNote = note;
                    
                    for ( int i = 0; i < notes.size(); i++)
                    {
                        notes.get(i).selected = false;
                    }      
                    
                    selected = true;                             
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (dis.getBounds().x + e.getX() >= 0 && dis.getBounds().y + e.getY() >= 0 && dis.getBounds().x + e.getX() < canvas.getWidth() && dis.getBounds().y + e.getY() < canvas.getHeight())
                {   
                    if (!isBlocked(dis) && !dis.resizing)
                    {
                       setBounds( start*xScale, 7*note*yScale/12, xScale * length, 7*yScale/12);   
                       channels.changeKey(new Note(channels.getChannel(), 118 - oldNote, velocity, oldStart, oldStart + length ), 118 - note);      
                       if(oldStart != start)
                       {
                          channels.changeDuration(new Note(channels.getChannel(), 118 - note, velocity, oldStart, oldStart + length ), start, start+ length );
                          channels.getPlayer().setChangedKeyLast(true);
                          channels.getPlayer().setChangedDurationLast(true);
                          channels.getPlayer().setAddedLast(false);
                          channels.getPlayer().setDeletedLast(false);
                       }                    
                    }                
                    else
                    {
                        start = oldStart;
                        note = oldNote;  
                    }
                    
                    if (!isBlocked(dis) && resizing)
                    {                                                    
                        channels.changeDuration(new Note(channels.getChannel(), 118 - note, velocity, oldStart, oldStart + oldLength ), start, start+ length );   
                        oldNote = length; 
                    }
                }
                else
                {
                    start = oldStart;
                    note = oldNote;
                    length = oldLength;
                }
                
                resizing = false;
                canvas.repaint();               
                
                if (selected)
                {
                    leftPanel.getSlider2().setValue(velocity);
                }
            }
            
            public void mouseDragged(MouseEvent e)
            {
                if (selected && !resizing)
                {
                    start = (-xShift + e.getX() + getBounds().x)/xScale;
                    note = (12 * (-yShift + e.getY() + getBounds().y)/yScale)/7;               
                    canvas.repaint();
                }
                else if (resizing)
                {
                    length = (e.getX())/xScale;
                    if (length <= 0)
                        length = 1;
                    
                    canvas.repaint();
                }
            }
            
            public void mouseMoved(MouseEvent event) { }         
            public void mouseEntered(MouseEvent event) { }         
            public void mouseExited(MouseEvent event) { }
            public void mouseClicked(MouseEvent e) { }     
            
            public String toString()
            {
                return "Key: " + note + " - Duration: " + start + "-" + start + length + " - Velocity:" + velocity + "///";
            }
        } 
        
        // Automatically resize Buttons on notes
        public void resetBounds()
        {
            this.setBounds( xShift + start * xScale, yShift + 7*note*yScale/12, xScale * length, 7*yScale/12);
        }
        
    }
    // Listener
    public class click implements MouseListener, MouseMotionListener
    {
        @Override
        public void mousePressed(MouseEvent e)
        {            
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                left = true;
                x = (-xShift + e.getX())/xScale;
                y = (12 * (-yShift + e.getY())/yScale)/7;
                newNote = new NoteButton( y, x, 1, 100);
            }
            
            if (e.getButton() == MouseEvent.BUTTON3)
            {
                for (int i = 0; i < notes.size(); i++)
                {
                    notes.get(i).selected = false;
                }
            }            
            repaint();
        }
        
        @Override
        public void mouseReleased(MouseEvent e)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                if (!isBlocked(newNote))
                {
                    if(newNote.note >= 0)
                    {
                        NoteButton note = new NoteButton( y, x, newNote.length, velocity);
                        add(note);                    
                        notes.add(note);                    
                        channels.addNotes( 118 -  y, note.velocity, x, x + note.length );
                    }
                }
                
                newNote = null;
                left = false;
            }        
            
            repaint();
        }
        
        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (left)
            {
                if ((-xShift + e.getX())/xScale - x + 1 > 1)
                {
                    newNote.length = (-xShift + e.getX())/xScale - x + 1;
                }
                else
                {
                    newNote.length = 1;
                }
            }
            repaint();
            
        }
        
        public void mouseMoved(MouseEvent event) { }
        public void mouseClicked(MouseEvent event) { }
        public void mouseEntered(MouseEvent event) { }
        public void mouseExited(MouseEvent event) { }
    }
    
    /*
     * Check is a different note is obstructing
     * @param n note
     */
    public boolean isBlocked(NoteButton n)
    {
        boolean blocked = false;
        if (n.note > 118 || n.start < 0)
            return true;
        
        for (int i = 0; i < notes.size(); i++)
        {
            if (  n.start + n.length > notes.get(i).start && n.start < notes.get(i).start + notes.get(i).length)
            {
                if ( n != notes.get(i) && n.note == notes.get(i).note)
                {
                    blocked = true;
                }
            }
        }
        return blocked;
    }    
    
    
    // OTHER SETTERS
    
    // Activates when a note change is made outside of the composition screen
    public void setChannel(Channels c)
    {
        this.channels = c;
        setNoteData( c.setNoteData() );
    } 
    
    public void setTime(int t)
    {
        this.time = t;
    }   
    
    public void setDefaultVelocity(int x)
    {
        velocity = x;
    }
    
    public void setLeftPanel(LeftPanel x)
    {
        leftPanel = x;
    }
    
    public void setMusicScale(int x)
    {
       musicScale = x;
    }
    
    // GETTERS
    
    public ArrayList<NoteButton> getNotesArray()
    {
        return notes;
    }
    
    public int getNoteSize()
    {
        return notes.size();
    }
    
    // Get selected note in canvas
    public Note getSelected()
    {
        for ( int i = 0; i < notes.size(); i++)
        {
            if (notes.get(i).selected)
            {
                return (new Note( channels.getChannel(), 118 - notes.get(i).note, notes.get(i).velocity, notes.get(i).start, notes.get(i).start + notes.get(i).length ));
            }
        }
        return null;
    }
}



