package org.randoom.setlx.exceptions;

public class NotInByteRangeException extends CatchableInSetlXException {
    public NotInByteRangeException() {
        super("The value you have entered is out of range!");
    }
}
