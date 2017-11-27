package org.randoom.setlx.MusicPlayer.RealTimeProcessing;

import jm.music.data.Note;
import jm.music.rt.RTLine;

import java.util.ArrayList;

/**
 * Created by Lukas on 24.11.2017.
 *
 * A Class, that generates a line of Notes, that can be processed by a mixer in Real Time.
 * It should be implemented as a queue, that takes new notes via the @addNote method.
 *
 */
public interface iSetlXRTLine extends RTLine {

    void addNote(Note n);

    /**
     * Returns an array of all notes that will be played in the future.
     * @return
     */
    ArrayList<Note> getNotes();

    /**
     * Clears all cached and future played notes.
     */
    void clearQueue();

    /**
     * allows setting the tempo in BMP (Beats per Minute)
     * @param bpm
     */
    void setTempo(double bpm);

    //TODO more methods if needed...

}
