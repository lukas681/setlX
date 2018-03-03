package org.randoom.setlx.setlXMusic;

import org.randoom.setlx.setlXMusic.musicPlayer.iMusicPlayer;
import org.randoom.setlx.setlXMusic.musicSystem.musicManager.iMusicManager;
import org.randoom.setlx.setlXMusic.realTimeSystem.iRealTimePlayer;
import org.randoom.setlx.setlXMusic.factories.atomFactory.iAtomFactory;
import org.randoom.setlx.setlXMusic.factories.chordProgressionFactory.iChordProgressionFactory;
import org.randoom.setlx.setlXMusic.factories.noteFactory.iNoteFactory;
import org.randoom.setlx.setlXMusic.factories.patternFactory.iPatternFactory;
import org.randoom.setlx.setlXMusic.factories.rhythmFactory.iRhythmFactory;

/**
 * Provides access to every component of the soundplugin.
 * It is the central entrypoint for all functionalities, that can be used by setlX.
 */
public interface iSoundPlugin {

    /**
     * Returns the instance of the Music Manger
     *
     * @return
     */
    iMusicManager getMusicManager();

    /**
     * returns the instance of the music player
     *
     * @return
     */
    iMusicPlayer getMusicPlayer();

    /**
     * returns the instance of the real time player, that can be used
     * for playing tones in real time without preprocessing.
     */
    iRealTimePlayer getRealTimePlayer();


    /**
     * Returns a Atom Factory
     *
     * @return Atom Factory
     */
    iAtomFactory getAtomFactory();

    /**
     * Returns a Pattern Factory
     *
     * @return
     */
    iPatternFactory getPatternFactoy();

    /**
     * Returns a Chord Progression Factory
     *
     * @return
     */
    iChordProgressionFactory getChordProgressionFactory();

    /**
     * Returns a Note Factory
     *
     * @return
     */
    iNoteFactory getNoteFactory();

    /**
     * Returns a Rhythm Factory
     *
     * @return
     */
    iRhythmFactory getRhythmFactory();

}
