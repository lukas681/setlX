package org.randoom.setlx.setlXMusic.factories.patternFactory;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.setlXMusic.factories.exceptions.PatternValueOutOfRangeException;

public interface iPatternFactory {

    Pattern createPattern(String pattern, int instrument, int tempo, int voice) throws PatternValueOutOfRangeException;

    Pattern createPattern(String pattern, int instrument, int tempo) throws PatternValueOutOfRangeException;

    Pattern createPattern(String pattern, int instrument) throws PatternValueOutOfRangeException;

    Pattern createPattern(String pattern);
}
