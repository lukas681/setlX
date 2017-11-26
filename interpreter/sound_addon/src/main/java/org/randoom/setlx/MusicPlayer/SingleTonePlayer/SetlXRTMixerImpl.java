package org.randoom.setlx.MusicPlayer.SingleTonePlayer;

import jm.audio.Instrument;
import jm.audio.RTMixer;
import jm.music.data.Note;
import jm.music.rt.RTLine;
import org.randoom.setlx.MusicPlayer.SingleTonePlayer.instrumentSets.RTPluckInst;

public class SetlXRTMixerImpl extends RTMixer implements SetlXRTMixerIntf {

    //Sample Rate in hz
    private final int sampleRate = 44100;

    public SetlXRTMixerImpl(SetlXRTLineIntf[] rtlines) {
        super((RTLine[]) rtlines);
    }

    public SetlXRTMixerImpl(SetlXRTLineIntf rtline) {
        super(rtlineeee);
        SetlXRTLineIntf[] lines = {rtline};
    }

    @Override
    public void addNewSetlXRTLine(SetlXRTLineIntf[] lines) {

    }

    /**
     * Overrides @addNewSetlXRTLine for adding just one single @{@link RTLine}
     *
     * @param line
     */
    public void addNewSetlXRTLine(SetlXRTLineIntf line) {
        SetlXRTLineIntf[] lines = {line};
        this.addNewSetlXRTLine(lines);
    }

    @Override
    public void removeSetlXRTLine() {

    }


    public static void main(String[] args) throws InterruptedException {
        //int sampleRate = 44100;
        Instrument inst = new RTPluckInst(sampleRate);
        Instrument[] insts = new Instrument[]{inst};

        SetlXRTLineIntf t = new SetlXRTLineImpl(insts);
        final RTMixer rtm = new RTMixer(t);

        System.out.println("GO");
        rtm.begin();
        t.addNote(new Note((int) (Math.random() * 60 + 30), 10,
                (int) (Math.random() * 100 + 27)));
        Thread.sleep(5000);
        rtm.begin();
        System.out.println(System.currentTimeMillis());
        t.addNote(new Note((int) (Math.random() * 60 + 30), 10,
                (int) (Math.random() * 100 + 27)));
        System.out.println(System.currentTimeMillis());
        Thread.sleep(10000);
        rtm.stop();

    }

}
