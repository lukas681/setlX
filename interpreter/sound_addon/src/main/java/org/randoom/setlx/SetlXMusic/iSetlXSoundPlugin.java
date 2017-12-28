package org.randoom.setlx;

import org.randoom.setlx.SetlXMusic.SetlXPatternManager.iSetlXPatternManager;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.iSetlXMusicPlayer;
import org.randoom.setlx.SetlXRealTimePlayer.iSetlXRealTimePlayer;

/**
 * provides access to every components of the sound plugin
 * It is central entrypoint for all functionalities
 */
public interface iSetlXSoundPlugin {

    /**
     * Returns the instance of the Music Manger
     * @return
     */
    iSetlXPatternManager getSetlXMusicManager();

    /**
     * returns the instance of the music player
     * @return
     */
    iSetlXMusicPlayer getSetlxMusicPlayer();

    /**
     *  returns the instance of the real time player, that can be used
     *  for playing tones in real time without preprocessing.
     */
    iSetlXRealTimePlayer getSetlXRealTimePlayer();
}
