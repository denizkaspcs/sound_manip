package Composition_Screen.CS_Elements; 

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import player.*;
import Composition_Screen.*;
public class MBar extends JPanel 
    {
        PlayButton play;
        Channels channels;
        boolean isPlaying;
        boolean isFirst;
        String status;
        MTimeline timeline;
        CS cs;
        JComboBox<String> mScale;
   
        String[] scaleNames = {"Nothing","C Major", "Db Major", "D Major", "Eb Major", "F Major", "Gb Major", "G Major", "Ab Major", "A Major", "Bb Major", "B Major",
        "C Minor", "Db Minor", "D Minor", "Eb Minor", "F Minor", "Gb Minor", "G Minor", "Ab Minor", "A Minor", "Bb Minor", "B Minor"};
        public MBar(Channels channels, MTimeline timeline,CS cs)
        {
            this.channels = channels;
            this.cs = cs;
            this.timeline = timeline;
            this.setLayout( new BorderLayout() );
            this.setBackground( new Color(50, 50, 50) );
            play = new PlayButton(); 
            play.setText("Play");
            this.add(play, BorderLayout.WEST);
            play.setBounds( 0, 0, 100, 30);
            mScale = new JComboBox<String>(scaleNames);
            mScale.addActionListener( new ComboBoxListener() );
            add(mScale,BorderLayout.CENTER);
            
            
            isPlaying = false;
            isFirst = true;
        }
        public void setIsPlaying(boolean x)
        {
           isPlaying = x;
        }
        public void setIsFirst(boolean x)
        {
           isFirst = x;
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
                      status = "Stop";
                      play.setText(status);
                      channels.getPlayer().updatePlayer(timeline.timeButton.time);
                      channels.getPlayer().startPlaying();
                      
                      timeline.setPeriod(channels.getTempo() );
                      timeline.play();
                      isPlaying = true;
                      isFirst = false;
                   }
                   else if (isPlaying)
                   {
                      status = "Play";
                      play.setText(status);
                      
                      channels.getPlayer().stopPlaying();
                      timeline.stop();
                      
                      isPlaying = false;
                      isFirst = false;
                   }
                   else if(!isPlaying)
                   {
                      status = "Stop";
                      play.setText(status);
                      
                      channels.getPlayer().startPlaying();
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
        public class ComboBoxListener implements ActionListener
        {
           @Override
           public void actionPerformed ( ActionEvent e)
           {
              if( e.getSource() == mScale)
              {
                 //channel.setInstrument( ((JComboBox)e.getSource()).getSelectedIndex() );
                 cs.setMusicScale( ((JComboBox)e.getSource()).getSelectedIndex() );
                 cs.repaintAll();
              }
           }
        }
        public void setStatus(String s)
        {
           status = s;
           play.setText(status);
        }
        
}