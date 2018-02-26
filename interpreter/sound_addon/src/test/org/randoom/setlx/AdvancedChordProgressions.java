package org.randoom.setlx;

import org.jfugue.pattern.Atom;
import org.jfugue.player.ManagedPlayerListener;
import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.ChordProgression;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;

public class AdvancedChordProgressions {
    public static void main(String[] args) throws InterruptedException {
        RealtimePlayer rt = null;
        try {
            rt = new RealtimePlayer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        byte a = 0;
        byte b = 0;
        byte c = 0;
// Das ist ein neuer Kommentar;;;
        rt.play(new Atom(a,b++,c, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));
        rt.play(new Atom(a,b,c++, "C"));

        ChordProgression cp = new ChordProgression("I IV V");
        Player player = new Player();
        player.getManagedPlayer().addManagedPlayerListener(new ManagedPlayerListener() {
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
        player.play(cp.eachChordAs("$0q $1q $2q Rq"));

        player.play(cp.allChordsAs("$0q $0q $0q $0q $1q $1q $2q $0q"));

        player.play(cp.allChordsAs("$0 $0 $0 $0 $1 $1 $2 $0").eachChordAs("V0 $0s $1s $2s Rs V1 $!q"));
    }
}