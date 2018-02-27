package org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer;

import org.jfugue.pattern.Atom;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.theory.Note;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.Exceptions.NegativeArgumentException;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.Exceptions.SetlXMidiNotAvailableException;

/**
 * This players allows playing tones in Real Time without any preprocessing.
 */

public interface iSetlXRealTimePlayer {


    /**
     * Stops all current queued notes. Immediately stops the playback.
     */
    void stopNotes() throws SetlXMidiNotAvailableException;

    /**
     * simply plays a musical pattern. This pattern is accessing to the global properties of the
     * iSetlXRealTimePlayer
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
