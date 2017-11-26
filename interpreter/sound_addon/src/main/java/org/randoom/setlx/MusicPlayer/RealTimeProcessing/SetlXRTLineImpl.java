package org.randoom.setlx.MusicPlayer.RealTimeProcessing;

import jm.music.rt.*;
import jm.audio.*;
import jm.music.data.*;

import java.util.ArrayList;

import static jm.constants.Durations.*;
import static jm.music.data.Note.*;

public class SetlXRTLineImpl extends RTLine implements SetlXRTLineIntf {


    ArrayList<Note> noteQueue = new ArrayList<>();
    private boolean isPaused = false;


    public SetlXRTLineImpl(Instrument[] inst) {
        super(inst);
    }


    @Override
    public void addNote(Note n){
        noteQueue.add(n);
        isPaused = false;
        this.unPause(); //There is at least on Note in the queue
    }

    @Override
    public ArrayList<Note> getNotes() {
        return noteQueue;
    }

    @Override
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

    /**
     *  not used yet TODO examine, wether this method is needed
     * @param ob
     * @param i
     */

    public void externalAction(Object ob, int i) {
        // do filter change here
        // ob will be slider - get value
        // set filter value in instrument to slider value
        // in the instrumnet implement setController over ride method
        // in filter use the setCutOff method to change coefficients
    }

}