package org.randoom.setlx;

import org.randoom.setlx.SetlXMusic.SetlXPatternManager.SetlXPatternManager;
import org.randoom.setlx.SetlXMusic.SetlXPatternManager.iSetlXPatternManager;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.SetlXMusicPlayer;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.iSetlXMusicPlayer;

import org.randoom.setlx.SetlXRealTimePlayer.SetlXRealTimePlayer;
import org.randoom.setlx.SetlXRealTimePlayer.iSetlXRealTimePlayer;

public class SetlXMusic implements iSetlXMusic {

    private static SetlXMusic setlxMusic;

    private iSetlXMusicPlayer musicPlayer;
    private iSetlXPatternManager musicManager;
    private iSetlXRealTimePlayer realTimePlayer;

    private SetlXMusic(){
        initializeComponents();
    }

    /**
     * Initializes all components: Creates new instances.
      */
    private void initializeComponents(){

        musicManager = new SetlXPatternManager();
        musicPlayer = new SetlXMusicPlayer(musicManager);
        realTimePlayer = new SetlXRealTimePlayer();
    }
    @Override
    public iSetlXPatternManager getSetlXMusicManager() {
        return musicManager;
    }

    @Override
    public iSetlXMusicPlayer getSetlxMusicPlayer() {
        return musicPlayer;
    }

    @Override
    public iSetlXRealTimePlayer getSetlXRealTimePlayer() {
        return realTimePlayer;
    }

    public static SetlXMusic getInstance(){
        if(setlxMusic == null){ //Singleton
           setlxMusic = new SetlXMusic();
        }
        return setlxMusic;
    }
}
