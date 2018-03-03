package org.randoom.setlx.setlXMusic.musicSystem.storages;

import org.jfugue.pattern.PatternProducer;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.NullArgumentsException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.ProducerNotFoundExceptions.PatternNotFoundException;

import java.util.HashMap;

/**
 * Created by Lukas on 28.12.2017.
 */
public interface iMusicStorage<T extends PatternProducer> {


    /**
     * Adds a new musical element to this storage.
     * Musical Elements can be for example
     * {@link org.jfugue.pattern.Pattern}s, {@link org.jfugue.theory.ChordProgression}s or {@link org.jfugue.rhythm.Rhythm}s
     * A name must be specified in order to find the specific element again.
     *
     * @param name The name of the element
     * @param element
     */
    void addElement(String name, T element) throws NullArgumentsException;


    /**
     * Checks, if there is an element with that exact name in the storage.
     * Can be for example used to check existence before inserting new elements.
     *
     * @param name
     * @return
     */
    boolean checkExisting(String name);


    /**
     * Returns the element, that belongs to a specific symbolic name from storage.
     *
     * @param name
     * @return
     */
    T getElement(String name) throws PatternNotFoundException;

    /**
     * Allows to remove an element from the storage by using a symbolic name.
     *
     * @param name
     */
    void deleteElement(String name) throws PatternNotFoundException;

    /**
     * Returns all patterns, that are stored in this storage.
     *
     * @return
     */
    HashMap<String, T> getAllElements();
}
