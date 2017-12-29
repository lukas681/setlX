package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.SetlXMusic.SetlXPatternManager.SetlXPatternManager;
import org.randoom.setlx.SetlXMusic.SetlXPatternManager.iSetlXPatternManager;

public class SetlXMusicPlayerTest {
    iSetlXMusicPlayer player;
    iSetlXPatternManager pat = new SetlXPatternManager();
    @Before
    public void setUp() throws Exception {
        pat.addPattern("Test",new Pattern("A B C"));
        player = new SetlXMusicPlayer(pat);
    }

    @Test
    public void play() throws Exception {

        //How to test this class?
        //player.play("Test");

    }

}