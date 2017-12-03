package org.randoom.setlx;

import jm.util.Play;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by Lukas on 03.12.2017.
 */
public class test {
    public static void main(String[] args) throws MidiUnavailableException {
        Pattern p1 = new Pattern("Eq Ch. | Eq Ch. | Dq Eq Dq Cq").setVoice(0).setInstrument("Piano");
        Pattern p2 = new Pattern("Eq Ch.     | Eq Ch.     | GmajQQQ  CmajQ").setVoice(1).setInstrument("Flute");
        Player player = new Player();
        player.play(p1.setTempo(200));
        player.play(p1, p2);
    }
}
