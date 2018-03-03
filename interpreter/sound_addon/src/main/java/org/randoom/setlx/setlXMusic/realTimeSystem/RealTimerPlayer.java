package org.randoom.setlx.setlXMusic.realTimeSystem;

import org.jfugue.pattern.Atom;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;
import org.randoom.setlx.setlXMusic.realTimeSystem.Exceptions.NegativeArgumentException;
import org.randoom.setlx.setlXMusic.realTimeSystem.Exceptions.MidiNotAvailableException;
import org.randoom.setlx.setlXMusic.factories.atomFactory.iAtomFactory;
import org.randoom.setlx.setlXMusic.factories.noteFactory.iNoteFactory;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class RealTimerPlayer implements iRealTimePlayer {

    RealtimePlayer rtplayer;
    iNoteFactory noteFac;
    iAtomFactory atomFac;

    public RealTimerPlayer(iNoteFactory noteFactory, iAtomFactory atomFactory) throws MidiNotAvailableException {
        try {
            rtplayer = new RealtimePlayer();
        } catch (MidiUnavailableException midiException) {
            throw new MidiNotAvailableException();
        }
        noteFac = noteFactory;
        atomFac = atomFactory;
    }

    @Override
    public void stopNotes() throws MidiNotAvailableException {
        rtplayer.close();
        try {
            //We replace the current RealTime player with an new object.
            //Maybe in a future version of jfugue it becomes possible
            //to reset a Real Time palyer
            rtplayer = new RealtimePlayer();
        } catch (MidiUnavailableException e) {
            throw new MidiNotAvailableException();
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
        if (isNegativeOrZeroValue(voice, layer, instrument)) {
            throw new NegativeArgumentException();
        }
        rtplayer.play(atomFac.createAtom(voice, layer, instrument, note));
    }

    @Override
    public void play(byte voice, byte layer, byte instrument, int value, double durationRelative) throws NegativeArgumentException {
        if (isNegativeOrZeroValue(voice, layer, instrument, value) || durationRelative <= 0) {
            throw new NegativeArgumentException();
        }
        play(voice, layer, instrument, noteFac.createNote(value, durationRelative));
    }
    @Override
    public void play(byte voice, byte layer, byte instrument, int value, int durationBPM) throws NegativeArgumentException {
        play(voice, layer, instrument, value, convertBPMToRelativeValue(durationBPM));

    }


    /**
     * Returns true, if one of the input values is zero or negative
     *
     * @param values
     * @return
     */
    private boolean isNegativeOrZeroValue(long... values) {
        for (long x : values) {
            if (x <= 0)
                return true;
        }
        return false;
    }

    /**
     * Converts a Beats per Minute value into a relative one ranging from [0,1]
     *
     * It is a pure function defined by
     /*      convertBPMToRelativeValue: ]0,infty] -> ]0,1]
     *
     * Notice, that
     /*      convertBPMToRelativeValue(30) = 1 as the slowest value.
     *
     * @param bpm Beats per minute
     * @return
     */
    public double convertBPMToRelativeValue(int bpm){
      //  if(bpm==0) return 0;
            //TODO Possible Div by Zero excpetion;
        return 30d/(double)bpm; // 30 is the slowest possible tempo.
    }

}
