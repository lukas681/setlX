package org.randoom.setlx.setlXMusic.musicSystem.exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class ProducerNotSupportedException extends CatchableInSetlXException {
    public ProducerNotSupportedException() {
        super("Your PatternProducer is not supported by the Manager. Only Instances of Rhythmic, Chordprogression and Pattern-classes are allowed.");
    }
}
