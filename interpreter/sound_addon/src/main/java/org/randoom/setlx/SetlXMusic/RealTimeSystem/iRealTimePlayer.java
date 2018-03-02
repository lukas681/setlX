package org.randoom.setlx.SetlXMusic.RealTimeSystem;

import org.jfugue.pattern.Atom;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.theory.Note;
import org.randoom.setlx.SetlXMusic.RealTimeSystem.Exceptions.NegativeArgumentException;
import org.randoom.setlx.SetlXMusic.RealTimeSystem.Exceptions.SetlXMidiNotAvailableException;

/**
 * This players allows playing tones in Real Time without any preprocessing.
 */

public interface iRealTimePlayer {


    /**
     * Stops all current queued notes. Immediately stops the playback.
     */
    void stopNotes() throws SetlXMidiNotAvailableException;

    /**
     * simply plays a musical pattern. This pattern is accessing to the global properties of the
     * iRealTimePlayer
     *
     * @param pattern
     */
    void play(PatternProducer pattern);

    void play(Atom musicalUnit);

    void play(byte voice, byte layer, byte instrument, Note note) throws NegativeArgumentException;

    void play(byte voice, byte layer, byte instrument, int value, double duration) throws NegativeArgumentException;

    void changeInstrument(int instrument) throws NegativeArgumentException;

    void setNoteDuration(int duration) throws NegativeArgumentException;

}
