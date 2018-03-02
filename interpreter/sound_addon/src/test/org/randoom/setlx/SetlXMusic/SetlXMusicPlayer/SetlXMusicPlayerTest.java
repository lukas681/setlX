package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.SetlXMusic.MusicPlayer.MusicPlayer;
import org.randoom.setlx.SetlXMusic.MusicPlayer.iMusicPlayer;
import org.randoom.setlx.SetlXMusic.MusicSystem.MusicManager.MusicManager;
import org.randoom.setlx.SetlXMusic.MusicSystem.MusicManager.iMusicManager;

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