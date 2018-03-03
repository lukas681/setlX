package org.randoom.setlx.setlXMusic.factories.exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class KeyNotAcceptedException extends CatchableInSetlXException {
    public KeyNotAcceptedException() {
        super("The base key you want to use does not exists. Use B instead of an H and #b for flat/sharp notes.\n" +
                "The allowed regex is [b-a][#b]?");
    }
}
