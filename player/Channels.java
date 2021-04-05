package player;

import java.util.*;
/*
 * This class in for convenience, the whole code could work without this class but it would be much harder to write and
 * follow.
 * An instance of this class represent a channel
 * A channel is a place you could add notes, set their instrument and so on
 * There can be a maximum of 16 channels in a track so our program is restricted to a maximum of 16 diff instruments
 * 
 * 
 * 
 */
public class Channels
{
   //properties
   MiniPlayer player;
   int channel;
   
   //constructor
   public Channels (MiniPlayer player, int channel)
   {
      this.player = player;
      this.channel = channel;
   }
   //----------------METHODS----------------------
   
   /* Takes a note that the user wants to add to this channel
    * passes it to the miniPlayer class and it's own channel as a identifier
    */
   public void addNotes(int key, int velocity, int start, int end)
   {
      player.addNote(channel, key,velocity,start,end);
      player.getAddCheck().add(new Note(0,0,0,0,0));
   }
   /*Removes the given note from the channel
    * 
    */
   public void removeNote(Note n)
   {
      player.removeNote(n);
      player.getAddCheck().removeAll(player.getAddCheck() );
   }
   
   /*Changes the key of a note by first saving all of the note's properties,
    * then removing the note
    * and then adding the new version to an array
    * 
    * Note: in the future instead of taking a note, this method could take all the properties of the wanted note and
    * use those properties in the equals() method and then do it's function, 
    * This may be usefull since we do not name the notes but create them in loops by writing new Note (...)
    */
   public void changeKey(Note n, int key)
   {
      int tempChannel, tempKey, tempStart, tempEnd, tempVelocity;
      tempChannel = n.getChannel();
      tempKey = n.getKey();
      tempStart = n.getTickInit();
      tempEnd = n.getTickEnd();
      tempVelocity = n.getVelocity();
      
      player.getErasedKeyArray().add(n);
      player.getAddCheck().removeAll(player.getAddCheck() );
      player.removeNote(n);
      
      
      
      player.addNote(tempChannel, key, tempVelocity, tempStart, tempEnd);
      //these values are for the "undo/redo" function which is explained further in the MiniPlayer class 
      player.setChangedKeyLast(true);
      player.setChangedDurationLast(false);
      player.setAddedLast(false);
      player.setDeletedLast(false);
   }
   
   /*Changes the duration of a note by first saving all of the note's properties,
    * then removing the note
    * and then adding the new version to an array
    * 
    * Note: in the future instead of taking a note, this method could take all the properties of the wanted note and
    * use those properties in the equals() method and then do it's function, 
    * This may be usefull since we do not name the notes but create them in loops by writing new Note (...)
    */
   public void changeDuration(Note n, int start, int end)
   {
      System.out.println("x");
      int tempChannel, tempKey, tempStart, tempEnd, tempVelocity;
      tempChannel = n.getChannel();
      tempKey = n.getKey();
      tempStart = n.getTickInit();
      tempEnd = n.getTickEnd();
      tempVelocity = n.getVelocity();
      
      player.getErasedDurationArray().add(n);
      player.getAddCheck().removeAll(player.getAddCheck() );
      player.removeNote(n);
      
      
      player.addNote(tempChannel, tempKey, tempVelocity, start, end);
      
      //these values are for the "undo/redo" function which is explained further in the MiniPlayer class 
      player.setChangedDurationLast(true);
      player.setChangedKeyLast(false);
      player.setAddedLast(false);
      player.setDeletedLast(false);
   }
   //------------All of these methods just passes the same info the the MiniPlayer class
   //What they do and how they do it are explained in the MiniPlayer class
   public void undo()
   {
      player.undo();
   }
   
   public void redo()
   {
      player.redo();
   }
   
   public void mute()
   {
      player.mute(channel);
   }
   
   public void unmute()
   {
      player.unmute(channel);
   }
   
   public void solo()
   {
      player.solo(channel);
   }
   
   public void unSolo()
   {
      player.unSolo(channel);
   }
   
   //---SETTER----GETTER---
   public MiniPlayer getPlayer()
   {
      return player;
   }
   
   public void setInstrument(int instrument)
   {
      player.setInstrument(channel, instrument);
   }
   
   public int getChannel()
   {
      return channel;
   }
   
   
   public ArrayList<Note> setNoteData()
   {
      ArrayList<Note> newTemp = new ArrayList<Note>();
      
      for (int i = 0; i < player.getNotesArray().size(); i++)
      {
         
         newTemp.add( player.getNotesArray().get(i) );
      }
      
      for(int i = player.getNotesArray().size() - 1; i >= 0 ; i--)
      {
         if (!(player.getNotesArray().get(i).getChannel() == channel))
            newTemp.remove(i);
      }
      
      return newTemp;
   }
   
   public String toString()
   {
      return "Channel " + channel;
   }
   
   public float getTempo()
   {
      return ( player.getTempo() / 2 );
   }
   
   public void setVelocity( int velocity)
   {
      for( int i = 0; i < player.getNotesArray().size(); i++)
      {
         if(player.getNotesArray().get(i).getChannel() == channel )
            player.getNotesArray().get(i).setVelocity(velocity);
      }
   }
   public int getLastIndex()
   {
      int max = 0;
      for(int i = 0; i < player.getNotesArray().size(); i++)
      {
         if (player.getNotesArray().get(i).getTickEnd() >= max)
            max = player.getNotesArray().get(i).getTickEnd();
      }
      return max;
   }
   
   public void clearAllNotes()
   {
      for(int i = player.getNotesArray().size() -1; i >= 0 ; i--)
      {
         if(player.getNotesArray().get(i).getChannel() == channel)
         {
            player.getNotesArray().remove(i);
         }
      }
   }
   
   public void setNameForPlayer(String s)
   {
      player.setName(s);
   }
      
   
} 