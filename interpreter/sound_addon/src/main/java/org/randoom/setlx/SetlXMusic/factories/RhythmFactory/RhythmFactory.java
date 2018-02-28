package org.randoom.setlx.SetlXMusic.factories.RhythmFactory;

import org.jfugue.rhythm.Rhythm;

public class RhythmFactory implements iRhythmFactory {

    @Override
    public Rhythm createRhythm(String rhythmPattern) {
        return new Rhythm(rhythmPattern);
    }
}
