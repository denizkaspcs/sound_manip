package Composition_Screen.CS_Elements; 

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import player.*;

public class MBar extends JPanel 
    {
        PlayButton play;
        Channels channels;
        boolean isPlaying;
        boolean isFirst;
        
        MTimeline timeline;
        
        public MBar(Channels channels, MTimeline timeline)
        {
            this.channels = channels;
            this.timeline = timeline;
            this.setLayout( null);
            this.setBackground( new Color(50, 50, 50) );
            play = new PlayButton();           
            this.add(play);
            play.setBounds( 0, 0, 100, 30);
            
            isPlaying = false;
            isFirst = true;
        }
        
        private class PlayButton extends JButton
        {
            
            public PlayButton()
            {
                isPlaying = false;
                this.addMouseListener(new click());                  
            }
            
            private class click implements MouseListener
            {
                public void mousePressed(MouseEvent e)
                {
                   
                }
                public void mouseReleased(MouseEvent e)
                {
                   if (isFirst)
                   {
                      channels.getPlayer().startPlaying();
                      timeline.play();
                      isPlaying = true;
                      isFirst = false;
                   }
                   else if (isPlaying)
                   {
                      channels.getPlayer().stopPlaying();
                      timeline.stop();
                      isPlaying = false;
                      isFirst = false;
                   }
                   else if(!isPlaying)
                   {
                      channels.getPlayer().resumePlaying();
                      timeline.play();
                      isPlaying = true;
                      isFirst = false;
                   }
                }
                public void mouseEntered(MouseEvent e) { }
                public void mouseExited(MouseEvent e) { }  
                public void mouseClicked(MouseEvent e) { }
                
            }
        }
        
        public class MusicScale extends JComboBox
        {
            public MusicScale()
            {
                String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
                JComboBox petList = new JComboBox(petStrings);
                petList.setSelectedIndex(4);
                petList.addActionListener(this);
            }
            
            public class Select implements ActionListener
            {
                public void actionPerformed( ActionEvent e)
                {
                    JComboBox cb = (JComboBox)e.getSource();
                    String petName = (String)cb.getSelectedItem();
                   // updateLabel(petName);
                }
            }
        }
    }