package org.randoom.setlx.setlXMusic.realTimeSystem.Exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class MidiNotAvailableException extends CatchableInSetlXException {
    public MidiNotAvailableException() {
        super("The Midi device, that is being called can not be reached." +
                "Maybe the Global Synthesizer is not available.");
    }
}
