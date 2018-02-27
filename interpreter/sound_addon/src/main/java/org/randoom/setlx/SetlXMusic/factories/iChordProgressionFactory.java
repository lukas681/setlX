package org.randoom.setlx.SetlXMusic.factories;

import org.jfugue.theory.ChordProgression;
import org.randoom.setlx.SetlXMusic.factories.Exceptions.KeyNotAcceptedException;

public interface iChordProgressionFactory {
    /**
     * Creates a new Chord Progression Pattern
     * @param chordProgression
     */
    ChordProgression createChordProgression(String chordProgression, String key) throws KeyNotAcceptedException;
}
