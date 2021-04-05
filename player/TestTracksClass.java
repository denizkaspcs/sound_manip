package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import Composition_Screen.*;
import GUI_STUFF.*;

public class TestTracksClass extends JFrame
{
   CS screen;
   MiniPlayer player;
   Channels[] channelsArray = new Channels[16];
   JMenuBar menuBar;
   JMenu file,  view, help, uiScale;
   JMenuItem sc50, sc100,  newFile, saveProject, load, advice,saveMidi;
   LeftPanel leftPanel;
   
   public TestTracksClass()
   {
      player = new MiniPlayer();
      
      Channels channel1 = new Channels(player, 1);
      Channels channel2 = new Channels(player, 2);
      Channels channel3 = new Channels(player, 3);
      Channels channel4 = new Channels(player, 4);
      Channels channel5 = new Channels(player, 5);
      Channels channel6 = new Channels(player, 6);
      Channels channel7 = new Channels(player, 7);
      Channels channel8 = new Channels(player, 8);
      Channels channel9 = new Channels(player, 9);
      Channels channel10 = new Channels(player, 10);
      Channels channel11 = new Channels(player, 11);
      Channels channel12 = new Channels(player, 12);
      Channels channel13 = new Channels(player, 13);
      Channels channel14 = new Channels(player, 14);
      Channels channel15 = new Channels(player, 15);
      Channels channel16 = new Channels(player, 16); 
      
      channelsArray[0] = channel1;
      channelsArray[1] = channel2;
      channelsArray[2] = channel3;
      channelsArray[3] = channel4;
      channelsArray[4] = channel5;
      channelsArray[5] = channel6;
      channelsArray[6] = channel7;
      channelsArray[7] = channel8;
      channelsArray[8] = channel9;
      channelsArray[9] = channel10;
      channelsArray[10] = channel11;
      channelsArray[11] = channel12;
      channelsArray[12] = channel13;
      channelsArray[13] = channel14;
      channelsArray[14] = channel15;
      channelsArray[15] = channel16;
      
      
      
      
      
      Note testNote = new Note (1, 80, 100, 0, 10);
      Note testNote2 = new Note(1,70,100,10,20);
      Note testNote3 = new Note(1,60,100,20,30);
      

      
      setLayout(new BorderLayout() );
      screen = new CS( 36, 36, channel1);
      //  screen.setChannel(1);
      this.add(screen,BorderLayout.CENTER);
      //screen.setPreferredSize( new Dimension(1000, 0));
      setTitle( "Test Screen");
      setBounds( 300, 500, 500, 500);
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
      setTitle("Music Composer");
      menuBar = new JMenuBar();
      file = new JMenu("File");
      view = new JMenu("View");
      help = new JMenu("Help");
      uiScale = new JMenu("UIScale"); // Inner Menu of view
      
      sc50 = new JMenuItem("50%");
      sc100 = new JMenuItem("100%");
      saveProject = new JMenuItem("Save Project");
      saveMidi = new JMenuItem("Save Midi");
      load = new JMenuItem("Load");
      advice = new JMenuItem("Advice");
      
      setJMenuBar(menuBar);
      
      menuBar.add(file);
      menuBar.add(view);
      menuBar.add(help);
      
      view.add(uiScale);
      
      file.add(saveProject);
      file.add(saveMidi);
      file.add(load);
      
      uiScale.add(sc50);
      uiScale.add(sc100);
      
      help.add(advice);
      
      // ActionListeners
      
      saveProject.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent arg0) {
            WriteObject obj = new WriteObject();
            obj.serializePlayer(player.getNotesArray(), player.getName());
            
         }
      });
      
      saveMidi.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent arg0) {
            
            
            player.saveMidi(player.getName());
            
            
         }
      });
      load.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent arg0) {
            ReadObject obj = new ReadObject();
            ArrayList<Note> loadedNotes = obj.deserialzePlayer();
            for(int i = 0; i < loadedNotes.size(); i++)
            {
               System.out.println(i + "  :" +  loadedNotes.get(i));  
            }
            player.setNotesArray(loadedNotes);
            screen.setChannel( screen.getChannel() );
            screen.repaintAll();
         }
      });
      
      sc50.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent arg0) {
            screen.setXScale(18);
            screen.setYScale(18);
            screen.repaintAll();
         }
      });
      
      sc100.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent arg0) {
            screen.setXScale(36);
            screen.setYScale(36);
            screen.repaintAll();
         }
      });
      
      advice.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent arg0) {
            try {
               Desktop desktop = Desktop.getDesktop();
               desktop.browse(new URI("https://www.amazon.com/Music-Theory-Dummies-Michael-Pilhofer-ebook/dp/B00QQMVMD0"));
            } 
            catch (IOException ex) {
               ex.printStackTrace();
            } 
            catch (URISyntaxException ex) {
               ex.printStackTrace();
            }
         }
      });
      
      screen.repaintAll();
      setVisible(true);   
      
      UndoRedo undoRedo = new UndoRedo(channel1, screen);
      
      LeftPanel leftPanel = new LeftPanel(screen, channel1, undoRedo);
      add(leftPanel,BorderLayout.WEST);
      
      screen.getCanvas().setLeftPanel(leftPanel );
      
        
      TrackPanel tPanel;
      tPanel = new TrackPanel(channelsArray, screen);
      
      add(tPanel, BorderLayout.SOUTH);
      
      
   }
   
   public static void main(String []args)
   {
      JFrame f = new TestTracksClass();
      
   }
}