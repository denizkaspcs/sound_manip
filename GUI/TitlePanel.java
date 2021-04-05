package GUI_STUFF;

import player.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.ButtonUI;

public class TitlePanel extends JPanel
{
   
   final String titles = "                                     Track Name                             Instrument             S      M                            Volume";
   GridBagConstraints constraints;
   JLabel titlesLabel;
   final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 14);
   
   
   
   TitlePanel()
   {
      
      constraints = new GridBagConstraints();
      
      titlesLabel= new JLabel(titles);
      //setBackground(new Color(50,50,200));
      setFont(DEFAULT_FONT);
      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.fill = GridBagConstraints.CENTER;
      add(titlesLabel, constraints);
   }
}