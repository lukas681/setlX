package org.randoom.setlx.setlXMusic.musicSystem.exceptions.MidiExceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;


public class SetlXIOException extends CatchableInSetlXException {
    public SetlXIOException() {
        super("An IO-Exception has occured.");
    }
}
