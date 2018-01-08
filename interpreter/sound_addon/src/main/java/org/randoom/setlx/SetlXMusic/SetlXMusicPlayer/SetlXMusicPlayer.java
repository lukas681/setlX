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
    Player str = new Player();

    public SetlXMusicPlayer(iSetlXPatternManager musicSource) {
        this.musicSource = musicSource;
        player = new Player(); //Initialized the first player
/*        player.getManagedPlayer().addManagedPlayerListener(new ManagedPlayerListener() {
            @Override
            public void onStarted(Sequence sequence) {
                System.out.println("Started");
            }

            @Override
            public void onFinished() {
                System.out.println("Finished");
            }

            @Override
            public void onPaused() {
                System.out.println("Paused");
            }

            @Override
            public void onResumed() {
                System.out.println("Resumed");
            }

            @Override
            public void onSeek(long l) {
                System.out.println("Seek" + l);
            }

            @Override
            public void onReset() {
                System.out.println("Reset");
            }
        });
*/
    }

    @Override
    public void play(String... patternNames) throws PatternNotFoundException {
        ArrayList<Pattern> temp = new ArrayList<>();
        for (String name : patternNames) {
            if (musicSource.getPattern(name) != null) { //Id the entry exists, than add it. TODO Maybe later: Catch exception, if pattern not found
                temp.add(musicSource.getPattern(name));
            } else {
                throw new PatternNotFoundException();
            }
        }
        player.play(temp.toArray(new Pattern[temp.size()]));
        player = new Player(); //TODO A bug, that is delaying the start of new Patterns. Keep track of future updates of the framework
    }
}
