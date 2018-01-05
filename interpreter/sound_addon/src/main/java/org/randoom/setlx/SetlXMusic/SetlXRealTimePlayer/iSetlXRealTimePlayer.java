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

    void play(PatternProducer pattern);

    void play(Atom musicalUnit);

    void changeInstrument(int instrument);

    void setNoteDuration(int duration);

}
