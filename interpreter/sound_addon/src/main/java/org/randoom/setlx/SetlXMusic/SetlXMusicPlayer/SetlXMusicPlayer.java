package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.PatternNotFoundException;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.iSetlXPatternManager;

import java.util.ArrayList;

/**
 * Created by Lukas on 28.12.2017.
 */
public class SetlXMusicPlayer implements iSetlXMusicPlayer {
    iSetlXPatternManager musicSource;
    Player player;

    public SetlXMusicPlayer(iSetlXPatternManager musicSource) {
        this.musicSource = musicSource;
        player = new Player(); //Initialized the first player
    }

    @Override
    public void play(String... patternNames) throws PatternNotFoundException {
        ArrayList<Pattern> temp = new ArrayList<>();
        for (String name : patternNames) {
                temp.add(musicSource.getPattern(name));
        }
        player.play(temp.toArray(new Pattern[temp.size()]));
        player = new Player(); //TODO A bug, that is delaying the start of new Patterns. Keep track of future updates of the framework
    }
}
