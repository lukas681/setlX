package org.randoom.setlx.setlXMusic.realTimeSystem;

import org.jfugue.pattern.Atom;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.theory.Note;
import org.randoom.setlx.setlXMusic.realTimeSystem.Exceptions.NegativeArgumentException;
import org.randoom.setlx.setlXMusic.realTimeSystem.Exceptions.MidiNotAvailableException;

/**
 * This player allows playing tones in real time without any pre-processing.
 * You do not have to create persistent instances in order to play a few notes in real time.
 */

public interface iRealTimePlayer {


    /**
     * Stops all current queued notes and immediately stops the playback.
     */
    void stopNotes() throws MidiNotAvailableException;

    /**
     * Simply plays a musical pattern. This pattern is accessing to the global properties of the
     * iRealTimePlayer
     *
     * @param pattern
     */
    void play(PatternProducer pattern);

    void play(Atom musicalUnit);

    /**
     * Plays a particular tone with the given parameters.
     * @param voice
     * @param layer
     * @param instrument
     * @param note
     * @throws NegativeArgumentException
     */
    void play(byte voice, byte layer, byte instrument, Note note) throws NegativeArgumentException;

    void play(byte voice, byte layer, byte instrument, int value, double durationRelative) throws NegativeArgumentException;

    void play(byte voice, byte layer, byte instrument, int value, int durationBPM) throws NegativeArgumentException;

}
