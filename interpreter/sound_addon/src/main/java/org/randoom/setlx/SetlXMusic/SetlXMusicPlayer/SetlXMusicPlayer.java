package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayerListener;
import org.jfugue.player.Player;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.NullArgumentsException;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.PatternNotFoundException;
import org.randoom.setlx.SetlXMusic.Patterns.PatternParameters;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.SetlXPatternManager;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.iSetlXPatternManager;
import org.randoom.setlx.exceptions.SetlException;

import javax.sound.midi.Sequence;
import java.util.ArrayList;

/**
 * Created by Lukas on 28.12.2017.
 */
public class SetlXMusicPlayer implements iSetlXMusicPlayer{
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
        for(String name: patternNames) {
            if (musicSource.getPattern(name) != null) { //Id the entry exists, than add it. TODO Maybe later: Catch exception, if pattern not found
                temp.add(musicSource.getPattern(name));
            }else{
                throw new PatternNotFoundException();
            }
        }
        player.play(temp.toArray(new Pattern[temp.size()]));
        player = new Player(); //TODO A bug, that is delaying the start of new Patterns. Keep track of future updates of the framework

    }

    public static void main(String[] args) throws InterruptedException, SetlException {
        Player p = new Player();
        p.getManagedPlayer().addManagedPlayerListener(new ManagedPlayerListener() {
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

            }

            @Override
            public void onResumed() {

            }

            @Override
            public void onSeek(long l) {

            }

            @Override
            public void onReset() {

            }
        });
            iSetlXPatternManager m = new SetlXPatternManager();
            Pattern p1 = new Pattern("C F G C");
        System.out.println(p1.toString());
        try {
            m.addPattern("Test1", p1);
        } catch (NullArgumentsException e) {
            e.printStackTrace();
        }
        try {
            m.addPattern("Test2", new Pattern("G C D G"));
        } catch (NullArgumentsException e) {
            e.printStackTrace();
        }
        iSetlXMusicPlayer pl = new SetlXMusicPlayer(m);
        m.modifyPatternProperty("Test2", PatternParameters.VOICE, 2);
        m.modifyPatternProperty("Test1", PatternParameters.VOICE, 1);
        System.out.println(m.getPattern("Test1").toString());
        try {
            pl.play("Test2", "Test1");
        } catch (PatternNotFoundException e) {
            e.printStackTrace();
        }
        //      pl.play("Test2");
        //     pl.play("Test2");
        Thread.sleep(100);
        System.out.println("Go");
        //      pl.play("Test2");
        System.out.println("Tewat");
    }
}
