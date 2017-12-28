package org.randoom.setlx.SetlXMusic.Patterns;

import org.jfugue.pattern.Pattern;

/**
 * Created by Lukas on 28.12.2017.
 */
public interface iPatternStorage {



    /**
     * Adds a new paatern to the pattern storage.
     * A name MUST be specified in order to later identify the specific pattern.
     * @param name
     * @param pattern
     */
    void addPattern(String name, Pattern pattern);

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
    Pattern getPattern(String name);

    /**
     * Allows to remove a pattern from the storage by using a symbolic name
     * @param name
     */
    void deletePattern(String name);

}
