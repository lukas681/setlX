package org.randoom.setlx.Exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class NotInByteRangeException extends CatchableInSetlXException {
    public NotInByteRangeException() {
        super("The value you have entered is out of range!");
    }
}
