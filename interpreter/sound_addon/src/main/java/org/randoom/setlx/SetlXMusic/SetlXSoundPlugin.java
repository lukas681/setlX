package org.randoom.setlx.SetlXMusic;

import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.SetlXMusicManager;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.iSetlXMusicManager;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.SetlXMusicPlayer;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.iSetlXMusicPlayer;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.Exceptions.SetlXMidiNotAvailableException;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.SetlXRealTimePlayer;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.iSetlXRealTimePlayer;
import org.randoom.setlx.SetlXMusic.factories.*;

public class SetlXSoundPlugin implements iSetlXSoundPlugin {

    private static SetlXSoundPlugin setlxSoundPlugin;

    private iSetlXMusicPlayer musicPlayer;
    private iSetlXMusicManager patternManager;
    private iSetlXRealTimePlayer realTimePlayer;

    // Factories TODO Sum up factories
    private iAtomFactory atomFactory;
    private iNoteFactory noteFactory;
    private iChordProgressionFactory chordProgressionFactory;
    private iPatternFactory patternFactory;

    private SetlXSoundPlugin() throws SetlXMidiNotAvailableException { //TODO SetlXMidiNotAvailable lassen?
        initializeComponents();
    }

    /**
     * Initializes all components: Instantiates the Factories, Players and Storages
     */
    private void initializeComponents() throws SetlXMidiNotAvailableException {

        patternManager = new SetlXMusicManager();
        musicPlayer = new SetlXMusicPlayer(patternManager);
        realTimePlayer = new SetlXRealTimePlayer();

        atomFactory = new AtomFactory();
        noteFactory = new NoteFactory();
        chordProgressionFactory = new ChordProgressionFactory();
        patternFactory = new PatternFactory();
    }

    @Override
    public iSetlXMusicManager getSetlXPatternManager() {
        return patternManager;
    }

    @Override
    public iSetlXMusicPlayer getSetlxMusicPlayer() {
        return musicPlayer;
    }

    @Override
    public iSetlXRealTimePlayer getSetlXRealTimePlayer() {
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

    public static SetlXSoundPlugin getInstance() {
        if (setlxSoundPlugin == null) { //Singleton
            try {
                setlxSoundPlugin = new SetlXSoundPlugin();
            } catch (SetlXMidiNotAvailableException e) {
                e.printStackTrace();
            }
        }
        return setlxSoundPlugin;
    }
}
