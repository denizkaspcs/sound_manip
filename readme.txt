HOW TO RUN
------------
Execute the Music_Composer.jar













---------------------------------DESCRIPTION OF CLASSES------------------------------
The code works on three different components in three different packages

The Composition_Screen package that contains the composition screen,
The player package that includes the code that the program runs on,
The GUI_STUFF package that the left panel and the track panel are located on,

The miniPlayer class is essentially a player that plays and compiles all the notes, each note is an instance
of the notes class, which are stored in an arrayList inside of the miniPlayer class.

An instance of the channels class represents a channel, kind of like an instrument, every channel can have one instrument
and the melody or the song written in an instrument is stored in an instance of the channels class.

You can create or compose songs by creating multiple channels -a maximum of 16- and playing them together.
(Sorry it's kind of hard to explain formally, think of it like this: you play something on a piano or a guitar and record it
that is one channel, your friend plays the drums and that is another channel. By playing them together you get a song)
 
The channels class has a miniPlayer property as a parameter in its constructor, all the instances of the chanells
classes takes the same player as a parameter ass all the notes in all the channels will be played at the same time.

Composition screen takes a channel property as a parameter, and it only shows the notes on the selected channel,
you can change the channel you want to view on the track panel,

Composition screen is also where you add, edit and remove notes.

On the track pannel, you can edit a channels properties, like change the instrument, mute the channel, solo it, or change its volume

Left panel edits a channel or a note, for it to edit a single note, you need to select that note which can be done on the composition screen.
------------------------------------------------END OF DESCRIPTION---------------------------

-------------------------------------------------CLASS HIERARCHY---------------------------------------
Note class's constructor takes some int values to initialize the note with javax.sound.midi
-There is only one instance of a miniplayer- MiniPlayer's constructor does not take any parameters->it does not communicate with others, the others communicate with it
Channels's constructor takes a miniPlayer instance to communicate with it, every single notes needs to be on a channel, so notes are passed from the channel's class to the player
CS class's constructor takes a channel to interact with notes, you can access the player through the channel class, so the cs (composition screen) can interact with both channels and player
All CS_Elements take a channel -some of them also takes a CS- to interact with notes,channels,player and the composition screen
All the GUI_STUFF class's takes a channel and a CS to interact with both of them
----------------------------------------------END OF CLASS HIERARCHY---------------------------
---what tools we used--------------------------------
The code uses javax.sound.midi, java.io, swing, awt,
As an IDE, most of used DRJAVA but some used Eclipse
---------------------------------------------------------------------------

---------------------------------HOW TO USE--RUN--- AND WHERE TO FIND SOME STUFF-----------------
Because we kind of finished the project at the last second there is no pretty way to use it,
You should compile and run our main method which is TestTracksClass(which is also an old name)

On the right side of the play button there is a dropbox(everything on the right side of the play button is a dropbox) currently checking "nothing" which allows you to select different scales
in music, only certain notes sound good after a specific note, this feature helps you to create or compose better in different keys and in major/minor.

To use the save feature ->

First, you can't save an empty canvas, after you added some notes and made your notes, you should press save project,
locate where you want to save the project file and then add an "\" and then enter the file name you want to save as.

To you use the save midi feature-> (which is how you get a sound file for troubles)
is the same with the save feature

To use the load feature ->
You can browse your computer to find the destination of the file, the file you want to open should be a ".ser" file (which kind of means needs to be created with our save project feature)

In the toolbar->view->uiScale you could change the scale of the composition screen, the piano and so on..

You can find the save/load/save midi features in the file menu in the toolbar


----------------------------------------GENERAL INFO -------------------------
Project Number: g1C
Title Description: Music Composer
Current status: Working but: redo button disabled, the right part of the track panel(the panel that is located at the bottom) is currently not working-has all the methods but still too buggy to include-
List of group members:

Batuhan Budak -> Save, load, save midi, Javax.sound.midi
Deniz Kasap -> Javax.sound.midi, listeners, communication between different components
Ali Çetinkaya -> Composition screen, help with track panel
--------------------------------------------GENERAL INFO END-----------------------
