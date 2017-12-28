package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.randoom.setlx.SetlXMusic.SetlXPatternManager.SetlXPatternManager;
import org.randoom.setlx.SetlXMusic.SetlXPatternManager.iSetlXPatternManager;

import java.util.ArrayList;

/**
 * Created by Lukas on 28.12.2017.
 */
public class SetlXMusicPlayer implements iSetlXMusicPlayer{
    iSetlXPatternManager musicSource;
    Player player = new Player();

    public SetlXMusicPlayer(iSetlXPatternManager musicSource) {
        this.musicSource = musicSource;
    }

    @Override
    public void play(String... patternNames) {
        ArrayList<Pattern> temp = new ArrayList<>();
        for(String name: patternNames){
            temp.add(musicSource.getPattern(name));
        }
        // temp.stream().filter(p -> p != null).collect(Collectors.toList());
        player.play(temp.toArray(new Pattern[patternNames.length]));
    }

    public static void main(String[] args) {
            iSetlXPatternManager m = new SetlXPatternManager();
        m.addPattern("Test1", new Pattern("A B"));
        m.addPattern("Test2", new Pattern("B C"));
        iSetlXMusicPlayer pl = new SetlXMusicPlayer(m);
        pl.play("Test2", "Test1");
        pl.play("Testz");
    }
}
