package org.randoom.setlx.SetlXMusic.SetlXPatternManager;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.SetlXMusic.Patterns.PatternParameters;

import java.util.HashMap;

/**
 * This Manager holds and manages patterns, that can be created by setlx.
 * It is also possible to modify an edit the patterns.
 */
public interface iSetlXPatternManager {

    /**
     * Adds a new pattern to this Music Manager. It can then me used to build a song.
     * @param name
     * @param pattern
     */
    void addPattern(String name, Pattern pattern);

    /**
     * Can be used to add notes to an existing pattern
     * @param patternName
     * @param notePattern
     */
    void addToPattern(String patternName, String notePattern);

    /**
     * Can be used to add notes to an existing pattern and specify, how often it should be repeated.
     * @param patternName
     * @param notePattern
     * @param repetitions
     */
    void addToPattern(String patternName, String notePattern, int repetitions);

    /**
     * Modifies a porperty of a pattern.
     *
     * @param patternName
     * @param param
     * @param value
     */
    void modifyPatternProperty(String patternName, PatternParameters param, int value);

    /**
     * Deletes a pattern from this music manager
     * @param patternName
     */
    void removePattern(String patternName);

    /**
     * Returns a specific pattern from the storage
     * @param patternName
     * @return
     */
    Pattern getPattern(String patternName);

    HashMap<String, Pattern> getAllPatterns();
}
