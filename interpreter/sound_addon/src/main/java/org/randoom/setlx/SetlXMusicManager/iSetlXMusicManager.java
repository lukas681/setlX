package org.randoom.setlx.SetlXMusicManager;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.SetlXMusicManager.Patterns.PatternParameters;

/**
 * This Manager holds and manages patterns, that can be created by setlx.
 * It is also possible to modify an edit the patterns.
 */
public interface iSetlXMusicManager {
    void addPattern(String name, Pattern pattern);

    void addToPattern(String patternName, String notePattern);

    void modifyPatternProperty(String patternName, PatternParameters param, int value);

    void removePattern(String patternName);
}
