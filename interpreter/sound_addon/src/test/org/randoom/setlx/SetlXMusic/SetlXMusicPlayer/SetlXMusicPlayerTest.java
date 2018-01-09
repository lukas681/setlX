package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.SetlXMusicManager;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.iSetlXMusicManager;

public class SetlXMusicPlayerTest {
    iSetlXMusicPlayer player;
    iSetlXMusicManager pat = new SetlXMusicManager();
    @Before
    public void setUp() throws Exception {
        pat.add("Test",new Pattern("A B C"));
        player = new SetlXMusicPlayer(pat);
    }

    @Test
    public void play() throws Exception {

        //How to test this class?
        //player.play("Test");

    }

}