package org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.ProducerNotFoundExceptions;

import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.BasicPatternProducerNotFoundException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class RhythmNotFoundException extends BasicPatternProducerNotFoundException {
    public RhythmNotFoundException() {
        super("Rhythm");
    }
}
