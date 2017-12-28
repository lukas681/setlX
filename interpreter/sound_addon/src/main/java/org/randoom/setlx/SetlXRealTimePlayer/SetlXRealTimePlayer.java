package org.randoom.setlx.SetlXRealTimePlayer;

import org.jfugue.pattern.PatternProducer;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class SetlXRealTimePlayer implements iSetlXRealTimePlayer {

    RealtimePlayer rtplayer;
    int noteDuration = 1;

    public SetlXRealTimePlayer() {
        try {
            rtplayer = new RealtimePlayer();
        }catch(MidiUnavailableException midiException){
            System.out.println("The Midi device is not availbable");
        }
    }

    @Override
    public void startPlayer() {
    }

    @Override
    public void stopPlayer() {

    }

    @Override
    public void play(PatternProducer pattern) {

    }

    @Override
    public void play(Note note) {
        rtplayer.play(note);
    }

    @Override
    public void changeInstrument(int instrument) {
        rtplayer.changeInstrument(instrument);
    }

    @Override
    public void setNoteDuration(int duration) {
        this.noteDuration = duration;
    }
}
