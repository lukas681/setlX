package org.randoom.setlx.SetlXMusic.Patterns.Exceptions.ProducerNotFoundExceptions;

import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.BasicPatternProducerNotFoundException;
import org.randoom.setlx.exceptions.CatchableInSetlXException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class PatternNotFoundException extends BasicPatternProducerNotFoundException {
    public PatternNotFoundException() {
        super("Pattern");
    }
}
