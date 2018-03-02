package org.randoom.setlx.SetlXMusic.MusicSystem.Storages;

import org.jfugue.pattern.PatternProducer;
import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.NullArgumentsException;
import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.ProducerNotFoundExceptions.PatternNotFoundException;

import java.util.HashMap;

/**
 *
 */
public class SetlXMusicStorage<T extends PatternProducer> implements iSetlXMusicStorage<T> {

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
    public T getElement(String name) throws PatternNotFoundException { //TODO Really Pattern not found? consolidate the exceptions
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
