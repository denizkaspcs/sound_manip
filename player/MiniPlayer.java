package player;

import javax.sound.midi.*; 
import java.util.*; 
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.io.*;

public class MiniPlayer implements Serializable { 
   //properties
   //ARRAY-LISTS
   ArrayList<Note> pit = new ArrayList<Note>(); // ArrayList of Notes
   ArrayList<Note> temp1 = new ArrayList<Note>();
   ArrayList<Note> temp2 = new ArrayList<Note>();
   ArrayList<Note> temp3 = new ArrayList<Note>();
   ArrayList<Note> temp4 = new ArrayList<Note>();
   ArrayList<Note> temp5 = new ArrayList<Note>();
   ArrayList<Note> temp6 = new ArrayList<Note>();
   ArrayList<Note> temp7 = new ArrayList<Note>();
   ArrayList<Note> temp8 = new ArrayList<Note>();
   ArrayList<Note> temp9 = new ArrayList<Note>();
   ArrayList<Note> temp10 = new ArrayList<Note>();
   ArrayList<Note> temp11 = new ArrayList<Note>();
   ArrayList<Note> temp12 = new ArrayList<Note>();
   ArrayList<Note> temp13 = new ArrayList<Note>();
   ArrayList<Note> temp14 = new ArrayList<Note>();
   ArrayList<Note> temp15 = new ArrayList<Note>();
   ArrayList<Note> temp16 = new ArrayList<Note>();
   ArrayList<Note> undoRedo = new ArrayList<Note>();
   ArrayList<Note> erased = new ArrayList<Note>();
   ArrayList<Note> muteVelocity = new ArrayList<Note>();
   ArrayList<Note> tempProgressArray = new ArrayList<Note>();
   ArrayList<Integer> instrumentArray = new ArrayList<Integer>();
   
   ArrayList<Note> addCheck = new ArrayList<Note>();
   ArrayList<Note> erasedKey = new ArrayList<Note>();
   ArrayList<Note> erasedDuration = new ArrayList<Note>();
   
