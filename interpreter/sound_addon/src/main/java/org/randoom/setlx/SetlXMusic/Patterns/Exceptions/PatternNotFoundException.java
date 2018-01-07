package org.randoom.setlx.SetlXMusic.Patterns.Exceptions;

        import org.randoom.setlx.exceptions.CatchableInSetlXException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class PatternNotFoundException extends CatchableInSetlXException {
    public PatternNotFoundException() {
        super("The nusical pattern you wanted to look up could not be found in all currently saved patterns.");
    }
}
