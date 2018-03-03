package org.randoom.setlx.setlXMusic.musicSystem.exceptions.MidiExceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;


public class NotAPatternException extends CatchableInSetlXException {
    public NotAPatternException() {
        super("You are just allowed to save Patterns to midi files. You might probably have to convert your Rhythm/Chord Progression patterns");
    }
}