   //PROPERTIES
   boolean undoRemovedLast;
   boolean undoKeyChanged;
   boolean undoDurationChanged;
   boolean addedLast;
   boolean deletedLast;
   boolean undoAddedLast;
   boolean changedKeyLast;
   boolean changedDurationLast;
   boolean stopSwitch;
   boolean startSwitch;
   int timeCheck;
   int iKEY;
   int iDUR;
   String name = "";
   //seq
   Sequencer sequencer;
   Sequence sequence;
   //Constructor
   public MiniPlayer()
   {
      //sets every value of the instrument array the 0
      //zero encodes the instrument piano which will be the default if the user doesn't change it
      for(int i = 0; i < 16; i++)
         instrumentArray.add(i,0);
      
      //init
      iKEY = 1;
      iDUR = 1;
      startSwitch = false;
      stopSwitch = false;
      timeCheck = 0;
      
   }
   //main
   public static void main(String[] args) 
   { 
      MiniPlayer player = new MiniPlayer(); 
      Note note; 
      player.setUpPlayer();
   } 
   /*SetUpPlayer this method does, well nearly everything, it "compiles" all the notes that are in the "pit" array,
    * then plays them.
    * The instrument changes all happen in this method
    * 
    * 
    */
   public void setUpPlayer() 
   { 
      
      try { 
         //init
         sequencer = MidiSystem.getSequencer(); 
         sequencer.open(); 
         
         sequence = new Sequence(Sequence.PPQ, 2); 
         
         Track track = sequence.createTrack();          
         //this method kind of sorts all the notes in the "pit" array to a new array specific to their channel's
         for(int i = 0; i < pit.size(); i++)
         {
            if (pit.get(i).getChannel() == 1 )
               temp1.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 2 )
               temp2.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 3 )
               temp3.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 4 )
               temp4.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 5 )
               temp5.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 6 )
               temp6.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 7 )
               temp7.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 8 )
               temp8.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 9 )
               temp9.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 10 )
               temp10.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 11 )
               temp11.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 12 )
               temp12.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 13 )
               temp13.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 14 )
               temp14.add(pit.get(i) );
            else if (pit.get(i).getChannel() == 15 )
               temp15.add(pit.get(i) );
            else
               temp16.add(pit.get(i) );
         }
         //Now that the sorting is done this loop sequence sets the instruments for all the channels
         //The contents of the instrumentArray is set in the Channels and MiniPlayer class's setInstrument method
         //Now the values assigned to the instrumentArray are set as the isntrument of that channel
         for(int i = 0; i < temp1.size(); i++)
         {
            track.add(temp1.get(i).makeEvent("instrument", instrumentArray.get(0) ));
         }
         for(int i = 0; i < temp2.size(); i++)
         {
            track.add(temp2.get(i).makeEvent("instrument", instrumentArray.get(1) ));
         }
         for(int i = 0; i < temp3.size(); i++)
         {
            track.add(temp3.get(i).makeEvent("instrument", instrumentArray.get(2) ));
         }
         for(int i = 0; i < temp4.size(); i++)
         {
            track.add(temp4.get(i).makeEvent("instrument", instrumentArray.get(3) ));
         }
         for(int i = 0; i < temp5.size(); i++)
         {
            track.add(temp5.get(i).makeEvent("instrument", instrumentArray.get(4) ));
         }
         for(int i = 0; i < temp6.size(); i++)
         {
            track.add(temp6.get(i).makeEvent("instrument", instrumentArray.get(5) ));
         }
         for(int i = 0; i < temp7.size(); i++)
         {
            track.add(temp7.get(i).makeEvent("instrument", instrumentArray.get(6) ));
         }
         for(int i = 0; i < temp8.size(); i++)
         {
            track.add(temp8.get(i).makeEvent("instrument", instrumentArray.get(7) ));
         }
         for(int i = 0; i < temp9.size(); i++)
         {
            track.add(temp9.get(i).makeEvent("instrument", instrumentArray.get(8) ));
         }
         for(int i = 0; i < temp10.size(); i++)
         {
            track.add(temp10.get(i).makeEvent("instrument", instrumentArray.get(9) ));
         }
         for(int i = 0; i < temp11.size(); i++)
         {
            track.add(temp11.get(i).makeEvent("instrument", instrumentArray.get(10) ));
         }
         for(int i = 0; i < temp12.size(); i++)
         {
            track.add(temp12.get(i).makeEvent("instrument", instrumentArray.get(11) ));
         }
         for(int i = 0; i < temp13.size(); i++)
         {
            track.add(temp13.get(i).makeEvent("instrument", instrumentArray.get(12) ));
         }
         for(int i = 0; i < temp14.size(); i++)
         {
            track.add(temp14.get(i).makeEvent("instrument", instrumentArray.get(13) ));
         }
         for(int i = 0; i < temp15.size(); i++)
         {
            track.add(temp15.get(i).makeEvent("instrument", instrumentArray.get(14) ));
         }
         for(int i = 0; i < temp16.size(); i++)
         {
            track.add(temp16.get(i).makeEvent("instrument", instrumentArray.get(15) ));
         }
         
         
         //--------------------                                   track.add(makeEvent(192, 1, 1, 0, 0));
         // Add Notes which are stored in the pit to the track
         
         //Kind of "compiles" all the notes
         //Normally to play a note you need 2-3 lines of code like 
         //track.add(MakeEvent(144,channel,key,velocity,start,end ) ) , track.add(MakeEvent(128,channel,key,velocity,start,end) )
         //Writing this code for every single note is hard so we made an alternative
         for(int i = 0; i < tempProgressArray.size(); i++ ) {
            //the value 1 that is at the left of the "start" and "end" is for the "instrument" command. It does nothing for start or end
            
            
            track.add(tempProgressArray.get(i).makeEvent("start", 1) );
            track.add(tempProgressArray.get(i).makeEvent("end", 1) );
            
         }
         //Bugracým bu comment senin icin satýr 226 e kadar olan kodu okuyup
         //ne kadar sacma oldugunu farkedip duzeltmeye calýsma
         //onun dogrusu bende var su an ki hali sadece start,stop,resume
         //daha rahat calýssýn diye
         //bu haliyle calýsýyo ama start stop resume u gui de gorup
         //methodlarý nasýl kullancagýmýzý tam kavrayana kadar bu haliyle kalsýn
         //sonra ben halletcem
         
         //sets the sequencer and starts it
         sequencer.setSequence(sequence);
         
         if(startSwitch)
         {
            sequencer.start();
            
         }
         
         //to end the sequencer
         while (!sequencer.isRunning() ) { 
            
            if (!sequencer.isRunning()) { 
               sequencer.close();
               startSwitch = false;
               //System.exit(1); 
               
            } 
         } 
      } 
      catch (Exception ex) { 
         
         ex.printStackTrace(); 
      } 
   }
   //---------------------------------------------------------METHODS--------------------------------------------------
   
   /* AddNote method adds the entered to the arrayList named "pit" which contains all notes
    * the boolean values in the methods are for the "undo/redo" function
    * undoRedo.clear(); --> this line cleans the array which holds necessary values for the undo/redo function
    */
   public void addNote(int channel, int key, int velocity, int start, int end)
   {
      pit.add( new Note(channel, key, velocity, start, end) );
      
      undoRedo.clear();
      addedLast = true;
      deletedLast = false;
      changedKeyLast = false;
      changedDurationLast = false;
   }
   /*setInstrument method sets the instrument for the wanted channel, it changes the instrument of the sound you will hear
    * @param channel is the channel which's instrument will be set
    * @param instrument is the instrument type that will be set for the entered channel
    *
    * There can be maximum 16 channels, the instrumentArray that is used in the code holds the wanted instruments 
    * for the respective channels. With this method you simply change the value stored in the array, so when the code runs
    * the sound will be a different instrument
    */
   public void setInstrument(int channel, int instrument)
   {
      if(channel == 1)
         instrumentArray.add(0,instrument);
      
      else if (channel == 2)
         instrumentArray.add(1,instrument);
      
      else if (channel == 3)
         instrumentArray.add(2,instrument);
      
      else if (channel == 4)
         instrumentArray.add(3,instrument);
      
      else if (channel == 5)
         instrumentArray.add(4,instrument);
      
      else if (channel == 6)
         instrumentArray.add(5,instrument);
      
      else if (channel == 7)
         instrumentArray.add(6,instrument);
      
      else if (channel == 8)
         instrumentArray.add(7,instrument);
      
      else if (channel == 9)
         instrumentArray.add(8,instrument);
      
      else if (channel == 10)
         instrumentArray.add(9,instrument);
      
      else if (channel == 11)
         instrumentArray.add(10,instrument);
      
      else if (channel == 12)
         instrumentArray.add(11,instrument);
      
      else if (channel == 13)
         instrumentArray.add(12,instrument);
      
      else if (channel == 14)
         instrumentArray.add(13,instrument);
      
      else if (channel == 15)
         instrumentArray.add(14,instrument);
      
      else
         instrumentArray.add(15,instrument);
      
   }
   /*removeNote method removes a note from the "pit" array which holds all the notes that will be played
    * @param Note n is the note that wil be removed
    * 
    */
   public void removeNote(Note n)
   {
      for(int i = 0; i < pit.size(); i++)
      {
         if(pit.get(i).equals(n) )
         {
            //the erased.add line adds the will be removed note into an array called "erased"
            //this is for do "undo/redo" function
            erased.add(pit.get(i) );
            pit.remove(i);
         }
      }
      //these boolean sets are for the "undo/redo" function
      deletedLast = true;
      addedLast = false;
      changedKeyLast = false;
      changedDurationLast = false;
   }
   /*This method undo's the last action performed, but since we don't have an action log or something like that it
    * only works last action performed. This means that you can add infinitely many notes and undo them infinite times
    * But if you do another action like removing a note, then you can only add that note back by pressing undo, you cant
    * remove the note you previosuly added.
    * 
    * 
    */
   public void undo()
   {
      //The boolean values that are in the add, remove, changeKey, changeDuration methods are for this part
      //Undo's the durationChange methods's actions
      if(addedLast)
      {
         pit.remove(pit.size() - 1);
         addCheck.remove( addCheck.size() -1);
         
         System.out.println("ASD" + addCheck.size());
            
         if(addCheck.size() == 0)
         {
            System.out.println("ADD" + addCheck.size());
            addedLast = false;
         }
            //redoShit
      }
      else if (deletedLast)
      {
         
         pit.add(erased.get(erased.size() - 1) );
         erased.remove(erased.size() -1);
         //redoShit
         if(erased.size() == 0)
            deletedLast = false;
      }
      else if(changedKeyLast == true && changedDurationLast == false)
      {
         pit.remove(pit.size() - iKEY);
         pit.add(erasedKey.get(erasedKey.size() -1));
         erasedKey.remove(erasedKey.size() -1);
         iKEY++;
         if(erasedKey.size() == 0)
         {
            iKEY = 1;
            changedKeyLast = false;
         }
         //redoShit
      }
      else if(changedDurationLast == true && changedKeyLast == false)
      {
         pit.remove(pit.size() - iDUR);
         pit.add(erasedDuration.get(erasedDuration.size() -1));
         erasedDuration.remove(erasedDuration.size() -1);
         iDUR++;
         //redoShit
         if(erasedDuration.size() == 0)
         {
            iDUR = 1;
            changedDurationLast = false;
         }
      }
      else if(changedDurationLast == true && changedKeyLast == true)
      {
      
      }
      
   }
   /*Redo
    * Basically undo's the "undo" function
    * 
    */
   public void redo()
   {
      if(undoAddedLast == false)
      {
         if(undoRedo.size() > 0)
         {
            pit.add(undoRedo.get(undoRedo.size() -1) );
            undoRedo.remove(undoRedo.size() -1);
         }
      }
      else
         pit.remove(pit.size() -1 );
   }
   /*mute's the notes in the given channel
    * @param is the channel that will be muted
    * The muting works by setting all of the notes's (which are in the given channel) velocity properties to 0
    */
   public void mute(int channel)
   {
      for(int i = 0; i < pit.size(); i++)
      {
         if(pit.get(i).getChannel() == channel)
            pit.get(i).setVelocity(0);
         
      }
   }
   /*To be determined
    * Either set's all the velocity values back the their previous state
    * or just sets them all to 100
    * 
    */
   public void unmute(int channel)
   {
      for(int i = 0; i < pit.size(); i++)
      {
         if(pit.get(i).getChannel() == channel)
            pit.get(i).setVelocity(100);
         
      }
   }
   /*Solo
    * Mutes every other channel instead of the given one
    * @param channel is the only channel that won't be muted 
    */
   public void solo(int channel)
   {
      for(int i = 0; i < pit.size(); i++)
      {
         if(pit.get(i).getChannel() != channel)
            pit.get(i).setVelocity(0);
         
      }
   }
   /*Removes the effects of the solo method
    * 
    * works by unmuting all other channels
    * Since unmute method is not yet determined how this will work is undetermined too 
    */
   public void unSolo(int channel)
   {
      for(int i = 0; i < pit.size(); i++)
      {
         if(pit.get(i).getChannel() != channel)
            pit.get(i).setVelocity(100);
         
      }
   }
   //SETTER-----GETTER---------
   public Sequence getSequence()
   {
      return sequence;
   }
   
   public Sequencer getSequencer()
   {
      return sequencer;
   }
   
   public boolean getAddedLast()
   {
      return addedLast;
   }
   
   public ArrayList<Note> getAddCheck()
   {
      return addCheck;
   }
   
   public void setAddedLast(boolean x)
   {
      this.addedLast = x;
   }
   
   public boolean getDeletedLast()
   {
      return deletedLast;
   }
   
   public void setDeletedLast(boolean x)
   {
      deletedLast = x;
   }
   public boolean getChangedKeyLast()
   {
      return changedKeyLast;
   }
   
   public boolean getChangedDurationLast()
   {
      return changedDurationLast;
   }
   
   public void setChangedKeyLast(boolean x)
   {
      this.changedKeyLast = x;
   }
   
   public void setChangedDurationLast(boolean x)
   {
      this.changedDurationLast = x;
   }
   
   public ArrayList<Note> getNotesArray()
   {
      return pit;
   }
   
   public ArrayList<Note> getErasedKeyArray()
   {
      return erasedKey;
   }
   
   public ArrayList<Note> getErasedDurationArray()
   {
      return erasedDuration;
   }
   
   public ArrayList<Note> getErasedArray()
   {
      return erased;
   }
   
   
   // Start Playing
   public void startPlaying()
   {
      startSwitch = true;
      setUpPlayer();
   }
   public void setStartSwitch(boolean x)
   {
      startSwitch = x;
   }
   
   // Stop Playig
   public void stopPlaying()
   {
      if(startSwitch )
         sequencer.stop();
      
   }
   
   //ResumePlaying
   public void resumePlaying()
   {
      if(startSwitch)
         sequencer.start();
   }
   
   // Save
   public void saveMidi(String fileOut)
   {
      fileOut = "";
      JButton save = new JButton();
      JFileChooser fc = new JFileChooser();
      fc.setCurrentDirectory(new java.io.File("."));
      fc.setDialogTitle("Read Object");
      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      if(fc.showSaveDialog(save) == JFileChooser.APPROVE_OPTION){
      }
      String filePath = fc.getSelectedFile().getAbsolutePath();
      
      try{
         File file = new File(filePath + ".mid");
         MidiSystem.write(sequence,0,file);
      }
      catch(IOException ex)
      {
         ex.printStackTrace();
      }
   }
   
   public float getTempo()
   {
      return sequencer.getTempoInMPQ();
   }
   
   // get instrument names
   public static String[] getInstrumentNames()
   {
      final int DEFAULT_BANK = 0; // General MIDI 1 compatible
      Synthesizer synth;
      Soundbank soundBank;
      int instrNum;
      Instrument[] instruments;
      Instrument[] generalMidiInstruments;
      String[] instrumentNames;
      
      
      generalMidiInstruments = null;
      
      // getting available instruments
      try {
         synth = MidiSystem.getSynthesizer();
         synth.open();
         soundBank = synth.getDefaultSoundbank();
         instruments = soundBank.getInstruments();
         
         instrNum = 0;
         generalMidiInstruments = new Instrument[128];
         while ( instrNum < 128 && instrNum < instruments.length)
         {
            generalMidiInstruments[instrNum] = soundBank.getInstrument( new Patch( DEFAULT_BANK, instrNum));
            
            instrNum++;
         }
      }
      catch (Exception e) 
      {
         e.printStackTrace();
      }
      
      instrumentNames = new String[generalMidiInstruments.length];
      
      for(int j = 0; j < generalMidiInstruments.length; j++)
      {
         instrumentNames[j] = generalMidiInstruments[j].getName();
      }
      return instrumentNames;
   } 
   
   public void updatePlayer(int time)
   {
      tempProgressArray.removeAll(tempProgressArray);
      for(int i = 0; i < pit.size(); i++)
      {
         if(pit.get(i).getTickInit() >= time)
            tempProgressArray.add(new Note(pit.get(i).getChannel(), pit.get(i).getKey(), pit.get(i).getVelocity(), pit.get(i).getTickInit() - time, pit.get(i).getTickEnd() - time));
      }
   }
   
   public void setName(String s)
   {
      name = s;
   }
   
   public String getName()
   {
      return name;
   }
    public void setNotesArray(ArrayList<Note> loadedNotes)
    {
       pit = loadedNotes;
    }
   
   
   
} 
