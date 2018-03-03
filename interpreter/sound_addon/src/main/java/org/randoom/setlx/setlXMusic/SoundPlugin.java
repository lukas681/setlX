package org.randoom.setlx.setlXMusic;

import org.randoom.setlx.setlXMusic.musicPlayer.MusicPlayer;
import org.randoom.setlx.setlXMusic.musicPlayer.iMusicPlayer;
import org.randoom.setlx.setlXMusic.musicSystem.musicManager.MusicManager;
import org.randoom.setlx.setlXMusic.musicSystem.musicManager.iMusicManager;
import org.randoom.setlx.setlXMusic.realTimeSystem.Exceptions.MidiNotAvailableException;
import org.randoom.setlx.setlXMusic.realTimeSystem.*;
import org.randoom.setlx.setlXMusic.factories.atomFactory.*;
import org.randoom.setlx.setlXMusic.factories.chordProgressionFactory.*;
import org.randoom.setlx.setlXMusic.factories.noteFactory.*;
import org.randoom.setlx.setlXMusic.factories.patternFactory.*;
import org.randoom.setlx.setlXMusic.factories.rhythmFactory.*;

/**
 * This is the main class for the SetlX Soundplugin.
 * It is a singleton on which every SetlX Pre-Defined function has access to.
 */
public class SoundPlugin implements iSoundPlugin {

    private static SoundPlugin setlxSoundPlugin;

    private iMusicPlayer musicPlayer;
    private iMusicManager musicManager;
    private iRealTimePlayer realTimePlayer;

    // factories TODO Sum up factories
    private iAtomFactory atomFactory;
    private iNoteFactory noteFactory;
    private iChordProgressionFactory chordProgressionFactory;
    private iPatternFactory patternFactory;
    private iRhythmFactory rhythmFactory;

    private SoundPlugin() throws MidiNotAvailableException { //TODO SetlXMidiNotAvailable lassen?
        initializeComponents();
    }

    /**
     * Initializes all components: Instantiates the factories, Players and storage
     */
    private void initializeComponents() throws MidiNotAvailableException {

        // Initializes factories
        atomFactory = new AtomFactory();
        noteFactory = new NoteFactory();
        chordProgressionFactory = new ChordProgressionFactory();
        patternFactory = new PatternFactory();
        rhythmFactory = new RhythmFactory();

        musicManager = new MusicManager();
        musicPlayer = new MusicPlayer(musicManager);
        realTimePlayer = new RealTimerPlayer(noteFactory, atomFactory);

    }

    @Override
    public iMusicManager getMusicManager() {
        return musicManager;
    }

    @Override
    public iMusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    @Override
    public iRealTimePlayer getRealTimePlayer() {
        return realTimePlayer;
    }

    @Override
    public iAtomFactory getAtomFactory() {
        return atomFactory;
    }

    @Override
    public iPatternFactory getPatternFactoy() {
        return patternFactory;
    }

    @Override
    public iChordProgressionFactory getChordProgressionFactory() {
        return chordProgressionFactory;
    }

    @Override
    public iNoteFactory getNoteFactory() {
        return noteFactory;
    }

    @Override
    public iRhythmFactory getRhythmFactory() {
        return rhythmFactory;
    }

    /**
     * This static method creates exactly one instance of this {@link SoundPlugin}.
     *
     * @return the only instance of {@link SoundPlugin}
     */
    public static SoundPlugin getInstance() {
        if (setlxSoundPlugin == null) { //Singleton
            try {
                setlxSoundPlugin = new SoundPlugin();
            } catch (MidiNotAvailableException e) {
                e.printStackTrace();
            }
        }
        return setlxSoundPlugin;
    }
}
