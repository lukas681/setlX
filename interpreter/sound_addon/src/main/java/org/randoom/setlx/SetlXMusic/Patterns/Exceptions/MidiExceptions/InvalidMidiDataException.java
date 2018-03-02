package org.randoom.setlx.SetlXMusic.Patterns.Exceptions.MidiExceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;


public class InvalidMidiDataException extends CatchableInSetlXException {
    public InvalidMidiDataException(){
        super("The midi Data you want to load is invalid!");
    } //TODO examine, when this can happen
}
