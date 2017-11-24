package org.randoom.setlx.MusicPlayer.SingleTonePlayer;

import jm.music.rt.*;
import jm.audio.*;
import jm.music.data.*;
import org.randoom.setlx.MusicPlayer.SingleTonePlayer.instrumentSets.RTPluckInst;

import java.util.ArrayList;

import static jm.constants.Durations.*;
import static jm.music.data.Note.*;

public class RealtimeTonePlayer extends RTLine {

    ArrayList<Note> noteQueue = new ArrayList<>();
    private boolean isPaused = false;

    public RealtimeTonePlayer(Instrument[] inst) {
        super(inst);
    }

    /**
     * Adds a new note into the queue.
     * If it is
     * @param n
     */
    public void addNote(Note n){
        noteQueue.add(n);
        isPaused = false;
        this.unPause(); //There is at least on Note in the queue
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


    public static void main(String[] args) throws InterruptedException {
        int sampleRate = 44100;
        Instrument inst = new RTPluckInst(sampleRate);
        Instrument[] insts = new Instrument[] {inst};
        RealtimeTonePlayer t = new RealtimeTonePlayer(insts);
        RTLine[] rtlines = {t};
        final RTMixer rtm = new     RTMixer(rtlines);
        System.out.println("GO");
        rtm.begin();
        t.addNote(new Note((int)(Math.random() * 60 + 30), 10,
                (int)(Math.random() * 100 + 27)));
        Thread.sleep(5000);
        rtm.begin();
        System.out.println(System.currentTimeMillis());
        t.addNote(new Note((int)(Math.random() * 60 + 30), 10,
                (int)(Math.random() * 100 + 27)));
        System.out.println(System.currentTimeMillis());
        Thread.sleep(10000);
        rtm.stop();

    }

}