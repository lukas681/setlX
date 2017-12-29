package org.randoom.setlx.Exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class NullArgumentsException extends CatchableInSetlXException {
    public NullArgumentsException() {
        super("Some of your arguments had a null value, but are forbidden to be null");
    }
}
