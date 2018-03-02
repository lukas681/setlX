package org.randoom.setlx.SetlXMusic.Patterns.Exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

import java.security.Key;

public class KeyAlreadyInUseException extends CatchableInSetlXException {
    public KeyAlreadyInUseException() {
        super("The key/name you want to use is already in use!");
    }
}
