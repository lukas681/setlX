package org.randoom.setlx.SetlXMusic.Patterns;

import org.jfugue.pattern.Pattern;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.NullArgumentsException;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.PatternNotFoundException;

import java.util.HashMap;

/**
 * Created by Lukas on 28.12.2017.
 */
public interface iSetlXPatternStorage {



    /**
     * Adds a new paatern to the pattern storage.
     * A name MUST be specified in order to later identify the specific pattern.
     * @param name
     * @param pattern
     */
    void addPattern(String name, Pattern pattern) throws NullArgumentsException;

    /**
     * Checks, if there is also a registed pattern with this name.
     * Can be used to check possible collisions
     * @param name
     * @return
     */
    boolean checkExisting(String name);


    /**
     * returns the pattern, that belongs to a specific symbolic name from stroage storage
     * @param name
     * @return
     */
    Pattern getPattern(String name) throws PatternNotFoundException;

    /**
     * Allows to remove a pattern from the storage by using a symbolic name
     * @param name
     */
    void deletePattern(String name) throws PatternNotFoundException;

    /**
     * Returns all patterns, that are stored in this storage
     * @return
     */
    HashMap<String, Pattern> getAllPatterns();

}
