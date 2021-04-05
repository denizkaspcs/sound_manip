package GUI_STUFF;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import player.*;
import Composition_Screen.*;
import Composition_Screen.CS_Elements.*;

public class LeftPanel extends JPanel
{
   final Dimension PANEL_SIZE = new Dimension(400, 300);
   final Font FONT = new Font("Arial", Font.BOLD, 16);
   JPanel panelVolume1, panelVolume2;
   JLabel noteBrushLabel, noteLabel, loudnessLabel1, loudnessLabel2;
   JSlider slider1, slider2;
   Font font;
   GridBagConstraints gridCons;
   CS cs;
   Channels channel;
   JPanel undoRedo;
   public LeftPanel(CS cs, Channels channel, JPanel undoRedo)
   {
     
      //setPreferredSize(PANEL_SIZE);
      this.setLayout(new GridBagLayout());
      this.undoRedo = undoRedo;
      gridCons = new GridBagConstraints();
      gridCons.gridx = 0; //single column
      gridCons.fill = GridBagConstraints.BOTH;
      
      this.cs = cs;
      this.channel = channel;
      
      noteBrushLabel = new JLabel("<html><u> Note Brush Properties </u></html>");
      noteBrushLabel.setHorizontalAlignment(0);
      noteBrushLabel.setFont(font);
      
      loudnessLabel1 = new JLabel("Default Loudness");
      loudnessLabel1.setHorizontalAlignment(0);
      
      slider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
      slider1.setMajorTickSpacing(25);
      slider1.setPaintTicks(true);
      slider1.setPaintLabels(true);
      slider1.addChangeListener(new SliderListener1() );
      
      
      
      noteLabel = new JLabel("<html><u> Note Properties </u></html>");
      noteLabel.setHorizontalAlignment(0);
      noteLabel.setFont(font);
      
      loudnessLabel2 = new JLabel("Note's Loudness");
      loudnessLabel2.setHorizontalAlignment(0);
      
      slider2 = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
      slider2.setMajorTickSpacing(25);
      slider2.setPaintTicks(true);
      slider2.setPaintLabels(true);
      slider2.addChangeListener(new SliderListener2() );
      
      //add UNDO / REDO here
      
      add(undoRedo, gridCons);
      
      //add NoteBrushProperties
      panelVolume1 = new JPanel();
      panelVolume1.setLayout(new GridLayout(3, 1));
      panelVolume1.add(noteBrushLabel);
      panelVolume1.add(loudnessLabel1);
      panelVolume1.add(slider1 );
      gridCons.gridy = 1;
      gridCons.weighty = 5;
      this.add(panelVolume1, gridCons);
      
      //add NoteProperties
      panelVolume2 = new JPanel();
      panelVolume2.setLayout(new GridLayout(3, 1));
      panelVolume2.add(noteLabel);
      panelVolume2.add(loudnessLabel2);
      panelVolume2.add(slider2);
      gridCons.gridy = 2;
      gridCons.weighty = 5;
      this.add(panelVolume2, gridCons);
      //gridCons.insets = new Insets(50, 20, 90, 10);
      
   }
   public JSlider getSlider2()
   {
      return slider2;
   }
   public class SliderListener2 implements ChangeListener
   {
      @Override
      public void stateChanged (ChangeEvent e)
      {
         if(e.getSource() == slider2 && !(((JSlider)e.getSource()).getValueIsAdjusting()) )
         {
            System.out.println("Selected note is: " + cs.getSelected() );
            if(cs.getSelected() == null)
               System.out.println("Im NULL");
            else
            {
               for(int i = 0; i < channel.getPlayer().getNotesArray().size(); i++)
               {
                  if(channel.getPlayer().getNotesArray().get(i).equals(cs.getSelected() ) )
                  {
                     if(((JSlider)e.getSource()).getValue() != channel.getPlayer().getNotesArray().get(i).getVelocity())
                     {
                        channel.getPlayer().getNotesArray().get(i).setVelocity(((JSlider)e.getSource()).getValue());
                        cs.setChannel(channel);
                     }
               //      channel.getPlayer().updatePlayer(0);
                  }
               }
            }
         }
      }
   }

   public class SliderListener1 implements ChangeListener
   {
      @Override
      public void stateChanged (ChangeEvent e)
      {
         if(e.getSource() == slider1)
         {
            cs.setDefaultVelocity( ((JSlider)e.getSource()).getValue());
         }
      }
   }
}
