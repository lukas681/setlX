package org.randoom.setlx.MusicPlayer.SingleTonePlayer;

import jm.music.rt.*;
import jm.audio.*;
import jm.music.data.*;
import org.randoom.setlx.MusicPlayer.SingleTonePlayer.instrumentSets.RTPluckInst;

import java.util.ArrayList;

import static jm.constants.Articulations.LEGATO;
import static jm.constants.Durations.CROTCHET;
import static jm.constants.Panning.PAN_CENTRE;
import static jm.constants.Pitches.C4;
import static jm.constants.Pitches.REST;
import static jm.constants.Pitches.c7;
import static jm.constants.Volumes.MF;

public class test extends RTLine {

    ArrayList<Note> noteQueue = new ArrayList<>();
    private final double intervall = 0;

    public test(Instrument[] inst) {
        super(inst);
    }

    public void addNote(Note n){
        noteQueue.add(n);
        this.unPause(); //There is at least on Note in the queue
    }

    // required method to override
    public Note getNextNote() {
        System.out.println("START");
        if(noteQueue.isEmpty()) {
            System.out.println("Empty");
            Note n = new Note(REST, CROTCHET, -1, 0);
            n.setDuration(0.1d); //We manually set the duration of a break.
            this.pause(); //As the queue is empty, we will pause the system. As soon, as the length of the queu is greater than 0 again, it will be unpaused
            return n;
                 //   n.setRhythmValue(0.5d);
                   // return n; //empty note
        }
        Note tmp = noteQueue.get(0);
        System.out.println(tmp);
        noteQueue.remove(0);
        return tmp;
    }

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
        test t = new test(insts);
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