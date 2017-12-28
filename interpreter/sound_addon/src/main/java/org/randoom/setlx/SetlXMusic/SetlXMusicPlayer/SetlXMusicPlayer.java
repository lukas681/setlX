package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.randoom.setlx.Exceptions.PatternNotFoundException;
import org.randoom.setlx.SetlXMusic.SetlXPatternManager.SetlXPatternManager;
import org.randoom.setlx.SetlXMusic.SetlXPatternManager.iSetlXPatternManager;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void play(String... patternNames) throws PatternNotFoundException {
        ArrayList<Pattern> temp = new ArrayList<>();
        for(String name: patternNames) {
            if (musicSource.getPattern(name) != null) { //Id the entry exists, than add it. TODO Maybe later: Catch exception, if pattern not found
                temp.add(musicSource.getPattern(name));
            }else{
                throw new PatternNotFoundException();
            }
        }
        player = new Player(); //TODO figure out, why the player is delaying. This is just a workaround.
        //I think, we have to reinstanceiate the player on every call, because it is not designed for a Real Time purpose.

        player.play(temp.toArray(new Pattern[temp.size()]));
    }


    public static void main(String[] args) throws InterruptedException {
            iSetlXPatternManager m = new SetlXPatternManager();
        m.addPattern("Test1", new Pattern("A B"));
        m.addPattern("Test2", new Pattern("B C"));
        iSetlXMusicPlayer pl = new SetlXMusicPlayer(m);
        try {
            pl.play("Test2", "Test1");
        } catch (PatternNotFoundException e) {
            e.printStackTrace();
        }
        Thread.sleep(5000);
        try {
            pl.play("Test2");
        } catch (PatternNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Tewat");
    }
}
