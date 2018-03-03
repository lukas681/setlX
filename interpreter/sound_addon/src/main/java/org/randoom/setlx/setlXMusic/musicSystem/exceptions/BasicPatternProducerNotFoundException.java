package org.randoom.setlx.setlXMusic.musicSystem.exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class BasicPatternProducerNotFoundException extends CatchableInSetlXException {
    public BasicPatternProducerNotFoundException(String classname) {
        super("The musical " + classname + " could not be found in the storage");
    }
}
