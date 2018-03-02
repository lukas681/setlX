package org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer;

import org.jfugue.pattern.Atom;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.Exceptions.NegativeArgumentException;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.Exceptions.SetlXMidiNotAvailableException;
import org.randoom.setlx.SetlXMusic.factories.AtomFactory.iAtomFactory;
import org.randoom.setlx.SetlXMusic.factories.NoteFactory.iNoteFactory;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class RealTimerPlayer implements iRealTimePlayer {

    RealtimePlayer rtplayer;
    int noteDuration = 1;
    iNoteFactory noteFac;
    iAtomFactory atomFac;

    public RealTimerPlayer(iNoteFactory noteFactory, iAtomFactory atomFactory) throws SetlXMidiNotAvailableException {
        try {
            rtplayer = new RealtimePlayer();
        } catch (MidiUnavailableException midiException) {
            throw new SetlXMidiNotAvailableException();
        }
        noteFac = noteFactory;
        atomFac = atomFactory;
    }

    @Override
    public void stopNotes() throws SetlXMidiNotAvailableException {
        rtplayer.close();
        try {
            //We replace the current RealTime player with an new object.
            //Maybe in a future version of jfugue it becomes possible
            //to reset a Real Time palyer
            rtplayer = new RealtimePlayer();
        } catch (MidiUnavailableException e) {
            throw new SetlXMidiNotAvailableException();
        }
    }

    @Override
    public void play(PatternProducer pattern) {
        rtplayer.play(pattern);
    }

    @Override
    public void play(Atom musicalUnit) {
        rtplayer.play(musicalUnit);
    }

    @Override
    public void play(byte voice, byte layer, byte instrument, Note note) throws NegativeArgumentException {
        if(isNegativeOrZeroValue(voice, layer, instrument)){
            throw new NegativeArgumentException();
        }
        rtplayer.play(atomFac.createAtom(voice, layer, instrument, note));
    }

    @Override
    public void play(byte voice, byte layer, byte instrument, int value, double duration) throws NegativeArgumentException {
        if(isNegativeOrZeroValue(voice, layer, instrument, value)||duration<=0){
            throw new NegativeArgumentException();
        }
        play(voice, layer, instrument, noteFac.createNote(value, duration));
    }

    @Override
    public void changeInstrument(int instrument) throws NegativeArgumentException {
        if(instrument<=0){
            throw new NegativeArgumentException();
        }
        rtplayer.changeInstrument(instrument);
    }

    @Override
    public void setNoteDuration(int duration) throws NegativeArgumentException {
        if(duration <=0){
            throw new NegativeArgumentException();
        }
        this.noteDuration = duration;
    }

    /**
     * Returns true, if one of the input values is zero or negative
     * @param values
     * @return
     */
    private boolean isNegativeOrZeroValue(long... values){
        for(long x: values){
            if(x<=0)
                return true;
        }
        return false;
    }

}
