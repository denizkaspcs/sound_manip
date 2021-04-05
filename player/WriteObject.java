package player;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.*;
import java.util.*;
import javax.swing.*;

// @author Batuhan Budak

public class WriteObject
{
   
   /*
    * Write the MiniPlayer.pit ArrayList to the File Object
    * @param noteList
    * @param realFileName
    */ 
   public void serializePlayer(ArrayList<Note> noteList, String realFileName)
   {
      String fileName = "";
      
      FileOutputStream fout = null;
      ObjectOutputStream oos = null;
            
      JButton save = new JButton();
      JFileChooser fc = new JFileChooser();// Creating JFileChooser
      fc.setCurrentDirectory(new java.io.File("."));// setting current directory to the project folder
      fc.setDialogTitle("Save Project");// setting dialog title
      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      
      if(fc.showSaveDialog(save) == JFileChooser.APPROVE_OPTION){//empty if statement do declare save to approve option
      }
      String filePath = fc.getSelectedFile().getAbsolutePath();
      
      //filename = fc.getSelectedFile().getName();
      
      File file = new File(filePath + "\\" + fileName);
      file.setWritable(true);
      
      // try catch statement to write the noteList into a File
      try{
         fout = new FileOutputStream(file);
         oos = new ObjectOutputStream(fout);
         oos.writeObject(noteList);
         
      } catch (Exception ex){
         ex.printStackTrace();
         
      } finally {
         if(fout != null){
            try{
               fout.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
         
         if(oos != null){
            try{
               oos.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
   } 
}