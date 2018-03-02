package org.randoom.setlx.SetlXMusic.Factories.RhythmFactory;

import org.jfugue.rhythm.Rhythm;

public class RhythmFactory implements iRhythmFactory {

    @Override
    public Rhythm createRhythm(String rhythmPattern) {
        return new Rhythm(rhythmPattern);
    }
}
