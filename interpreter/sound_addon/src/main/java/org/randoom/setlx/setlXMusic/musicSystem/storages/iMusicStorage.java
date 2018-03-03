package org.randoom.setlx.SetlXMusic.MusicSystem.Storages;

import org.jfugue.pattern.PatternProducer;
import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.NullArgumentsException;
import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.ProducerNotFoundExceptions.PatternNotFoundException;

import java.util.HashMap;

/**
 * Created by Lukas on 28.12.2017.
 */
public interface iMusicStorage<T extends PatternProducer> {


    /**
     * Adds a new musical element to this storage, Musical Elements can be for example
     * {@link org.jfugue.pattern.Pattern}s, {@link org.jfugue.theory.ChordProgression}s and so on.
     * A name MUST be specified in order to later identify the specific element.
     *
     * @param name
     * @param element
     */
    void addElement(String name, T element) throws NullArgumentsException;


    /**
     * Checks, if there is a element with this name in the storage.
     * Can be used to check possible collisions
     *
     * @param name
     * @return
     */
    boolean checkExisting(String name);


    /**
     * Returns the element, that belongs to a specific symbolic name from storage
     *
     * @param name
     * @return
     */
    T getElement(String name) throws PatternNotFoundException;

    /**
     * Allows to remove a Element from the storage by using a symbolic name
     *
     * @param name
     */
    void deleteElement(String name) throws PatternNotFoundException;

    /**
     * Returns all patterns, that are stored in this storage
     *
     * @return
     */
    HashMap<String, T> getAllElements();
}
