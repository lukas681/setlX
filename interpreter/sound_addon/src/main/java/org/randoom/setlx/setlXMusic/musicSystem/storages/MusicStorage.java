package org.randoom.setlx.setlXMusic.musicSystem.storages;

import org.jfugue.pattern.PatternProducer;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.NullArgumentsException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.ProducerNotFoundExceptions.PatternNotFoundException;

import java.util.HashMap;

/**
 * Stores a set of Type Pattern producer
 */
public class MusicStorage<T extends PatternProducer> implements iMusicStorage<T> {

    HashMap<String, T> patternStorage = new HashMap<>();

    @Override
    public void addElement(String name, T element) throws NullArgumentsException {
        if (name != null && element != null) {
            patternStorage.put(name, element);
        } else {
            throw new NullArgumentsException();
        }
    }

    @Override
    public boolean checkExisting(String name) {
        return patternStorage.containsKey(name);
    }

    @Override
    public T getElement(String name) throws PatternNotFoundException {
        if (!checkExisting(name)) {
            throw new PatternNotFoundException();
        }
        return patternStorage.get(name);
    }

    @Override
    public void deleteElement(String name) {
        patternStorage.remove(name);
    }

    @Override
    public HashMap<String, T> getAllElements() {
        return patternStorage;
    }

}
