package org.randoom.setlx.setlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.setlXMusic.musicPlayer.MusicPlayer;
import org.randoom.setlx.setlXMusic.musicPlayer.iMusicPlayer;
import org.randoom.setlx.setlXMusic.musicSystem.musicManager.MusicManager;
import org.randoom.setlx.setlXMusic.musicSystem.musicManager.iMusicManager;

public class SetlXMusicPlayerTest {
    iMusicPlayer player;
    iMusicManager pat = new MusicManager();
    @Before
    public void setUp() throws Exception {
        pat.add("Test",new Pattern("A B C"));
        player = new MusicPlayer(pat);
    }

    @Test
    public void play() throws Exception {

        //How to test this class?
        //player.play("Test");

    }

}