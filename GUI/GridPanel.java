package GUI_STUFF;

import player.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import Composition_Screen.*;
import Composition_Screen.CS_Elements.*;

public class GridPanel extends JPanel
{
   static int count = 0;
   
   final Dimension BUTTON_DIMENSION =  new Dimension(100, 25);
   final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 14);   
   
   Channels channel;  
   CS cs;
   
   int index;
   
   BarButton[] barButtons;
   
   public GridPanel(Channels channel, CS cs)
   {
      setLayout(new FlowLayout(50,0,0));
      setPreferredSize(new Dimension( 50*26, 37));
      barButtons = new BarButton[50];
      
      count++;
      index = count;
      
      for (int i = 0; i < 50; i++) 
      {
          barButtons[i] = new BarButton();          
          barButtons[i].setPreferredSize(new Dimension(26,37));
          add(barButtons[i]);
         // barButtons[i].setBackground(new Color(150,150,150));
          barButtons[i].setBackground(Color.RED);
          barButtons[i].setEnabled(false);
          
      }
      
      this.channel = channel;
      this.cs = cs;
      
   }
   public void updateGrid()
   {
      for(int i = 0; i < channel.getPlayer().getNotesArray().size(); i++)
      {
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 4 )
            barButtons[0].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 8 )
            barButtons[1].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 12 )
            barButtons[2].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 16 )
            barButtons[3].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 20 )
            barButtons[4].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 24 )
            barButtons[5].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 28 )
            barButtons[6].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 32 )
            barButtons[7].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 40 )
            barButtons[8].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 4 )
            barButtons[9].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 4 )
            barButtons[10].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 4 )
            barButtons[11].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 8 )
            barButtons[12].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 12 )
            barButtons[13].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 16 )
            barButtons[14].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 20 )
            barButtons[15].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 24 )
            barButtons[16].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 28 )
            barButtons[17].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 32 )
            barButtons[18].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 40 )
            barButtons[19].setEnabled(true);
         if(channel.getPlayer().getNotesArray().get(i).getTickInit() < 4 )
            barButtons[20].setEnabled(true);
         
         
            
      }
   }
   
   public class BarButton extends JButton
   {
       public BarButton()
       {
           
       }
   }
   
   public class clicked implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
          
      }
   }
   
   
   
   
}