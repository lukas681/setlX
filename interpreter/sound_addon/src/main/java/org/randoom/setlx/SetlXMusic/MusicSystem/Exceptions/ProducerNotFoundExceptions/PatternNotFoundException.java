package org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.ProducerNotFoundExceptions;

import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.BasicPatternProducerNotFoundException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class PatternNotFoundException extends BasicPatternProducerNotFoundException {
    public PatternNotFoundException() {
        super("Pattern");
    }
}
