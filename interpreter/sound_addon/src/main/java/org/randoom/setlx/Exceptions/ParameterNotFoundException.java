package org.randoom.setlx.Exceptions;

import org.randoom.setlx.exceptions.CatchableInSetlXException;

public class ParameterNotFoundException extends CatchableInSetlXException {
    public ParameterNotFoundException() {
        super("The musical property you have entered does not exist.");
    }
}
