package org.randoom.setlx.Exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class NotInByteRangeException extends CatchableInSetlXException {
    public NotInByteRangeException() {
        super("The value you have entered is not in the range of a Byte variable.");
    }
}
