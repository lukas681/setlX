package org.randoom.setlx.setlXMusic.musicSystem.exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class CanNotConvertException extends CatchableInSetlXException {
    public CanNotConvertException() {
        super("It is not possible to convert the element. Maybe it is already a pattern, or not registered in Rhythm or Chord Progression list?");
    }
}
