package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.randoom.setlx.SetlXMusic.SetlXMusicManager.SetlXMusicManager;
import org.randoom.setlx.SetlXMusic.SetlXMusicManager.iSetlXMusicManager;

import java.util.ArrayList;

/**
 * Created by Lukas on 28.12.2017.
 */
public class SetlXMusicPlayer implements iSetlXMusicPlayer{
    iSetlXMusicManager musicSource;
    Player player = new Player();

    public SetlXMusicPlayer(iSetlXMusicManager musicSource) {
        this.musicSource = musicSource;
    }

    @Override
    public void play(String... patternNames) {
        ArrayList<Pattern> temp = new ArrayList<>();
        for(String name: patternNames){
            temp.add(musicSource.getPattern(name));
        }
        player.play(temp.toArray(new Pattern[patternNames.length]));
    }

    public static void main(String[] args) {
            iSetlXMusicManager m = new SetlXMusicManager();
        m.addPattern("Test1", new Pattern("A B"));
        m.addPattern("Test2", new Pattern("B C"));
        iSetlXMusicPlayer pl = new SetlXMusicPlayer(m);
        pl.play("Test2", "Test1");
        pl.play("Testz");
    }
}
