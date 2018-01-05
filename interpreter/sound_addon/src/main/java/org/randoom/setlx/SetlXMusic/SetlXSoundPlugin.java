package org.randoom.setlx.SetlXMusic;

import org.randoom.setlx.SetlXMusic.SetlXPatternManager.SetlXPatternManager;
import org.randoom.setlx.SetlXMusic.SetlXPatternManager.iSetlXPatternManager;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.SetlXMusicPlayer;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.iSetlXMusicPlayer;

import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.SetlXRealTimePlayer;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.iSetlXRealTimePlayer;
import org.randoom.setlx.SetlXMusic.factories.AtomFactory;
import org.randoom.setlx.SetlXMusic.factories.NoteFactory;

public class SetlXSoundPlugin implements iSetlXSoundPlugin {

    private static SetlXSoundPlugin setlxSoundPlugin;

    private iSetlXMusicPlayer musicPlayer;
    private iSetlXPatternManager patternManager;
    private iSetlXRealTimePlayer realTimePlayer;

    private AtomFactory atomFactory;
    public NoteFactory noteFactory;

    private SetlXSoundPlugin(){
        initializeComponents();
    }

    /**
     * Initializes all components: Creates new instances.
      */
    private void initializeComponents(){

        patternManager = new SetlXPatternManager();
        musicPlayer = new SetlXMusicPlayer(patternManager);
        realTimePlayer = new SetlXRealTimePlayer();
        atomFactory = new AtomFactory();
        noteFactory = new NoteFactory();
    }
    @Override
    public iSetlXPatternManager getSetlXPatternManager() {
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

    public static SetlXSoundPlugin getInstance(){
        if(setlxSoundPlugin == null){ //Singleton
            setlxSoundPlugin = new SetlXSoundPlugin();
        }
        return setlxSoundPlugin;
    }
}
