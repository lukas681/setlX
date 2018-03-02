package org.randoom.setlx.SetlXMusic.Patterns.Exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class BasicPatternProducerNotFoundException extends CatchableInSetlXException {
    public BasicPatternProducerNotFoundException(String classname) {
        super("The musical " + classname + " could not be found in the storage");
    }
}
