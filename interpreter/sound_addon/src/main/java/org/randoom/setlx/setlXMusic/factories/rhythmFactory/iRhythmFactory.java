package org.randoom.setlx.setlXMusic.factories.rhythmFactory;

import org.jfugue.rhythm.Rhythm;

public interface iRhythmFactory {
    /**
     * Creates a new Rhythm Pattern
     *
     * @param rhythmPattern
     */
    Rhythm createRhythm(String rhythmPattern);
}
