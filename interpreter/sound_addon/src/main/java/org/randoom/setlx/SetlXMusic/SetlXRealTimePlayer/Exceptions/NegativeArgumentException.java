package org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.Exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class NegativeArgumentException extends CatchableInSetlXException{
    public NegativeArgumentException() {
        super("An argument was zero or negative, where negative values are not allowed.");
    }
}
