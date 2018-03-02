package org.randoom.setlx.SetlXMusic.Factories.ChordProgressionFactory;

import org.jfugue.theory.ChordProgression;
import org.randoom.setlx.SetlXMusic.Factories.Exceptions.KeyNotAcceptedException;

public interface iChordProgressionFactory {
    /**
     * Creates a new Chord Progression Pattern
     *
     * @param chordProgression
     */
    ChordProgression createChordProgression(String chordProgression, String key) throws KeyNotAcceptedException;
}
