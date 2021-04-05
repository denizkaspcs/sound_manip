package player;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import javax.swing.
  *;
import java.io.*;

// @author Batuhan Budak
public class ReadObject 
{
   /*
    * Load a specified File object to the project
    * @return loadedNoteList
    */ 
   public ArrayList<Note> deserialzePlayer ()
   {
      String filename;
    
      ArrayList<Note> loadedNoteList = null;

      FileInputStream fin = null; 
      ObjectInputStream ois = null;
      JButton open = new JButton();
      JFileChooser fc = new JFileChooser();// Creating JFileChooser
      
      fc.setCurrentDirectory(new java.io.File("."));// setting current directory to project folder
      fc.setDialogTitle("Load Project");// Setting dialog title
      
      if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){// empty if statement to declare open to approve option option
      }
      filename = fc.getSelectedFile().getAbsolutePath();
      
      // Try cath statement for reading object from specified file
      try{
         fin = new FileInputStream(filename);
         ois = new ObjectInputStream(fin);
         loadedNoteList = (ArrayList<Note>)ois.readObject();
      }catch (Exception ex){
         ex.printStackTrace();
      }finally{
         
         if(fin != null){
            try{
               fin.close();
            }catch (IOException e){
               e.printStackTrace();
            }
         }
      }
      return loadedNoteList;
   }
}   