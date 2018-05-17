package org.randoom.setlx.exceptions;

public class ParameterNotFoundException extends CatchableInSetlXException {
    public ParameterNotFoundException() {
        super("The musical property you have entered does not exist.");
    }
}
