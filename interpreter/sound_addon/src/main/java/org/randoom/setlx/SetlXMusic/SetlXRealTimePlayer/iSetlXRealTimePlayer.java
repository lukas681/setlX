package org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer;

import org.jfugue.pattern.Atom;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.theory.Note;

/**
 *
 * This players allows playing tones in Real Time without any preprocessing.
 */

public interface iSetlXRealTimePlayer {

    void startPlayer();

    void stopPlayer();

    /**
     * simply plays a musical pattern. This pattern is accessing to the global properties of the
     * iSetlXRealTimePlayer
     * @param pattern
     */
    void play(PatternProducer pattern);

    void play(Atom musicalUnit);

    void play(byte voice, byte layer, byte instrument, Note note);

    void play(byte voice, byte layer, byte instrument, int value, double duration);


    void changeInstrument(int instrument);

    void setNoteDuration(int duration);

}
