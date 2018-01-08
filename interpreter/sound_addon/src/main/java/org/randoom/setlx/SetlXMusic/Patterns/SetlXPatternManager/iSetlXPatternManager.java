package org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager;

import org.jfugue.pattern.Pattern;
import org.jfugue.tools.GetPatternStats;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.NullArgumentsException;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.PatternNotFoundException;
import org.randoom.setlx.SetlXMusic.Patterns.PatternParameters;
import org.randoom.setlx.exceptions.SetlException;

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
    void addPattern(String name, Pattern pattern) throws NullArgumentsException;

    /**
     * Can be used to add notes to an existing pattern
     * @param patternName
     * @param notePattern
     */
    void addToPattern(String patternName, String notePattern) throws PatternNotFoundException;

    /**
     * Can be used to add notes to an existing pattern and specify, how often it should be repeated.
     * @param patternName
     * @param notePattern
     * @param repetitions
     */
    void addToPattern(String patternName, String notePattern, int repetitions) throws PatternNotFoundException;

    /**
     * Modifies a porperty of a pattern.
     *
     * @param patternName
     * @param param
     * @param value
     */
    void modifyPatternProperty(String patternName, PatternParameters param, int value) throws SetlException;

    /**
     * Deletes a pattern from this music manager
     * @param patternName
     */
    void removePattern(String patternName) throws PatternNotFoundException;

    /**
     * Returns a specific pattern from the storage
     * @param patternName
     * @return
     */
    Pattern getPattern(String patternName) throws PatternNotFoundException;

    HashMap<String, Pattern> getAllPatterns();

    /**
     * Duplicates an existing pattern. It might loose explicit set settings.
     * There are many usecases for this:
     *  1) A pattern is used in different voices at the same time
     *  2) A pattern is transposed. This is useful, when making chords or just play the melody in an
     *  other tonality.
     *  @param newName The name of the duplicate pattern
     */
    void duplicatePattern(String sourceName, String newName) throws PatternNotFoundException, NullArgumentsException;

    /**
     * Returns a Hashmap containing detailed statistics for a pattern. This includes
     * Harmonic, Interval, Pitch, Rest, Duration and Rythmic N, Average, SD and Range.
     * @param patternName
     * @return
     * @throws PatternNotFoundException
     */
    HashMap<String, GetPatternStats.Stats> getDetailPatternStats(String patternName) throws PatternNotFoundException;

    /**
     * Returns general statistics for a pattern. These basic metrics include number of notes, rests and measures
     * @param patternName
     * @return Array index 0: N of Notes; index 1: N of rests; index 2: N of measures
     * @throws PatternNotFoundException
     */
    int[] getGeneralPatternStats(String patternName) throws PatternNotFoundException;
}
