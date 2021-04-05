package player;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import java.io.*;

public class Note implements Serializable {
   //properties
   int channel, key, velocity, tickInit, tickEnd;
   //constructor
   public Note(int channel, int key, int velocity, int tickInit, int tickEnd) {
      this.channel = channel;
      this.key = key;
      this.velocity = velocity;
      this.tickInit = tickInit;
      this.tickEnd = tickEnd;
   }
   
   
   public MidiEvent makeEvent(String command, int instrument) 
   { 
      
      MidiEvent event = null; 
      //this will help "compile" the notes in the MiniPlayer class
      //instead of writing 144,.... which means "the note starts at"
      //we write start and the same for "end" & "instrument"
      try { 
         if (command == "start")
         {
            ShortMessage a = new ShortMessage(); 
            a.setMessage(144, channel, key, velocity); 
            
            event = new MidiEvent(a, tickInit);
         }
         else if (command == "end")
         {
            ShortMessage a = new ShortMessage(); 
            a.setMessage(128, channel, key, velocity); 
            
            event = new MidiEvent(a, tickEnd);
         }
         else if (command == "instrument")
         {
            ShortMessage a = new ShortMessage(); 
            a.setMessage(192, channel, instrument, 0); 
            
            event = new MidiEvent(a, 0);
            
         }
         
      } 
      catch (Exception ex) { 
         
         ex.printStackTrace(); 
      } 
      return event; 
   }
   //----------------------------------------------------------METHODS--------------------------------------------------
   /*equals
    * Checks if the given notes are the same
    * 
    * There can't be two notes with the exact same key,channel,start and end since they will be the same and there won't
    * any sound difference when played
    * 
    */
   public boolean equals(Note n)
   {
      if(n == null)
         System.out.println("Hola");
      else
      {
         if(n.getChannel() == this.channel && n.getKey() == this.key && n.getVelocity() == this.velocity && n.getTickInit() == this.tickInit && n.getTickEnd() == this.tickEnd)
            return true;
         else 
            return false;
      }
      return false;
   }
   
   //GETTER-------SETTER--------
   public int getChannel()
   {
      return channel;
   }
   
   public int getKey()
   {
      return key;
   }
   
   public int getVelocity()
   {
      return velocity;
   }
   
   public int getTickInit()
   {
      return tickInit;
   }
   
   public int getTickEnd()
   {
      return tickEnd;
   }
   
   public void setVelocity(int velocity)
   {
      this.velocity = velocity;
   }
   //---TOSTRING---
   public String toString()
   {
      return "Key: " + key + " Duration :" + tickInit + "-" + tickEnd +"/ "; 
   }
   
}
