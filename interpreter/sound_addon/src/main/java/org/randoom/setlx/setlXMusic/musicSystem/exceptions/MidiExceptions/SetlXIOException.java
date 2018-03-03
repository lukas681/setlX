package org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.MidiExceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;


public class SetlXIOException extends CatchableInSetlXException {
    public SetlXIOException() {
        super("An IO-Exception has occured.");
    }
}
