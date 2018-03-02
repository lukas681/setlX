package org.randoom.setlx.SetlXMusic.factories.ChordProgressionFactory;

import org.jfugue.theory.ChordProgression;
import org.randoom.setlx.SetlXMusic.factories.Exceptions.KeyNotAcceptedException;

public class ChordProgressionFactory implements iChordProgressionFactory {

    @Override
    public ChordProgression createChordProgression(String chordProgression, String key) throws KeyNotAcceptedException {

        if (!checkKey(key)) { //Checks weather the given base key is allowed
            throw new KeyNotAcceptedException();
        }
        return new ChordProgression(chordProgression).setKey(key);
    }

    /**
     * Returns true, iff the given key is correct. Only "real" keys are allowed.
     *
     * @param key
     * @return
     */
    public boolean checkKey(String key) {
        return key.matches("[a-gA-G][#b]?");
    }
}
