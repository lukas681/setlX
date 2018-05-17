package org.randoom.setlx.setlXMusic.musicSystem.exceptions.MidiExceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;


public class InvalidMidiDataException extends CatchableInSetlXException {
    public InvalidMidiDataException() {
        super("The midi Data you want to load is invalid!");
    } //TODO examine, when this can happen
}
