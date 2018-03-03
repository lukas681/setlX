package org.randoom.setlx.setlXMusic.musicSystem.exceptions.ProducerNotFoundExceptions;

import org.randoom.setlx.setlXMusic.musicSystem.exceptions.BasicPatternProducerNotFoundException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class ChordProgressionNotFoundException extends BasicPatternProducerNotFoundException {
    public ChordProgressionNotFoundException() {
        super("Chord");
    }
}
