package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.Player;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.ProducerNotFoundExceptions.PatternNotFoundException;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.iSetlXMusicManager;

import java.util.ArrayList;

/**
 * Created by Lukas on 28.12.2017.
 */
public class SetlXMusicPlayer implements iSetlXMusicPlayer {
    iSetlXMusicManager musicSource;
    Player player;

    public SetlXMusicPlayer(iSetlXMusicManager musicSource) {
        this.musicSource = musicSource;
        player = new Player(); //Initialized the first player
    }

    @Override
    public void play(String... patternNames) throws PatternNotFoundException {
        ArrayList<PatternProducer> temp = new ArrayList<>();
        for (String name : patternNames) {
            switch(musicSource.getStorageWhereKeyIsUsed(name)){
                case PATTERN_STORAGE:
                        temp.add(musicSource.getPattern(name));
                        break;
                case RHYTHM_STORAGE:
                        temp.add(musicSource.getRhythm(name));
                        break;
                case CHORD_PROGRESSION_STORAGE:
                        temp.add(musicSource.getChordProgression(name));
                        break;
            }
        }
        player.play(temp.toArray(new PatternProducer[temp.size()]));
        player = new Player(); //TODO A bug, that is delaying the start of new Patterns. Keep track of future updates of the framework
    }
}
