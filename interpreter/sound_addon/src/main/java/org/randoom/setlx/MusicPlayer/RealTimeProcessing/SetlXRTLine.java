package org.randoom.setlx.MusicPlayer.RealTimeProcessing;

import jm.music.rt.*;
import jm.audio.*;
import jm.music.data.*;

import java.util.ArrayList;

import static jm.constants.Durations.*;
import static jm.music.data.Note.*;

/**
 * Created by Lukas on 24.11.2017.
 *
 * A Class, that generates a line of Notes, that can be processed by a mixer in Real Time.
 * It should be implemented as a queue, that takes new notes via the @addNote method.
 *
 */
public class SetlXRTLine extends RTLine {


    ArrayList<Note> noteQueue = new ArrayList<>();
    private boolean isPaused = false;


    public SetlXRTLine(Instrument[] inst) {
        super(inst);
    }


    /**
     * Add a new {@link Note} to this Line, that can be consumed by a {@link SetlXRTMixerImpl}
     * @param n
     */
    public void addNote(Note n){
        noteQueue.add(n);
        isPaused = false;
        this.unPause(); //There is at least on Note in the queue
    }

    /**
     * Returns an array of all notes that will be played in the future.
     * @return
     */
    public ArrayList<Note> getNotes() {
        return noteQueue;
    }

    /**
     * Clears all cached and future played notes.
     */
    public void clearQueue() {
        noteQueue.clear();
    }


    @Override
    /**
     * Takes the next note, that is stored in the noteQueue. If it is empty, the System will be paused.
     */
    public Note getNextNote() {
        if(noteQueue.isEmpty()) {
            Note n = new Note(REST, CROTCHET, -1, 0);
            n.setDuration(0.1d);
            n.setRhythmValue(0.1d); //We manually set the duration of a break.
            this.pause(); //As the queue is empty, we will pause the system. As soon, as the length of the queu is greater than 0 again, it will be unpaused
            isPaused = true;
            return n;
        }
        Note tmp = noteQueue.get(0);
        noteQueue.remove(0);
        return tmp;
    }
}