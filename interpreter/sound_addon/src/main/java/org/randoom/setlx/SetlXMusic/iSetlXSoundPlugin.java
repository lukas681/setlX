package org.randoom.setlx.SetlXMusic;

import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.iSetlXMusicManager;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.iSetlXMusicPlayer;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.iSetlXRealTimePlayer;
import org.randoom.setlx.SetlXMusic.factories.AtomFactory.iAtomFactory;
import org.randoom.setlx.SetlXMusic.factories.ChordProgressionFactory.iChordProgressionFactory;
import org.randoom.setlx.SetlXMusic.factories.NoteFactory.iNoteFactory;
import org.randoom.setlx.SetlXMusic.factories.PatternFactory.iPatternFactory;
import org.randoom.setlx.SetlXMusic.factories.RhythmFactory.iRhythmFactory;

/**
 * provides access to every components of the sound plugin
 * It is central entrypoint for all functionalities
 */
public interface iSetlXSoundPlugin {

    /**
     * Returns the instance of the Music Manger
     *
     * @return
     */
    iSetlXMusicManager getSetlXPatternManager();

    /**
     * returns the instance of the music player
     *
     * @return
     */
    iSetlXMusicPlayer getSetlxMusicPlayer();

    /**
     * returns the instance of the real time player, that can be used
     * for playing tones in real time without preprocessing.
     */
    iSetlXRealTimePlayer getSetlXRealTimePlayer();


    /**
     * Returns a Atom Factory
     * @return Atom Factory
     */
    iAtomFactory getAtomFactory();

    /**
     * Returns a Pattern Factory
     * @return
     */
    iPatternFactory getPatternFactoy();

    /**
     * Returns a Chord Progression Factory
     * @return
     */
    iChordProgressionFactory getChordProgressionFactory();

    /**
     * Returns a Note Factory
     * @return
     */
    iNoteFactory getNoteFactory();

    /**
     * Returns a Rhythm Factory
     * @return
     */
    iRhythmFactory getRhythmFactory();

}
