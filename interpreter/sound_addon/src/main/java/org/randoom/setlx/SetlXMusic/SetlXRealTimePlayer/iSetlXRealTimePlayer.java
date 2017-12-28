package org.randoom.setlx.SetlXRealTimePlayer;

import org.jfugue.pattern.PatternProducer;
import org.jfugue.theory.Note;

/**
 *
 * This players allows playing tones in Real Time without any preprocessing.
 */

public interface iSetlXRealTimePlayer {
    boolean isRunning = false;

    void startPlayer();

    void stopPlayer();

    void play(PatternProducer pattern);

    void play(Note note);

    void changeInstrument(int instrument);

    void setNoteDuration(int duration);

}
