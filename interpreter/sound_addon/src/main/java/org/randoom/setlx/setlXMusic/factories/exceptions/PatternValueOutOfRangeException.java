package org.randoom.setlx.SetlXMusic.Factories.Exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class PatternValueOutOfRangeException extends CatchableInSetlXException {
    public PatternValueOutOfRangeException() {
        super("One of the values for the pattern is not allowed.\n" +
                "0 < tempo <= 300\n" +
                "0 <= voice <= 16\n" +
                "0 <= instrument <= 300");
    }
}
