package org.randoom.setlx.SetlXMusic.Factories.PatternFactory;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.SetlXMusic.Factories.Exceptions.PatternValueOutOfRangeException;

public interface iPatternFactory {

    Pattern createPattern(String pattern, int instrument, int tempo, int voice) throws PatternValueOutOfRangeException;

    Pattern createPattern(String pattern, int instrument, int tempo) throws PatternValueOutOfRangeException;

    Pattern createPattern(String pattern, int instrument) throws PatternValueOutOfRangeException;

    Pattern createPattern(String pattern);
}
