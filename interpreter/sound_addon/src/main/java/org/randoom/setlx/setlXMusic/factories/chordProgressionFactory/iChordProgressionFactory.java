package org.randoom.setlx.setlXMusic.factories.chordProgressionFactory;

import org.jfugue.theory.ChordProgression;
import org.randoom.setlx.setlXMusic.factories.exceptions.KeyNotAcceptedException;

public interface iChordProgressionFactory {
    /**
     * Creates a new Chord Progression Pattern
     *
     * @param chordProgression
     */
    ChordProgression createChordProgression(String chordProgression, String key) throws KeyNotAcceptedException;
}
