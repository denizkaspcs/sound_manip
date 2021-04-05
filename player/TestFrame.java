package player;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class TestFrame extends JFrame
{
   MiniPlayer player = new MiniPlayer();
   Channels channel = new Channels(player,1);

   
   Scanner scan = new Scanner (System.in);
   public TestFrame()
   {
      JFrame test = new JFrame();
      JPanel testP = new JPanel();
      test.setSize(800,600);
      
      JButton b1 = new JButton("start");
      JButton b2 = new JButton("stop");
      JButton b3 = new JButton("resume ins");
      
      b1.addActionListener( new MyActionListener() );
      b2.addActionListener(new MyActionListener() );
      b3.addActionListener(new MyActionListener() );
      
      
      testP.add(b1);
      testP.add(b2);
      testP.add(b3);
      
      test.add(testP);
      
      test.setVisible(true);
      
      channel.addNotes(70,100,0,5);
      channel.addNotes(80,100,5,10);
      channel.addNotes(90,100,10,15);
      channel.addNotes(80,100,15,20);
      channel.addNotes(80,100,20,25);
      channel.addNotes(80,100,25,30);
      channel.addNotes(80,100,30,35);
      channel.addNotes(80,100,35,40);
      channel.addNotes(80,100,40,45);
      channel.addNotes(80,100,45,50);
      
   }
   public static void main(String[] args)
   {
      new TestFrame();
   }
   public class MyActionListener implements ActionListener
   {
      public void actionPerformed (ActionEvent e)
      {
         if(((JButton)e.getSource()).getText().equals("start"))
         {
            channel.getPlayer().startPlaying();
            channel.getPlayer().save("testSave.mid");
         }
         else if (((JButton)e.getSource()).getText().equals("stop"))
         {
            System.out.println("Stop!");
            channel.getPlayer().stopPlaying();
         }
          else if (((JButton)e.getSource()).getText().equals("resume ins"))
         {
            System.out.println("Resume ins!");
            channel.getPlayer().resumePlaying();
         }
      }
   }
}