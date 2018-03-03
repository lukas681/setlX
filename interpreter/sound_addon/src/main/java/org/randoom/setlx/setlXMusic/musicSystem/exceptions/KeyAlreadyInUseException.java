package org.randoom.setlx.setlXMusic.musicSystem.exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class KeyAlreadyInUseException extends CatchableInSetlXException {
    public KeyAlreadyInUseException() {
        super("The key/name you want to use is already in use!");
    }
}
